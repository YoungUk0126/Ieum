<template>
  <div class="content-container">
    <!-- 총 1개의 부분으로 분리
      * 대분류 div 태그에는 major-c 태그 부여
    2. 시각 입력부(아침, 점심, 저녁)
    -->
    <!-- 2-1. 시각 입력부 dropdown(아침)-->
    <div class="major-c time-input row">
      <div class="col-8">
        <h3>아침</h3>
      </div>
      <div class="row d-flex align-items-center">
        <div class="col-8 d-flex">
          <select v-model="selectedNumber" id="numberSelect">
            <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers1" :key="num" :value="num">{{ num }}</option>
          </select>
          <span>시</span>
          <select v-model="selectedNumber2" id="numberSelect2" class="minute-select">
            <!-- 0부터 59까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers2" :key="num" :value="num">{{ num }}</option>
          </select>
          <span>분</span>
        </div>
      </div>
    </div>
    <!-- 2-2. 시각 입력부 dropdown(점심)-->
    <div class="major-c time-input row">
      <div class="col-8">
        <h3>점심</h3>
      </div>
      <div class="row d-flex align-items-center">
        <div class="col-8 d-flex">
          <select v-model="selectedNumber3" id="numberSelect">
            <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers3" :key="num" :value="num">{{ num }}</option>
          </select>
          <span>시</span>
          <select v-model="selectedNumber4" id="numberSelect2" class="minute-select">
            <!-- 0부터 59까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers4" :key="num" :value="num">{{ num }}</option>
          </select>
          <span>분</span>
        </div>
      </div>
    </div>
    <!-- 2-3. 시각 입력부 dropdown(저녁)-->
    <div class="major-c time-input row">
      <div class="col-8">
        <h3>저녁</h3>
      </div>
      <div class="row d-flex align-items-center">
        <div class="col-8 d-flex">
          <select v-model="selectedNumber5" id="numberSelect">
            <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers5" :key="num" :value="num">{{ num }}</option>
          </select>
          <span>시</span>
          <select v-model="selectedNumber6" id="numberSelect2" class="minute-select">
            <!-- 0부터 59까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers6" :key="num" :value="num">{{ num }}</option>
          </select>
          <span>분</span>
        </div>
      </div>
    </div>
    <!-- Modal footer -->
    <div
      class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
    >
      <button
        data-modal-hide="default-modal"
        type="button"
        class="text-white bg-blue-700 hover:bg-blue-800"
        @click="postAlarmdata"
      >
        확인
      </button>
      <button
        data-modal-hide="default-modal"
        type="button"
        class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
      >
        취소
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import { postInject } from '@/api/modalAlarms/injection.js'
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

  postInject(jsonData)
    .then(() => {
      // API 호출이 성공한 경우에만 모달을 닫습니다.
      props.closeModal()
    })
    .catch((error) => {
      console.error('API 호출 중 오류 발생:', error)
    })
}
</script>

<style scoped>
.btn {
  border: 1px black solid;
}
.modal-dialog {
  width: 40%;
}
.alarm-btn,
.register-button {
  padding-top: 6px;
  padding-bottom: 5.11px;
  padding-left: 20px;
  padding-right: 20px;
  background: #33a38f;
  border-radius: 10px;
  overflow: hidden;
  border: 1px #1de4c1 solid;
  justify-content: center;
  align-items: center;
  display: inline-flex;
  color: white;
  font-size: 16px;
  font-family: Work Sans;
  font-weight: 700;
  line-height: 24px;
  word-wrap: break-word;
  margin-right: 5%;
}

.close-btn {
  display: none;
}

.title-input {
  width: 60%;
}
.register-button.font {
  color: #f5f5f5;
  font-size: 25px;
  font-family: Inter;
  font-weight: 700;
  word-wrap: break-word;
}

.calendar-box {
  width: 60%;
  height: 100%;
  margin-left: 3%;
  padding: 0;
  background: white;
  border-radius: 3px;
  border: 1px rgba(0, 0, 0, 0.1) solid;
}
</style>
