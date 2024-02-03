<template>
  <div class="flex mt-10">
    <div class="w-full relative overflow-x-auto shadow-md sm:rounded-lg">
      <div
        class="flex items-center justify-between flex-column flex-wrap md:flex-row space-y-4 md:space-y-0 pb-4 bg-white dark:bg-gray-900"
      >
        <label for="table-search" class="sr-only">Search</label>
        <div class="relative w-full">
          <div class="w-2/3 mb-3 relative max-w-sm">
            <div class="absolute inset-y-0 start-0 flex items-center ps-3.5 pointer-events-none">
              <svg
                class="w-4 h-4 text-gray-500 dark:text-gray-400"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path
                  d="M20 4a2 2 0 0 0-2-2h-2V1a1 1 0 0 0-2 0v1h-3V1a1 1 0 0 0-2 0v1H6V1a1 1 0 0 0-2 0v1H2a2 2 0 0 0-2 2v2h20V4ZM0 18a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V8H0v10Zm5-8h10a1 1 0 0 1 0 2H5a1 1 0 0 1 0-2Z"
                />
              </svg>
            </div>
            <input
              type="date"
              id="datepicker"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Select date"
              v-model="datePicker"
            />
          </div>
        </div>
      </div>
      <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead
          class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
        >
          <tr>
            <th scope="col" class="px-6 py-3">이름</th>
            <th scope="col" class="px-6 py-3">내용</th>
            <th scope="col" class="px-6 py-3">감정 상태</th>
            <th scope="col" class="px-6 py-3">날짜</th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
            v-for="(chat, index) in list"
            :key="index"
          >
            <th
              scope="row"
              class="flex items-center px-6 py-4 text-gray-900 whitespace-nowrap dark:text-white"
            >
              <img
                class="w-5 h-5 rounded-full"
                :src="chat.speaker === '이음이' ? lobotProfile : careProfile"
                alt="Jese image"
              />
              <div class="ps-3">
                <div class="text-base font-semibold">{{ chat.speaker }}</div>
              </div>
            </th>
            <td class="px-6 py-4">{{ chat.message }}</td>
            <td class="px-6 py-4">
              <div class="flex items-center">
                <div
                  class="h-2.5 w-2.5 rounded-full me-2"
                  :class="{
                    'bg-green-500': chat.emotion === 'happy',
                    'bg-yellow-200': chat.emotion === 'common',
                    'bg-red-600': chat.emotion === 'sad'
                  }"
                ></div>
                <img
                  class="w-5 h-5 rounded-full"
                  :src="
                    chat.emotion === 'happy'
                      ? happyEmotion
                      : chat.emotion === 'sad'
                        ? sadEmotion
                        : commonEmotion
                  "
                  alt="Jese image"
                />
              </div>
            </td>
            <td class="px-6 py-4">
              {{ changeDateFormat(chat.date) }}
            </td>
          </tr>
        </tbody>
      </table>
      <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
          <li>
            <button
              @click="prevPage"
              class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
            >
              <span class="sr-only">Previous</span>
              <svg
                class="w-2.5 h-2.5 rtl:rotate-180"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 6 10"
              >
                <path
                  stroke="currentColor"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M5 1 1 5l4 4"
                />
              </svg>
            </button>
          </li>

          <li
            v-for="(pageIndex, index) in Array.from(
              { length: Math.min(10, pages.totalPages - Math.floor(pages.curPage / 10) * 10) },
              (_, index) => index + Math.floor(pages.curPage / 10) * 10 + 1
            )"
            :key="index"
          >
            <button
              v-if="pageIndex !== pages.curPage"
              :value="pageIndex"
              @click="changePage(pageIndex)"
              class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
            >
              {{ pageIndex }}
            </button>
            <button
              v-if="pageIndex === pages.curPage"
              :value="pageIndex"
              @click="changePage(pageIndex)"
              aria-current="page"
              class="z-10 flex items-center justify-center px-3 h-8 leading-tight text-blue-600 border border-blue-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white"
            >
              {{ pageIndex }}
            </button>
          </li>
          <li>
            <button
              @click="nextPage"
              class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
            >
              <span class="sr-only">Next</span>
              <svg
                class="w-2.5 h-2.5 rtl:rotate-180"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 6 10"
              >
                <path
                  stroke="currentColor"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="m1 9 4-4-4-4"
                />
              </svg>
            </button>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { getList } from '../../api/chat.js'

const lobotProfile = '/src/assets/images/ieum.png'
const careProfile = '/src/assets/images/영욱이.png'

const sadEmotion = '/src/assets/images/sad.png'
const happyEmotion = '/src/assets/images/happy.png'
const commonEmotion = '/src/assets/images/common.png'

const datePicker = ref()

const pages = ref({
  totalPages: 10,
  totalElements: 0,
  curPage: 1,
  startDate: '2022-02-02',
  endDate: '2022-02-02'
})

const list = ref([])

onMounted(() => {
  let today = new Date()
  let year = today.getFullYear() // 년도
  let month = today.getMonth() + 1 // 월
  let day = today.getDate() // 날짜
  const curDate = `${year}-0${month}-01`
  datePicker.value = curDate
  pages.value.startDate = curDate
  pages.value.endDate = curDate
  changePage(1)
})

watch(
  () => datePicker.value,
  (nv) => {
    if (nv !== '') {
      pages.value.startDate = datePicker.value
      pages.value.endDate = datePicker.value
      changePage(1)
    } else {
      let today = new Date()
      let year = today.getFullYear() // 년도
      let month = today.getMonth() + 1 // 월
      let day = today.getDate() // 날짜
      const curDate = `${year}-0${month}-01`
      datePicker.value = curDate
    }
  },
  { deep: true }
)

const changePage = (newPage) => {
  pages.value.curPage = newPage

  getList(
    {
      startDate: pages.value.startDate,
      endDate: pages.value.endDate,
      page: pages.value.curPage - 1
    },
    ({ data }) => {
      list.value = data.data.contents
      pages.value.totalPages = data.data.totalPages
      pages.value.totalElements = data.data.totalElements
    }
  )
}

const prevPage = () => {
  if (pages.value.curPage > 1) {
    pages.value.curPage--
    changePage(pages.value.curPage)
  }
}

const nextPage = () => {
  if (pages.value.curPage < pages.value.totalPages) {
    pages.value.curPage++
    changePage(pages.value.curPage)
  }
}

const changeDateFormat = (value) => {
  const dateObject = new Date(value)
  const formattedDate = `${dateObject.getFullYear()}-${padZero(dateObject.getMonth() + 1)}-${padZero(dateObject.getDate())} ${padZero(dateObject.getHours())}:${padZero(dateObject.getMinutes())}`
  return formattedDate
}

// 0을 붙이는 보조 함수
function padZero(value) {
  return String(value).padStart(2, '0')
}
</script>

<style scoped></style>
