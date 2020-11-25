/*
@author: pengjia
@date: 2020/11/25
@description:
*/
export function liftCycleFormat(lifecycle) {
  if (lifecycle == 1) {
    return '潜在'
  } else if (lifecycle == 2) {
    return '签约'
  } else if (lifecycle == 3) {
    return '重复'
  } else if (lifecycle == 4) {
    return '失效'
  } else {
    return '暂定'
  }
}
