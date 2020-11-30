
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
        <el-table-column prop="szOrder.ordTheme"  label="对应订单主题" ></el-table-column>
        <el-table-column prop="planPeriod" label="回款期次" ></el-table-column>
        <el-table-column prop="planCaozuopeople" label="操作人" ></el-table-column>
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
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" @click="chakan_record(scope.row.planId),dialogTableVisible = true">操作回款记录</el-button>
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
    <el-dialog :visible.sync="Dingda">
      <el-table :data="listDingda">
        <el-table-column prop="ordId" label="订单Id"></el-table-column>
        <el-table-column prop="ordTheme" label="主题"></el-table-column>
        <!--<el-table-column prop="customerResp.cusName" label="客户"></el-table-column>-->
        <el-table-column prop="ordHead" label="负责人"></el-table-column>
        <el-table-column prop="ordConsignee" label="收货人"></el-table-column>
        <!--      <el-table-column prop="productResp.productName" label="产品"></el-table-column>-->
        <el-table-column prop="ordPhone" label="手机号码"></el-table-column>
        <el-table-column width="80" label="操作" >
          <template slot-scope="scope">
            <el-button type="text" size="small" icon="el-icon-plus"
                       @click="addpro(scope.row.ordId)" :disabled="isDisable"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background
                     @current-change="handleCurrentChangedd"
                     :current-page="fukuan.pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="fukuan.pageSize" :total="fukuan.total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-dialog>
    <!---->
    <el-dialog title="回款计划添加" :visible.sync="addDialog" @close="addHandleClose" size="medium" top="20px">
      <el-form :model="addForm" label-width="80px" ref="addFormRef"
               label-position="right" :rules="FormRules">
        <div style="padding-bottom: 20px">
         <el-button size="mini" type="primary" icon="el-icon-plus" @click="xians">选择订单</el-button>
        </div>
<!--          <el-col :span="12">
            <el-form-item>
              <el-button size="mini" type="primary" icon="el-icon-plus" @click="xians"></el-button>
            </el-form-item>
          </el-col>-->
        <el-row>
          <el-col :span="12">
            <el-form-item label="关联订单" width="217px" >
              <el-input v-model="ordTheme" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总金额" size="medium" :disabled=false >
              <el-input v-model="ordTotalmoney" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="分期(可选)" :data="xuanfenqi">
              <el-select v-model="addForm.planPeriod" placeholder="请选择期次"  @change="fenqi" clearable>
                <el-option label="全款" value="1"></el-option>
                <el-option label="分3期(免手续费)" value="3"></el-option>
                <el-option label="分6期(免手续费)" value="6"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" height="36px">
            <el-form-item label="操作人" >
              <el-select v-model="addForm.empId">
                <el-option v-for="(item,i) in empList" :key="i"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!-- 分期表格-->
      <el-table :data="addrecord" style="text-align: center;" size="small" align="center">
        <el-table-column label="期次" align="center" width="140px">
          <template slot-scope="s">
            第{{s.row.recordPlan}}期
          </template>
        </el-table-column>
        <el-table-column prop="timePlan" label="还款时间" align="center"></el-table-column>
        <el-table-column prop="moneyPlan" label="还款金额" align="center"></el-table-column>
      </el-table>

      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addPlanClick"
                   :loading="addPlanButtonLoading">确定</el-button>
      </span>
    </el-dialog>
    <el-drawer
      :visible.sync="dialogTableVisible"
      direction="btt" size="60%"  width="80%"
      :with-header="false"
    >
      <div class="recordstyle">回款记录</div>
      <el-table :data="szrecord" :row-style="{height:'1px'}"
                :cell-style="{padding:'1px 0'}" height="300px">
        <el-table-column prop="recoId" label="回款记录编号" width="150" height="100px"></el-table-column>
        <el-table-column  label="回款期次" width="180px">
          <template slot-scope="recordPlans">
            第{{recordPlans.row.recordPlan}}期
          </template>
        </el-table-column>
        <el-table-column  label="最晚回款时间"  width="240px">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            {{scope.row.timePlan | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="recoTime" label="实际回款时间" width="240px">
          <template slot-scope="scope">
             <span v-if="scope.row.recoHasmoney > 0">
                <i class="el-icon-time"></i>
                {{scope.row.recoTime | dateFormat}}
             </span>
          </template>
        </el-table-column>
        <el-table-column prop="moneyPlan" label="应回款金额" width="200">
          <template slot-scope="scope">
            {{scope.row.moneyPlan}} 元
          </template>
        </el-table-column>
        <el-table-column  label="状态" width="200">
          <template slot-scope="scope">
              <span v-if="scope.row.recoHasmoney > 0 && scope.row.moneyPlan > scope.row.recoHasmoney">
                <el-tag type="warning">回款中</el-tag>
              </span>
            <span v-if="scope.row.moneyPlan == scope.row.recoHasmoney">
                <el-tag type="success">已回款</el-tag>
              </span>
            <span  v-if="scope.row.recoHasmoney == 0 || scope.row.recoHasmoney==null">
                <el-tag type="danger">未回款</el-tag>
              </span>
          </template>
        </el-table-column>
        <el-table-column  label="操作">
          <template slot-scope="scope">
             <span v-if="scope.row.recoHasmoney == 0 || scope.row.recoHasmoney==null">
                <el-button  @click="like_record(scope.row.recoId)" size="mini" plain>立即回款</el-button>
              </span>
            <span v-if="scope.row.moneyPlan == scope.row.recoHasmoney">
                <el-button @click="look_record(scope.row.recoId)" size="mini" plain>查看记录</el-button>
              </span>
            <span v-if="scope.row.recoHasmoney > 0 && scope.row.moneyPlan > scope.row.recoHasmoney">
                <el-button @click="again_record(scope.row.recoId)" size="mini" plain>继续回款</el-button>
              </span>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
    <!--立刻回款的弹窗-->
    <el-dialog :visible.sync="likeDialog" @close="likeHandleClose" title="回款记录">
      <el-form :model="likeForm"  label-width="100px" :rules="likeFormRules"
               label-position="right" ref="likeFormRef">
        <el-row>
          <el-col :span="11">
            <el-form-item label="回款记录编号" prop="recoId">
              <el-input v-model="likeForm.recoId" size="medium " :disabled="true" style="border: 0px"/>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="应回款金额" prop="moneyPlan">
              <el-input v-model="likeForm.moneyPlan" size="medium" :disabled="true"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="预计回款时间" prop="timePlan">
              <el-date-picker type="date" placeholder="选择日期"
                              v-model="likeForm.timePlan" style="width: 100%;":disabled="true">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="回款期次" prop="recordPlan">
              <el-input v-model="likeForm.recordPlan" size="medium " :disabled="true" style="border: 0px"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="已回款金额" prop="recoHasmoney">
              <el-input v-model="likeForm.recoHasmoney" size="medium " style="border: 0px" :disabled="true"/>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="回款金额" prop="recoMoney">
              <el-input v-model="likeForm.recoMoney" size="medium "/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="交易流水号" prop="recoLiushui" >
              <el-input v-model="likeForm.recoLiushui" size="medium"/>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="回款时间" prop="recoTime">
              <el-date-picker type="date" placeholder="选择日期"
                              v-model="likeForm.recoTime" style="width: 100%;">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="likeDialog = false">取消</el-button>
        <el-button type="primary" @click="like_recordClick"
                   :loading="like_recordButtonLoading">确定</el-button>
      </span>
    </el-dialog>
    <!--修改-->
    <!-- <el-dialog
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
         </el-row>
       </el-form>
       <span slot="footer">
         <el-button @click="editDialog = false">取消</el-button>
         <el-button type="primary" @click="editPlanClick"
                    :loading="editPlanButtonLoading">确定</el-button>
       </span>
     </el-dialog>-->
  </div>
</template>

<script>
  import {planHttp} from "../../network/system/plan";
  import {orderHttp} from "../../network/system/order";
  import {userHttp} from "../../network/system/user";

  export default {
    name: "SzReceivavlePlan",
    data() {
      return {
        searchInput:'',
        empList:[],
        ordList:[],
        fukuan:{
          total:0,
          pageNum:1,
          pageSize:5
        },
        addForm:{
          ordId:'',
          empId:'',
          planMoney:'',
          szReceivableRecorde:[]
        },
        likeForm:{},
        likeFormRules:{},
        editForm:{},
        editFormRules:{},
        FormRules:{},
        ordTotalmoney:0,
        rowplanId: 0,
        rowrecoId: 0,
        isDisable:false,
        Dingda:false,
        tableLoading:false,
        buttonDisabled:true,
        /* advancedSearch:false,高级查询*/
        addDialog:false,
        addPlanButtonLoading:false,
        like_recordButtonLoading:false,
        editPlanButtonLoading:false,
        dialogTableVisible:false,
        listDingda:[],
        listForm:[],
        ordList:[],
        addrecord:[],
        xuanfenqi:[],
        szrecord:[],
        record:[],
        pageNum:1,
        pageSize:5,
        total:1,
        editDialog:false,
        likeDialog:false,
        multipleSelection: [],
        suijishu:'',
      }
    },
    methods: {
      addpro(ordId) {
        this.addrecord=[]
        this.Dingda = false
        console.log(ordId)
        this.addForm.ordId = ordId
        orderHttp.getOrder(ordId).then(res => {
          this.ordTheme=res.data.ordTheme
          this.ordTotalmoney=res.data.ordTotalmoney
          console.log("2res:",res)
          console.log("2this.ordTheme:",this.ordTheme)
          console.log("2res.data.ordTheme:",res.data.ordTheme)
          this.listDingda = res.data
        })
      },
      xians(){
        this.Dingda = true
        orderHttp.listDialog(this.fukuan).then(res=>{
          this.listDingda = res.data.list
          this.fukuan.total = res.data.total
          this.fukuan.pageNum = res.data.pageNum
        })
      },
      /*      oidChange(val) {
              console.log("val:",val)
              //根据选中的id 查询单条即可
              orderHttp.getOrder(val).then(res=>{
                //绑定总金额
                this.ordTotalmoney=res.data.ordTotalmoney
                console.log("res:",res)
                console.log("this.ordTotalmoney:",this.ordTotalmoney)
                console.log("res.data.ordTotalmoney:",res.data.ordTotalmoney)
              })
            },*/
      searchInputClick() {
        this.listForm.planCaozuopeople = this.searchInput
        planHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      fenqi(){
        this.addrecord=[]
        console.log("1分期",this.addForm.planPeriod)
        let it = this.addForm.planPeriod; //选中的x期次
        let recordplan=0;
        let money=0; //前x-1期
        let qici=1;
        let qc=[];
        let yumoney=0;
        var nowDate = new Date();//时间
        for(var i=1; i<=it;i++){
          console.log("it:",it)
          console.log("addForm.planPeriod分期",this.addForm.planPeriod)
          var date = {
            year: nowDate.getFullYear(),
            month: nowDate.getMonth() + 1 +i,
            day: nowDate.getDate(),
          }
          if (date.month>12){
            date.year = date.year+1
            date.month=date.month-12
          }
          //分期小于循环
          if(this.addForm.planPeriod<=i){
            yumoney= parseFloat(this.ordTotalmoney - money);
            recordplan=yumoney;
            console.log("最后一期："+yumoney);
            console.log("recordplan",recordplan)
            this.addrecord.splice(i,0,{recordPlan:i,timePlan:date.year + '-' + ("0" + (date.month)).slice(-2) + '-' + date.day ,moneyPlan:recordplan.toFixed(2)})
            return false;
          }
          money+=parseInt(this.ordTotalmoney/this.addForm.planPeriod);
          console.log("金额：",money);
          console.log("第",i,"次循环");
          console.log("this.ordTotalmoney",this.ordTotalmoney);
          recordplan=parseInt(this.ordTotalmoney/this.addForm.planPeriod);
          qici++;
          qc.push(qici);
          this.addrecord.splice(i,0,{recordPlan:i,timePlan:date.year+ '-'  + ("0" + (date.month)).slice(-2) + '-'  + date.day , moneyPlan:recordplan.toFixed(2)})
        }
      },
      addPlanClick(){
        console.log("添加确定：",this.addForm)
        //this.addrecord.push(this.addForm.planPeriod);
        console.log("1this.addrecord",this.addForm.szReceivableRecorde)  //给planPeriod[]赋值
        this.addForm.szReceivableRecorde=this.addrecord
        planHttp.addplan(this.addForm).then(res => {
          console.log("kkk",this.addForm)
          if (res.code === 20000) {
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
          }
        })
      },
      openAddDialog() {
        this.addForm=[]
        this.addDialog = true
        this.initOrderList()
        this.initEmpList()
        console.log("addform：",this.addForm)
      },
      /*添加记录-选择员工*/
      initEmpList(){
        userHttp.list().then(res =>{
          this.empList = res.data.list
        })
      },
      /*添加记录-选择订单*/
      initOrderList(){
        orderHttp.list_all().then(res=>{
          this.ordList=res.data.list
        })
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
      },
      likeHandleClose(){
        this.$refs.likeFormRef.resetFields()
        this.like_recordButtonLoading=false
      },
      addHandleClose(){
        this.$refs.addFormRef.resetFields()
        this.addPlanButtonLoading = false
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

      /*立即回款*/
      like_recordClick(){
        this.like_recordButtonLoading=true
        console.log(this.likeForm.recoId)
        this.likeForm.recoId=this.likeForm.recoId
        planHttp.editrecordhas(this.likeForm).then(res=>{
          console.log("11111",this.likeForm);
          if (res.code === 20000) {
            this.$message.success(res.message)
            /*this.initList()*/
            /*this.chakan_record()*/
            this.like_recordButtonLoading = false
            this.likeDialog = false
          }else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.like_recordButtonLoading = false
            window.console.log(this.likeDialog)
          }
        })
      },
      /*  editPlanClick(){
          this.editPlanButtonLoading=true
          /!*this.editForm.cusId=this.rowplanId*!/
          planHttp.editplan(this.editForm).then(res=>{
            console.log("111")
          })
        },*/
      chakan_record(val){
        this.dialogTableVisible = true;
        planHttp.chakan_record(val).then(res=>{
          this.szrecord=res
          console.log("asdasdsad:",this.szrecord)
        })
      },
      like_record(val){
        this.likeDialog = true;
        planHttp.getrecord(val).then(res=>{
          this.likeForm=res.data
          this.date=new Date()
          this.likeForm.recoTime=this.date
          console.log("this.likeForm.recoTime",this.likeForm.recoTime)
        })
      },
      look_record(val){
        this.likeDialog = true;
        planHttp.getrecord(val).then(res=>{
          this.likeForm=res.data
          console.log(this.likeForm)
        })
      },
      again_record(val){
        this.likeDialog = true;
        planHttp.getrecord(val).then(res=>{
          this.likeForm=res.data
          console.log(this.likeForm)
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
      handleCurrentChangedd(pageIndex){
        this.fukuan.pageNum = pageIndex
        orderHttp.listDialog(this.fukuan).then(res => {
          this.listDingda = res.data.list
          this.fukuan.total = res.data.total
          this.fukuan.pageNum = res.data.pageNum
        })
      },
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
      this.initEmpList()
    }
  }
</script>

<style scoped>
  /*去掉打开抽屉时自动选中标题时的蓝色边框*/
  /deep/ :focus {
    outline: 0;
  }
  .recordstyle{
    text-align: center;
    width: 100%;
    font-size:20px;
    padding: 20px 0px;
    color: #656561;
    font-weight: 900;
  }
</style>
