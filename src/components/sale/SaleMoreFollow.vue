<!--
@author: pengjia
@date: 2020/11/18
@description:
-->
<template>
  <div>
    <el-dialog title="制定跟进日志" :visible.sync="addDialog"
               @close="addHandleClose">
      <el-form :model="addForm" ref="addSaleFollowFormRef" size="mini" label-width="80px"
               label-position="right" :rules="addFormRules">
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="跟进主题" prop="followTitle">
              <el-input v-model="addForm.followTitle" clearable
                        placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-tooltip effect="dark" content="使用默认" placement="top">
              <el-button icon="el-icon-s-help" size="mini"
                         @click="generateFollowTitle"></el-button>
            </el-tooltip>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="跟进类别" prop="followCategory">
              <el-select v-model="addForm.followCategory" clearable
                         placeholder="请选择" @change="followCategoryChange">
                <el-option v-for="item in followCategoryList" :key="item.label"
                           :label="item.value" :value="item.label"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="跟进类型" prop="followType">
              <el-select v-model="addForm.followType" clearable
                         placeholder="请选择" @change="followTypeChange">
                <el-option v-for="item in followTypeList" :key="item.label"
                           :label="item.value" :value="item">
                  <span style="float: left">{{item.value}}</span>
                  <i style="float: right;line-height: 35px" :class="item.icon"></i>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="跟进开始" prop="followStartTime">
              <el-date-picker v-model="addForm.followStartTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date" placeholder="请输入"
                              :picker-options="pickerOptions"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="跟进结束" prop="followEndTime">
              <el-date-picker v-model="addForm.followEndTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date" placeholder="请输入"
                              :picker-options="pickerOptions"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机会" prop="cusId">
              <el-tag>{{saleName}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上续跟进" prop="followPid">
              <el-select v-model="addForm.followPid" clearable
                         placeholder="选择">
                <el-option v-for="item in followPidList" :key="item.followId"
                           :label="item.followTitle" :value="item.followId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="描述" prop="followContent">
              <el-input v-model="addForm.followContent" type="textarea"
                        placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" :loading="addButtonLoading"
                   @click="addSaleFollowFormClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {followCategory, followType} from "../../common/data/follow_date";
  import {dateFormat} from "../../common/formatUtils";
  import {followHttp} from "../../network/pre_sale/followlog";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {saleHttp} from "../../network/pre_sale/sale";

  export default {
    name: "SaleMoreFollow",
    props:['sale-id','cus-id','emp-id'],
    data() {
      return {
        saleName:'',
        pickerOptions:{
          disabledDate(time) {
            return time.getTime() < Date.now() - 8.64e7;//如果没有后面的-8.64e7就是不可以选择今天的
          }
        },

        addDialog:false,
        addForm:{
          followTitle:'',
          followCategory:'',
          followType:'',
          followTypeIcon:'',
          followStartTime: dateFormat(new Date()),
          followEndTime:'',
          followContent:'',
          saleId:this.saleId,
          empId:this.empId,
          followPid:''
        },
        addFormRules:{
          followTitle:[
            {required:true,message:'请输入跟进标题',trigger:'blur'}
          ],
          followCategory:[
            {required:true,message:'请选择跟进类别',trigger:'change'}
          ],
          followType:[
            {required:true,message:'请选择跟进类型',trigger:'change'}
          ],
          followContent:[
            {required:true,message:'请输入跟进描述',trigger:'blur'}
          ],
        },
        addButtonLoading:false,
        followCategoryList: followCategory,
        followTypeList: followType,
        customerList:[],
        followPidList:[],
        saleList:[]
      }
    },
    methods:{
      followCategoryChange(value) {
        if (value == 2) {
          let format = dateFormat(new Date());
          this.addForm.followEndTime = format
        }
      },
      openAddDialog() {
        this.addDialog = true
        for (let i=0;i<this.saleList.length;i++) {
          if (this.saleList[i].saleId == this.saleId) {
            this.saleName = this.saleList[i].saleName
          }
        }
        console.log(this.saleName)
      },
      /*到达客户公司实体测量墙体，选定产品，安装数量*/
      addSaleFollowFormClick() {
        this.$refs.addSaleFollowFormRef.validate(valid => {
          if (!valid) return
          this.addButtonLoading = true
          followHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addButtonLoading = false
              this.addDialog = false
              this.$emit('init-page')
            } else {
              this.$message({
                message:res.message,
                type:'error'
              })
              this.addButtonLoading = false
            }
          })
        })
      },
      addHandleClose() {
        this.$refs.addSaleFollowFormRef.resetFields()
        this.addButtonLoading = false
      },
      generateFollowTitle() {
        let str = dateFormat(new Date())+"客户跟进";
        this.addForm.followTitle = str
      },
      followTypeChange(val) {
        this.addForm.followType = val.value
        this.addForm.followTypeIcon = val.icon
      },
      initFollowPidList() {
        followHttp.listPidFollow().then(res => {
          this.followPidList = res.data
        })
      },
      initCustomerList() {
        customerHttp.listAll().then(res => {
          this.customerList = res.data
        })
      },
      initSaleList() {
        saleHttp.list_all().then(res => {
          this.saleList = res.data.list
        })
      }
    },
    created() {
      this.initCustomerList()
      this.initFollowPidList()
      this.initSaleList()
    }
  }
</script>

<style scoped>

</style>
