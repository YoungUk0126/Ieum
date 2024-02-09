import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

const form = localSessionAxiosFormData()

const endpoint = 'i10a303.p.ssafy.io'

const url = `http://${endpoint}:8080/api/ani`

function getAllEvent(param, success) {
  local.get(`${url}/list/${param}`).then(success).catch(fail)
}

function getOneEvent(param, success) {
  local.get(`${url}/detail/${param}`).then(success).catch(fail)
}

function postEvent(data, success) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifyEvent(data, success) {
  form.put(`${url}`, data).then(success).catch(fail)
}

function deleteEvent(param, success) {
  local.delete(`${url}/${param}`).then(success).catch(fail)
}

export { getAllEvent, getOneEvent, postEvent, modifyEvent, deleteEvent }
