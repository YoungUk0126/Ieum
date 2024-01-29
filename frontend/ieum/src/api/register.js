import { localAxios } from '@/util/http-commons'

const local = localAxios()

const url = 'domain'

function register(data, success, fail) {
  local.post(`${url}/api/member/join`, JSON.stringify(data)).then(success).catch(fail)
}

function sendVerificationCode(data, success, fail) {
  local.post(`${url}/api/member/send`, JSON.stringify(data)).then(success).catch(fail)
}

function checkVerificationCode(data, success, fail) {
  local.post(`${url}/api/member/verify`, JSON.stringify(data)).then(success).catch(fail)
}

function idcheck(param, success, fail) {
  local
    .get(`${url}/api/member/check/${param}`)
    .then((response) => {
      if (response.data && response.data.isDuplicate) {
        fail() // 중복일 경우 fail 호출
      } else {
        success(response) // 중복이 아닐 경우 success 호출
      }
    })
    .catch((error) => {
      fail(error)
    })
}

export { register, checkVerificationCode, sendVerificationCode, idcheck }
