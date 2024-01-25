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
.content-container {
  margin: 3%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-family: Work Sans;
  font-weight: 700;
}
</style>
