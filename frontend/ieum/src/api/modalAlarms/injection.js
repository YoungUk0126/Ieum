import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

const form = localSessionAxiosFormData()

const url = 'http://54.180.108.118:8080/api/pill'

function getAllInject(param, success) {
  local.get(`${url}/${param}`).then(success).catch(fail)
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
