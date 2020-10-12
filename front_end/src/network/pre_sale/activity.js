/*
@author: pengjia
@date: 2020/10/12
@description:
*/
import {postJson,get} from "../request";

export const activityHttp = {
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
        'empId': activity.empId
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
