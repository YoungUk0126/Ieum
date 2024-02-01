<template>
  <div class="mx-auto my-12 w-2/3">
    <div class="border-b border-gray-900/10 pb-12">
      <div class="mt-10 grid grid-cols-3 gap-x-6 gap-y-8">
        <div class="col-span-3">
          <h2 class="text-base font-semibold leading-7 text-gray-900">비밀번호 변경</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">변경하실 비밀번호를 입력해주세요</p>
        </div>
        <div class="col-span-1">
          <label class="block text-center text-sm font-medium leading-6 text-gray-900"
            >비밀번호</label
          >
        </div>
        <div class="col-span-2">
          <input
            type="password"
            name="password"
            v-model="data.password"
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          />
        </div>

        <div class="col-span-1">
          <label class="block text-center text-sm font-medium leading-6 text-gray-900"
            >비밀번호 확인</label
          >
        </div>
        <div class="col-span-2">
          <input
            type="password"
            name="passwordCheck"
            v-model="data.passwordCheck"
            @change="checkEqual"
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          />
          <div
            class="p-4 mt-3 mb-4 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400"
            role="alert"
            v-show="!check"
          >
            <span class="font-medium">비밀번호가 일치하지 않습니다</span>
          </div>
        </div>
      </div>
      <div class="col-span-3 text-center mt-10">
        <button
          type="button"
          class="w-1/2 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          @click="next"
        >
          수정
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, defineProps, onMounted } from 'vue'
import { useRouter } from 'vue-router'
const props = defineProps(['member', 'memberAuth', 'editSubmit'])

const memberInfo = ref({
  memberId: '',
  memberPhone: ''
})

const data = ref({
  password: '',
  passwordCheck: ''
})

const check = ref(true)

const router = useRouter()

onMounted(() => {
  if (!props.memberAuth) {
    router.replace({ name: 'TheInputInfo' })
  }
  memberInfo.value.memberId = props.member.memberId
  memberInfo.value.memberPhone = props.member.memberPhone
})

// 휴대폰 번호 입력 함수
// watch를 사용하여 phone이 변경될 때마다 처리
const checkEqual = () => {
  console.log('check = ' + data.value.passwordCheck)
  console.log(data.value.password)

  check.value = data.value.passwordCheck === data.value.password && data.value.password != ''
}

const next = () => {
  if (true) {
    props.editSubmit(memberInfo.value)
  }
}
</script>

<style lang="scss" scoped></style>
