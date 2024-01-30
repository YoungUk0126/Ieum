<template>
  <div class="content-container mx-auto">
    <TheProfile></TheProfile>
    <TheRecentConversations></TheRecentConversations>
    <TheVoiceVideoMessages></TheVoiceVideoMessages>
    <router-view></router-view>
  </div>
</template>

<script setup>
import TheProfile from '@/components/profile/TheProfile.vue'
import TheRecentConversations from '@/components/main/TheRecentConversations.vue'
import TheVoiceVideoMessages from '@/components/main/TheVoiceVideoMessages.vue'
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap');

.content-container {
  margin: 7%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-family: Gowun Dodum;
  font-weight: 700;
}
</style>
