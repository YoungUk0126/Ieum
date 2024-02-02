<template>
  <div>
    <!-- 약 정보 입력 모달 -->
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close-btn" @click="closeModal">&times;</span>
        <h2>약 정보 입력</h2>
        <div class="form-group">
          <label for="pillName">약의 이름:</label>
          <input type="text" id="pillName" v-model="jsonData.pillName" />
        </div>
        <div class="form-group">
          <label for="startDate">시작 날짜:</label>
          <input type="date" id="startDate" v-model="jsonData.startDate" />
        </div>
        <div class="form-group">
          <label for="endDate">끝 날짜:</label>
          <input type="date" id="endDate" v-model="jsonData.endDate" />
        </div>
        <div class="form-group">
          <label>투약 요일:</label>
          <div v-for="(day, index) in days" :key="index" class="form-check form-check-inline">
            <input type="checkbox" :id="'day' + index" v-model="jsonData.pillDate[index]" />
            <label :for="'day' + index">{{ day }}</label>
          </div>
        </div>
        <div class="form-group">
          <label>투약 방법:</label>
          <div class="form-check form-check-inline">
            <input type="radio" id="beforeMeal" value="식전" v-model="jsonData.pillMethod" />
            <label for="beforeMeal">식전</label>
          </div>
          <div class="form-check form-check-inline">
            <input type="radio" id="afterMeal" value="식후" v-model="jsonData.pillMethod" />
            <label for="afterMeal">식후</label>
          </div>
        </div>
        <div class="form-group">
          <label for="pillTime">투약 시각:</label>
          <input type="time" id="pillTime" v-model="jsonData.pillTime" />
        </div>
        <button @click="addSchedule">일정에 추가</button>
      </div>
    </div>

    <!-- 투약 일정 목록 -->
    <div class="schedule-list">
      <div v-for="(schedule, index) in scheduleDatas" :key="index">
        <input type="checkbox" v-model="schedule.checked" />
        <span
          >{{ schedule.pillName }} - {{ schedule.startDate }} ~ {{ schedule.endDate }} -
          {{ schedule.pillTime }}</span
        >
        <button @click="deleteSchedule(index)">삭제</button>
      </div>
    </div>

    <!-- 확인 및 취소 버튼 -->
    <div>
      <button @click="postAlarmdata">확인</button>
      <button @click="cancel">취소</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// 모달 관련 데이터
const showModal = ref(false)
const jsonData = ref({
  careNo: '123',
  pillName: '',
  startDate: '',
  endDate: '',
  pillDate: [false, false, false, false, false, false, false], // 0: 월, 1: 화, ..., 6: 일
  pillMethod: '식전',
  pillTime: '09:00:00'
})

// 투약 일정 목록
const scheduleDatas = ref([])

// 요일 배열
const days = ['월', '화', '수', '목', '금', '토', '일']

// 모달 열기
function openModal() {
  showModal.value = true
}

// 모달 닫기
function closeModal() {
  showModal.value = false
}

// 투약 일정에 추가
function addSchedule() {
  const newSchedule = { ...jsonData.value, checked: false }
  scheduleDatas.value.push(newSchedule)
  closeModal()
}

// 투약 일정 삭제
function deleteSchedule(index) {
  scheduleDatas.value.splice(index, 1)
}

// 확인 버튼 클릭 시 API로 데이터 전송
function postAlarmdata() {
  // 여기에 API 호출 및 데이터 전송 로직 추가
  console.log(scheduleDatas.value)
}

// 취소 버튼 클릭 시 모달 닫기
function cancel() {
  closeModal()
}
</script>

<style scoped>
/* 모달 스타일 */
.modal {
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  justify-content: center;
  align-items: center;
  z-index: 1;
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}

/* 추가적인 스타일링은 필요에 따라 추가하십시오. */
.schedule-list {
  margin-top: 20px;
}
</style>
