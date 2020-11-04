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
        <!--  <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>-->
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

        <!--<el-table-column prop="customerResp.cusName" label="客户"></el-table-column> prop="order.ordTheme"-->
        <el-table-column prop="szOrder.ordTheme"  label="对应订单主题" ></el-table-column>
        <el-table-column prop="planMoney" label="回款金额" sortable></el-table-column>
        <el-table-column prop="planTime" label="计划回款时间" sortable>
          <template slot-scope="scope">
            {{scope.row.planTime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="planPeriod" label="回款期次" sortable></el-table-column>
        <el-table-column prop="empResp.empName" label="操作人" sortable></el-table-column>

        <el-table-column prop="planCaozuotime" label="操作时间" sortable>
          <template slot-scope="scope">
            {{scope.row.planCaozuotime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="planInvoice" label="开票">
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
      <el-dialog title="回款计划添加" :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" label-width="80px" ref="addFormRef"
               label-position="right" :rules="FormRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="选择订单" >
              <el-select v-model="addForm.ordId" placeholder="请选择订单" size="small" @change="oidChange">
                <el-option v-for="item in ordList" :key="item.ordId"
                           :label="item.ordTheme" :value="item.ordId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总金额" size="small">  <!--clearable :disabled="true"-->
              <el-input v-model="addForm.planMoney" size="mini" >

                </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="回款日期">
              <el-date-picker v-model="addForm.planTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date"
                              placeholder="请选择计划回款日期" size="small"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作人">
              <el-select v-model="addForm.empId">
                <el-option v-for="item in empList" :key="item.empId"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
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

<!--修改-->
    <el-dialog
      title="修改计划"
      :visible.sync="editDialog"
      width="50%"
      @close="editHandleClose">
      <el-form ref="editFormRef" :model="editForm" :rules="editFormRules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="计划回款时间" prop="planTime">
              <el-input v-model="editForm.planTime"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="8">
           <!-- <el-form-item label="执行人" prop="customerExecutor">
              <el-select v-model="editForm.empId">
                <el-option v-for="item in edpList" :key="item.empId"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
            </el-form-item>-->
          </el-col>

        </el-row>
      </el-form>

      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editPlanClick"
                   :loading="editPlanButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {planHttp} from "../../network/system/plan";
  import {orderHttp} from "../../network/system/order";
  import {userHttp} from "../../network/system/user";
  import {productHttp} from "../../network/system/product";

  export default {
    name: "SzReceivavlePlan",
    data() {
      return {
        searchInput:'',
        empList:[],
        ordList:[],
        addForm:{
          ordId:'',
          empId:'',
          planMoney:'',
          planTime:'',

        },
        editForm:{
        },
        editFormRules:{

        },
        FormRules:{
        },
        rowplanId: 0,
        tableLoading:false,
        buttonDisabled:true,
       /* advancedSearch:false,高级查询*/
        addDialog:false,
        addPlanButtonLoading:false,
        editPlanButtonLoading:false,
        listForm:[],
        ordList:[],
        pageNum:1,
        pageSize:5,
        total:1,
        editDialog:false,
        multipleSelection: []
      }
    },
    methods: {
      oidChange(val) {
        console.log(val)
        orderHttp.list(this.addDialog).then(res=>{
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },

      searchInputClick() {
        this.listForm.planCaozuopeople = this.searchInput
        planHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },      handleSelectionChange(val) {
        this.multipleSelection = val;
      },

      addPlanClick(){
        planHttp.addplan(this.addForm).then(res => {
          console.log("kkk",this.addForm)
          /*if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.addDialog = false
            this.addPlanButtonLoading = false
          } else {
            this.$message({
              message:res.message,
              type:"error"
            })
            this.addPlanButtonLoading = false
          }*/
        })
      },

      openAddDialog() {
        this.addDialog = true
        this.initEmpList()
        this.initOrderList()
      },
      /*新增选择员工*/
      initEmpList(){
        userHttp.list().then(res =>{
          this.empList = res.data.list
        })
      },
      /*新增选择订单*/
      initOrderList(){
        orderHttp.list().then(res=>{
          this.ordList=res.data.list
        })
      },
      /*高级搜索*/
     /* resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowplanId = 0
        this.buttonDisabled = true
      },*/
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
      },
      addHandleClose(){
        this.$refs["addform"].resetFields()
      },
      /*点击修改按钮获取改行id*/
      openEditPlan(){
        this.editDialog=true;
        this.getEditPlan()
      },
      delPlan(planId){
        this.$confirm('确定删除此订单吗','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          planId = this.rowplanId
          planHttp.delPlan(planId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message({
                message:res.message,
                type:'error'
              })
            }
          })
        })
      },

      getEditPlan(){
        planHttp.getplan(this.rowplanId).then(res=>{
          console.log("获得修改数据：",res.data);
          /*this.editForm.empName=res.data.empResp.empName
          console.log("this.editForm.empName:",this.editForm)*/
        })
      },
      editPlanClick(){
        this.editPlanButtonLoading=true
        /*this.editForm.cusId=this.rowplanId*/
        planHttp.editplan(this.editForm).then(res=>{
          console.log("111")
        })
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
        this.rowplanId= row.planId
        if (this.rowplanId != 0) {
          this.buttonDisabled = false
        }
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
      /*initCusList() {
        orderHttp.list().then(res => {
          this.cusList = res.data
        })
      },*/
      initList() {
        planHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
          console.log("ppp:",res.data.list)
        })
      },
      resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowplanId = 0
        this.buttonDisabled = true
      },
    },
    created() {
      this.initList()
      this.initOrderList()
      this.initEmpList()
    }
  }
</script>

<style scoped>

</style>
