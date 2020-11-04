/*
@author: pengjia
@date: 2020/10/21
@description:
*/
import {postJson,get} from "../request";

export const contactsHttp = {
  addContacts:function (contacts) {
    let params = {
      url:'/contacts/add',
      data:{
        'contactsName':contacts.contactsName,
        'contactsPhone':contacts.contactsPhone,
        'qq':contacts.qq,
        'wechat':contacts.wechat
      }
    }
    return postJson(params)
  },
  getByCustomerId:function (customerId) {
    let params = {
      url:'/contacts/get_by_cus',
      data:{
        'customerId':customerId
      }
    }
    return get(params)
  },
  list_all:function () {
    let params = {
      url:'/contacts/list'
    }
    return get(params)
  },
  getByCusId:function (cusId) {
    let params = {
      url:'/contacts/list',
      data:{
        'cusId':cusId
      }
    }
    return get(params)
  }
}
