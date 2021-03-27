<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>关于我</span>
          </div>
          <div>
            <el-form :v-model="personalForm" size="mini" label-width="80px" label-position="left">
              <el-row :gutter="10">
                <el-col :span="12">
                  <span><i class="el-icon-picture"></i>头像：
                    <el-avatar style="margin-left: 60px" :size="100" :src="personalForm.headImage"></el-avatar>
                  </span>
                </el-col>
                <!--<el-col :span="12">
                  <el-upload
                    class="upload-demo"
                    action="http://localhost:8099/qiniu/upload"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :file-list="fileList"
                    list-type="picture">
                    <el-button size="small" type="primary">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                  </el-upload>
                </el-col>-->
              </el-row>
              <el-row>
                <el-col>
                  <span><i class="el-icon-s-home"></i> 部门：{{deptName}}</span>
                </el-col>
              </el-row>
              <hr/>
              <el-row>
                <el-col>
                  <span><i class="el-icon-tickets"></i> 昵称：{{personalForm.nickName}}</span>
                </el-col>
              </el-row>
              <hr/>
              <el-row>
                <el-col>
                  <i class="el-icon-reading"> Education</i>
                  <br>
                  <span>JS in Computer Science from the University of Technology</span>
                </el-col>
              </el-row>
              <hr/>
              <el-row>
                <el-col>
                  <span><i class="el-icon-message"></i> 邮箱：{{personalForm.email}}</span>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </el-card>
      </el-col>

      <el-col :span="18">
        <el-card>
          <el-tabs v-model="activeName">
            <el-tab-pane label="Activity" name="first">
              <div>
                <span>青春是一个短暂的美梦, 当你醒来时, 它早已消失无踪</span>
                <el-divider></el-divider>
                <span>少量的邪恶足以抵消全部高贵的品质, 害得人声名狼藉</span>
                <span>
                  <el-carousel :interval="4000" type="card" height="200px">
                    <el-carousel-item v-for="item in imageList" :key="item">
                      <el-image :src="item" fit="contain"></el-image>
                    </el-carousel-item>
                  </el-carousel>
                </span>
              </div>
            </el-tab-pane>
            <el-tab-pane label="Timeline" name="second">
              <!--<el-timeline>
                <el-timeline-item timestamp="2018/4/12" placement="top">
                  <el-card>
                    <h4>更新 Github 模板</h4>
                    <p>王小虎 提交于 2018/4/12 20:46</p>
                  </el-card>
                </el-timeline-item>
                <el-timeline-item timestamp="2018/4/3" placement="top">
                  <el-card>
                    <h4>更新 Github 模板</h4>
                    <p>王小虎 提交于 2018/4/3 20:46</p>
                  </el-card>
                </el-timeline-item>
                <el-timeline-item timestamp="2018/4/2" placement="top">
                  <el-card>
                    <h4>更新 Github 模板</h4>
                    <p>王小虎 提交于 2018/4/2 20:46</p>
                  </el-card>
                </el-timeline-item>
              </el-timeline>-->
              <!--<template>
                <div class="infinite-list-wrapper" style="height: 200px;overflow:auto">
                  <ul class="list" v-infinite-scroll="load" infinite-scroll-disabled="disabled">
                    <li v-for="i in logListForm" class="list-item">{{ i.logTitle }}</li>
                  </ul>
                  <p v-if="loading">加载中...</p>
                  <p v-if="noMore">没有更多了</p>
                </div>
              </template>-->
            </el-tab-pane>
            <el-tab-pane label="Account" name="third">
              <div>
              <el-form ref="form" :model="editPasswordForm" label-width="80px"
                       size="small" label-position="left">
                <el-form-item label="旧密码：" prop="oldPassword">
                  <el-input v-model="editPasswordForm.oldPassword" clearable></el-input>
                </el-form-item>
                <el-form-item label="新密码：" prop="newPassword">
                  <el-input v-model="editPasswordForm.newPassword" clearable></el-input>
                </el-form-item>
                <el-form-item label="请确认：" prop="enterPassword">
                  <el-input v-model="editPasswordForm.enterPassword" clearable></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button :loading="editPasswordLoading" icon="el-icon-edit"
                             type="primary" @click="editPasswordClick">EDIT</el-button>
                </el-form-item>
              </el-form>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {userHttp} from "../../network/system/user";
  import {syslogHttp} from "../../network/system/syslog";

  export default {
    data() {
      return {
        deptName:'',
        editPasswordForm:{
          empId:'',
          oldPassword:'',
          newPassword:'',
          enterPassword:''
        },
        editPasswordFormRules:{
          oldPassword:[
            {required:true,message:'请输入密码',trigger:'blur'},
            {min:6,max:12,message:'密码长度在6~12个字符之间'}
          ],
          newPassword:[
            {required:true,message:'请输入密码',trigger:'blur'},
            {min:6,max:12,message:'密码长度在6~12个字符之间'}
          ],
          enterPassword:[
            {required:true,message:'请输入密码',trigger:'blur'},
            {min:6,max:12,message:'密码长度在6~12个字符之间'}
          ]
          //新密码不能和旧密码一样
          //旧密码失去焦点时判断是否输入正确
        },
        editPasswordLoading:false,

        personalForm:{},
        form: {
          mm: '',
          yx:'',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        activeName:'first',
        imageList:[
          'https://gradua.eblog.icu/wallhaven-4577l1.jpg',
          'https://gradua.eblog.icu/wallhaven-4d8d73.jpg',
          'https://gradua.eblog.icu/wallhaven-x1wroo.jpg',
          'https://gradua.eblog.icu/wallhaven-ym99pl.png'
        ],

        logListForm:[],
      }
    },
    methods:{
      initPersonal() {
        userHttp.getEmpByPersonal(this.$store.state.empId).then(res => {
          this.personalForm = res.data
          this.deptName = res.data.dept.deptName
        })
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      initLogListForm() {
        syslogHttp.listByEmpId(this.$store.state.empId).then(res => {
          this.logListForm = res.data
        })
      },
      editPasswordClick() {
        userHttp.editPassword().then(res => {

        })
      }
    },
    created() {
      this.initPersonal()
      this.initLogListForm()
    }
  }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
