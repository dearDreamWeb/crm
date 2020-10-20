import {postJson,get} from "../request";

export const qaHttp = {
  add:function (qa) {
    let params = {
      url:'/qa/add',
      data:{
        'qaWtproblem':qa.qaWtproblem,
        'qaJjsolve':qa.qaJjsolve,
        'qaCustomerfk':qa.qaCustomerfk,
        'qaData':qa.qaData

      }
    }
    return postJson(params)
  },
  get:function (qaId) {
    let params = {
      url:'/qa',
      data:{
        'qaId':qaId
      }
    }
    return get(params)
  },
  update:function (qa) {
    let params = {
      url:'/qa/update',
      data:{
        'qaId':qa.qaId,
        'qaWtproblem':qa.qaWtproblem,
        'qaJjsolve':qa.qaJjsolve,
        'qaData':qa.qaData,
        'qaCustomerfk':qa.qaCustomerfk

      }
    }
    return postJson(params)
  },
  del:function (qaId) {
    let params = {
      url:'/qa/del',
      data:{
        'qaId':qaId
      }
    }
    return postJson(params)
  },
  list:function (qa) {
    let params = {
      url:'/qa/list',
      data:{
        'qaWtproblem':qa.qaWtproblem,
        'qaJjsolve':qa.qaJjsolve,
        'qaCustomerfk':qa.qaCustomerfk,
        'qaData':qa.qaData
      }
    }
    return get(params)
  },
  queryEmp:function (qa) {
    let params = {
      url:'/qa/list',
      data:{
        'qaWtproblem':qa.qaWtproblem,
        'qaJjsolve':qa.qaJjsolve,
        'qaCustomerfk':qa.qaCustomerfk,
        'qaData':qa.qaData,
        'startDate':qa.startDate,
        'endDate':qa.endDate,
        'pageNum':qa.pageNum,
        'pageSize':qa.pageSize
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/qa/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
