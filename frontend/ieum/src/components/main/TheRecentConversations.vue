<template>
  <div class="recent-message-container row mt-5">
    <div class="col-9">
      <h3>최근 대화 목록</h3>
      <div class="alarm-buttons">
        <VModal></VModal>
        <div class="dropdown">
          <button
            class="btn btn-secondary dropdown-toggle alarm-btn"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            알람 종류
          </button>
          <ul class="dropdown-menu bg-success-subtle">
            <li><a class="dropdown-item" href="#">식사</a></li>
            <li><a class="dropdown-item" href="#">투약 정보</a></li>
            <li><a class="dropdown-item" href="#">취침</a></li>
            <li><a class="dropdown-item" href="#">기념일</a></li>
          </ul>
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
    <div class="col-1 to-whole-conv">
      <a href="/chat">>전체 대화</a>
    </div>
  </div>
  <!-- <VModal :modalId="conv"></VModal> -->
</template>

<script setup>
import { ref } from 'vue'
import VModal from '../modal/VModal.vue'
</script>

<style scoped>
.recent-message-container {
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

.alarm-buttons {
  display: flex;
  justify-content: flex-end;
}

.alarm-btn {
  padding-top: 6px;
  padding-bottom: 5.11px;
  padding-left: 20px;
  padding-right: 20px;
  background: #33a38f;
  border-radius: 10px;
  overflow: hidden;
  border: 1px #1de4c1 solid;
  justify-content: center;
  align-items: center;
  display: inline-flex;
  color: white;
  font-size: 16px;
  font-family: Work Sans;
  font-weight: 700;
  line-height: 24px;
  word-wrap: break-word;
  margin-right: 5%;
}

.tbody {
  border: white;
}

.dropdown {
  display: inline-block;
}

.to-whole-conv {
  font-size: 13px;
}
</style>
