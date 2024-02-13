<template>
  <video class="w-screen min-w-96" muted ref="videoElement" autoplay />
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'

const props = defineProps(['streamManager'])
const videoElement = ref(null)

onMounted(() => {
  props.streamManager.addVideoElement(videoElement.value)
})

watch(
  () => props.streamManager,
  () => {
    props.streamManager.addVideoElement(videoElement.value)
  }
)
</script>
