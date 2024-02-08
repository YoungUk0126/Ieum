<template>
  <nav class="bg-white shadow-md rounded-md">
    <div class="w-full flex flex-wrap items-center justify-between mx-auto p-4 min-w-80">
      <div class="flex">
        <router-link
          :to="{ name: 'TheMainViewVue' }"
          class="flex items-center space-x-3 rtl:space-x-reverse"
        >
          <img src="@/assets/images/logo.png" class="h-16" alt="Flowbite Logo" />
        </router-link>
      </div>
      <div
        v-show="!hide"
        class="flex items-center md:order-2 space-x-3 md:space-x-0 rtl:space-x-reverse mr-5"
      >
        <button
          type="button"
          class="flex text-sm bg-gray-800 rounded-full md:me-0 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
          id="user-menu-button"
          aria-expanded="false"
          data-dropdown-toggle="user-dropdown"
          data-dropdown-placement="bottom"
        >
          <span class="sr-only">Open user menu</span>
          <img class="w-12 h-12 rounded-full" src="@/assets/images/영욱이.png" alt="user photo" />
        </button>
        <div
          class="z-50 hidden my-4 text-base list-none bg-white divide-y divide-gray-100 rounded-lg shadow dark:bg-gray-700 dark:divide-gray-600"
          id="user-dropdown"
        >
          <ul class="py-2" aria-labelledby="user-menu-button">
            <li>
              <router-link
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                :to="{ name: 'TheMemberInfoView' }"
              >
                내정보
              </router-link>
            </li>
            <li>
              <button
                class="block w-full px-4 text-start py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                @click="logout"
              >
                로그아웃
              </button>
            </li>

            <li>
              <router-link
                :to="{ name: 'TheChatView' }"
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >대화목록
              </router-link>
            </li>
            <li>
              <a
                href="#"
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >부모님 정보</a
              >
            </li>
            <li>
              <router-link
                :to="{ name: 'TheScheduleView' }"
                href="#"
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >일정</router-link
              >
            </li>
            <li>
              <router-link
                :to="{ name: 'TheCallView' }"
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >화상통화</router-link
              >
            </li>
            <li>
              <router-link
                :to="{ name: 'TheMessageView' }"
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >메세지</router-link
              >
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import VueCookies from 'vue-cookies'
import { useRouter } from 'vue-router'
import swal from 'sweetalert'
import { useCounterStore } from '@/stores/counter'

const router = useRouter()

const store = useCounterStore()

const hide = ref(!store.auth)

onMounted(() => {
  if (VueCookies.get('auth')) {
    hide.value = false
    store.auth = true
  }
})

watch(
  () => store.auth,
  () => {
    if (VueCookies.get('auth')) {
      hide.value = false
    } else {
      hide.value = true
    }
  },
  { deep: true }
)

const logout = () => {
  swal({
    title: '로그아웃',
    text: '정상적으로 로그아웃 됐습니다.',
    icon: 'success',
    buttons: {
      confirm: {
        text: '확인',
        visible: true,
        className: '',
        closeModal: true
      }
    }
  }).then(() => {
    VueCookies.remove('accessToken')
    VueCookies.remove('refreshToken')
    VueCookies.remove('auth')
    store.auth = false
    hide.value = true
    router.push({ path: '/login' })
  })
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap');

nav {
  background-color: hsl(146, 100%, 97%);
  font-family: Gowun Dodum;
}

/* 미디어 쿼리를 사용하여 모바일 장치에 대한 스타일 지정 */
@media only screen and (max-width: 768px) {
  /* 모바일 화면에 적용되는 스타일 */
  .w-full {
    width: 100%;
  }
  .flex-wrap {
    flex-wrap: wrap;
  }
  .hidden {
    display: none;
  }
  /* 필요한 다른 스타일들을 여기에 추가할 수 있습니다 */
}
</style>
