<template>
  <div class="message-container mx-auto bg-white shadow-md rounded-md m-5">
    <div class="grid grid-rows-3">
      <h3>최근 대화 목록</h3>
      <div class="alarm-buttons">
        <!-- <VModal></VModal> -->

        <button
          id="dropdownDefaultButton"
          data-dropdown-toggle="dropdown"
          class="dropdown-main-button text-white bg-green-300 hover:bg-green-400 focus:ring-2 focus:outline-none focus:bg-green-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-green-600 dark:hover:bg-green-300 dark:focus:ring-green-200"
          type="button"
        >
          알람 종류
          <svg
            class="w-2.5 h-2.5 ms-3"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 10 6"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="m1 1 4 4 4-4"
            />
          </svg>
        </button>

        <!-- Dropdown menu -->
        <div
          id="dropdown"
          class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700"
        >
          <ul
            class="py-2 text-sm text-gray-700 dark:text-gray-200"
            aria-labelledby="dropdownDefaultButton"
          >
            <li>
              <a
                href="#"
                class="block px-3 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                >기념일</a
              >
            </li>
            <li>
              <a
                href="#"
                class="block px-3 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                >투약</a
              >
            </li>
            <li>
              <a
                href="#"
                class="block px-3 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                >식사 시간</a
              >
            </li>
            <li>
              <a
                href="#"
                class="block px-3 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                >기상/취침</a
              >
            </li>
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
    <div class="grid grid-rows-2">
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
.message-container {
  /* justify-content: center; */
  width: 80%;
  height: 100%;
  padding: 6%;
}

.dropdown-main-button {
  --tw-ring-color: #2dffc7;
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
