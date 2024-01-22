<template>
  <footer class="all">
    <div class="py-3 my-4 mb-0">
      <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="/" class="nav-link px-2 text-muted">Home</a></li>
        <li class="nav-item">
          <a href="#" class="nav-link px-2 text-muted" @click="showPrivacyPolicy">개인정보처리방침</a>
        </li>
      </ul>
      <p class="text-center text-muted">© 2024 Young Uk Company, Inc</p>
    </div>
  </footer>
</template>
  
<script setup>
import swal from 'sweetalert'

const showPrivacyPolicy = () => {
  swal(`이음이(영욱컴퍼니(주))는 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.
  
이 개인정보 처리방침은 2024년 1월 1일부터 적용됩니다.

제1조(개인정보의 처리 목적)
<이음이>(영욱컴퍼니(주))은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.`);
};
</script>
  
<style scoped>
/* Footer가 항상 페이지의 최하단에 고정되도록 스타일 추가 */
footer {
  width: 100%;
}

.all {
  background-color: rgb(235, 251, 247);
}
</style>
  