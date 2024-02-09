<template>
  <div v-if="streamManager">
    <VOvVideo :stream-manager="streamManager" />
  </div>
</template>

<script setup>
import VOvVideo from './VOvVideo.vue'
import { ref, computed, watch } from 'vue'
const props = defineProps(['streamManager'])
const streamManager = ref(props.streamManager)

const clientData = computed(() => {
  const { clientData } = getConnectionData()
  return clientData
})

const getConnectionData = () => {
  const { connection } = streamManager.value.stream
  return JSON.parse(connection.data)
}

watch(
  () => props.streamManager,
  () => {
    streamManager.value = props.streamManager
  }
)
</script>
