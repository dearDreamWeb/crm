<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入主题内容查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">新增</el-button>
          <el-button size="mini" type="primary" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditCare">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="deleteCare">删除</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
          <el-form :model="searchForm" ref="advancedSearchFormRef"
                   size="mini" label-position="right" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item prop="careZt" label="昵称">
                  <el-input v-model="searchForm.careZt" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="carelxrcontacts" label="联系人">
                  <el-input v-model="searchForm.carelxrcontacts" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="客户">
                  <el-select v-model="searchForm.cusId">
                    <el-option v-for="item in empList" :key="item.cusId"
                               :label="item.cusName" :value="item.cusId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-form-item label="日期">
                  <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="careexecutor" label="执行人">
                  <el-select v-model="searchForm.empId">
                    <el-option v-for="item in edpList" :key="item.empId"
                               :label="item.empName" :value="item.empId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item>
                  <el-button size="mini" @click="advancedQueryClick"
                             type="primary" icon="el-icon-search"></el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </transition>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="40"></el-table-column>
        <el-table-column prop="repairProblem" label="主题" sortable></el-table-column>
        <el-table-column prop="cusId" label="对应客户"></el-table-column>
        <el-table-column prop="repairWxfy" label="费用"></el-table-column>
        <el-table-column prop="repairGdstae" label="状态"></el-table-column>
        <el-table-column prop="empResp.empName" label="承接部门"></el-table-column>
        <el-table-column prop="empId" label="接单人"></el-table-column>
      </el-table>

      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

  </div>
</template>
<script>
import {repairHttp} from "../../network/system/repair";
import {userHttp} from "../../network/system/user";
  import {customerHttp} from "../../network/pre_sale/customer";

  export default {
    data() {
      return {

        rowCareId:0,
        searchForm:{
          empId:'',
          repairDate: '',
          repairProblem:'',
          repairPersonnel:'',
          repairAppointment:'',
          repairActual:'',
          repairGdstae:'',
          region: '',
          date1: '',
          repairFault: '',
          repairHfjl:'',
          repairWxfy:'',
          repairSfzb:'',
          cusId:'',
          productId:'',
          orderId:'',
          region: '',
          date1: '',

          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        advancedSearch:false,
        updateform:{},
        addDialog:false,
        buttonDisabled:true,
        addDictButtonLoading:false,
        editDictButtonLoading:false,
        editDialog:false,
        searchInput:'',
        careZt:'',

        careData:'',

        listForm:[],
        tableLoading:'',
        total:0,
        pageNum:1,
        pageSize:3,
        value: '',
        input: '',

        tableData: [],
        form: {
          empId:'',
          repairDate: '',
          repairProblem:'',
          repairPersonnel:'',
          repairAppointment:'',
          repairActual:'',
          repairGdstae:'',
          region: '',
          date1: '',
          repairFault: '',
          repairHfjl:'',
          repairWxfy:'',
          repairSfzb:'',
          cusId:'',
          productId:'',
          orderId:'',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        num: 1,
        rules:{
          careZt:[
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            { min: 4, max: 8, message: '长度在 4 到 8个字符', trigger: 'blur' }
          ]

        }
      }
    },
    methods:{
      openAddDialog() {
        this.addDialog = true
        this.initEmpList()
        this.initEdpList()
      },
      initEdpList(){
        userHttp.list().then(res =>{
          this.edpList = res.data.list
        })
      },
      initEmpList() {
        customerHttp.listAll().then(res => {
          this.empList = res.data
        })
      },


      resetForm(){
        this.$refs["advancedSearchFormRef"].resetFields()
        this.searchInput = ''
        this.initList()
        this.rowCareId = 0
        this.buttonDisabled = true
      },
      advancedQueryClick(){
        repairHttp.queryEmp(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },

      openEditCare(){
        this.editDialog = true;
        this.getEmpDetail()
      },

      editHandleClose() {
        this.$refs["updateform"].resetFields()
        this.editDictButtonLoading = false
      },

      addHandleClose() {
        this.$refs["addform"].resetFields()
      },

      searchInputClick(){
        this.listForm.repairProblem = this.searchInput
        repairHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      handleClose(){
        this.$refs["addform"].resetFields()
      },
      handleRowClick(row,event,column) {
        this.rowCareId = row.repairId
        if (this.repairId != 0) {
          this.buttonDisabled = false
        }
      },
      iHeaderCellStyle(){

      },
      iHeaderRowStyle(){

      },
      handleCurrentChange(pageIndex){
        this.searchForm.pageNum = pageIndex
        this.searchForm.pageSize = this.pageSize
        repairHttp.queryEmp(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      initList() {
        repairHttp.listPage(this.pageNum, this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
    },
    created() {
      this.initList()
      this.initEmpList()
      this.initEdpList()
    }
  }
</script>

<style>
</style>
