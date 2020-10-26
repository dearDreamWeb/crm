/*
@author: pengjia
@date: 2020/10/26
@description:
*/
import {postJson,get} from "../request";

export const productHttp = {
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/product/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  addProduct:function (product) {
    let params = {
      url:'/product/add',
      data:{
        'productName':product.productName,
        'productImage':product.productImage,
        'productModel':product.productModel,
        'productPrice':product.productPrice,
        'productBrand':product.productBrand,
        'productStock':product.productStock,
        'productSize':product.productSize,
        'productCost':product.productCost,
        'productBarCode':product.productBarCode,
        'remark':product.remark
      }
    }
    return postJson(params)
  },
  getProduct:function (productId) {
    let params = {
      url:'/product',
      data:{
        'productId':productId
      }
    }
    return get(params)
  },
  listAll:function () {
    let params = {
      url:'/product/list'
    }
    return get(params)
  }
}
