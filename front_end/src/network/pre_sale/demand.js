/*
@author: pengjia
@date: 2020/11/3
@description:
*/
import {postJson,get} from "../request";

export const demandHttp = {
  add:function (demand) {
    let params = {
      url:'/demand/add',
      data:{
        'demandTitle':demand.demandTitle,
        'demandDegree':demand.demandDegree,
        'demandContent':demand.demandContent,
        'isSolve':demand.isSolve,
        'contactsId':demand.contactsId,
        'saleId':demand.saleId,
        'empId':demand.empId,
        'cusId':demand.cusId
      }
    }
    return postJson(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/demand/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  search:function (demand) {
    let params = {
      url:'/demand/list',
      data:{
        'demandTitle':demand.demandTitle,
        'demandDegree':demand.demandDegree,
        'isSolve':demand.isSolve,
        'contactsId':demand.contactsId,
        'saleId':demand.saleId,
        'empId':demand.empId,
        'cusId':demand.cusId
      }
    }
    return get(params)
  },
  getById:function (demandId) {
    let params = {
      url:'/demand',
      data:{
        'demandId':demandId
      }
    }
    return get(params)
  },
  editDemand:function (demand) {
    let params = {
      url:'/demand/edit',
      data:{
        'demandId':demand.demandId,
        'demandTitle':demand.demandTitle,
        'demandDegree':demand.demandDegree,
        'demandContent':demand.demandContent
      }
    }
    return postJson(params)
  },
  delDemand:function (demandId) {
    let params = {
      url:'/demand/del',
      data:{
        'demandId':demandId
      }
    }
    return postJson(params)
  },
  getBySaleId:function (saleId) {
    let params = {
      url:'/demand/get_by_saleId',
      data:{
        'saleId':saleId
      }
    }
    return get(params)
  },
  listAll:function () {
    let params = {
      url:'/demand/list'
    }
    return get(params)
  },
  list_by_cus:function (cusId) {
    let params = {
      url:'/demand/list_by_cus',
      data:{
        'cusId':cusId
      }
    }
    return get(params)
  }
}
