<template>
  <footer class="all">
    <div class="py-3 my-4">
      <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="/" class="nav-link px-2 text-muted">Home</a></li>
        <li class="nav-item">
          <a href="#" class="nav-link px-2 text-muted" @click="showPrivacyPolicy"
            >개인정보처리방침</a
          >
        </li>
      </ul>
      <p class="text-center text-muted">© 2022 Young Uk Company, Inc</p>
    </div>
  </footer>
</template>

<script setup>
import swal from 'sweetalert'
const showPrivacyPolicy = () => {
  swal('여기에 내용')
} //이벤트 리스너. 누르면 alert창이 뜨도록 설정하는 매서드.
</script>

<style scoped>
/* Footer가 항상 페이지의 최하단에 고정되도록 스타일 추가 */
footer {
  bottom: 0;
  width: 100%;
}

.all {
  background-color: rgb(235, 251, 247);
}
</style>
