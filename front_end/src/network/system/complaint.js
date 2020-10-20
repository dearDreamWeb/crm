import {postJson,get} from "../request";

export const complaintHttp = {
  add:function (complaint) {
    let params = {
      url:'/complaint/add',
      data:{
        'complaintZt':complaint.complaintZt,
        'complaintReceptionist':complaint.complaintReceptionist,
        'complaintClassification':complaint.complaintClassification,
        'complaintDescribe':complaint.complaintDescribe,
        'complaintData':complaint.complaintData,
        'complaintComplainants':complaint.complaintComplainants,
        'complaintUrgent':complaint.complaintUrgent,
        'complaintHandle':complaint.complaintHandle,
        'complaintHandlegc':complaint.complaintHandlegc,
        'complaintCustomerfk':complaint.complaintCustomerfk
      }
    }
    return postJson(params)
  },
  get:function (complaintId) {
    let params = {
      url:'/complaint',
      data:{
        'complaintId':complaintId
      }
    }
    return get(params)
  },
  update:function (complaint) {
    let params = {
      url:'/complaint/update',
      data:{
        'complaintId':complaint.complaintId,
        'complaintZt':complaint.complaintZt,
        'complaintReceptionist':complaint.complaintReceptionist,
        'complaintClassification':complaint.complaintClassification,
        'complaintDescribe':complaint.complaintDescribe,
        'complaintData':complaint.complaintData,
        'complaintComplainants':complaint.complaintComplainants,
        'complaintUrgent':complaint.complaintUrgent,
        'complaintHandle':complaint.complaintHandle,
        'complaintHandlegc':complaint.complaintHandlegc,
        'complaintCustomerfk':complaint.complaintCustomerfk
      }
    }
    return postJson(params)
  },
  del:function (complaintId) {
    let params = {
      url:'/complaint/del',
      data:{
        'complaintId':complaintId
      }
    }
    return postJson(params)
  },
  list:function (complaint) {
    let params = {
      url:'/complaint/list',
      data:{
        'complaintZt':complaint.complaintZt,
        'complaintReceptionist':complaint.complaintReceptionist,
        'complaintClassification':complaint.complaintClassification,
        'complaintDescribe':complaint.complaintDescribe,
        'complaintData':complaint.complaintData,
        'complaintComplainants':complaint.complaintComplainants,
        'complaintUrgent':complaint.complaintUrgent,
        'complaintHandle':complaint.complaintHandle,
        'complaintHandlegc':complaint.complaintHandlegc,
        'complaintCustomerfk':complaint.complaintCustomerfk
      }
    }
    return get(params)
  },
  queryEmp:function (complaint) {
    let params = {
      url:'/complaint/list',
      data:{
        'complaintZt':complaint.complaintZt,
        'complaintReceptionist':complaint.complaintReceptionist,
        'complaintClassification':complaint.complaintClassification,
        'complaintDescribe':complaint.complaintDescribe,

        'complaintComplainants':complaint.complaintComplainants,
        'complaintUrgent':complaint.complaintUrgent,
        'complaintHandle':complaint.complaintHandle,
        'complaintHandlegc':complaint.complaintHandlegc,
        'complaintCustomerfk':complaint.complaintCustomerfk,
        'startDate':complaint.startDate,
        'endDate':complaint.endDate,
        'pageNum':complaint.pageNum,
        'pageSize':complaint.pageSize
      }
    }
    return get(params)
  },
  listPage:function (pageNum,pageSize) {
    let params = {
      url:'/complaint/list',
      data:{
        'pageNum':pageNum,
        'pageSize':pageSize
      }
    }
    return get(params)
  }
}
