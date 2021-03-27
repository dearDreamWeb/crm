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
        'productId':product.productId,
        'productName':product.productName,
        'productImage':product.productImage,
        'productModel':product.productModel,
        'productPrice':product.productPrice,
        'productBrand':product.productBrand,
        'productStock':product.productStock,
        'productSize':product.productSize,
        'productCost':product.productCost,
        'productCount':product.productCount,
        'remark':product.remark
      }
    }
    return postJson(params)
  },
  getProduct:function (productId) {
    let params = {
      url:'/product_detail/get_by_productId',
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
  },
  getProductByName:function (productName) {
    let params = {
      url:'/product/get_by_name',
      data:{
        'productName':productName
      }
    }
    return get(params)
  },
  list:function (product) {
    let params = {
      url:'/product/list',
      data:{
        'productName':product.productName,
        'productBrand':product.productBrand,
        'productModel':product.productModel,
        'productSize':product.productSize,
        'productStartCost':product.productStartCost,
        'productEndCost':product.productEndCost,
        'productStartPrice':product.productStartPrice,
        'productEndPrice':product.productEndPrice,
        'productStatus':product.productStatus,
        'startDate':product.startDate,
        'endDate':product.endDate,
        'pageNum':product.pageNum,
        'pageSize':product.pageSize
      }
    }
    return get(params)
  },
  listDialog:function (product) {
    let params = {
      url:'/product/list',
      data:{
        'pageNum':product.pageNum,
        'pageSize':product.pageSize
      }
    }
    return get(params)
  },
  edit:function (product) {
    let params = {
      url:'/product/edit',
      data:{
        'productId':product.productId,
        'productName':product.productName,
        'productBrand':product.productBrand,
        'productModel':product.productModel,
        'productSize':product.productSize,
        'productPrice':product.productPrice,
        'productCost':product.productCost,
        'productStatus':product.productStatus,
        'remark':product.remark
      }
    }
    return postJson(params)
  },
  del:function (productId) {
    let params = {
      url:'/product/del',
      data:{
        'productId':productId
      }
    }
    return postJson(params)
  }
}
