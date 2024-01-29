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
        <tr v-for="(item, index) in items" :key="item.message_no">
          <th scope="row">{{ item.message_sender }}</th>
          <td>{{ item.message_time }}</td>

          <td>
            <img v-if="item.message_type == 'video'" src="../../assets/images/video.png" />
            <img v-if="item.message_type == 'voice'" src="../../assets/images/voice.png" />
          </td>

          <template v-if="check(item.message_time)">
            <td>전송완료</td>
            <td>
              <input type="button" class="btn btn-secondary" value="수정" disabled />
            </td>
            <td>
              <input type="button" class="btn btn-secondary" value="삭제" disabled />
            </td>
          </template>
          <template v-if="!check(item.message_time)">
            <td>전송전</td>
            <td>
              <input
                type="button"
                class="btn btn-primary"
                value="수정"
                :name="index"
                @click="edit()"
              />
            </td>
            <td>
              <input
                type="button"
                class="btn btn-danger"
                value="삭제"
                :name="item.message_no"
                @click="remove()"
              />
            </td>
          </template>
        </tr>
      </tbody>
    </table>
  </div>
  <!-- Button trigger modal -->
  <button
    type="button"
    class="invisible"
    data-bs-toggle="modal"
    data-bs-target="#staticBackdrop"
    id="editModal"
  ></button>

  <!-- Modal -->
  <div
    class="modal fade"
    id="staticBackdrop"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <VVoiceModal v-if="!modalType" :messageState="editSelect"></VVoiceModal>
        <VVideoModal v-if="modalType" :messageState="editSelect"></VVideoModal>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import swal from 'sweetalert'
import { getApi, registApi, modifyApi, removeApi } from '@/api/message.js'
import VVoiceModal from '@/components/message/VVoiceModal.vue'
import VVideoModal from '@/components/message/VVideoModal.vue'

const careId = '1'
const items = ref([])
const modalType = ref(false)
const editSelect = ref({
  message_no: 0,
  message_type: ''
})

const openModal = () => {
  document.getElementById('editModal').click()
}

onMounted(() => {
  getApi(
    careId,
    (response) => {
      items.value = response.data.data
    },
    () => {
      alert('조회 실패')
    }
  )
})

const check = (date) => {
  const today = new Date()
  const targetDate = new Date(date)
  return targetDate.getTime() <= today.getTime()
}

const edit = () => {
  const item = items.value[event.target.name]

  modalType.value = item.message_type === 'video'

  editSelect.value = item

  openModal()
}

const remove = () => {
  swal({
    title: '주의',
    text: '해당 메세지를 삭제하시겠습니까?',
    icon: 'warning',
    buttons: {
      cancel: {
        text: '취소',
        value: false,
        visible: true,
        className: '',
        closeModal: true
      },
      confirm: {
        text: '확인',
        value: event.target.name,
        visible: true,
        className: '',
        closeModal: true
      }
    }
  }).then((value) => {
    if (value !== false) {
      removeSuccess(value)
    }
  })
}

const removeSuccess = (id) => {
  removeApi(
    id,
    // success
    ({ data }) => {
      if (data.success) {
        // 삭제가 성공됐다면 리스트 다시 불러오기
        getApi(
          careId,
          (response) => {
            items.value = response.data.data
          },
          () => {
            alert('조회 실패')
          }
        )
      }
    },
    // fail
    (value) => {
      console.log('fail')
    }
  )
}

//swal 예시
/*
아이콘
success, error, warning, info, question
swal({
타이틀, 내용, 아이콘, 버튼
})
.then((value) => {})
입력 받는 창인 경우 위처럼 value 설정

*/
</script>

<style>
.swal-footer {
  text-align: center;
}
</style>
