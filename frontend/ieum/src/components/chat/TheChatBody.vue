<template>
  <div class="mt-8 text-center">
    <h1 class="text-4xl font-extrabold text-gray-500 dark:text-white">이음이 대화 내역</h1>
  </div>
  <div
    class="flex items-center justify-between mt-5 flex-column flex-wrap md:flex-row space-y-4 md:space-y-0 pb-4 pl-3 bg-white dark:bg-gray-900"
  >
    <label for="table-search" class="sr-only">Search</label>
    <div class="relative w-full">
      <VDatePicker v-model="datePicker">
        <template #default="{ inputValue, inputEvents }">
          <div class="w-1/2 mb-3 relative max-w-sm mx-auto">
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
              type="text"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              :value="inputValue"
              v-on="inputEvents"
            />
          </div>
        </template>
      </VDatePicker>
    </div>
  </div>
  <div
    class="flex gap-5 p-5 flex-col pb-10 w-full chat-body relative overflow-x-auto sm:rounded-lg"
  >
    <div class="text-center">
      <span class="bg-gray-200 rounded-xl px-6 py-1 font-bold">
        {{ pages.startDate }}
      </span>
    </div>

    <template v-if="list.length == 0">
      <div class="flex items-center gap-5">
        <div class="w-full flex items-end gap-2.5 justify-center">
          <div
            class="flex flex-col w-full leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-e-xl rounded-es-xl dark:bg-gray-700"
          >
            <p class="text-sm font-normal py-2.5 text-gray-900 dark:text-white text-center">
              대화 내용이 존재하지 않아요
            </p>
          </div>
        </div>
      </div>
    </template>
    <template v-if="list.length > 0">
      <template v-for="(chat, index) in list" :key="index">
        <div class="flex items-start gap-5 px-5" v-if="chat.speaker === 'AI'">
          <div class="w-full flex items-end gap-2.5 justify-center">
            <img class="w-11 h-11 rounded-full" src="@/assets/images/ieum.png" alt="Jese image" />
            <div
              class="flex flex-col w-full leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-e-xl rounded-es-xl dark:bg-gray-700"
            >
              <div class="flex items-center space-x-2 rtl:space-x-reverse">
                <span class="text-sm font-semibold text-gray-900 dark:text-white">이음이</span>
                <span class="text-sm font-normal text-gray-500 dark:text-gray-400">{{
                  changeDateFormat(chat.date)
                }}</span>
              </div>
              <p class="text-sm font-normal py-2.5 text-gray-900 dark:text-white">
                {{ chat.message }}
              </p>
            </div>
          </div>
        </div>
        <div class="flex items-start gap-5 px-5" v-if="chat.speaker !== 'AI'">
          <div class="w-full flex items-end gap-2.5 justify-center">
            <div
              class="flex flex-col w-full leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-e-xl rounded-es-xl dark:bg-gray-700"
            >
              <div class="flex items-center space-x-2 rtl:space-x-reverse">
                <span class="text-sm font-semibold text-gray-900 dark:text-white">{{
                  `${care.careName} ${care.careGender}`
                }}</span>
                <span class="text-sm font-normal text-gray-500 dark:text-gray-400">{{
                  changeDateFormat(chat.date)
                }}</span>
              </div>
              <p class="text-sm font-normal py-2.5 text-gray-900 dark:text-white">
                {{ chat.message }}
              </p>
              <div class="items-end">
                <img
                  v-if="
                    chat.emotion !== undefined &&
                    (chat.emotion.toLowerCase() === 'happy' ||
                      chat.emotion.toLowerCase() === 'sad' ||
                      chat.emotion.toLowerCase() === 'common')
                  "
                  class="w-8 ms-auto"
                  :src="'/assets/images/' + chat.emotion + '.png'"
                />
              </div>
            </div>
            <img
              class="w-11 h-11 rounded-full"
              :src="'/assets/images/' + care.careImage"
              alt="Jese image"
            />
          </div>
        </div>
      </template>
    </template>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { getList } from '../../api/chat.js'
import { getCareInfo } from '@/api/careInfoModify'

const datePicker = ref()

const pages = ref({
  totalPages: 10,
  totalElements: 0,
  curPage: 1,
  startDate: '2022-02-02',
  endDate: '2022-02-02'
})

const care = ref({})

const list = ref([])

onMounted(() => {
  let today = new Date()
  const curDate = changeDateFormat(today)
  datePicker.value = curDate
  getCareInfo(({ data }) => {
    care.value = data.data
    care.value.careImage = care.value.careGender === 'FEMALE' ? 'old-woman.png' : 'old-man.png'
    care.value.careGender = care.value.careGender === 'FEMALE' ? '할머니' : '할아버지'
  })
})

watch(
  () => datePicker.value,
  (nv) => {
    if (nv !== '') {
      pages.value.startDate = changeDateFormat(nv)
      pages.value.endDate = changeDateFormat(nv)
      changePage(1)
    } else {
      let today = new Date()

      datePicker.value = changeDateFormat(today)
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
/* 무한스크롤 위한 페이지네이션
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
*/
const changeDateFormat = (value) => {
  const dateObject = new Date(value)
  const formattedDate = `${dateObject.getFullYear()}-${padZero(dateObject.getMonth() + 1)}-${padZero(dateObject.getDate())}`
  return formattedDate
}

// 0을 붙이는 보조 함수
function padZero(value) {
  return String(value).padStart(2, '0')
}
</script>

<style scoped>
@media screen and (max-width: 768px) {
  .chat-body {
    max-height: 60vh !important;
  }
}

.chat-body {
  max-height: 55vh;
}

/* 스크롤바의 폭 너비 */
::-webkit-scrollbar {
  width: 10px;
}

::-webkit-scrollbar-thumb {
  background: #d9d9d9 /* 스크롤바 색상 */;
  border-radius: 10px; /* 스크롤바 둥근 테두리 */
}

::-webkit-scrollbar-track {
  background: rgba(220, 20, 60, 0.1); /*스크롤바 뒷 배경 색상*/
}
</style>
