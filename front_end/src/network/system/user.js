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
      url:'/emp/dict_list',
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
        'startDate':emp.startDate,
        'endDate':emp.endDate,
        'pageNum':emp.pageNum,
        'pageSize':emp.pageSize
      }
    }
    return get(params)
  }
}
