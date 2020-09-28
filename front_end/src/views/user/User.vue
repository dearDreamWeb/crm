<template xmlns="http://www.w3.org/1999/html">
  <div>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入内容" size="mini" class="input-with-select">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button type="primary" size="mini" icon="el-icon-plus" @click="openAddDialog">添加用户</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh"></el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled">修改用户</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="deleteEmp">删除用户</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
          <el-form size="mini" label-position="right" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="问题">
                  <el-input size="mini" placeholder="INPUT" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="日期">
                  <el-date-picker type="daterange" range-separator="至"
                                  start-placeholder="开始日期" end-placeholder="结束日期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </transition>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="empName" label="empName" sortable width="130px"></el-table-column>
        <el-table-column prop="nickName" label="nickName"></el-table-column>
        <el-table-column prop="email" label="email" width="180px"></el-table-column>
        <el-table-column prop="sex" label="sex" width="100px">
          <template slot-scope="scope">
            {{scope.row.sex | sexFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="phone"></el-table-column>
        <el-table-column prop="empStatus" label="empStatus" align="center">
          <template slot-scope="scope">
            <el-switch active-color="#13ce66" inactive-color="#ff4949"
                       v-model="scope.row.empStatus" :active-value="1"
                       :inactive-value="0" @change="empStatusChange(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="creteTime">
          <template slot-scope="scope">
            {{scope.row.createTime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="remark" label="remark"></el-table-column>
      </el-table>

      <el-pagination background @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="sizes, prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="用户添加" :visible.sync="addDialog">
      <el-form label-width="100px" label-position="right">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账号">
              <el-input size="mini" placeholder="请输入EmpName" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="昵称">
              <el-input size="mini" placeholder="请输入EmpName" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="邮箱">
              <el-input size="mini" placeholder="请输入EmpName" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-input size="mini" placeholder="请输入EmpName" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号">
              <el-input size="mini" placeholder="请输入EmpName" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注">
              <el-input size="mini" placeholder="请输入EmpName" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {userHttp} from "../../network/system/user";

  export default {
    name: "User",
    data(){
      return{
        rowEmpId: 0,
        buttonDisabled:true,
        pageNum:1,
        pageSize:10,
        total:1,
        listForm:[],
        advancedSearch:false,
        addDialog:false
      }
    },
    methods:{
      deleteEmp() {
        this.$confirm('删除后不可恢复！！请谨慎操作！！').then(_ => {
          userHttp.del(this.rowEmpId).then(res => {
            this.$message.success('删除成功')
          })
        }).catch(_ => {

        })
      },
      handleRowClick(row,event,column) {
        this.rowEmpId = row.empId
        if (this.rowEmpId != 0) {
          this.buttonDisabled = false
        }
      },
      openAddDialog() {
        this.addDialog = true
      },
      handleSizeChange(pageIndex) {
        this.pageSize = pageIndex
        userHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        userHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      initList() {
        userHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list;
          this.total = res.data.total
          this.pageNum = res.data.pageNum
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
