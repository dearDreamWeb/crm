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
  }
}
