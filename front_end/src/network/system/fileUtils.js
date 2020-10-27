/*
@author: pengjia
@date: 2020/10/27
@description:
*/
import {postForm,get} from "../request";

export const fileHttp = {
  delFile:function (fileName) {
    let params = {
      url:'/qiniu/delete',
      data:{
        'fileName':fileName
      }
    }
    return postForm(params)
  }
}
