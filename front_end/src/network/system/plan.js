import {postJson,get} from "../request";

export const planHttp = {
  addPlan:function (plan) {
    let params = {
      url:'/plan/add',
      data:{
        'careData':plan.careData,
        'planMoney':plan.planMoney,
        'ordId':plan.ordId,
        'empId':plan.empId
      }
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

  list:function (plan) {
    let params = {
      url:'/plan/list_all',
      data:{
        'planMoney':plan.planMoney,
        'planTime':plan.planTime,
        'planPeriod':plan.planPeriod,
        'planCaozuopeople':plan.planCaozuopeople,
        'planCaozuotime':plan.planCaozuotime,
        'planInvoice':plan.planInvoice
      }
    }
    return get(params)
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
