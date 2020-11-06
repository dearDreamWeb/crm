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
  }
}
