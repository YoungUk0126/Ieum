<!-- <template>
  <ModalItem ref="baseModal">
    <div class="content-container">
      <div class="row">
        <div class="cancel">
          <button
            style="
              width: 100%;
              height: 100%;
              padding: 4px;
              background: #e5e5e5;
              border-radius: 29px;
              justify-content: center;
              align-items: center;
              gap: 10px;
              display: inline-flex;
            "
            class="cancel-button"
            @click="cancel"
          >
            <div style="width: 24px; height: 25px; background: #4f4f4f"></div>
          </button>
        </div>
        <div class="dropdown">
          <button
            class="btn btn-secondary dropdown-toggle alarm-btn"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            알람 종류
          </button>
          <ul class="dropdown-menu bg-success-subtle">
            <li><a class="dropdown-item" href="#">기념일</a></li>
            <li><a class="dropdown-item" href="#">기상/취침</a></li>
            <li><a class="dropdown-item" href="#">투약</a></li>
            <li><a class="dropdown-item" href="#">식사</a></li>
          </ul>
        </div>
        <div class="title-input">
          <input
            type="text"
            class="form-control"
            placeholder="제목을 입력해주세요"
            aria-label="Username"
            aria-describedby="basic-addon1"
          />
        </div>
        <div class="calendar">
          <input type="checkbox" class="btn-check" id="btncheck1" autocomplete="off" />
          <label class="btn btn-outline-primary" for="btncheck1">Checkbox 1</label>
        </div>
        <div class="register">
          <button class="register-button" @click="confirm"></button>
        </div>
      </div>
    </div>
    <div class="buttons-container">
      <button class="btn confirm" @click="confirm">확인</button>
      <button class="btn cancel" @click="cancel">취소</button>
    </div>
  </ModalItem>
</template>

<script>
import ModalItem from '@/components/modal/TheModal.vue'
import { ref } from 'vue'

export default {
  name: 'AnniversaryModal',
  components: {
    ModalItem
  },
  // 렌더링할 텍스트를 가져옵니다.
  props: {
    content: Array
  },
  setup() {
    // 자식 컴포넌트를 핸들링하기 위한 ref
    const baseModal = ref(null)
    // Promise 객체를 핸들링하기 위한 ref
    const resolvePromise = ref(null)

    const show = () => {
      // baseModal을 직접 컨트롤합니다.
      baseModal.value.open()
      // Promise 객체를 사용하여, 현재 모달에서 확인 / 취소의
      // 응답이 돌아가기 전까지 작업을 기다리게 할 수 있습니다.
      return new Promise((resolve, _) => {
        // resolve 함수를 담아 외부에서 사용합니다.
        resolvePromise.value = resolve
      })
    }

    const confirm = () => {
      baseModal.value.close()
      resolvePromise.value(true)
    }

    const cancel = () => {
      baseModal.value.close()
      resolvePromise.value(false)
    }
    return { baseModal, show, confirm, cancel }
  }
}
</script>

<style scoped>
.register-button {
  width: 100%;
  height: 100%;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 14px;
  padding-bottom: 14px;
  background: #4f4f4f;
  border-radius: 8px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  display: inline-flex
    '
}

.register-button.font {
       color: #F5F5F5;
       font-size: 25px;
       font-family: Inter;
       font-weight: 700;
       word-wrap: break-word;
}
</style> -->
<template>
  <!-- Button trigger modal -->
  <button type="button" class="alarm-btn" data-bs-toggle="modal" data-bs-target="#anniv">
    알람 등록
  </button>

  <!-- Modal -->
  <div
    class="modal fade modal-lg"
    :id="modalId"
    tabindex="-1"
    aria-labelledby="anniv"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"></h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
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
                    <a class="dropdown-item" href="@/components/modal/TheAnniversaryAlarm.vue"
                      >기념일</a
                    >
                  </li>
                  s
                  <li>
                    <a class="dropdown-item" href="@/components/modal/TheAnniversaryAlarm.vue"
                      >기상/취침</a
                    >
                  </li>
                  <li>
                    <a class="dropdown-item" href="@/components/modal/TheAnniversaryAlarm.vue"
                      >투약</a
                    >
                  </li>
                  <li>
                    <a class="dropdown-item" href="@/components/modal/TheAnniversaryAlarm.vue"
                      >식사</a
                    >
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
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            v-bind="closeBtn"
            type="button"
            id="btnClose"
            class="btn close-btn"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button type="button" class="register-button" @click="closeModal">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
const props = defineProps(['modalId'])

const modalId = ref(props.modalId)
const closeBtn = ref()
const closeModal = () => {
  console.log(document.getElementById('btnClose'))
  document.getElementById('btnClose').click()
}
</script>

<style scoped>
.btn {
  border: 1px black solid;
}
.modal-dialog {
  width: 40%;
}
.alarm-btn {
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

.register-button {
  max-height: 10%;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 14px;
  padding-bottom: 14px;
  background: #4f4f4f;
  border-radius: 8px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  display: inline-flex;
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
