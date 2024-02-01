<script setup>
import TheFooter from './components/common/TheFooter.vue'
import TheNavbar from './components/common/TheNavbar.vue'
</script>

<template>
  <div class="body">
    <TheNavbar></TheNavbar>
    <router-view></router-view>
  </div>
  <TheFooter></TheFooter>
</template>

<style scoped>
.body {
  min-width: 550px;
}
</style>
