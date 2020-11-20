<template>
  <el-container style="height: 100%" class="outer-container">
    <el-aside class="sidebar" v-bind:class="{'sidebar-collapse':isCollapse}"
              style="transition: .5s;">
      <el-header class="logo">
        <img src="@/assets/logo.png">
        <h1>Element</h1>
      </el-header>
      <el-menu router :default-active="$route.path" :collapse="isCollapse"
               active-text-color="#ffd04b" :unique-opened="true">
        <el-menu-item index="/home">
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </el-menu-item>
        <el-submenu :index="index + ''" v-for="(item,index) in menuList"
                    :key="item.menuId">
          <template slot="title">
            <i :class="item.icon"></i>
            <span>{{item.menuName}}</span>
          </template>
          <el-menu-item :index="subItem.url + ''" v-for="subItem in item.children"
                        :key="subItem.menuId">
            <template slot="title">
              <i :class="subItem.icon"></i>
              <span>{{subItem.menuName}}</span>
            </template>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <i @click="isCollapse=!isCollapse" class="el-icon-menu sidebarToggle"></i>
        <div class="float-right">
          <el-dropdown trigger="click" class="header-action">
            <span>{{$store.state.nickName}}<i class="el-icon-arrow-down el-icon--right"></i></span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="Profile">
                <i class="el-icon-setting"></i>
                <el-button type="text" @click="adminlog">个人设定</el-button>
              </el-dropdown-item>
              <el-dropdown-item command="SignOut">
                <i class="el-icon-refresh"></i>
                <el-button type="text" @click="logout">安全退出</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
<!--        <div class="float-right" style="width: 10px"></div>-->
        <div class="float-right">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              个人代办<i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item class="clearfix">
                评论
                <el-badge class="mark" :value="12" />
              </el-dropdown-item>
              <el-dropdown-item class="clearfix">
                回复
                <el-badge class="mark" :value="3" />
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
  import {userHttp} from "../../network/system/user";
  import {socketHttp} from "../../socket/socket";

  export default {
    name: "Layout",
    data(){
      return{
        isCollapse:false,
        menuList:[],
        empName:''
      }
    },
    methods:{
      logout(){
        this.$store.commit('clearAll')
        window.sessionStorage.clear()
        this.$router.push('/login')
        // socketHttp.closeWebSocket()
      },
      adminlog(){
        this.$router.push('/personal')
      },
      getMenuList() {
        userHttp.getMenuList().then(res => {
          if (res.code === 20000) {
            this.menuList = res.data
            this.$store.commit('addMenu',res.data)
            this.$message.success(res.message)
          } else {
            this.$message.error(res.message)
          }
        })
      }
    },
    created() {
      this.getMenuList()
      this.empName = this.$store.state.empName
    }
  }
</script>

<style scoped>
  .sidebar{
    overflow-x: scroll;
    white-space: nowrap;
  }
  ::-webkit-scrollbar {
    width: 0 !important;
  }
  ::-webkit-scrollbar {
    width: 0 !important;height: 0;
  }
</style>
