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
            @click="navigateToTheCallView"
          />
          <h5 class="card-title">영욱이 할아버지</h5>
        </div>
        <div class="recent-status">
          <div class="circle"></div>
          <div class="card-text">최근 대화 내역 : 1일전</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

const navigateToTheCallView = () => {
  router.push({
    path: '/call'
  })
}
</script>

<style scoped>
.card {
  width: 60%;
  border: 0px;
}

.card-title {
  color: #00725e;
  font-size: 36px;
  font-family: Work Sans;
  font-weight: 600;
  word-wrap: break-word;
  padding-left: 7px;
}

.card-text {
  color: #00725e;
  font-size: 20px;
  font-family: Inria Sans;
  font-weight: 400;
  word-wrap: break-word;
  padding-left: 0px;
  padding-top: 8px;
}

.card-body {
  grid-column: span 5; /* 3번째 열부터 5개의 열 차지 */
}

.recent-status {
  display: flex;
}

.circle {
  display: inline-block;
  width: 8px;
  height: 8px;
  background-color: #00cf46;
  border-radius: 50%; /* 원 모양으로 만들기 위해 반지름 50% 설정 */
  margin: 20px; /* 간격 조절 */
  cursor: pointer; /* 커서를 손가락 모양으로 변경 */
}
.profile-img1 {
  width: 70%; /* 이미지의 크기 조절 */
}

.profile-img2 {
  width: 10%;
}
</style>
