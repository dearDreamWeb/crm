<!--
@author: pengjia
@date: 2020/11/15
@description:
-->
<template>
  <div>
    <el-dialog title="三定" :visible.sync="sanYiDialog" width="30%" class="sanDinDialogBox">
      <el-row>
        <el-col>
          <span style="float: right;margin-bottom: 3px">1.定性</span>
        </el-col>
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.radio" label="1" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px;">有价值</span>
          </el-radio>
        </el-col>
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.radio" label="2" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px">有价值</span>
          </el-radio>
        </el-col>
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.radio" label="3" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px">有价值</span>
          </el-radio>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <el-row>
        <el-col>
          <span style="float: right;margin-bottom: 3px">2.定级</span>
        </el-col>
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.radio" label="1" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px;">大单</span>
          </el-radio>
        </el-col>
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.radio" label="2" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px">正常</span>
          </el-radio>
        </el-col>
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.radio" label="3" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px">小单</span>
          </el-radio>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <el-row>
        <el-col>
          <span style="float: right;margin-bottom: 3px">3.定量</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <span>预估签约日期</span>
        </el-col>
      </el-row>
      <div style="height: 15px"></div>
      <el-row type="flex" justify="space-between">
        <el-col :span="5">
          <el-radio v-model="sanDinYiForm.radio" label="1" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px;">本周</span>
          </el-radio>
        </el-col>
        <el-col :span="5">
          <el-radio v-model="sanDinYiForm.radio" label="2" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px">下周</span>
          </el-radio>
        </el-col>
        <el-col :span="5">
          <el-radio v-model="sanDinYiForm.radio" label="3" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px">本月</span>
          </el-radio>
        </el-col>
        <el-col :span="5">
          <el-radio v-model="sanDinYiForm.radio" label="3" class="radio-box">
            <i style="font-size: 20px" class="el-icon-search"></i><br>
            <span style="margin-left: 13px">下月</span>
          </el-radio>
        </el-col>
      </el-row>
      <div style="height: 15px"></div>
      <el-row>
        <el-col>
          <span>预估签约金额</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-button type="text">{{estimateMoney}}</el-button>
        </el-col>
        <el-col :span="12">
          <el-button type="text">{{estimateNumber}}</el-button>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "CustomerMoreSanYi",
    data() {
      return {
        sanYiDialog:false,
        sanDinYiForm:{
          radio:0,
        },
        estimateMoney:'输入预估金额',
        estimateNumber:'输入预估销量'
      }
    },
    methods:{
      openSanYiDialog() {
        this.sanYiDialog = true
      }
    }
  }
</script>

<style scoped>

</style>
