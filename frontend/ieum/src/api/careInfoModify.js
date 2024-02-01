import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from './fail.js'

const localSession = localSessionAxios()

const localSessionFormData = localSessionAxiosFormData()

const url = 'http://54.180.108.118:8080/api'

function serialInfoEdit(data, success) {
  localSession.put(`${url}/devices`, JSON.stringify(data)).then(success).catch(fail)
}
function userInfoEdit(data, success) {
  localSession.put(`${url}/care`, JSON.stringify(data)).then(success).catch(fail)
}

function getCareInfo(success) {
  localSession.get(`${url}/care`).then(success).catch(fail)
}

function getSerialNumber(success) {
  localSession.get(`${url}/devices`).then(success).catch(fail)
}

function profileEdit(data, success) {
  localSessionFormData.put(`${url}/profile`, data).then(success).catch(fail)
}

export { serialInfoEdit, userInfoEdit, getCareInfo, getSerialNumber, profileEdit }