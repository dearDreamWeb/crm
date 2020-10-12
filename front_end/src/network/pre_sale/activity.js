<!--
@author: pengjia
@date: 2020/10/12
@description:
-->
import {postJson,get} from "../request";

export const activityHttp = {
  addActivity:function(activity) {
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
  listPage:function (pageNum,pageSize) {
    let params = {
      url: '/activity/list',
      data: {
        'pageNum': pageNum,
        'pageSize': pageSize
      }
    }
    return get(params)
  }
}
