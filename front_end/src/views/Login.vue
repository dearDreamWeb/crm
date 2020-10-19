<template>
  <div class="login">
    <el-row style="margin-top: 200px;z-index: 1;">
      <el-col :offset="9" :span="7">
        <el-card class="login-box">
          <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules">
            <h1 class="title">WELCOME TO YOU</h1>
            <el-form-item prop="empName">
              <el-input type="text" auto-complete="off" placeholder="请输入用户名"
                        suffix-icon="el-icon-bell" v-model="loginForm.empName"></el-input>
            </el-form-item>
            <el-form-item prop="passWord">
              <el-input type="password" auto-complete="off" placeholder="请输入密码"
                        suffix-icon="el-icon-edit"  v-on:keyup.enter.native="login()"
                        v-model="loginForm.passWord"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="login" class="pull-right"
                         style="width: 100%" v-loading.fullscreen.lock="fullscreenLoading">登录</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {userHttp} from "../network/system/user";
  import {socketHttp} from "../socket/socket";

  export default {
    name: "Login",
    data(){
      return{
        loginForm:{
          empName:'',
          passWord:''
        },
        loginFormRules:{
          empName:[
            {required:true,message:'请输入登录名称',trigger:'blur'},
            {min:3,max:10,message:'长度在 3 到 10 个字符',trigger:'blur'}
          ],
          passWord:[
            { required: true, message: '请输入登录密码', trigger: 'blur' },
            { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
          ]
        },
        fullscreenLoading:false
      }
    },
    methods:{
      login(){
        this.$refs.loginFormRef.validate(async valid => {
          if (!valid) return
          this.fullscreenLoading = true
          userHttp.login(this.loginForm).then(res => {
            console.log(res)
            if (res && res.code==20000) {
              const emp = {
                empId:res.data.empId,
                empName:res.data.empName,
                nickName:res.data.nickName,
                token:res.data.token
              }
              this.$store.commit('addEmp',emp)
              window.sessionStorage.setItem('token',emp.token)
              this.fullscreenLoading = false
              this.$router.push('/home')
              socketHttp.initWebSocket(emp.empName)
            } else {
              this.fullscreenLoading = false
              this.$message({
                message:res.message,
                type:"error"
              })
            }
          })
        })
      }
    },

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
