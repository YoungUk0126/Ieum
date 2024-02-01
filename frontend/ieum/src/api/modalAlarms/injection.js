import { localAxios, localAxiosFormData } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

const form = localSessionAxiosFormData()

const url = 'http://localhost:5173/api/event'

function getAllEvent(care_no, success) {
  local.get(`${url}/list/${care_no}`).then(success).catch(fail)
}

function getOneEvent(event_no, success) {
  local.get(`${url}/detail/${event_no}`).then(success).catch(fail)
}

function postEvent(data, success) {
  form.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifyEvent(data, success) {
  form.put(`${url}`, data).then(success).catch(fail)
}
function deleteEvent(event_no, success) {
  local.delete(`${url}/${event_no}`).then(success).catch(fail)
}

export { getAllEvent, getOneEvent, postEvent, modifyEvent, deleteEvent }
