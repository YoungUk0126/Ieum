<template>
    <div class="register">
        <section class="vh-100 gradient-custom">
            <div class="container py-5 h-100">
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7 w-100">
                        <div class="card shadow-2-strong card-registration">
                            <div class="card-body p-4 p-md-5">
                                <h3 class="regi">회원가입</h3>
                                <form>

                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <label class="form-label">아이디/ID</label>
                                                <button type="button" class="id_check btn btn-custom-primary btn-sm"
                                                    @click="validateId">아이디 검사</button>
                                                <input type="text" v-model="userInfo.member_id"
                                                    class="form-control form-control-lg" @input="check_id" />
                                                <label v-if="checkidtype">아이디형식이 일치하지 않습니다.</label>

                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <label class="form-label" for="Name">이름/Name</label>
                                                <input type="text" v-model="userInfo.name" @input="validateName"
                                                    class="form-control form-control-lg" autocomplete="new-password" />
                                                <label v-if="checknametype">이름형식이 일치하지 않습니다.</label>

                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 d-flex align-items-center">

                                            <div class="form-outline datepicker w-100">
                                                <label for="password" class="form-label">비밀번호/password</label>
                                                <input type="password" v-model="userInfo.password"
                                                    class="form-control form-control-lg" autocomplete="new-password"
                                                    @input="passwordlength" />
                                                <label v-if="checkpasswordtype">비밀번호 형식이 일치하지 않습니다.</label>

                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline datepicker w-100">
                                                <label for="passwordCheck" class="form-label">비밀번호확인/password check</label>
                                                <input type="password" v-model="password_check" @input="check_password"
                                                    class="form-control form-control-lg" autocomplete="new-password" />
                                                <label v-if="checkpasswordrepeattype">비밀번호칸에 적은 비밀번호와 다릅니다.</label>

                                            </div>

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <label class="form-label" for="email">이메일/email</label>
                                                <input type="email" v-model="userInfo.email" @input="check_eamil"
                                                    class="form-control form-control-lg email" />
                                                <label v-if="checkemailtype">이메일 형식이 일치하지 않습니다.</label>

                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <label class="form-label" for="phoneNumber">휴대폰 번호/Phone Number</label>
                                                <input type="text" v-model="userInfo.phone" @input="check_phone"
                                                    class="form-control form-control-lg" />
                                                <label v-if="checkphonenumbertype">전화번호 형식이 일치하지 않습니다.</label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 pb-2 d-flex align-items-center">
                                            <button type="button" class="btn btn-custom-primary w-35 py-2 fw-bold"
                                                @click="certifiedsend">인증
                                                전송</button>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2 ">

                                            <label class="form-label" for="certifiedNumber">인증번호/Certified</label>
                                            <div class="form-outline">
                                                <input type="text" id="certifiedNumber" class="form-control form-control-lg"
                                                    v-model="certifiedcode" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 pb-2 d-flex align-items-center">
                                            <button type="button" class="btn btn-custom-primary w-35 py-2 fw-bold"
                                                @click="certifiedcheck">인증번호
                                                확인</button>
                                        </div>
                                    </div>

                                    <div class="mt-1" style="text-align: center; align-items: center;">
                                        <input type="checkbox" @click="check_policy" style="margin-right: 8px;">
                                        <label for="myCheckbox">회원가입 시, 필요한 개인정보 수집 이용내역에 대해
                                            동의합니다.</label>
                                    </div>

                                    <div class="mt-5 d-flex justify-content-around">
                                        <div class="col-4 text-center">
                                            <button @click="check_policy_agree"
                                                class="btn btn-custom-primary btn-lg w-50 fw-bold">확인</button>
                                        </div>
                                        <div class="col-4 text-center">
                                            <a href="/login" class="btn btn-custom-danger btn-lg w-50 fw-bold">취소</a>
                                        </div>
                                    </div>

                                    <div @click="check_policy_agree">테스트</div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>


<script setup>
import { register } from '@/api/register';
import { checkVerificationCode } from '@/api/register';
import { idcheck } from '@/api/register';
import { ref } from "vue";
import swal from 'sweetalert';

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
    if (!checkidtype.value && !checknametype.value && !checkpasswordrepeattype.value && !checkemailtype.value && !checkphonenumbertype.value && !checkpasswordtype.value && policy.value && certifiedcodestate.value) {
        registerFunc();
        swal('회원가입이 완료되었습니다.');
    }
    else {
        swal('회원가입 중 오류가 발생하였습니다.')
    }
}
//최종적으로 회원가입이 가능한지 모든 조건을 확인하는 메서드. 
//추가적인 작업을 하고, 조건을 더 추가해야함.

const registerFunc = () => {

    console.log(userInfo.value);
    register(
        userInfo.value,
        () => {
        }, () => {
        }
    )
}
//register.js의 주소로 해당 데이터를 보내는 메서드.

const validateIdstate = ref(false);
//사용가능한 아이디일경우, true로 전환.

const validateId = () => {
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

const checkidtype = ref(true);

const check_id = () => {
    const validateId = /^[A-Za-z0-9]{4,12}$/;
    checkidtype.value = !validateId.test(userInfo.value.member_id);

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
}
// 이메일의 형식이 유효한지 검사하는 메서드.

const checkphonenumbertype = ref(true);
const check_phone = () => {
    const validatephone = /^010-\d{4}-\d{4}$/;
    checkphonenumbertype.value = !validatephone.test(userInfo.value.phone)

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

.h1,
.h2,
.h3,
.h4,
.h5,
.h6,
h1,
h2,
h3,
h4,
h5,
h6 {
    margin-top: 0;
    margin-bottom: 30px;
    font-weight: 500;
    line-height: 1.2;
    color: var(--bs-heading-color);
}


.regi {
    color: rgb(0, 114, 94);
    font-family: 'Noto Sans KR', sans-serif;
}

.btn-custom-primary {
    background-color: #27A791;
    color: #fff;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.btn-custom-danger {
    background-color: #A4A4A4;
    color: #fff;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.btn-custom-primary:hover {
    background-color: #1d7b66;
}

.btn-custom-danger:hover {
    background-color: #8c8c8c;
}

.btn-custom-primary:hover,
.btn-custom-danger:hover {
    color: #fff !important;
}

.register {
    font-family: 'Noto Sans KR', sans-serif;
}

.w-35 {
    width: 35%;
}

.id_check {
    margin-left: 340px;
}
</style>