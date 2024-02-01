<template>
  <div
    class="flex items-center w-full p-3 space-x-2 text-sm font-medium text-center text-gray-500 bg-white border border-gray-200 rounded-lg shadow-sm dark:text-gray-400 sm:text-base dark:bg-gray-800 dark:border-gray-700 sm:p-4 sm:space-x-4 rtl:space-x-reverse"
  >
    <input value="아이디" />
    <input value="이름" />
    <input value="비밀번호" />
    <input value="이메일" />
    <input value="전화번호" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getInfo } from '../../api/member.js'

const info = ref({})

onMounted(() => {
  getInfo(({ data }) => {
    console.log(data.data)
    info.value = data.data
  })
})
</script>

<style scoped></style>
