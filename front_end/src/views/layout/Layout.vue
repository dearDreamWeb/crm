<template>
  <el-container>
    <el-aside class="sidebar">
      <el-header class="logo">
        <img src="@/assets/logo.png">
        <h1>Element Admin</h1>
      </el-header>
      <el-menu router :default-active="1">
        <el-menu-item index="1">处理中心</el-menu-item>
        <el-submenu index="2">
          <template slot="title">我的工作台</template>
          <el-menu-item index="2-1">选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
          <el-menu-item index="2-3">选项3</el-menu-item>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项1</el-menu-item>
            <el-menu-item index="2-4-2">选项2</el-menu-item>
            <el-menu-item index="2-4-3">选项3</el-menu-item>
            <el-submenu index="3">
              <template slot="title">选项5</template>
              <el-menu-item index="3-1-1">选项1</el-menu-item>
              <el-menu-item index="3-1-2">选项1</el-menu-item>
              <el-menu-item index="3-1-3">选项1</el-menu-item>
              <el-menu-item index="3-1-4">选项1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-submenu>
        <el-menu-item index="3" disabled>消息中心</el-menu-item>
        <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <i class="el-icon-menu sidebarToggle"></i>
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
              <el-dropdown-item command="Profile"> <i class="el-icon-setting"></i> 个人设定</el-dropdown-item>
              <el-dropdown-item command="SignOut"> <i class="el-icon-refresh"></i> 安全退出</el-dropdown-item>
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
    name: "Layout"
  }
</script>

<style scoped>
  .sidebar{
    background-color: #D3D3D3;
    box-shadow: 2px 0 6px rgba(0,21,41,0.35);
    width: 200px !important;
  }
  .logo{
    background-color: #D3D3D3;
    color: #D3D3D3;
    line-height: 60px;
  }
  .logo img{
    height: 32px;
    vertical-align: middle;
    display: inline-block;
  }
  h1{
    font-size: 20px;
    display: inline-block;
    vertical-align: middle;
    margin: 0 0 0 12px;
    padding: 0;
    text-align: center;
  }
  .el-menu{
    border-right: none;
    background-color: white;
  }
  .header{
    background-color: #fff;
    box-shadow: 0 1px 4px rgba(0,21,41,0.08);
  }
  .header *{
    line-height: 60px;
  }
  .header .sidebarToggle{
    font-size: 28px;
  }
  .header .header-action{
    margin-left: 12px;
  }
  .float-right{
    float: right;
  }

</style>
