<script setup>
import TheFooter from './components/common/TheFooter.vue'
import TheNavbar from './components/common/TheNavbar.vue'
</script>

<template>
  <div>
    <TheNavbar></TheNavbar>
    <router-view></router-view>
  </div>
  <TheFooter></TheFooter>
</template>

<style></style>
