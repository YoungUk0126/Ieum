import { localAxios, localSessionAxios } from '@/util/http-commons'
import swal from 'sweetalert'
import VueCookies from 'vue-cookies'

const local = localAxios()

const localSession = localSessionAxios()

const url = 'http://localhost:8080/api/member'

const fail = ({ response }) => {
  if (response.status == 500) {
    swal({
      title: '오류',
      text: '서버 오류!',
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
  } else if (response.status == 400) {
    swal({
      title: '알림',
      text: '잘못된 로그인 정보입니다',
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
  } else if (response.status == 403) {
    refreshAccessToken()
    return true
  } else {
    swal({
      title: '버그',
      text: '뭔 오류일까요..' + response.status,
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
  return false
}

// Axios를 사용한 토큰 갱신 로직
function refreshAccessToken() {
  const data = { refreshToken: VueCookies.get('refreshToken') }
  local
    .post(`${url}/refresh`, JSON.stringify(data))
    .then(({ data }) => {
      VueCookies.set('accessToken', data.accessToken)
      VueCookies.set('auth', true)
    })
    .catch(() => {
      VueCookies.remove('accessToken')
      VueCookies.remove('refreshToken')
      VueCookies.set('auth', false)
    })
}

function register(data, success) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}
function login(data, success) {
  local.post(`${url}/login`, JSON.stringify(data)).then(success).catch(fail)
}

// refresh 토큰 과정 필요
function modifyApi(data, success) {
  localSession
    .put(`${url}`, JSON.stringify(data))
    .then(success)
    .catch((response) => {
      // refresh 토큰 재발급 과정에서만 true를 return 함
      if (fail(response)) {
        modifyApi(data, success)
      }
    })
}

function removeApi(param, success) {
  localSession
    .delete(`${url}/${param}`)
    .then(success)
    .catch(() => {
      // refresh 토큰 재발급 과정에서만 true를 return 함
      if (fail()) {
        removeApi(param, success)
      }
    })
}

export { register, login, modifyApi, removeApi }
