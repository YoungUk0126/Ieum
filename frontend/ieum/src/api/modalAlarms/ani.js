import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

const form = localSessionAxiosFormData()

const endpoint = 'i10a303.p.ssafy.io'

const url = `https://${endpoint}:443/api/ani`

function getAllAni(success) {
  local.get(`${url}`).then(success).catch(fail)
}

function getOneAni(param, success) {
  local.get(`${url}/detail/${param}`).then(success).catch(fail)
}

function postAni(data, success) {
  console.log(url)
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifyAni(data, success) {
  form.put(`${url}`, data).then(success).catch(fail)
}

function deleteAni(param, success) {
  local.delete(`${url}/${param}`).then(success).catch(fail)
}

export { getAllAni, getOneAni, postAni, modifyAni, deleteAni }
