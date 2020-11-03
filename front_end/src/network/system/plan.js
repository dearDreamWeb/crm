import {postJson,get} from "../request";

export const planHttp = {
  addplan:function (plan) {
    let params = {
      url:'/plan/add',
      data:{
        'planTime':plan.planTime,
        'planMoney':plan.planMoney,
        'ordId':plan.ordId,
        'empId':plan.empId
         /*data:plan*/
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
