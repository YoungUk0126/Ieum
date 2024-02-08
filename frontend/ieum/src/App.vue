<script setup>
import TheFooter from './components/common/TheFooter.vue'
import TheNavbar from './components/common/TheNavbar.vue'
</script>

<template>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <div class="body">
    <TheNavbar></TheNavbar>
    <router-view></router-view>
  </div>
  <TheFooter></TheFooter>
</template>

<style scoped></style>
