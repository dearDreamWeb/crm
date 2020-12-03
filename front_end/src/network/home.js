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
  }
}
