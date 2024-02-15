import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

const form = localSessionAxiosFormData()

const endpoint = 'i10a303.p.ssafy.io'

const url = `https://${endpoint}:443/api/event`

function getAllEvent(success) {
  local.get(`${url}`).then(success).catch(fail)
}

function postEvent(data, success) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifyEvent(data, success) {
  form.put(`${url}`, data).then(success).catch(fail)
}
function deleteEvent(sleep_info_no, success) {
  local.delete(`${url}/${sleep_info_no}`).then(success).catch(fail)
}

export { getAllEvent, postEvent, modifyEvent, deleteEvent }
