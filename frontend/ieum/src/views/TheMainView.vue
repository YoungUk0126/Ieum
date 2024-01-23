<template>
  <div class="content-container mx-auto">
    <TheProfile></TheProfile>

    <TheLatestMessage></TheLatestMessage>

    <router-view></router-view>
  </div>
</template>

<script setup>
import TheProfile from '@/components/profile/TheProfile.vue'
import TheLatestMessage from '@/components/mainframes/TheLatestMessage.vue'
</script>

<style scoped>
.content-container {
  margin: 3%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
