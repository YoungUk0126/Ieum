<template>
  <div>
    <table class="table table-hover table-borderless text-center fw-bold align-middle">
      <thead class="border-bottom">
        <tr>
          <th scope="col mb-3">발신자</th>
          <th scope="col">날짜</th>
          <th scope="col">내용</th>
          <th scope="col">전송여부</th>
          <th scope="col">수정</th>
          <th scope="col">삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in items" :key="item.message_id">
          <th scope="row">{{ item.message_sender }}</th>
          <td>{{ item.message_time }}</td>
          <td>{{ item.message_type }}</td>
          <template v-if="check(item.message_time)">
            <td>전송완료</td>
            <td>
              <input
                type="button"
                class="btn btn-secondary"
                value="수정"
                :id="message_id"
                disabled
              />
            </td>
            <td>
              <input
                type="button"
                class="btn btn-secondary"
                value="삭제"
                :id="message_id"
                disabled
              />
            </td>
          </template>
          <template v-if="!check(item.message_time)">
            <td>전송전</td>
            <td>
              <input
                type="button"
                class="btn btn-primary"
                value="수정"
                :id="message_id"
                @click="edit"
              />
            </td>
            <td>
              <input
                type="button"
                class="btn btn-danger"
                value="삭제"
                :id="message_id"
                @click="remove"
              />
            </td>
          </template>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref } from 'vue'
const items = ref([
  {
    message_id: '1',
    message_name: 'update.mp3',
    message_type: 'video',
    message_time: '2024-01-15',
    message_sender: '딸'
  },
  {
    message_id: '2',
    message_name: 'update.mp3',
    message_type: 'video',
    message_time: '2024-01-15',
    message_sender: '딸'
  },
  {
    message_id: '3',
    message_name: 'update.mp3',
    message_type: 'video',
    message_time: '2024-05-15',
    message_sender: '딸'
  }
])

const check = (date) => {
  const today = new Date()
  const targetDate = new Date(date)
  return targetDate.getTime() <= today.getTime()
}

const edit = () => {}

const remove = () => {
  alert('삭제?')
}
</script>

<style scoped></style>
