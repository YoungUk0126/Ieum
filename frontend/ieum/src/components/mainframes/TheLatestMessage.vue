<template>
  <div class="container gap-0 row">
    <div class="box1 col-10 row-gap-3">
      <h3>최근 대화 목록</h3>
      <button>알람 등록</button>
      <div class="dropdown">
        <a
          class="nav-link dropdown-toggle"
          href="#"
          role="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          알람 종류
        </a>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#">끼니 알람</a></li>
          <li><a class="dropdown-item" href="#">투약 정보 알람</a></li>
          <li><a class="dropdown-item" href="#">취침 정보 알람</a></li>
          <li><a class="dropdown-item" href="#">기념일 정보 알람</a></li>
        </ul>
      </div>
    </div>
    <div class="col-2">모든 대화</div>
    <div class="box2 col-10 row-gap-3">
      <div class="col-1">주체</div>
      <div class="col-3">날짜</div>
      <div class="col-7">내용</div>
      <div class="col-1">감정</div>

      <div class="col-1">AI</div>
      <div class="col-3">2024-01-07 09:00</div>
      <div class="col-7">할아버지!! 안녕히 주무셨어요?? 좋은 아침이에요</div>
      <div class="col-1">😄</div>

      <div class="col-1">영욱이</div>
      <div class="col-3">2024-01-07 09:00</div>
      <div class="col-7">그래~ 잘잤니?? 오늘 날씨가 참 좋구나</div>
      <div class="col-1">😢</div>

      <div class="col-1">AI</div>
      <div class="col-3">2024-01-07 09:00</div>
      <div class="col-7">그러게요!! 날씨도 좋은데 할아버지랑 산책가고 싶어요!!</div>
      <div class="col-1">🙂</div>
    </div>
  </div>
</template>

<script>
// Your script logic goes here
</script>

<style scoped>
.container {
  margin: 2%;
}

.box1 {
  display: flex;
  justify-content: space-around;
}

.box2 {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  margin: 0 auto;
  /* justify-content: ; */
}

.box2 > div {
  padding: 10px;
}
</style>
