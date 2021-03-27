<!--
@author: pengjia
@date: 2020/11/15
@description:
-->
<template>
  <div>
    <el-dialog title="三定" :visible.sync="sanYiDialog" width="30%"
               top="15px" class="sanDinDialogBox" @close="sanYiDialogClose">
      <el-row>
        <el-col>
          <span style="float: right;margin-bottom: 3px">1.定性</span>
          <p>$attrs: {{msg}}</p>
        </el-col>
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.qualitativeRadio" label="1"
                    @change="qualitativeRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-zan"></i><br>
            <span style="margin-left: 13px;">有价值</span>
          </el-radio>
        </el-col>
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.qualitativeRadio" label="2"
                    @change="qualitativeRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-shoushi-tucao"></i><br>
            <span style="margin-left: 13px">无价值</span>
          </el-radio>
        </el-col>
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.qualitativeRadio" label="3"
                    @change="qualitativeRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-wenhao"></i><br>
            <span style="margin-left: 13px">不确定</span>
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
          <el-radio v-model="sanDinYiForm.gradingRadio" label="1"
                    @change="gradingRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-chenggongbiaoqing"></i><br>
            <span style="margin-left: 13px;">大单</span>
          </el-radio>
        </el-col>
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.gradingRadio" label="2"
                    @change="gradingRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-biaoqing1"></i><br>
            <span style="margin-left: 13px">正常</span>
          </el-radio>
        </el-col>
        <el-col :span="7">
          <el-radio v-model="sanDinYiForm.gradingRadio" label="3"
                    @change="gradingRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-shibaibiaoqing"></i><br>
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
          <el-radio v-model="sanDinYiForm.rationRadio" label="1"
                    @change="rationRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-benzhou"></i><br>
            <span style="margin-left: 13px;">本周</span>
          </el-radio>
        </el-col>
        <el-col :span="5">
          <el-radio v-model="sanDinYiForm.rationRadio" label="2"
                    @change="rationRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-xiazhou"></i><br>
            <span style="margin-left: 13px">下周</span>
          </el-radio>
        </el-col>
        <el-col :span="5">
          <el-radio v-model="sanDinYiForm.rationRadio" label="3"
                    @change="rationRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-6"></i><br>
            <span style="margin-left: 13px">本月</span>
          </el-radio>
        </el-col>
        <el-col :span="5">
          <el-radio v-model="sanDinYiForm.rationRadio" label="4"
                    @change="rationRadioChange" class="radio-box">
            <i style="font-size: 20px" class="iconfont icon-xiayue"></i><br>
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
          <el-button @click="openMoneyDialog"
                     type="text">{{estimateMoneyText}}</el-button>
        </el-col>
        <el-col :span="12">
          <el-button @click="openNumberDialog"
                     type="text">{{estimateNumberText}}</el-button>
        </el-col>
      </el-row>
    </el-dialog>

    <el-dialog title="预估金额" :visible.sync="estimateMoneyDialog"
               @close="estimateMoneyDialogClose" width="30%">
      <el-input placeholder="请输入内容" v-model="estimateMoney"
                onkeyup="this.value = this.value.replace(/[^\d.]/g,'');"
                maxlength="8">
        <template slot="append">元</template>
      </el-input>
    </el-dialog>

    <el-dialog title="预估销量" :visible.sync="estimateNumberDialog"
               @close="estimateNumberDialogClose" width="30%">
      <el-input placeholder="请输入内容" v-model="estimateNumber"
                onkeyup="this.value = this.value.replace(/[^\d.]/g,'');"
                maxlength="8">
        <template slot="append">件</template>
      </el-input>
    </el-dialog>
  </div>
</template>

<script>
  import {sanYiKeHttp} from "../../network/pre_sale/sanyike";
  import {getMonday, getMonth} from "../../common/sanYiDate";

  export default {
    name: "CustomerMoreSanYi",
    props:['msg'],
    data() {
      return {
        qualitativeLoading:false,
        gradingLoading:false,
        rationLoading:false,

        estimateMoney:'',
        estimateNumber:'',
        estimateMoneyDialog:false,
        estimateNumberDialog:false,

        sanYiDialog:false,
        sanDinYiForm:{
          rationRadio:0,
          gradingRadio:0,
          qualitativeRadio:0
        },
        estimateMoneyText:'输入预估金额',
        estimateNumberText:'输入预估销量',

        qualitativeForm:{
          qualitativeIcon:'',
          qualitativeName:'',
          cusId:''
        },
        rationForm:{
          rationIcon:'',
          rationName:'',
          rationTime:'',
          expectSigningTime:'',
          expectSigningPrice:'',
          expectSigningNumber:'',
          cusId:''
        },
        gradingForm:{
          gradingIcon:'',
          gradingName:'',
          cusId:''
        }
      }
    },
    watch:{

    },
    methods:{

      sanYiDialogClose() {
        if (this.sanDinYiForm.qualitativeRadio != 0) {
          this.qualitativeForm.cusId = this.msg
          sanYiKeHttp.addQualitative(this.qualitativeForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.qualitativeLoading = true
            }
          })
        }
        if (this.sanDinYiForm.gradingRadio != 0) {
          this.gradingForm.cusId = this.msg
          sanYiKeHttp.addGrading(this.gradingForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.gradingLoading = true
            }
          })
        }
        if (this.sanDinYiForm.rationRadio != 0) {
          this.rationForm.cusId = this.msg
          sanYiKeHttp.addRation(this.rationForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.rationLoading = true
            }
          })
        }
      },

      estimateNumberDialogClose() {
        if (this.estimateNumber != null && this.estimateNumber != 0) {
          this.rationForm.expectSigningNumber = this.estimateNumber
          this.estimateNumberText = this.rationForm.expectSigningNumber
        }
        this.estimateNumber = ''
      },
      estimateMoneyDialogClose() {
        if (this.estimateMoney != null && this.estimateMoney != 0) {
          this.rationForm.expectSigningPrice = this.estimateMoney
          this.estimateMoneyText = this.rationForm.expectSigningPrice
        }
        this.estimateMoney = ''
      },
      openMoneyDialog() {
        this.estimateMoneyDialog = true
      },
      openNumberDialog() {
        this.estimateNumberDialog = true
      },

      rationRadioChange(value) {
        if (value == 1) {
          let starMonday = getMonday("s",0);
          let endMonday = getMonday("e",0);
          this.rationForm.rationIcon = 'iconfont icon-benzhou'
          this.rationForm.rationName = '本周'
          this.rationForm.expectSigningTime = starMonday+":"+endMonday
        } else if (value == 2) {
          let starMonday = getMonday("s",1);
          let endMonday = getMonday("e",1);
          this.rationForm.rationIcon = 'iconfont icon-xiazhou'
          this.rationForm.rationName = '下周'
          this.rationForm.expectSigningTime = starMonday+":"+endMonday
        } else if (value == 3) {
          let starMonth = getMonth("s",0);
          let endMonth = getMonth("e",0);
          this.rationForm.rationIcon = 'iconfont icon-6'
          this.rationForm.rationName = '本月'
          this.rationForm.expectSigningTime = starMonth+":"+endMonth
        } else if (value == 4) {
          let starMonth = getMonth("s",1);
          let endMonth = getMonth("e",1);
          this.rationForm.rationIcon = 'iconfont icon-xiayue'
          this.rationForm.rationName = '下月'
          this.rationForm.expectSigningTime = starMonth+":"+endMonth
        }
      },
      gradingRadioChange(value) {
        if (value == 1) {
          this.gradingForm.gradingIcon = 'iconfont icon-chenggongbiaoqing'
          this.gradingForm.gradingName = '大单'
        } else if (value == 2) {
          this.gradingForm.gradingIcon = 'iconfont icon-biaoqing1'
          this.gradingForm.gradingName = '正常'
        } else if (value == 3) {
          this.gradingForm.gradingIcon = 'iconfont icon-shibaibiaoqing'
          this.gradingForm.gradingName = '小单'
        }
        console.log(value)
      },
      qualitativeRadioChange(value) {
        if (value == 1) {
          this.qualitativeForm.qualitativeIcon = 'iconfont icon-zan'
          this.qualitativeForm.qualitativeName = '有价值'
        } else if (value == 2) {
          this.qualitativeForm.qualitativeIcon = 'iconfont icon-shoushi-tucao'
          this.qualitativeForm.qualitativeName = '无价值'
        } else if (value == 3) {
          this.qualitativeForm.qualitativeIcon = 'iconfont icon-wenhao'
          this.qualitativeForm.qualitativeName = '不确定'
        }
        console.log(value)
      },
      openSanYiDialog() {
        this.sanYiDialog = true
      },
    },
    created() {
    }
  }
</script>

<style scoped>

</style>
