import {postJson,get} from "../request";

export const planHttp = {
  addplan:function (plan) {
    let params = {
      url:'/plan/add',
      data:plan
      /*data:{
        'planTime':plan.planTime,
        'planMoney':plan.planMoney,
        'ordId':plan.ordId,
        'empId':plan.empId
      }*/
    }
    return postJson(params)
  },
  delPlan:function(planId) {
    let params = {
      url:'/plan/del',
      data:{
        'planId':planId
      }
    }
    return postJson(params)
  },
  editplan:function(plan){
    let params={
      url:'/plan/edit',
      data:{
        'planId':plan.planId,
        'planTime':plan.planTime,
        'planPeriod':plan.planPeriod,
        'planCaozuopeople':plan.planCaozuopeople,
        'ordId':plan.ordId,
        'empId':plan.empId
      }
    }
    return postJson(params)
  },
  getplan:function (planId) {
    let params = {
      url:'/plan',
      data:{
        'planId':planId
      }
    }
    return get(params)
  },
  list:function (plan) {
    let params = {
      url:'/plan/list_all',
      data:{
        'planMoney':plan.planMoney,
        'planTime':plan.planTime,
        'planPeriod':plan.planPeriod,
        'planCaozuopeople':plan.planCaozuopeople,
        'planCaozuotime':plan.planCaozuotime,
        'planInvoice':plan.planInvoice,
        'ordId':plan.ordId
      }
    }
    return get(params)
  },
  chakan_record:function (id) {
    let params = {
      url:'/record/all',
      data:{
        'recoId':id
      }
    }
    return get(params)
  },
  getrecord:function (recoId) {
    let params = {
      url:'/record',
      data:{
        'recoId':recoId
      }
    }
    return get(params)
  },
  editrecordhas:function (record) {
    let params = {
      url:'/record/edit_has',
      data:{
        'recoId':record.recoId,
        'moneyPlan':record.moneyPlan,
        'timePlan':record.timePlan,
        'recordPlan':record.recordPlan,
        'recoMoney':record.recoMoney,
        'recoLiushui':record.recoLiushui,
        'recoTime':record.recoTime,
      }
    }
    return postJson(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/plan/list_all',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
