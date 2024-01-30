<template>
  <div class="container mx-auto my-12">
    <div class="flex w-75 justify-center items-center">
      <div class="flex flex-1 pr-7 justify-end">
        <img class="w-80" src="../../assets/images/login.png" />
      </div>
      <div class="flex-1 flex justify-start">
        <div class="w-full max-w-sm ms-2 me-auto">
          <div class="mb-5">
            <label for="id" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >ID
            </label>
            <input
              type="id"
              id="id"
              class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
              placeholder="id"
              v-model="data.member_id"
              required
            />
          </div>
          <div class="mb-5">
            <label
              for="password"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >Password</label
            >
            <input
              type="password"
              id="password"
              class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
              placeholder="password"
              v-model="data.password"
              required
            />
          </div>

          <div class="flex items-start mb-5">
            <div class="flex items-center h-5">
              <input
                id="terms"
                type="checkbox"
                value=""
                class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800"
                required
              />
            </div>
            <div class="w-full">
              <label for="terms" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >아이디 저장
              </label>
            </div>
          </div>
          <div class="flex mb-3">
            <div class="w-1/2 ps-4">
              <a href="#" class="text-blue-600 hover:underline dark:text-blue-500">회원가입</a>
            </div>
            <div class="w-1/2 text-end pr-4">
              <a href="#" class="text-blue-600 hover:underline dark:text-blue-500"
                >아이디/비밀번호 찾기</a
              >
            </div>
          </div>

          <button
            type="button"
            class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
            @click="loginSubmit"
          >
            로그인
          </button>
        </div>
      </div>
    </div>
  </div>
  <button @click="cookieText">쿠키 테스트</button>
</template>

<script setup>
import { ref } from 'vue'
import { login } from '../../api/member.js'
import VueCookies from 'vue-cookies'

VueCookies.set('jwt', 'sdsdsd')

const data = ref({
  member_id: '',
  password: ''
})

const loginSubmit = () => {
  login(
    data.value,
    ({ data }) => {
      console.log(data)
    },
    () => {
      console.log('fail')
    }
  )
}

const cookieText = () => {
  console.log(VueCookies.get('jwt'))
}
</script>

<style scoped></style>
