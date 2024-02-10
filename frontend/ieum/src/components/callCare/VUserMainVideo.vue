<template>
  <div v-if="streamManager">
    <VOvVideo :stream-manager="streamManager" />
  </div>
</template>

<script setup>
import VOvVideo from './VOvMainVideo.vue'
import { ref, watch } from 'vue'
const props = defineProps(['streamManager'])
const streamManager = ref(props.streamManager)

watch(
  () => props.streamManager,
  () => {
    streamManager.value = props.streamManager
  }
)
</script>
