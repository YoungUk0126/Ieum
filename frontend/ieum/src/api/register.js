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
    .get(`${url}/api/member/check-id/${param}`)
    .then((response) => {
      if (response.data && response.data.isDuplicate) {
        fail()
      } else {
        success(response)
      }
    })
    .catch((error) => {
      fail(error)
    })
}

function emailcheck(data, success, fail) {
  local
    .post(`${url}/api/member/check-email`, JSON.stringify(data))
    .then((response) => {
      if (response.data && response.data.isDuplicate) {
        fail()
      } else {
        success(response)
      }
    })
    .catch((error) => {
      fail(error)
    })
}

function phonecheck(data, success, fail) {
  local
    .post(`${url}/api/member/check-phone`, JSON.stringify(data))
    .then((response) => {
      if (response.data && response.data.isDuplicate) {
        fail()
      } else {
        success(response)
      }
    })
    .catch((error) => {
      fail(error)
    })
}

export { register, checkVerificationCode, sendVerificationCode, idcheck, emailcheck, phonecheck }
