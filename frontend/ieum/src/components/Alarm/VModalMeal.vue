<template>
  <div class="relative p-4 w-full max-w-2xl max-h-full">
    <div class="content-container relative p-6 flex flex-col space-y-4">
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
            <input
              v-model="selectedNumber"
              class="w-1/5 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
            <span class="ml-2 mr-2">시</span>
            <input
              v-model="selectedNumber2"
              class="w-1/5 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
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
            <input
              v-model="selectedNumber3"
              class="w-1/5 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
            <span class="ml-2 mr-2">시</span>
            <input
              v-model="selectedNumber4"
              class="w-1/5 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
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
            <input
              v-model="selectedNumber5"
              class="w-1/5 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
            <span class="ml-2 mr-2">시</span>
            <input
              v-model="selectedNumber6"
              class="w-1/5 p-2 border rounded bg-gray-10 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            />
            <span class="ml-2 mr-2">분</span>
          </div>
        </div>
      </div>
      <!-- Modal footer -->
      <div class="flex justify-end mt-4 space-x-4">
        <button
          :data-modal-hide="props.mainModal"
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
import { ref, defineProps, onMounted } from 'vue'
import { modifyMeal, postMeal, getMeal } from '@/api/modalAlarms/meal.js'
import swal from 'sweetalert'

const mealInfoNo = ref()

onMounted(() => {
  getMeal(({ data }) => {
    if (data.data === false) {
      // 이전 데이터가 없는 경우
      mealInfoNo.value = undefined
    } else {
      // 이전 데이터
      // data.data
      // 변환해서 selectedNumber에 각각 넣어주기
      selectedNumber.value = formatTimeH(data.data.mealTime1)
      selectedNumber2.value = formatTimeM(data.data.mealTime1)
      selectedNumber3.value = formatTimeH(data.data.mealTime2)
      selectedNumber4.value = formatTimeM(data.data.mealTime2)
      selectedNumber5.value = formatTimeH(data.data.mealTime3)
      selectedNumber6.value = formatTimeM(data.data.mealTime3)
      mealInfoNo.value = data.data.mealInfoNo
      console.log(data.data)
    }
  })
})
// 오전
const selectedNumber = ref() // 초기값 설정
const selectedNumber2 = ref()

// 점심
const selectedNumber3 = ref() // 초기값 설정
const selectedNumber4 = ref()

// 저녁
const selectedNumber5 = ref() // 초기값 설정
const selectedNumber6 = ref()

const jsonData = ref({
  mealTime1: '',
  mealTime2: '',
  mealTime3: ''
})

// 모달 닫기
const props = defineProps(['closeModal', 'modalId1'])

const formatTimeM = (data) => {
  let temp = data
  temp = temp % 3600
  return Math.floor(temp / 60)
}

const formatTimeH = (data) => {
  return Math.floor(data / 3600)
}

const formatTime2 = (hours, minutes) => {
  const formattedHours = hours * 3600
  const formattedMinutes = minutes * 60

  return formattedHours + formattedMinutes
}

const postAlarmdata = () => {
  jsonData.value.mealTime1 = formatTime2(selectedNumber.value, selectedNumber2.value)
  jsonData.value.mealTime2 = formatTime2(selectedNumber3.value, selectedNumber4.value)
  jsonData.value.mealTime3 = formatTime2(selectedNumber5.value, selectedNumber6.value)

  console.log(jsonData.value)
  if (
    jsonData.value.mealTime1 === '' ||
    jsonData.value.mealTime2 === '' ||
    jsonData.value.mealTime3 === ''
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

  if (mealInfoNo.value === undefined) {
    console.log(mealInfoNo.value)
    postMeal(jsonData.value, (response) => {
      console.log(response.data)
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
        }).then = () => {
          props.closeModal()
        }
      }
    })
  } else {
    jsonData.value.mealInfoNo = mealInfoNo.value
    modifyMeal(jsonData.value, (response) => {
      console.log(response.data)
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
        }).then = () => {
          props.closeModal()
        }
      }
    })
  }
}
</script>

<style scoped></style>
