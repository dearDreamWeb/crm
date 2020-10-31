import {postJson,get} from "../request";

export const orderHttp = {
  addOrder:function(product) {
    console.log(product);
    let params = {
      url:'/order/add_deta',
      data:{
        'product':product,
        /*'ordTheme':product.prod.ordTheme,
        'ordHead':product.prod.ordHead,
        'ordTotalmoney':product.prod.ordTotalmoney,
        'ordConsignee':product.prod.ordConsignee,
        'ordPhone':product.prod.ordPhone,
        'ordProvince':product.prod.ordProvince,
        'ordCity':product.prod.ordCity,
        'ordCountry':product.prod.ordCountry,
        'ordDetail':product.prod.ordDetail,
        "cusId":product.prod.cusId*/
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
        'ordId':order.ordId,
        'ordTheme':order.ordTheme,
        'ordConsignee':order.ordConsignee,
        'ordPhone':order.ordPhone,
        'ordProvince':order.ordProvince,
        'ordCity':order.ordCity,
        'ordCountry':order.ordCountry,
        'ordDetail':order.ordDetail,
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
        'endDate':order.endDate,
        'cusId':order.cusId
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
  addpro:function (id) {
    let params={
      url:'/product/all',
      data:{
        'productId':id
      }
    }
  }
}
