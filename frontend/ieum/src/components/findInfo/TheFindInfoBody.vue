<template>
  <ol
    class="flex items-center w-full p-3 space-x-2 text-sm font-medium text-center text-gray-500 bg-white border border-gray-200 rounded-lg shadow-sm dark:text-gray-400 sm:text-base dark:bg-gray-800 dark:border-gray-700 sm:p-4 sm:space-x-4 rtl:space-x-reverse"
  >
    <li
      class="flex items-center"
      :class="{
        'text-blue-600 dark:text-blue-500': curStep == 1,
        'dark:text-gray-400 text-gray-500': curStep != 1
      }"
    >
      <span
        class="flex items-center justify-center w-5 h-5 me-2 text-xs border rounded-full shrink-0"
        :class="{
          'border-blue-600 dark:border-blue-500': curStep == 1,
          'dark:border-gray-400 border-gray-500': curStep != 1
        }"
      >
        1
      </span>
      인증 절차
      <svg
        class="w-3 h-3 ms-2 sm:ms-4 rtl:rotate-180"
        aria-hidden="true"
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 12 10"
      >
        <path
          stroke="currentColor"
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="m7 9 4-4-4-4M1 9l4-4-4-4"
        />
      </svg>
    </li>
    <li
      class="flex items-center"
      :class="{
        'text-blue-600 dark:text-blue-500': curStep == 2,
        'dark:text-gray-400 text-gray-500': curStep != 2
      }"
    >
      <span
        class="flex items-center justify-center w-5 h-5 me-2 text-xs border rounded-full shrink-0"
        :class="{
          'border-blue-600 dark:border-blue-500': curStep == 2,
          'dark:border-gray-400 border-gray-500': curStep != 2
        }"
      >
        2
      </span>
      정보 수정
      <svg
        class="w-3 h-3 ms-2 sm:ms-4 rtl:rotate-180"
        aria-hidden="true"
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 12 10"
      >
        <path
          stroke="currentColor"
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="m7 9 4-4-4-4M1 9l4-4-4-4"
        />
      </svg>
    </li>
    <li
      class="flex items-center"
      :class="{
        'text-blue-600 dark:text-blue-500': curStep == 3,
        'dark:text-gray-400 text-gray-500': curStep != 3
      }"
    >
      <span
        class="flex items-center justify-center w-5 h-5 me-2 text-xs border rounded-full shrink-0"
        :class="{
          'border-blue-600 dark:border-blue-500': curStep == 3,
          'dark:border-gray-400 border-gray-500': curStep != 3
        }"
      >
        3
      </span>
      변경 완료
    </li>
  </ol>

  <router-view
    :changeMemberInfo="changeMemberInfo"
    :member="member"
    :memberAuth="memberAuth"
    :editSubmit="editSubmit"
  ></router-view>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { editPassword } from '../../api/member'

const router = useRouter()

const curStep = ref(1)
const member = ref({
  memberId: '',
  memberPhone: ''
})
const memberAuth = ref(false)

onMounted(() => {
  if (curStep.value == 2) {
    router.replace({ name: 'TheAuthInfo' })
  } else if (curStep.value == 3) {
    router.replace({ name: 'TheEndInfo' })
  } else {
    router.replace({ name: 'TheInputInfo' })
  }
})

watch(
  () => curStep.value,
  (nv, ov) => {
    if (nv == 2) {
      router.replace({ name: 'TheAuthInfo' })
    } else if (nv == 3) {
      router.replace({ name: 'TheEndInfo' })
    } else {
      router.replace({ name: 'TheInputInfo' })
    }
  }
)

const changeMemberInfo = (data) => {
  member.value.memberId = data.memberId
  member.value.memberPhone = data.memberPhone
  curStep.value++
  memberAuth.value = true
}

const editSubmit = (password) => {
  editPassword(
    {
      memberId: member.value.memberId,
      password: password
    },
    ({ data }) => {
      if (data.success) {
        curStep.value++
      }
    }
  )
}
</script>

<style scoped></style>
