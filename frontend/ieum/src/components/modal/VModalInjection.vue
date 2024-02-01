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
          v-model="jsonData.start_date"
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
          v-model="jsonData.end_date"
        />
      </div>
    </div>

    <!-- 3. 요일 선택부 -->
    <div class="major-c select-day">
      <div class="form-check form-check-inline">
        <input
          class="form-check-input"
          type="checkbox"
          id="inlineCheckbox1"
          value="0"
          @click="setDay(0)"
        />
        <label class="form-check-label" for="inlineCheckbox1">월</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox2" @click="setDay(1)" />
        <label class="form-check-label" for="inlineCheckbox2">화</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox3" @click="setDay(2)" />
        <label class="form-check-label" for="inlineCheckbox3">수</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox1" @click="setDay(3)" />
        <label class="form-check-label" for="inlineCheckbox1">목</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox2" @click="setDay(4)" />
        <label class="form-check-label" for="inlineCheckbox2">금</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox3" @click="setDay(5)" />
        <label class="form-check-label" for="inlineCheckbox3">토</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox3" @click="setDay(6)" />
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
        @click="addSchedule"
      >
        일정에 추가
      </button>
    </div>
    <!-- 7. 투약 일정 -->
    <div class="major-c inject-plans">
      <form class="inject-form">
        <div class="d-flex justify-content-between">
          <h3 class="col-4">투약 일정</h3>
          <button class="col-2 btn btn-outline-secondary">삭제</button>
        </div>
        <ul class="list-group">
          <li class="list-group-item">
            <input class="form-check-input me-1 px-2" type="checkbox" value="" id="checkbox1" />
            <label class="title-text" for="firstCheckbox">제목</label>
            <label class="day-text" for="firstCheckbox">요일</label>
            <label class="time-text" for="firstCheckbox">시각</label>
          </li>
          <li class="list-group-item">
            <input class="form-check-input me-1 px-2" type="checkbox" value="" id="checkbox2" />
            <label class="title-text" for="firstCheckbox">제목</label>
            <label class="day-text" for="firstCheckbox">요일</label>
            <label class="time-text" for="firstCheckbox">시각</label>
          </li>
          <li class="list-group-item">
            <input class="form-check-input me-1 px-2" type="checkbox" value="" id="checkbox3" />
            <label class="title-text" for="firstCheckbox">제목</label>
            <label class="day-text" for="firstCheckbox">요일</label>
            <label class="time-text" for="firstCheckbox">시각</label>
          </li>
        </ul>
      </form>
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
import { postEvent } from '@/api/modalAlarms/event.js'
import swal from 'sweetalert'

// 약 이름
const pill_name = ref('')

// 요일 설정
const day = ref([0, 0, 0, 0, 0, 0, 0])

function setDay(param) {
  day.value[param] = !day.value[param]
  console.log(day)
}

// 식전, 식후 분별
const beforeafter = ref()

// 시각
const hour = ref(Array.from({ length: 24 }, (_, i) => i + 1)) // 0부터 23까지의 숫자 배열
const minute = ref(Array.from({ length: 60 }, (_, i) => i))

const selectedHour = ref(0) // 시
const selectedMinute = ref(0) // 분

// 투약 일정에 추가
const scheduleDatas = []

function addSchedule() {
  const scheduleData = ['제목', '요일', '시각']

  if (pill_name.value === '') {
    swal({
      title: '',
      text: '제목을 입력해주세요',
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

  if (day.value == [0, 0, 0, 0, 0, 0, 0]) {
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
  }

  scheduleData[0] = pill_name.value
  scheduleData[1] = day.value
  scheduleData[2] = `${selectedHour.value}:${selectedMinute.value}:00`

  scheduleDatas.push(scheduleData)
}

// 모달 닫기
const props = defineProps(['closeModal'])

const jsonData = ref({
  care_no: '123',
  pill_name: '', // 1. 약의 이름
  start_date: '2024-01-15', // 2-1. 시작일
  end_date: '2024-01-18', // 2-2. 끝일
  pill_date: '0000000', // 3. 요일(1일시 활성화)
  pill_method: '식전', // 4. 식전 / 식후
  pill_time: '09:00:00' // 5. 시각
})

function postAlarmdata() {
  if (jsonData.value.title === '' || jsonData.value.date === '') {
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

  postEvent(jsonData)
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
