import { localAxios, localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const local = localAxios()

const localSession = localSessionAxios()

const url = `https://i10a303.p.ssafy.io:443/api/chat`

function getList(param, success) {
  localSession.get(`${url}`, { params: param }).then(success).catch(fail)
}

export { getList }
