<template>
  <div class="container card m-5">
    <div class="row g-0">
      <div class="col-md-4 text-center">
        <img
          src="@/assets/images/영욱이.png"
          class="img-fluid rounded-start profile-img1"
          alt="..."
        />
      </div>
      <div class="col-md-8">
        <div class="card-body d-flex justify-contents-center align-items-center">
          <img
            src="@/assets/images/전화걸기.png"
            class="img-fluid rounded-start profile-img2"
            alt="..."
          />
          <h5 class="card-title">영욱이 할아버지</h5>
        </div>
        <div class="card-text">최근 대화 내역</div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.card {
  width: 70%;
  border: 0px;
}

.card-body {
  grid-column: span 5; /* 3번째 열부터 5개의 열 차지 */
}

.profile-img1 {
  width: 70%; /* 이미지의 크기 조절 */
}

.profile-img2 {
  width: 10%;
}

.card-text {
  padding: 16px;
}
</style>
