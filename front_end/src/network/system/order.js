import {postJson,get} from "../request";

export const orderHttp = {
  addOrder:function(order) {
    let params = {
      url:'/order/add',
      data:{
        'ordTheme':order.ordTheme,
        'ordHead':order.ordHead,
        'ordTotalmoney':order.ordTotalmoney,
        'ordConsignee':order.ordConsignee,
        'ordPhone':order.ordPhone,
        'ordProvince':order.ordProvince,
        'ordCity':order.ordCity,
        'ordCountry':order.ordCountry,
        'ordDetail':order.ordDetail,
        'ordStarttime':order.ordStarttime,
        'ordState':order.ordState
      }
    }
    return postJson(params)
  },
  delOrder:function(ordId) {
    let params = {
      url:'/order/del',
      data:{
        'ordId':ordId
      }
    }
    return postJson(params)
  },
  szxiangq:function (id) {
    let params = {
      url:'/details/all',
      data:{
        'odetId':id
      }
    }
    return get(params)
  },
  editOrder:function (order) {
    let params = {
      url:'/order/edit',
      data:{
        'ordId':order.ordId,
        'ordTheme':order.ordTheme,
        'ordHead':order.ordHead,
        'ordTotalmoney':order.ordTotalmoney,
        'ordState':order.ordState,
        'ordStarttime':order.ordStarttime
      }
    }
    return postJson(params)
  },
  getOrder:function (ordId) {
    let params = {
      url:'/order',
      data:{
        'ordId':ordId
      }
    }
    return get(params)
  },
  list:function (order) {
    let params = {
      url:'/order/list_all',
      data:{
        'ordTheme':order.ordTheme,
        'ordHead':order.ordHead,
        'ordTotalmoney':order.ordTotalmoney,
        /*'ordStarttime':order.ordStarttime,*/
        'ordState':order.ordState,
        'startDate':order.startDate,
        'endDate':order.endDate
      }
    }
    return get(params)
  },

  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/order/list_all',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
