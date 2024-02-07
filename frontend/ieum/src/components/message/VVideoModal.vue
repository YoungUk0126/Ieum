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
    <video ref="video" autoplay controls>
      <source ref="videoSave" type="video/webm" />
    </video>
    <div class="flex flex-wrap items-center">
      <div class="w-1/3 text-center md:text-right mb-3">
        <p class="font-bold mb-0">발신자</p>
      </div>
      <div class="w-2/3 mb-3">
        <input
          type="text"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="ex) 첫째딸"
          v-model="editState.messageSender"
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
          type="date"
          id="datepicker"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="Select date"
          v-model="editState.messageTime"
        />
      </div>
      <div class="w-full border-t border-4 my-3"></div>

      <div class="w-1/2 text-center">
        <button
          type="button"
          class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          @click="startRecording"
        >
          녹화시작
        </button>
      </div>
      <div class="w-1/2 text-center">
        <button
          type="button"
          class="text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
          @click="stopRecording"
        >
          녹화종료
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
      @click="editSubmit"
    >
      수정
    </button>
    <button
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
const videoSave = ref()
const editState = ref({
  messageNo: '',
  careNo: '',
  messageSender: '',
  messageName: '',
  messageType: ''
})

const closeModal = () => {
  endStream()
  modal.value.hide()
}

// 이후 messageState가 변경됨에 따라 이전 음성을 바꿔줘야 한다.
watch(
  () => props.messageState,
  (nv, ov) => {
    console.log(nv)
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

const stream = ref()
const video = ref()
const recorder = ref()
const isRecording = ref(false)
const recordedChunks = ref([])
const recordedBlob = ref()

const constraints = {
  audio: true,
  video: true
}
onMounted(() => {
  editState.value = props.messageState
})

const stopRecording = () => {
  recorder.value.stop()
  endStream()
}

const startRecording = async () => {
  await navigator.mediaDevices.getUserMedia(constraints).then(handleStream).catch(handleError)

  try {
    // MediaRecorder 초기화 및 시작
    recorder.value = new MediaRecorder(stream.value, {
      mimeType: 'video/webm; codecs=vp9'
    })

    recorder.value.ondataavailable = (event) => {
      if (event.data.size > 0) {
        // 여기에서 녹화된 데이터를 처리하거나 저장합니다.
        recordedChunks.value.push(event.data)
      }
    }

    recorder.value.onstop = () => {
      // 녹화가 중지될 때 수행할 작업을 정의합니다.
      recordedBlob.value = new Blob(recordedChunks.value, { type: 'video/webm' })

      // 여기에서 녹화된 Blob 데이터를 처리하거나 저장합니다.
      // 예를 들어, 저장할 때는 a 태그를 만들고 download 속성을 사용하여 Blob을 다운로드할 수 있습니다.
      const downloadLink = document.createElement('a')
      downloadLink.href = URL.createObjectURL(recordedBlob.value)
      downloadLink.download = 'recorded-video.webm'
      document.body.appendChild(downloadLink)
      downloadLink.click()
      document.body.removeChild(downloadLink)
      console.log('Recording stopped')
      // Set the src attribute of the video tag to the URL of the recorded video
      videoSave.value.src = URL.createObjectURL(recordedBlob.value)
      recordedChunks.value = []
    }

    recorder.value.start()
    isRecording.value = true
  } catch (error) {
    console.error('Error accessing media devices:', error)
  }
}

const endStream = () => {
  if (stream.value != undefined) {
    // 미디어 스트림의 트랙들 가져오기
    const tracks = stream.value.getTracks()

    // 각 트랙에 대해 stop() 메서드 호출
    tracks.forEach((track) => {
      track.stop()
    })
  }
}

const handleStream = (data) => {
  stream.value = data

  stream.value.onremovetrack = () => {
    console.log('Stream ended')
  }
  video.value.srcObject = stream.value
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

const editSubmit = () => {
  const formData = new FormData()
  const json = JSON.stringify(editState.value)
  const formJson = new Blob([json], { type: 'application/json' })
  formData.append('data', formJson)
  formData.append('file', recordedBlob.value, 'editFile.webm')

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
