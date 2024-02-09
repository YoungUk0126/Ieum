<template>
  <div class="profile-container bg-white shadow-md rounded-md m-5">
    <div class="flex flex-wrap align-middle justify-items-between me-4 p-4 w-98">
      <div>알람</div>
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
      <VModal></VModal>
    </div>
    <div class="flex flex-wrap align-middle justify-items-between me-4 p-4 w-98"></div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import VModal from '@/components/Alarm/VModal.vue'
</script>

<style scoped></style>
