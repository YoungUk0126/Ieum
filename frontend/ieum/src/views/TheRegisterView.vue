<template>
    <div class="all mt-40 mb-40 ml-80 mr-80">
        <div id="ID" class="mt-6">
            <label for="ID" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">아이디/ID</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-2">
                    <input type="text"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="아이디를 입력해주세요." @input="check_id" v-model="userInfo.member_id" required>
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkvalidateId">아이디
                    확인</button>
            </div>
            <label v-if="checkidtype">아이디는 4-12자의 영어또는숫자를 사용해야합니다.</label>
            <label v-else>사용이 가능한 아이디입니다.</label>
        </div>
        <div id="name" class="mt-6">
            <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">이름/Name</label>
            <div class="grid grid-cols-3 gap-4">
                <input type="text" id="name"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="이름을 입력해주세요." v-model="userInfo.name" @input="validateName" required>
            </div>
            <label v-if="checknametype">이름은 1글자이상의 영어 또는 한글만 입력 가능합니다.</label>
            <label v-else>사용이 가능한 이름입니다.</label>
        </div>
        <div id="email" class="mt-6">
            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email address</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-2">
                    <input type="email" id="email"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="john.doe@company.com" v-model="userInfo.email" @input="check_eamil" required>
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkvalidateemail">이메일 확인</button>
            </div>
            <label v-if="checkemailtype">이메일 형식이 일치하지 않습니다. xxxx@xxx.xxx로 기입해주세요.</label>
        </div>
        <div id="password" class="mt-6 grid grid-cols-2 gap-4">
            <div>
                <label for="password"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">비밀번호/Password</label>
                <input type="password" id="password"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="•••••••••" v-model="userInfo.password" @input="passwordlength" required>
                <label v-if="checkpasswordtype">비밀번호는 영어,숫자,특수문자'!@#$%^&*()'가 적어도 1개씩 들어간 7-16자로
                    기입해주세요.</label>
                <label v-else>사용이 가능한 비밀번호입니다.</label>
            </div>
            <div>
                <label for="confirm_password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">비밀번호
                    확인/Password
                    Check</label>
                <input type="password" id="confirm_password"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="•••••••••" v-model="password_check" @input="check_password" required>
                <label v-if="checkpasswordrepeattype">비밀번호칸에 적은 비밀번호와 다릅니다.</label>
            </div>
        </div>
        <div id="phone" class="mt-6">
            <label for="last_name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">전화번호/Phone
                Number</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-2">
                    <input type="text" id="phone_number"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="010-xxxx-xxxx" v-model="userInfo.phone" @input="check_phone" required>
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="checkvalidatephonenumber">전화번호 확인</button>
            </div>
            <label v-if="checkphonenumbertype">전화번호 형식이 일치하지 않습니다. 010-xxxx-xxxx로
                입력해주세요.</label>
        </div>

        <div id="certification" class="mt-6">
            <label for="last_name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">인증번호/Certified
                Number</label>
            <div class="grid grid-cols-6 gap-4">
                <div class="col-span-2">
                    <input type="text" id="last_name"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        placeholder="인증번호를 입력해주세요." v-model="certifiedcode" required>
                </div>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="certifiedsend">인증전송</button>
                <button type="button"
                    class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    @click="certifiedcheck">인증번호 확인</button>
            </div>
        </div>

        <div id="policy" class="items-start mt-6 flex justify-center">
            <div class="flex items-center h-5">
                <input id="remember" type="checkbox" value=""
                    class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800"
                    @click="check_policy" required>
            </div>
            <label for="remember" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">가입 시, <a href="#"
                    class="text-blue-600 hover:underline dark:text-blue-500" @click="showPrivacyPolicy">개인정보처리방침</a>에
                동의합니다.</label>
        </div>
        <div id="final check" class="mt-6 flex justify-center gap-20">
            <button
                class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                @click="check_policy_agree">확인</button>
            <button
                class="text-white bg-green-500 hover:bg-[#1d7b66] focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">취소</button>
        </div>
    </div>
</template>


<script setup>
import { register } from '@/api/register';
import { checkVerificationCode } from '@/api/register';
import { idcheck } from '@/api/register';
import { ref } from "vue";
import swal from 'sweetalert';
import { emailcheck } from '@/api/register';
import { phonecheck } from '@/api/register';

const showPrivacyPolicy = () => {
    swal(`이음이(영욱컴퍼니(주))는 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.
  
이 개인정보 처리방침은 2024년 1월 1일부터 적용됩니다.

제1조(개인정보의 처리 목적)
<이음이>(영욱컴퍼니(주))은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.`)
}

const password_check = ref('');
// 비밀번호 확인을 위한 변수.

const policy = ref(false);
// 약관동의를 확인을 위한 변수.

const userInfo = ref({
    "member_id": '',
    "password": '',
    "name": '',
    "phone": '',
    "email": '',
})
//json형식으로 유저정보를 보내는 변수.

const check_policy = () => {
    policy.value = !policy.value;
}
//체크박스 여부를 확인하기 위한 메서드.

const checkpasswordrepeattype = ref(true);
const check_password = () => {
    if (password_check.value === userInfo.value.password) {
        checkpasswordrepeattype.value = false;
    }
    else {
        checkpasswordrepeattype.value = true;
    }
}
//비밀번호와 비밀번호 확인이 동일한지 확인하는 메서드.

const check_policy_agree = () => {
    if (!checkidtype.value && !checknametype.value && !checkpasswordrepeattype.value && !checkemailtype.value
        && !checkphonenumbertype.value && !checkpasswordtype.value && policy.value && certifiedcodestate.value) {
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

    console.log(userInfo.value);
    register(
        userInfo.value,
        () => {
        }, () => {
        }
    )
}
//회원가입 데이터를 보내는 메서드.

const validateIdstate = ref(false);
//사용가능한 아이디일경우, true로 전환.

const checkvalidateId = () => {
    idcheck(
        userInfo.value.member_id,
        () => {
            swal('사용 가능한 아이디입니다.');
            validateIdstate.value = true;
        },
        () => {
            swal('이미 사용 중인 아이디입니다.');
            validateIdstate.value = false;
        }
    );
}
// 아이디가 중복인지 아닌지 검사하는 메서드.

const validateemailstate = ref(false);

const checkvalidateemail = () => {
    emailcheck(
        userInfo.value.email,
        () => {
            swal('사용 가능한 이메일입니다.');
            validateemailstate.value = true;
        },
        () => {
            swal('이미 사용 중인 이메일입니다.');
            validateemailstate.value = false;
        }
    );
}
// 이메일이 중복인지 아닌지 검사하는 메서드.

const validatephonestate = ref(false);

const checkvalidatephonenumber = () => {
    phonecheck(
        userInfo.value.phone,
        () => {
            swal('사용 가능한 전화번호입니다.');
            validatephonestate.value = true;
        },
        () => {
            swal('이미 사용 중인 전화번호입니다.');
            validatephonestate.value = false;
        }
    );
}
// 전화번호가 중복인지 아닌지 검사하는 메서드.

const checkidtype = ref(true);

const check_id = () => {
    const validateId = /^[A-Za-z0-9]{4,12}$/;
    checkidtype.value = !validateId.test(userInfo.value.member_id);
    validateIdstate.value = false;

}
// 아이디가 유효한지 검사하는 메서드.

const checknametype = ref(true);

const validateName = () => {
    const validateName = /^[A-Za-z가-힣]+$/;
    checknametype.value = !validateName.test(userInfo.value.name);
}
// 이름의 형식이 유효한지 검사하는 메서드.

const checkemailtype = ref(true);
const check_eamil = () => {
    const validateemail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    checkemailtype.value = !validateemail.test(userInfo.value.email)
    validateemailstate.value = false;
}
// 이메일의 형식이 유효한지 검사하는 메서드.

const checkphonenumbertype = ref(true);
const check_phone = () => {
    const validatephone = /^010-\d{4}-\d{4}$/;
    checkphonenumbertype.value = !validatephone.test(userInfo.value.phone)
    validatephonestate.value = false;

}
// 핸드폰 번호의 형식이 유효한지 검사하는 메서드.

const certifiedsend = () => {
    swal('인증번호가 전송되었습니다.')
    register(
        userInfo.value.phone,
        () => {
        },
        () => {
        }

    )
}
// 인증번호를 보내야하는 메서드.

const certifiedcode = ref('');
//코드를 담을 변수.

const certifiedcodestate = ref(true);
//코드의 상태를 담을 변수.

const certifiedcheck = () => {
    checkVerificationCode(
        userInfo.value.phone,
        certifiedcode.value,
        (response) => {
            console.log(response);
            if (response.data === true) {
                certifiedcodestate.value = true;
            } else {
                certifiedcodestate.value = false;
            }
        },
        () => {
        }
    );


}
// 인증번호를 체크하는 메서드. 

const checkpasswordtype = ref(true);
const passwordlength = () => {
    const validatepassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()])[A-Za-z\d!@#$%^&*()]{7,16}$/;
    checkpasswordtype.value = !validatepassword.test(userInfo.value.password);


}
// 비밀번호의 유효성 검사를 진행하는 메서드.


</script>



<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');

.all {
    font-family: 'Noto Sans KR', sans-serif;
}
</style>