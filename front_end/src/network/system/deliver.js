import {postJson,get} from "../request";

export const deliverHttp = {
  addszDeliver:function(deliver) {
    let params = {
      url:'/deliver/add',
      data:{

      }
    }
    return postJson(params)
  },
}
