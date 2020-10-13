/*
@author: pengjia
@date: 2020/10/12
@description:
*/
import {postJson,get} from "../request";

export const activityHttp = {
  del:function(activityId) {
    let params = {
      url:'/activity/del',
      data:{
        'activityId':activityId
      }
    }
    return postJson(params)
  },
  getActivity:function(activityId) {
    let params = {
      url:'/activity',
      data:{
        'activityId':activityId
      }
    }
    return get(params)
  },
  edit:function(activity) {
    let params = {
      url:'/activity/edit',
      data:{
        'activityId':activity.activityId,
        'activityTitle': activity.activityTitle,
        'content': activity.content,
        'activityLink': activity.activityLink,
        'startTime': activity.startTime,
        'endTime': activity.endTime
      }
    }
    return postJson(params)
  },
  add:function(activity) {
    let params = {
      url: '/activity/add',
      data: {
        'activityTitle': activity.activityTitle,
        'content': activity.content,
        'activityLink': activity.activityLink,
        'views':activity.views,
        'createBy': activity.createBy,
        'startTime': activity.startTime,
        'endTime': activity.endTime,
        'empId': activity.empId,
      }
    }
    return postJson(params)
  },
  list:function (activity) {
    let params = {
      url: '/activity/list',
      data: {
        'activityTitle': activity.activityTitle,
        'createBy': activity.createBy
      }
    }
    return get(params)
  },
  listNotAuth:function () {
    let params = {
      url: '/activity/list_not_auth'
    }
    return get(params)
  },
  batchEdit:function (activity) {
    let params = {
      url:'/activity/batch_edit',
      data:{
        'empId':activity.empId,
        'activityIdList':activity.activityIdList
      }
    }
    return postJson(params)
  },
  getActivityListByEmpId:function (empId) {
    let params = {
      url:'/activity/get_by_emp',
      data:{
        'empId':empId
      }
    }
    return get(params)
  }
}
