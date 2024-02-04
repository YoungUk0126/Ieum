import { localAxios, localSessionAxios } from '@/util/http-commons'
import { fail } from './fail.js'

const local = localAxios()

const localSession = localSessionAxios()

const url = 'http://localhost:8080/api/chat'

function getList(param, success) {
  localSession.get(`${url}`, { params: param }).then(success).catch(fail)
}

export { getList }
