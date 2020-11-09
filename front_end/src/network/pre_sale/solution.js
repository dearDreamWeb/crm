/*
@author: pengjia
@date: 2020/11/6
@description:
*/
import {postJson,get} from "../request";

export const solutionHttp = {
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/solution/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  addSolution:function (solution) {
    let params = {
      url:'/solution/add',
      data:{
        'solutionTitle':solution.solutionTitle,
        'content':solution.content,
        'cusId':solution.cusId,
        'saleId':solution.saleId,
        'demandId':solution.demandId,
        'createTime':solution.createTime
      }
    }
    return postJson(params)
  },
  getSolution:function (solutionId) {
    let params = {
      url:'/solution',
      data:{
        'solutionId':solutionId
      }
    }
    return get(params)
  },
  edit:function (solution) {
    let params = {
      url:'/solution/edit',
      data:{
        'solutionId':solution.solutionId,
        'solutionTitle':solution.solutionTitle,
        'content':solution.content,
        'cusId':solution.cusId,
        'saleId':solution.saleId,
        'demandId':solution.demandId
      }
    }
    return postJson(params)
  },
  del:function (solutionId) {
    let params = {
      url:'/solution/del',
      data:{
        'solutionId':solutionId
      }
    }
    return postJson(params)
  },
  list:function (solution) {
    let params = {
      url:'/solution/list',
      data:{
        'solutionTitle':solution.solutionTitle,
        'cusId':solution.cusId,
        'saleId':solution.saleId,
        'demandId':solution.demandId,
        'startDate':solution.starData,
        'endDate':solution.endDate
      }
    }
    return get(params)
  }
}
