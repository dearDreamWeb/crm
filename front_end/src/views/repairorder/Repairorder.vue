<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入状态内容查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">新增</el-button>
          <el-button size="mini" type="primary" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditCare">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="deleteCare">删除</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
          <el-form :model="searchForm" ref="advancedSearchFormRef"
                   size="mini" label-position="right" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item prop="careZt" label="部门">
                  <el-select v-model="searchForm.deptId">
                    <el-option v-for="item in deptList" :key="item.deptId"
                               :label="item.deptName" :value="item.deptId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="repairSfzb" label="联系人">
                  <el-input v-model="searchForm.repairSfzb" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="客户">
                  <el-select v-model="searchForm.cusId">
                    <el-option v-for="item in empList" :key="item.cusId"
                               :label="item.cusName" :value="item.cusId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item prop="careexecutor" label="接单人">
                  <el-select v-model="searchForm.empId">
                    <el-option v-for="item in edpList" :key="item.empId"
                               :label="item.empName" :value="item.empId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="产品">
                  <el-select v-model="searchForm.productId">
                    <el-option v-for="item in productList" :key="item.productId"
                               :label="item.productName" :value="item.productId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item>
                  <el-button size="mini" @click="advancedQueryClick"
                             type="primary" icon="el-icon-search"></el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </transition>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="40"></el-table-column>
        <el-table-column prop="szOrder.ordId" label="订单Id"></el-table-column>
        <el-table-column prop="customerResp.cusName" label="对应客户"></el-table-column>
        <el-table-column prop="szOrder.ordConsignee" label="联系人"></el-table-column>
        <el-table-column prop="repairWxfy" label="费用"></el-table-column>
        <el-table-column prop="repairGdstae" label="状态"></el-table-column>
<!--        <el-table-column prop="deptResp.deptName" label="维修部门"></el-table-column>-->
        <el-table-column prop="productResp.productName" label="维修产品"></el-table-column>
<!--        <el-table-column prop="empResp.empName" label="接单人"></el-table-column>-->
        <el-table-column prop="careData" label="日期">
          <template slot-scope="scope">
            {{scope.row.repairDate | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="repairsjhm" label="是否在保"></el-table-column>
      </el-table>

      <el-pagination background
                                      @current-change="handleCurrentChange"
                                      :current-page="pageNum" :page-sizes="[1,2,5,10]"
                                      :page-size="pageSize" :total="total"
                                      layout="prev, pager, next, jumper, total">
    </el-pagination>
    </el-card>

    <el-dialog :visible.sync="Dingda">
    <el-table :data="listDingda">
      <el-table-column prop="ordId" label="订单Id"></el-table-column>
      <el-table-column prop="ordTheme" label="主题"></el-table-column>
      <el-table-column prop="customerResp.cusName" label="客户"></el-table-column>
      <el-table-column prop="ordHead" label="负责人"></el-table-column>
      <el-table-column prop="ordConsignee" label="收货人"></el-table-column>
<!--      <el-table-column prop="productResp.productName" label="产品"></el-table-column>-->
      <el-table-column prop="ordPhone" label="手机号码"></el-table-column>
      <el-table-column width="80" label="操作" >
        <template slot-scope="scope">
          <el-button type="text" size="small" icon="el-icon-plus" @click="addpro(scope.row.ordId,scope.row.customerResp.cusId)" :disabled="isDisable"></el-button>
        </template>
      </el-table-column>
    </el-table>
      <el-pagination background
                     @current-change="handleCurrentChangedd"
                     :current-page="pageNum1" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize1" :total="total1"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
  </el-dialog>
    <el-dialog
      title="新增"
      :visible.sync="addDialog"
      width="50%"
      @close="handleClose">

      <el-form ref="addformRef" :model="addform" :rules="rules" label-width="80px">

        <el-row>
          <el-col :span="8">
            <el-form-item label="订单编号" prop="orderId">
              <el-input
                placeholder="请输入内容"
                v-model="addform.orderId"
                :disabled="true">
              </el-input>
            </el-form-item>
          </el-col>
            <el-col :span="8">
              <el-form-item>
                <el-button size="mini" type="primary" icon="el-icon-plus" @click="xians"></el-button>
              </el-form-item>
            </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="联系人" prop="repairLxr">
              <el-input v-model="addform.repairLxr"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="日期">
              <el-date-picker type="date" placeholder="选择日期" v-model="addform.repairDate" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="接单人">-->
<!--              <el-select v-model="addform.empId">-->
<!--                <el-option v-for="item in edpList" :key="item.empId"-->
<!--                           :label="item.empName" :value="item.empId">-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label="状态">
              <el-select v-model="addform.repairGdstae" placeholder="请选择">
                <el-option label="执行中" value="执行中"></el-option>
                <el-option label="结束" value="结束"></el-option>
                <el-option label="未执行" value="未执行"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="保修">
              <el-select v-model="addform.repairSfzb" placeholder="请选择">
                <el-option label="在保" value="在保"></el-option>
                <el-option label="出保" value="出保"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="故障描述">
              <el-input type="textarea" v-model="addform.repairFault"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="维修产品">
              <el-select v-model="addform.productId">
                <el-option v-for="item in productList" :key="item.productId"
                           :label="item.productName" :value="item.productId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="费用">
              <el-input v-model="addform.repairWxfy"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div style="text-align: center;">
        <el-button @click="addDialog = false">取 消</el-button>
        <el-button type="primary" @click="addClick"
                   :loading="addDictButtonLoading">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改"
      :visible.sync="editDialog"
      width="50%"
      @close="editHandleClose">
      <el-form ref="updateform" :model="updateform" :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="联系人">
                <el-input v-model="updateform.repairSfzb"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="状态">
                <el-select v-model="updateform.repairGdstae" placeholder="请选择">
                  <el-option label="执行中" value="执行中"></el-option>
                  <el-option label="结束" value="结束"></el-option>
                  <el-option label="未执行" value="未执行"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="客户">
                <el-input
                  placeholder="请输入内容"
                  v-model="updateform.cusName"
                  :disabled="true">
                </el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="日期">
                <el-date-picker type="date" placeholder="选择日期" v-model="updateform.repairDate" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
<!--            <el-col :span="8">-->
<!--              <el-form-item label="接单人">-->
<!--                <el-select v-model="updateform.empId">-->
<!--                  <el-option v-for="item in edpList" :key="item.empId"-->
<!--                             :label="item.empName" :value="item.empId">-->
<!--                  </el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
            <el-col :span="8">
              <el-form-item label="费用">
                <el-input
                  placeholder="请输入内容"
                  v-model="updateform.repairWxfy"
                  :disabled="true">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="维修产品">
                <el-select v-model="updateform.productId">
                  <el-option v-for="item in productList" :key="item.productId"
                             :label="item.productName" :value="item.productId">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="保修">
                <el-select v-model="updateform.repairsjhm" placeholder="请选择">
                  <el-option label="在保" value="在保"></el-option>
                  <el-option label="出保" value="出保"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="故障描述">
                <el-input type="textarea" v-model="updateform.repairFault"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
<!--            <el-col :span="8">-->
<!--              <el-form-item label="维修部门">-->
<!--                <el-select v-model="updateform.deptId">-->
<!--                  <el-option v-for="item in deptList" :key="item.deptId"-->
<!--                             :label="item.deptName" :value="item.deptId">-->
<!--                  </el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :span="8">-->
<!--              <el-form-item label="费用">-->
<!--                <el-input v-model="updateform.repairWxfy"></el-input>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
          </el-row>
        </el-form>
      <div style="text-align: center;">
        <el-button @click="editDialog = false">取 消</el-button>
        <el-button type="warning" @click="editCareClick" :loading="editDictButtonLoading">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {repairHttp} from "../../network/system/repair";
import {userHttp} from "../../network/system/user";
  import {customerHttp} from "../../network/pre_sale/customer";
import {deptHttp} from "../../network/system/dept";
import {productHttp} from "../../network/system/product";
import {orderHttp} from "../../network/system/order";
import {followHttp} from "../../network/pre_sale/followlog";

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
          repairDate: '',
          repairProblem:'',
          repairPersonnel:'',
          repairAppointment:'',
          repairActual:'',
          repairGdstae:'',
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
          repairGdstae:'',
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
          repairGdstae:'',
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
        num: 1,
        rules:{
          ordId:[
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            { min: 4, max: 8, message: '长度在 4 到 8个字符', trigger: 'blur' }
          ]

        }
      }
    },
    methods:{
      openAddDialog() {
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
          repairHttp.add(this.addform).then(res => {
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
      deleteCare() {
        this.$confirm('此操作将永久删除，是否继续','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          repairHttp.del(this.rowCareId).then(res => {
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
        this.listForm.repairGdstae = this.searchInput
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

<style>
</style>
