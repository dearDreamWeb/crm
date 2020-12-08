import {postJson,get} from "../request";

export const planHttp = {
  editOrdState:function(order){
    let params={
      url:'/order/editOrdState',
      data:order
    }
    return postJson(params)
  },
/*修改回款计划的状态*/
  editPlanInvoice:function(plan){
    let params={
      url:'/plan/editPlanInvoice',
      data:plan
    }
    return postJson(params)
  },
  plan_editOrder:function(plan){
    let params={
      url:'/plan/plan_editOrder',
        data:{
        ordId:plan.ordId
        }
    }
    return postJson(params)
  },
  addplan:function (plan) {
    let params = {
      url:'/plan/add',
      data:plan
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
  editrecordhas2:function (record) {
    let params = {
      url:'/record/edit_has2',
      data:{
        'recoId':record.recoId,
        'moneyPlan':record.moneyPlan,
        'timePlan':record.timePlan,
        'recordPlan':record.recordPlan,
        'recoMoney':record.recoMoney,
        'recoLiushui':record.recoLiushui,
        'recoTime':record.recoTime,
        'planId':record.planId
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
