import {postJson,get} from "../request";

export const planHttp = {
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
