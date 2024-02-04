<template>
  <div class="message-container grid grid-cols-12 mx-auto bg-white shadow-md rounded-md m-5 p-6">
    <div class="col-span-11">
      <div class="grid grid-rows-3 items-center">
        <h3 class="text-lg font-semibold mb-4">최근 대화 목록</h3>
        <div class="flex items-center space-x-4 mb-4">
          <VModal></VModal>
          <button
            id="dropdownDefaultButton"
            data-dropdown-toggle="dropdown"
            class="dropdown-main-button btn"
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
          <div id="dropdown" class="hidden dropdown-menu">
            <ul
              class="py-2 text-sm text-gray-700 dark:text-gray-200"
              aria-labelledby="dropdownDefaultButton"
            >
              <li>
                <a href="#" class="dropdown-item">기념일</a>
              </li>
              <li>
                <a href="#" class="dropdown-item">투약</a>
              </li>
              <li>
                <a href="#" class="dropdown-item">식사 시간</a>
              </li>
              <li>
                <a href="#" class="dropdown-item">기상/취침</a>
              </li>
            </ul>
          </div>
        </div>
        <div>
          <table class="table">
            <!-- ... -->
          </table>
        </div>
      </div>
    </div>
    <div class="col-span-1">
      <a href="/chat" class="to-whole-conv">>전체 대화</a>
    </div>
  </div>
</template>

<style scoped>
/* 커스텀 스타일링 추가 */
.btn {
  /* tailwindcss 클래스 추가 */
  @apply px-5 py-2.5 rounded-lg text-sm inline-flex items-center focus:outline-none focus:ring-2 focus:ring-green-300 dark:focus:ring-green-200;
}

.dropdown-menu {
  /* tailwindcss 클래스 추가 */
  @apply z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700;
}

.dropdown-item {
  /* tailwindcss 클래스 추가 */
  @apply block px-3 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white;
}
</style>
