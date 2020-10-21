<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入主题进行查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">添加订单</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditOrder">修改订单</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delOrder">删除订单</el-button>
        </el-col>
      </el-row>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="ordTheme" label="主题" sortable></el-table-column>
        <el-table-column prop="ordTotalmoney" label="总金额" sortable></el-table-column>
        <el-table-column prop="ordStarttime" label="开始时间" sortable>
          <template slot-scope="scope">
            {{scope.row.ordStarttime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="ordDealtime" label="成交时间" sortable>
          <template slot-scope="scope">
            {{scope.row.ordDealtime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="ordState" label="订单状态" sortable>
          <template slot-scope="scope">
            {{scope.row.ordState | ordStateFormat}}
          </template>
        </el-table-column>
        <!--<el-table-column prop="ordState" label="订单状态" sortable></el-table-column>-->
        <el-table-column prop="ordHead" label="负责人" sortable></el-table-column>
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
  import {deliverHttp} from "../../network/system/order";

  export default {
    name: "SzDeliver",
    data() {
      return {
        searchInput:'',
        editForm:{

        },
        addDialog:false,
        rowordId: 0,
        tableLoading:false,
        buttonDisabled:true,
        advancedSearch:false,
        listForm:[],
        pageNum:1,
        pageSize:5,
        total:1,
        formRules:{
          ordHead:[
            {required:true,message:'请输入订单名称',trigger:'blur'},
          ]
        },
        addOrderButtonLoading:false,
        editDialog:false,
        editOrderButtonLoading:false,
      }
    },
    methods: {
      searchInputClick() {
        this.listForm.ordTheme = this.searchInput
        orderHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowordId = 0
        this.buttonDisabled = true
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editOrderButtonLoading = false
      },
      addHandleClose(){
        /*添加*/
      },
      openEditOrder(){
        /*修改*/
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.pageSize = this.pageSize
        orderHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      openAddDialog() {
        this.addDialog = true
      },
      handleRowClick(row,event,column) {
        this.rowordId= row.ordId
        if (this.rowOrdId != 0) {
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
        orderHttp.listPage(this.pageNum,this.pageSize).then(res => {
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
