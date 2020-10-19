/*
@author: pengjia
@date: 2020/10/15
@description:
*/
import {postJson,get} from "../request";

export const clueHttp = {
  list:function (clue) {
    let params = {
      url:'/clue/list',
      data:{
        'clueName':clue.clueName,
        'cluePhone':clue.cluePhone,
        'clueStatus':clue.clueStatus,
        'handlePerson':clue.handlePerson,
        'handleResult':clue.handleResult,
        'activityId':clue.activityId,
        'empId':clue.empId,
        'startDate':clue.startDate,
        'endDate':clue.endDate
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/clue/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  add:function (clue) {
    let params = {
      url:'/clue/add',
      data:{
        'clueName':clue.clueName,
        'cluePhone':clue.cluePhone,
        'handlePerson':clue.handlePerson
      }
    }
    return postJson(params)
  },
  get:function (clueId) {
    let params = {
      url:'/clue',
      data:{
        'clueId':clueId
      }
    }
    return get(params)
  },
  getDetail:function(clueId) {
    let params = {
      url: '/clue/detail',
      data:{
        'clueId':clueId
      }
    }
    return get(params)
  },
  edit:function (clue) {
    let params = {
      url:'/clue/edit',
      data:{
        'clueId':clue.clueId,
        'clueName':clue.clueName,
        'cluePhone':clue.cluePhone,
        'handlePerson':clue.handlePerson
      }
    }
    return postJson(params)
  },
  del:function (clueId) {
    let params = {
      url:'/clue/del',
      data:{
        'clueId':clueId
      }
    }
    return postJson(params)
  },
  cluePageAdd:function (clue) {
    let params = {
      url:'/clue/front/add',
      data:{
        'clueName':clue.clueName,
        'cluePhone':clue.cluePhone,
        'activityId':clue.activityId,
        'empId':clue.empId
      }
    }
    return postJson(params)
  }
}