/*
@author: pengjia
@date: 2020/10/30
@description:
*/
import {postJson,get} from "../request";

export const followHttp = {
  add:function (followLog) {
    let params = {
      url:'/follow/add',
      data:{
        'followTitle':followLog.followTitle,
        'followCategory':followLog.followCategory,
        'followType':followLog.followType,
        'followStartTime':followLog.followStartTime,
        'followEndTime':followLog.followEndTime,
        'followContent':followLog.followContent,
        'cusId':followLog.cusId,
        'followPid':followLog.followPid
      }
    }
    return postJson(params)
  },
  listPidFollow:function () {
    let params = {
      url:'/follow/list_pid'
    }
    return get(params)
  }
}
