<template>
  <div>
      <div class="row">
        <div class="col-md-8 ">
          Grid 8개 차지
        </div>
        <div class="col-md-4">
          Grid 4개 차지
        </div>
      </div>
      <TheFooter></TheFooter>

  </div>
  <!--app.vue에는 공용 헤더, 푸터 그리고 라우터만 해주는 역할을 해야함!!!!-->
</template>

<script setup>
// eslint-disable-next-line no-unused-vars
import TheFooter from '@/components/common/TheFooter.vue'

</script>

<style>
  
</style>
