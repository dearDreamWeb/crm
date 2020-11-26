/*
@author: pengjia
@date: 2020/10/21
@description:
*/
import {postJson,get} from "../request";

export const customerHttp = {
  addCusAndCon:function(cusCon) {
    let params = {
      url:'/customer/add_cus_con',
      data:{
        'clueId':cusCon.clueId,
        'cusName':cusCon.cusName,
        'abbreviation':cusCon.abbreviation,
        'cusDictSource':cusCon.cusDictSource,
        'cusRemark':cusCon.cusRemark,
        'empId':cusCon.empId,
        'contactsName':cusCon.contactsName,
        'contactsPhone':cusCon.contactsPhone,
        'qq':cusCon.qq,
        'wechat':cusCon.wechat,
        'email':cusCon.email
      }
    }
    return postJson(params)
  },
  insertCusAndCon:function(cusCon) {
    let params = {
      url:'/customer/insert',
      data:{
        'cusName':cusCon.cusName,
        'cusDictSource':cusCon.cusDictSource,
        'cusRemark':cusCon.cusRemark,
        'contactsName':cusCon.contactsName,
        'contactsPhone':cusCon.contactsPhone,
        'email':cusCon.email
      }
    }
    return postJson(params)
  },
  addCustomer:function (customer) {
    let params = {
      url:'/customer/add',
      data:{
        'cusName':customer.cusName,
        'abbreviation':customer.abbreviation,
        'cusDictSource':customer.cusDictSource,
        'cusRemark':customer.cusRemark
      }
    }
    return postJson(params)
  },
  listAll:function () {
    let params = {
      url:'/customer/list_all',
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/customer/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  list:function (customer) {
    let params = {
      url:'/customer/list',
      data:{
        'cusName':customer.cusName,
        'abbreviation':customer.abbreviation,
        'lifeCycle':customer.lifeCycle,
        'sanyGuest':customer.sanyGuest,
        'creditId':customer.creditId,
        'cusDictSource':customer.cusDictSource,
        'cusDictStage':customer.cusDictStage,
        'starSigningDate':customer.starSigningDate,
        'endSigningDate':customer.endSigningDate,
        'startDate':customer.startDate,
        'endDate':customer.endDate,
        'settlement':customer.settlement,
        'empId':customer.empId
      }
    }
    return get(params)
  },
  getCusById:function (cusId) {
    let params = {
      url:'/customer',
      data:{
        'cusId':cusId
      }
    }
    return get(params)
  },
  listCustomerRecord:function (customerRecord) {
    let params = {
      url:'/customer_record/list',
      data:{
        'recordTitle':customerRecord.recordTitle,
        'recordType':customerRecord.recordType,
        'cusId':customerRecord.cusId,
        'empId':customerRecord.empId
      }
    }
    return get(params)
  },
  listRecordById:function (cusId) {
    let params = {
      url:'/customer_record/list',
      data:{
        'cusId':cusId
      }
    }
    return get(params)
  }
}
