import {postJson,get} from "../request";

export const syslogHttp = {
  login:function (syslog) {
    let params = {
      url:'/syslog/login',
      data:{

      }
    }
    return postJson(params)
  },
  getEmp:function (logId) {
    let params = {
      url:'/syslog',
      data:{
        'logId':logId
      }
    }
    return get(params)
  },

  queryEmp:function (syslog) {
    let params = {
      url:'/syslog/list',
      data:{
        'logTitle':syslog.logTitle,
        'userId':syslog.userId,
        'logMethod':syslog.logMethod,
        'logContent':syslog.logContent,
        'logIp':syslog.logIp,
        'logUri':syslog.logUri,
        'createTime':syslog.createTime,
      }
    }
    return get(params)
  },
  list:function () {
    let params = {
      url:'/syslog/list'
    }
    return get(params)
  }
}
