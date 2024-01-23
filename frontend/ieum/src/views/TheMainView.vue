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
import TheRecentConversations from '@/components/mainframes/TheRecentConversations.vue'
import TheVoiceVideoMessages from '@/components/mainframes/TheVoiceVideoMessages.vue'
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
