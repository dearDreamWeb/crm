/*
@author: pengjia
@date: 2020/10/17
@description:
*/

import {postJson,get} from "../request";

export const activityDetailHttp = {
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/activity_detail/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  },
  list:function (activityDetail) {
    let params = {
      url:'/activity_detail/list',
      data:{
        'activityId':activityDetail.activityId,
        'empId':activityDetail.empId
      }
    }
    return get(params)
  },
  getEmpByActivityId:function (activityId) {
    let params = {
      url:'/activity_detail/get_emp_by_activity',
      data:{
        'activityId':activityId
      }
    }
    return get(params)
  }
}
