<script setup>
import TheFooter from './components/common/TheFooter.vue';
import TheNavbar from './components/common/TheNavbar.vue'

</script>

<template>
  <div>
    <TheNavbar></TheNavbar>

    <router-view></router-view>
    <TheFooter></TheFooter>


  </div>
  <!--app.vue에는 공용 헤더, 푸터 그리고 라우터만 해주는 역할을 해야함!!!!-->
</template>



<style></style>
