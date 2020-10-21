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

  getszDeliver:function (delId) {
    let params = {
      url:'/deliver',
      data:{
        'delId':delId
      }
    }
    return get(params)
  },
  list:function (deliver) {
    let params = {
      url:'/deliver/list_all',
      data:{
        'delWuliuid':deliver.delWuliuid,
        'delPeople':deliver.delPeople,
        'delDelivertime':deliver.delDelivertime,
        'delState':deliver.delState,
        'startDate':deliver.startDate,
        'endDate':deliver.endDate,
        'ordId':deliver.ordId
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/deliver/list_all',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
