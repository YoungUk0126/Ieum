<template>
  <div class="mx-auto my-12 p-6 w-full">
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
            :readonly="stateInfo.sendSuccess"
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
            pattern="010-[0-9]{3}-[0-9]{4}"
            placeholder="123-456-7890"
            maxlength="13"
            required
            :disabled="stateInfo.sendSuccess"
          />
        </div>

        <div class="col-span-1">
          <button
            type="button"
            class="w-2/3 text-white bg-gray-400 hover:bg-gray-600 focus:ring-4 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 focus:outline-none"
            @click="sendCode"
            :disabled="stateInfo.checkAuth"
          >
            전송
          </button>
        </div>

        <div class="col-span-3 items-center" v-show="stateInfo.checkInput">
          <div
            class="mx-auto w-2/3 p-4 mb-4 text-sm text-center text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400"
            role="alert"
          >
            <span class="font-medium"> 입력되지 않은 정보가 존재합니다.</span>
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
            v-model="authCode"
            autocomplete="given-name"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-4 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            :disabled="stateInfo.checkInput || stateInfo.checkAuth"
            maxlength="6"
          />
        </div>
        <div class="col-span-1">
          <button
            type="button"
            class="w-2/3 text-white bg-gray-400 hover:bg-gray-600 focus:ring-4 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 focus:outline-none"
            @click="verifyCode"
            :disabled="stateInfo.checkInput || stateInfo.checkAuth"
          >
            확인
          </button>
        </div>
      </div>
      <div class="col-span-3 text-center mt-10">
        <button
          type="button"
          class="w-1/2 text-white bg-gray-400 hover:bg-gray-600 focus:ring-4 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 focus:outline-none"
          @click="next"
        >
          다음 단계
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import swal from 'sweetalert'
import { ref, watch, defineProps } from 'vue'
import { sendAuth, checkMember, verify } from '../../api/member'

const props = defineProps(['changeMemberInfo'])

const memberInfo = ref({
  memberId: '',
  memberPhone: ''
})

const authCode = ref('')

const stateInfo = ref({
  checkAuth: false,
  checkInput: false,
  sendSuccess: false
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
  if (stateInfo.value.checkAuth) {
    props.changeMemberInfo(memberInfo.value)
  } else {
    swal({
      title: '알림',
      text: '회원 인증을 먼저 진행해주시기 바랍니다.',
      icon: 'warning',
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

const verifyCode = () => {
  if (authCode.value.length == 6) {
    verify(
      {
        phone: memberInfo.value.memberPhone,
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
  if (memberInfo.value.memberId === '' || memberInfo.value.memberPhone.length < 13) {
    stateInfo.value.checkInput = true
  } else {
    stateInfo.value.checkInput = false
    checkMember(
      {
        memberId: memberInfo.value.memberId,
        phone: memberInfo.value.memberPhone
      },
      ({ data }) => {
        console.log(data.data.isExist)
        if (data.data.isExist) {
          sendAuth(memberInfo.value.memberPhone, ({ data }) => {
            if (data.success) {
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
        } else {
          swal({
            title: '알림',
            text: '존재하지 않는 회원입니다',
            icon: 'warning',
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
</script>

<style lang="scss" scoped></style>
