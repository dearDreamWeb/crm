<!--
@author: pengjia
@date: 2020/11/2
@description:
-->
<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="5">
          <el-input size="mini" clearable placeholder="请输入">
            <el-button icon="el-icon-search" slot="append"></el-button>
          </el-input>
          <el-tree style="margin-top: 20px" :data="data" :props="defaultProps"
                   @node-click="handleNodeClick"></el-tree>
        </el-col>
        <el-col :span="1">
          <el-divider direction="vertical"></el-divider>
        </el-col>
        <el-col :span="18">
          <el-row :gutter="20">
            <el-col :span="7">
              <el-input size="mini" clearable placeholder="请输入">
                <el-button icon="el-icon-search" slot="append"></el-button>
              </el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" icon="el-icon-plus" size="mini"
                         @click="openAddDialog">新增</el-button>
              <el-button type="primary" icon="el-icon-zoom-in" size="mini">高查</el-button>
              <el-button type="primary" icon="el-icon-refresh" size="mini"></el-button>
            </el-col>
            <el-col :span="9">
              <el-button type="warning" size="mini" icon="el-icon-edit">修改</el-button>
              <el-button type="danger" size="mini" icon="el-icon-delete">删除</el-button>
            </el-col>
          </el-row>

          <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                    :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                    highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
            <el-table-column type="index" width="50"></el-table-column>
            <el-table-column label="占位符"></el-table-column>
            <el-table-column label="占位符"></el-table-column>
            <el-table-column label="占位符"></el-table-column>
            <el-table-column label="占位符"></el-table-column>
            <el-table-column label="占位符"></el-table-column>
            <el-table-column label="占位符"></el-table-column>
            <el-table-column label="占位符"></el-table-column>
            <el-table-column label="占位符"></el-table-column>
          </el-table>

          <el-pagination background :page-size="pageSize" :total="total"
                         :current-page="pageNum" :page-sizes="[1,2,5,10]"
                         @current-change="handleCurrentChange"
                         layout="prev, pager, next, jumper, total">
          </el-pagination>
        </el-col>
      </el-row>
    </el-card>

    <el-dialog title="制定方案" :visible.sync="addDialog" @close="addDialogClose">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef"
               label-width="80px" label-position="right" size="mini">
        <el-row>
          <el-col>
            <el-form-item label="方案主题" prop="solutionTitle">
              <el-input v-model="addForm.solutionTitle" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户" prop="cusId">
              <el-select v-model="addForm.cusId" clearable placeholder="请选择"
                         @change="customerChange">
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机会" prop="saleId">
              <el-select v-model="addForm.saleId" clearable placeholder="请选择"
                         @change="saleChange">
                <el-option v-for="item in saleList" :key="item.saleId"
                           :label="item.saleName" :value="item.saleId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="需求" prop="demandId">
              <el-select v-model="addForm.demandId" clearable placeholder="请选择">
                <el-option v-for="item in demandList" :key="item.demandId"
                           :label="item.demandTitle" :value="item.demandId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="提交时间" prop="createTime">
              <el-date-picker v-model="addForm.createTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date" disabled
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="方案内容" prop="content">
              <el-input type="textarea" v-model="addForm.content"
                        clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" :loading="addButtonLoading"
                   @click="addSolutionClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {solutionHttp} from "../../network/pre_sale/solution";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {saleHttp} from "../../network/pre_sale/sale";
  import {demandHttp} from "../../network/pre_sale/demand";

  export default {
    name: "Solution",
    data() {
      return {
        addForm:{
          solutionTitle:'',
          content:'',
          cusId:'',
          saleId:'',
          demandId:'',
          createTime:new Date()
        },
        addFormRules:{
          solutionTitle:[
            {required:true,message:'请输入方案主题',trigger:'blur'}
          ],
          content:[
            {required:true,message:'请输入方案内容',trigger:'blur'}
          ],
          cusId:[
            {required:true,message:'请选择客户',trigger:'change'}
          ],
          saleId:[
            {required:true,message:'请选择机会',trigger:'change'}
          ],
          demandId:[
            {required:true,message:'请选择需求',trigger:'change'}
          ]
        },
        addButtonLoading:false,
        addDialog:false,
        customerList:[],
        demandList:[],
        saleList:[],

        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        rowSolutionId:0,
        buttonDisabled:true,

        data: [{
          label: '一级 1',
          children: [{
            label: '二级 1-1',
            children: [{
              label: '三级 1-1-1'
            }]
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    methods:{
      addSolutionClick() {

      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addButtonLoading = false
      },
      openAddDialog() {
        this.addDialog = true
      },
      customerChange(val) {
        saleHttp.getByCusId(val).then(res => {
          this.saleList = res.data
        })
      },
      saleChange(val) {
        demandHttp.getBySaleId(val).then(res => {
          this.demandList = res.data
        })
      },

      handleRowClick(row,event,column) {
        this.rowSolutionId = row.solutionId
        if (this.rowSolutionId  != 0) {
          this.buttonDisabled = false
        }
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.tableLoading = true
        solutionHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      initList() {
        this.tableLoading = true
        solutionHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      initCustomerList() {
        customerHttp.listAll().then(res => {
          this.customerList = res.data
        })
      },

      handleNodeClick(data) {
        console.log(data);
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      }
    },
    created() {
      this.initList()
    }
  }
</script>

<style scoped>
  .el-divider--vertical{
    height: 2em !important;
  }
</style>
