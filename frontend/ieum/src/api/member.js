import { localAxios, localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const local = localAxios()

const localSession = localSessionAxios()

const url = 'http://localhost:8080/api/member'

function register(data, success) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}
function login(data, success) {
  local.post(`${url}/login`, JSON.stringify(data)).then(success).catch(fail)
}

function sendAuth(data, success) {
  local.post(`${url}/auth`, JSON.stringify(data)).then(success).catch(fail)
}

function verify(data, success) {
  local.post(`${url}/verify`, JSON.stringify(data)).then(success).catch(fail)
}

function checkMember(data, success) {
  local.post(`${url}/check-exist`, JSON.stringify(data)).then(success).catch(fail)
}

function editPassword(data, success) {
  local.post(`${url}/password`, JSON.stringify(data)).then(success).catch(fail)
}

// refresh 토큰 과정 필요
function modifyApi(data, success) {
  localSession.put(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function removeApi(param, success) {
  localSession.delete(`${url}/${param}`).then(success).catch(fail)
}

function getInfo(success) {
  localSession.get(`${url}`).then(success).catch(fail)
}

export {
  register,
  login,
  modifyApi,
  removeApi,
  sendAuth,
  checkMember,
  verify,
  editPassword,
  getInfo
}
