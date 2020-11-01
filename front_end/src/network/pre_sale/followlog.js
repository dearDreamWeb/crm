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
        'followTypeIcon':followLog.followTypeIcon,
        'followStartTime':followLog.followStartTime,
        'followEndTime':followLog.followEndTime,
        'followContent':followLog.followContent,
        'cusId':followLog.cusId,
        'empId':followLog.empId,
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
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/follow/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  listAllArgs:function (followLog) {
    let params = {
      url:'/follow/list_by_start',
      data:{
        'followTitle':followLog.followTitle,
        'followCategory':followLog.followCategory,
        'followType':followLog.followType,
        'followContent':followLog.followContent,
        'followStartTime':followLog.followStartTime,
        'cusId':followLog.cusId,
        'empId':followLog.empId,
        'followPid':followLog.followPid
      }
    }
    return get(params)
  },
  getFollow:function (followId) {
    let params = {
      url:'/follow',
      data:{
        'followId':followId
      }
    }
    return get(params)
  },
  handleFollow:function (follow) {
    let params = {
      url:'/follow/edit',
      data:{
        'followId':follow.followId,
        'intention':follow.intention
      }
    }
    return postJson(params)
  },
  edit:function (followLog) {
    let params = {
      url:'/follow/edit',
      data:{
        'followId':followLog.followId,
        'followTitle':followLog.followTitle,
        'followCategory':followLog.followCategory,
        'followType':followLog.followType,
        'followContent':followLog.followContent,
        'followStartTime':followLog.followStartTime,
        'cusId':followLog.cusId,
        'followPid':followLog.followPid
      }
    }
    return postJson(params)
  },
  list:function (followLog) {
    let params = {
      url:'/follow/list',
      data:{
        'followTitle':followLog.followTitle,
        'followCategory':followLog.followCategory,
        'followType':followLog.followType,
        'cusId':followLog.cusId,
        'followPid':followLog.followPid,
        'empId':followLog.empId
      }
    }
    return get(params)
  },
  del:function (followId) {
    let params = {
      url:'/follow/del',
      data:{
        'followId':followId
      }
    }
    return postJson(params)
  }
}
