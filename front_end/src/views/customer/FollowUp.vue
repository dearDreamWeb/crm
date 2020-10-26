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
          <el-button type="primary" size="mini" icon="el-icon-plus">制定</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in">高级查询</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-edit">删除</el-button>
        </el-col>
      </el-row>

      <el-table style="width: 100%;margin-top: 10px;margin-bottom: 10px"
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
      </el-table>

      <el-pagination background :page-size="pageSize" :total="total"
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="产品添加" top="20px" :visible.sync="addDialog">
      <el-form :model="addForm" label-width="80px" label-position="right"
               ref="addFormRef" :rules="addFormRules">
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
              <el-input size="mini" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button>取消</el-button>
        <el-button type="primary">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {productHttp} from "../../network/system/product";

  export default {
    name: "FollowUp",
    data() {
      return {
        addForm:{
          productName:'',
          productImage:'',
          productModel:'',
          productPrice:'',
          productBrand:'',
          productStock:'',
          productSize:'',
          productCost:'',
          productBarCode:'',
          remark:''
        },
        addFormRules:{

        },

        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        rowProductId:0
      }
    },
    methods:{
      initList() {
        productHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
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
    }
  }
</script>

<style scoped>

</style>
