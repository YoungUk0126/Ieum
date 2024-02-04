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
                <label for="phoneNumber"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6">전화번호</label>
                <input type="text"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    @input="checkPhoneFunction" v-model="careInfo.carePhone">

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
    if (checkPhone.value) {
        updateCareInfo()
        router.push('/')
        swal('정보가 변경되었습니다.')
    }
    else {
        swal('전화번호 형식을 다시 확인해주세요.')
        return;
    }
}

//클릭했을 때, 정보를 보내는 메서드를 실행시키는 메서드.

const updateCareInfo = () => {
    const json = JSON.stringify(careInfo.value)
    const formJson = new Blob([json], { type: 'application/json' })
    formData.append('data', formJson)
    formData.append('file', blob.value, file.value.name)
    profileEdit(
        formData,
        () => {

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
};
//이미지를 수정하는 메서드.

const checkPhoneFunction = () => {
    const validatephone = /^010-\d{4}-\d{4}$/;
    checkPhone.value = validatephone.test(careInfo.value.carePhone)
}
//전화번호가 유효한 형식인지 알아보는 메서드.



</script>

<style scoped></style>