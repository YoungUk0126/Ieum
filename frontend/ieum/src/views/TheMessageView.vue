<template>
  <div class="body container my-5 border p-5">
    <h3 class="mb-5 fw-bold">전체 음성/ 영상 메세지 목록</h3>
    <TheMessageBody></TheMessageBody>
  </div>
</template>

<script setup>
import TheMessageBody from '@/components/message/TheMessageBody.vue'
</script>

<style scoped>
.body {
  min-height: 70vh;
  border-radius: 10px;
  border: 1px solid #1de4c1;
  background: #fff;
  box-shadow: 0px 4px 8px 0px rgba(0, 200, 156, 0.1);
}
</style>
