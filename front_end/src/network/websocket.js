/*
@author: pengjia
@date: 2020/10/18
@description:
*/
export const socketHttp = {
  getWebSocket() {
    let webSocket = new WebSocket('ws://localhost:8099/socket')
    webSocket.onopen = function (event) {
      console.log('websocket打开连接')
    }
    webSocket.onmessage = function (event) {
      console.log(event)
      console.log('webSocket收到消息：%c' + event.data,'color:green')
      let message = JSON.parse(event.data)
      console.log(message)
    }
    webSocket.onclose = function (event) {
      console.log('webSocket关闭连接')
    }
    webSocket.onerror = function (event) {
      console.log('websocket发生异常')
    }
    return webSocket
  },
  closeWebSocket() {
    let webSocket = new WebSocket('ws://localhost:8099/socket')
    webSocket.onclose = function (event) {
      console.log('webSocket关闭连接')
    }
  }
}
