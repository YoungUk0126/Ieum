<template>
  <div>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
      <table class="w-full text-sm rtl:text-right text-gray-500 dark:text-gray-400 text-center">
        <thead
          class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
        >
          <tr>
            <th scope="col" class="px-6 py-3">발신자</th>
            <th scope="col" class="px-6 py-3">날짜</th>
            <th scope="col" class="px-6 py-3">내용</th>
            <th scope="col" class="px-6 py-3">전송여부</th>

            <th scope="col" class="px-6 py-3">
              <span class="sr-only">수정</span>
            </th>
            <th scope="col" class="px-6 py-3">
              <span class="sr-only">삭제</span>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(item, index) in items"
            :key="item.message_no"
            class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
          >
            <th
              scope="row"
              class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
            >
              {{ item.message_sender }}
            </th>
            <td class="px-6 py-4">{{ item.message_time }}</td>
            <td class="px-6 py-4">
              <img v-if="item.message_type == 'video'" src="../../assets/images/video.png" />
              <img v-if="item.message_type == 'voice'" src="../../assets/images/voice.png" />
            </td>
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
import { ref, onMounted } from 'vue'
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
</style>
