<template>
  <!-- Modal toggle -->
  <button
    data-modal-target="default-modal"
    data-modal-toggle="default-modal"
    class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
    type="button"
  >
    일정 등록
  </button>

  <!-- Modal -->
  <!-- Main modal -->
  <div
    id="default-modal"
    data-modal-backdrop="default-modal"
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
        <div class="modal-header text-3xl rounded-lg text-green-600 font-bold p-8">일정 등록</div>
        <!-- Modal body -->
        <div
          class="modal-body font-bold flex flex-col grid justify-items-center space-y-8 px-10 py-12"
        >
          <button
            class="w-full text-black text-xl bg-green-200 hover:bg-gradient-to-br focus:ring-2 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-3.5 text-center"
            data-modal-target="vmodalanniversary"
            data-modal-toggle="vmodalanniversary"
          >
            기념일 등록
          </button>
          <button
            class="w-full text-black text-xl bg-green-200 hover:bg-gradient-to-br focus:ring-2 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-3.5 text-center"
            data-modal-target="vmodal"
            data-modal-toggle="vmodalannibody"
          >
            어르신 약속 등록
          </button>
          <button
            class="w-full text-black text-xl bg-green-200 hover:bg-gradient-to-br focus:ring-2 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-3.5 text-center"
          >
            취침시간 등록
          </button>
        </div>
        <div class="w-full p-4 text-end">
          <button
            class="text-black bg-red-300 hover:bg-gradient-to-br focus:ring-2 focus:outline-none focus:ring-red-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
            @click="closeModal"
          >
            닫기
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

// modal의 body 전환을 위한 변수
const selectedOption = ref('alarm')

const closeModal = () => {
  document.getElementById('btn-close').click()
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
