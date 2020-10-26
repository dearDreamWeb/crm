<!--
@author: pengjia
@date: 2020/10/26
@description:
-->
<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入产品名称查询" clearable size="mini">
            <el-button size="mini" icon="el-icon-search" slot="append"></el-button>
          </el-input>
        </el-col>
        <el-col :span="9">
          <el-button type="primary" icon="el-icon-plus" size="mini">添加</el-button>
          <el-button type="primary" icon="el-icon-zoom-in" size="mini">高级查询</el-button>
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
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "Product",
    data() {
      return {
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false
      }
    },
    methods:{
      handleCurrentChange() {

      },
      handleRowClick() {

      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      }
    }
  }
</script>

<style scoped>

</style>
