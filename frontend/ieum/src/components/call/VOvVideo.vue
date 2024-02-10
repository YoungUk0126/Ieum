<template>
  <video ref="videoElement" autoplay muted class="w-screen-20 fix-profile min-w-32" />
</template>

<script setup>
import { ref, onMounted } from 'vue'

const props = defineProps(['streamManager'])
const videoElement = ref(null)

onMounted(() => {
  props.streamManager.addVideoElement(videoElement.value)
})
</script>

<style>
.w-screen-20 {
  width: 20vw;
}
.fix-profile {
  position: absolute;
  z-index: 100;
}
</style>
