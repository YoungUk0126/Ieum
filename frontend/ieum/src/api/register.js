import { localAxios } from '@/util/http-commons'

const local = localAxios()

const url = 'domain'

function register(data, success, fail) {
  local.post(`${url}/api/member/join`, JSON.stringify(data)).then(success).catch(fail)
}

function sendVerificationCode(data, success, fail) {
  local.post(`${url}/api/member/auth`, JSON.stringify(data)).then(success).catch(fail)
}

function checkVerificationCode(data, success, fail) {
  local.post(`${url}/api/member/verify`, JSON.stringify(data)).then(success).catch(fail)
}

function idCheck(param, success, fail) {
  local.get(`${url}/api/member/check-id/${param}`).then(success).catch(fail)
}

function emailCheck(data, success, fail) {
  local.post(`${url}/api/member/check-email`, JSON.stringify(data)).then(success).catch(fail)
}

function phoneCheck(data, success, fail) {
  local.post(`${url}/api/member/check-phone`, JSON.stringify(data)).then(success).catch(fail)
}

export { register, checkVerificationCode, sendVerificationCode, idCheck, emailCheck, phoneCheck }
