import {postJson,get} from "../request";

export const menuHttp = {
  tree:function () {
    let params = {
      url:'/menu/tree'
    }
    return get(params)
  }
}
