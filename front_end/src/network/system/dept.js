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
  }
}
