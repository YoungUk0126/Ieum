import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  const count = ref(0)
  const doubleCount = computed(() => count.value * 2)
  const headerState = ref(true)
  function increment() {
    count.value++
  }

  const auth = ref(false)

  return { count, doubleCount, increment, auth , headerState}
})
