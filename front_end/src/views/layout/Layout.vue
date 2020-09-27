<template>
  <el-container style="height: 100%">
    <el-aside class="sidebar" v-bind:class="{'sidebar-collapse':isCollapse}"
              style="transition: .5s">
      <el-header class="logo">
        <img src="@/assets/logo.png">
        <h1>Element</h1>
      </el-header>
      <el-menu router :default-active="$route.path" :collapse="isCollapse"
               active-text-color="#ffd04b">
        <el-menu-item index="/home">
          <i class="el-icon-s-help"></i>
          <span slot="title">首页</span>
        </el-menu-item>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-tickets"></i>
            <span slot="title">测试</span>
          </template>
          <el-menu-item index="1-2">
            <i class="el-icon-monitor"></i>
            <span slot="title">页面2</span>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <i @click="isCollapse=!isCollapse" class="el-icon-menu sidebarToggle"></i>
        <div class="float-right">
          <el-dropdown trigger="click" class="header-action">
            <span>语言<i class="el-icon-arrow-down el-icon--right"></i></span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>中文</el-dropdown-item>
              <el-dropdown-item>English</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-dropdown trigger="click" class="header-action">
            <span>Admin<i class="el-icon-arrow-down el-icon--right"></i></span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="Profile">
                <i class="el-icon-setting"></i>个人设定
              </el-dropdown-item>
              <el-dropdown-item command="SignOut">
                <i class="el-icon-refresh"></i>
                <el-button type="text" @click="logout">安全退出</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: "Layout",
    data(){
      return{
        isCollapse:false
      }
    },
    methods:{
      logout(){
        this.$store.commit('clearAll')
        window.sessionStorage.clear()
        this.$router.push('/login')
      }
    }
  }
</script>

<style scoped>

</style>
