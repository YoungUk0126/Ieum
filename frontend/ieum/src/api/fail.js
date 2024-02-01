import { localRefreshAxios } from '@/util/http-commons'
import swal from 'sweetalert'
import VueCookies from 'vue-cookies'
import { useCounterStore } from '@/stores/counter'

const local = localRefreshAxios()
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

    // Bad request로 잘못된 정보 입력
  } else if (response.status == 400) {
    swal({
      title: '알림',
      text: '잘못된 정보입니다',
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

    // 세션 정보 없음
  } else if (response.status == 401) {
    swal({
      title: '로그인',
      text: '로그인 정보가 존재하지 않습니다',
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

    // refresh 토큰 재발급, 발급 완료되면 이전 HTTP 다시 실행
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
      const store = useCounterStore()
      VueCookies.set('accessToken', data.accessToken)
      VueCookies.set('refreshToken', data.refreshToken)
      VueCookies.set('auth', true)
      store.auth = false
    })
    .catch(() => {
      swal({
        title: '로그인',
        text: '로그인 정보가 존재하지 않습니다',
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
    })
}

export { fail }
