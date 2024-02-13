<template>
  <div class="body">
    <CareInfo></CareInfo>
  </div>
</template>

<script setup>
import CareInfo from '../components/myInfoPage/CareInfo.vue'
</script>

<style scoped>
  .body {
    min-height: calc(100vh - 96px);
  }
</style>
