<template>
  <div class="mx-auto my-12 w-2/3">
    <div class="border-b border-gray-900/10 pb-12">
      <div class="mt-10 grid grid-cols-3 gap-x-6 gap-y-8">
        <div class="col-span-3">
          <h2 class="text-base font-semibold leading-7 text-gray-900">비밀번호 찾기</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">
            찾으실 아이디와 전화번호를 입력해주세요
          </p>
        </div>
        <div class="col-span-1">
          <label class="block text-center text-sm font-medium leading-6 text-gray-900"
            >아이디</label
          >
        </div>
        <div class="col-span-2">
          <input
            type="text"
            name="memberId"
            v-model="memberInfo.memberId"
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          />
        </div>

        <div class="col-span-1">
          <label class="block text-center text-sm font-medium leading-6 text-gray-900"
            >전화 번호</label
          >
        </div>
        <div class="col-span-1 relative">
          <div
            class="absolute inset-y-0 start-0 top-0 flex items-center ps-3.5 pointer-events-none"
          >
            <svg
              class="w-4 h-4 text-gray-500 dark:text-gray-400"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="currentColor"
              viewBox="0 0 19 18"
            >
              <path
                d="M18 13.446a3.02 3.02 0 0 0-.946-1.985l-1.4-1.4a3.054 3.054 0 0 0-4.218 0l-.7.7a.983.983 0 0 1-1.39 0l-2.1-2.1a.983.983 0 0 1 0-1.389l.7-.7a2.98 2.98 0 0 0 0-4.217l-1.4-1.4a2.824 2.824 0 0 0-4.218 0c-3.619 3.619-3 8.229 1.752 12.979C6.785 16.639 9.45 18 11.912 18a7.175 7.175 0 0 0 5.139-2.325A2.9 2.9 0 0 0 18 13.446Z"
              />
            </svg>
          </div>
          <input
            type="text"
            v-model="memberInfo.memberPhone"
            aria-describedby="helper-text-explanation"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
            placeholder="123-456-7890"
            maxlength="13"
            required
          />
        </div>
        <div class="col-span-1">
          <button
            type="button"
            class="w-2/3 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          >
            전송
          </button>
        </div>

        <div class="col-span-1">
          <label class="block text-center text-sm font-medium leading-6 text-gray-900"
            >인증번호</label
          >
        </div>
        <div class="col-span-1">
          <input
            type="text"
            name="memberId"
            v-model="memberInfo.memberId"
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          />
        </div>
        <div class="col-span-1">
          <button
            type="button"
            class="w-2/3 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            @click="next"
          >
            확인
          </button>
        </div>
      </div>
      <div class="col-span-3 text-center mt-10">
        <button
          type="button"
          class="w-1/2 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          @click="next"
        >
          다음 단계
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, defineProps } from 'vue'
const props = defineProps(['changeMemberInfo'])

const memberInfo = ref({
  memberId: '',
  memberPhone: ''
})

// 휴대폰 번호 입력 함수
// watch를 사용하여 phone이 변경될 때마다 처리
watch(
  () => memberInfo.value.memberPhone,
  (newValue) => {
    const formattedmemberPhone = newValue
      .replace(/\D+/g, '')
      .replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3')
    memberInfo.value.memberPhone = formattedmemberPhone
  }
)

const next = () => {
  // 나중에 회원 조회 넣기
  if (true) {
    props.changeMemberInfo(memberInfo.value)
  }
}
</script>

<style lang="scss" scoped></style>
