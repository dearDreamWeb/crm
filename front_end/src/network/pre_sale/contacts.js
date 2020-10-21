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
  }
}
