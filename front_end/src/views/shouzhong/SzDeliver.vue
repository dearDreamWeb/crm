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
                <el-button type="success" plain size="mini" @click="fahuo(scope.row)">
                  <i class="el-icon-shopping-cart-1 "> </i>去发货
                </el-button>
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

    <el-dialog title="发货" :visible.sync="FahuoTableVisible" width="65%" top="20px" style="padding: 10px 20px;">
      <el-form :model="fahuoForm" label-width="100px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="物流公司" prop="delCompany" >
              <el-select v-model="fahuoForm.delCompany" style="width: 250px"  clearable  @change="gaibian">
                <el-option label="顺丰快递" value="0"></el-option>
                <el-option label="韵达快递" value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="发货单号" prop="delWuliuid">
              <el-input v-model="fahuoForm.delWuliuid" size="middle" placeholder="请输入发货单号"
                        style="width: 250px" clearable :readonly="true"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-tabs type="border-card" >
        <el-tab-pane label="需发产品">
          <el-table :data="szProduct">
            <el-table-column prop="productId" label="编号" width="70px"></el-table-column>
            <el-table-column prop="productName" label="产品名称"width="250px"></el-table-column>
            <el-table-column prop="productModel" label="型号"width="115px"></el-table-column>
            <el-table-column prop="productBrand" label="品牌" width="80px"></el-table-column>
            <el-table-column prop="productSize" label="尺寸" width="80px"></el-table-column>
            <el-table-column label="购买数量" width="80px">
              <template slot-scope="scope">
                <div v-for="x in szProducts" v-if="x.productId == scope.row.productId" >
                  {{x.ddetNum}}
                </div>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="100px">
              <template slot-scope="scope">
                <div v-for="x in szProducts" v-if="x.productId == scope.row.productId" >
                  <el-button type="primary" plain size="mini" @click="productxq(x,x.ddetNum)" >
                    产品详情
                  </el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 15px 10px 10px">产品详情</div>
          <el-table :data="prxq">
            <el-table-column prop="productDetailId" label="详情编号" width="100px"></el-table-column>
            <el-table-column label="产品名称">
              <template slot-scope="scope">
                <div v-for="y in szProduct" v-if="y.productId == scope.row.productId" >
                  {{y.productName}}
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="productBarCode" label="产品序列号" ></el-table-column>
            <el-table-column label="操作" width="100px">
              <template slot-scope="scope">
                <el-button type="warning" size="mini" :disabled="scope.row.status ==-1?true:false" @click="addbuy(scope.row)">添加</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination background
                         @current-change="handleCurrentChange_one"
                         :current-page="pageNum_one" :page-sizes="[1,2,5,10]"
                         :page-size="pageSize_one" :total="total_one"
                         layout="prev, pager, next, jumper, total">
          </el-pagination>
        </el-tab-pane>
        <el-tab-pane label="已选产品">
          <el-table :data="haspro">
            <el-table-column prop="productDetailId" label="产品详情编号" width="150"></el-table-column>
            <el-table-column prop="productId" label="产品编号" width="200"></el-table-column>
            <el-table-column prop="productBarCode" label="产品序列号"></el-table-column>
            <el-table-column label="操作" width="100px">
              <template slot-scope="scope">
                <el-button type="warning" size="mini" @click="delhaspro(scope.row.productDetailId)">删除</el-button>
              </template>
            </el-table-column>
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
              <el-input v-model="addForm.delWuliuid" size="mini" placeholder="请输入发货单号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物流公司" prop="delCompany" >
              <el-select v-model="addForm.delCompany" clearable>
                <el-option label="中通快递" value="0"></el-option>
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
        fahouid:0,
        fahuoddetId:0,
        /*选项卡默认选中未发货not*/
        activeName: 'not',
        searchInput:'',
        editForm:{
        },
        quanxian:true,
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
        suijishu:0,
        pageSize:5,
        total:1,
        pageNum_one:1,
        pageSize_one:5,
        total_one:1,
        fahuoForm:{
          delCompany:"",
          delWuliuid:""
        },
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
        haspro:[],
        szProducts:[],
        szProduct:[],
        szorder:[],
        multipleSelection: [],
        yangNumber:0,
        ddetNumber:0,
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
      /*删除已选产品*/
      delhaspro(inedx,row){
        var index = this.haspro.indexOf(row)
        this.haspro.splice(index,1)
      },
      /*选择产品添加*/
      addbuy(row){
        alert(row.productDetailId)
        console.log("添加")
        let seq={
          productDetailId:row.productDetailId,
          productBarCode:row.productBarCode,
          productId:row.productId
        }
        console.log(row);
        console.log(this.haspro);
        let deta = this.haspro;
        //已选产品全部数量
        console.log("deta.length::",deta.length);
        //购买数量
        console.log("this.ddetNumber::",this.ddetNumber);
        let idChangelength = 0 ;
        let i = 0;
        if(idChangelength > this.ddetNumber ){
          console.log(idChangelength,'>=',this.ddetNumber)
          for (let v of deta) {
            if (v.productId != row.productId ){
            }
          }
        }else{
          i++;
          idChangelength++;

          let result = this.haspro.filter(p=>{
            return p.productBarCode==seq.productBarCode
          });
          if(result && result.length>0){
            this.$message({
              message: '该产品序列号已选，可去已选产品中查看',
              type: 'warning'
            });
          }else{
            console.log("push!!")
            this.haspro.push(seq)
          }
          console.log(idChangelength,'<',this.ddetNumber)

        }
        console.log(i);
      },
      /*产品详情*/
      productxq(row,num){
        console.log("购买数量：",num)
        console.log("id：",row)
        this.ddetNumber = num;
        this.fahuoddetId =row.ddetId;
        productHttp.getProduct(row.productId).then(res=>{
          let demo = res.data.slice(0,num);
          this.prxq=demo;
          console.log(demo)
        })
      },
      /*去发货*/
      fahuo(val){
        this.fahuoForm={};
        this.prxq=[];
        this.haspro=[];
        this.fahouid=val.delId;
        this.FahuoTableVisible = true;
          deliverHttp.getszDeliver(val.delId).then(res=>{
            this.szProduct=res.data[0].productReq;
            console.log(res.data[0].productReq);
            console.log(res.data[0].productReq);
            this.szProducts=res.data[0].szDeliverDetails
            console.log(res.data[0].szDeliverDetails)
        })
      },
      /*根据选择物流公司 生成随机数改变快递单号*/
      gaibian(){
        this.suijishu=new Date().getTime()
        if(this.fahuoForm.delCompany==0){
          this.fahuoForm.delWuliuid="SF"+this.suijishu
        }else if(this.fahuoForm.delCompany==1){
          this.fahuoForm.delWuliuid="YD"+this.suijishu
        }
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
      handleCurrentChange_one(pageIndex) {
        this.pageNum_one = pageIndex
        this.pageSize_one = this.pageSize_one
        productHttp.listPage(this.pageNum_one,this.pageSize_one).then(res => {
          this.listForm = res.data.list
          this.total_one = res.data.total_one
          this.pageNum_one = res.data.pageNum_one
        })
      },
      handleRowClick(row,event,column) {
        this.rowdelId= row.delId
        if (this.rowDelId != 0) {
          this.buttonDisabled = false
        }
      },
      DeliverClick(){
        //获取已选产品中的数组
        if(this.haspro.length>0){
          console.log("this.haspro:",this.haspro)
          var hasproId=[];
          var addhaspro=[];
          var inputs=[];
          //循环得到（产品详情编号，发货详情编号）
          for(var i=0,n=this.haspro.length;i<n;i++){
            console.log("haspro中发货产品详情编号:",this.haspro[i].productDetailId)
            console.log("haspro中发货详情编号:",this.fahuoddetId)
            var json={
              productDetailId:this.haspro[i].productDetailId,
              ddetId:this.fahuoddetId,
            }
            addhaspro.push(json)
            /*新增发货明细*/
            deliverHttp.add_mingxi(addhaspro[i]).then(res=>{
              console.log(res);
            })
          }
          console.log(json)
          console.log("addhaspro:",addhaspro)
          //给文本框绑定值
          let addinputs={
            delWuliuid:this.fahuoForm.delWuliuid,
            delCompany:this.fahuoForm.delCompany,
            delId:this.fahouid,
          }
          inputs.push(addinputs);
          /*修改发货单状态等....*/
          deliverHttp.mx_editszDeliver(addinputs).then(res=>{

          })
/*          let ProDetailId={
            productDetailId:this.haspro[i].productDetailId,
          }
          hasproId.push(ProDetailId);
          /!*修改产品详表的库存状态*!/
          deliverHttp.mx_editProDetail(ProDetailId).then(res=>{
            console.log(res)
            console.log("productDetailId",this.productDetailId)
          })*/
        }

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
  .el-dialog__body {

  }
</style>
