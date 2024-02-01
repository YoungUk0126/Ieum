import swal from 'sweetalert'

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
    }).then(() => {
      window.location.href = '/login'
    })
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

export { fail }
