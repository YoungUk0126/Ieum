<template>
  <footer class="bg-[#EBFBF7] rounded-lg shadow dark:bg-gray-900 m-4">
    <div class="w-full max-w-screen-xl mx-auto p-4 md:py-8">
      <div class="sm:flex sm:items-center sm:justify-between">
        <a href="https://flowbite.com/" class="flex items-center mb-4 sm:mb-0 space-x-3 rtl:space-x-reverse">
          <img src="../../assets/images/logo.png" class="h-16" alt="Flowbite Logo" />
          <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">A303</span>
        </a>
        <ul class="flex flex-wrap items-center mb-6 text-sm font-medium text-gray-500 sm:mb-0 dark:text-gray-400">
          <li>
            <a href="#" class="hover:text-black me-4 md:me-6 text-xl">About</a>
          </li>
          <li>
            <a href="#" class="hover:text-black me-4 md:me-6 text-xl" @click="showPrivacyPolicy">Privacy Policy</a>
          </li>
          <li>
            <a href="#" class="hover:text-black me-4 md:me-6 text-xl">Licensing</a>
          </li>
          <li>
            <a href="#" class="hover:text-black me-16 text-xl">Contact</a>
          </li>
        </ul>
      </div>
      <hr class="my-6 border-gray-200 sm:mx-auto dark:border-gray-700 lg:my-8" />
      <span class="block text-sm text-gray-500 sm:text-center dark:text-gray-400">© 2023 <a href="https://flowbite.com/"
          class="hover:text-black">A303</a>. All Rights Reserved.</span>
    </div>
  </footer>
</template>

<script setup>
import swal from 'sweetalert'
const showPrivacyPolicy = () => {
  swal(`이음이(영욱컴퍼니(주))는 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.
  
이 개인정보 처리방침은 2024년 1월 1일부터 적용됩니다.

제1조(개인정보의 처리 목적)
<이음이>(영욱컴퍼니(주))은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.`)
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
