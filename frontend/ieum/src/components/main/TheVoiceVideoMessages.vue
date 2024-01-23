<template>
  <div class="container row mt-5">
    <div class="col-9">
      <h3>음성 / 영상 메세지</h3>
      <div class="col message-buttons mx-auto">
        <div class="col-4"></div>
        <div class="col-3">캘린더</div>
        <div class="col-1">
          <img src="@/assets/images/음성녹음.png" class="voice-button" alt="..." />
        </div>
        <div class="col-1">
          <img src="@/assets/images/영상녹화.png" class="video-button" alt="..." />
        </div>
        <div class="col-1">
          <button class="button">전송</button>
        </div>
      </div>
      <div class="col-12">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">발신자</th>
              <th scope="col">날짜</th>
              <th scope="col">내용</th>
              <th scope="col">전송여부</th>
              <th scope="col">수정</th>
              <th scope="col">삭제</th>
            </tr>
          </thead>
          <tbody class="tbody table-hover">
            <tr>
              <td>AI</td>
              <td>2024-01-07 09:00</td>
              <td>할아버지!! 안녕히 주무셨어요?? 좋은 아침이에요</td>
              <td>대기중</td>
              <td><button class="button modify-button">수정</button></td>
              <td><button class="button delete-button">삭제</button></td>
            </tr>
            <tr>
              <td>영욱이</td>
              <td>2024-01-07 09:00</td>
              <td>그래~ 잘잤니?? 오늘 날씨가 참 좋구나</td>
              <td>대기중</td>
              <td><button class="button modify-button">수정</button></td>
              <td><button class="button delete-button">삭제</button></td>
            </tr>
            <tr>
              <td>AI</td>
              <td>2024-01-07 09:00</td>
              <td>그러게요!! 날씨도 좋은데 할아버지랑 산책가고 싶어요!!</td>
              <td>전송완료</td>
              <td><button class="button non-active-button modify-button">수정</button></td>
              <td><button class="button non-active-button delete-button">삭제</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="col-2">
      <a href="/message" class="to-whole-message">>전체 메세지</a>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.container {
  font-family: 'Montserrat';
  justify-content: center;
  width: 80%;
  height: 100%;
  background: white;
  box-shadow: 0px 4px 8px rgba(0, 200, 156, 0.1);
  border-radius: 10px;
  border: 1px #1de4c1 solid;
  padding-top: 2%;
  padding-bottom: 2%;
}

.message-buttons {
  display: flex;
  justify-content: space-between;
}

.button {
  padding-top: 5px;
  padding-bottom: 6px;
  padding-left: 15px;
  padding-right: 15px;
  background: #33a38f;
  border-radius: 15px;
  overflow: hidden;
  border: 1px #1de4c1 solid;
  justify-content: center;
  align-items: center;
  display: inline-flex;
  color: white;
  font-size: 14px;
  font-family: Work Sans;
  font-weight: 550;
  line-height: 24px;
  word-wrap: break-word;
}

.modify-button,
.delete-button {
  padding-left: 10px;
  padding-right: 10px;
}

.non-active-button {
  background: gray;
}

. .voice-button,
.video-button {
  width: 35px;
  height: 35px;
  object-fit: cover;
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

.to-whole-message {
  font-size: 13px;
}
</style>
