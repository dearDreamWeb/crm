<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="发货单号查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">添加发货</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditDeliver">修改发货单</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delDeliver">删除发货单</el-button>
        </el-col>
      </el-row>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="delDelivertime" label="发货时间" sortable>
          <template slot-scope="scope">
            <el-tag>{{scope.row.delDelivertime | dateFormat}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="delWuliuid" label="发货单号" sortable></el-table-column>
        <el-table-column prop="szOrder.ordHead" label="订单" sortable>
      </el-table-column>
        <el-table-column prop="delPeople" label="发货人" sortable></el-table-column>
        <el-table-column prop="delState" label="发货状态" sortable>
          <template slot-scope="scope">
            {{scope.row.delState | delStateFormat}}
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
  import {deliverHttp} from "../../network/system/deliver";

  export default {
    name: "SzDeliver",
    data() {
      return {
        searchInput:'',
        editForm:{

        },
        addDialog:false,
        rowdelId: 0,
        tableLoading:false,
        buttonDisabled:true,
        advancedSearch:false,
        listForm:[],
        pageNum:1,
        pageSize:5,
        total:1,
        formRules:{
          /* ordHead:[
             {required:true,message:'请输入订单名称',trigger:'blur'},
           ]*/
        },
        editDialog:false,
      }
    },
    methods: {
      searchInputClick() {
        this.listForm.delWuliuid = this.searchInput
        deliverHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      openAddDialog(){

      },
      resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowdelId = 0
        this.buttonDisabled = true
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
      },
      addHandleClose(){
        /*添加*/
      },
      openEditDeliver(){
        /*修改*/
      },
      delDeliver(){
        /*删除*/
      },

      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.pageSize = this.pageSize
        deliverHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
     /* openAddDialog() {
        this.addDialog = true
      },*/
      handleRowClick(row,event,column) {
        this.rowdelId= row.delId
        if (this.rowDelId != 0) {
          this.buttonDisabled = false
        }
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
      initList() {
        deliverHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
    },
    created() {
      this.initList()
    }
  }
</script>

<style scoped>

</style>
