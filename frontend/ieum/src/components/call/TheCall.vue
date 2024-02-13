<template>
  <div class="call-body w-full mx-auto mt-4">
    <div id="session" v-if="session">
      <div id="video-container">
        <template v-if="who">
          <UserVideo
            class="col-md-6 w-full"
            :stream-manager="pub"
            @click="updateMainVideoStreamManager(pub)"
          />
        </template>
        <template v-if="!who">
          <UserVideo
            :stream-manager="subscriber"
            class="col-md-6 w-full"
            @click="updateMainVideoStreamManager(subscriber)"
          />
        </template>
      </div>
      <div class="col-md-6 w-full">
        <UserMainVideo :stream-manager="mainStreamManager" />
      </div>
      <video ref="soundRef" autoplay class="hidden"></video>
    </div>
  </div>
  <div
    class="z-50 grid mt-10 w-full h-16 grid-cols-1 px-8 bg-white dark:bg-gray-700 dark:border-gray-600"
  >
    <div class="flex items-center justify-center mx-auto">
      <button
        data-tooltip-target="tooltip-microphone"
        :class="[
          'p-2.5 group rounded-full me-4',
          {
            'bg-gray-100': audioState,
            'bg-red-500': !audioState,
            'dark:bg-gray-600 dark:hover:bg-gray-800': audioState
          }
        ]"
        @click="muteAudio"
      >
        <svg
          class="w-11 h-11 text-gray-500 dark:text-gray-300 group-hover:text-gray-900 dark:group-hover:text-white"
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
        <span class="sr-only">{{ audioState ? '음소거 해제' : '음소거' }}</span>
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
        :class="[
          'p-2.5 rounded-full  me-4 ',
          {
            'bg-gray-100': videoState,
            'bg-red-500': !videoState,
            'dark:bg-gray-600 dark:hover:bg-gray-800': videoState
          }
        ]"
        @click="enableVideo"
      >
        <svg
          class="w-11 h-11 text-gray-500 dark:text-gray-300 group-hover:text-gray-900 dark:group-hover:text-white"
          aria-hidden="true"
          xmlns="http://www.w3.org/2000/svg"
          fill="currentColor"
          viewBox="0 0 20 14"
        >
          <path
            d="M11 0H2a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2Zm8.585 1.189a.994.994 0 0 0-.9-.138l-2.965.983a1 1 0 0 0-.685.949v8a1 1 0 0 0 .675.946l2.965 1.02a1.013 1.013 0 0 0 1.032-.242A1 1 0 0 0 20 12V2a1 1 0 0 0-.415-.811Z"
          />
        </svg>
        <span class="sr-only">{{ audioState ? '화면 켜기' : '화면 끄기' }}</span>
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
        class="p-2.5 bg-gray-100 group rounded-full hover:bg-gray-200 me-4 focus:outline-none dark:bg-gray-600 dark:hover:bg-gray-800"
        @click="leaveSession"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          class="w-11 h-11 text-gray-500 dark:text-gray-300 group-hover:text-gray-900 dark:group-hover:text-white"
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
    <audio ref="bgmAudio" src="/src/assets/bgm.mp3" controls style="display: none"></audio>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from './VUserVideo.vue'
import UserMainVideo from './VUserMainVideo.vue'
import swal from 'sweetalert'
import { createToken, createSession } from '@/api/call.js'
import { useRouter } from 'vue-router'
const router = useRouter()
const OV = ref()
const session = ref()
const mainStreamManager = ref()
const subscriber = ref()
const pub = ref()
const who = ref(false)
const videoState = ref(true)
const audioState = ref(true)
const soundRef = ref()
const bgmAudio = ref()

onMounted(() => {
  bgmAudio.value.play() // 음원 재생

  joinSession()

  window.onunload = () => {
    bgmAudio.value.pause() // 일시 정지
    if (session.value) session.value.disconnect()
    session.value = undefined
  }
})

const joinSession = () => {
  OV.value = new OpenVidu()
  session.value = OV.value.initSession()

  session.value.on('streamCreated', ({ stream }) => {
    // 구독은 상대방 즉 care에 대한 스트림을 관리함
    subscriber.value = session.value.subscribe(stream)

    // 어떤 화면이든 소리를 듣기 위한 soundRef 추가시켜주기
    subscriber.value.addVideoElement(soundRef.value)

    bgmAudio.value.pause() // 일시 정지

    subscriber.value.on('streamPlaying', (event) => {
      updateMainVideoStreamManager(event.target.stream.streamManager)
    })
  })

  session.value.on('streamDestroyed', ({ stream }) => {
    /*if (mainStreamManager.value == stream.streamManager) {
      mainStreamManager.value = pub.value
      who.value = false
    }*/
    pub.value = undefined
    subscriber.value = undefined
    endSession()
  })

  session.value.on('exception', ({ exception }) => {
    console.warn(exception)
  })

  getToken().then((token) => {
    session.value
      .connect(token)
      .then(() => {
        let publisher = OV.value.initPublisher(undefined, {
          audioSource: undefined,
          videoSource: undefined,
          publishAudio: true,
          publishVideo: true,
          resolution: '640x480',
          frameRate: 30,
          insertMode: 'REPLACE',
          mirror: true
        })

        mainStreamManager.value = publisher
        pub.value = publisher
        session.value.publish(publisher)
      })
      .catch((error) => {
        bgmAudio.value.pause() // 일시 정지
        swal({
          title: '알림',
          text: '현재 이음이가 자고 있어요. 나중에 다시 전화해주세요',
          icon: 'info',
          buttons: {
            confirm: {
              text: '확인',
              visible: true,
              className: '',
              closeModal: true
            }
          }
        }).then(() => {
          router.push({ name: 'TheMainViewVue' })
        })
      })
  })

  window.addEventListener('beforeunload', leaveSession)
}

const leaveSession = () => {
  if (session.value) {
    session.value.disconnect()

    session.value = undefined
    mainStreamManager.value = undefined
    subscriber.value = undefined
    OV.value = undefined
    endSession()
  }
}

const endSession = () => {
  bgmAudio.value.pause() // 일시 정지
  swal({
    title: '종료',
    text: '통화가 종료되었습니다.',
    icon: 'info',
    buttons: {
      confirm: {
        text: '확인',
        visible: true,
        className: '',
        closeModal: true
      }
    }
  }).then(() => {
    router.push({ name: 'TheMainViewVue' })
  })
}

const updateMainVideoStreamManager = (stream) => {
  if (mainStreamManager.value !== stream) {
    who.value = !who.value
    mainStreamManager.value = stream
  }
}

const getToken = async () => {
  const sessionId = await createSession()
  return await createToken(sessionId)
}

// 네비게이션 가드를 사용하여 네비게이션 이벤트를 감지합니다.
router.beforeEach((to, from, next) => {
  // 세션 연결을 종료시키기
  if (session.value) session.value.disconnect()
  session.value = undefined
  next()
})

const muteAudio = () => {
  // true to unmute the audio track, false to mute it
  window.blur()
  if (audioState.value) {
    audioState.value = false
    pub.value.publishAudio(false)
  } else {
    audioState.value = true
    pub.value.publishAudio(true)
  }
}

const enableVideo = () => {
  // true to enable the video track, false to disable it
  if (videoState.value) {
    videoState.value = false
    pub.value.publishVideo(false)
  } else {
    videoState.value = true
    pub.value.publishVideo(true)
  }
}
</script>

<style>
.call-body {
  min-height: 60vh;
}
</style>
