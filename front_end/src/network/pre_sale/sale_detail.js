/*
@author: pengjia
@date: 2020/11/3
@description:
*/

import {postJson,get} from "../request";

export const saleDetailHttp = {
  add:function (saleDetail) {
    let params = {
      url:'/sale_detail/add',
      data:{
        'saleEstimateDate':saleDetail.saleEstimateDate,
        'salePossibility':saleDetail.salePossibility,
        'saleExpectMoney':saleDetail.saleExpectMoney,
        'saleStarBeacon':saleDetail.saleStarBeacon,
        'salePriorLevel':saleDetail.salePriorLevel,
        'saleStage':saleDetail.saleStage,
        'saleStageRemark':saleDetail.saleStageRemark,
        'saleId':saleDetail.saleId
      }
    }
    return postJson(params)
  }
}
