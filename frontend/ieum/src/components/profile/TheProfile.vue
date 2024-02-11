<template>
  <div class="profile-container bg-white shadow-md rounded-md m-5">
    <div class="grid grid-cols-1 md:grid-cols-2">
      <div class="flex flex-col justify-center px-4 md:px-8 py-4">
        <div class="flex items-center space-x-4">
          <img
            src="@/assets/images/전화걸기.png"
            class="w-16 rounded-md profile-img2 cursor-pointer"
            alt="전화 걸기 아이콘"
            @click="navigateToTheCallView"
          />
          <h5 class="text-green-700 text-3xl font-semibold">영욱이 할아버지</h5>
        </div>
        <div class="recent-status flex items-center space-x-2 mt-2">
          <div
            class="w-2 h-2"
            :class="{
              'bg-yellow-200 rounded-full': status === '현재 사용중입니다',
              'bg-green-500 rounded-full': status !== '현재 사용중입니다'
            }"
          ></div>
          <div class="text-green-700 text-base font-normal">{{ status }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { getStatus } from '@/api/iot.js'

const router = useRouter()
const status = ref()

onMounted(() => {
  getStatus(({ data }) => {
    status.value = data.result
  })
})

const navigateToTheCallView = () => {
  router.push({
    path: '/call'
  })
}
</script>

<style scoped>
.profile-img1 {
  width: 30%;
}

.profile-img2 {
  width: 10%;
}
</style>
