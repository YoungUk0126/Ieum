<template>
  <div class="modal-header">
    <h1 class="modal-title fs-5" id="staticBackdropLabel">영상 메세지 수정</h1>
    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
  </div>
  <div class="modal-body">...</div>
  <div class="modal-footer">
    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary">Understood</button>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
const props = defineProps(['messageState'])

const messageState = ref(props.messageState)

onMounted(() => {
  console.log(props.messageState)
})

// 이후 messageState가 변경됨에 따라 이전 음성을 바꿔줘야 한다.
watch(messageState, (nv, ov) => {}, { deep: true })
</script>

<style scoped></style>
