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
