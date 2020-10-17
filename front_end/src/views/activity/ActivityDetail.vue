<!--
@author: pengjia
@date: 2020/10/17
@description:
-->
<template>
  <div>
    <el-card>
      <el-form :model="searchForm" ref="searchFormRef">
        <el-row :gutter="10">
          <el-col :span="4">
            <el-form-item>
              <el-select v-model="searchForm.activityId" size="mini" clearable
                         placeholder="根据活动查询">
                <el-option v-for="item in detailSearchActivityList" :key="item.activityId"
                           :label="item.activityTitle" :value="item.activityId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-select v-model="searchForm.empId" size="mini" clearable
                         placeholder="根据员工查询">
                <el-option v-for="item in detailSearchEmpList" :key="item.empId"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-button type="primary" size="mini" icon="el-icon-search"
                         @click="searchClick"></el-button>
              <el-button size="mini" icon="el-icon-refresh"
                         @click="refreshClick"></el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <el-table :data="listForm"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column label="专属链接" prop="url" width="350" show-overflow-tooltip></el-table-column>
        <el-table-column label="分配日期" prop="authDate">
          <template slot-scope="scope">
            {{scope.row.authDate | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column label="所属活动" prop="activityTitle"></el-table-column>
        <el-table-column label="所属员工" prop="empName"></el-table-column>
        <el-table-column label="创建时间" prop="createTime">
          <template slot-scope="scope">
            {{scope.row.createTime | dateFormat}}
          </template>
        </el-table-column>
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
  import {userHttp} from "../../network/system/user";
  import {activityHttp} from "../../network/pre_sale/activity";
  import {activityDetailHttp} from "../../network/pre_sale/activityDetail";

  export default {
    name: "ActivityDetail",
    data() {
      return {
        listForm:[],
        tableLoading:false,
        pageNum:1,
        pageSize:10,
        total:1,
        rowDetailId:0,

        searchForm:{
          activityId:'',
          empId:''
        },
        detailSearchEmpList:[],
        detailSearchActivityList:[],
      }
    },
    methods:{
      refreshClick() {
        this.$refs.searchFormRef.resetFields()
        this.initList()
        this.rowDetailId = 0
      },
      searchClick() {
        activityDetailHttp.list(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.pageNum = res.data.pageNum
            this.total = res.data.total
          }
        })
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        activityDetailHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      handleRowClick(row,event,column) {
        this.rowDeptId = row.activityDetailId
      },

      initList() {
        this.tableLoading = true
        activityDetailHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      initEmpList() {
        userHttp.list().then(res => {
          this.detailSearchEmpList = res.data.list
        })
      },
      initActivityList() {
        activityHttp.listNotArgs().then(res => {
          this.detailSearchActivityList = res.data
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
      this.initEmpList()
      this.initActivityList()
      this.initList()
    }
  }
</script>

<style scoped>

</style>
