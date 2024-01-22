<script setup>
import TheNavbar from './components/common/TheNavbar.vue'
import TheFooter from './components/common/TheFooter.vue'
</script>

<template>
  <div>
    <TheNavbar></TheNavbar>
    <div class="content-container">
      <router-view></router-view>
    </div>
    <TheFooter></TheFooter>
  </div>
</template>

<style scoped>
.content-container {
  margin-top: 3%;
  margin-bottom: 3%;
}
</style>
