import {postJson,get} from "../request";

export const dictHttp = {
  tree:function () {
    let params = {
      url:'/dict/tree'
    }
    return get(params)
  },
  getPidList:function () {
    let params = {
      url:'/dict/get_pid'
    }
    return get(params)
  },
  add:function (dict) {
    let params = {
      url:'/dict/add',
      data:{
        'dictName':dict.dictName,
        'dictCode':dict.dictCode,
        'pid':dict.pid
      }
    }
    return postJson(params)
  },
  get:function (dictId) {
    let params = {
      url:'/dict',
      data:{
        'dictId':dictId
      }
    }
    return get(params)
  },
  edit:function (dict) {
    let params = {
      url:'/dict/edit',
      data:{
        'dictId':dict.dictId,
        'dictName':dict.dictName,
        'dictCode':dict.dictCode,
        'pid':dict.pid
      }
    }
    return postJson(params)
  },
  del:function (dictId) {
    let params = {
      url:'/dict/del',
      data:{
        'dictId':dictId
      }
    }
    return postJson(params)
  },
  list:function (dict) {
    let params = {
      url:'/dict/list',
      data:{
        'dictName':dict.dictName,
        'dictCode':dict.dictCode
      }
    }
    return get(params)
  }
}
