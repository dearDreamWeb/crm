/*
@author: pengjia
@date: 2020/10/25
@description:
*/
import {postJson,get} from "../request";

export const creditHttp = {
  noArgsList:function () {
    let params = {
      url:'/sys_credit/list'
    }
    return get(params)
  }
}
