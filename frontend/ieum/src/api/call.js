
import axios from 'axios'

const APPLICATION_SERVER_URL = 'http://localhost:8080/'

const createSession = async (sessionId) => {

  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions',
    { customSessionId: sessionId },
    {
      headers: { 'Content-Type': 'application/json' }
    }
  )
  return response.data
}

const createToken = async (sessionId) => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions/connections/' + sessionId,
    {},
    {
      headers: { 'Content-Type': 'application/json' }
    }
  )
  return response.data
}

export {
  createSession,
    createToken
};

