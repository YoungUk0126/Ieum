import { localSessionAxiosFormData, localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const local = localSessionAxios()

const form = localSessionAxiosFormData()

const endpoint = 'i10a303.p.ssafy.io'

const url = `http://${endpoint}:8080/api/message`

function getApi(success) {
  local.get(`${url}`).then(success).catch(fail)
}
function registApi(data, success) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}
function modifyApi(data, success) {
  form.put(`${url}`, data).then(success).catch(fail)
}
function removeApi(param, success) {
  local.delete(`${url}/${param}`).then(success).catch(fail)
}

export { getApi, registApi, modifyApi, removeApi }
