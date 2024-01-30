<template>
  <div class="container mx-auto my-12">
    <div class="flex w-75 justify-center items-center">
      <div class="flex flex-1 pr-7 justify-end">
        <img class="w-80" src="../../assets/images/login.png" />
      </div>
      <div class="flex-1 flex justify-start">
        <form class="w-full max-w-sm ms-2 me-auto">
          <div class="mb-5">
            <label for="id" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >ID
            </label>
            <input
              type="id"
              id="id"
              class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
              placeholder="id"
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
          <div>
            <a href="#" class="text-blue-600 hover:underline dark:text-blue-500">회원가입</a>
          </div>
          <button
            type="submit"
            class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          >
            로그인
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
</script>

<style scoped></style>
