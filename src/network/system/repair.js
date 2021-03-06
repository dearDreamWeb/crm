import {postJson, get, postForm} from "../request";

export const repairHttp = {
  add:function (repair) {
    let params = {
      url:'/repair/add',
      data:{
        'repairProblem':repair.repairProblem,
        'repairPersonnel':repair.repairPersonnel,
        'repairDate':repair.repairDate,
        'repairAppointment':repair.repairAppointment,
        'repairActual':repair.repairActual,
        'repairGdstae':repair.repairGdstae,
        'repairFault':repair.repairFault,
        'repairHfjl':repair.repairHfjl,
        'repairWxfy':repair.repairWxfy,
        'repairSfzb':repair.repairSfzb,
        'repairLxr':repair.repairLxr,
        'repairsjhm':repair.repairsjhm,
        'cusId':repair.cusId,
        'deptId':repair.deptId,
        'orderId':repair.orderId,
        'productId':repair.productId,
        'empId':repair.empId
      }
    }
    return postJson(params)
  },
  get:function (repairId) {
    let params = {
      url:'/repair',
      data:{
        'repairId':repairId
      }
    }
    return get(params)
  },
  update:function (repair) {
    let params = {
      url:'/repair/update',
      data:{
        'repairId':repair.repairId,
        'repairProblem':repair.repairProblem,
        'repairPersonnel':repair.repairPersonnel,
        'repairDate':repair.repairDate,
        'repairAppointment':repair.repairAppointment,
        'repairActual':repair.repairActual,
        'repairGdstae':repair.repairGdstae,
        'repairFault':repair.repairFault,
        'repairHfjl':repair.repairHfjl,
        'repairWxfy':repair.repairWxfy,
        'repairSfzb':repair.repairSfzb,
        'repairLxr':repair.repairLxr,
        'repairsjhm':repair.repairsjhm,
        'cusId':repair.cusId,
        'orderId':repair.orderId,
        'deptId':repair.deptId,
        'productId':repair.productId,
        'empId':repair.empId
      }
    }
    return postJson(params)
  },
  del:function (repairId) {
    let params = {
      url:'/repair/del',
      data:{
        'repairId':repairId
      }
    }
    return postJson(params)
  },
  list:function (repair) {
    let params = {
      url:'/repair/list',
      data:{
        'repairProblem':repair.repairProblem,
        'repairPersonnel':repair.repairPersonnel,
        'repairDate':repair.repairDate,
        'repairAppointment':repair.repairAppointment,
        'repairActual':repair.repairActual,
        'repairsjhm':repair.repairsjhm,
        'repairGdstate':repair.repairGdstate,
        'cusId':repair.cusId,
        'deptId':repair.deptId,
        'orderId':repair.orderId,
        'productId':repair.productId,
        'empId':repair.empId

      }
    }
    return get(params)
  },
  queryEmp:function (repair) {
    let params = {
      url:'/repair/list',
      data:{
        'empId':repair.empId,
        'repairProblem':repair.repairProblem,
        'repairPersonnel':repair.repairPersonnel,
        'repairAppointment':repair.repairAppointment,
        'repairActual':repair.repairActual,
        'repairGdstae':repair.repairGdstae,
        'repairFault':repair.repairFault,
        'repairHfjl':repair.repairHfjl,
        'repairWxfy':repair.repairWxfy,
        'repairSfzb':repair.repairSfzb,
        'cusId':repair.cusId,
        'deptId':repair.deptId,
        'productId':repair.productId,
        'orderId':repair.orderId,
        'dictId':repair.dictId,
        'pageNum':repair.pageNum,
        'pageSize':repair.pageSize
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/repair/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  updatePaidan:function(repair){
    let params ={
      url: '/repair/update',
      data:{
        'repairHfjl':repair.repairHfjl,
        'repairGdstate': repair.repairGdstate,
        'repairId': repair.repairId,
        'empId': repair.empId,
        'repairProblem':repair.repairProblem
      }
    }
    return postJson(params)
  },
  updatefuk:function(repair){
    let params ={
      url: '/repair/updatefuk',
      data:{
        'repairId':repair.repairId,
        'repairHfjl':repair.repairHfjl,
        'repairWxfy':repair.repairWxfy,
      }
    }
    return postJson(params)
  },
  updateQuxiao:function(repair){
    let params ={
      url: '/repair/updateQuxiao',
      data:{
        'repairId': repair.repairId,
      }
    }
    return postJson(params)
  }
}
