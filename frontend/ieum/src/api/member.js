import { localAxios, localAxiosFormData } from "@/util/http-commons";

const local = localAxios();

const form = localAxiosFormData();

const url = "http://localhost:8080/api/member"

function register(param, success, fail) {
  local.get(`${url}`,).then(success).catch(fail);
}
function login(data, success, fail) {
  local.post(`${url}/login`, JSON.stringify(data)).then(success).catch(fail);
}
function modifyApi(data, success, fail) {
  form.put(`${url}`, data).then(success).catch(fail);
}
function removeApi(param, success, fail) {
  local.delete(`${url}/${param}`).then(success).catch(fail);
}

export {
    register,
    login,
    modifyApi,
    removeApi
};
