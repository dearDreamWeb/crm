import {postJson,get} from "../request";

export const customerkfHttp = {
  add:function (customerkf) {
    let params = {
      url:'/customerkf/add',
      data:{
        'customerTheme':customerkf.customerTheme,
        'customerServicelx':customerkf.customerServicelx,
        'customerServicefs':customerkf.customerServicefs,
        'customerData':customerkf.customerData,
        'customerKhcontacts':customerkf.customerKhcontacts,
        'customerState':customerkf.customerState,
        'customerExecutor':customerkf.customerExecutor,
        'customerServicenr':customerkf.customerServicenr,
        'customerCustomerfk':customerkf.customerCustomerfk,
        'cusId':customerkf.cusId,
        'empId':customerkf.empId
      }
    }
    return postJson(params)
  },
  get:function (customerId) {
    let params = {
      url:'/customerkf',
      data:{
        'customerId':customerId
      }
    }
    return get(params)
  },
  update:function (customerkf) {
    let params = {
      url:'/customerkf/update',
      data:{
        'customerId':customerkf.customerId,
        'customerTheme':customerkf.customerTheme,
        'customerServicelx':customerkf.customerServicelx,
        'customerServicefs':customerkf.customerServicefs,
        'customerData':customerkf.customerData,
        'customerKhcontacts':customerkf.customerKhcontacts,
        'customerState':customerkf.customerState,
        'customerExecutor':customerkf.customerExecutor,
        'customerServicenr':customerkf.customerServicenr,
        'customerCustomerfk':customerkf.customerCustomerfk,
        'cusId':customerkf.cusId,
        'empId':customerkf.empId
      }
    }
    return postJson(params)
  },
  del:function (customerId) {
    let params = {
      url:'/customerkf/del',
      data:{
        'customerId':customerId
      }
    }
    return postJson(params)
  },
  list:function (customerkf) {
    let params = {
      url:'/customerkf/list',
      data:{
        'customerTheme':customerkf.customerTheme,
        'customerServicelx':customerkf.customerServicelx,
        'customerServicefs':customerkf.customerServicefs,
        'customerData':customerkf.customerData,
        'customerKhcontacts':customerkf.customerKhcontacts,
        'customerState':customerkf.customerState,
        'customerExecutor':customerkf.customerExecutor,
        'customerServicenr':customerkf.customerServicenr,
        'customerCustomerfk':customerkf.customerCustomerfk,
        'cusId':customerkf.cusId,
        'empId':customerkf.empId
      }
    }
    return get(params)
  },
  queryEmp:function (customerkf) {
    let params = {
      url:'/customerkf/list',
      data:{
        'customerTheme':customerkf.customerTheme,
        'customerServicelx':customerkf.customerServicelx,
        'customerServicefs':customerkf.customerServicefs,
        'cusId':customerkf.cusId,
        'empId':customerkf.empId,
        'customerKhcontacts':customerkf.customerKhcontacts,
        'customerState':customerkf.customerState,
        'customerExecutor':customerkf.customerExecutor,
        'customerServicenr':customerkf.customerServicenr,
        'customerCustomerfk':customerkf.customerCustomerfk,
        'pageNum':customerkf.pageNum,
        'pageSize':customerkf.pageSize
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/customerkf/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
