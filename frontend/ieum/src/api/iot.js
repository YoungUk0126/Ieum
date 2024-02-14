import { localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const localSession = localSessionAxios()

const url = `https://i10a303.p.ssafy.io:443/api/devices`

function getStatus(success) {
  localSession.get(`${url}/check-status`).then(success).catch(fail)
}

function registerSerial(data, success) {
  localSession.post(`${url}`, JSON.stringify(data)).then(success)
}

export { getStatus, registerSerial }
