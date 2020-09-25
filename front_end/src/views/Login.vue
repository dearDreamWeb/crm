<template>
  <div class="login">
    <el-row style="margin-top: 200px;z-index: 1;">
      <el-col :offset="9" :span="7">
        <el-card class="login-box">
          <el-form>
            <h1 class="title">WELCOME TO YOU</h1>
            <el-form-item>
              <el-input type="text" auto-complete="off" placeholder="请输入用户名"
                        suffix-icon="el-icon-bell"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input type="password" auto-complete="off" placeholder="请输入密码"
                        suffix-icon="el-icon-edit"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="pull-right" style="width: 100%">登录</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Login"
  }
</script>

<style scoped>
  .login{
    background: url("../assets/bg.png")no-repeat scroll center center / cover;
    background-size: 100% 100%;
    width: 100%;
    height: 100%;
    position: fixed;
  }
  .login-box{
    background: #ffffff;
    border: none;
  }
  .login-box .title{
    color: #000000;
    text-align: center;
  }
</style>
