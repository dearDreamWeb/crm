import axios from 'axios'
import Qs from 'qs'

export function request(config) {
  //创建axios实例
  const instance = axios.create({
    baseURL : 'http://cxcrm.71baomu.com',
    timeout : 20000
  })
  //请求拦截器
  instance.interceptors.request.use(config => {
    //添加请求头
    const token = window.sessionStorage.getItem('token')
    config.headers.common = {'X-token' : token}
    return config
  },error => {
    console.log(error)
  })
  //响应拦截器
  instance.interceptors.response.use(res => {
    return res.data
  },error => {
    console.log(error)
  })
  //发送请求
  return instance(config)
}

//封装get，post请求
export function get(params) {
  return request({
    url : params.url,
    params : params.data
  })
}

//postJson请求
export function postJson(params) {
  return request({
    headers : {
      'Content-Type':'application/json'
    },
    transformRequest : [function (data) {
      data = JSON.stringify(data)
      return data
    }],
    url : params.url,
    method : 'post',
    data : params.data
  })
}

//post表单请求
export function postForm(params) {
  return request({
    headers : {
      'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
    },
    transformRequest : [function (data) {
      data = Qs.stringify(data)
      return data
    }],
    url : params.url,
    method : 'post',
    data : params.data
  })
}

export function uploadForm(params) {
  return request({
    headers : {
      'enctype':'multipart/form-data'
    },
    url : params.url,
    method : 'post',
    data : params.data
  })
}
