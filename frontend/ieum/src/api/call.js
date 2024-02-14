import { localAxios, localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'
const url = `https://i10a303.p.ssafy.io:443`

const localSession = localSessionAxios()

const local = localAxios()

// 피보호자 접속시 시리얼번호
const createSessionCare = async (serialNo) => {
  const response = await local.post(
    `${url}/api/sessions/care`,
    JSON.stringify({ customSessionId: serialNo })
  )
  return response.data
}

const createTokenCare = async (sessionId) => {
  const response = await local.post(`${url}/api/sessions/care/connections/${sessionId}`).catch(fail)
  return response.data
}

// 보호자 세션 접속
const createSession = async () => {
  const response = await localSession.post(`${url}/api/sessions`).catch(fail)
  return response.data
}

const createToken = async (sessionId) => {
  const response = await localSession
    .post(`${url}/api/sessions/connections/${sessionId}`)
    .catch(fail)
  return response.data
}

const sendEndAlert = () => {
  localSession.get(`${url}/api/devices/closeAlert`).catch(fail)
}

export { createSession, createToken, createSessionCare, createTokenCare, sendEndAlert }
