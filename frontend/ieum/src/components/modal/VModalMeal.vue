<template>
  <div class="content-container">
    <div class="row">
      <div class="dropdown mb-4">
        <button
          class="btn dropdown-toggle"
          type="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          알람 종류
        </button>
        <ul class="dropdown-menu">
          <li>
            <div class="dropdown-item" dropdownId="anniv" @click="goAnniv">기념일</div>
          </li>
          <li>
            <a class="dropdown-item" dropdownId="wakeandsleep" @click="goWake">기상/취침</a>
          </li>
          <li>
            <a class="dropdown-item" dropdownId="injection" @click="goInject">투약</a>
          </li>
          <li>
            <a class="dropdown-item" dropdownId="meal" @click="goMeal">식사</a>
          </li>
        </ul>
      </div>
      <div class="title-input mb-4">
        <input
          type="text"
          class="form-control"
          placeholder="제목을 입력해주세요"
          aria-label="Username"
          aria-describedby="basic-addon1"
        />
      </div>
      <div class="calendar row mb-4">
        <div class="calendar-box col-6">
          <img src="@/assets/images/달력버튼.png" />
        </div>
        <div class="checkbox-box col-4">
          <input type="checkbox" class="btn btn-check" id="btncheck1" autocomplete="off" />
          <label class="btn btn-outline-secondary" for="btncheck1">매년 반복</label>
          <div>식사</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
// import VModalAnniBody from './VModalAnniBody.vue'
// import VModalInjection from './VModalInjection.vue'
// import VModalMeal from './VModalMeal.vue'
// import VModalWakeandSleep from './VModalWakeandSleep.vue'
const props = defineProps(['modalId'])

const modalId = ref(props.modalId)
console.log(modalId.value)

const goAnniv = () => {}

const goInject = () => {}

const goMeal = () => {}

const goWake = () => {}
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
