import {postJson,get} from "../request";

export const deptHttp = {
  list:function (dept) {
    let params = {
      url:'/dept/list',
      data:dept
    }
    return get(params)
  },
  listAll:function () {
    let params = {
      url:'/dept/list_all',
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/dept/list',
      data: {
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
