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

  // instance에 response intercepter 추가
  instance.interceptors.response.use(
    // 2xx 의 요청이 올때 처리
    (response) => {
      // 응답 데이터가 있는 작업 수행
      return response
    },
    // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거함
    async (error) => {
      // response 해체
      const {
        config,
        response: { status }
      } = error

      // 토큰이 만료된 요청일 경우
      if (status === 403) {
        const originRequest = config

        await refreshAccessToken()

        // 새로운 토큰으로 이전 요청을 복제하여 다시 시도
        const newToken = `${VueCookies.get('grantType')} ${VueCookies.get('accessToken')}`
        originRequest.headers.Authorization = newToken

        return axios(originRequest)
      }

      // 요청을 수행하기전 config 수행 전에 error
      return Promise.reject(error)
    }
  )

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

  // instance에 response intercepter 추가
  instance.interceptors.response.use(
    // 2xx 의 요청이 올때 처리
    (response) => {
      // 응답 데이터가 있는 작업 수행
      return response
    },
    // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거함
    async (error) => {
      // response 해체
      const {
        config,
        response: { status }
      } = error

      // 토큰이 만료된 요청일 경우
      if (status === 403) {
        const originRequest = config

        await refreshAccessToken()

        // 새로운 토큰으로 이전 요청을 복제하여 다시 시도
        const newToken = `${VueCookies.get('grantType')} ${VueCookies.get('accessToken')}`
        originRequest.headers.Authorization = newToken

        return axios(originRequest)
      }

      // 요청을 수행하기전 config 수행 전에 error
      return Promise.reject(error)
    }
  )

  return instance
}

const url = 'http://localhost:8080/api/member'

// Axios를 사용한 토큰 갱신 로직
async function refreshAccessToken() {
  const reset = localRefreshAxios()

  const data = { refreshToken: VueCookies.get('refreshToken') }
  await reset
    .post(`${url}/refresh`, JSON.stringify(data))
    .then(({ data }) => {
      VueCookies.set('accessToken', data.accessToken)
      VueCookies.set('refreshToken', data.refreshToken)
      VueCookies.set('auth', true)
      return true
    })
    .catch(() => {
      window.location.href = '/login'
      return false
    })
}

export {
  localAxios,
  localAxiosFormData,
  localSessionAxiosFormData,
  localSessionAxios,
  localRefreshAxios
}
