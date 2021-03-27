import {postJson,get} from "../request";

export const syslogHttp = {
  search:function (syslog) {
    let params = {
      url:'/syslog/list',
      data:{
        'logTitle':syslog.logTitle,
        'userId':syslog.userId,
        'logMethod':syslog.logMethod,
        'logContent':syslog.logContent,
        'startDate':syslog.startDate,
        'endDate':syslog.endDate
      }
    }
    return get(params)
  },
  list:function () {
    let params = {
      url:'/syslog/list'
    }
    return get(params)
  },
  listByEmpId:function (empId) {
    let params = {
      url:'/syslog/list',
      data:{
        'userId':empId
      }
    }
    return get(params)
  }
}
