import {postJson,get} from "../request";

export const deliverHttp = {
/*  mx_editProDetail:function(prodetail){
    let params={
      url:'/deliver/mx_editProDetail',
      data:{
        productDetailId:prodetail.productDetailId,
        productDetailState:prodetail.productDetailState
      }
    }
    return postJson(params)
  },*/
  mx_editszDeliver:function(deliver){
    let params={
      url:'/deliver/mx_editszDeliver',
      data:{
        delId:deliver.delId,
        delWuliuid: deliver.delWuliuid,
        delCompany:deliver.delCompany
      }
    }
    return postJson(params)
  },
  add_mingxi:function(deliver){
    let params={
      url:'/deliver/add_mingxi',
      data:deliver
    }
    return postJson(params)
  },

/*  addANDord:function(deliver) {
    let params = {
      url:'/deliver/add',
      data:deliver
    }
    return postJson(params)
  },*/
/*  addord:function(ordId) {
    let params = {
      url:'/deliver/add',
      data:{
        'ordId':ordId,
      }
    }
    return postJson(params)
  },*/
  andall:function () {
    let params = {
      url:'/order/and_all',
      data:{
      }
    }
    return get(params)
  },
/*  addszDeliver:function(deliver) {
    let params = {
      url:'/deliver/add',
      data:{
        'odetId':deliver.odetId,
        'delWuliuid':deliver.delWuliuid,
        'delCompany':deliver.delCompany,
        'delPeople':deliver.delPeople
      }
    }
    return postJson(params)
  },*/
  getszDeliver:function (delId) {
    let params = {
      url:'/deliver',
      data:{
        'delId':delId
      }
    }
    return get(params)
  },
  list:function (deliver) {
    let params = {
      url:'/deliver/list_all',
      data:{
        'delWuliuid':deliver.delWuliuid,
        'delPeople':deliver.delPeople,
        'delDelivertime':deliver.delDelivertime,
        'delState':deliver.delState,
        'startDate':deliver.startDate,
        'endDate':deliver.endDate,
        'ordId':deliver.ordId
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/deliver/list_all',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
