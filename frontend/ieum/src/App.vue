<script setup>
import TheHeader from './components/common/TheHeader.vue'
</script>

<template>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <TheHeader></TheHeader>
  <div class="body">
    <div class="h-24"></div>
    <router-view></router-view>
  </div>
</template>
<style scoped>
/* 미디어 쿼리를 사용하여 모바일 장치에 대한 스타일 지정 */
@media only screen and (max-width: 768px) {
  /* 모바일 화면에 적용되는 스타일 */
  .w-full {
    min-width: 320px;
    width: 100%;
  }
  .flex-wrap {
    flex-wrap: wrap;
  }
  .hidden {
    display: none;
  }
  /* 필요한 다른 스타일들을 여기에 추가할 수 있습니다 */
}
</style>
