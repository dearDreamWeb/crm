import Vue from 'vue'

/*时间格式话函数，仅针对yyyy-MM-dd hh:mm:ss的格式 */

export function dateFormat(time) {
  let date = new Date(time)
  let year = date.getFullYear()
  let month = date.getMonth()+1<10?"0"+(date.getMonth()+1) : date.getMonth()+1
  let day = date.getDate()<10?"0"+date.getDate():date.getDate()
  let hours = date.getHours()<10?"0"+date.getHours():date.getHours()
  let minutes = date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes()
  let seconds = date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds()
  //拼接
  return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds
}

export function sexFormat(sex) {
  if (sex === 1){
    return '男'
  } else {
    return '女'
  }
}
