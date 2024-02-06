import { localAxios, localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const local = localAxios()

const localSession = localSessionAxios()

const endpoint = 'i10a303.p.ssafy.io'

const url = `http://${endpoint}:8080/api/chat`

function getList(param, success) {
  localSession.get(`${url}`, { params: param }).then(success).catch(fail)
}

export { getList }
