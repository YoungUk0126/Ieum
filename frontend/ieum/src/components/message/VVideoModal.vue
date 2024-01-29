<template>
  <!-- Modal header -->
  <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
    <h3 class="text-xl font-medium text-gray-900 dark:text-white">영상 메세지 수정</h3>
    <button
      type="button"
      class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
      @click="closeModal"
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
    <video ref="video" autoplay />
    <div class="flex flex-wrap items-center">
      <div class="w-1/3 text-center md:text-right mb-3">
        <p class="font-bold mb-0">발신자</p>
      </div>
      <div class="w-2/3 mb-3">
        <input
          type="text"
          id="first_name"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="ex) 첫째딸"
          v-model="editState.message_sender"
        />
      </div>
      <div class="w-1/3 text-center md:text-right mb-3">
        <p class="font-bold mb-0">날짜</p>
      </div>
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
          datepicker
          datepicker-autohide
          type="text"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="Select date"
          v-model="editState.message_time"
        />
      </div>
      <div class="w-full border-t border-4 my-3"></div>
      <div class="w-full text-left md:text-right items-center mb-2">
        <p class="font-bold mb-0">이전 메세지</p>
      </div>
      <div class="w-2/3 md:w-2/3">
        <audio id="prevVoice" controls class="w-full"></audio>
      </div>
      <div class="w-full border-t border-4 my-3"></div>
      <div class="w-full text-left items-center">
        <p class="font-bold mb-0">새로 녹음</p>
      </div>
      <div class="w-2/3 mt-2">
        <audio id="newVoice" controls class="w-full"></audio>
      </div>
      <div class="w-1/3 text-center">
        <button
          type="button"
          class="text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
          @click="record"
        >
          녹음
        </button>
      </div>
    </div>
  </div>
  <!-- Modal footer -->
  <div
    class="flex flex-row-reverse items-center gap-x-2 p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
  >
    <button
      type="button"
      class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
      @click="closeModal"
    >
      수정
    </button>
    <button
      data-modal-hide="edit-modal"
      type="button"
      class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-200 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
      @click="closeModal"
    >
      닫기
    </button>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { modifyApi } from '@/api/message'

const props = defineProps(['messageState', 'modal'])
const modal = ref(props.modal)

const editState = ref({
  message_no: '',
  care_no: '',
  message_sender: '',
  message_name: '',
  message_type: ''
})

const closeModal = () => {
  modal.value.hide()
}

// 이후 messageState가 변경됨에 따라 이전 음성을 바꿔줘야 한다.
watch(
  () => props.messageState,
  (nv, ov) => {
    // 깊은 복사
    editState.value = JSON.parse(JSON.stringify(nv))
  }
)

watch(
  () => props.modal,
  () => {
    modal.value = props.modal
  }
)

const video = ref(null)
const constraints = {
  audio: false,
  video: true
}

const handleStream = (stream) => {
  const videoTracks = stream.getVideoTracks()
  console.log('Got stream with constraints:', constraints)
  console.log(`Using video device: ${videoTracks[0].label}`)
  stream.onremovetrack = () => {
    console.log('Stream ended')
  }
  video.value.srcObject = stream
  console.log(stream)
}

const handleError = (error) => {
  if (error.name === 'OverconstrainedError') {
    console.error(
      `The resolution ${constraints.video.width.exact}x${constraints.video.height.exact} px is not supported by your device.`
    )
  } else if (error.name === 'NotAllowedError') {
    console.error('You need to grant this page permission to access your camera and microphone.')
  } else {
    console.error(`getUserMedia error: ${error.name}`, error)
  }
}

onMounted(() => {
  navigator.mediaDevices.getUserMedia(constraints).then(handleStream).catch(handleError)
})
</script>

<style scoped></style>
