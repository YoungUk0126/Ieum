<template>
  <div
    data-modal-backdrop="static"
    :id="props.injectModal"
    tabindex="-1"
    aria-hidden="true"
    class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
  >
    <button :data-modal-hide="props.injectModal" id="btn-close2" type="button" class="hidden">
      취소
    </button>
    <div class="relative p-4 w-full max-w-2xl max-h-full">
      <!-- Modal content -->

      <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
        <!-- Modal header -->
        <div class="modal-header text-3xl rounded-lg text-green-600 font-bold p-8">투약 등록</div>
        <!-- Modal body -->
        <div class="modal-body container mx-auto p-6">
          <!-- 1. 약 이름 입력부 input -->
          <div class="mb-8">
            <label for="title" class="block mb-2 text-sm font-medium text-gray-700">제목</label>
            <input
              id="title"
              type="text"
              class="p-2 w-2/5 border rounded"
              placeholder="약의 이름을 입력해주세요"
              v-model="pill_name"
            />
          </div>

          <!-- 2. 시작일/종료일 calendar -->
          <div class="grid grid-cols-2 gap-4 mb-8">
            <div>
              <label for="start" class="block mb-2 text-sm font-medium text-gray-600"
                >투약 시작일</label
              >
              <input
                type="date"
                id="start"
                name="start"
                value="2025-01-01"
                min="2018-01-01"
                max="2100-12-31"
                v-model="start_date"
                class="w-full p-2 border rounded"
              />
            </div>
            <div>
              <label for="end" class="block mb-2 text-sm font-medium text-gray-600"
                >투약 종료일</label
              >
              <input
                type="date"
                id="end"
                name="end"
                value="2025-01-01"
                min="2018-01-01"
                max="2100-12-31"
                v-model="end_date"
                class="w-full p-2 border rounded"
              />
            </div>
          </div>

          <!-- 3. 요일 선택부 -->
          <label class="block mb-2 text-sm font-medium text-gray-700">요일</label>
          <ul
            class="mb-8 items-center text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-white"
          >
            <li
              v-for="(dayCheckbox, index) in day"
              :key="index"
              id="days"
              class="flex items-center ps-3 border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
            >
              <input
                :id="'dayCheckbox' + index"
                type="checkbox"
                class="w-4 h-4 text-green-600 bg-gray-100 border-gray-300"
                @click="setDay(index)"
              />
              <label
                :for="'dayCheckbox' + index"
                class="form-check-label py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >
                {{ ['월', '화', '수', '목', '금', '토', '일'][index] }}
              </label>
            </li>
          </ul>

          <!-- 4. 식전/식후 입력부 dropdown -->
          <label class="block mb-2 text-sm font-medium text-gray-700">식전/식후</label>
          <div class="mb-8">
            <div class="flex items-center space-x-2">
              <input
                type="radio"
                class="btn-check"
                name="vbtn-radio"
                id="beforemeal"
                autocomplete="off"
                value="식전"
                v-model="beforeAfter"
              />
              <label class="btn btn-outline-secondary" for="beforemeal">식전</label>
              <input
                type="radio"
                class="btn-check"
                name="vbtn-radio"
                id="aftermeal"
                autocomplete="off"
                value="식후"
                v-model="beforeAfter"
              />
              <label class="btn btn-outline-secondary" for="aftermeal">식후</label>
            </div>
          </div>

          <!-- 5. 시각 -->
          <label class="block mb-2 text-sm font-medium text-gray-700">복용시각</label>
          <div class="flex items-center mb-8 space-x-2">
            <select
              v-model="selectedHour"
              class="border bg-gray-10 border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            >
              <option v-for="num in hour" :key="num" :value="num">{{ num }}</option>
            </select>
            <span>시</span>
            <select
              v-model="selectedMinute"
              class="border bg-gray-10 border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-gray-500 focus:border-gray-500 p-2.5 dark:bg-gray-400 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-gray-400 dark:focus:border-gray-400"
            >
              <option v-for="num in minute" :key="num" :value="num">{{ num }}</option>
            </select>
            <span>분</span>
          </div>

          <!-- 6. 투약 일정에 추가 -->
          <div class="text-right mb-8">
            <button
              type="button"
              class="text-gray-500 bg-white px-4 py-2 rounded border border-gray-300 hover:text-gray-900 focus:outline-none focus:ring focus:border-blue-300"
              @click="addSchedule"
            >
              일정 추가
            </button>
          </div>

          <!-- 7. 투약 일정 -->
          <div class="mb-8 p-8 border rounded bg-gray-300">
            <div class="flex justify-between mb-4">
              <h3 class="text-lg font-semibold">투약 일정</h3>
              <button
                class="btn btn-outline-secondary px-2 py-1 rounded border border-gray-500 bg-gray-300"
                @click="deleteSelectedSchedules"
              >
                삭제
              </button>
            </div>
            <ul class="list-group space-y-3">
              <li
                class="list-group-item p-2 items-center text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex dark:bg-gray-700 dark:border-gray-600 dark:text-white"
                v-for="(data, index) in scheduleDatas"
                :key="index"
              >
                <input
                  class="form-check-input me-1 px-2"
                  type="checkbox"
                  :value="index"
                  :id="'checkbox' + index"
                  v-model="selectedSchedules"
                />
                <label class="title-text pl-2" :for="'checkbox' + index">{{ data[0] }} /</label>
                <label class="day-text pl-2" :for="'checkbox' + index">{{ data[1] }} /</label>
                <label class="time-text pl-2" :for="'checkbox' + index">{{ data[2] }}</label>
              </li>
            </ul>
          </div>

          <!-- Modal footer -->
          <div class="flex justify-end mt-4 space-x-4">
            <button
              type="button"
              class="bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500 focus:outline-none focus:ring"
              @click="postAlarmdata"
            >
              확인
            </button>
            <button
              type="button"
              class="text-gray-500 bg-white px-4 py-2 rounded border border-gray-300 hover:text-gray-900 focus:outline-none focus:ring focus:border-blue-300"
              @click="closeModal"
            >
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import { postInject } from '@/api/modalAlarms/injection.js'
import swal from 'sweetalert'
import moment from 'moment'

// 0. 모달 닫기

const closeModal = () => {
  document.getElementById('btn-close2').click()
}

const props = defineProps({ injectModal: String })

// 1 약 이름
const pill_name = ref('')

//--------------------------------------------------------------------------------------------------------------------------------------

// 2 시작일 / 끝일
const start_date = ref('')
const end_date = ref('')

//--------------------------------------------------------------------------------------------------------------------------------------

// 3 요일 설정
const day = ref(['0', '0', '0', '0', '0', '0', '0'])

const setDay = (param) => {
  if (day.value[param] === '0') {
    day.value[param] = '1'
  } else {
    day.value[param] = '0'
  }
}

//--------------------------------------------------------------------------------------------------------------------------------------

// 4 식전, 식후 분별
const beforeAfter = ref()

//--------------------------------------------------------------------------------------------------------------------------------------

// 5. 시각
const hour = ref(Array.from({ length: 24 }, (_, i) => i + 1)) // 0부터 23까지의 숫자 배열
const minute = ref(Array.from({ length: 60 }, (_, i) => i))

const selectedHour = ref(25) // 시
const selectedMinute = ref(61) // 분

const isValidTimeFormat = (timeString) => {
  // 시간 형식 확인을 위한 정규표현식
  const timeRegex = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/
  return timeRegex.test(timeString)
}
//--------------------------------------------------------------------------------------------------------------------------------------

// 6 투약 일정에 추가
const scheduleDatas = ref([])

// 6-1 요일 입력 체크
const allElementsZero = (arr) => {
  return arr.every((element) => element === '0')
}

// 6-2 데이터 체크
const checkInputDatas = () => {
  if (
    // 약 이름, 투약 시작 및 끝일자를 입력하지 않은 경우
    pill_name.value === '' ||
    start_date.value === '' ||
    end_date.value === ''
  ) {
    swal({
      title: '',
      text: '제목 혹은 투약 기간을 설정해주세요',
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
  } else if (start_date.value > end_date.value) {
    // 투약 시작일이 투약 종료일보다 늦는 경우
    swal({
      title: '',
      text: '투약 시작일과 투약 종료일을 정확히 설정해주세요',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          value: false,
          visible: true,
          className: ''
        }
      }
    })
    return 0
  } else if (allElementsZero(day.value)) {
    // 요일을 설정하지 않은 경우
    swal({
      title: '',
      text: '요일을 설정해주세요',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          value: false,
          visible: true,
          className: ''
        }
      }
    })
    return 0
  } else if (!beforeAfter.value) {
    // 식전 혹은 식후를 설정하지 않은 경우
    swal({
      title: '',
      text: '식전 및 식후 설정해주세요',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          value: false,
          visible: true,
          className: ''
        }
      }
    })

    return 0
  } else if (selectedHour.value === 25 || selectedMinute.value === 61) {
    // 투약 시각을 설정하지 않은 경우
    swal({
      title: '',
      text: '투약 시각을 설정해주세요',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          value: false,
          visible: true,
          className: ''
        }
      }
    })

    return 0
  } else if (scheduleDatas.value.length >= 3) {
    // 투약 일정이 3개 이상인 경우
    swal({
      title: '',
      text: '투약 일정은 복용 시각별로 3개 이하로 설정해주세요',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          value: false,
          visible: true,
          className: ''
        }
      }
    })
    return 0
  }

  return 1
}

// 6-3 투약 일정 format
const addSchedule = () => {
  if (checkInputDatas()) {
    const scheduleData = ['제목', '', '시각']

    scheduleData[0] = pill_name.value
    scheduleData[1] = day.value
      .reduce((acc, curr, index) => {
        if (curr === '1') {
          acc.push(['월', '화', '수', '목', '금', '토', '일'][index])
        }
        return acc
      }, [])
      .join(', ')
    scheduleData[2] = formatTime(`${selectedHour.value}`, `${selectedMinute.value}`)

    scheduleDatas.value.push(scheduleData)
  }
}

//--------------------------------------------------------------------------------------------------------------------------------------

// 7 jsonData

// 7-2 시각의 형태를 시:분:초의 데이터 형태로 변경
const formatTime = (hours, minutes) => {
  // 각 값이 한 자리 숫자일 경우 앞에 0을 붙여 두 자리로 만듭니다.
  const formattedHours = String(hours).padStart(2, '0')
  const formattedMinutes = String(minutes).padStart(2, '0')

  // 시간, 분, 초를 HH:mm:SS 형태로 조합하여 반환합니다.
  return `${formattedHours}:${formattedMinutes}:00`
}

// 7-2-2 일정에 담긴 시각들의 형태를 json데이터 형태로 변경
const settimetosecond = (formattime) => {
  const hour = parseInt(formattime.substring(0, 2)) * 3600
  const minute = parseInt(formattime.substring(3, 5)) * 60

  return hour + minute
}

const settimetolong = (formattime) => {
  const selectedTime = moment().set({
    hour: parseInt(formattime.substring(0, 2)),
    minute: parseInt(formattime.substring(3, 5)),
    second: 0
  })

  return selectedTime.valueOf() // Unix 시간을 반환
}

// 7-3 약 시각들 추가
const addPillTime = (jsondata) => {
  const scheduleDataPillTimes = new Set()

  for (let schedule of scheduleDatas.value) {
    scheduleDataPillTimes.add(settimetosecond(schedule[2]))
  }

  // for (let time in scheduleDataPillTimes) {
  //   if (isValidTimeFormat(time)) {
  //     // 투약 시각을 설정하지 않은 경우
  //     swal({
  //       title: '',
  //       text: '투약 시각을 설정해주세요',
  //       icon: 'error',
  //       buttons: {
  //         confirm: {
  //           text: '확인',
  //           value: false,
  //           visible: true,
  //           className: ''
  //         }
  //       }
  //     })
  //     return 0
  //   }
  // }

  for (let time of scheduleDataPillTimes) {
    const jsonPillTime = ref({ pillTakeTime: '' })
    jsonPillTime.value.pillTakeTime = `${time}`
    jsondata.value.pillTimes.push(jsonPillTime.value)
  }

  return 1
}

// 7-4 체크된 투약 일정 삭제
// 선택된 일정을 담는 배열
const selectedSchedules = ref([])

const deleteSelectedSchedules = () => {
  if (selectedSchedules.value.length === 0) {
    // 삭제할 일정이 선택되지 않은 경우
    return
  }

  // 선택된 일정을 역순으로 정렬하여 삭제하기
  selectedSchedules.value
    .sort((a, b) => b - a)
    .forEach((index) => {
      scheduleDatas.value.splice(index, 1)
    })

  // 선택된 일정 비우기
  selectedSchedules.value = []
}

// 7-5 확인버튼 클릭시 post
const postAlarmdata = () => {
  const jsonData = ref({
    pillName: pill_name, // 1. 약의 이름
    pillStartDate: start_date, // 2-1. 시작일
    pillEndDate: end_date, // 2-2. 끝일
    pillDate: day.value.join(''), // 3. 요일(1일시 활성화)
    pillMethod: beforeAfter, // 4. 식전 / 식후
    pillTimes: [] // 5. 시각
  })

  if (addPillTime(jsonData)) {
    postInject(jsonData.value, (response) => {
      if (response.data.success === true) {
        swal({
          title: '투약 일정 알리미',
          text: '투약 일정 등록이 완료되었습니다!',
          icon: 'success',
          buttons: {
            confirm: {
              text: '확인',
              value: false,
              visible: true,
              className: ''
            }
          }
        })
        closeModal()
      } else {
        console.log('무슨문제일까요?1')
        console.log(response)
      }
    })
  } else {
    console.log('문제있음2')
  }
}

//--------------------------------------------------------------------------------------------------------------------------------------
</script>

<style scoped>
/* Custom styling specific to this component if needed */
</style>
