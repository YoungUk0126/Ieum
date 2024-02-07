<template>
  <div id="all" class="flex justify-center">
    <div id="grid" class="grid grid-cols-9 gap-4 mb-96 mt-64">
      <div id="image" class="col-span-3 w-60">
        <img src="@/assets/images/영욱이.png" alt="Your Image" class="w-full h-full object-cover" />
      </div>
      <div id="profile" class="col-span-3 w-60">
        <label
          for="sirial code"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6"
          >시리얼코드</label
        >
        <input
          type="text"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careSerial"
          disabled
        />

        <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6"
          >부모님 성함</label
        >
        <input
          type="text"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careName"
          disabled
        />

        <label
          for="gneder"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6"
          >성별</label
        >
        <input
          type="text"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careGender"
          disabled
        />
      </div>

      <div id="profile2" class="col-span-3 w-60">
        <label
          for="birthday"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6"
          >생년월일</label
        >
        <input
          type="text"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careBirth"
          disabled
        />

        <label
          for="phoneNumber"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6"
          >전화번호</label
        >
        <input
          type="text"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.carePhone"
          disabled
        />

        <label
          for="address"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6"
          >주소</label
        >
        <input
          type="text"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careAddr"
          disabled
        />
      </div>

      <div id="editButton" class="col-end-10">
        <button
          type="button"
          class="px-6 py-3.5 text-base font-medium text-white bg-gray-800 hover:bg-black focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          @click="link"
        >
          수정
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getCareInfo } from '../../api/careInfoModify'
import { useRouter } from 'vue-router'

const router = useRouter()

const careInfo = ref({
  careName: '',
  careGender: '',
  careBirth: '',
  carePhone: '',
  careAddr: '',
  careSerial: '',
  careImg: ''
})

onMounted(() => {
  beforeCareInfo()
})
//미리 기존 정보를 씌우기 위한 onMounted

const beforeCareInfo = () => {
  getCareInfo(({ data }) => {
    careInfo.value = data.data
  })
}
//미리 기존 피보호자 정보를 불러오는 메서드.

const link = () => {
  router.push('/careinfo')
}
//피보호자 정보 수정 페이지로 이동시키는 메서드
</script>

<style scoped></style>
