<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="根据操作人查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">添加回款计划</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditPlan">修改回款计划</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delPlan">删除回款计划</el-button>
        </el-col>
      </el-row>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <!--<el-table-column prop="customerResp.cusName" label="客户"></el-table-column>-->
        <el-table-column prop="order.ordTheme" label="订单主题" sortable></el-table-column>

        <el-table-column prop="planMoney" label="回款金额" sortable></el-table-column>

        <el-table-column prop="planTime" label="计划回款时间" sortable>
          <template slot-scope="scope">
            {{scope.row.planTime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="planPeriod" label="回款期次" sortable></el-table-column>
        <el-table-column prop="planCaozuopeople" label="操作人" sortable></el-table-column>

        <el-table-column prop="planCaozuotime" label="操作时间" sortable>
          <template slot-scope="scope">
            {{scope.row.planCaozuotime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="planInvoice" label="开票" sortable>
          <template slot-scope="scope">
            {{scope.row.planInvoice | planInvoiceFormat}}
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
<!--width="65%" top="20px"-->
      <el-dialog title="回款计划添加"  :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" label-width="80px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="8">
            <el-form-item label="1" prop="ordTheme">
              <el-input v-model="addForm.ordTheme" size="mini" placeholder="请输入主题" clearable/>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="总金额" prop="ordTotalmoney">
              <el-input v-model="addForm.ordTotalmoney" size="mini" placeholder="请输入总金额" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addPlanClick"
                   :loading="addPlanButtonLoading">确定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import {planHttp} from "../../network/system/plan";

  export default {
    name: "SzReceivavlePlan",
    data() {
      return {
        searchInput:'',

        addForm:{
        },
        editForm:{
        },
        formRules:{
        },
        rowdelId: 0,
        tableLoading:false,
        buttonDisabled:true,
        advancedSearch:false,
        addDialog:false,
        addPlanButtonLoading:false,
        listForm:[],
        pageNum:1,
        pageSize:2,
        total:1,
        editDialog:false,
        multipleSelection: []
      }
    },
    methods: {
      searchInputClick() {
        this.listForm.planCaozuopeople = this.searchInput
        planHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
      ,
      openAddDialog() {
        this.addDialog = true
      },
      addPlanClick(){

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

      },
      openEditPlan(){
      },
      delPlan(){

      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.pageSize = this.pageSize
        planHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
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
        planHttp.listPage(this.pageNum,this.pageSize).then(res => {
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
