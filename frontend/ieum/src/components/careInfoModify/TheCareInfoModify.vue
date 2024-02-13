<template>
    <div class="max-w-3xl p-6 mx-auto">
        <div id="image" class="flex justify-center mt-24 ml-6 mb-10">
            <img :src="imageLink" alt="Your Image" class="w-1/3 h-1/3" />
        </div>
        <div id="fileUpload" class="mb-24 flex justify-center">
            <input
                class="w-50 text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
                id="file_input" type="file" @change="handleFileUpload" accept=".jpg, .png">
        </div>
        <div id="name" class="grid grid-cols-4 gap-4 mb-12">
            <label id="name"
                class="color text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">성함</label>
            <input type="text"
                class="col-span-3 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                v-model="careInfo.careName" />
        </div>
        <div id="code" class="grid grid-cols-4 gap-4 mb-12">
            <label id="code"
                class="color text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">코드</label>
            <input type="text"
                class="col-span-3 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                v-model="careInfo.careSerial" />
        </div>
        <div id="birthDay" class="grid grid-cols-4 gap-4 mb-12">
            <label id="birthDay"
                class="color text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">생일</label>
            <input type="date"
                class="col-span-3 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                v-model="careInfo.careBirth" />
        </div>
        <div id="address" class="grid grid-cols-4 gap-4 mb-12">
            <label id="address"
                class="color text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">주소</label>
            <input type="text"
                class="col-span-3 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                v-model="careInfo.careAddr" />
        </div>
        <div id="sleepStartTime" class="grid grid-cols-4 gap-4 mb-12">
            <label id="sleepStartTime"
                class="color text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">취침</label>
            <input type="time"
                class="col-span-3 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                @input="checkSleepStart" v-model="wakeSleepTime.sleepStartTime" />
        </div>
        <div id="sleepEndTime" class="grid grid-cols-4 gap-4 mb-12">
            <label id="sleepEndTime"
                class="color text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">기상</label>
            <input type="time"
                class="col-span-3 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                @input="checkSleepEnd" v-model="wakeSleepTime.sleepEndTime" />
        </div>
        <div id="phone" class="grid grid-cols-4 gap-4 mb-12">
            <label id="phone"
                class="color text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">번호</label>
            <input type="text"
                class="col-span-3 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                @input="checkPhoneFunction" v-model="careInfo.carePhone" />
        </div>
        <div id="phoneCheck" class="grid grid-cols-4 gap-4 mb-12">
            <button type="button"
                class="h-12 col-start-3 col-end-5 text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                @click="duplicatePhoneCheck">
                <span class="text-xl">전화번호 확인</span>
            </button>

        </div>
        <div id="gender" class="grid grid-cols-8 gap-4 mb-12">
            <label id="gender"
                class="col-span-2 color mt-4 text-3xl text-gray-900 dark:text-white font-semibold flex justify-center">성별</label>
            <div class="col-span-3 flex items-center ps-4 border border-gray-200 rounded dark:border-gray-700">
                <input id="bordered-radio-1" type="radio" v-model="careInfo.careGender" value="MALE" name="bordered-radio"
                    class="radio w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                <label for="bordered-radio-1"
                    class="w-full py-4 ms-2 text-2xl font-medium text-gray-900 dark:text-gray-300">남</label>
            </div>
            <div class="col-span-3 flex items-center ps-4 border border-gray-200 rounded dark:border-gray-700">
                <input checked id="bordered-radio-2" type="radio" v-model="careInfo.careGender" value="FEMALE"
                    name="bordered-radio"
                    class="radio w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                <label for="bordered-radio-2"
                    class="w-full py-4 ms-2 text-2xl font-medium text-gray-900 dark:text-gray-300">여</label>
            </div>
        </div>
        <div class="flex justify-center mt-20 ml-4">
            <button type="button"
                class="edit px-14 py-3.5 text-3xl font-bold text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
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
import { getAllSleep, modifySleep } from "../../api/modalAlarms/sleep";

const careInfo = ref({
    "careName": "",
    "careGender": "",
    "careBirth": "",
    "carePhone": "",
    "careAddr": "",
    "careSerial": "",
    "careImage": ""
})

const wakeSleepTime = ref({
    "sleepStartTime": "",
    "sleepEndTime": "",
    "sleepInfoNo": ""
})

const decoyWakeSleepTime = ref({
    "sleepStartTime": "",
    "sleepEndTime": ""
})

const checkSleepStart = () => {
    wakeSleepTime.value.sleepStartTime = `${wakeSleepTime.value.sleepStartTime}:00`
    // const sleepList = wakeSleepTime.value.sleepStartTime.split(':');
    // const sleepValue = (sleepList[0] * 3600) + (sleepList[1] * 60)
    // console.log(sleepValue)
}

const checkSleepEnd = () => {
    wakeSleepTime.value.sleepEndTime = `${wakeSleepTime.value.sleepEndTime}:00`
}

const imageLink = `https://i10a303.p.ssafy.io/images/${careInfo.value.careImage}`

const formData = new FormData()

const checkPhone = ref(true);
//전화번호 유효 체크 변수.

const validatePhoneState = ref(true);
//전화번호 중복 상태 체크 변수.

const imageUploadState = ref(false);
//이미지가 업로드 되어 있는지 확인하기 위한 변수.

onMounted(() => {
    beforeCareInfo()
    beforeSleepInfo()
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

const beforeSleepInfo = () => {
    getAllSleep(
        ({ data }) => {
            const startTimeParts = data.data.sleepStartTime.split(':');
            const endTimeParts = data.data.sleepEndTime.split(':');
            wakeSleepTime.value.sleepStartTime = startTimeParts[0] + ':' + startTimeParts[1];
            wakeSleepTime.value.sleepEndTime = endTimeParts[0] + ':' + endTimeParts[1];
            decoyWakeSleepTime.value.sleepStartTime = wakeSleepTime.value.sleepStartTime
            decoyWakeSleepTime.value.sleepEndTime = wakeSleepTime.value.sleepEndTime
            wakeSleepTime.value.sleepInfoNo = data.data.sleepInfoNo;
        }
    )
}

const updateInfo = () => {
    if (checkPhone.value && validatePhoneState.value) {
        updateCareInfo()
    }
    else {
        swal('전화번호 확인을 다시 진행해주세요.')
        return;
    }
}

//클릭했을 때, 정보를 보내는 메서드를 실행시키는 메서드.

const updateCareInfo = () => {
    if (wakeSleepTime.value.sleepStartTime === decoyWakeSleepTime.value.sleepStartTime) {
        wakeSleepTime.value.sleepStartTime = `${wakeSleepTime.value.sleepStartTime}:00`
    }
    if (wakeSleepTime.value.sleepEndTime === decoyWakeSleepTime.value.sleepEndTime) {
        wakeSleepTime.value.sleepEndTime = `${wakeSleepTime.value.sleepEndTime}:00`
    }
    const json = JSON.stringify(careInfo.value)
    const formJson = new Blob([json], { type: 'application/json' })
    formData.append('data', formJson)
    if (imageUploadState.value) {
        formData.append('file', blob.value, file.value.name)
    }
    else {
        formData.append('file', null)
    }
    modifySleep(
        wakeSleepTime.value,
        (response) => {
            console.log(wakeSleepTime.value)
            console.log(response.data.success)
            console.log(response)
            if (response.data.success) {
                profileEdit(
                    formData,
                    (response) => {
                        console.log(json)
                        console.log(response.data)
                        if (response.data.success) {
                            swal('정보가 변경되었습니다.').then(
                                router.push('/careinfo')
                            )
                        }
                        else {
                            swal('정보 변경 중 오류가 발생하였습니다.')
                            return;
                        }
                    }
                )
            }
            else {
                swal('시간형식을 다시 확인해주세요.')
                return;
            }
        }
    )
    // profileEdit(
    //     formData,
    //     (response) => {
    //         console.log(json)
    //         console.log(response.data)
    //         if (response.data.success) {
    //             swal('정보가 변경되었습니다.').then(
    //                 router.push('/careinfo')
    //             )
    //         }
    //         else {
    //             swal('정보 변경 중 오류가 발생하였습니다.')
    //             return;
    //         }
    //     }
    // )


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
    if (!checkPhone.value) {
        swal('전화번호 형식을 확인해주세요.')
        return;
    }
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
.edit {
    background-color: #8FF9F9;
    color: #707070;
}

.color {
    color: #707070;
}
</style>