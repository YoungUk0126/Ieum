<template>
  <div class="flex items-center justify-center min-h-screen gap-3">
    <div class="w-full items-center text-center">
      <h1 class="font-mono text-5xl">변경 완료</h1>
      <p class="text-lg mt-4">비밀번호가 변경되었습니다. 변경된 비밀번호로 로그인해주세요</p>
      <a href="/login" class="underline">Go back home</a>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, defineProps } from 'vue'
</script>

<style lang="scss" scoped></style>
