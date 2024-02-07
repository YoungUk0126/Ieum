<template>
  <video ref="videoElement" autoplay />
</template>

<script setup>
import { ref, onMounted } from 'vue'

const props = defineProps(['streamManager'])
const videoElement = ref(null)

onMounted(() => {
  props.streamManager.addVideoElement(videoElement.value)
})
</script>
