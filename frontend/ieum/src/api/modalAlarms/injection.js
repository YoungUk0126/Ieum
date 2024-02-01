import { localAxios, localAxiosFormData } from '@/util/http-commons'

const local = localAxios()

const form = localAxiosFormData()

const url = 'http://localhost:5173/api/event'

function getAllEvent(care_no, success, fail) {
  local.get(`${url}/list/${care_no}`).then(success).catch(fail)
}

function getOneEvent(event_no, success, fail) {
  local.get(`${url}/detail/${event_no}`).then(success).catch(fail)
}

function postEvent(data, success, fail) {
  form.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifyEvent(data, success, fail) {
  form.put(`${url}`, data).then(success).catch(fail)
}
function deleteEvent(event_no, success, fail) {
  local.delete(`${url}/${event_no}`).then(success).catch(fail)
}

export { getAllEvent, getOneEvent, postEvent, modifyEvent, deleteEvent }
