<template>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <div class="body">
    <TheHeader v-show="stores.headerState"></TheHeader>
    <div style="height: 96px"></div>
    <router-view></router-view>
  </div>
</template>
<script setup>
import TheHeader from './components/common/TheHeader.vue'
import { useRouter } from 'vue-router'
import { useCounterStore } from '@/stores/counter.js'
const stores = useCounterStore()
const router = useRouter()
router.beforeEach((to, from, next) => {
  if (to.path === '/callcare') {
    stores.headerState = false
  } else {
    stores.headerState = true
  }
  next()
})
</script>

<style scoped></style>
