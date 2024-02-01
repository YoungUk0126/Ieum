import { localAxios, localAxiosFormData } from '@/util/http-commons'

const local = localAxios()

const form = localAxiosFormData()

const url = 'http://localhost:5173/api/sleepMode'

function getAllSleep(care_no, success, fail) {
  local.get(`${url}`).then(success).catch(fail)
}

function postSleep(data, success, fail) {
  form.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifySleep(data, success, fail) {
  form.put(`${url}`, data).then(success).catch(fail)
}
function deleteSleep(sleep_info_no, success, fail) {
  local.delete(`${url}/${sleep_info_no}`).then(success).catch(fail)
}

export { getAllSleep, postSleep, modifySleep, deleteSleep }
