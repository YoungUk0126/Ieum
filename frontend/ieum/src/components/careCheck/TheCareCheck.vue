<template>
  <div class="all">
    <div id="image" class="flex justify-center mt-24 ml-6 mb-24">
        <img src="@/assets/images/ieum.png" alt="Your Image" class="w-1/3 h-1/3" />
    </div>
    <div id="name" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
      <label id="name" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">성함</label>
      <input
          type="text"
          class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careName"
          disabled
        />
    </div>
    <div id="code" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
      <label id="code" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">코드</label>
      <input
          type="text"
          class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careSerial"
          disabled
        />
    </div>
    <div id="birthDay" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
      <label id="birthDay" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">생일</label>
      <input
          type="text"
          class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careBirth"
          disabled
        />
    </div>
    <div id="phone" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
      <label id="phone" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">번호</label>
      <input
          type="text"
          class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.carePhone"
          disabled
        />
    </div>
    <div id="address" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
      <label id="address" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">주소</label>
      <input
          type="text"
          class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="careInfo.careAddr"
          disabled
        />
    </div>
    <div id="gender" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
      <label id="gender" class="color mt-4 block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">성별</label>
      <div class="flex items-center ps-4 border border-gray-200 rounded dark:border-gray-700">
    <input id="bordered-radio-1" type="radio" v-model="careInfo.careGender" value="MALE" name="bordered-radio" class="radio w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" disabled>
    <label for="bordered-radio-1" class="w-full py-4 ms-2 text-2xl font-medium text-gray-900 dark:text-gray-300">남</label>
      </div>
       <div class="flex items-center ps-4 border border-gray-200 rounded dark:border-gray-700">
    <input checked id="bordered-radio-2" type="radio" v-model="careInfo.careGender" value="FEMALE" name="bordered-radio" class="radio w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" disabled>
    <label for="bordered-radio-2" class="w-full py-4 ms-2 text-2xl font-medium text-gray-900 dark:text-gray-300">여</label>
       </div>
    </div>
    <div class="flex justify-center ml-6 mt-20 ml-4">
      <button type="button" class="edit px-14 py-3.5 text-3xl font-bold text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
      @click="link">수정하기</button>
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

<style scoped>
.all {
    min-width: 680px !important;
    min-height: 560px !important;
    max-width: 680px !important;
    max-height: 985px !important;
}

.edit{
  background-color: #8FF9F9;
  color: #707070;
}

.color{
  color: #707070;
}

</style>