<template>
  <!-- Modal toggle -->
  <button
    data-modal-target="default-modal"
    data-modal-toggle="default-modal"
    class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
    type="button"
  >
    알람
  </button>

  <!-- Modal -->
  <!-- Main modal -->
  <div
    id="default-modal"
    data-modal-backdrop="static"
    tabindex="-1"
    aria-hidden="true"
    class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
  >
    <button data-modal-hide="default-modal" id="btn-close" type="button" class="hidden">
      취소
    </button>
    <div class="relative p-4 w-full max-w-2xl max-h-full">
      <!-- Modal content -->
      <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
        <!-- Modal header -->
        <div
          class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600"
        >
          <label
            for="countries"
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          ></label>
          <select
            id="alarms"
            v-model="selectedOption"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          >
            <option selected>알람을 선택해주세요</option>
            <option value="anniversary">기념일</option>
            <option value="medication">투약</option>
            <option value="sleep">취침</option>
            <option value="meal">식사</option>
          </select>

          <button
            type="button"
            class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
            data-modal-hide="default-modal"
          >
            <svg
              class="w-3 h-3"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 14 14"
            >
              <path
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
              />
            </svg>
            <span class="sr-only">Close modal</span>
          </button>
        </div>
        <!-- Modal body -->
        <!-- 동적으로 변경되는 내용 추가 -->
        <div class="modal-body">
          <template v-if="selectedOption === 'alarm'">
            <p>알람을 지정해주세요.</p>
          </template>
          <template v-else-if="selectedOption === 'anniversary'">
            <VModalAnniBody :closeModal="closeModal"></VModalAnniBody>
          </template>
          <template v-else-if="selectedOption === 'medication'">
            <VModalInjection :closeModal="closeModal"></VModalInjection>
          </template>
          <template v-else-if="selectedOption === 'sleep'">
            <VModalWakeandSleep :closeModal="closeModal"></VModalWakeandSleep>
            <!-- 취침 내용 추가 -->
          </template>
          <template v-else-if="selectedOption === 'meal'">
            <VModalMeal :closeModal="closeModal"></VModalMeal>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import VModalAnniBody from './VModalAnniBody.vue'
import VModalInjection from './VModalInjection.vue'
import VModalMeal from './VModalMeal.vue'
import VModalWakeandSleep from './VModalWakeandSleep.vue'

// modal의 body 전환을 위한 변수
const selectedOption = ref('alarm')

const closeModal = () => {
  document.getElementById('btn-close').click()
}
</script>

<style scoped>
.modal-header,
.modal-body,
.modal-footer {
  background-color: hsla(0, 0%, 83%, 0.3);
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
</style>
