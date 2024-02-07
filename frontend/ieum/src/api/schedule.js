import { localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const localSession = localSessionAxios()

const url = `https://i10a303.p.ssafy.io:443/api/event`

// refresh 토큰 과정 필요
function getList(year, month, success) {
  localSession.get(`${url}/${year}/${month}`).then(success).catch(fail)
}

export { getList }
