<template>
  <div class="mx-auto my-12 p-6 w-full">
    <div class="flex flex-col items-center justify-center gap-3">
      <h1 class="font-mono text-5xl">변경 완료</h1>
      <p class="text-lg mt-4">비밀번호가 변경되었습니다.</p>
      <p>변경된 비밀번호로 로그인해주세요</p>
      <buttom @click="goHome" class="underline">Go back home</buttom>
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
