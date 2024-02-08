<script setup>
import TheHeader from './components/common/TheHeader.vue'
</script>

<template>
  <div class="body">
    <TheHeader></TheHeader>
    <div style="height: 96px"></div>
    <router-view></router-view>
  </div>
</template>
<style scoped></style>
