import { localRefreshAxios } from '@/util/http-commons'
import swal from 'sweetalert'
import VueCookies from 'vue-cookies'

const local = localRefreshAxios()
const url = 'http://localhost:8080/api/member'

const fail = ({ response }) => {
  console.log(response)
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
    console.log(response.status)
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
    .catch((response) => {
      console.log(response)
      //   VueCookies.remove('accessToken')
      //   VueCookies.remove('refreshToken')
      //   VueCookies.set('auth', false)
      // 로그인 페이지로 리디렉션
      //window.location.href = '/login'

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
