import { localSessionAxios } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

// const form = localSessionAxiosFormData()

// const endpoint = 'i10a303.p.ssafy.io'

const url = `https://i10a303.p.ssafy.io:443/api/sleepMode`

// const url2 = `http://localhost:8080/api/sleepMode`

function getAllSleep(success) {
  local.get(`${url}`).then(success).catch(fail)
}

function postSleep(data, success) {
  local.post(`${url}`, data).then(success).catch(fail)
}

function modifySleep(data, success) {
  local.put(`${url}`, data).then(success).catch(fail)
}
function deleteSleep(sleep_info_no, success) {
  local.delete(`${url}/${sleep_info_no}`).then(success).catch(fail)
}

export { getAllSleep, postSleep, modifySleep, deleteSleep }
