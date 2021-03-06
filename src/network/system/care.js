import {postJson,get} from "../request";

export const careHttp = {
  add:function (care) {
    let params = {
      url:'/care/add',
      data:{
        'careZt':care.careZt,
        'carelxrcontacts':care.carelxrcontacts,
        'careData':care.careData,
        'careexecutor':care.careexecutor,
        'carenr':care.carenr,
        'carecustomerfk':care.carecustomerfk,
        'cusId':care.cusId,
        'empId':care.empId
      }
    }
    return postJson(params)
  },
  get:function (careId) {
    let params = {
      url:'/care',
      data:{
        'careId':careId
      }
    }
    return get(params)
  },
  update:function (care) {
    let params = {
      url:'/care/update',
      data:{
        'careId':care.careId,
        'careZt':care.careZt,
        'carelxrcontacts':care.carelxrcontacts,
        'careData':care.careData,
        'careexecutor':care.careexecutor,
        'carenr':care.carenr,
        'carecustomerfk':care.carecustomerfk,
        'cusId':care.cusId,
        'empId':care.empId
      }
    }
    return postJson(params)
  },
  del:function (careId) {
    let params = {
      url:'/care/del',
      data:{
        'careId':careId
      }
    }
    return postJson(params)
  },
  list:function (care) {
    let params = {
      url:'/care/list',
      data:{
        'careZt':care.careZt,
        'carelxrcontacts':care.carelxrcontacts,
        'careData':care.careData,
        'careexecutor':care.careexecutor,
        'carenr':care.carenr,
        'carecustomerfk':care.carecustomerfk,
        'cusId':care.cusId,
        'empId':care.empId

      }
    }
    return get(params)
  },
  queryEmp:function (care) {
    let params = {
      url:'/care/list',
      data:{
        'careZt':care.careZt,
        'carelxrcontacts':care.carelxrcontacts,
        'cusId':care.cusId,
        'empId':care.empId,
        'empStatus':care.empStatus,
        'startDate':care.startDate,
        'endDate':care.endDate,
        'careexecutor':care.careexecutor,
        'carenr':care.carenr,
        'carecustomerfk':care.carecustomerfk,
        'pageNum':care.pageNum,
        'pageSize':care.pageSize
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/care/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
