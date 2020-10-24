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
          <el-input placeholder="请输入" clearable size="mini">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" size="mini" icon="el-icon-plus">添加</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in"
                     @click="advancedSearch = !advancedSearch">高查</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete">删除</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card v-show="advancedSearch" class="advanced_search" style="margin-top: 10px">
          <el-form size="mini" label-position="right" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="占位符">
                  <el-input size="mini" clearable placeholder="请输入"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="占位符">
                  <el-input size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="占位符">
                  <el-input size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="占位符">
                  <el-input size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="占位符">
                  <el-input size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="占位符">
                  <el-button size="mini" icon="el-icon-zoom-out"
                             @click="advancedSearch = !advancedSearch"></el-button>
                  <el-button type="primary" size="mini" icon="el-icon-search"></el-button>
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
        <el-table-column prop="cusName" label="客户名称" sortable></el-table-column>
        <el-table-column prop="abbreviation" label="简称" sortable></el-table-column>
        <el-table-column prop="lifeCycle" label="生命周期" sortable></el-table-column>
        <el-table-column prop="credit" label="信用等级" sortable></el-table-column>
        <el-table-column prop="cusDictSource" label="来源" sortable></el-table-column>
        <el-table-column prop="cusRemark" label="备注" sortable></el-table-column>
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

  export default {
    name: "Customer",
    data() {
      return {
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
