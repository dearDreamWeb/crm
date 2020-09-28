import {postJson,get} from "../request";

export const userHttp = {
  login:function (user) {
    let params = {
      url:'/emp/login',
      data:{
        'empName':user.empName,
        'passWord':user.passWord
      }
    }
    return postJson(params)
  }
}
