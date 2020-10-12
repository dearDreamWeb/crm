<!--
@author: pengjia
@date: 2020/10/12
@description:
-->
<template>
<div>
  <el-card>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-input placeholder="请输入活动名称搜索" clearable size="mini">
          <el-button slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </el-col>
      <el-col :span="10">
        <el-button type="primary" size="mini" icon="el-icon-plus">添加活动</el-button>
        <el-button type="primary" size="mini" icon="el-icon-refresh"></el-button>
      </el-col>
      <el-col :span="8">
        <el-button type="warning" size="mini" icon="el-icon-edit">修改活动</el-button>
        <el-button type="danger" size="mini" icon="el-icon-delete">删除活动</el-button>
      </el-col>
    </el-row>

    <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
              :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
              highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column label="活动名称"></el-table-column>
      <el-table-column label="活动内容"></el-table-column>
      <el-table-column label="活动链接"></el-table-column>
      <el-table-column label="访问量"></el-table-column>
      <el-table-column label="创建人"></el-table-column>
      <el-table-column label="开始时间">
        <template slot-scope="scope">

        </template>
      </el-table-column>
      <el-table-column label="结束时间">
        <template slot-scope="scope">

        </template>
      </el-table-column>
    </el-table>
  </el-card>
</div>
</template>

<script>
  export default {
    name: "Activity",
    data() {
      return {
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1
      }
    },
    methods:{
      initList() {

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
