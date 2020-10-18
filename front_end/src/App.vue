<template>
  <div id="app" @mousemove="moveEvent" @click="moveEvent">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      timer:null
    }
  },
  methods: {
    moveEvent() {
      let path = ['/login']
      if (!path.includes(this.$route.path)) {
        clearTimeout(this.timer)
        this.init()
      }
    },
    init() {
      this.timer = setTimeout(() => {
        sessionStorage.removeItem('token')
        sessionStorage.clear()
        //清除缓存
        // this.$cache.reset()
        this.$store.commit('clearAll')
        this.$router.push({
          path:'/login'
        })
      },60*60*1000)
    },

    /*getWebSocket() {
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
    }*/
  },
  created() {
    if (sessionStorage.getItem("store")) {
      this.$store.replaceState(Object.assign({},this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
    }
    window.addEventListener("beforeunload",()=> {
      sessionStorage.setItem("store",JSON.stringify(this.$store.state))
    })

    // this.getWebSocket()
  }
}
</script>

<style>

</style>
