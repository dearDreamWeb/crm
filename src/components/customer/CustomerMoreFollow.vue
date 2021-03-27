<!--
@author: pengjia
@date: 2020/11/15
@description:
-->
<template>
  <div>
    <el-dialog title="制定跟进日志" :visible.sync="addDialog"
               @close="addHandleClose">
      <el-form :model="addForm" ref="addFormRef" label-width="80px"
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
              <el-button icon="el-icon-s-help"
                         @click="generateFollowTitle"></el-button>
            </el-tooltip>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="跟进类别" prop="followCategory">
              <el-select v-model="addForm.followCategory" clearable
                         placeholder="请选择">
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
                              value-format="yyyy-MM-dd" type="date"
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="跟进结束" prop="followEndTime">
              <el-date-picker v-model="addForm.followEndTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date"
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户" prop="cusId">
              <el-select v-model="addForm.cusId" clearable
                         placeholder="请选择">
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId">
                </el-option>
              </el-select>
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
                   @click="addFormClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {followCategory, followType} from "../../common/data/follow_date";
  import {dateFormat} from "../../common/formatUtils";

  export default {
    name: "CustomerMoreFollow",
    data() {
      return {
        addDialog:false,
        addButtonLoading:false,
        addForm:{},
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
          cusId:[
            {required:true,message:'请选择客户',trigger:'change'}
          ]
        },
        followCategoryList: followCategory,
        followTypeList: followType,
        followPidList:[],
        customerList:[]
      }
    },
    methods:{
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
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
      addFormClick() {

      },
      openAddDialog() {
        this.addDialog = true
      }
    },
    created() {
    }
  }
</script>

<style scoped>

</style>
