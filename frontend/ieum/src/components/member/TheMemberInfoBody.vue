<template>
  <div
    class="flex items-center w-full p-3 space-x-2 text-sm font-medium text-gray-500 bg-white border border-gray-200 rounded-lg shadow-sm dark:text-gray-400 sm:text-base dark:bg-gray-800 dark:border-gray-700 sm:p-4 sm:space-x-4 rtl:space-x-reverse"
  >
    <div class="mx-auto border-b border-gray-900/10 pb-12">
      <div class="mt-10 grid grid-cols-3 gap-x-6 gap-y-8">
        <div class="col-span-1 text-center">
          <h2 class="text-base font-semibold leading-7 text-gray-900">회원 정보 수정</h2>
        </div>
        <div class="col-span-2"></div>
        <div class="col-span-1">
          <label class="block text-center text-sm font-medium leading-6 text-gray-900"
            >아이디</label
          >
        </div>
        <div class="col-span-2">
          <input
            type="text"
            name="memberId"
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            :value="memberInfo.memberId"
            disabled
          />
        </div>
        <div class="col-span-1">
          <label class="block text-center text-sm font-medium leading-6 text-gray-900">이름</label>
        </div>
        <div class="col-span-2">
          <input
            type="text"
            name="memberName"
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            :value="memberInfo.name"
          />
        </div>

        <div class="col-span-1">
          <label class="block text-center text-sm font-medium leading-6 text-gray-900"
            >이메일</label
          >
        </div>
        <div class="col-span-2">
          <input
            type="text"
            name="memberId"
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            :value="memberInfo.email"
            placeholder="ieum@naver.com"
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
            aria-describedby="helper-text-explanation"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            pattern="010-[0-9]{3}-[0-9]{4}"
            placeholder="123-456-7890"
            maxlength="13"
            v-model="memberInfo.phone"
            required
          />
        </div>

        <div class="col-span-1">
          <button
            type="button"
            class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            @click="sendCode"
          >
            전송
          </button>
        </div>

        <div class="col-span-3 items-center" v-show="stateInfo.validPhone">
          <div
            class="mx-auto w-2/3 p-4 mb-4 text-sm text-center text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400"
            role="alert"
          >
            <span class="font-medium"> 전화번호를 확인해주세요.</span>
          </div>
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
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            maxlength="6"
            v-model="authCode"
          />
        </div>
        <div class="col-span-1">
          <button
            type="button"
            class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            @click="verifyCode"
          >
            확인
          </button>
        </div>
      </div>
      <div class="col-span-3 text-center mt-10">
        <button
          type="button"
          class="w-1/2 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          @click="edit"
        >
          수정
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { getInfo } from '../../api/member.js'
import swal from 'sweetalert'
import { sendAuth, verify, modify } from '../../api/member'

const memberInfo = ref({})

const prevMemberInfo = ref({})

const stateInfo = ref({
  checkAuth: false,
  checkInput: false,
  sendSuccess: true,
  changePhone: false,
  checkInputPhone: false,
  validPhone: false
})

const authCode = ref('')

// 휴대폰 번호 입력 함수
// watch를 사용하여 phone이 변경될 때마다 처리
watch(
  () => memberInfo.value.phone,
  (newValue) => {
    const formattedphone = newValue.replace(/\D+/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3')
    memberInfo.value.phone = formattedphone
  }
)

watch(
  () => memberInfo.value.phone,
  (nv) => {
    stateInfo.value.sendSuccess = false
    stateInfo.value.changePhone = nv !== prevMemberInfo.value.phone
  }
)
onMounted(() => {
  console.log('')
  getInfo(({ data }) => {
    memberInfo.value = data.data
    prevMemberInfo.value = JSON.parse(JSON.stringify(data.data))
  })
})

const verifyCode = () => {
  if (stateInfo.value.sendSuccess) {
    swal({
      title: '알림',
      text: '인증 번호 전송을 확인해주세요.',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          visible: true,
          className: '',
          closeModal: true
        }
      }
    })
  } else {
    console.log({
      phone: memberInfo.value.phone,
      code: authCode.value
    })
    verify(
      {
        phone: memberInfo.value.phone,
        code: authCode.value
      },
      ({ data }) => {
        if (data.success) {
          stateInfo.value.checkAuth = true

          swal({
            title: '알림',
            text: '인증이 완료됐습니다.',
            icon: 'success',
            buttons: {
              confirm: {
                text: '확인',
                visible: true,
                className: '',
                closeModal: true
              }
            }
          })
        } else {
          swal({
            title: '알림',
            text: '인증번호가 일치하지 않습니다.',
            icon: 'error',
            buttons: {
              confirm: {
                text: '확인',
                visible: true,
                className: '',
                closeModal: true
              }
            }
          })
        }
      }
    )
  }
}

const sendCode = () => {
  if (memberInfo.value.memberId === '' || memberInfo.value.phone.length < 13) {
    stateInfo.value.checkInput = true
  } else {
    sendAuth(memberInfo.value.phone, ({ data }) => {
      if (data.success) {
        stateInfo.value.checkInputPhone = false
        stateInfo.value.sendSuccess = true
        swal({
          title: '알림',
          text: '전송이 완료됐습니다',
          icon: 'success',
          buttons: {
            confirm: {
              text: '확인',
              visible: true,
              className: '',
              closeModal: true
            }
          }
        })
      } else {
        swal({
          title: '알림',
          text: '전송에 실패했습니다',
          icon: 'error',
          buttons: {
            confirm: {
              text: '확인',
              visible: true,
              className: '',
              closeModal: true
            }
          }
        })
      }
    })
  }
}

const edit = () => {
  const memberForm = {
    memberId: memberInfo.value.memberId,
    password: memberInfo.value.password,
    name: memberInfo.value.name,
    phone: memberInfo.value.phone,
    email: memberInfo.value.email
  }
  modify(memberForm, ({ data }) => {
    console.log(data)
  })
}
</script>

<style scoped></style>
