<template>
  <div>
    <table class="table table-hover table-borderless text-center fw-bold align-middle">
      <thead class="border-bottom">
        <tr>
          <th scope="col mb-3">발신자</th>
          <th scope="col">날짜</th>
          <th scope="col">내용</th>
          <th scope="col">전송여부</th>
          <th scope="col">수정</th>
          <th scope="col">삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in items" :key="item.message_no">
          <th scope="row">{{ item.message_sender }}</th>
          <td>{{ item.message_time }}</td>

          <td>
            <img v-if="item.message_type == 'video'" src="../../assets/images/video.png" />
            <img v-if="item.message_type == 'voice'" src="../../assets/images/voice.png" />
          </td>

          <template v-if="check(item.message_time)">
            <td>전송완료</td>
            <td>
              <input type="button" class="btn btn-secondary" value="수정" disabled />
            </td>
            <td>
              <input type="button" class="btn btn-secondary" value="삭제" disabled />
            </td>
          </template>
          <template v-if="!check(item.message_time)">
            <td>전송전</td>
            <td>
              <input
                type="button"
                class="btn btn-primary"
                value="수정"
                :name="index"
                @click="edit()"
              />
            </td>
            <td>
              <input
                type="button"
                class="btn btn-danger"
                value="삭제"
                :name="item.message_no"
                @click="remove()"
              />
            </td>
          </template>
        </tr>
      </tbody>
    </table>
  </div>
  <!-- Button trigger modal -->
  <button
    id="editModal"
    data-modal-target="default-modal"
    data-modal-toggle="default-modal"
    class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
    type="button"
  >
    Toggle modal
  </button>

  <!-- Modal -->
  <div
    id="default-modal"
    tabindex="-1"
    aria-hidden="true"
    class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
  >
    <div class="relative p-4 w-full max-w-2xl max-h-full">
      <!-- Modal content -->
      <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
        <!-- Main modal -->
        <div
          id="default-modal"
          tabindex="-1"
          aria-hidden="true"
          class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
        >
          <div class="relative p-4 w-full max-w-2xl max-h-full">
            <!-- Modal content -->
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
              <!-- Modal header -->
              <div
                class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600"
              >
                <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
                  Terms of Service
                </h3>
                <button
                  type="button"
                  class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                  data-modal-hide="default-modal"
                >
                  <svg
                    class="w-3 h-3"
                    aria-hidden="true"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 14 14"
                  >
                    <path
                      stroke="currentColor"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
                    />
                  </svg>
                  <span class="sr-only">Close modal</span>
                </button>
              </div>
              <!-- Modal body -->
              <div class="p-4 md:p-5 space-y-4">
                <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
                  With less than a month to go before the European Union enacts new consumer privacy
                  laws for its citizens, companies around the world are updating their terms of
                  service agreements to comply.
                </p>
                <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
                  The European Union’s General Data Protection Regulation (G.D.P.R.) goes into
                  effect on May 25 and is meant to ensure a common set of data rights in the
                  European Union. It requires organizations to notify users as soon as possible of
                  high-risk data breaches that could personally affect them.
                </p>
              </div>
              <!-- Modal footer -->
              <div
                class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
              >
                <button
                  data-modal-hide="default-modal"
                  type="button"
                  class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                >
                  I accept
                </button>
                <button
                  data-modal-hide="default-modal"
                  type="button"
                  class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
                >
                  Decline
                </button>
              </div>
            </div>
          </div>
        </div>
        <!--
          <VVoiceModal v-if="!modalType" :messageState="editSelect"></VVoiceModal>
          <VVideoModal v-if="modalType" :messageState="editSelect"></VVideoModal>
        -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import swal from 'sweetalert'
import { getApi, registApi, modifyApi, removeApi } from '@/api/message.js'
import VVoiceModal from '@/components/message/VVoiceModal.vue'
import VVideoModal from '@/components/message/VVideoModal.vue'

const careId = '1'
const items = ref([])
const modalType = ref(false)
const editSelect = ref({
  message_no: 0,
  message_type: ''
})

const openModal = () => {
  document.getElementById('editModal').click()
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
})

const check = (date) => {
  const today = new Date()
  const targetDate = new Date(date)
  return targetDate.getTime() <= today.getTime()
}

const edit = () => {
  const item = items.value[event.target.name]

  modalType.value = item.message_type === 'video'

  editSelect.value = item

  openModal()
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
