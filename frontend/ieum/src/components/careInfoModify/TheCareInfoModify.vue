<template>
    <div class="all">
      <div id="image" class="flex justify-center mt-24 ml-6 mb-10">
          <img src="@/assets/images/ieum.png" alt="Your Image" class="w-1/3 h-1/3" />
      </div>
      <div id="fileUpload" class="ml-32 mr-32 mb-24">
        <input
            class="col-span-2 block w-full text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
            id="file_input" type="file" @change="handleFileUpload" accept=".jpg, .png">
      </div>
      <div id="name" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
        <label id="name" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">성함</label>
        <input
            type="text"
            class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            v-model="careInfo.careName"
          />
      </div>
      <div id="code" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
        <label id="code" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">코드</label>
        <input
            type="text"
            class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            v-model="careInfo.careSerial"
          />
      </div>
      <div id="birthDay" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
        <label id="birthDay" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">생일</label>
        <input
            type="text"
            class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            v-model="careInfo.careBirth"
          />
      </div>
      <div id="phone" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
        <label id="phone" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">번호</label>
        <input
            type="text"
            class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            @input="checkPhoneFunction" v-model="careInfo.carePhone"
          />
      </div>
      <div id="address" class="grid grid-cols-3 gap-4 ml-8 mr-20 mb-12">
        <label id="address" class="color block text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">주소</label>
        <input
            type="text"
            class="col-span-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            v-model="careInfo.careAddr"
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
        @click="updateInfo">수정하기</button>
      </div>
    </div>
  
  </template>

<script setup>
import { ref, onMounted } from "vue";
import { profileEdit } from '../../api/careInfoModify';
import { getCareInfo } from '../../api/careInfoModify';
import { phoneCheck } from '../../api/careInfoModify';
import swal from 'sweetalert';
import router from "@/router";

const careInfo = ref({
    "careName": "",
    "careGender": "",
    "careBirth": "",
    "carePhone": "",
    "careAddr": "",
    "careSerial": "",
    "careImage": ""
})

const formData = new FormData()

const checkPhone = ref(true);
//전화번호 유효 체크 변수.

const validatePhoneState = ref(false);
//전화번호 중복 상태 체크 변수.

const imageUploadState = ref(false);
//이미지가 업로드 되어 있는지 확인하기 위한 변수.

onMounted(() => {
    beforeCareInfo()
})
//미리 기존 정보를 씌우기 위한 onMounted

const beforeCareInfo = () => {
    getCareInfo(
        ({ data }) => {
            careInfo.value = data.data
        }

    )
}
//미리 기존 피보호자 정보를 불러오는 메서드. 

const updateInfo = () => {
    if (checkPhone.value && validatePhoneState.value) {
        updateCareInfo()
        router.push('/')
    }
    else {
        swal('전화번호 확인을 다시 시도해주세요.')
        return;
    }
}

//클릭했을 때, 정보를 보내는 메서드를 실행시키는 메서드.

const updateCareInfo = () => {
    duplicatePhoneCheck();
    const json = JSON.stringify(careInfo.value)
    const formJson = new Blob([json], { type: 'application/json' })
    formData.append('data', formJson)
    if (imageUploadState.value) {
        formData.append('file', blob.value, file.value.name)
    }
    else {
        formData.append('file', null)
    }
    profileEdit(
        formData,
        (response) => {
            if (response.data.success) {
                swal('정보가 변경되었습니다.')
            }
            else {
                swal('정보 변경 중 오류가 발생하였습니다.')
            }
        }
    )


}
//변경된 피보호자 정보를 보내는 메서드.

const fileInput = ref('');
const file = ref(null);
const blob = ref(null);

const handleFileUpload = () => {
    // 이미지 정보 가져오기
    fileInput.value = document.getElementById('file_input');
    file.value = fileInput.value.files[0];

    // careImage이름 넣어주기
    careInfo.value.careImage = file.value.name;

    // Blob 사용해서 이미지 파일 만들기
    blob.value = new Blob([file.value], { type: file.value.type })

    imageUploadState.value = true;
    //이미지를 업로드했을 때, true로 변경.
};
//이미지를 수정하는 메서드.

const checkPhoneFunction = () => {
    const validatephone = /^010-\d{4}-\d{4}$/;
    checkPhone.value = validatephone.test(careInfo.value.carePhone)
    validatePhoneState.value = false
}
//전화번호가 유효한 형식인지 알아보는 메서드.

const duplicatePhoneCheck = () => {
    phoneCheck(
        careInfo.value.carePhone,
        ({ data }) => {
            if (data.data.isDuplicated) {
                swal('이미 사용 중인 전화번호입니다.')
                validatePhoneState.value = false
                return;
            }
            else {
                swal('사용 가능한 전화번호입니다.')
                validatePhoneState.value = true
            }

        }
    )



}



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