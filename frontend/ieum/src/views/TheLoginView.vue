<template>
  <div class="loginBody">
    <TheLoginBody></TheLoginBody>
  </div>
</template>

<script setup>
import TheLoginBody from '../components/login/TheLoginBody.vue'
</script>

<style scoped>
.loginBody {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 96px);
}
</style>
