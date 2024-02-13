<template>
  <!-- Modal -->
  <!-- Main modal -->
  <div
    data-modal-backdrop="static"
    :id="props.mainModal"
    tabindex="-1"
    aria-hidden="true"
    class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
  >
    <button :data-modal-hide="props.mainModal" id="btn-close" type="button" class="hidden">
      취소
    </button>
    <div class="relative p-4 w-full max-w-2xl max-h-full">
      <!-- Modal content -->
      <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
        <!-- Modal header -->
        <div class="modal-header flex items-center justify-between rounded-lg text-3xl">
          <div class="text-green-600 font-bold p-8">{{ selectedOption }} 등록</div>
          <button
            type="button"
            class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-md mr-4 w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
            :data-modal-hide="props.mainModal"
          >
            <svg
              class="w-4 h-4"
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
        <div
          class="modal-body font-bold flex-col grid justify-items-center space-y-8 px-10 pt-4 pb-12"
        >
          <template v-if="selectedOption === options[0]">
            <VModalAnniversary :init-option="''"></VModalAnniversary>
          </template>
          <template v-else-if="selectedOption === options[1]">
            <VModalMeal :init-option="''"></VModalMeal>
          </template>
          <template v-else-if="selectedOption === options[2]">
            <VModalWakeandSleep :init-option="''"></VModalWakeandSleep>
          </template>
          <button
            class="w-full text-black text-xl bg-green-200 hover:bg-gradient-to-br focus:ring-2 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-3.5 text-center"
            @click="selectOption(0)"
          >
            기념일 등록
          </button>
          <button
            class="w-full text-black text-xl bg-green-200 hover:bg-gradient-to-br focus:ring-2 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-3.5 text-center"
            type="button"
            @click="selectOption(1)"
          >
            식사 시간 등록
          </button>
          <button
            class="w-full text-black text-xl bg-green-200 hover:bg-gradient-to-br focus:ring-2 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-3.5 text-center"
            @click="selectOption(2)"
          >
            취침시간 등록
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import VModalAnniversary from './VModalAnniversary.vue'
import VModalMeal from './VModalMeal.vue'
import VModalWakeandSleep from './VModalWakeandSleep.vue'

const props = defineProps({ mainModal: String })
const options = ref(['기념일', '식사 시간', '취침 시간'])
const selectedOption = ref('일정')

const closeModal = () => {
  document.getElementById('btn-close').click()
}

const selectOption = (index) => {
  selectedOption.value = options.value[index]
  // 각 모달을 변경하는 로직 추가
}
</script>

<style scoped>
.modal-header {
  background-color: hsl(146, 100%, 97%);
}

.modal-body {
  border-bottom: 1px solid hsl(158, 82%, 83%);
  border-top: 1px solid hsl(158, 82%, 83%);
}
</style>
