import { localSessionAxios } from '@/util/http-commons'

const localSession = localSessionAxios()

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

function profileEdit(formdata, success, fail) {
  localSession({
    method: 'put',
    url: `${url}/profile`,
    data: formdata,
    headers: {
      'Content-Type': 'multipart/form-data' //해당 요청이 multipart/form-data 형식의 데이터를 전송한다는 것을 서버에 알림.
    }
  })
    .then(success)
    .catch(fail)
}

export { serialInfoEdit, userInfoEdit, getCareInfo, getSerialNumber, profileEdit }
