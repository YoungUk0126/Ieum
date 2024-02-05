import { localAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const local = localAxios()

const url = 'http://i10a303.p.ssafy.io:8080/api/member'

// const url = 'http://localhost:8080/api/member'

function register(data, success) {
  local.post(`${url}/join`, JSON.stringify(data)).then(success).catch(fail)
}
//서버로 넘어감.

function sendVerificationCode(data, success) {
  local.post(`${url}/auth`, JSON.stringify(data)).then(success).catch(fail)
}

function checkVerificationCode(data, success) {
  local.post(`${url}/verify`, JSON.stringify(data)).then(success).catch(fail)
}

function idCheck(param, success) {
  local.get(`${url}/check-id/${param}`).then(success).catch(fail)
}

function emailCheck(data, success) {
  local.post(`${url}/check-email`, JSON.stringify(data)).then(success).catch(fail)
}

function phoneCheck(data, success) {
  local.post(`${url}/check-phone`, JSON.stringify(data)).then(success).catch(fail)
}

export { register, checkVerificationCode, sendVerificationCode, idCheck, emailCheck, phoneCheck }
