<template>
  <nav class="bg-white shadow-md rounded-md">
    <div class="w-full flex flex-wrap items-center justify-between mx-auto p-4">
      <div class="flex">
        <a href="http://localhost:5173/" class="flex items-center space-x-3 rtl:space-x-reverse">
          <img src="@/assets/images/logo.png" class="h-16" alt="Flowbite Logo" />
        </a>
        <div
          class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1"
          id="navbar-user"
        >
          <ul
            class="flex flex-col font-bold p-4 md:p-0 mt-4 md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0"
          >
            <li>
              <a
                href="#"
                class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
                aria-current="page"
                >대화목록</a
              >
            </li>
            <li>
              <a
                href="#"
                class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
                >부모님 정보</a
              >
            </li>
            <li>
              <a
                href="#"
                class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
                >일정</a
              >
            </li>
            <li>
              <!-- Main modal -->
              <VModal></VModal>
            </li>
          </ul>
        </div>
      </div>
      <div class="flex items-center md:order-2 space-x-3 md:space-x-0 rtl:space-x-reverse">
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
          <div class="px-4 py-3">
            <span class="block text-sm text-gray-900 dark:text-white">흑막 할배</span>
            <span class="block text-sm text-gray-500 truncate dark:text-gray-400"
              >name@flowbite.com</span
            >
          </div>
          <ul class="py-2" aria-labelledby="user-menu-button">
            <li>
              <a
                href="#"
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >내 정보</a
              >
            </li>
            <li>
              <button
                class="block w-full px-4 text-start py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                @click="logout"
              >
                로그아웃
              </button>
            </li>
          </ul>
        </div>
        <button
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
      </div>
    </div>
  </nav>
</template>

<script setup>
import VModal from '@/components/modal/VModal.vue'
import VueCookies from 'vue-cookies'
import { useRouter } from 'vue-router'
import swal from 'sweetalert'
const router = useRouter()

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
</style>
