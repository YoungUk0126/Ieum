import { localSessionAxios } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

const endpoint = 'i10a303.p.ssafy.io'

const url = `https://${endpoint}:443/api/meal`

function getMeal(success) {
  local.get(`${url}`).then(success).catch(fail)
}

function postMeal(data, success) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifyMeal(data, success) {
  local.put(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function deleteMeal(param, success) {
  local.delete(`${url}/${param}`).then(success).catch(fail)
}

export { getMeal, postMeal, modifyMeal, deleteMeal }
