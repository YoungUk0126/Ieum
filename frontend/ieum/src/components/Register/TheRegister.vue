<template>
    <div class="all mt-40 mb-40 ml-6 mr-2">
        <div id="ID" class="mt-6">
            <label for="ID" class="block mb-2 text-lg text-gray-900 dark:text-white font-bold ">아이디/ID</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-3">
                    <input type="text"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="아이디를 입력해주세요." @input="checkId" v-model="userInfo.memberId" required />
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkValidateId">
                    <span class="text-xs">아이디 확인</span>
                </button>
            </div>
            <label v-if="checkIdType" class="idIncorrect">4~12자의 영문자, 숫자만 사용 가능합니다.</label>
        </div>
        <div id="name" class="mt-8">
            <label for="name" class="block mb-2 text-lg text-gray-900 dark:text-white font-bold">이름/Name</label>
            <div class="grid grid-cols-2 gap-4">
                <input type="text" id="name"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 mb-2"
                    placeholder="이름을 입력해주세요." v-model="userInfo.name" @input="validateName" required />
            </div>
            <label v-if="checkNameType" class="nameIncorrect mt-2">이름은 영문자, 한글만 입력 가능합니다.</label>
        </div>
        <div id="email" class="mt-8">
            <label for="email" class="block mb-2 text-lg text-gray-900 dark:text-white font-bold">이메일/email</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-3">
                    <input type="email" id="email"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="john@company.com" v-model="userInfo.email" @input="checkEmail" required />
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkValidateEmail">
                    <span class="text-xs">이메일 확인</span>
                </button>
            </div>
            <label v-if="checkEmailType" class="emailIncorrect">이메일 형식이 일치하지 않습니다.</label>
        </div>
        <div id="password" class="mt-8 grid grid-cols-2 gap-4">
            <div>
                <label for="password"
                    class="block mb-2 text-lg text-gray-900 dark:text-white font-bold">비밀번호/Password</label>
                <input type="password" id="password"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 mb-2"
                    placeholder="•••••••••" v-model="userInfo.password" @input="passwordLength" required />
                <label v-if="checkPasswordType" class="passwordIncorrect">영문, 숫자, 특수문자(~!@#$%^&*) 조합 7~16 자리로
                    작성해주세요.</label>
                <label v-else>사용 가능한 비밀번호입니다.</label>
            </div>
            <div>
                <label for="confirm_password" class="block mb-2 text-lg text-gray-900 dark:text-white font-bold">비밀번호
                    확인/Password Check</label>
                <input type="password" id="confirm_password"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 mb-2"
                    placeholder="•••••••••" v-model="passwordCheck" @input="checkPassword" required />
                <label v-if="checkPasswordRepeatType" class="passwordcheckIncorrect">일치하지 않습니다. 다시 입력해주세요.</label>
            </div>
        </div>
        <div id="phone" class="mt-8">
            <label for="phone" class="block mb-2 text-lg text-gray-900 dark:text-white font-bold">전화번호/phone Number</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-3">
                    <input type="text" id="phone"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="john@company.com" v-model="userInfo.phone" @input="checkPhone" required />
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkValidatePhoneNumber">
                    <span class="text-xs2">전화번호확인</span>
                </button>
            </div>
            <label v-if="checkPhoneNumberType" class="phoneIncorrect">전화번호 형식이 일치하지 않습니다.</label>
        </div>

        <div id="certification" class="mt-8">
            <label for="last_name" class="block mb-2 text-lg text-gray-900 dark:text-white font-bold">인증번호/Certified
                Number</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-3">
                    <input type="text" id="last_name"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="인증번호를 입력해주세요." v-model="certifiedCode" required />
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="certifiedSend">
                    <span class="text-xs">인증전송</span>
                </button>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="certifiedCheck">
                    <span class="text-xs2">인증번호확인</span>
                </button>
            </div>
        </div>

        <div id="policy" class="items-start mt-8 flex justify-center">
            <div class="flex items-center h-5">
                <input id="remember" type="checkbox" value=""
                    class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800"
                    @click="checkPolicy" required />
            </div>
            <label for="remember" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">가입 시,
                <a href="#" class="text-blue-600 hover:underline dark:text-blue-500"
                    @click="showPrivacyPolicy">개인정보처리방침</a>에
                동의합니다.</label>
        </div>
        <div id="final check" class="mt-8 flex justify-center gap-20">
            <button
                class="accept text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                @click="checkPolicyAgree">
                확인
            </button>
            <button
                class="deny text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">
                취소
            </button>
        </div>
    </div>
</template>
  
<script setup>
import { register } from '../../api/register'
import { checkVerificationCode } from '@/api/register'
import { idCheck } from '@/api/register'
import { ref } from 'vue'
import swal from 'sweetalert'
import { emailCheck } from '@/api/register'
import { phoneCheck } from '@/api/register'
import { sendVerificationCode } from '@/api/register'
import { useRouter } from 'vue-router';

const showPrivacyPolicy = () => {
    swal(`이음이(영욱컴퍼니(주))는 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.
    
  이 개인정보 처리방침은 2024년 1월 1일부터 적용됩니다.
  
  제1조(개인정보의 처리 목적)
  <이음이>(영욱컴퍼니(주))은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.`)
}

const router = useRouter();

const passwordCheck = ref('')
// 비밀번호 확인을 위한 변수.

const policy = ref(false)
// 약관동의를 확인을 위한 변수.

const userInfo = ref({
    memberId: '',
    password: '',
    name: '',
    phone: '',
    email: ''
})
//json형식으로 유저정보를 보내는 변수.



const checkPasswordRepeatType = ref(true)
//비밀번호와 비밀번호 확인이 동일한지 확인하는 변수.

const validateIdstate = ref(false)
//아이디가 중복인지 아닌지 검사하는 변수.

const validateemailstate = ref(false)
//이메일이 중복인지 아닌지 검사하는 변수.

const validatephonestate = ref(false)
//전화번호가 중복인지 아닌지 검사하는 변수.

const checkIdType = ref(true)
//아이디가 유효한지 검사하는 변수.

const checkNameType = ref(true)
//이름이 유효한지 검사하는 변수.

const checkEmailType = ref(true)
//이메일이 유효한지 검사하는 변수.

const checkPhoneNumberType = ref(true)
//전화번호가 유효한지 검사하는 변수.

const checkPasswordType = ref(true)
//비밀번호가 유효한지 검사하는 변수.

const certifiedCode = ref('')
//인증코드를 담을 변수.

const certifiedCodeState = ref(false)
//인증상태를 담을 변수.

const checkValidateId = () => {
    idCheck(
        userInfo.value.memberId,
        ({ data }) => {
            if (data.data.isDuplicated) {
                swal('이미 사용 중인 아이디입니다.')
                validateIdstate.value = false
            } else {
                swal('사용 가능한 아이디입니다.')
                validateIdstate.value = true
            }
        }
    )
}
// 아이디가 중복인지 아닌지 검사하는 메서드.

const checkValidateEmail = () => {
    emailCheck(
        userInfo.value.email,
        ({ data }) => {
            if (data.data.isDuplicated) {
                swal('이미 사용 중인 이메일입니다.')
                validateemailstate.value = false
            } else {
                swal('사용 가능한 이메일입니다.')
                validateemailstate.value = true
            }
        }
    )
}
// 이메일이 중복인지 아닌지 검사하는 메서드.

const checkValidatePhoneNumber = () => {
    phoneCheck(
        userInfo.value.phone,
        ({ data }) => {
            if (data.data.isDuplicated) {
                swal('이미 사용 중인 전화번호입니다.')
                validatephonestate.value = false
            } else {
                swal('사용 가능한 전화번호입니다.')
                validatephonestate.value = true
            }
        }
    )
}
// 전화번호가 중복인지 아닌지 검사하는 메서드.

const checkPassword = () => {
    if (passwordCheck.value === userInfo.value.password) {
        checkPasswordRepeatType.value = false
    } else {
        checkPasswordRepeatType.value = true
    }
}
//비밀번호와 비밀번호 확인이 동일한지 확인하는 메서드.

const certifiedSend = () => {
    if (validatephonestate.value) {
        sendVerificationCode(
            userInfo.value.phone,
            (response) => {
                if (response.data.success) {
                    swal('인증번호를 보냈습니다.')
                }
                else {
                    swal('전화번호를 확인해주세요.')
                }
            }
        )
    }
    else {
        swal('중복이 아닌 전화번호를 입력해주세요.')
    }
}
// 인증번호를 보내야하는 메서드.

const certifiedCheck = () => {
    checkVerificationCode(
        {
            "phone": userInfo.value.phone,
            "code": certifiedCode.value
        },
        (response) => {
            if (response.data.success) {
                certifiedCodeState.value = true
                swal('인증번호가 확인되었습니다.')
            } else {
                certifiedCodeState.value = false
                swal('인증번호를 다시 확인해주세요.')
            }
        }
    )
}
// 인증번호를 체크하는 메서드.

const checkId = () => {
    const validateId = /^[A-Za-z0-9]{4,12}$/
    checkIdType.value = !validateId.test(userInfo.value.memberId)
    validateIdstate.value = false
}
// 아이디가 유효한지 검사하는 메서드.

const validateName = () => {
    const validateName = /^[A-Za-z가-힣]+$/
    checkNameType.value = !validateName.test(userInfo.value.name)
}
// 이름의 형식이 유효한지 검사하는 메서드.

const checkEmail = () => {
    const validateemail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
    checkEmailType.value = !validateemail.test(userInfo.value.email)
    validateemailstate.value = false
}
// 이메일의 형식이 유효한지 검사하는 메서드.

const checkPhone = () => {
    const validatephone = /^010-\d{4}-\d{4}$/
    checkPhoneNumberType.value = !validatephone.test(userInfo.value.phone)
    validatephonestate.value = false
}
// 핸드폰 번호의 형식이 유효한지 검사하는 메서드.

const passwordLength = () => {
    const validatepassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()])[A-Za-z\d!@#$%^&*()]{7,16}$/
    checkPasswordType.value = !validatepassword.test(userInfo.value.password)
}
// 비밀번호의 유효성 검사를 진행하는 메서드.

const checkPolicy = () => {
    policy.value = !policy.value
}
//체크박스 여부를 확인하기 위한 메서드.

const checkPolicyAgree = () => {
    if (
        !checkIdType.value &&
        !checkNameType.value &&
        !checkPasswordRepeatType.value &&
        !checkEmailType.value &&
        !checkPhoneNumberType.value &&
        !checkPasswordType.value &&
        policy.value &&
        certifiedCodeState.value &&
        validateIdstate.value &&
        validateemailstate.value &&
        validatephonestate.value
    ) {
        registerFunc()
        swal('회원가입이 완료되었습니다. 로그인 해주세요.')
        router.push('/');
    } else {
        swal('회원가입 중 오류가 발생하였습니다.')
    }
}
//최종적으로 회원가입이 가능한지 모든 조건을 확인하는 메서드.
//각 인증 후, 상태 체크 추가해야함.

const registerFunc = () => {
    register(
        userInfo.value,
        (response) => {
            if (response.data.success) {
                swal('회원가입이 완료되었습니다.')
            }
            else {
                swal('회원가입 중 오류가 발생하였습니다.')
            }
        }
    )
}
//회원가입 데이터를 보내는 메서드.
</script>
  
<style scoped>
.all {
    min-width: 680px !important;
    min-height: 560px !important;
    max-width: 680px !important;
    max-height: 985px !important;
}

.text-xs{
    font-size: 9.5px;
}

.text-xs2{
    font-size: 8.5px;
}

.accept{
    background-color: #8FF9F9;
    color: #707070;
}

.deny{
    background-color: #FBEBEB;
    color: #707070;
}

.idIncorrect,
.emailIncorrect,
.passwordIncorrect,
.passwordcheckIncorrect,
.nameIncorrect,
.phoneIncorrect{
    font-size: 16px;
    
}

</style>