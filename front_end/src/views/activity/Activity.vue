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
        <el-button type="warning" size="mini" icon="el-icon-edit"
                   :disabled="buttonDisabled">修改活动</el-button>
        <el-button type="danger" size="mini" icon="el-icon-delete"
                   :disabled="buttonDisabled">删除活动</el-button>
      </el-col>
    </el-row>

    <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
              :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
              highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column label="活动名称" prop="activityTitle"></el-table-column>
      <el-table-column label="活动内容" prop="content" show-overflow-tooltip></el-table-column>
      <el-table-column label="活动链接" prop="activityLink" show-overflow-tooltip></el-table-column>
      <el-table-column label="访问量" prop="views"></el-table-column>
      <el-table-column label="创建人" prop="createBy"></el-table-column>
      <el-table-column label="开始时间">
        <template slot-scope="scope">
          {{scope.row.startTime | dateFormat}}
        </template>
      </el-table-column>
      <el-table-column label="结束时间">
        <template slot-scope="scope">
          {{scope.row.endTime | dateFormat}}
        </template>
      </el-table-column>
    </el-table>

    <el-row :gutter="20">
      <el-col :span="6" v-for="item in listForm" :key="item.activityId">
        <el-card shadow="hover">
          <el-row type="flex" justify="space-between">
            <el-col :span="1">
              <el-tag>{{item.activityTitle}}</el-tag>
            </el-col>
            <el-col :span="10">
              <el-tag>{{item.views}}</el-tag>
            </el-col>
          </el-row>
          <hr>
<!--          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">-->
          <div>
            <hr>
            <span style="font-family: 楷体">{{item.content}}</span>
            <div>
              <el-button type="text">编辑</el-button>
              <el-button type="text" style="float: right">删除</el-button>
            </div>
            <el-tag>{{item.startTime | dateFormat}}</el-tag>
            至
            <el-tag>{{item.endTime | dateFormat}}</el-tag>
          </div>
        </el-card>
      </el-col>
      <!--<el-col :span="8">
        <el-card>
          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
          <div>
            <span>好吃的汉堡</span>
            <div class="bottom clearfix">
              <el-button type="text" class="button">操作按钮</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
          <div>
            <span>好吃的汉堡</span>
            <div class="bottom clearfix">
              <el-button type="text" class="button">操作按钮</el-button>
            </div>
          </div>
        </el-card>
      </el-col>-->
    </el-row>
  </el-card>
</div>
</template>

<script>
  import {activityHttp} from "../../network/pre_sale/activity";

  export default {
    name: "Activity",
    data() {
      return {
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        buttonDisabled:true,
        rowActivityId:0,
        currentDate: new Date()
      }
    },
    methods:{
      handleRowClick(row,event,column) {
        this.rowActivityId = row.activityId
        if (this.rowActivityId != 0) {
          this.buttonDisabled = false
        }
      },
      initList() {
        this.tableLoading = true
        activityHttp.list(this.listForm).then(res => {
          this.listForm = res.data
          this.tableLoading = false
        })
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
.image{
  width: 100%;
}
</style>
