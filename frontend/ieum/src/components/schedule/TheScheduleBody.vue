<template>
  <div class="mt-5 mb-5">
    <div class="datepicker border">
      <Calendar
        v-model="date"
        :attributes="attributes"
        @didMove="loadSchedule"
        @dayclick="click"
        expanded
      >
        <template #footer> </template>
      </Calendar>
    </div>
    <div class="container border mt-5 p-5 rounded border-success-subtle">
      <h1>일정 상세</h1>
      <div v-if="schedule.length > 0">
        <div v-for="item in schedule" :key="item">
          <div class="mt-3 d-flex">
            <div class="content">{{ item }}</div>
            <div class="col-2 text-center">
              <a href="#" class="btn btn-custom-primary btn-lg w-50 fw-bold">수정</a>
            </div>
            <div class="col-2 text-center">
              <a href="#" class="btn btn-custom-danger btn-lg w-50 fw-bold">삭제</a>
            </div>
          </div>
        </div>
      </div>
      <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead
          class="text-xs text-gray-700 uppercase bg-gray-100 dark:bg-gray-700 dark:text-gray-400"
        >
          <tr>
            <th scope="col" class="px-6 py-3 rounded-s-lg">일정 내용</th>
            <th scope="col" class="px-6 py-3 text-center">수정</th>
            <th scope="col" class="px-6 py-3 text-center rounded-e-lg">삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr class="bg-white dark:bg-gray-800">
            <th
              scope="row"
              class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
            >
              <input
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                value="Apple MacBook Pro 17"
              />
            </th>
            <td class="px-6 py-4 text-center">
              <button
                type="button"
                class="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                @click="edit"
              >
                수정
              </button>
            </td>
            <td class="px-6 py-4 text-center">
              <button
                type="button"
                class="text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
              >
                삭제
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { Calendar } from 'v-calendar'
import { getList } from '../../api/schedule.js'
import 'v-calendar/style.css'

const date = ref(new Date())
const schedule = ref([])

onMounted(() => {
  date.value.setHours(9, 0, 0, 0)
  getApi(date.value.getFullYear(), date.value.getMonth() + 1)
})

const todos = ref([])

const attributes = computed(() => [
  ...todos.value.map((todo) => ({
    dates: todo.dates,
    dot: {
      color: todo.color,
      ...(todo.isComplete && { class: 'opacity-75' })
    },
    popover: {
      label: todo.description
    }
  }))
])

const click = (clickedDate) => {
  date.value = new Date(clickedDate.id) // 클릭한 날짜로 업데이트
}

watch(
  () => date.value,
  (nv) => {
    schedule.value = []
    for (let i = 0; i < todos.value.length; i++) {
      if (nv.getTime() === todos.value[i].dates.getTime()) {
        schedule.value.push(todos.value[i])
      }
    }
  }
)

const loadSchedule = (data) => {
  const curYear = data[0].year
  const curMonth = data[0].month
  getApi(curYear, curMonth)
}

const getApi = (year, month) => {
  getList(year, month, ({ data }) => {
    todos.value = []
    data.data.forEach((element) => {
      todos.value.push({
        description: element.eventName,
        isComplete: false,
        dates: new Date(element.eventDate),
        color: 'orange',
        eventNo: element.eventNo
      })
    })
  })
}
</script>

<style scoped>
.content {
  font-size: 30px;
}

.btn-custom-primary {
  background-color: #27a791;
  color: #fff;
  transition:
    background-color 0.3s ease,
    color 0.3s ease;
}

.btn-custom-danger {
  background-color: #a4a4a4;
  color: #fff;
  transition:
    background-color 0.3s ease,
    color 0.3s ease;
}
</style>
