<template>
  <div class="profile-container bg-white shadow-md rounded-md m-5">
    <div class="grid grid-cols-1 md:grid-cols-2">
      <div class="flex flex-col justify-center px-4 md:px-8 py-4">
        <div class="flex items-center space-x-4">
          <h5 class="text-green-700 text-3xl font-semibold">
            {{ `${care.careName}  ${care.careGender}` }}
          </h5>
        </div>
        <div class="recent-status flex items-center space-x-2 mt-2">
          <div
            class="w-2 h-2"
            :class="{
              'bg-yellow-200 rounded-full': status !== '현재 사용중입니다',
              'bg-green-500 rounded-full': status === '현재 사용중입니다'
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
import { getStatus } from '@/api/iot.js'
import { getCareInfo } from '@/api/careInfoModify.js'

const status = ref()
const care = ref({})

onMounted(() => {
  getStatus(({ data }) => {
    status.value = data.result
  })
  getCareInfo(({ data }) => {
    if (data.data !== undefined) {
      care.value = data.data
      care.value.careGender = care.value.careGender === 'FEMALE' ? '할머니' : '할아버지'
    }
  })
})
</script>

<style scoped>
.profile-img1 {
  width: 30%;
}

.profile-img2 {
  width: 10%;
}
</style>
