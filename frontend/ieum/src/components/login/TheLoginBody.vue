<template>
  <div class="container mx-auto px-6">
    <div
      class="shadow-md border-2 border-solid rounded-xl max-w-xl mx-auto h-full flex w-75 justify-start items-center p-6 bg-white"
    >
      <div class="w-full max-w-xl mx-auto">
        <div class="mb-5 text-center font-bold text-xl text-gray-500">
          <h3>로그인</h3>
        </div>
        <div class="mb-5">
          <label for="id" class="block mb-2 text-sm font-medium text-gray-500 dark:text-white"
            >ID
          </label>
          <input
            type="id"
            id="id"
            class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
            placeholder="id"
            v-model="data.memberId"
            required
          />
        </div>
        <div class="mb-5">
          <label for="password" class="block mb-2 text-sm font-medium text-gray-500 dark:text-white"
            >Password</label
          >
          <input
            type="password"
            id="password"
            class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
            placeholder="password"
            v-model="data.password"
            @keyup.enter="loginSubmit"
            required
          />
        </div>

        <div class="flex items-start mb-5">
          <div class="flex items-center h-5">
            <input
              id="terms"
              type="checkbox"
              v-model="savedCheck"
              class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800"
              required
            />
          </div>
          <div class="w-full">
            <label for="terms" class="ms-2 text-sm font-medium text-gray-500 dark:text-gray-300"
              >아이디 저장
            </label>
          </div>
        </div>
        <div class="flex mb-3">
          <div class="w-1/2 ps-4">
            <router-link class="text-gray-400 hover:underline dark:text-blue-500" to="/register">
              회원가입
            </router-link>
          </div>
          <div class="w-1/2 text-end pr-4">
            <router-link class="text-gray-400 hover:underline dark:text-blue-500" to="/findInfo">
              비밀번호 찾기
            </router-link>
          </div>
        </div>

        <button
          type="button"
          class="w-full text-white font-bold bg-gray-300 hover:bg-gray-700 focus:ring-4 focus:outline-none rounded-lg text-sm px-5 py-2.5 text-center"
          @click="loginSubmit"
        >
          로그인
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { login } from '../../api/member.js'
import VueCookies from 'vue-cookies'
import swal from 'sweetalert'
import { useCounterStore } from '@/stores/counter'

const store = useCounterStore()

const data = ref({
  memberId: '',
  password: ''
})

const savedCheck = ref(false)

onMounted(() => {
  if (VueCookies.isKey('savedId')) {
    savedCheck.value = true
    data.value.memberId = VueCookies.get('savedId')
  }
})

const loginSubmit = () => {
  // 아이디 저장 로직
  if (savedCheck.value) {
    VueCookies.set('savedId', data.value.memberId)
  } else {
    VueCookies.remove('savedId')
  }

  login(data.value, (response) => {
    if (response.data.accessToken !== undefined) {
      // 각 토큰 값 쿠키로 저장
      VueCookies.set('accessToken', response.data.accessToken)
      VueCookies.set('refreshToken', response.data.refreshToken)
      VueCookies.set('grantType', response.data.grantType)
      VueCookies.set('auth', true)
      store.auth = true
      //router.push({ name: 'TheMainViewVue' })
      window.location.href = '/'
    } else {
      swal({
        title: '알림',
        text: '잘못된 로그인 정보입니다',
        icon: 'error',
        buttons: {
          confirm: {
            text: '확인',
            visible: true,
            className: '',
            closeModal: true
          }
        }
      })
    }
  })
}
</script>

<style scoped></style>
