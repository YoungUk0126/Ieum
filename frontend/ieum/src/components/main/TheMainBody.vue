<template>
  <div>
    <div class="flex flex-wrap x-w-screen-xl w-screen max-w-3xl">
      <div class="w-full px-10">
        <button
          type="button"
          class="w-full min-h-40 text-4xl font-bold text-gray-500 bg-gray-200 hover:bg-gray-500 hover:text-gray-200 focus:outline-none focus:ring-4 focus:ring-gray-300 rounded-lg px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
          @click="changeRouter('TheCallView')"
        >
          <div class="flex justify-center">
            <div>
              <img src="@/assets/images/call.png" class="w-16 h-16" />
            </div>
            <div class="flex items-center">영상 통화</div>
          </div>
        </button>
      </div>
      <div class="w-1/2 pl-10 pr-1">
        <button
          type="button"
          class="w-full min-h-40 text-xl font-bold text-gray-500 bg-gray-200 hover:bg-gray-500 hover:text-gray-200 focus:outline-none focus:ring-4 focus:ring-gray-300 rounded-lg px-3 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
          @click="changeRouter('TheChatView')"
        >
          <div class="flex justify-center gap-x-1">
            <div>
              <img src="@/assets/images/chat.png" class="w-10 h-10" />
            </div>
            <div class="flex items-center">대화목록</div>
          </div>
        </button>
      </div>
      <div class="w-1/2 pr-10 pl-1">
        <button
          type="button"
          class="w-full min-h-40 text-xl font-bold text-gray-500 bg-gray-200 hover:bg-gray-500 hover:text-gray-200 focus:outline-none focus:ring-4 focus:ring-gray-300 rounded-lg px-3 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
          @click="openInjectionModal"
        >
          <div class="flex justify-center gap-x-1">
            <div>
              <img src="@/assets/images/pill.png" class="w-10 h-10" />
            </div>
            <div class="flex items-center">복용약</div>
          </div>
        </button>
      </div>
      <div class="w-full px-10">
        <button
          type="button"
          class="w-full min-h-40 text-4xl font-bold text-gray-500 bg-gray-200 hover:bg-gray-500 hover:text-gray-200 focus:outline-none focus:ring-4 focus:ring-gray-300 rounded-lg px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
          @click="openVModal"
        >
          <div class="flex justify-center">
            <div>
              <img src="@/assets/images/calendar.png" class="w-16 h-16" />
            </div>
            <!-- Modal toggle -->
            <div class="flex items-center">알림 등록</div>
          </div>
        </button>
      </div>
    </div>
    <VModal :open-modal="openVModal" :close-modal="closeVModal" />
    <VModalInjection :open-modal="openInjectionModal" :close-modal="closeInjectionModal" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import VModal from '../Alarm/VModal.vue'
import VModalInjection from '../Alarm/VModalInjection.vue'
import { useRouter } from 'vue-router'
import { Modal } from 'flowbite'

const router = useRouter()
const injectionModal = ref()
const allModal = ref()

onMounted(() => {
  initInjectionModal()
  initVModal()
})

const initInjectionModal = () => {
  const $targetEl = document.getElementById('VModalPill')

  // options with default values
  const options = {
    placement: 'center',
    backdrop: 'static',
    backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
      console.log('modal is hidden')
    },
    onShow: () => {
      console.log('modal is shown')
    },
    onToggle: () => {
      console.log('modal has been toggled')
    }
  }

  // instance options object
  const instanceOptions = {
    id: 'VModalPill',
    override: true
  }

  injectionModal.value = new Modal($targetEl, options, instanceOptions)
}

const openInjectionModal = () => {
  injectionModal.value.show()
}

const closeInjectionModal = () => {
  injectionModal.value.hide()
}

const initVModal = () => {
  const $targetEl = document.getElementById('VModal')

  // options with default values
  const options = {
    placement: 'center',
    backdrop: 'static',
    backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
      console.log('modal is hidden')
    },
    onShow: () => {
      console.log('modal is shown')
    },
    onToggle: () => {
      console.log('modal has been toggled')
    }
  }

  // instance options object
  const instanceOptions = {
    id: 'VModal',
    override: true
  }

  allModal.value = new Modal($targetEl, options, instanceOptions)
}

const openVModal = () => {
  allModal.value.show()
}

const closeVModal = () => {
  allModal.value.hide()
}

const changeRouter = (name) => {
  router.push({ name: name })
}
</script>

<style scoped></style>
