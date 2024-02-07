<template>
  <div class="profile-container bg-white shadow-md rounded-md m-5">
    <div class="grid grid-cols-1 md:grid-cols-2">
      <div class="md:text-center">
        <img
          src="@/assets/images/영욱이.png"
          class="w-16 md:w-1/15 mx-auto my-5 rounded-full profile-img1"
          alt="영욱이 프로필 이미지"
        />
      </div>
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
          <div class="w-2 h-2 bg-green-500 rounded-full"></div>
          <div class="text-green-700 text-base font-normal">최근 대화 내역: 1일전</div>
        </div>
        <div class="flex">
          <div class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1">
            <ul
              class="p-8 flex flex-col font-bold md:p-0 mt-4 md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0"
            >
              <li>
                <router-link
                  :to="{ name: 'TheChatView' }"
                  class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
                  >대화목록
                </router-link>
              </li>
              <li>
                <router-link
                  :to="{ name: 'TheCareInfoCheckView' }"
                  class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
                  >부모님 정보</router-link
                >
              </li>
              <li>
                <router-link
                  :to="{ name: 'TheScheduleView' }"
                  class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
                  >일정</router-link
                >
              </li>
              <li>
                <router-link
                  :to="{ name: 'TheAlarmView' }"
                  class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
                  >알람</router-link
                >
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

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
