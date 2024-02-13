<template>
  <div class="body max-w-3xl">
    <CareInfo></CareInfo>
  </div>
</template>

<script setup>
import CareInfo from '../components/myInfoPage/CareInfo.vue'
</script>

<style scoped>
  .body {
    min-height: calc(100vh - 96px);
    margin: 0 auto;
  }
</style>
