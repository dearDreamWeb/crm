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
        <!--<el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditDeliver">修改发货单</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delDeliver">删除发货单</el-button>
        </el-col>-->
      </el-row>
      <el-tabs v-model="activeName">
        <el-tab-pane label="未发货" name="not">
          <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                    :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                    highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
            <el-table-column prop="delId" width="150" label="发货单编号"></el-table-column>
            <el-table-column prop="szOrder.ordTheme"  label="对应订单" ></el-table-column>
            <el-table-column prop="szOrder.productReq.productName"  label="对应产品" ></el-table-column>
            <el-table-column prop="delExpecttime" label="预计发货时间" sortable>
              <template slot-scope="scope">
                {{scope.row.delExpecttime | dateFormat}}
              </template>
            </el-table-column>
            <el-table-column prop="delState" label="发货状态" sortable>
              <template slot-scope="scope">
                {{scope.row.delState | delStateFormat}}
              </template>
            </el-table-column>
            <el-table-column label="操作" >
              <template slot-scope="scope">
                <el-button type="success" plain size="mini" @click="fahuo(scope.row.delId)">去发货</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="已发货" name="has">
          <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                    :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                    highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
            <el-table-column prop="delId" width="150" label="发货单编号"></el-table-column>
            <el-table-column prop="szOrder.ordTheme"  label="对应订单" ></el-table-column>
            <el-table-column prop="szOrder.productReq.productName"  label="对应产品" ></el-table-column>
            <el-table-column prop="delDelivertime" label="发货时间" sortable>
              <template slot-scope="scope">
                {{scope.row.delDelivertime | dateFormat}}
              </template>
            </el-table-column>
            <el-table-column prop="delWuliuid" label="发货单号" sortable></el-table-column>
            <!--<el-table-column prop="szOrder.ordHead" label="订单" sortable>
            </el-table-column>-->
            <el-table-column prop="delPeople" label="发货人" sortable></el-table-column>
            <el-table-column prop="delState" label="发货状态" sortable>
              <template slot-scope="scope">
                {{scope.row.delState | delStateFormat}}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>

      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="发货" :visible.sync="FahuoTableVisible" width="65%" top="40px">
      <el-form :model="addForm" label-width="100px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="物流公司" prop="delCompany" >
              <el-select v-model="addForm.delCompany" style="width: 250px"  clearable>
                <el-option label="中通快递" value="中通"></el-option>
                <el-option label="韵达快递" value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="发货单号" prop="delWuliuid">
              <el-input v-model="addForm.delWuliuid" size="middle" placeholder="请输入发货单号" style="width: 250px" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <el-tabs type="border-card" >
        <el-tab-pane label="需发产品">
          <el-table :data="szProduct">
            <el-table-column prop="productId" label="编号"></el-table-column>
            <el-table-column prop="productName" label="产品名称"></el-table-column>
            <el-table-column prop="productModel" label="产品型号"></el-table-column>
            <el-table-column prop="productBrand" label="产品品牌"></el-table-column>
            <el-table-column prop="productSize" label="产品尺寸"></el-table-column>+
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="primary" plain size="mini" @click="productxq(scope.row)">产品详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <span style="margin-top: 20px">产品详情</span>
          <el-table :data="prxq">
            <el-table-column prop="productDetailId" label="产品详情id"></el-table-column>
            <el-table-column prop="productName" label="产品名称"></el-table-column>
            <el-table-column prop="productBarCode" label="产品序列号" ></el-table-column>
            <el-table-column label="操作" >
              <template slot-scope="scope">
                <el-button type="text" size="mini">添加</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="已选产品">
          <el-table>
            <el-table-column prop="productDetailId" label="产品详情号" width="150"></el-table-column>
            <el-table-column prop="productId" label="产品编号" width="200"></el-table-column>
            <el-table-column prop="" label="产品序列号" width="200"></el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>

      <span slot="footer">
        <el-button @click="DeliverDialog = false">取消</el-button>
        <el-button type="primary" @click="DeliverClick"
                   :loading="DeliverButtonLoading">确定</el-button>
      </span>
    </el-dialog>


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
  import {planHttp} from "../../network/system/plan";
  import {productHttp} from "../../network/system/product";

  export default {
    name: "SzDeliver",
    data() {
      return {
        /*选项卡默认选中未发货not*/
        activeName: 'not',
        searchInput:'',
        editForm:{
        },
        DeliverDialog:false,
        FahuoTableVisible:false,
        addDialog:false,
        rowdelId: 0,
        tableLoading:false,
        buttonDisabled:true,
        advancedSearch:false,
        listForm:[],
        prxq:[],
        pageNum:1,
        pageSize:5,
        total:1,
        addForm:{
          ordId:"",
          delWuliuid:"",
          delPeople:"",
          delCompany:""
        },
        formRules:{},
        DeliverButtonLoading:false,
        editDialog:false,
        addDeliverButtonLoading:false,
        szProduct:[],
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
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
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
        deliverHttp.getszDeliver(this.rowdelId).then(res=>{
          var deldeiver =res.data;
          if (deldeiver.delState==1){
            alert("发货订单不允许删除");
            return;
          }
        })
      },
      productxq(val){
        productHttp.getProduct(val.productId).then(res=>{
          console.log(res.data)
          this.prxq=res.data;
        })
      },
      fahuo(val){
        this.FahuoTableVisible = true;
          deliverHttp.getszDeliver(val).then(res=>{
            this.szProduct=res.data[0].productReq;
            console.log(res.data[0].productReq);
        })
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
      DeliverClick(){

      },
      addDeliverClick(){
        for (let i = 0; i <this.multipleSelection.length ; i++) {
           alert(this.multipleSelection[i].ordId);
          this.addForm.ordId=this.multipleSelection[i].ordId;
        }
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addDeliverButtonLoading = true
          deliverHttp.addANDord(this.addForm).then(res =>{
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.addDeliverButtonLoading = false
              this.addDialog = false
            } else {
              this.addDeliverButtonLoading = false
              this.$message({
                message:res.message,
                type:"error"
              })
            }
          })
        })
        /*for (let i = 0; i <this.multipleSelection.length ; i++) {
         /!* alert(this.multipleSelection[i].ordId);*!/
          this.addFormOrd.ordId=this.multipleSelection[i].ordId;
          this.addFormOrd.delCompany=this.addForm.delCompany;
          this.addFormOrd.delPeople=this.addForm.delPeople;
          this.addFormOrd.delWuliuid=this.addForm.delWuliuid;
          deliverHttp.addANDord(this.addFormOrd).then(res=>{

          })
        }*/
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
