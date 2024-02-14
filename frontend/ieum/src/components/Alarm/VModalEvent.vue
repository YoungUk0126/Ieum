<template>
  <div class="relative p-4 w-full max-w-2xl max-h-full">
    <div class="content-container p-6 flex flex-col space-y-4">
      <!-- 이벤트 Input Section -->
      <div class="major-c time-input row mb-4">
        <!-- 이벤트명 -->
        <div class="col-8">
          <label for="start" class="block mb-1 text-sm font-medium text-gray-600">일정 이름</label>
        </div>
        <div class="row d-flex align-items-center">
          <div class="col-3 d-flex flex-column"></div>
          <div class="col-8 d-flex">
            <input
              v-model="eventName"
              class="w-full mt-2 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
          </div>
        </div>
      </div>

      <!-- 이벤트 날짜 -->
      <div class="major-c time-input row">
        <div class="w-full flex space-x-4">
          <div class="mb-3 w-1/4">
            <label for="date" class="block mb-2 text-sm font-medium text-gray-700">날짜:</label>
            <input
              type="date"
              id="start"
              name="trip-start"
              value="2025-01-01"
              min="1900-01-01"
              max="2100-12-31"
              class="p-2 border border-gray-300 rounded placeholder-gray-400 focus:outline-none focus:ring focus:border-blue-300"
              v-model="selectedDate"
            />
          </div>
        </div>
      </div>

      <!-- 이벤트 시각 -->
      <div class="major-c time-input row">
        <div class="col-8">
          <label for="start" class="block mb-2 text-sm font-medium text-gray-600">일정 시각</label>
        </div>
        <div class="row d-flex align-items-center mb-4">
          <div class="col-8 d-flex">
            <input
              v-model="selectedHour"
              class="w-1/5 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
            <span class="ml-2 mr-2">시</span>
            <input
              v-model="selectedMinute"
              class="w-1/5 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
            <span class="ml-2 mr-2">분</span>
          </div>
        </div>
      </div>

      <!-- Modal Footer -->
      <div class="flex justify-end mt-4 space-x-4">
        <button
          type="button"
          class="bg-green-400 text-white px-4 py-2 rounded hover:bg-green-500 focus:outline-none focus:ring"
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

// 1. 사용자 입력받기

// 1-1 사용자 입력값
const eventName = ref()
const selectedDate = ref()
const selectedHour = ref()
const selectedMinute = ref()

// 모달 닫기
const props = defineProps(['closeModal'])

const jsonData = ref({
  eventName: '',
  eventDate: ''
})

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

// 2. json format으로 변경 후 post

function formatDate(date, hours, minutes) {
  // 각 값이 한 자리 숫자일 경우 앞에 0을 붙여 두 자리로 만듭니다.
  const formattedHours = String(hours).padStart(2, '0')
  const formattedMinutes = String(minutes).padStart(2, '0')

  // 시간, 분, 초를 HH:mm:SS 형태로 조합하여 반환합니다.
  return `${date} ${formattedHours}:${formattedMinutes}:00`
}

const postAlarmdata = () => {
  jsonData.value.eventDate = formatDate(
    selectedDate.value,
    selectedHour.value,
    selectedMinute.value
  )

  if (
    jsonData.value.selectedDate === '' ||
    jsonData.value.selectedHour === '' ||
    jsonData.value.selectedDate === ''
  ) {
    swal({
      title: '',
      text: '시각 혹은 날짜를 입력해주세요',
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
    return
  }

  postEvent(jsonData.value, (response) => {
    console.log(response)
    if (response.data.success === true) {
      swal({
        title: '이벤트 알리미',
        text: '이벤트 알람 등록이 완료되었습니다!',
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

<style scoped>
.time-select {
  margin-right: 0.5rem;
}
</style>
