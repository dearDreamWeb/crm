/*
@author: pengjia
@date: 2020/10/18
@description:获取地址参数信息
*/
export default {
  getQueryVariable:function (variable) {
    let query = window.location.search.substring(1);
    let vars = query.split("&");
    for (let i=0;i<vars.length;i++) {
      let pair = vars[i].split("=");
      if(pair[0] == variable){return pair[1];}
    }
    return(false);
  },
  GetQueryString:function (name) {
    let reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
  }
}
