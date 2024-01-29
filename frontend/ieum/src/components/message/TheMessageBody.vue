<template>
  <div class="relative overflow-x-auto sm:rounded-lg">
    <div class="mb-4 border-b border-gray-200 dark:border-gray-700">
      <ul
        class="flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:border-gray-700 dark:text-gray-400"
      >
        <li class="me-2">
          <a
            href="#"
            aria-current="page"
            class="inline-block p-4 text-blue-600 bg-gray-100 rounded-t-lg active dark:bg-gray-800 dark:text-blue-500"
            >All</a
          >
        </li>
        <li class="me-2">
          <a
            href="#"
            class="inline-block p-4 rounded-t-lg hover:text-gray-600 hover:bg-gray-50 dark:hover:bg-gray-800 dark:hover:text-gray-300"
            >before</a
          >
        </li>
        <li class="me-2">
          <a
            href="#"
            class="inline-block p-4 rounded-t-lg hover:text-gray-600 hover:bg-gray-50 dark:hover:bg-gray-800 dark:hover:text-gray-300"
            >after</a
          >
        </li>
      </ul>
    </div>
    <!-- Card -->
    <div class="flex flex-wrap">
      <div
        v-for="(item, index) in items"
        :key="item.message_no"
        class="flex-shrink-0 min-w-100 sm:w-full md:w-1/2 lg:w-1/3 xl:w-1/4 p-5"
      >
        <div
          :class="{
            'bg-gray-200': check(item.message_time),
            'bg-white': !check(item.message_time)
          }"
          class="px-3 pt-8 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
        >
          <div class="flex flex-col items-center pb-5">
            <img
              class="w-24 h-24 mb-3 rounded-full shadow-lg"
              :src="'/src/assets/images/' + item.message_type + '.png'"
            />
            <h5 class="mb-1 text-xl font-medium text-gray-900 dark:text-white">
              {{ item.message_sender }}
            </h5>
            <span class="text-sm text-gray-500 dark:text-gray-400">{{ item.message_time }}</span>
            <template v-if="check(item.message_time)">
              <div class="flex mt-4 md:mt-6 gap-3">
                <button
                  type="button"
                  class="inline-flex items-center text-white bg-gray-300 dark:bg-gray-300 cursor-not-allowed font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                  disabled
                >
                  수정
                </button>
                <button
                  type="button"
                  class="text-white bg-gray-300 dark:bg-gray-300 cursor-not-allowed font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                  disabled
                >
                  삭제
                </button>
              </div>
            </template>
            <template v-if="!check(item.message_time)">
              <div class="flex mt-4 md:mt-6 gap-3">
                <button
                  type="button"
                  class="inline-flex items-center text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                  :name="index"
                  @click="edit()"
                >
                  수정
                </button>
                <button
                  type="button"
                  class="inline-flex items-center focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
                  :name="item.message_no"
                  @click="remove()"
                >
                  삭제
                </button>
              </div>
            </template>
          </div>
        </div>
      </div>
    </div>

    <table class="w-full text-sm rtl:text-right text-gray-500 dark:text-gray-400 text-center">
      <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
          <th scope="col" class="px-6 py-3">발신자</th>
          <th scope="col" class="px-6 py-3">날짜</th>
          <th scope="col" class="px-6 py-3">내용</th>
          <th scope="col" class="px-6 py-3">전송여부</th>

          <th scope="col" class="px-6 py-3">
            <span>수정</span>
          </th>
          <th scope="col" class="px-6 py-3">
            <span>삭제</span>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(item, index) in items"
          :key="item.message_no"
          class="bg-white dark:bg-gray-800 hover:bg-gray-50 dark:hover:bg-gray-600"
        >
          <th
            scope="row"
            class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
          >
            <img
              class="mx-auto"
              v-if="item.message_type == 'video'"
              src="../../assets/images/video.png"
            />
            <img
              class="mx-auto"
              v-if="item.message_type == 'voice'"
              src="../../assets/images/voice.png"
            />
          </th>
          <td class="px-6 py-4">
            {{ item.message_sender }}
          </td>
          <td class="px-6 py-4">{{ item.message_time }}</td>
          <template v-if="check(item.message_time)">
            <td class="px-6 py-4">전송완료</td>
            <td class="px-6 py-4">
              <button
                type="button"
                class="text-white bg-gray-300 dark:bg-gray-300 cursor-not-allowed font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                disabled
              >
                수정
              </button>
            </td>
            <td class="px-6 py-4">
              <button
                type="button"
                class="text-white bg-gray-300 dark:bg-gray-300 cursor-not-allowed font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                disabled
              >
                삭제
              </button>
            </td>
          </template>
          <template v-if="!check(item.message_time)">
            <td class="px-6 py-4">전송전</td>
            <td class="px-6 py-4">
              <button
                type="button"
                class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                :name="index"
                @click="edit()"
              >
                수정
              </button>
            </td>
            <td class="px-6 py-4">
              <button
                type="button"
                class="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
                :name="item.message_no"
                @click="remove()"
              >
                삭제
              </button>
            </td>
          </template>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- Modal toggle -->
  <button
    hidden
    class="hidden w-full md:w-auto text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
    type="button"
  ></button>
  <!-- Main modal -->
  <div
    id="edit-modal"
    tabindex="-1"
    class="fixed top-0 left-0 right-0 z-50 hidden w-full p-4 overflow-x-hidden overflow-y-auto md:inset-0 h-[calc(100%-1rem)] max-h-full"
  >
    <div class="relative w-full max-w-lg max-h-full">
      <!-- Modal content -->
      <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
        <VVoiceModal v-if="!modalType" :messageState="editSelect" :modal="modal"></VVoiceModal>
        <VVideoModal v-if="modalType" :messageState="editSelect" :modal="modal"></VVideoModal>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import swal from 'sweetalert'
import { getApi, removeApi } from '@/api/message.js'
import VVoiceModal from '@/components/message/VVoiceModal.vue'
import VVideoModal from '@/components/message/VVideoModal.vue'
import { Modal } from 'flowbite'
import 'flowbite/dist/datepicker.js'
const careId = '1'
const items = ref([])
const modalType = ref(false)
const editSelect = ref({
  message_no: 0,
  message_type: ''
})
const modal = ref()

const openModal = () => {
  modal.value.show()
}

onMounted(() => {
  getApi(
    careId,
    (response) => {
      items.value = response.data.data
    },
    () => {
      alert('조회 실패')
    }
  )
  initModal()
})

const initModal = () => {
  const $targetEl = document.getElementById('edit-modal')

  // options with default values
  const options = {
    placement: 'center',
    backdrop: 'static',
    backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
      console.log('modal is hidden')
    },
    onShow: () => {
      console.log('modal is shown')
    },
    onToggle: () => {
      console.log('modal has been toggled')
    }
  }

  // instance options object
  const instanceOptions = {
    id: 'edit-modal',
    override: true
  }

  modal.value = new Modal($targetEl, options, instanceOptions)
}

const check = (date) => {
  const today = new Date()
  const targetDate = new Date(date)
  return targetDate.getTime() <= today.getTime()
}

const edit = () => {
  const item = items.value[event.target.name]

  modalType.value = item.message_type === 'video'

  editSelect.value = item

  modal.value.show()
}

const remove = () => {
  swal({
    title: '주의',
    text: '해당 메세지를 삭제하시겠습니까?',
    icon: 'warning',
    buttons: {
      cancel: {
        text: '취소',
        value: false,
        visible: true,
        className: '',
        closeModal: true
      },
      confirm: {
        text: '확인',
        value: event.target.name,
        visible: true,
        className: '',
        closeModal: true
      }
    }
  }).then((value) => {
    if (value !== false) {
      removeSuccess(value)
    }
  })
}

const removeSuccess = (id) => {
  removeApi(
    id,
    // success
    ({ data }) => {
      if (data.success) {
        // 삭제가 성공됐다면 리스트 다시 불러오기
        getApi(
          careId,
          (response) => {
            items.value = response.data.data
          },
          () => {
            alert('조회 실패')
          }
        )
      }
    },
    // fail
    (value) => {
      console.log('fail')
    }
  )
}

//swal 예시
/*
아이콘
success, error, warning, info, question
swal({
타이틀, 내용, 아이콘, 버튼
})
.then((value) => {})
입력 받는 창인 경우 위처럼 value 설정

*/
</script>

<style>
.swal-footer {
  text-align: center;
}

@media (max-width: 640px) {
  .min-w-100 {
    width: 100%; /* 미디어 쿼리에 따라 max-width를 해제합니다. */
  }
}

.end-message {
  background-color: blue;
}
</style>
