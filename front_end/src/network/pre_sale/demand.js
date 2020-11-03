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
  }
}
