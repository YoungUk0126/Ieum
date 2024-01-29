<template>
  <!-- Modal header -->
  <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
    <h3 class="text-xl font-semibold text-gray-900 dark:text-white">Terms of Service</h3>
    <button
      type="button"
      class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
      data-modal-hide="default-modal"
    >
      <svg
        class="w-3 h-3"
        aria-hidden="true"
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 14 14"
      >
        <path
          stroke="currentColor"
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
        />
      </svg>
      <span class="sr-only">Close modal</span>
    </button>
  </div>
  <!-- Modal body -->
  <div class="p-4 md:p-5 space-y-4">
    <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
      With less than a month to go before the European Union enacts new consumer privacy laws for
      its citizens, companies around the world are updating their terms of service agreements to
      comply.
    </p>
    <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
      The European Union’s General Data Protection Regulation (G.D.P.R.) goes into effect on May 25
      and is meant to ensure a common set of data rights in the European Union. It requires
      organizations to notify users as soon as possible of high-risk data breaches that could
      personally affect them.
    </p>
  </div>
  <!-- Modal footer -->
  <div class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600">
    <button
      data-modal-hide="default-modal"
      type="button"
      class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
    >
      I accept
    </button>
    <button
      data-modal-hide="default-modal"
      type="button"
      class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
    >
      Decline
    </button>
  </div>

  <!--

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
--></template>

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

  const json = JSON.stringify(editState.value)
  const formJson = new Blob([json], { type: 'application/json' })

  formData.append('formJson', formJson)
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
