/*
@author: pengjia
@date: 2020/11/21
@description:
*/
import {get,postJson} from "../request";

export const offerHttp = {
  add:function (offer) {
    let params = {
      url:'/offer/add',
      data:{
        'offerTheme':offer.offerTheme,
        'offerNumbers':offer.offerNumbers,
        'saleId':offer.saleId,
        'contactsId':offer.contactsId,
        'empId':offer.empId,
        'remark':offer.remark
      }
    }
    return postJson(params)
  },
  list_sale:function (offer) {
    let params = {
      url:'/offer/list',
      data:{
        'saleId':offer.saleId
      }
    }
    return get(params)
  },
  list_by_sale:function (saleId) {
    let params = {
      url:'/offer/list',
      data:{
        'saleId':saleId
      }
    }
    return get(params)
  },
  offer_detail_add:function (offerDetail) {
    let params = {
      url:'/offer_detail/add',
      data:{
        'offerDetailId':offerDetail.offerDetailId,
        'offerDetailCount':offerDetail.offerDetailCount,
        'productName':offerDetail.productName,
        'productBrand':offerDetail.productBrand,
        'productModel':offerDetail.productModel,
        'productPrice':offerDetail.productPrice,
        'amountMoney':offerDetail.amountMoney,
        'remark':offerDetail.remark,
        'offerId':offerDetail.offerId,
        'productId':offerDetail.productId
      }
    }
    return postJson(params)
  },
  get_detail_by_offerId:function (offerId) {
    let params = {
      url:'/offer_detail',
      data:{
        'offerId':offerId
      }
    }
    return get(params)
  },
  getOffer:function (offerId) {
    let params = {
      url:'/offer',
      data:{
        'offerId':offerId
      }
    }
    return get(params)
  },
  del_offer_detail:function (offerDetailId) {
    let params = {
      url:'/offer_detail/del',
      data:{
        'offerDetailId':offerDetailId
      }
    }
    return postJson(params)
  },
  get_offer_detail:function (offerDetailId) {
    let params = {
      url:'/offer_detail/get_detail',
      data:{
        'offerDetailId':offerDetailId
      }
    }
    return get(params)
  },
  listByCus:function (cusId) {
    let params = {
      url:'/offer/list_by_cusId',
      data:{
        'cusId':cusId
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/offer/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
