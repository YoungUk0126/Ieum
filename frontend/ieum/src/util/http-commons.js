import axios from 'axios'

import VueCookies from 'vue-cookies'

const { VITE_VUE_API_URL } = import.meta.env

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    }
  })
  return instance
}

function localAxiosFormData() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
  return instance
}

function localSessionAxiosFormData() {
  const token = `${VueCookies.get('grantType')} ${VueCookies.get('accessToken')}`
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      'Content-Type': 'multipart/form-data',
      Authorization: token
    }
  })
  return instance
}

function localSessionAxios() {
  const token = `${VueCookies.get('grantType')} ${VueCookies.get('accessToken')}`

  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
      Authorization: token
    }
  })
  return instance
}

function localRefreshAxios() {
  const token = `${VueCookies.get('grantType')} ${VueCookies.get('refreshToken')}`

  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
      'Authorization-refresh': token
    }
  })
  return instance
}

export {
  localAxios,
  localAxiosFormData,
  localSessionAxiosFormData,
  localSessionAxios,
  localRefreshAxios
}

