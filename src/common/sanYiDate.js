/*
@author: pengjia
@date: 2020/11/17
@description:
*/
/*获取上周、本周、下周的开始日期和结束日期*/
// 1.调用方法 getMonday("s",0)、getMonday("e",0)
// 2.type 为"s"代表开始日期,type为"e"代表结束日期
// 3.dates  不传或0代表本周，-1代表上周，1代表下周
export const getMonday = (type,dates) => {
  var now = new Date();
  var nowTime = now.getTime();
  var day = now.getDay();
  var longTime = 24 * 60 * 60 *1000;
  var n = longTime * 7 * (dates || 0);
  if (type == "s") {
    var dd = nowTime - (day - 1) * longTime + n;
  };
  if (type == "e") {
    var dd = nowTime + (7 - day) * longTime + n;
  };
  dd = new Date(dd);
  var y = dd.getFullYear();
  var m = dd.getMonth() + 1;
  var d = dd.getDate();
  m = m < 10 ? "0" + m: m;
  d = d < 10 ? "0" + d: d;
  var day = y + "-" + m + "-" + d;
  return day;
}

/*获取本月、上月、下月的起始日、结束日 (不传或0代表本月，-1代表上月，1代表下月)*/
// 1.调用方法 getMonth("s",0)、getMonth("e",0)
// 2.type 为"s"代表开始日期,type为"e"代表结束日期
// 3.dates  不传或0代表本月，-1代表上月，1代表下月

export const getMonth = (type,months) => {
  var d = new Date();
  var year = d.getFullYear();
  var month = d.getMonth() + 1;
  if (Math.abs(months) > 12) {
    months = months % 12;
  };
  if (months != 0) {
    if (month + months > 12) {
      year++;
      month = (month + months) % 12;
    } else if (month + months < 1) {
      year--;
      month = 12 + month + months;
    } else {
      month = month + months;
    };
  };
  month = month < 10 ? "0" + month: month;
  var date = d.getDate();
  var firstday = year + "-" + month + "-" + "01";
  var lastday = "";
  if (month == "01" || month == "03" || month == "05" || month == "07" || month == "08" || month == "10" || month == "12") {
    lastday = year + "-" + month + "-" + 31;
  } else if (month == "02") {
    if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0)) {
      lastday = year + "-" + month + "-" + 29;
    } else {
      lastday = year + "-" + month + "-" + 28;
    };
  } else {
    lastday = year + "-" + month + "-" + 30;
  };
  var day = "";
  if (type == "s") {
    day = firstday;
  } else {
    day = lastday;
  };
  return day;
}

export const homeChange = (num) => {
  if (num == 1) {
    let starMonth = getMonth("s",-1);
    let endMonth = getMonth("e",-1);
    return starMonth+','+endMonth;
  } else if (num == 2) {
    let starMonday = getMonday("s",-1);
    let endMonday = getMonday("e",-1);
    return starMonday+','+endMonday;
  } else if (num == 3) {
    let starMonday = getMonday("s",0);
    let endMonday = getMonday("e",0);
    return starMonday+','+endMonday;
  } else if (num == 4) {
    var curYearStartDate = getCurYearStartDate();
    var curYearEndDate = getCurYearEndDate();
    return curYearStartDate+','+curYearEndDate;
  } else {
    let starMonday = getMonday("s",0);
    let endMonday = getMonday("e",0);
    return starMonday+','+endMonday;
  }
}

var date = new Date();
function formatDate(curDate) {
  var getCurYear = curDate.getFullYear();
  var month = curDate.getMonth() + 1;
  var curDate = curDate.getDate();

  if (month > 0 && month < 10) {
    month = "0" + month;
  }
  if (curDate > 0 && curDate < 10) {
    curDate = "0" + curDate;
  }
  return getCurYear + "-" + month + "-" + curDate;
}

function getCurYearStartDate() {
  var curYear = date.getFullYear();
  return formatDate(new Date(curYear,0,1));
}

function getCurYearEndDate() {
  var curtYear = date.getFullYear();
  return formatDate(new Date(curtYear,11,31));
}

export function Percentage(num, total) {
  if (num == 0 || total == 0){
    return 0;
  }
  return (Math.round(num / total * 10000) / 100.00 + "%");// 小数点后两位百分比
}

export function noRepeat(arr){
  // 准备一个空箱子
  var lsArr = [];
  // 逐个拿到老箱子中的所有商品
  for(var i=0;i<arr.length;i++){
    // 在新箱子中查看,是否有这个商品
    if(!lsArr.includes(arr[i])){
      // 如果没有,放进去
      lsArr.push(arr[i]);
    }
  }
  // 返回去重之后的新箱子
  return lsArr;
}
