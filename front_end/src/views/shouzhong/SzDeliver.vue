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

    <el-dialog title="添加发货" :visible.sync="addDialog" >
      <el-table
        ref="multipleTable"
        :data="szorder"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
          <el-table-column property="ordId" label="订单编号" width="180"></el-table-column>
          <el-table-column property="ordTheme" label="订单主题" width="200"></el-table-column>
          <el-table-column property="ordHead" label="负责人" width="160"></el-table-column>
        </el-table>

      <el-form :model="addForm" label-width="100px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="发货单号" prop="delWuliuid">
              <el-input v-model="addForm.delWuliuid" size="mini" placeholder="请输入发货单号" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物流公司" prop="delCompany" >
              <el-select v-model="addForm.delCompany" clearable>
                <el-option label="中通快递" value="中通"></el-option>
                <el-option label="韵达快递" value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="发货人" prop="delPeople">
              <el-input v-model="addForm.delPeople" size="mini" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addDeliverClick"
                   :loading="addDeliverButtonLoading">确定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import {deliverHttp} from "../../network/system/deliver";
  import {orderHttp} from "../../network/system/order";

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
        addForm:{
          delWuliuid:"",
          delPeople:"",
          delCompany:""
        },
        addFormOrd:{
          delWuliuid:"",
          delPeople:"",
          delCompany:"",
          ordId:""
        },
        formRules:{
          /* ordHead:[
             {required:true,message:'请输入订单名称',trigger:'blur'},
           ]*/
        },
        editDialog:false,
        addDeliverButtonLoading:false,
        szorder:[],
        multipleSelection: []
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
      },      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
,
      openAddDialog(){
        this.addDialog = true;
        deliverHttp.andall().then(res=>{
          this.szorder=res;

        })
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
      handleRowClick(row,event,column) {
        this.rowdelId= row.delId
        if (this.rowDelId != 0) {
          this.buttonDisabled = false
        }
      },
      addDeliverClick(){
        for (let i = 0; i <this.multipleSelection.length ; i++) {
         /* alert(this.multipleSelection[i].ordId);*/
          this.addFormOrd.ordId=this.multipleSelection[i].ordId;
          this.addFormOrd.delCompany=this.addForm.delCompany;
          this.addFormOrd.delPeople=this.addForm.delPeople;
          this.addFormOrd.delWuliuid=this.addForm.delWuliuid;
          deliverHttp.addANDord(this.addFormOrd).then(res=>{

          })
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
