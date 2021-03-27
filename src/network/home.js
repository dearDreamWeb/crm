/*
@author: pengjia
@date: 2020/12/3
@description:
*/
import {get,postJson} from "./request";

export const homeHttp = {
  getNewClue:function (newClue) {
    let params = {
      url:'/home/new_clue',
      data:{
        'startDate':newClue.startDate,
        'endDate':newClue.endDate
      }
    }
    return get(params)
  },
  getClueCount:function() {
    let params = {
      url:'/home/clue_count'
    }
    return get(params)
  },
  getNewCustomer:function (newCustomer) {
    let params = {
      url:'/home/new_customer',
      data:{
        'startDate':newCustomer.startDate,
        'endDate':newCustomer.endDate
      }
    }
    return get(params)
  },
  getPageView:function () {
    let params = {
      url:'/home/new_page_view'
    }
    return get(params)
  },
  getNewOrder:function (newOrder) {
    let params = {
      url:'/home/new_order',
      data:{
        'startDate':newOrder.startDate,
        'endDate':newOrder.endDate
      }
    }
    return get(params)
  }
}
