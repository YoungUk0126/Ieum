<template>
  <div class="content-container">
    <!-- 총 5개의 부분으로 분리
        * 대분류 div 태그에는 major-c 태그 부여
      1. 제목 입력부
      2. 시작일/종료일 calendar
      3. 시작/종료일 캘린더 설정
      4. 요일 선택부
      5. 투약 일정 정리 박스
      -->
    <!-- 1. 약 이름 입력부 input -->
    <div class="major-c title">
      <div class="title-input mb-4">
        <input
          type="text"
          class="form-control"
          placeholder="약의 이름을 입력해주세요"
          aria-label="Username"
          aria-describedby="basic-addon1"
          v-model="pill_name"
        />
      </div>
    </div>
    <!-- 2. 시작일/종료일 calendar -->
    <div class="major-c start-end-calendar-date row">
      <div class="start-date col-6">
        <label for="start">Start date:</label>

        <input
          type="date"
          id="start"
          name="trip-start"
          value="2025-01-01"
          min="2018-01-01"
          max="2100-12-31"
          v-model="start_date"
        />
      </div>
      <div class="end-date col-6">
        <label for="end">end date:</label>

        <input
          type="date"
          id="end"
          name="trip-start"
          value="2025-01-01"
          min="2018-01-01"
          max="2100-12-31"
          v-model="end_date"
        />
      </div>
    </div>

    <!-- 3. 요일 선택부 -->
    <div class="major-c select-day">
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="dayCheckbox0" @click="setDay(0)" />
        <label class="form-check-label" for="dayCheckbox1">월</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="dayCheckbox1" @click="setDay(1)" />
        <label class="form-check-label" for="inlineCheckbox2">화</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="dayCheckbox2" @click="setDay(2)" />
        <label class="form-check-label" for="inlineCheckbox3">수</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="dayCheckbox3" @click="setDay(3)" />
        <label class="form-check-label" for="inlineCheckbox1">목</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="dayCheckbox4" @click="setDay(4)" />
        <label class="form-check-label" for="inlineCheckbox2">금</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="dayCheckbox5" @click="setDay(5)" />
        <label class="form-check-label" for="inlineCheckbox3">토</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="dayCheckbox6" @click="setDay(6)" />
        <label class="form-check-label" for="inlineCheckbox3">일</label>
      </div>
    </div>
    <!-- 4. 식전/식후 입력부 dropdown -->
    <div class="major-c time-input">
      <div class="row d-flex align-items-center">
        <div class="col-3 d-flex flex-column">
          <div
            class="btn-group-vertical"
            role="group"
            aria-label="Vertical radio toggle button group"
          >
            <input
              type="radio"
              class="btn-check"
              name="vbtn-radio"
              id="beforemeal"
              autocomplete="off"
              value="식전"
              v-model="beforeafter"
            />
            <label class="btn btn-outline-secondary" for="vbtn-radio1">식전</label>
            <input
              type="radio"
              class="btn-check"
              name="vbtn-radio"
              id="aftermeal"
              autocomplete="off"
              value="식후"
              v-model="beforeafter"
            />
            <label class="btn btn-outline-secondary" for="vbtn-radio2">식후</label>
          </div>
        </div>
      </div>
    </div>
    <!-- 5. 시각 -->
    <div class="col-8 d-flex">
      <select v-model="selectedHour" id="numberSelect">
        <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
        <option v-for="num in hour" :key="num" :value="num">{{ num }}</option>
      </select>
      <span>시</span>
      <select v-model="selectedMinute" id="numberSelect2" class="minute-select">
        <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
        <option v-for="num in minute" :key="num" :value="num">{{ num }}</option>
      </select>
      <span>분</span>
    </div>

    <!-- 6. 투약 일정에 추가 -->
    <div class="text-right">
      <button
        type="button"
        class="text-white bg-gradient-to-r from-green-400 via-green-500 to-green-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2"
        @click="addJsonData"
      >
        일정에 추가
      </button>
    </div>
    <!-- 7. 투약 일정 -->
    <div class="major-c inject-plans">
      <div class="inject-form">
        <div class="d-flex justify-content-between">
          <h3 class="col-4">투약 일정</h3>
          <button class="col-2 btn btn-outline-secondary" @click="deleteSelectedSchedules">
            삭제
          </button>
        </div>
        <ul class="list-group">
          <li class="list-group-item" v-for="(data, index) in scheduleDatas" :key="index">
            <input
              class="form-check-input me-1 px-2"
              type="checkbox"
              :value="index"
              id="checkbox1"
              v-model="selectedSchedules"
            />
            <label class="title-text" for="firstCheckbox">{{ data[0] }}</label>
            <label class="day-text" for="firstCheckbox">{{ data[1] }}</label>
            <label class="time-text" for="firstCheckbox">{{ data[2] }}</label>
          </li>
        </ul>
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
        @click="[postAlarmdata(), props.closeModal()]"
      >
        확인
      </button>
      <button
        data-modal-hide="default-modal"
        type="button"
        class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
        @click="props.closeModal"
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

// 1 약 이름
const pill_name = ref('')

// 2 시작일 / 끝일
const start_date = ref('')
const end_date = ref('')

// 3 요일 설정
const day = ref(['0', '0', '0', '0', '0', '0', '0'])

const setDay = (param) => {
  if (day.value[param] === '0') {
    day.value[param] = '1'
  } else {
    day.value[param] = '0'
  }
}

// 4 식전, 식후 분별
const beforeafter = ref() //

// 5. 시각
const hour = ref(Array.from({ length: 24 }, (_, i) => i + 1)) // 0부터 23까지의 숫자 배열
const minute = ref(Array.from({ length: 60 }, (_, i) => i))

const selectedHour = ref(25) // 시
const selectedMinute = ref(25) // 분

// 6. 투약 일정에 추가
const scheduleDatas = ref([])
const jsonDatas = ref([])

const allElementsZero = (arr) => {
  // 요일 입력받았는지 확인
  return arr.every((element) => element === '0')
}

function formatTime(hours, minutes) {
  // 각 값이 한 자리 숫자일 경우 앞에 0을 붙여 두 자리로 만듭니다.
  const formattedHours = String(hours).padStart(2, '0')
  const formattedMinutes = String(minutes).padStart(2, '0')

  // 시간, 분, 초를 HH:mm:SS 형태로 조합하여 반환합니다.
  return `${formattedHours}:${formattedMinutes}:00`
}

const addSchedule = () => {
  // 화면상 투약 일정에 출력을 위해 스케줄을 추가하는 함수
  const scheduleData = ['제목', '', '시각']

  scheduleData[0] = pill_name.value
  scheduleData[1] = day.value
    .reduce((acc, curr, index) => {
      if (curr) {
        acc.push(['월', '화', '수', '목', '금', '토', '일'][index])
      }
      return acc
    }, [])
    .join(', ')
  scheduleData[2] = formatTime(`${selectedHour.value}`, `${selectedMinute.value}`)

  scheduleDatas.value.push(scheduleData)
}

const addJsonData = () => {
  const jsonData = ref({
    pillName: pill_name.value, // 1. 약의 이름
    startDate: start_date, // 2-1. 시작일
    endDate: end_date, // 2-2. 끝일
    pillDate: day.value.join(', '), // 3. 요일(1일시 활성화)
    pillMethod: beforeafter, // 4. 식전 / 식후
    pillTime: formatTime(`${selectedHour.value}`, `${selectedMinute.value}`) // 5. 시각
  })

  console.log(jsonData.value)

  if (
    // 약 이름, 투약 시작 및 끝일자를 입력하지 않은 경우
    jsonData.value.pillName === '' ||
    jsonData.value.startDate === '' ||
    jsonData.value.endDate === ''
  ) {
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
          className: '',
          closeModal: true
        }
      }
    })
    return
  } else if (beforeafter.value === '') {
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
          className: '',
          closeModal: true
        }
      }
    })

    return 0
  } else if (jsonData.value.pillTime === '25:25:00') {
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
          className: '',
          closeModal: true
        }
      }
    })

    return 0
  }
  jsonDatas.value.push(jsonData.value)
  addSchedule()
}

// 7-2 체크된 투약 일정 삭제
// 선택된 일정을 담는 배열
const selectedSchedules = ref([])

// 투약 일정 삭제 메소드
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

// 모달 닫기
const props = defineProps(['closeModal'])

// 8. 확인버튼 클릭시 post
const postAlarmdata = () => {
  console.log(jsonDatas.value)
  postInject(jsonDatas.value)
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

.major-c {
  margin: 3%;
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

.inject-form {
  background-color: hsla(0, 0%, 83%, 1);
  padding: 5%;
}

.minute-select {
  margin-left: 2%;
}

.title-text,
.day-text,
.time-text {
  margin-right: 10px; /* 제목 라벨 오른쪽에 10px 마진 부여 */
}
</style>
