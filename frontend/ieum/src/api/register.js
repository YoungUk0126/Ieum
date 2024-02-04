import { localAxios } from '@/util/http-commons'

const local = localAxios()

// const url = 'http://54.180.108.118:8080/api/member'

const url = 'http://localhost:8080/api/member'

function register(data, success, fail) {
  local.post(`${url}/join`, JSON.stringify(data)).then(success).catch(fail)
}
//서버로 넘어감.

function sendVerificationCode(data, success, fail) {
  local.post(`${url}/auth`, JSON.stringify(data)).then(success).catch(fail)
}

function checkVerificationCode(data, success, fail) {
  local.post(`${url}/verify`, JSON.stringify(data)).then(success).catch(fail)
}

function idCheck(param, success, fail) {
  local.get(`${url}/check-id/${param}`).then(success).catch(fail)
}

function emailCheck(data, success, fail) {
  local.post(`${url}/check-email`, JSON.stringify(data)).then(success).catch(fail)
}

function phoneCheck(data, success, fail) {
  local.post(`${url}/check-phone`, JSON.stringify(data)).then(success).catch(fail)
}

export { register, checkVerificationCode, sendVerificationCode, idCheck, emailCheck, phoneCheck }
