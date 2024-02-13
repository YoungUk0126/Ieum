<template>
  <div class="call-body w-screen h-screen mx-auto">
    <div class="col-md-6 w-full h-full">
      <UserMainVideo :stream-manager="mainStreamManager" />
    </div>
    <video ref="soundRef" autoplay class="hidden"></video>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { OpenVidu } from 'openvidu-browser'
import UserMainVideo from './VUserMainVideo.vue'
import swal from 'sweetalert'
import { createTokenCare, createSessionCare } from '@/api/call.js'
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
const OV = ref()
const session = ref()
const mainStreamManager = ref()
const subscriber = ref()
const pub = ref()
const soundRef = ref()

onMounted(() => {
  window.onunload = () => {
    if (session.value) session.value.disconnect()
    session.value = undefined
  }
  if (route.query.serial === undefined) {
    window.close()
  } else {
    joinSession(route.query.serial)
  }
})

const joinSession = (serial) => {
  OV.value = new OpenVidu()
  session.value = OV.value.initSession()

  session.value.on('streamCreated', ({ stream }) => {
    // 구독은 상대방 즉 care에 대한 스트림을 관리함
    subscriber.value = session.value.subscribe(stream)

    // 어떤 화면이든 소리를 듣기 위한 soundRef 추가시켜주기
    subscriber.value.addVideoElement(soundRef.value)

    subscriber.value.on('streamPlaying', (event) => {
      updateMainVideoStreamManager(event.target.stream.streamManager)
    })
  })

  session.value.on('streamDestroyed', ({ stream }) => {
    if (mainStreamManager.value == stream.streamManager) {
      mainStreamManager.value = pub.value
    }
  })

  session.value.on('exception', ({ exception }) => {
    console.warn(exception)
  })

  getToken(serial).then((token) => {
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
        console.log('There was an error connecting to the session:', error.code, error.message)
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
}

const updateMainVideoStreamManager = (stream) => {
  if (mainStreamManager.value !== stream) {
    mainStreamManager.value = stream
  }
}

const getToken = async (serial) => {
  const sessionId = await createSessionCare(serial)
  return await createTokenCare(sessionId)
}

// 네비게이션 가드를 사용하여 네비게이션 이벤트를 감지합니다.
router.beforeEach((to, from, next) => {
  // 세션 연결을 종료시키기
  if (session.value) session.value.disconnect()
  session.value = undefined
  next()
})
</script>

<style>
.call-body {
  min-height: 60vh;
}
</style>
