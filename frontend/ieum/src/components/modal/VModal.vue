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
            id="countries"
            v-model="selectedOption"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
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
        <div class="modal-body">
          <!-- 동적으로 변경되는 내용 추가 -->
          <template v-if="selectedOption === 'alarm'">
            <p>알람을 지정해주세요.</p>
          </template>
          <template v-else-if="selectedOption === 'anniversary'">
            <VModalAnniBody></VModalAnniBody>
          </template>
          <template v-else-if="selectedOption === 'medication'">
            <VModalInjection :modalId="modalId"></VModalInjection>
          </template>
          <template v-else-if="selectedOption === 'sleep'">
            <VModalWakeandSleep :modalId="modalId"></VModalWakeandSleep>
            <!-- 취침 내용 추가 -->
          </template>
          <template v-else-if="selectedOption === 'meal'">
            <VModalMeal :modalId="modalId"></VModalMeal>
          </template>
        </div>
        <!-- Modal footer -->
        <div
          class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
        >
          <button
            data-modal-hide="default-modal"
            type="button"
            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          >
            I accept
          </button>
          <button
            data-modal-hide="default-modal"
            type="button"
            class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
          >
            Decline
          </button>
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

const modalId = ref('anniv')
const selectedOption = ref('alarm')

// const closeBtn = ref()

// const closeModal = () => {
//   console.log(document.getElementById('btnClose'))
//   document.getElementById('btnClose').click()
// }
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
