<template>
  <div class="mx-auto my-12 p-6 w-full max-w-3xl">
    <div class="flex flex-col items-center justify-center gap-3">
      <h1 class="font-bold text-5xl">변경 완료</h1>
      <p class="text-lg mt-4">비밀번호가 변경되었습니다.</p>
      <p>변경된 비밀번호로 로그인해주세요</p>
      <buttom
        @click="goHome"
        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
        >로그인 화면으로</buttom
      >
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goHome = () => {
  router.push({ name: 'TheLoginView' })
}
</script>

<style lang="scss" scoped></style>
