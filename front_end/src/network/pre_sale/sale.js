/*
@author: pengjia
@date: 2020/11/3
@description:
*/
import {postJson,get} from "../request";

export const saleHttp = {
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/sale/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  add:function (sale) {
    let params = {
      url:'/sale/add',
      data:{
        'saleName':sale.saleName,
        'saleStatus':sale.saleStatus,
        'saleType':sale.saleType,
        'discoveryTime':sale.discoveryTime,
        'cusId':sale.cusId,
        'contactsId':sale.contactsId,
        'empId':sale.empId
      }
    }
    return postJson(params)
  },
  addSaleAndDetail:function (saleDetailDemand) {
    let params = {
      url:'/sale/add_reunite',
      data:{
        'saleName':saleDetailDemand.saleName,
        'saleStatus':saleDetailDemand.saleStatus,
        'saleType':saleDetailDemand.saleType,
        'discoveryTime':saleDetailDemand.discoveryTime,
        'cusId':saleDetailDemand.cusId,
        'contactsId':saleDetailDemand.contactsId,
        'saleStarBeacon':saleDetailDemand.saleStarBeacon,
        'salePriorLevel':saleDetailDemand.salePriorLevel,
        'demandContent':saleDetailDemand.demandContent
      }
    }
    return postJson(params)
  },
  list_all:function () {
    let params = {
      url:'/sale/list'
    }
    return get(params)
  },
  getByCusId:function (cusId) {
    let params = {
      url:'/sale/list',
      data:{
        'cusId':cusId
      }
    }
    return get(params)
  },
  search_sale:function (sale) {
    let params = {
      url:'/sale/list',
      data:{
        'saleName':sale.saleName,
        'saleStatus':sale.saleStatus,
        'saleType':sale.saleType,
        'cusId':sale.cusId,
        'empId':sale.empId,
        'salePriorLevel':sale.salePriorLevel,
        'saleStage':sale.saleStage,
        'saleStarBeacon':sale.saleStarBeacon
      }
    }
    return get(params)
  },
  del:function (saleId) {
    let params = {
      url:'/sale/del',
      data:{
        'saleId':saleId
      }
    }
    return postJson(params)
  },
  get_by_id:function (saleId) {
    let params = {
      url:'/sale',
      data:{
        'saleId':saleId
      }
    }
    return get(params)
  },
  editSaleAndDetail:function (sale) {
    let params = {
      url:'/sale/sale_edit',
      data:{
        'saleId':sale.saleId,
        'saleName':sale.saleName,
        'saleType':sale.saleType,
        'saleStarBeacon':sale.saleStarBeacon,
        'salePriorLevel':sale.salePriorLevel,
        'saleEstimateDate':sale.saleEstimateDate,
        'saleExpectMoney':sale.saleExpectMoney,
        'salePossibility':sale.salePossibility,
        'saleStage':sale.saleStage
      }
    }
    return postJson(params)
  },
  selectAll:function (cusId) {
    let params = {
      url:'/sale/select_all',
      data:{
        'cusId':cusId
      }
    }
    return get(params)
  }
}
