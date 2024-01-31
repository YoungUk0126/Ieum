<template>
    <div class="all mt-40 mb-40 ml-80 mr-80">
        <div id="ID" class="mt-6">
            <label for="ID" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">아이디/ID</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-2">
                    <input type="text"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="아이디를 입력해주세요." @input="checkId" v-model="userInfo.memberId" required>
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkValidateId">아이디
                    확인</button>
            </div>
            <label v-if="checkIdType" class="idIncorrect">4~12자의 영문자, 숫자만 사용 가능합니다.</label>
            <label v-else>사용이 가능한 아이디입니다.</label>
        </div>
        <div id="name" class="mt-6">
            <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">이름/Name</label>
            <div class="grid grid-cols-3 gap-4">
                <input type="text" id="name"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="이름을 입력해주세요." v-model="userInfo.name" @input="validateName" required>
            </div>
            <label v-if="checkNameType" class="nameIncorrect">이름은 영문자, 한글만 입력 가능합니다.</label>
            <label v-else>사용이 가능한 이름입니다.</label>
        </div>
        <div id="email" class="mt-6">
            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email address</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-2">
                    <input type="email" id="email"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="john@company.com" v-model="userInfo.email" @input="checkEmail" required>
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkValidateEmail">이메일 확인</button>
            </div>
            <label v-if="checkEmailType" class="emailIncorrect">이메일 형식이 일치하지 않습니다.</label>
        </div>
        <div id="password" class="mt-6 grid grid-cols-2 gap-4">
            <div>
                <label for="password"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">비밀번호/Password</label>
                <input type="password" id="password"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="•••••••••" v-model="userInfo.password" @input="passwordLength" required>
                <label v-if="checkPasswordType" class="passwordIncorrect">영문, 숫자, 특수문자(~!@#$%^&*) 조합 7~16 자리로
                    작성해주세요.</label>
                <label v-else>사용 가능한 비밀번호입니다.</label>
            </div>
            <div>
                <label for="confirm_password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">비밀번호
                    확인/Password
                    Check</label>
                <input type="password" id="confirm_password"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="•••••••••" v-model="passwordCheck" @input="checkPassword" required>
                <label v-if="checkPasswordRepeatType" class="passwordcheckIncorrect">일치하지 않습니다. 다시 입력해주세요.</label>
            </div>
        </div>
        <div id="phone" class="mt-6">
            <label for="last_name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">전화번호/Phone
                Number</label>
            <div class="grid grid-cols-6 gap-4">

                <div class="relative col-span-2">
                    <div class="absolute inset-y-0 start-0 top-0 flex items-center ps-3.5 pointer-events-none">
                        <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 19 18">
                            <path
                                d="M18 13.446a3.02 3.02 0 0 0-.946-1.985l-1.4-1.4a3.054 3.054 0 0 0-4.218 0l-.7.7a.983.983 0 0 1-1.39 0l-2.1-2.1a.983.983 0 0 1 0-1.389l.7-.7a2.98 2.98 0 0 0 0-4.217l-1.4-1.4a2.824 2.824 0 0 0-4.218 0c-3.619 3.619-3 8.229 1.752 12.979C6.785 16.639 9.45 18 11.912 18a7.175 7.175 0 0 0 5.139-2.325A2.9 2.9 0 0 0 18 13.446Z" />
                        </svg>
                    </div>
                    <input type="text" id="phone-input" aria-describedby="helper-text-explanation"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        @input=checkPhone v-model="userInfo.phone" placeholder="010-3333-4444" required>
                </div>

                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkValidatePhoneNumber">전화번호 확인</button>
            </div>
            <label v-if="checkPhoneNumberType" class="phoneIncorrect">전화번호 형식이 일치하지 않습니다.</label>
        </div>

        <div id="certification" class="mt-6">
            <label for="last_name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">인증번호/Certified
                Number</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-2">
                    <input type="text" id="last_name"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="인증번호를 입력해주세요." v-model="certifiedCode" required>
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="certifiedSend">인증전송</button>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="certifiedCheck">인증번호 확인</button>
            </div>
        </div>

        <div id="policy" class="items-start mt-6 flex justify-center">
            <div class="flex items-center h-5">
                <input id="remember" type="checkbox" value=""
                    class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800"
                    @click="checkPolicy" required>
            </div>
            <label for="remember" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">가입 시, <a href="#"
                    class="text-blue-600 hover:underline dark:text-blue-500" @click="showPrivacyPolicy">개인정보처리방침</a>에
                동의합니다.</label>
        </div>
        <div id="final check" class="mt-6 flex justify-center gap-20">
            <button
                class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                @click="checkPolicyAgree">확인</button>
            <button
                class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">취소</button>
        </div>
    </div>
</template>


<script setup>
import { register } from '@/api/register';
import { checkVerificationCode } from '@/api/register';
import { idCheck } from '@/api/register';
import { ref } from "vue";
import swal from 'sweetalert';
import { emailCheck } from '@/api/register';
import { phoneCheck } from '@/api/register';
import { sendVerificationCode } from '@/api/register';

const showPrivacyPolicy = () => {
    swal(`이음이(영욱컴퍼니(주))는 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.
  
이 개인정보 처리방침은 2024년 1월 1일부터 적용됩니다.

제1조(개인정보의 처리 목적)
<이음이>(영욱컴퍼니(주))은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.`)
}

const passwordCheck = ref('');
// 비밀번호 확인을 위한 변수.

const policy = ref(false);
// 약관동의를 확인을 위한 변수.

const userInfo = ref({
    "memberId": '',
    "password": '',
    "name": '',
    "phone": '',
    "email": '',
})
//json형식으로 유저정보를 보내는 변수.

const checkPasswordRepeatType = ref(true);
//비밀번호와 비밀번호 확인이 동일한지 확인하는 변수.

const validateIdstate = ref(false);
//아이디가 중복인지 아닌지 검사하는 변수.

const validateemailstate = ref(false);
//이메일이 중복인지 아닌지 검사하는 변수.

const validatephonestate = ref(false);
//전화번호가 중복인지 아닌지 검사하는 변수.

const checkIdType = ref(true);
//아이디가 유효한지 검사하는 변수.

const checkNameType = ref(true);
//이름이 유효한지 검사하는 변수.

const checkEmailType = ref(true);
//이메일이 유효한지 검사하는 변수.

const checkPhoneNumberType = ref(true);
//전화번호가 유효한지 검사하는 변수.

const checkPasswordType = ref(true);
//비밀번호가 유효한지 검사하는 변수.

const certifiedCode = ref('');
//인증코드를 담을 변수.

const certifiedCodeState = ref(true);
//인증상태를 담을 변수.

const checkValidateId = () => {
    idCheck(
        userInfo.value.memberId,
        ({ data }) => {
            if (data.data && data.data.isDuplicate) {
                swal('이미 사용 중인 아이디입니다.');
                validateIdstate.value = false;
            }
            else {
                swal('사용 가능한 아이디입니다.');
                validateIdstate.value = true;
            }
        },
        () => {
        }
    );
}
// 아이디가 중복인지 아닌지 검사하는 메서드.

const checkValidateEmail = () => {
    emailCheck(
        userInfo.value.email,
        ({ data }) => {
            if (data.data && data.data.isDuplicate) {
                swal('이미 사용 중인 이메일입니다.');
                validateIdstate.value = false;
            }
            else {
                swal('사용 가능한 이메일입니다.');
                validateIdstate.value = true;
            }
        },
        () => {

        }
    );
}
// 이메일이 중복인지 아닌지 검사하는 메서드.

const checkValidatePhoneNumber = () => {
    phoneCheck(
        userInfo.value.phone,
        ({ data }) => {
            if (data.data && data.data.isDuplicate) {
                swal('이미 사용 중인 전화번호입니다.');
                validateIdstate.value = false;
            }
            else {
                swal('사용 가능한 전화번호입니다.');
                validateIdstate.value = true;
            }
        },
        () => {
        }
    );
}
// 전화번호가 중복인지 아닌지 검사하는 메서드.

const checkPassword = () => {
    if (passwordCheck.value === userInfo.value.password) {
        checkPasswordRepeatType.value = false;
    }
    else {
        checkPasswordRepeatType.value = true;
    }
}
//비밀번호와 비밀번호 확인이 동일한지 확인하는 메서드.

const certifiedSend = () => {
    sendVerificationCode(
        userInfo.value.phone,
        () => {
        },
        () => {
        }

    )
}
// 인증번호를 보내야하는 메서드.

const certifiedCheck = () => {
    checkVerificationCode(
        userInfo.value.phone,
        certifiedCode.value,
        ({ data }) => {
            if (data.data === true) {
                certifiedCodeState.value = true;
            } else {
                certifiedCodeState.value = false;
            }
        },
        () => {
        }
    );


}
// 인증번호를 체크하는 메서드. 

const checkId = () => {
    const validateId = /^[A-Za-z0-9]{4,12}$/;
    checkIdType.value = !validateId.test(userInfo.value.memberId);
    validateIdstate.value = false;

}
// 아이디가 유효한지 검사하는 메서드.


const validateName = () => {
    const validateName = /^[A-Za-z가-힣]+$/;
    checkNameType.value = !validateName.test(userInfo.value.name);
}
// 이름의 형식이 유효한지 검사하는 메서드.

const checkEmail = () => {
    const validateemail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    checkEmailType.value = !validateemail.test(userInfo.value.email)
    validateemailstate.value = false;
}
// 이메일의 형식이 유효한지 검사하는 메서드.

const checkPhone = () => {
    const validatephone = /^010-\d{4}-\d{4}$/;
    checkPhoneNumberType.value = !validatephone.test(userInfo.value.phone)
    validatephonestate.value = false;

}
// 핸드폰 번호의 형식이 유효한지 검사하는 메서드.

const passwordLength = () => {
    const validatepassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()])[A-Za-z\d!@#$%^&*()]{7,16}$/;
    checkPasswordType.value = !validatepassword.test(userInfo.value.password);
}
// 비밀번호의 유효성 검사를 진행하는 메서드.

const checkPolicy = () => {
    policy.value = !policy.value;
}
//체크박스 여부를 확인하기 위한 메서드.

const checkPolicyAgree = () => {
    if (!checkIdType.value && !checkNameType.value && !checkPasswordRepeatType.value && !checkEmailType.value
        && !checkPhoneNumberType.value && !checkPasswordType.value && policy.value && certifiedCodeState.value) {
        registerFunc();
        swal('회원가입이 완료되었습니다.');
    }
    else {
        swal('회원가입 중 오류가 발생하였습니다.')
    }
}
//최종적으로 회원가입이 가능한지 모든 조건을 확인하는 메서드. 
//각 인증 후, 상태 체크 추가해야함.

const registerFunc = () => {
    register(
        userInfo.value,
        () => {
        }, () => {
        }
    )
}
//회원가입 데이터를 보내는 메서드.


</script>



<style scoped>
.idIncorrect,
.nameIncorrect,
.emailIncorrect,
.passwordIncorrect,
.passwordcheckIncorrect,
.phoneIncorrect {
    color: red;
}
</style>