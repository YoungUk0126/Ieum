<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card mb-3 border-0" style="max-width: 50%">
          <div class="row g-0">
            <div class="col-md-4">
              <img src="@/assets/images/영욱이.png" class="img-fluid rounded-start" alt="..." />
            </div>
            <div class="col-md-2 d-flex justify-content-center align-items-center">
              <img
                src="@/assets/images//전화걸기.png"
                class="img-fluid rounded-start"
                alt="..."
                style="width: 70%"
              />
            </div>
            <div class="col-md-6 d-flex justify-content-center align-items-center">
              <div class="card-body">
                <h5 class="card-title">영욱이</h5>
                <p class="card-text">최근 대화 이력: 1일 전</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
/* 추가된 스타일 부분 */
@media (min-width: 768px) {
  .card-body {
    grid-column: span 5; /* 3번째 열부터 5개의 열 차지 */
  }
}
</style>
