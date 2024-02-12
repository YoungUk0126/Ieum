<template>
  <nav
    class="bg-white dark:bg-gray-900 fixed w-full z-20 top-0 start-0 border-b border-gray-200 dark:border-gray-600 transition-all duration-500 ease-in-out"
  >
    <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
      <template v-if="!hide">
        <router-link
          :to="{ name: 'TheMainViewVue' }"
          class="flex items-center space-x-3 rtl:space-x-reverse"
        >
          <img src="@/assets/images/logo.png" class="h-16" />
        </router-link>
      </template>
      <template v-if="hide">
        <router-link
          :to="{ name: 'TheLoginView' }"
          class="flex items-center space-x-3 rtl:space-x-reverse"
        >
          <img src="@/assets/images/logo.png" class="h-16" />
        </router-link>
      </template>
      <button
        v-show="!hide"
        ref="hamb"
        data-collapse-toggle="navbar-user"
        type="button"
        class="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
        aria-controls="navbar-user"
        aria-expanded="false"
      >
        <span class="sr-only">Open main menu</span>
        <svg
          class="w-5 h-5"
          aria-hidden="true"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 17 14"
        >
          <path
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M1 1h15M1 7h15M1 13h15"
          />
        </svg>
      </button>
      <div
        v-show="!hide"
        class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1"
        id="navbar-user"
      >
        <ul
          class="flex flex-col font-bold text-right space-y-2 p-4 md:p-0 mt-0 md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0"
        >
          <li>
            <router-link
              :to="{ name: 'TheChatView' }"
              class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
              >대화목록
            </router-link>
          </li>
          <li>
            <a
              href="#"
              class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
              >부모님 정보</a
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'TheScheduleView' }"
              href="#"
              class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
              >일정</router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'TheCallView' }"
              class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
              >화상통화</router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'TheMessageView' }"
              class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
              >메세지</router-link
            >
          </li>
          <li>
            <button
              @click="logout"
              class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
            >
              로그아웃
            </button>
          </li>
        </ul>
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

const hamb = ref()

// 네비게이션 가드를 사용하여 네비게이션 이벤트를 감지합니다.
router.beforeEach((to, from, next) => {
  // 햄버거 메뉴를 닫습니다.
  closeNavMenu()
  next()
})

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

const closeNavMenu = () => {
  if (hamb.value.ariaExpanded === 'true') {
    hamb.value.click()
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap');

nav {
  background-color: hsl(146, 100%, 97%);
  font-family: Gowun Dodum;
}
</style>
