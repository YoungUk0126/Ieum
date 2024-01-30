import { localAxios, localAxiosFormData } from "@/util/http-commons";
import swal from 'sweetalert'

const local = localAxios();

const form = localAxiosFormData();

const url = "http://localhost:8080/api/member"

const fail = ({response}) =>{
  if(response.status == 500){
    swal({
      title: '오류',
      text: '서버 오류!',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          visible: true,
          className: '',
          closeModal: true
        }
      }
    })
  }else if(response.status == 400){
    swal({
      title: '알림',
      text: '잘못된 로그인 정보입니다',
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          visible: true,
          className: '',
          closeModal: true
        }
      }
    })
  }else{
    swal({
      title: '버그',
      text: '뭔 오류일까요..' + response.status,
      icon: 'error',
      buttons: {
        confirm: {
          text: '확인',
          visible: true,
          className: '',
          closeModal: true
        }
      }
    })
  }
}

function register(param, success) {
  local.get(`${url}`,).then(success).catch(fail);
}
function login(data, success) {
  local.post(`${url}/login`, JSON.stringify(data)).then(success).catch(fail);
}
function modifyApi(data, success) {
  form.put(`${url}`, data).then(success).catch(fail);
}
function removeApi(param, success) {
  local.delete(`${url}/${param}`).then(success).catch(fail);
}

export {
    register,
    login,
    modifyApi,
    removeApi
};
