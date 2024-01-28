<template>
  <div class="modal-header">
    <h1 class="modal-title fs-5" id="staticBackdropLabel">음성 메세지 수정</h1>
    <button
      type="button"
      id="closeModal"
      class="btn-close"
      data-bs-dismiss="modal"
      aria-label="Close"
    ></button>
  </div>
  <div class="modal-body">
    <div class="row align-items-center">
      <div class="col-4 text-center align-middle mb-3">
        <p class="fw-bold mb-0">발신자</p>
      </div>
      <div class="col-8 mb-3">
        <input type="text" class="input-group-text w-75" v-model="editState.message_sender" />
      </div>
      <div class="col-4 text-center align-middle mb-3">
        <p class="fw-bold mb-0">날짜</p>
      </div>
      <div class="col-8 mb-3">
        <input type="date" class="input-group-text w-75" v-model="editState.message_time" />
      </div>
      <div class="col-12 border-top border-4 my-3"></div>
      <div class="col-12 text-start align-middle mb-2">
        <p class="fw-bold mb-0">이전 메세지</p>
      </div>
      <div class="col-8">
        <audio id="prevVoice" controls></audio>
      </div>
      <div class="col-12 border-top border-4 my-3"></div>
      <div class="col-12 text-start align-middle">
        <p class="fw-bold mb-0">새로 녹음</p>
      </div>
      <div class="col-8 mt-2">
        <audio id="newVoice" controls></audio>
      </div>
      <div class="col-4 text-center">
        <button class="btn btn-danger" @click="record">녹음</button>
      </div>
    </div>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
    <button type="button" class="btn btn-primary" @click="editSubmit">수정하기</button>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { modifyApi } from '@/api/message'

const props = defineProps(['messageState'])
const emit = defineEmits(['closeModal'])

const editState = ref({
  message_no: '',
  care_no: '',
  message_sender: '',
  message_name: '',
  message_type: ''
})

const closeModal = () => {
  document.querySelector('#closeModal').click()
}

onMounted(() => {})

// 이후 messageState가 변경됨에 따라 이전 음성을 바꿔줘야 한다.
watch(
  () => props.messageState,
  (nv, ov) => {
    // 깊은 복사
    editState.value = JSON.parse(JSON.stringify(nv))
  }
)

// 녹음중 상태 변수
const isRecording = ref(false)

// MediaRecorder 변수 생성
const mediaRecorder = ref()

// 녹음 데이터 저장 배열
const audioArray = ref([])

// 녹음 데이터 Blob 형식
const file = ref()

const record = async () => {
  // 엘리먼트 취득
  const _audioEl = document.querySelector('#newVoice')

  if (!isRecording.value) {
    // 마이크 mediaStream 생성: Promise를 반환하므로 async/await 사용
    const mediaStream = await navigator.mediaDevices.getUserMedia({ audio: true })

    // MediaRecorder 생성
    mediaRecorder.value = new MediaRecorder(mediaStream)

    // 이벤트핸들러: 녹음 데이터 취득 처리
    mediaRecorder.value.ondataavailable = (event) => {
      audioArray.value.push(event.data) // 오디오 데이터가 취득될 때마다 배열에 담아둔다.
    }

    // 이벤트핸들러: 녹음 종료 처리 & 재생하기
    mediaRecorder.value.onstop = () => {
      // 녹음이 종료되면, 배열에 담긴 오디오 데이터(Blob)들을 합친다: 코덱도 설정해준다.
      const blob = new Blob(audioArray.value, { type: 'audio/ogg codecs=opus' })
      audioArray.value.splice(0) // 기존 오디오 데이터들은 모두 비워 초기화한다.

      // Blob 데이터에 접근할 수 있는 주소를 생성한다.
      const blobURL = window.URL.createObjectURL(blob)

      // audio엘리먼트로 재생한다.
      _audioEl.src = blobURL
      _audioEl.play()
      file.value = blob
      /*
      const clipName = 'voiceRecord'

      // 다운 로직
      const a = document.createElement('a')
      a.href = _audioEl.src

      a.download = clipName
      a.click()
      */
    }

    // 녹음 시작
    mediaRecorder.value.start()
    isRecording.value = true
  } else {
    // 녹음 종료
    mediaRecorder.value.stop()
    isRecording.value = false
  }
}

const editSubmit = () => {
  const formData = new FormData()
  formData.append('messageNo', Number(editState.value.message_no))
  formData.append('careNo', Number(editState.value.care_no))
  formData.append('messageSender', editState.value.message_sender)
  formData.append('messageName', editState.value.message_name)
  formData.append('messageType', editState.value.message_type)
  formData.append('messageTime', editState.value.message_time)
  formData.append('file', file.value, 'editFile.ogg')

  modifyApi(
    formData,
    ({ data }) => {
      if (data.success) {
        closeModal()
      }
    },
    () => {
      console.log('fail')
    }
  )
}
</script>

<style scoped></style>
