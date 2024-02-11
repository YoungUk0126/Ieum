import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

const form = localSessionAxiosFormData()

const endpoint = 'i10a303.p.ssafy.io'

const url = `https://${endpoint}:8080/api/pill`

function getAllInject(success) {
  local.get(`${url}`).then(success).catch(fail)
}

function getOneInject(param, success) {
  local.get(`${url}/${param}`).then(success).catch(fail)
}

function postInject(data, success) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifyInject(data, success) {
  form.put(`${url}`, data).then(success).catch(fail)
}
function deleteInject(param, success) {
  local.delete(`${url}/${param}`).then(success).catch(fail)
}

export { getOneInject, getAllInject, postInject, modifyInject, deleteInject }
