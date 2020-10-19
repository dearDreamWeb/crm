<template>
  <div id="app" @mousemove="moveEvent" @click="moveEvent">
    <router-view/>
  </div>
</template>

<script>
import {socketHttp} from "./socket/socket";

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
  },
  created() {
    if (sessionStorage.getItem("store")) {
      this.$store.replaceState(Object.assign({},this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
    }
    window.addEventListener("beforeunload",()=> {
      sessionStorage.setItem("store",JSON.stringify(this.$store.state))
    })
  },
  beforeDestroy() {
    socketHttp.onbeforeunload()
  },
}
</script>

<style>

</style>
