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
          <el-input v-model="searchForm.followTitle" placeholder="请输入主题查询" clearable size="mini">
            <el-button @click="searchClick" slot="append" icon="el-icon-search" size="mini"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="openAddDialog">制定</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in"
                     @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh"
                     @click="resetForm"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     @click="openEditDialog" :disabled="buttonDisabled">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="delFollow">删除</el-button>
          <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline"></el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in:top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px">
          <el-form :model="searchForm" ref="searchFormRef" size="mini"
                   label-position="right" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级查询"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="主题" prop="followTitle">
                  <el-input v-model="searchForm.followTitle" clearable
                            placeholder="请输入"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="类别" prop="followCategory">
                  <el-select v-model="searchForm.followCategory" clearable
                             placeholder="请选择" @change="followCategoryChange">
                    <el-option v-for="item in followCategoryList" :key="item.label"
                               :label="item.value" :value="item.label"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="类型" prop="followType">
                  <el-select v-model="searchForm.followType" clearable
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
              <el-col :span="8">
                <el-form-item label="客户" prop="cusId">
                  <el-select v-model="searchForm.cusId" clearable
                             placeholder="请选择">
                    <el-option v-for="item in customerList" :key="item.cusId"
                               :label="item.cusName" :value="item.cusId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="负责人" prop="empId">
                  <el-select v-model="searchForm.empId" clearable
                             placeholder="请选择">
                    <el-option v-for="item in empList" :key="item.empId"
                               :label="item.empName" :value="item.empId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item>
                  <el-button icon="el-icon-zoom-out"
                             @click="closeAdvancedSearch"></el-button>
                  <el-button icon="el-icon-search" type="primary"
                             @click="searchClick"></el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </transition>

      <el-row :gutter="20" style="margin-top: 10px">
        <el-col :span="17">
          <el-card>
            <el-calendar>
              <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
              <template slot="dateCell" slot-scope="{date, data}">
                <p @click="getIsSelect">
                  {{ data.day.split('-').slice(0).join('-') }}<br/>
                </p>
                <div v-for="(item,index) in listForm" :key="index">
                  <div v-if="(item.resDate) === data.day.split('-').slice(0).join('-')">
                    <i style="float: left;margin-left: 5px;" :class="item.followTypeIcon"></i>
                  </div>
                  <div v-else></div>
                </div>
              </template>
            </el-calendar>
          </el-card>
        </el-col>
        <el-col :span="7">
          <el-card>
            查看今日跟进列表：<el-button size="mini" @click="getIsSelect" icon="el-icon-search"></el-button>
            <hr>
            <el-collapse v-model="activeNames" @change="handleChange">
              <el-collapse-item v-for="item in collapseList" :title="item.followTitle"
                                :key="item.followId" :name="item.followId">
                <div>{{item.followContent}}</div>
              </el-collapse-item>
            </el-collapse>
          </el-card>
        </el-col>
      </el-row>

      <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="followCategory" label="类别" width="60px" align="center">
          <template slot-scope="scope">
            <el-tooltip effect="dark" :content="scope.row.followCategory === 1 ? '待办' : '记录'"
                        placement="top">
              <i :class="scope.row.followCategory | followCategoryFormat"></i>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column prop="followTitle" label="主题" header-align="center"></el-table-column>
        <el-table-column prop="saleResp.saleName" label="机会" align="center"></el-table-column>
        <el-table-column prop="followStartTime" label="开始日期">
          <template slot-scope="scope">
            {{scope.row.followStartTime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="empResp.empName" label="所有者" align="center"></el-table-column>
        <el-table-column prop="followType" label="类型" align="center"></el-table-column>
        <el-table-column prop="followContent" label="描述"></el-table-column>
        <el-table-column prop="intention" label="客户意向"></el-table-column>
        <el-table-column label="操作" width="60px" align="center">
          <template slot-scope="scope">
            <el-tooltip placement="top" effect="dark" content="处理">
              <el-button icon="el-icon-s-tools" size="mini" type="text"
                         @click="followHandleClick(scope.row.followId)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
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
            <el-form-item label="销售机会" prop="saleId">
              <el-select v-model="addForm.saleId" clearable
                         placeholder="请选择">
                <el-option v-for="item in saleList" :key="item.saleId"
                           :label="item.saleName" :value="item.saleId">
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

    <el-dialog title="跟进处理" :visible.sync="followHandleDialog"
               @close="followHandleDialogClose">
      <el-form :model="followHandleForm" label-width="100px" label-position="right"
               ref="followHandleFormRef">
        <el-row>
          <el-col>
            <el-form-item label="跟进主题：">
              {{followHandleForm.followTitle}}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="开始日期：">
              {{followHandleForm.followStartTime}}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型：">
              {{followHandleForm.followType}}
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="客户意向：">
          <el-input v-model="followHandleForm.intention" type="textarea" clearable></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="followHandleDialog = false">取消</el-button>
        <el-button type="primary" @click="handleFollowClick"
                   :loading="handleFollowClickLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="跟进日志修改" :visible.sync="editDialog"
               @close="editDialogClose">
      <el-form :model="editForm" ref="editFormRef" label-width="80px"
               label-position="right" size="mini" :rules="editFormRules">
        <el-row :gutter="20">
          <el-col :span="16">
            <el-form-item label="跟进主题" prop="followTitle">
              <el-input v-model="editForm.followTitle" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="跟进类别" prop="followCategory">
              <el-select v-model="editForm.followCategory" clearable>
                <el-option label="待办" :value="1"></el-option>
                <el-option label="记录" :value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="followStartTime">
              <el-date-picker v-model="editForm.followStartTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date"
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="跟进类型" prop="followType">
              <el-select v-model="editForm.followType" clearable
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
            <el-form-item label="机会" prop="saleId">
              <el-select v-model="editForm.saleId" clearable
                         placeholder="请选择" disabled>
                <el-option v-for="item in saleList" :key="item.saleId"
                           :label="item.saleName" :value="item.saleId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上续跟进" prop="followPid">
              <el-select v-model="editForm.followPid" clearable
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
            <el-form-item label="跟进描述" prop="followContent">
              <el-input v-model="editForm.followContent" type="textarea" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editHandleClick"
                   :loading="editButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {productHttp} from "../../network/system/product";
  import {followCategory,followType} from '../../common/data/follow_date'
  import {followHttp} from "../../network/pre_sale/followlog";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {dateFormat} from "../../common/formatUtils";
  import {saleHttp} from "../../network/pre_sale/sale";

  export default {
    name: "FollowUp",
    data() {
      return {
        searchForm:{},
        advancedSearch:false,
        empList:[],

        editButtonLoading:false,
        editForm:{
          followId:'',
          followTitle:'',
          followCategory:'',
          followStartTime:'',
          followType:'',
          cusId:'',
          followPid:'',
          followContent:''
        },
        editDialog:false,
        editFormRules:{
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
          ],
          followStartTime:[
            {required:true,message:'请确定时间',trigger:'change'}
          ]
        },

        handleFollowClickLoading:false,
        followHandleForm:{
          followId:'',
          followTitle:'',
          followType:'',
          followStartTime:'',
          followEndTime:'',
          intention:'',
          followHandleResult:''
        },
        followHandleDialog:false,

        activeNames:['1'],

        addDisabled:true,
        addForm:{
          followTitle:'',
          followCategory:'',
          followType:'',
          followTypeIcon:'',
          followStartTime: dateFormat(new Date()),
          followEndTime:'',
          followContent:'',
          saleId:'',
          empId:'',
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
          cusId:[
            {required:true,message:'请选择客户',trigger:'change'}
          ]
        },
        addButtonLoading:false,
        followCategoryList: followCategory,
        followTypeList: followType,
        addDialog:false,

        followPidList:[],
        customerList: [],
        saleList:[],
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        rowFollowId:0,
        buttonDisabled:true,

        todayList:{
          followStartTime:''
        },
        collapseList:[]
      }
    },
    methods:{
      clickHandle() {
        console.log("打印")
      },

      delFollow() {
        this.$confirm('将删除该跟进计划，请谨慎操作','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          followHttp.del(this.rowFollowId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message.error(res.message)
            }
          })
        })
      },

      resetForm() {
        this.$refs.searchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowFollowId = 0
        this.buttonDisabled = true
      },
      searchClick() {
        followHttp.list(this.searchForm).then(res => {
          this.listForm = res.data.list
        })
      },
      closeAdvancedSearch() {
        this.advancedSearch = !this.advancedSearch
        this.$refs.searchFormRef.resetFields()
      },

      openEditDialog() {
        this.editDialog = true
        this.initCustomerList()
        this.initFollowPidList()
        followHttp.getFollow(this.rowFollowId).then(res => {
          this.editForm = res.data
        })
      },
      editDialogClose() {
        this.$refs.editFormRef.resetFields()
        this.editButtonLoading = false
      },
      editHandleClick() {
        this.$refs.editFormRef.validate(valid => {
          if (!valid) return
          this.editButtonLoading = true
          followHttp.edit(this.editForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.editButtonLoading = false
              this.editDialog = false
              this.initList()
            } else {
              this.editButtonLoading = false
              this.$message.error(res.message)
            }
          })
        })
      },

      followHandleDialogClose() {
        this.$refs.followHandleFormRef.resetFields()
        this.handleFollowClickLoading = false
      },
      handleFollowClick() {
        this.handleFollowClickLoading = true
        followHttp.handleFollow(this.followHandleForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.handleFollowClickLoading = false
            this.followHandleDialog = false
            this.initList()
          } else {
            this.handleFollowClickLoading = false
            this.$message.error(res.message)
          }
        })
      },
      followHandleClick(followId) {
        console.log(followId)
        this.followHandleDialog = true
        followHttp.getFollow(followId).then(res => {
          this.followHandleForm = res.data
        })
      },

      followCategoryChange(value) {
        if (value == 2) {
          let format = dateFormat(new Date());
          this.addForm.followEndTime = format
        }
      },
      followTypeChange(val) {
        this.addForm.followType = val.value
        this.addForm.followTypeIcon = val.icon
      },
      generateFollowTitle() {
        let str = dateFormat(new Date())+"客户跟进";
        this.addForm.followTitle = str
      },

      handleChange() {

      },

      getIsSelect() {
        let innerText = document.getElementsByClassName("is-selected")[0].innerText.trim();
        console.log(innerText)
        this.todayList.followStartTime = innerText
        followHttp.listAllArgs(this.todayList).then(res => {
          this.collapseList = res.data.list
        })
      },

      openAddDialog() {
        this.addDialog = true
        this.initFollowPidList()
        this.initSaleList()
      },
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addButtonLoading = false
      },
      addFormClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addButtonLoading = true
          this.addForm.empId = this.$store.state.empId
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
        followHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          for (let i=0;i<this.listForm.length;i++) {
            this.listForm[i].resDate = dateFormat(this.listForm[i].followStartTime)
          }
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          // console.log(this.listForm)
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
      },
      initFollowPidList() {
        followHttp.listPidFollow().then(res => {
          this.followPidList = res.data
        })
      },
      handleRowClick(row,event,column) {
        this.rowFollowId = row.followId
        if (this.rowFollowId != 0) {
          this.buttonDisabled = false
        }
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
