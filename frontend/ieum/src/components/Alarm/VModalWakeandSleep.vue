<template>
  <div class="content-container p-6 flex flex-col space-y-4">
    <!-- Time Input Section -->
    <div class="major-c time-input row mb-4">
      <!-- Wake Up Time -->
      <div class="col-8">
        <label for="start" class="block mb-1 text-sm font-medium text-gray-600">기상 시각</label>
      </div>
      <div class="row d-flex align-items-center">
        <div class="col-3 d-flex flex-column"></div>
        <div class="col-8 d-flex">
          <select
            v-model="selectedNumber"
            id="numberSelect1"
            class="p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
          >
            <option v-for="num in numbers1" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">시</span>
          <select
            v-model="selectedNumber2"
            id="numberSelect2"
            class="p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
          >
            <option v-for="num in numbers2" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">분</span>
        </div>
      </div>
    </div>

    <!-- Sleep Time -->
    <div class="major-c time-input row">
      <div class="col-8">
        <label for="start" class="block mb-2 text-sm font-medium text-gray-600">취침 시각</label>
      </div>
      <div class="row d-flex align-items-center mb-4">
        <div class="col-8 d-flex">
          <select
            v-model="selectedNumber3"
            id="numberSelect3"
            class="p-2 border rounded bg-gray-10 border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
          >
            <option v-for="num in numbers3" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">시</span>
          <select
            v-model="selectedNumber4"
            id="numberSelect4"
            class="p-2 border rounded bg-gray-10 border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
          >
            <option v-for="num in numbers4" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">분</span>
        </div>
      </div>
    </div>

    <!-- Modal Footer -->
    <div class="flex justify-end mt-4 space-x-4">
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
</template>

<script setup>
import { ref, defineProps } from 'vue'
import { postSleep } from '@/api/modalAlarms/sleep.js'
import swal from 'sweetalert'

const numbers1 = ref(Array.from({ length: 24 }, (_, i) => i + 1)) // 0부터 23까지의 숫자 배열
const numbers2 = ref(Array.from({ length: 60 }, (_, i) => i))
const numbers3 = ref(Array.from({ length: 24 }, (_, i) => i + 1)) // 0부터 23까지의 숫자 배열
const numbers4 = ref(Array.from({ length: 60 }, (_, i) => i))

const selectedNumber = ref(25) // 초기값 설정
const selectedNumber2 = ref(61)
const selectedNumber3 = ref(25) // 초기값 설정
const selectedNumber4 = ref(61)

// 모달 닫기
const props = defineProps(['closeModal'])

const jsonData = ref({
  sleepStartTime: '',
  sleepEndTime: ''
})

function formatTime(hours, minutes) {
  // 각 값이 한 자리 숫자일 경우 앞에 0을 붙여 두 자리로 만듭니다.
  const formattedHours = String(hours).padStart(2, '0')
  const formattedMinutes = String(minutes).padStart(2, '0')

  // 시간, 분, 초를 HH:mm:SS 형태로 조합하여 반환합니다.
  return `${formattedHours}:${formattedMinutes}:00`
}

const postAlarmdata = () => {
  jsonData.value.sleepStartTime = formatTime(selectedNumber.value, selectedNumber2.value)
  jsonData.value.sleepEndTime = formatTime(selectedNumber3.value, selectedNumber4.value)

  if (jsonData.value.sleepStartTime === '25:61:00' || jsonData.value.sleepEndTime === '25:61:00') {
    swal({
      title: '',
      text: '시간을 입력해주세요',
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

  console.log(jsonData.value)
  postSleep(jsonData.value, (response) => {
    console.log(response)
    if (response.data.success === true) {
      swal({
        title: '기상/취침 알리미',
        text: '기상/취침 알람 등록이 완료되었습니다!',
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
