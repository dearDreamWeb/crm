/*
@author: pengjia
@date: 2020/11/17
@description:
*/
import {get,postJson} from "../request";

export const sanYiKeHttp = {
  addQualitative:function (qualitative) {
    let params = {
      url:'/san_yi/qualitative',
      data:{
        'qualitativeIcon':qualitative.qualitativeIcon,
        'qualitativeName':qualitative.qualitativeName,
        'cusId':qualitative.cusId
      }
    }
    return postJson(params)
  },
  addRation:function (ration) {
    let params = {
      url:'/san_yi/ration',
      data:{
        'rationIcon':ration.rationIcon,
        'rationName':ration.rationName,
        'rationTime':ration.rationTime,
        'expectSigningTime':ration.expectSigningTime,
        'expectSigningPrice':ration.expectSigningPrice,
        'expectSigningNumber':ration.expectSigningNumber,
        'cusId':ration.cusId
      }
    }
    return postJson(params)
  },
  addGrading:function (grading) {
    let params = {
      url:'/san_yi/grading',
      data:{
        'gradingIcon':grading.gradingIcon,
        'gradingName':grading.gradingName,
        'cusId':grading.cusId
      }
    }
    return postJson(params)
  }
}
