import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from './fail.js'

const localSession = localSessionAxios()

const localSessionFormData = localSessionAxiosFormData()

const url = 'http://i10a303.p.ssafy.io:8080/api'


function userInfoEdit(data, success) {
  localSession.put(`${url}/care`, data).then(success).catch(fail)
}

function getCareInfo(success) {
  localSession.get(`${url}/care`).then(success).catch(fail)
}

function profileEdit(data, success) {
  localSessionFormData.put(`${url}/care`, data).then(success).catch(fail)
}

function phoneCheck(data, success) {
  localSession.post(`${url}/care/check-phone`, JSON.stringify(data)).then(success).catch(fail)
}

export { userInfoEdit, getCareInfo, profileEdit, phoneCheck }
