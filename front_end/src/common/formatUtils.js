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
  return year+"-"+month+"-"+day
}

export function dateTimeFormat(time) {
  let date = new Date(time)
  let year = date.getFullYear()
  let month = date.getMonth()+1<10?"0"+(date.getMonth()+1) : date.getMonth()+1
  let day = date.getDate()<10?"0"+date.getDate():date.getDate()
  let hours = date.getHours()<10?"0"+date.getHours():date.getHours()
  let minutes = date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes()
  let seconds = date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds()
  //拼接
  return year+"-"+month+"-"+day + " " + hours + ":" + minutes + ":" +seconds
}

export function sexFormat(sex) {
  if (sex === 1){
    return '男'
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
