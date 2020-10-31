<!--
@author: pengjia
@date: 2020/10/26
@description:
-->
<template>
  <div>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入标题查询" clearable size="mini">
            <el-button slot="append" icon="el-icon-search" size="mini"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="openAddDialog">制定</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in">高级查询</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-edit">删除</el-button>
          <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline"></el-button>
        </el-col>
      </el-row>

      <el-row :gutter="20" style="margin-top: 10px">
        <el-col :span="16">
          <el-card>
            <el-calendar>
              <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
              <template slot="dateCell" slot-scope="{date, data}">
                <p @click="getIsSelect">
                  {{ data.day.split('-').slice(0).join('.') }}<br/>
                </p>
                <div v-for="(item,index) in resDate" :key="index">
                  <div v-if="(item.date) === data.day.split('-').slice(0).join('-')">
                    <i style="float: left;margin-left: 5px;" :class="item.content"></i>
                  </div>
                  <div v-else></div>
                </div>
              </template>
            </el-calendar>
          </el-card>
        </el-col>
        <el-col :span="8">
          <!--<el-card>
            查看今日跟进列表：<el-button size="mini" @click="getIsSelect" icon="el-icon-plus"></el-button>
            <hr>
            <el-collapse v-model="activeNames" @change="handleChange">
              <el-collapse-item v-for="item in 5" :title="item" :name="item">
                ssss
              </el-collapse-item>
              <el-collapse-item title="一致性 Consistency" name="1">
                <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
                <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
              </el-collapse-item>
            </el-collapse>
          </el-card>-->
        </el-col>
      </el-row>

      <el-table style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="followCategory" label="类别"></el-table-column>
        <el-table-column prop="followTitle" label="主题"></el-table-column>
        <el-table-column prop="cusId" label="客户"></el-table-column>
        <el-table-column prop="followTime" label="开始日期"></el-table-column>
        <el-table-column prop="empId" label="所有者"></el-table-column>
        <el-table-column prop="followType" label="类型"></el-table-column>
        <el-table-column prop="intention" label="客户意向"></el-table-column>
      </el-table>

      <el-pagination background :page-size="pageSize" :total="total"
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="制定跟进日志" :visible.sync="addDialog"
               @close="addHandleClose">
      <el-form :model="addForm" ref="addFormRef" size="mini" label-width="80px"
               label-position="right" :rules="addFormRules">
        <el-row>
          <el-col>
            <el-form-item label="跟进主题" prop="followTitle">
              <el-input v-model="addForm.followTitle" clearable
                        placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="跟进类别" prop="followCategory">
              <el-select v-model="addForm.followCategory" clearable
                         placeholder="请选择">
                <el-option v-for="item in followCategoryList" :key="item.label"
                           :label="item.value" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="跟进类型" prop="followType">
              <el-select v-model="addForm.followType" clearable
                         placeholder="请选择">
                <el-option v-for="item in followTypeList" :key="item.label"
                           :label="item.value" :value="item.value"></el-option>
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
  import {productHttp} from "../../network/system/product";
  import {followCategory,followType} from '../../common/data/follow_date'
  import {followHttp} from "../../network/pre_sale/followlog";
  import {customerHttp} from "../../network/pre_sale/customer";

  export default {
    name: "FollowUp",
    data() {
      return {
        activeNames:['1'],

        resDate: [
          {date:'2020-10-20',content:'el-icon-edit'},
          {date:'2020-10-26',content:'el-icon-user'},
          {date:'2020-10-26',content:'el-icon-share'},
          {date:'2020-10-28',content:'el-icon-delete'},
          {date:'2020-10-27',content:'el-icon-phone'}
        ],

        addDisabled:true,
        addForm:{
          followTitle:'',
          followCategory:'',
          followType:'',
          followStartTime:'',
          followEndTime:'',
          followContent:'',
          cusId:'',
          followPid:''
        },
        addFormRules:{
          followTitle:[
            {required:true,message:'请输入跟进标题',trigger:'blur'}
          ],
          followCategory:[
            {required:true,message:'请选择跟进类别',trigger:'blur'}
          ],
          followType:[
            {required:true,message:'请选择跟进类型',trigger:'blur'}
          ],
          followContent:[
            {required:true,message:'请输入跟进描述',trigger:'blur'}
          ]
        },
        addButtonLoading:false,
        followCategoryList: followCategory,
        followTypeList: followType,
        addDialog:false,

        followPidList:[],
        customerList: [],
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        rowProductId:0
      }
    },
    methods:{
      handleChange() {

      },

      getIsSelect() {
        console.log(document.getElementsByClassName("is-selected")[0].innerText)
      },

      openAddDialog() {
        this.addDialog = true
        this.initCustomerList()
        this.initFollowPidList()
      },
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addButtonLoading = false
      },
      addFormClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addButtonLoading = true
          followHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addButtonLoading = false
              this.addDialog = false
              this.initList()
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

      initList() {
        productHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
        })
      },
      initCustomerList() {
        customerHttp.listAll().then(res => {
          this.customerList = res.data
        })
      },
      initFollowPidList() {
        followHttp.listPidFollow().then(res => {
          this.followPidList = res.data
        })
      },
      handleRowClick() {

      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        productHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
        })
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
    },
    created() {
      this.initList()
    },
  }
</script>

<style lang="scss" scoped>
  /*>>> .el-calendar-table .el-calendar-day{
     height: 55px !important;
   }
   .el-calendar{
     width: 100% !important;
   }
  >>> .el-calendar-table td.is-selected{
    background-color: cornflowerblue;
  }
  >>> .el-calendar-day:hover{
    background-color: dodgerblue;
  }*/
</style>
