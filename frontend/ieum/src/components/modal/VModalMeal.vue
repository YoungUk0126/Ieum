<template>
  <div class="content-container p-6 flex flex-col space-y-4">
    <!-- 총 1개의 부분으로 분리
      * 대분류 div 태그에는 major-c 태그 부여
    2. 시각 입력부(아침, 점심, 저녁)
    -->
    <!-- 2-1. 시각 입력부 dropdown(아침)-->
    <div class="major-c time-input row">
      <div class="col-8">
        <label class="block mb-1 text-sm font-medium text-gray-600">아침</label>
      </div>
      <div class="row d-flex align-items-center">
        <div class="col-8 d-flex">
          <select v-model="selectedNumber" id="numberSelect" class="p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400">
            <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers1" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">시</span>
          <select v-model="selectedNumber2" id="numberSelect2" class="p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400">
            <!-- 0부터 59까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers2" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">분</span>
        </div>
      </div>
    </div>
    <!-- 2-2. 시각 입력부 dropdown(점심)-->
    <div class="major-c time-input row">
      <div class="col-8">
        <label class="block mb-1 text-sm font-medium text-gray-600">점심</label>
      </div>
      <div class="row d-flex align-items-center">
        <div class="col-8 d-flex">
          <select v-model="selectedNumber3" id="numberSelect" class="p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400">
            <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers3" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">시</span>
          <select v-model="selectedNumber4" id="numberSelect2" class="p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400">
            <!-- 0부터 59까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers4" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">분</span>
        </div>
      </div>
    </div>
    <!-- 2-3. 시각 입력부 dropdown(저녁)-->
    <div class="major-c time-input row">
      <div class="col-8">
        <label class="block mb-1 text-sm font-medium text-gray-600">저녁</label>
      </div>
      <div class="row d-flex align-items-center">
        <div class="col-8 d-flex">
          <select v-model="selectedNumber5" id="numberSelect" class="p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400">
            <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers5" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">시</span>
          <select v-model="selectedNumber6" id="numberSelect2" class="p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400">
            <!-- 0부터 59까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers6" :key="num" :value="num">{{ num }}</option>
          </select>
          <span class="ml-2 mr-2">분</span>
        </div>
      </div>
    </div>
    <!-- Modal footer -->
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
import { postMeal } from '@/api/modalAlarms/meal.js'
import swal from 'sweetalert'


// 오전
const numbers1 = ref(Array.from({ length: 7 }, (_, i) => i + 5)) // 0부터 23까지의 숫자 배열
const numbers2 = ref(Array.from({ length: 60 }, (_, i) => i))
const selectedNumber = ref(25) // 초기값 설정
const selectedNumber2 = ref(61)
// 점심
const numbers3 = ref(Array.from({ length: 7 }, (_, i) => i + 11)) // 0부터 23까지의 숫자 배열
const numbers4 = ref(Array.from({ length: 60 }, (_, i) => i))
const selectedNumber3 = ref(25) // 초기값 설정
const selectedNumber4 = ref(61)
// 저녁
const numbers5 = ref(Array.from({ length: 7 }, (_, i) => i + 17)) // 0부터 23까지의 숫자 배열
const numbers6 = ref(Array.from({ length: 60 }, (_, i) => i))
const selectedNumber5 = ref(25) // 초기값 설정
const selectedNumber6 = ref(61)

const jsonData = ref({
  mealTime1: '',
  mealTime2: '',
  mealTime3: ''
})

// 모달 닫기
const props = defineProps(['closeModal'])

function formatTime(hours, minutes) {
  // 각 값이 한 자리 숫자일 경우 앞에 0을 붙여 두 자리로 만듭니다.
  const formattedHours = String(hours).padStart(2, '0')
  const formattedMinutes = String(minutes).padStart(2, '0')

  // 시간, 분, 초를 HH:mm:SS 형태로 조합하여 반환합니다.
  return `${formattedHours}${formattedMinutes}00`
}

const postAlarmdata = () => {
  jsonData.value.mealTime1 = formatTime(selectedNumber.value, selectedNumber2.value)
  jsonData.value.mealTime2 = formatTime(selectedNumber3.value, selectedNumber4.value)
  jsonData.value.mealTime3 = formatTime(selectedNumber5.value, selectedNumber6.value)

  if (
    jsonData.value.mealTime1 === '256100' ||
    jsonData.value.mealTime2 === '256100' ||
    jsonData.value.mealTime3 === '256100'
  ) {
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
  postMeal(jsonData.value, (response) => {
    console.log(response)
    if (response.data.success === true) {
      swal({
      title: '식사 알리미',
      text: '식사 알람 등록이 완료되었습니다!',
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
</style>
