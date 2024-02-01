import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'

const localSession = localSessionAxios()

const localSessionFormData = localSessionAxiosFormData()

const url = 'ourdomain/api'

function serialInfoEdit(data, success, fail) {
  localSession.put(`${url}/devices`, JSON.stringify(data)).then(success).catch(fail)
}
function userInfoEdit(data, success, fail) {
  localSession.put(`${url}/care`, JSON.stringify(data)).then(success).catch(fail)
}

function getCareInfo(param, success, fail) {
  localSession.get(`${url}/care/${param}`).then(success).catch(fail)
}

function getSerialNumber(success, fail) {
  localSession.get(`${url}/devices`).then(success).catch(fail)
}
//아직 이음이 시리얼코드 조회가 없어서 임시 조치.

function profileEdit(data, success, fail) {
  localSessionFormData.put(`${url}/profile`, data).then(success).catch(fail)
}

export { serialInfoEdit, userInfoEdit, getCareInfo, getSerialNumber, profileEdit }
