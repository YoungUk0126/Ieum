import { localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const localSession = localSessionAxios()

const url = `https://i10a303.p.ssafy.io:443/api/event`

// refresh 토큰 과정 필요
function getAllList(success) {
  localSession.get(`${url}`).then(success).catch(fail)
}

function getList(year, month, success) {
  localSession.get(`${url}/${year}/${month}`).then(success).catch(fail)
}

function addSchedule(data, success) {
  localSession.post(`${url}`, data).then(success).catch(fail)
}

function modifySchedule(data, success) {
  localSession.put(`${url}`, data).then(success).catch(fail)
}

function deleteSchedule(param, success) {
  localSession.delete(`${url}/${param}`).then(success).catch(fail)
}

export { getAllList, getList, addSchedule, modifySchedule, deleteSchedule }
