<!--
@author: pengjia
@date: 2020/10/16
@description:
-->
<template>
  <div>
    <el-row>
      <el-col :span="12" :offset="6">
        <el-card class="cardBG">
          <h1 style="font-family: 楷体;font-size: 40px;">{{activity.activityTitle}}</h1>
          <h6>——记得名称校验</h6>
          <hr>
          <h1 style="font-family: 华文彩云">{{activity.content}}</h1>
          <hr>
          <hr>
          <h1 style="font-family: 华文琥珀;color: red">截至~~~{{activity.endTime}}~~~结束</h1>
          <hr>
          <el-form :model="clueForm" ref="clueFormRef" :rules="clueFormRules">
            <el-form-item label="名称" prop="clueName">
              <el-input v-model="clueForm.clueName" clearable
                        placeholder="请输入名称"></el-input>
            </el-form-item>
            <el-form-item label="联系方式" prop="cluePhone">
              <el-input v-model="clueForm.cluePhone" clearable
                        placeholder="请输入联系方式"></el-input>
            </el-form-item>
            <el-row>
              <el-col :offset="9">
                <el-form-item size="large">
                  <el-button type="primary" @click="addClue"
                             :loading="addButtonLoading">点击按钮~立即参加</el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {activityHttp} from "../network/pre_sale/activity";
  import {clueHttp} from "../network/pre_sale/clue";

  export default {
    name: "CluePage",
    data() {
      let checkMobile = (rule,value,cb) => {
        const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
        if (regMobile.test(value)) {
          return cb()
        }
        cb(new Error('请输入合法的手机号'))
      }
      return {
        addButtonLoading:false,
        activityId:'',
        empId:'',
        activity:{},

        clueForm:{
          clueName:'',
          cluePhone:'',
          activityId:'',
          empId:''
        },
        clueFormRules:{
          clueName:[
            {required:true,message:'公司名称不能为空哦！！亲',trigger:'blur'}
          ],
          cluePhone:[
            {required:true,message:'联系方式不能为空哦！！亲',trigger:'blur'},
            {validator:checkMobile}
          ]
        }
      }
    },
    methods:{
      addClue() {
        this.clueForm.activityId = this.activityId
        this.clueForm.empId = this.empId
        this.$refs.clueFormRef.validate(valid => {
          if (!valid) return
          this.addButtonLoading = true
          clueHttp.cluePageAdd(this.clueForm).then(res => {
            if (res.code === 20000) {
              this.$message.success("提交成功！！可继续浏览或退出")
              this.addButtonLoading = false
              this.$refs.clueFormRef.resetFields()
            } else {
              this.$message({
                message:'OH！NO！人数太多，请退出重试！！',
                type:'error'
              })
            }
          })
        })
      },
      initActivity() {
        activityHttp.getActivity(this.activityId).then(res => {
          this.activity = res.data
        })
      },
      addViews() {
        activityHttp.updateViews(this.activityId).then(res => {
          if (res.code === 20000) {
            console.log("成功")
          }
        })
      }
    },
    created() {
      this.activityId = this.$urlUtil.getQueryVariable("activityId")
      this.empId = this.$urlUtil.getQueryVariable("empId")

      this.initActivity()
      this.addViews()
    }
  }
</script>

<style scoped>
.cardBG{
  background-image: url("../assets/bg.png");
}
/deep/ .el-form-item__label{
  font-size: 25px !important;
  font-family: 楷体;
}
</style>
