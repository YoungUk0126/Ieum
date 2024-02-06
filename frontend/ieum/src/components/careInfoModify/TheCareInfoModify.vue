<template>
    <div id="all" class="flex justify-center">
        <div id=grid class="grid grid-cols-9 gap-4 mb-96 mt-64">
            <div id="image" class="col-span-3 w-60">
                <img :src="`${careInfo.careImage}`" alt="Your Image" class="w-full h-full object-cover" />
            </div>
            <div id="profile" class="col-span-3 w-60">
                <label for="sirial code"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6">시리얼코드</label>
                <input type="text"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    v-model="careInfo.careSerial">


                <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6">부모님 성함</label>
                <input type="text"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    disabled>
                <label for="gneder" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6">성별</label>
                <input type="text"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    v-model="careInfo.careGender" disabled>


            </div>

            <div id="profile2" class="col-span-3 w-60">
                <label for="birthday" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6">생년월일</label>
                <input type="date"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    v-model="careInfo.careBirth">

                <div id="phoneCheck" class="mt-5">
                    <div class="block mb-2">
                        <label for="phoneNumber"
                            class="mb-4 text-sm font-medium text-gray-900 dark:text-white mt-6 mr-24">전화번호</label>
                        <label for="phoneNumber" class="mb-4 text-sm font-medium text-gray-900 dark:text-white mt-6"
                            @click="duplicatePhoneCheck">전화번호
                            확인</label>
                    </div>

                    <input type="text"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        @input="checkPhoneFunction" v-model="careInfo.carePhone">
                </div>

                <label for="address" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6">주소</label>
                <input type="text"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    v-model="careInfo.careAddr">
            </div>
            <div id="fileUpload" class="col-span-3">
                <label class="block mb-2 text-sm font-medium text-gray-900 dark:text-white" for="file_input">프로필 수정</label>
                <input
                    class="block w-full text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
                    id="file_input" type="file" @change="handleFileUpload" accept=".jpg, .png">
            </div>
            <div id="editButton" class="col-end-10">
                <button type="button"
                    class="px-6 py-3.5 text-base font-medium text-white bg-gray-800 hover:bg-black focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                    @click="updateInfo">수정</button>

            </div>
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
            }
            else {
                swal('사용 가능한 전화번호입니다.')
                validatePhoneState.value = true
            }

        }
    )



}



</script>

<style scoped></style>