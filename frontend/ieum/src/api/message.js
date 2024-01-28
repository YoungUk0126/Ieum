import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = "http://localhost:8080/api/message"

function getApi(param, success, fail) {
  local.get(`${url}/${param}`,).then(success).catch(fail);
}
function registApi(data, success, fail) {
  local.post(`${url}`, JSON.stringify(data)).then(success).catch(fail);
}
function modifyApi(data, success, fail) {
  local.put(`${url}`, JSON.stringify(data)).then(success).catch(fail);
}
function removeApi(param, success, fail) {
  local.delete(`${url}/${param}`).then(success).catch(fail);
}

export {
    getApi,
    registApi,
    modifyApi,
    removeApi
};
