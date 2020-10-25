<!--
@author: pengjia
@date: 2020/10/23
@description:
-->
<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入客户名称" clearable size="mini">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" size="mini" icon="el-icon-plus">添加</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in"
                     @click="openAdvancedSearch">高查</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete">删除</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card v-show="advancedSearch" class="advanced_search" style="margin-top: 10px">
          <el-form :model="searchForm" size="mini" ref="searchFormRef"
                   label-position="right" label-width="80px">
            <el-row>
              <el-col :span="4">
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="简称" prop="abbreviation">
                  <el-input v-model="searchForm.abbreviation" clearable size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="生命周期" prop="lifeCycle">
                  <el-select v-model="searchForm.lifeCycle" clearable size="mini">
                    <el-option v-for="item in cusLifeCycleList" :key="item.dictId"
                               :label="item.dictName" :value="item.dictId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="信用等级" prop="creditId">
                  <el-select v-model="searchForm.creditId" size="mini" placeholder="请选择" clearable>
                    <el-option v-for="item in creditList" :key="item.creditId"
                               :label="item.creditCode" :value="item.creditId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="客户来源" prop="cusDictSource">
                  <el-select v-model="searchForm.cusDictSource" size="mini" placeholder="请选择" clearable>
                    <el-option v-for="item in cusSourceList" :key="item.dictId"
                               :label="item.dictName" :value="item.dictId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="客户阶段" prop="cusDictStage">
                  <el-select v-model="searchForm.cusDictStage" size="mini" placeholder="请选择" clearable>
                    <el-option v-for="item in cusStageList" :key="item.dictId"
                               :label="item.dictName" :value="item.dictId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="首次签约">
                  <el-date-picker format="yyyy-MM-dd" v-model="searchForm.starSigningDate"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker format="yyyy-MM-dd" v-model="searchForm.endSigningDate"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="创建时间">
                  <el-date-picker format="yyyy-MM-dd" v-model="searchForm.startDate"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker format="yyyy-MM-dd" v-model="searchForm.endDate"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="结款方式" prop="settlement">
                  <el-select v-model="searchForm.settlement" size="mini" placeholder="请选择" clearable>
                    <el-option v-for="item in cusSettlementList" :key="item.dictId"
                               :label="item.dictName" :value="item.dictId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="负责人" prop="empId">
                  <el-select v-model="searchForm.empId" clearable size="mini">
                    <el-option v-for="item in cusEmpList" :key="item.empId"
                               :label="item.empName" :value="item.empId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item>
                  <el-button size="mini" icon="el-icon-zoom-out"
                             @click="closeAdvancedSearch"></el-button>
                  <el-button type="primary" @click="advancedSearchClick"
                             size="mini" icon="el-icon-search"></el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </transition>

      <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row v-loading="tableLoading" @row-click="handleRowClick">
        <el-table-column type="selection" width="50px"></el-table-column>
        <el-table-column prop="cusName" label="客户名称"></el-table-column>
        <el-table-column prop="abbreviation" label="简称"></el-table-column>
        <el-table-column prop="lifeCycle" label="生命周期"></el-table-column>
        <el-table-column prop="creditId" label="信用等级"></el-table-column>
        <el-table-column prop="cusDictSource" label="来源"></el-table-column>
        <el-table-column prop="cusRemark" label="备注"></el-table-column>
      </el-table>

      <el-pagination @current-change="handleCurrentChange"
                     :current-page="pageNum" :total="total"
                     :page-size="pageSize" background
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
  import {customerHttp} from "../../network/pre_sale/customer";
  import {creditHttp} from "../../network/system/syscredit";
  import {dictHttp} from "../../network/system/dict";
  import {userHttp} from "../../network/system/user";

  export default {
    name: "Customer",
    data() {
      return {
        cusLifeCycleList:[],
        cusEmpList:[],
        cusSettlementList:[],
        cusStageList:[],
        cusSourceList:[],
        creditList:[],
        searchForm:{
          abbreviation:'',
          lifeCycle:'',
          creditId:'',
          cusDictSource:'',
          cusDictStage:'',
          starSigningDate:'',
          endSigningDate:'',
          startDate:'',
          endDate:'',
          settlement:'',
          empId:''
        },

        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        advancedSearch:false,
        rowCustomerId:0,
      }
    },
    methods:{
      initLifeCycle() {
        dictHttp.tree_dict_byId(36).then(res => {
          this.cusLifeCycleList = res.data.children
        })
      },
      initEmpList() {
        userHttp.list().then(res => {
          this.cusEmpList = res.data.list
        })
      },
      initDictSettlement() {
        dictHttp.tree_dict_byId(28).then(res => {
          this.cusSettlementList = res.data.children
        })
      },
      initDictStage() {
        dictHttp.tree_dict_byId(3).then(res => {
          this.cusStageList = res.data.children
        })
      },
      initDictSource() {
        dictHttp.tree_dict_byId(2).then(res => {
          this.cusSourceList = res.data.children
        })
      },
      openAdvancedSearch() {
        this.advancedSearch = !this.advancedSearch
        this.initCreditList()
        this.initDictSource()
        this.initDictStage()
        this.initDictSettlement()
        this.initEmpList()
        this.initLifeCycle()
      },
      initCreditList() {
        creditHttp.noArgsList().then(res => {
          this.creditList = res.data
        })
      },
      advancedSearchClick() {
        customerHttp.list(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
      closeAdvancedSearch() {
        this.advancedSearch = !this.advancedSearch
        this.$refs.searchFormRef.resetFields()
      },
      handleRowClick(row,event,column) {
        this.rowCustomerId = row.cusId
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.tableLoading = true
        customerHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      initList() {
        this.tableLoading = true
        customerHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
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
    }
  }
</script>

<style scoped>

</style>
