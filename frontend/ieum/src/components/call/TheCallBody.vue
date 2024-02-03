<template>
  <div>
    <video ref="localVideo" autoplay></video>
    <video ref="remoteVideo" autoplay></video>
  </div>
  <div
    class="z-50 grid w-full h-16 grid-cols-1 px-8 bg-white border-t border-gray-200 dark:bg-gray-700 dark:border-gray-600"
  >
    <div class="flex items-center justify-center mx-auto">
      <button
        data-tooltip-target="tooltip-microphone"
        type="button"
        class="p-2.5 group bg-gray-100 rounded-full hover:bg-gray-200 me-4 focus:outline-none focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-800 dark:bg-gray-600 dark:hover:bg-gray-800"
      >
        <svg
          class="w-4 h-4 text-gray-500 dark:text-gray-300 group-hover:text-gray-900 dark:group-hover:text-white"
          aria-hidden="true"
          xmlns="http://www.w3.org/2000/svg"
          fill="currentColor"
          viewBox="0 0 16 19"
        >
          <path
            d="M15 5a1 1 0 0 0-1 1v3a4 4 0 0 1-4 4H6a4 4 0 0 1-4-4V6a1 1 0 0 0-2 0v3a6.006 6.006 0 0 0 6 6h1v2H5a1 1 0 0 0 0 2h6a1 1 0 0 0 0-2H9v-2h1a6.006 6.006 0 0 0 6-6V6a1 1 0 0 0-1-1Z"
          />
          <path d="M9 0H7a3 3 0 0 0-3 3v5a3 3 0 0 0 3 3h2a3 3 0 0 0 3-3V3a3 3 0 0 0-3-3Z" />
        </svg>
        <span class="sr-only">음소거</span>
      </button>
      <div
        id="tooltip-microphone"
        role="tooltip"
        class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
      >
        음소거
        <div class="tooltip-arrow" data-popper-arrow></div>
      </div>
      <button
        data-tooltip-target="tooltip-camera"
        type="button"
        class="p-2.5 bg-gray-100 group rounded-full hover:bg-gray-200 me-4 focus:outline-none focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-800 dark:bg-gray-600 dark:hover:bg-gray-800"
      >
        <svg
          class="w-4 h-4 text-gray-500 dark:text-gray-300 group-hover:text-gray-900 dark:group-hover:text-white"
          aria-hidden="true"
          xmlns="http://www.w3.org/2000/svg"
          fill="currentColor"
          viewBox="0 0 20 14"
        >
          <path
            d="M11 0H2a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2Zm8.585 1.189a.994.994 0 0 0-.9-.138l-2.965.983a1 1 0 0 0-.685.949v8a1 1 0 0 0 .675.946l2.965 1.02a1.013 1.013 0 0 0 1.032-.242A1 1 0 0 0 20 12V2a1 1 0 0 0-.415-.811Z"
          />
        </svg>
        <span class="sr-only">화면 끄기</span>
      </button>
      <div
        id="tooltip-camera"
        role="tooltip"
        class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
      >
        화면 끄기
        <div class="tooltip-arrow" data-popper-arrow></div>
      </div>
      <button
        data-tooltip-target="tooltip-feedback"
        type="button"
        class="p-2.5 bg-gray-100 group rounded-full hover:bg-gray-200 me-4 focus:outline-none focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-800 dark:bg-gray-600 dark:hover:bg-gray-800"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          class="w-4 h-4 text-gray-500 dark:text-gray-300 group-hover:text-gray-900 dark:group-hover:text-white"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M1.885.511a1.745 1.745 0 0 1 2.61.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511zm9.261 1.135a.5.5 0 0 1 .708 0L13 2.793l1.146-1.147a.5.5 0 0 1 .708.708L13.707 3.5l1.147 1.146a.5.5 0 0 1-.708.708L13 4.207l-1.146 1.147a.5.5 0 0 1-.708-.708L12.293 3.5l-1.147-1.146a.5.5 0 0 1 0-.708z"
          />
        </svg>
        <span class="sr-only">종료</span>
      </button>
      <div
        id="tooltip-feedback"
        role="tooltip"
        class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
      >
        종료
        <div class="tooltip-arrow" data-popper-arrow></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useWebRTC } from './useWebRTC'

const localVideo = ref(null)
const remoteVideo = ref(null)
const localStream = ref(null)
const config = { iceServers: [{ urls: 'stun:stun.l.google.com:19302' }] }
const { localPeerConnection, remotePeerConnection } = useWebRTC(config)

onMounted(() => {
  navigator.mediaDevices
    .getUserMedia({ video: true, audio: true })
    .then((stream) => {
      localStream.value = stream
      localVideo.value.srcObject = stream

      stream.getTracks().forEach((track) => localPeerConnection.addTrack(track, stream))

      return localPeerConnection.createOffer()
    })
    .then((offer) => localPeerConnection.setLocalDescription(offer))
    .then(() => remotePeerConnection.setRemoteDescription(localPeerConnection.localDescription))
    .then(() => remotePeerConnection.createAnswer())
    .then((answer) => remotePeerConnection.setLocalDescription(answer))
    .then(() => localPeerConnection.setRemoteDescription(remotePeerConnection.localDescription))
    .catch((error) => console.error('Error setting up localPeerConnection:', error))

  remotePeerConnection.ontrack = (event) => {
    remoteVideo.value.srcObject = event.streams[0]
  }

  localPeerConnection.onicecandidate = (event) => {
    if (event.candidate) {
      remotePeerConnection.addIceCandidate(event.candidate)
    }
  }

  remotePeerConnection.onicecandidate = (event) => {
    if (event.candidate) {
      localPeerConnection.addIceCandidate(event.candidate)
    }
  }

  window.onunload = () => {
    localPeerConnection.close()
    remotePeerConnection.close()
  }
})
</script>

<style scoped></style>
