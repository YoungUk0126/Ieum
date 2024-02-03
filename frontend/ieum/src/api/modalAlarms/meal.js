import { localSessionAxios, localSessionAxiosFormData } from '@/util/http-commons'
import { fail } from '@/api/fail.js'

const local = localSessionAxios()

const form = localSessionAxiosFormData()

const url = 'http://54.180.108.118:8080/api/meal'

function getMeal(param, success) {
  local.get(`${url}/${param}`).then(success).catch(fail)
}

function postMeal(data, success) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail)
}

function modifyMeal(data, success) {
  form.put(`${url}`, data).then(success).catch(fail)
}

function deleteMeal(param, success) {
  local.delete(`${url}/${param}`).then(success).catch(fail)
}

export { getMeal, postMeal, modifyMeal, deleteMeal }
