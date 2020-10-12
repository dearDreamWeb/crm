<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入内容" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">添加订单</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditDept">修改订单</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delorder">删除订单</el-button>
        </el-col>
      </el-row>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="orderName" label="负责人" sortable></el-table-column>
        <el-table-column prop="orderName" label="总金额" sortable></el-table-column>
        <el-table-column prop="orderName" label="创建时间" sortable></el-table-column>
        <el-table-column prop="orderName" label="成交时间" sortable></el-table-column>
        <el-table-column prop="orderName" label="发货时间" sortable></el-table-column>
        <el-table-column prop="orderName" label="回款金额" sortable></el-table-column>
      </el-table>
    </el-card>

  </div>
</template>

<script>
  import {deptHttp} from "../../network/system/dept";

  export default {
    name: "Order",
    data() {
      return {
        searchInput:'',
        editDialog:false,
        editForm:{},
        editDeptButtonLoading:false,
        addDeptButtonLoading:false,
        formRules:{
          orderName:[
            {required:true,message:'请输入订单名称',trigger:'blur'},
          ]
        },
        addForm: {
          orderName:''
        },
        addDialog:false,
        rowDeptId: 0,
        tableLoading:false,
        buttonDisabled:true,
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1
      }
    },
    methods: {
      searchInputClick() {
        this.listForm.orderName = this.searchInput
        deptHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      resetForm() {
        this.initDept()
        this.searchInput = ''
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editDeptButtonLoading = false
      },
      editDeptClick() {
        this.editDeptButtonLoading = true
        this.editForm.deptId = this.rowDeptId
        deptHttp.editDept(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initDept()
            this.editDeptButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editDeptButtonLoading = false
          }
        })
      },
      openEditDept() {
        this.editDialog = true
        this.getDeptDetail()
      },
      getDeptDetail() {
        deptHttp.getDept(this.rowDeptId).then(res => {
          this.editForm = res.data
        })
      },

      openAddDialog() {
        this.addDialog = true
      },
      addDeptClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addDeptButtonLoading = true
          deptHttp.addDept(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initDept()
              this.addDeptButtonLoading = false
              this.addDialog = false
            } else {
              this.addDeptButtonLoading = false
              this.$message({
                message:res.message,
                type:'error'
              })
            }
          })
        })
      },
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addDeptButtonLoading = false
      },
      handleRowClick(row,event,column) {
        this.rowDeptId = row.deptId
        if (this.rowDeptId != 0) {
          this.buttonDisabled = false
        }
      },
      initDept() {
        deptHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
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
      this.initDept()
    }
  }
</script>

<style scoped>

</style>
