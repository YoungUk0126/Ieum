<template>
  <div class="content-container">
    <div class="p-4 flex flex-col items-center space-y-4">
      <div class="w-full flex space-x-4">
        <div class="m-3 w-1/4">
          <label for="title" class="block mb-2 text-sm font-medium text-gray-700">제목:</label>
          <input
            type="text"
            id="title"
            class="p-2 border border-gray-300 rounded placeholder-gray-400 focus:outline-none focus:ring focus:border-blue-300"
            placeholder="제목을 입력해주세요"
            v-model="jsonData.eventName"
          />
        </div>
      </div>
      <div class="w-full flex space-x-4">
        <div class="m-3 w-1/4">
          <label for="date" class="block mb-2 text-sm font-medium text-gray-700">날짜:</label>
          <input
            type="date"
            id="start"
            name="trip-start"
            value="2025-01-01"
            min="2018-01-01"
            max="2100-12-31"
            class="w-full p-2 border border-gray-300 rounded placeholder-gray-400 focus:outline-none focus:ring focus:border-blue-300"
            v-model="jsonData.eventDate"
          />
        </div>
      </div>

      <!-- Modal footer -->
      <div class="flex ml-auto items-center mt-4 space-x-4">
        <button
          data-modal-hide="default-modal"
          type="button"
          class="bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500 focus:outline-none focus:ring"
          @click="postAlarmdata"
        >
          확인
        </button>
        <button
          type="button"
          class="text-gray-500 bg-white px-4 py-2 rounded border border-gray-300 hover:text-gray-900 focus:outline-none focus:ring focus:border-blue-300"
          @click="props.closeModal"
        >
          취소
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import { postEvent } from '@/api/modalAlarms/event.js'
import swal from 'sweetalert'

const props = defineProps(['closeModal'])

const jsonData = ref({
  eventName: '',
  eventDate: ''
})

function postAlarmdata() {
  // 시간, 분, 초를 HH:mm:SS 형태로 조합하여 반환합니다.

  if (jsonData.value.eventName === '' || jsonData.value.eventDate === '') {
    swal({
      title: '',
      text: '제목 혹은 날짜를 입력해주세요',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          value: false,
          visible: true,
          className: '',
          closeModal: true
        }
      }
    })

    return 0
  }

  postEvent(jsonData.value, (response) => {
    if (response.data.success === true) {
      swal({
        title: '기념일 알리미',
        text: '기념일 등록이 완료되었습니다!',
        icon: 'success',
        buttons: {
          confirm: {
            text: '확인',
            value: false,
            visible: true,
            className: '',
            closeModal: true
          }
        }
      })
      props.closeModal()
    }
  })
}
</script>

<style scoped></style>

--------------------------------------------------
