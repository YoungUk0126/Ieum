<template>
  <div class="container row mt-5">
    <div class="col-8">
      <h3>최근 음성 / 영상 메세지</h3>
      <div class="alarm-buttons">
        <button type="button" class="btn alarm-btn">알람 등록</button>
        <div class="dropdown">
          <a
            class="nav-link dropdown-toggle col-2"
            href="#"
            role="button"
            data-toggle="dropdown"
            aria-expanded="false"
          >
            알람 종류
          </a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#">끼니 알람</a>
            <a class="dropdown-item" href="#">투약 정보 알람</a>
            <a class="dropdown-item" href="#">취침 정보 알람</a>
            <a class="dropdown-item" href="#">기념일 정보 알람</a>
          </div>
        </div>
      </div>
      <div class="col-12">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">주체</th>
              <th scope="col">날짜</th>
              <th scope="col">내용</th>
              <th scope="col">감정</th>
            </tr>
          </thead>
          <tbody class="tbody table-hover">
            <tr>
              <td>AI</td>
              <td>2024-01-07 09:00</td>
              <td>할아버지!! 안녕히 주무셨어요?? 좋은 아침이에요</td>
              <td>😄</td>
            </tr>
            <tr>
              <td>영욱이</td>
              <td>2024-01-07 09:00</td>
              <td>그래~ 잘잤니?? 오늘 날씨가 참 좋구나</td>
              <td>😢</td>
            </tr>
            <tr>
              <td>AI</td>
              <td>2024-01-07 09:00</td>
              <td>그러게요!! 날씨도 좋은데 할아버지랑 산책가고 싶어요!!</td>
              <td>🙂</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="col-1">
      <a href="">>전체 대화</a>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.container {
  font-family: 'Montserrat';
  justify-content: center;
}

.alarm-buttons {
  display: flex;
  justify-content: end;
}

.alarm-btn {
  margin-right: 5%;
}

.tbody {
  border: white;
}

.alarm-btn {
  background-color: #33a38f;
  display: inline-block;
}

.dropdown {
  display: inline-block;
}
</style>
