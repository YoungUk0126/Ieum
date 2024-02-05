import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from './fail.js'

const localSession = localSessionAxios()

const localSessionFormData = localSessionAxiosFormData()

// const url = 'http://54.180.108.118:8080/api'

const url = 'http://localhost:8080/api'
// 로컬로 돌릴 때

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
