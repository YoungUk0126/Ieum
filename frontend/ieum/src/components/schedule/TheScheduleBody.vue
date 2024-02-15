<template>
  <div class="mt-5 mb-5">

    <!-- Modal toggle -->
    <button data-modal-target="static-modal" data-modal-toggle="static-modal"
      class="Iblock text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 mb-5"
      type="button">
      일정 추가
    </button>

    <!-- Main modal -->
    <div id="static-modal" data-modal-backdrop="static" tabindex="-1" aria-hidden="true"
      class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
      <div class="relative p-4 w-full max-w-2xl max-h-full">
        <!-- Modal content -->
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
          <!-- Modal header -->
          <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
            <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
              일정 추가
            </h3>
            <button type="button"
              class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
              data-modal-hide="static-modal">
              <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
              </svg>
              <span class="sr-only">Close modal</span>
            </button>
          </div>
          <!-- Modal body -->
          <div class="p-4 md:p-5 space-y-4">
            <label for="scheduleDate" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6">일정
              날짜</label>
            <input type="date"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              v-model="eventInfo.eventDate">
          </div>
          <div class="mr-5 ml-5 mb-7">
            <label for="name" class="block text-sm font-medium text-gray-900 dark:text-white mt-4 mb-4">일정 이름</label>
            <input type="text"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              v-model="eventInfo.eventName">

          </div>
          <!-- Modal footer -->
          <div class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600">
            <button data-modal-hide="static-modal" type="button"
              class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
              @click="addEventFunction">추가</button>
            <button data-modal-hide="static-modal" type="button"
              class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">취소</button>
          </div>
        </div>
      </div>
    </div>

    <div class="datepicker border">
      <Calendar v-model="date" :attributes="attributes" @didMove="loadSchedule" @dayclick="click" expanded>
        <template #footer> </template>
      </Calendar>
    </div>
    <div class="container border mt-5 p-5 rounded border-success-subtle">
      <h1>일정 상세</h1>
      <div v-if="schedule.length > 0">
        <template v-for="(item, index) in schedule" :key="item.eventNo">
          <div>
            <div class="mt-3 d-flex">
              <div class="content">{{ item }}</div>
              <div class="content">{{ index }}</div>
              <div class="col-2 text-center">
                <!-- Modal toggle -->
                <button data-modal-target="editModal" data-modal-toggle="editModal"
                  class="Jblock text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                  type="button" @click="editSendEventInformation(index)">
                  수정
                </button>
              </div>
              <div class="col-2 text-center">
                <a href="#" class="btn btn-custom-danger btn-lg w-50 fw-bold"
                  @click="deleteEventFunction(item.eventNo)">일정
                  삭제</a>
              </div>
            </div>
          </div>
        </template>
      </div>
      <div class="justify-center items-center">
        <div id="editModal" data-modal-backdrop="static" tabindex="-1" aria-hidden="true"
          class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full ">
          <div class="relative p-4 w-full max-w-2xl max-h-full">
            <!-- Modal content -->
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
              <!-- Modal header -->
              <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
                  일정 수정
                </h3>
                <button type="button"
                  class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                  data-modal-hide="static-modal" @click="hideModal">
                  <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                    viewBox="0 0 14 14">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
                  </svg>
                  <span class="sr-only">Close modal</span>
                </button>
              </div>
              <!-- Modal body -->
              <div class="p-4 md:p-5 space-y-4">
                <div class="p-4 md:p-5 space-y-4">
                  <label for="scheduleDate" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white mt-6">일정
                    날짜</label>
                  <input type="date"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    v-model="eventInfo.eventDate">
                </div>
                <div class="mr-5 ml-5 mb-7">
                  <label for="name" class="block text-sm font-medium text-gray-900 dark:text-white mt-4 mb-4">일정
                    이름</label>
                  <input type="text"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    v-model="eventInfo.eventName">

                </div>
              </div>
              <!-- Modal footer -->
              <div class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600">
                <button data-modal-hide="editModal" type="button"
                  class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                  @click="modifyEventFunction">수정</button>
                <button data-modal-hide="editModal" type="button"
                  class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
                  @click="hideModal">취소</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { Calendar } from 'v-calendar'
import { getList, addSchedule, deleteSchedule, modifySchedule } from '../../api/schedule.js'
import swal from 'sweetalert';
import 'v-calendar/style.css'

const date = ref(new Date())

const schedule = ref([])


const eventInfo = ref({
  "eventName": "",
  "eventDate": ""
})

const decoyEventNo = ref('')

const decoyschedule = ref([])

const addEventFunction = () => {
  addSchedule(
    eventInfo.value,
    (response) => {
      if (response.data.success) {
        swal('일정이 추가되었습니다.')
        eventInfo.value.eventName = ""
        eventInfo.value.eventDate = ''
        getApi(date.value.getFullYear(), date.value.getMonth() + 1)
      }
      else {
        swal('일정 형식을 다시 한 번 확인해주세요.')
      }
    }

  )
}

function showModal() {
  const modal = document.getElementById('editModal');

  if (modal && modal.classList.contains('hidden')) {
    modal.classList.remove('hidden');
  }
}

function hideModal() {
  const modal = document.getElementById('editModal');

  if (modal && !modal.classList.contains('hidden')) {
    modal.classList.add('hidden');
  }
}


const editSendEventInformation = (num) => {
  decoyschedule.value = schedule.value[num]
  console.log(decoyschedule.value)
  decoyEventNo.value = decoyschedule.value.eventNo;
  console.log(decoyEventNo.value)
  showModal()
}

const modifyEventFunction = () => {
  modifySchedule(
    {
      "eventNo": decoyEventNo.value,
      "eventName": eventInfo.value.eventName,
      "eventDate": eventInfo.value.eventDate
    },
    (response) => {
      console.log(response.data.success)
      if (response.data.success) {
        swal('일정이 변경되었습니다.')
        getApi(date.value.getFullYear(), date.value.getMonth() + 1)
        eventInfo.value.eventName = ''
        eventInfo.value.eventDate = ''
        hideModal()
      }
      else {
        swal('일정형식을 다시 확인해주세요.')
      }

    }
  )
}

const deleteEventFunction = (item) => {
  deleteSchedule(
    item,
    ({ data }) => {
      if (data.success) {
        // location.reload();
        swal('일정이 삭제되었습니다.')
        getApi(date.value.getFullYear(), date.value.getMonth() + 1)

      }
      else {
        swal('일정 삭제 중 오류가 발견되었습니다.')
      }
    }
  )
}

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
  },
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
        // isComplete: false,
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
