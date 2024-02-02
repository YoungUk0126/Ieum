<template>
  <div class="content-container">
    <div class="row">
      <div class="title-input mb-4">
        <input
          type="text"
          class="form-control"
          placeholder="제목을 입력해주세요"
          aria-label="Username"
          aria-describedby="basic-addon1"
          v-model="jsonData.eventName"
        />
      </div>
      <div class="calendar row mb-4">
        <div class="start-date col-6">
          <label for="start">날짜 : </label>

          <input
            type="date"
            id="start"
            name="trip-start"
            value="2025-01-01"
            min="2018-01-01"
            max="2100-12-31"
            v-model="jsonData.eventDate"
          />
        </div>

        <div class="checkbox-box col-4">
          <input
            type="checkbox"
            class="btn btn-check"
            id="btncheck1"
            autocomplete="off"
            v-model="jsonData.repeat"
          />
          <label class="btn btn-outline-secondary" for="btncheck1">매년 반복</label>
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
            type="button"
            class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
            @click="props.closeModal"
          >
            취소
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import { postEvent } from '@/api/modalAlarms/event.js'
import swal from 'sweetalert'

const props = defineProps(['closeModal'])

const jsonData = ref({
  eventName: '',
  eventDate: ''
})

function postAlarmdata() {
  // 시간, 분, 초를 HH:mm:SS 형태로 조합하여 반환합니다.

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

  console.log(jsonData.value)
  postEvent(jsonData.value, (response) => {
    console.log(response)
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

--------------------------------------------------
