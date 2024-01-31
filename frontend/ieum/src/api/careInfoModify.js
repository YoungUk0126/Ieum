import { localAxios } from '@/util/http-commons'

const local = localAxios()

const url = 'domain'

function serialInfoEdit(data, success, fail) {
  local.put(`${url}/api/devices`, JSON.stringify(data)).then(success).catch(fail)
}
function userInfoEdit(data, success, fail) {
  local.put(`${url}/api/care`, JSON.stringify(data)).then(success).catch(fail)
}

function getCareInfo(param, success, fail) {
  local.get(`${url}/api/care/{care-no}`, { params: param }).then(success).catch(fail)
}

function getSerialNumber(param, success, fail) {
  local.get(`${url}`, { params: param }).then(success).catch(fail)
}

function profileEdit(formdata, success, fail) {
  local({
    method: 'put',
    url: '우리도메인주소+api명세주소',
    data: formdata,
    headers: {
      'Content-Type': 'multipart/form-data' //해당 요청이 multipart/form-data 형식의 데이터를 전송한다는 것을 서버에 알림.
    }
  })
    .then(success)
    .catch(fail)
}

export { serialInfoEdit, userInfoEdit, getCareInfo, getSerialNumber, profileEdit }
