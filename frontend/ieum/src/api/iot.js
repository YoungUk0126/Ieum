import {  localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const localSession = localSessionAxios()

const url = `https://i10a303.p.ssafy.io:443/api/devices`

function getStatus(success) {
  localSession.get(`${url}/check-status`).then(success).catch(fail)
}

export { getStatus }
