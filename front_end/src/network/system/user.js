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
      url:'/emp',
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
  },
  getMenuList:function () {
    let params = {
      url:'/emp/menu',
    }
    return get(params)
  },
  addEmp:function (emp) {
    let params = {
      url:'/emp/add',
      data:{
        'empName':emp.empName,
        'nickName':emp.nickName,
        'email':emp.email,
        'sex':emp.sex,
        'phone':emp.phone,
        'passWord':emp.passWord,
        'remark':emp.passWord,
        'deptId':emp.deptId
      }
    }
    return postJson(params)
  },
  editEmp:function (emp) {
    let params = {
      url:'/emp/edit',
      data:{
        'empId':emp.empId,
        'empName':emp.empName,
        'nickName':emp.nickName,
        'email':emp.email,
        'phone':emp.phone,
        'sex':emp.sex,
        'empStatus':emp.empStatus,
        'remark':emp.remark,
        'deptId':emp.deptId
      }
    }
    return postJson(params)
  },
  queryEmp:function (emp) {
    let params = {
      url:'/emp/list',
      data:{
        'empName':emp.empName,
        'nickName':emp.nickName,
        'email':emp.email,
        'phone':emp.phone,
        'sex':emp.sex,
        'empStatus':emp.empStatus,
        'deptId':emp.deptId,
        'startDate':emp.startDate,
        'endDate':emp.endDate,
        'pageNum':emp.pageNum,
        'pageSize':emp.pageSize
      }
    }
    return get(params)
  },
  list:function () {
    let params = {
      url:'/emp/list'
    }
    return get(params)
  },
  listNotAuth:function(activityId) {
    let params = {
      url:'/emp/list_not_auth',
      data:{
        'activityId':activityId
      }
    }
    return get(params)
  },
  logout:function (empName) {
    let params = {
      url: '/emp/logout',
      data: {
        'empName': empName
      }
    }
    return postJson(params)
  },
  getPaidan(param){
    let params = {
      url:'/repair/list',
      data:{
        empId: param.empId,
        repairGdstate: param.repairGdstate,
        'pageNum':param.pageNum,
        'pageSize':param.pageSize
      }
    }
    return get(params)
  }
}
