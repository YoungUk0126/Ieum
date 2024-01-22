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
                                                <label class="form-label" for="firstName">아이디/ID</label>
                                                <input type="text" id="firstName" class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <label class="form-label" for="lastName">이름/Name</label>
                                                <input type="text" id="lastName" class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 d-flex align-items-center">

                                            <div class="form-outline datepicker w-100">
                                                <label for="birthdayDate" class="form-label">비밀번호/password</label>
                                                <input type="text" class="form-control form-control-lg" id="birthdayDate" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline datepicker w-100">
                                                <label for="birthdayDate" class="form-label">비밀번호확인/password cheak</label>
                                                <input type="text" class="form-control form-control-lg" id="birthdayDate" />
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <label class="form-label" for="emailAddress">휴대폰 번호/Phone Number</label>
                                                <input type="email" id="emailAddress"
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

                                            <label class="form-label" for="emailAddress">인증번호/Certified</label>
                                            <div class="form-outline">
                                                <input type="email" id="emailAddress"
                                                    class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 pb-2 d-flex align-items-center">
                                            <label class="form-label" for="firstName"></label>
                                            <button type="button" class="btn btn-custom-primary w-35 py-2 fw-bold">인증번호
                                                확인</button>
                                        </div>
                                    </div>

                                    <div class="mt-1" style="text-align: center; align-items: center;">
                                        <input type="checkbox" style="margin-right: 8px;">
                                        <label for="myCheckbox">회원가입 시, 필요한 개인정보 수집 이용내역에 대해
                                            동의합니다.</label>
                                    </div>

                                    <div class="mt-5 d-flex justify-content-around">
                                        <div class="col-4 text-center">
                                            <a href="/" class="btn btn-custom-primary btn-lg w-50 fw-bold">확인</a>
                                        </div>
                                        <div class="col-4 text-center">
                                            <a href="/login" class="btn btn-custom-danger btn-lg w-50 fw-bold">취소</a>
                                        </div>
                                    </div>

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