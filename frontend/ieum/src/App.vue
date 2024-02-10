<script setup>
import TheHeader from './components/common/TheHeader.vue'
</script>

<template>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <div class="body">
    <TheHeader></TheHeader>
    <div style="height: 96px"></div>
    <router-view></router-view>
  </div>
</template>

<style scoped></style>
