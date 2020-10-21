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
        'cusName':cusCon.cusName,
        'abbreviation':cusCon.abbreviation,
        'cusDictSource':cusCon.cusDictSource,
        'cusRemark':cusCon.cusRemark,
        'empId':cusCon.empId,
        'contactsName':cusCon.contactsName,
        'contactsPhone':cusCon.contactsPhone,
        'qq':cusCon.qq,
        'wechat':cusCon.wechat
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
  }
}
