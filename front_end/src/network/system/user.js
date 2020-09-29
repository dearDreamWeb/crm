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
  },
  getEmp:function (empId) {
    let params = {
      url:'/',
      data:{
        'empId':empId
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/emp/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  del:function (empId) {
    let params = {
      url:'/emp/del',
      data:{
        'empId':empId
      }
    }
    return postJson(params)
  }
}
