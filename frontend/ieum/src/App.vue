<script setup>
import TheNavbar from './components/common/TheNavbar.vue'
import TheFooter from './components/common/TheFooter.vue'
</script>

<template>
  <div>
    <TheNavbar></TheNavbar>
    <router-view></router-view>
  </div>
  <TheFooter></TheFooter>
</template>

<style scoped></style>
