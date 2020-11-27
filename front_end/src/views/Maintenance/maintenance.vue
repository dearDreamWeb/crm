<template>
<div>
  <el-card>
    <el-row>
      <el-col :span="6">
      <el-input v-model="searchInput" size="mini" placeholder="请输入状态内容查询" clearable>
        <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </el-col>
    </el-row>
  <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
            :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
            highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
    <el-table-column type="index" width="40"></el-table-column>
    <el-table-column prop="customerResp.cusName" label="对应客户"></el-table-column>
<!--    <el-table-column prop="repairSfzb" label="联系人"></el-table-column>-->
    <el-table-column prop="szOrder.ordPhone" label="手机号码"></el-table-column>
    <el-table-column prop="szOrder.ordConsignee" label="联系人"></el-table-column>
    <!--        <el-table-column prop="deptResp.deptName" label="维修部门"></el-table-column>-->
    <el-table-column prop="szOrder.ordTheme" label="维修产品"></el-table-column>
    <el-table-column prop="repairFault" label="故障描述"></el-table-column>
    <el-table-column prop="repairsjhm" label="是否在保"></el-table-column>
    <el-table-column prop="empResp.empName" label="维修人"></el-table-column>
    <el-table-column prop="repairProblem" label="类型"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button v-if="scope.row.repairGdstate != '已派单'"
              size="mini"
              @click="openAddDialog(scope.row.repairId)">派单</el-button>
            <el-button v-if="scope.row.repairGdstate == '已派单'"
              size="mini"
              type="danger"
              @click="updateCare(scope.row.repairId)">取消</el-button>
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
  <el-dialog
    title="新增"
    :visible.sync="addDialog"
    width="50%"
    @close="handleClose">

    <el-form ref="addformRef" :model="addform" :rules="rules" label-width="80px">
     <el-row>
       <el-col :span="8">
         <el-form-item label="维修人" prop="empId">
           <el-select v-model="addform.empId">
             <el-option v-for="item in edpList" :key="item.empId"
                        :label="item.empName" :value="item.empId">
             </el-option>
           </el-select>
         </el-form-item>
       </el-col>
       <el-col :span="8">
         <el-form-item label="类型" prop="repairProblem">
           <el-select v-model="addform.repairProblem">
             <el-option label="上门" value="上门"></el-option>
             <el-option label="电话" value="电话"></el-option>
           </el-select>
         </el-form-item>
       </el-col>
     </el-row>
    </el-form>
    <div style="text-align: center;">
      <el-button @click="addDialog = false">取 消</el-button>
      <el-button type="primary" @click="addClick()"
                 :loading="addDictButtonLoading">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
  import {repairHttp} from "../../network/system/repair";
  import {orderHttp} from "../../network/system/order";
  import {userHttp} from "../../network/system/user";
  import {deptHttp} from "../../network/system/dept";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {productHttp} from "../../network/system/product";

  export default {
    data() {
      return {
        deptList:[],
        empList:[],
        edpList:[],
        productList:[],
        isDisable:false,
        addform:{
          empId:'',
          cusId: '',
          repairDate: '',
          repairProblem:'',
          repairPersonnel:'',
          repairAppointment:'',
          repairActual:'',
          repairGdstate:'',
          region: '',
          repairsjhm:'',
          repairLxr:'',
          date1: '',
          repairFault: '',
          repairHfjl:'',
          repairWxfy:'',
          repairSfzb:'',
          cusId:'',
          deptId:'',
          productId:'',
          orderId:'',
          region: '',
          date1: '',
        },
        rowCareId:0,
        searchForm:{
          empId:'',
          repairDate: '',
          repairProblem:'',
          repairPersonnel:'',
          repairAppointment:'',
          repairActual:'',
          repairGdstate:'',
          region: '',
          date1: '',
          repairLxr:'',
          repairFault: '',
          repairHfjl:'',
          repairWxfy:'',
          repairSfzb:'',
          cusId:'',
          deptId:'',
          productId:'',
          orderId:'',
          region: '',
          date1: '',

          delivery: false,
          type: [],
          pageNum1:1,
          pageSize1:8,
          total1:0,
          resource: '',
          desc: ''
        },
        advancedSearch:false,
        updateform:{},
        addDialog:false,
        Dingda:false,
        buttonDisabled:true,
        addDictButtonLoading:false,
        Dingddd:false,
        editDictButtonLoading:false,
        editDialog:false,
        searchInput:'',

        listForm:[],
        listDingda:[],
        tableLoading:'',
        total:0,
        pageNum:1,
        pageSize:3,
        value: '',
        input: '',

        tableData: [],
        form: {
          empId:'',
          deptId:'',
          repairDate: '',
          repairProblem:'',
          repairPersonnel:'',
          repairAppointment:'',
          repairActual:'',
          repairGdstate:'',
          region: '',
          date1: '',
          repairLxr:'',
          repairFault: '',
          repairHfjl:'',
          repairWxfy:'',
          repairSfzb:'',
          cusId:'',
          productId:'',
          orderId:'',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        repairId:'',
        rules:{
          empId:[
            {required: true, message: '请选择员工', trigger: 'blur'},
          ]

        }
      }
    },
    methods: {
      openAddDialog(id) {
        console.log(id)
        this.repairId = id
        this.addDialog = true
        this.initEmpList()
        this.initEdpList()
        this.initDeptList()
        this.initProductList()
      },
      xians(){
        this.Dingda = true
        orderHttp.list(this.listDingda).then(res=>{
          this.listDingda = res.data.list
          this.total1 = res.data.total1
          this.pageNum1 = res.data.pageNum1
        })
      },
      addpro(ordId,cusId,productId) {
        this.Dingda = false
        console.log(ordId)
        console.log(cusId)
        console.log(productId)
        this.addform.orderId = ordId
        this.addform.cusId = cusId
        this.addform.productId = productId
        orderHttp.getOrder(ordId).then(res => {
          this.listDingda = res.data
        })
      },

      initEdpList(){
        userHttp.list().then(res =>{
          this.edpList = res.data.list
        })
      },
      initDeptList(){
        deptHttp.listAll().then(res =>{
          this.deptList = res.data.list
        })
      },
      initEmpList() {
        customerHttp.listAll().then(res => {
          this.empList = res.data
        })
      },
      initProductList(){
        productHttp.listAll().then(res =>{
          this.productList = res.data.list
        })
      },
      addClick(){
        this.addDictButtonLoading = true
        this.addform.repairGdstate = '已派单'
        this.addform.repairId =this.repairId;
        console.log(this.addform)
        repairHttp.updatePaidan(this.addform).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.addDialog = false
            this.addDictButtonLoading = false
          } else {
            this.$message({
              message:res.message,
              type:"error"
            })
            this.addDictButtonLoading = false
          }
        })

      },
      addDid(){
        console.log(this.$refs)
        this.$refs["addform"].validate(valid => {
          if (!valid) return
          this.Dingddd = true
          orderHttp.add(this.addform).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.Dingda = false
              this.Dingddd = false
            } else {
              this.$message({
                message:res.message,
                type:"error"
              })
              this.Dingddd = false
            }
          })
        })
      },
      updateCare(id) {
        this.$confirm('此操作将取消派单，是否继续','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let p = {
            'repairId': id
          }
          repairHttp.updateQuxiao(p).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message.error(res.message)
            }
          })
        })
      },

      resetForm(){
        this.$refs["advancedSearchFormRef"].resetFields()
        this.searchInput = ''
        this.initList()
        this.rowCareId = 0
        this.buttonDisabled = true
      },
      advancedQueryClick(){
        repairHttp.queryEmp(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },

      editCareClick(){
        this.editDictButtonLoading = true
        this.updateform.repairId = this.rowCareId
        repairHttp.update(this.updateform).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editDictButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editDictButtonLoading = false
          }
        })
      },
      openEditCare(){
        this.editDialog = true;
        this.getEmpDetail()
      },
      getEmpDetail(){
        repairHttp.get(this.rowCareId).then(res =>{
          console.log("编辑获得的数据",res.data);
          this.updateform = res.data;
          this.updateform.cusName = res.data.customerResp.cusName
          this.updateform.empName = res.data.empResp.empName
          this.updateform.deptName = res.data.deptResp.deptName
        })
      },
      editHandleClose() {
        this.$refs["updateform"].resetFields()
        this.editDictButtonLoading = false
      },

      addHandleClose() {
        this.$refs["addform"].resetFields()
      },

      searchInputClick(){
        this.listForm.repairsjhm = this.searchInput
        repairHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      handleClose(){
        this.$refs.addformRef.resetFields()
      },
      handleRowClick(row,event,column) {
        this.rowCareId = row.repairId
        if (this.repairId != 0) {
          this.buttonDisabled = false
        }
      },
      iHeaderCellStyle(){

      },
      iHeaderRowStyle(){

      },
      handleCurrentChange(pageIndex){
        this.searchForm.pageNum = pageIndex
        this.searchForm.pageSize = this.pageSize
        repairHttp.queryEmp(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      handleCurrentChangedd(pageIndex){
        this.listDingda.pageNum1 = pageIndex
        this.listDingda.pageSize1 = this.pageSize
        orderHttp.list(this.listDingda).then(res => {
          this.listDingda = res.data.list
          this.total1 = res.data.total1
          this.pageNum1 = res.data.pageNum1
        })
      },

      initList() {
        repairHttp.listPage(this.pageNum, this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
    },
    created() {
      this.initList()
      this.initEmpList()
      this.initEdpList()
      this.initDeptList()
      this.initProductList()
    }
  }
</script>

<style scoped>

</style>
