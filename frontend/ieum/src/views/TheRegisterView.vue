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
                                                <label class="form-label" for="id">아이디/ID</label>
                                                <!-- v-model은 양방향바인딩을 시켜줌 -->
                                                <input type="text" v-model="userInfo.member_id"
                                                    class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <label class="form-label" for="Name">이름/Name</label>
                                                <input type="text" v-model="userInfo.name"
                                                    class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 d-flex align-items-center">

                                            <div class="form-outline datepicker w-100">
                                                <label for="password" class="form-label">비밀번호/password</label>
                                                <input type="password" v-model="userInfo.password"
                                                    class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline datepicker w-100">
                                                <label for="passwordCheak" class="form-label">비밀번호확인/password cheak</label>
                                                <input type="password" v-model.lazy="password_check"
                                                    @change="check_password" class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <label class="form-label" for="email">이메일/email</label>
                                                <input type="email" v-model="userInfo.email"
                                                    class="form-control form-control-lg email" />
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <label class="form-label" for="phoneNumber">휴대폰 번호/Phone Number</label>
                                                <input type="text" v-model="userInfo.phone"
                                                    class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 pb-2 d-flex align-items-center">
                                            <button type="button" class="btn btn-custom-primary w-35 py-2 fw-bold">인증
                                                전송</button>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2 ">

                                            <label class="form-label" for="certifiedNumber">인증번호/Certified</label>
                                            <div class="form-outline">
                                                <input type="text" id="certifiedNumber"
                                                    class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 pb-2 d-flex align-items-center">
                                            <button type="button" class="btn btn-custom-primary w-35 py-2 fw-bold">인증번호
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
                                            <button @click="registerFunc()"
                                                class="btn btn-custom-primary btn-lg w-50 fw-bold">확인</button>
                                        </div>
                                        <div class="col-4 text-center">
                                            <a href="/login" class="btn btn-custom-danger btn-lg w-50 fw-bold">취소</a>
                                        </div>
                                    </div>

                                    <a @click="check_policy_agree()">
                                        테스트
                                    </a>

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
import { ref } from "vue";

const password_check = ref('');
const policy = ref(false);

const userInfo = ref({
    "member_id": '',
    "password": '',
    "name": '',
    "phone": '',
    "email": '',
})
//데이터를 json으로 보내는 방식

const check_policy = () => {
    policy.value = !policy.value;
}

const check_password = () => {
    if (password_check.value != '' && password_check.value != userInfo.value.password) {
        alert('비밀번호가 일치하지 않습니다.')
    }
}


const check_policy_agree = () => {
    if (userInfo.value.password === password_check.value && policy.value) {
        registerFunc();
        alert('회원가입 완료~~');
    }
    else {
        // alert('동의체크 비밀번호 확인점');
        alert('개인정보제공동의를 확인하거나 비밀번호가 같은지 확인해주세요.')

    }
}
const registerFunc = () => {

    console.log(userInfo.value);
    //보내기전에 데이터 잘 들어가나 확인
    register(
        userInfo.value,
        //이 데이터를 보낼거야
        (response) => {
            // 보내기 성공~~
            console.log(response);
        }, () => {
            // 보내기 실패...
            console.log("sdsd")
        }
    )
}
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
    color: rgb(0, 114, 94);
    font-family: 'Noto Sans KR', sans-serif;
}

.w-35 {
    width: 35%;
}
</style>