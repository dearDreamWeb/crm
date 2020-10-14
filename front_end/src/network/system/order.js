import {postJson,get} from "../request";

export const orderHttp = {
  addOrder:function(order) {
    let params = {
      url:'/order/add',
      data:{
        'ordHead':order.ordHead,
        'ordTotalmoney':order.ordTotalmoney,
        'ordProvince':order.ordProvince,
        'ordCity':order.ordCity,
        'ordCountry':order.ordCountry,
        'ordDetail':order.ordDetail,
        'ordPhone':order.ordPhone
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
  editOrder:function (order) {
    let params = {
      url:'/order/edit',
      data:{
        'ordHead':order.ordHead,
        'ordTotalmoney':order.ordTotalmoney,
        'ordProvince':order.ordProvince,
        'ordCity':order.ordCity,
        'ordCountry':order.ordCountry,
        'ordDetail':order.ordDetail,
        'ordPhone':order.ordPhone
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
        'ordHead':order.ordHead,
        'ordTotalmoney':order.ordTotalmoney,
        'ordProvince':order.ordProvince,
        'ordCity':order.ordCity,
        'ordCountry':order.ordCountry,
        'ordDetail':order.ordDetail,
        'ordPhone':order.ordPhone
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
