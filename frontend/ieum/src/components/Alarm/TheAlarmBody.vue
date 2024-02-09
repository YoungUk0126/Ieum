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
    <!-- 동적으로 변경되는 내용 추가 -->
    <div class="modax l-body">
      <template v-if="selectedOption === 'anniversary'">
        <TheBodyAnniv :closeModal="closeModal"></TheBodyAnniv>
      </template>
      <template v-else-if="selectedOption === 'medication'">
        <TheBodyInjection :closeModal="closeModal"></TheBodyInjection>
      </template>
      <template v-else-if="selectedOption === 'sleep'">
        <TheBodyMeal :closeModal="closeModal"></TheBodyMeal>
      </template>
      <template v-else-if="selectedOption === 'meal'">
        <TheBodyWake :closeModal="closeModal"></TheBodyWake>
      </template>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import VModal from '@/components/Alarm/VModal.vue'
import TheBodyAnniv from '@/components/Alarm/TheBodyAnniv.vue'
import TheBodyInjection from '@/components/Alarm/TheBodyInjection.vue'
import TheBodyMeal from '@/components/Alarm/TheBodyMeal.vue'
import TheBodyWake from '@/components/Alarm/TheBodyWake.vue'
</script>

<style scoped></style>
