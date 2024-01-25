import { localAxios } from '@/util/http-commons'

const local = localAxios()

const url = 'https://9173cf9f-f790-4410-a2a7-d4f9fb06f9f4.mock.pstmn.io/api/member/join'
//나중에 서버 연결되면, api명세서에 있는 주소 적기.

function register(data, success, fail) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}
function example2(data, success, fail) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
  // JSON.stringify(data) 해당 데이터를 JSON 문자열화 시킨다.
}

export { register, example2 }
