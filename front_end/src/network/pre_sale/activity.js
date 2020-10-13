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
        'createBy': activity.createBy,
        'startTime': activity.startTime,
        'endTime': activity.endTime,
        'empId': activity.empId,
        'views':activity.views
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
  }
}
