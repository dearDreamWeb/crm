/*
@author: pengjia
@date: 2020/10/19
@description:
*/
import Vue from 'vue'
import router from "../router";
import ElementUI from 'element-ui'

export const socketHttp = {
  initWebSocket(empName) {
    if ('WebSocket' in window) {
      this.websocket = new WebSocket('ws://localhost:8099/socket/'+empName)
    } else {
      alert('当前浏览器 Not support websocket')
    }
    // 连接错误
    this.websocket.onerror = this.setErrorMessage

    // 连接成功
    this.websocket.onopen = this.setOnopenMessage

    // 收到消息的回调
    this.websocket.onmessage = this.setOnmessageMessage

    // 连接关闭的回调
    this.websocket.onclose = this.setOncloseMessage

    // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = this.onbeforeunload
  },
  setErrorMessage () {
    ElementUI.Message({
      message:'WebSocket连接发生错误',
      type:'error'
    })
    console.log('WebSocket连接发生错误')
  },
  setOnopenMessage () {
    ElementUI.Message({
      message:'WebSocket连接成功',
      type:'success'
    })
    console.log('WebSocket连接成功')
  },
  setOnmessageMessage (event) {
    // 根据服务器推送的消息做自己的业务处理
    console.log('服务端返回：' + event.data)
    ElementUI.Message({
      message:'成功',
      type:'success'
    })
    let notification = ElementUI.Notification({
      title:'提示',
      dangerouslyUseHTMLString:true,
      /*message:JSON.parse(event.data).offerId,*/
      message:"<el-button style='cursor: pointer'>"+event.data+"</el-button>",
      duration:0,
      type:'success',
      onClick:function () {
        console.log("Notification点击")
        let resolve = router.resolve({
          path:'/dash_board'
        });
        window.open(resolve.href,"_blank")
      }
    });
    setTimeout(() => {notification.close()},5000)
  },
  setOncloseMessage () {
    console.log('WebSocket连接关闭')
  },
  onbeforeunload () {
    this.closeWebSocket()
  },
  closeWebSocket () {
    this.websocket.close()
  },
}
