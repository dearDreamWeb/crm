import {postJson,get} from "../request";

export const deptHttp = {
  editDept:function(dept) {
    let params = {
      url:'/dept/edit',
      data:{
        'deptId':dept.deptId,
        'deptName':dept.deptName,
        'deptKey':dept.deptKey
      }
    }
    return postJson(params)
  },
  getDept:function(deptId) {
    let params = {
      url:'/dept',
      data:{
        'deptId':deptId
      }
    }
    return get(params)
  },
  del:function(deptId) {
    let params = {
      url:'/dept/del',
      data:{
        'deptId':deptId
      }
    }
    return postJson(params)
  },
  addDept:function(dept) {
    let params = {
      url:'/dept/add',
      data:{
        'deptName':dept.deptName,
        'deptKey':dept.deptKey
      }
    }
    return postJson(params)
  },
  list:function (dept) {
    let params = {
      url:'/dept/list',
      data:{
        'deptName':dept.deptName,
        'deptKey':dept.deptKey,
        'startDate':dept.startDate,
        'endDate':dept.endDate
      }
    }
    return get(params)
  },
  listAll:function () {
    let params = {
      url:'/dept/list_all',
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/dept/list',
      data: {
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
