<template>
  <div class="content-container">
    <!-- 총 5개의 부분으로 분리
      * 대분류 div 태그에는 major-c 태그 부여
    2. 제목 입력부
    3. 시각 입력부
    4. 시작/종료일 캘린더 설정
    5. 요일 선택부
    6. 투약 일정 정리 박스
    -->
    <!-- 2. 제목 입력부 input -->
    <div class="major-c title">
      <div class="title-input mb-4">
        <input
          type="text"
          class="form-control"
          placeholder="제목을 입력해주세요"
          aria-label="Username"
          aria-describedby="basic-addon1"
        />
      </div>
    </div>
    <!-- 3. 시각 입력부 dropdown -->
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
              id="vbtn-radio1"
              autocomplete="off"
              checked
            />
            <label class="btn btn-outline-secondary" for="vbtn-radio1">오전</label>
            <input
              type="radio"
              class="btn-check"
              name="vbtn-radio"
              id="vbtn-radio2"
              autocomplete="off"
            />
            <label class="btn btn-outline-secondary" for="vbtn-radio2">오후</label>
          </div>
        </div>
        <div class="col-8 d-flex">
          <select v-model="selectedNumber" id="numberSelect">
            <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers1" :key="num" :value="num">{{ num }}</option>
          </select>
          <span>시</span>
          <select v-model="selectedNumber2" id="numberSelect2" class="minute-select">
            <!-- 0부터 12까지의 숫자를 반복하여 option 엘리먼트 생성 -->
            <option v-for="num in numbers2" :key="num" :value="num">{{ num }}</option>
          </select>
          <span>분</span>
        </div>
      </div>
    </div>
    <!-- 4. 시작일/종료일 calendar -->
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
        />
      </div>
    </div>
    <!-- 5. 요일 선택부 -->
    <div class="major-c select-day">
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1" />
        <label class="form-check-label" for="inlineCheckbox1">월</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2" />
        <label class="form-check-label" for="inlineCheckbox2">화</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" />
        <label class="form-check-label" for="inlineCheckbox3">수</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1" />
        <label class="form-check-label" for="inlineCheckbox1">목</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2" />
        <label class="form-check-label" for="inlineCheckbox2">금</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" />
        <label class="form-check-label" for="inlineCheckbox3">토</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" />
        <label class="form-check-label" for="inlineCheckbox3">일</label>
      </div>
    </div>
    <!-- 6. 투약 일정 -->
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
  </div>
</template>

<script setup>
import { ref } from 'vue'
const props = defineProps(['modalId'])

const modalId = ref(props.modalId)
const alarm_type = ref()
const numbers1 = ref(Array.from({ length: 12 }, (_, i) => i + 1)) // 0부터 23까지의 숫자 배열
const numbers2 = ref(Array.from({ length: 60 }, (_, i) => i))

const selectedNumber = ref(0) // 초기값 설정
const selectedNumber2 = ref(0)
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
