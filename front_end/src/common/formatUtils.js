import Vue from 'vue'

/*时间格式话函数，仅针对yyyy-MM-dd hh:mm:ss的格式 */

export function dateFormat(time) {
  if (time != null) {
    let date = new Date(time)
    let year = date.getFullYear()
    let month = date.getMonth()+1<10?"0"+(date.getMonth()+1) : date.getMonth()+1
    let day = date.getDate()<10?"0"+date.getDate():date.getDate()
    let hours = date.getHours()<10?"0"+date.getHours():date.getHours()
    let minutes = date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes()
    let seconds = date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds()
    //拼接
    return year+"-"+month+"-"+day
  } else {
    return ''
  }
}

export function dateTimeFormat(time) {
  let date = new Date(time)
  let year = date.getFullYear()
  let month = date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1
  let day = date.getDate()<10 ? "0"+date.getDate():date.getDate()
  let hours = date.getHours()<10 ? "0"+date.getHours():date.getHours()
  let minutes = date.getMinutes()<10 ? "0"+date.getMinutes():date.getMinutes()
  let seconds = date.getSeconds()<10 ? "0"+date.getSeconds():date.getSeconds()
  //拼接
  return year+"-"+month+"-"+day + " " + hours + ":" + minutes + ":" +seconds
}

export function timeFormat(time) {
  const date = new Date(time)
    let y = date.getfullyear(),
    m = date.getmonth() + 1,
    d = date.getdate(),
    h = date.gethours(),
    i = date.getminutes(),
    s = date.getseconds();
  if (m < 10) {
    m = '0' + m;
  }
  if (d < 10) {
    d = '0' + d;
  }
  if (h < 10) {
    h = '0' + h;
  }
  if (i < 10) {
    i = '0' + i;
  }
  if (s < 10) {
    s = '0' + s;
  }
  return y + '-' + m + '-' + d + ' ' + h + ':' + i;
}

export function sexFormat(sex) {
  if (sex === 1){
    return '男'
  } else if (sex === 2) {
    return '未知'
  } else {
    return '女'
  }
}

export function clueStatusFormat(clueStatus) {
  if (clueStatus === 1) {
    return '已处理'
  } else {
    return '未处理'
  }
}

export function clueTypeFormat(clueType) {
  if (clueType === 1) {
    return'公司'
  } else {
    return '个人'
  }
}

export function ordStateFormat(ordState) {
  if (ordState === 1){
    return '已完成'
  } else {
    return '执行中'
  }
}
export function delStateFormat(delState) {
  if (delState === 1){
    return '已发货'
  } else {
    return '未发货'
  }
}
export function planInvoiceFormat(planInvoice) {
  if (planInvoice === 1){
    return '已开票'
  } else {
    return '未开票'
  }
}
export function recoReceivableFormat(recoReceivable) {
  if (recoReceivable === 1){
    return '已回款'
  } else {
    return '未回款'
  }
}

export function clueHandleResultFormat(handleResult) {
  if (handleResult === 1) {
    return '有效'
  } else if (handleResult === 0) {
    return '无效'
  } else if (handleResult == null) {
    return '暂未处理'
  }
}

export function productStatusFormat(productStatus) {
  if (productStatus === 0) {
    return '上架'
  } else {
    return '已售完'
  }
}

export function followCategoryFormat(followCategory) {
  if (followCategory === 1) {
    return 'el-icon-date'
  } else {
    return 'el-icon-document'
  }
}

export function salePriorLevelFormat(salePriorLevel) {
  if (salePriorLevel === 1) {
    return '一级'
  } else if (salePriorLevel === 2) {
    return '二级'
  } else if (salePriorLevel === 3) {
    return '三级'
  } else {
    return ''
  }
}

export function saleStarBeaconFormat(saleStarBeacon) {
  if (saleStarBeacon === 1) {
    return 'http://www.eblog.icu/6.png'
  } else if (saleStarBeacon === 2) {
    return 'http://www.eblog.icu/1.png'
  } else if (saleStarBeacon === 3) {
    return 'http://www.eblog.icu/2.png'
  } else if (saleStarBeacon === 4) {
    return 'http://www.eblog.icu/4.png'
  } else {
    return 'http://www.eblog.icu/5.png'
  }
}

export function dateDiff(date) {
  var argsOne,argsTwo,argsThree,argsFour;
  argsOne = this.moment(date).format('YYYY-MM-DD').split("-");
  argsTwo = new Date(argsOne[1] + '-' + argsOne[2] + '-' + argsOne[0]);
  argsOne = this.moment(new Date()).format('YYYY-MM-DD').split("-");
  argsThree = new Date(argsOne[1] + '-' + argsOne[2] + '-' + argsOne[0]);
  argsFour = parseInt(Math.abs(argsTwo - argsThree) / 1000 / 60 / 60 / 24);
  return argsFour;
}

export function cusIsSolve(isSolve) {
  if (isSolve == 1) {
    return '已解决'
  } else {
    return '未完成'
  }
}

export function randomString(len) {
  len = len || 32;
  var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
  var maxPos = $chars.length
  var pwd = '';
  for (let i=0;i<len;i++) {
    pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
  }
  return pwd;
}

export function getWeekDate() {
  var now = new Date();
  var day = now.getDay();
  var weeks = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
  var week = weeks[day];
  return week;
}

export function offerStatusFormat(offerStatus) {
  if (offerStatus == 0) {
    return '未审核'
  } else {
    return '已审核'
  }
}
