<template>
    <div>
        <section class="vh-100 gradient-custom">
            <div class="container py-5 h-100">
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7">
                        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
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
                                                <label class="form-label" for="lastName">이름</label>
                                                <input type="text" id="lastName" class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 d-flex align-items-center">

                                            <div class="form-outline datepicker w-100">
                                                <label for="birthdayDate" class="form-label">비밀번호</label>
                                                <input type="text" class="form-control form-control-lg" id="birthdayDate" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline datepicker w-100">
                                                <label for="birthdayDate" class="form-label">비밀번호확인</label>
                                                <input type="text" class="form-control form-control-lg" id="birthdayDate" />
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                <label class="form-label" for="emailAddress">Email</label>
                                                <input type="email" id="emailAddress"
                                                    class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 pb-2 d-flex align-items-center justify-content-center">
                                            <button type="button" class="btn btn-primary">인증 전송</button>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2 ">

                                            <label class="form-label" for="emailAddress">인증번호</label>
                                            <div class="form-outline">
                                                <input type="email" id="emailAddress"
                                                    class="form-control form-control-lg" />
                                            </div>

                                        </div>
                                        <div class="col-md-6 pb-2 d-flex align-items-center justify-content-center">
                                            <label class="form-label" for="firstName"></label>
                                            <button type="button" class="btn btn-primary">인증번호 확인</button>

                                        </div>
                                    </div>

                                    <div class="d-flex justify-content-around">
                                        <div class="col-4 text-center">
                                            <a href="/" class="btn btn-primary btn-lg">확인</a>
                                        </div>
                                        <div class="col-4 text-center">
                                            <a href="/login" class="btn btn-danger btn-lg">취소</a>
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
</style>