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
                <el-form-item prop="ordTheme" label="主题">
                  <el-input v-model="searchForm.ordTheme" size="mini" placeholder="请输入主题" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="ordHead" label="负责人">
                  <el-input v-model="searchForm.ordHead" size="mini" placeholder="请输入负责人" clearable></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-form-item label="选择日期段">
                  <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item prop="ordState" label="订单状态">
                  <el-select v-model="searchForm.ordState" clearable>
                    <el-option label="执行中" value="0"></el-option>
                    <el-option label="已完成" value="1"></el-option>
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
        <el-table-column prop="customerResp.cusName" label="客户"></el-table-column>
        <el-table-column prop="ordState" label="订单状态" sortable>
          <template slot-scope="scope">
            {{scope.row.ordState | ordStateFormat}}
          </template>
        </el-table-column>
        <!--<el-table-column prop="ordState" label="订单状态" sortable></el-table-column>-->
        <el-table-column prop="ordHead" label="负责人" sortable></el-table-column>
        <el-table-column label="操作" sortable>
          <template slot-scope="scope">
            <el-button type="text" @click="chakan(scope.row.ordId)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
      <el-button  @click="anniu">按钮</el-button>
    </el-card>

    <el-dialog title="订单详情" :visible.sync="dialogTableVisible">
      <el-table :data="szorder">
        <el-table-column property="odetId" label="详情编号" width="150"></el-table-column>
        <el-table-column property="productId" label="产品编号" width="200"></el-table-column>
        <el-table-column property="odetBuynum" label="购买数量" width="200"></el-table-column>
        <el-table-column property="odetBuymoney" label="购买价格"></el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog title="订单添加" width="65%" top="20px" :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" label-width="80px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="8">
            <el-form-item label="主题" prop="ordTheme">
              <el-input v-model="addForm.ordTheme" size="mini" placeholder="请输入主题" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8" width="600px">
            <el-form-item label="客户">
              <el-select v-model="addForm.cusId" placeholder="请选择客户" >
                <el-option v-for="item in cusList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="总金额" prop="ordTotalmoney">
              <el-input v-model="addForm.ordTotalmoney" size="mini" placeholder="请输入总金额" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>

          <el-col :span="8">
            <el-form-item label="收货人" prop="ordConsignee">
              <el-input v-model="addForm.ordConsignee" size="mini" placeholder="请填写收货人姓名" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机号" prop="ordPhone">
              <el-input v-model="addForm.ordPhone" size="mini" placeholder="请填写收货人电话" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="地址">
              <area-cascader type='text' v-model="selected2" :level='1' :data="pcaa" @change="areaCascaderChange"></area-cascader>
            </el-form-item>
          </el-col>
        </el-row>

        <!--<el-row>
          <el-col :span="8">
            <el-form-item label="省" prop="ordProvince">
              <el-input v-model="addForm.ordProvince" size="mini" placeholder="省" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="市" prop="ordCity">
              <el-input v-model="addForm.ordCity" size="mini" placeholder="市" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="区/县" prop="ordCountry">
              <el-input v-model="addForm.ordCountry" size="mini" placeholder="区/县" clearable/>
            </el-form-item>
          </el-col>
          </el-row>-->
        <el-row>
          <el-col :span="24">
            <el-form-item label="详细地址" prop="ordDetail">
              <el-input v-model="addForm.ordDetail" size="mini" placeholder="请完善详细地址" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-popover
        placement="right"
        width="700"
        trigger="click">
        <el-table :data="gridData">
          <el-table-column property="date" label="产品编号"></el-table-column>
          <el-table-column  property="name" label="产品名称"></el-table-column>
          <el-table-column  property="address" label="产品型号"></el-table-column>
          <el-table-column  property="address" label="产品库存"></el-table-column>
          <el-table-column property="address" label="产品价格"></el-table-column>
          <el-table-column property="address" label="操作"></el-table-column>
        </el-table>
        <el-button slot="reference" icon="el-icon-plus" type="primary">选择产品</el-button>
      </el-popover>
      <h3>购物车</h3>
      <!--<el-table :data="szorder">-->
      <el-table>
        <el-table-column property="odetId" label="产品编号"></el-table-column>
        <el-table-column property="productId" label="产品名称" ></el-table-column>
        <el-table-column property="productId" label="产品型号" ></el-table-column>
        <el-table-column property="odetBuynum" label="购买数量" ></el-table-column>
        <el-table-column property="odetBuymoney" label="购买价格" ></el-table-column>
        <el-table-column property="odetBuymoney" label="操作" >
          <el-button type="text">查看详情</el-button>
        </el-table-column>
      </el-table>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addOrderClick"
                   :loading="addOrderButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="订单修改" :visible.sync="editDialog" @close="editHandleClose">
      <el-form :model="editForm" label-width="100px" :rules="editformRules"
               label-position="right" ref="editFormRef">
        <el-row >
          <el-col :span="12">
            <el-form-item label="主题" prop="ordTheme">
              <el-input v-model="editForm.ordTheme" size="mini"/>
            </el-form-item>
          </el-col>
          <!--<el-col :span="12">
            <el-form-item label="负责人" prop="ordHead">
              <el-input v-model="editForm.ordHead" size="mini"/>
            </el-form-item>
          </el-col>-->
          <el-col :span="12">
            <el-form-item label="客户">
              <el-select v-model="editForm.cusId" :disabled="true">
                <el-option style="width:215px" v-for="item in cusList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="总金额" prop="ordTotalmoney">
              <el-input v-model="editForm.ordTotalmoney" size="mini" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="订单状态" prop="ordState">
              <el-radio-group v-model="editForm.ordState" :disabled="true" >
                <el-radio :label=0>执行中</el-radio>
                <el-radio :label=1>已完成</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="收货人" prop="ordConsignee">
              <el-input v-model="editForm.ordConsignee" size="mini" placeholder="请填写收货人姓名" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="ordPhone">
              <el-input v-model="editForm.ordPhone" size="mini" placeholder="请填写收货人电话" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="地址">
              <area-cascader type='text' v-model="selected2" :level='1' :data="pcaa" @change="areaCascaderChange"></area-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="详细地址" prop="ordDetail">
              <el-input v-model="editForm.ordDetail" size="mini" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <!--<el-row :gutter="24">
          <el-col :span="12">
            <el-form-item label="开始时间" prop="ordStarttime">
              <el-date-picker v-model="editForm.ordStarttime" size="mini"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>-->
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editOrderClick"
                   :loading="editOrderButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {orderHttp} from "../../network/system/order";
  import {pca,pcaa} from 'area-data'
  import {customerHttp} from "../../network/pre_sale/customer";

  export default {
    name: "Order",

    data() {
      let checkMobile = (rule,value,cb) => {
        const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
        if (regMobile.test(value)) {
          return cb()
        }
        cb(new Error('请输入合法的手机号'))
      }
      return {
        input: '',
        searchInput:'',
        selected2:[],
        pca:pca,
        pcaa:pcaa,
        gridData:[],
        editForm:{

        },
        searchForm:{
          ordTheme:'',
          ordHead:'',
          ordStarttime:'',
          ordState:'',
          ordProvince:'',
          ordCity:'',
          ordCountry:'',
          ordDetail:'',
          pageNum:1,
          pageSize:10
        },
        addDialog:false,
        rowordId: 0,
        tableLoading:false,
        buttonDisabled:true,
        advancedSearch:false,
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        addForm: {
          ordTheme:'',
          ordHead:'',
          ordTotalmoney:'',
          ordConsignee:'',
          ordPhone:'',
          ordProvince:'',
          ordCity:'',
          ordCountry:'',
          ordDetail:'',
          cusId: '',
          cusIdList:[]
        },
        formRules:{
          ordTheme:[
            {required:true,message:'请输入主题',trigger:'blur'},
            {min:3,max:12,message:'长度在3~12个字符之间'}
          ],
          ordHead:[
            {required:true,message:'请选择客户,还没改ordHead',trigger:'blur'}
          ],
          ordConsignee:[
            {required:true,message:'请输入收货人姓名',trigger:'blur'},
            {min:1,max:20,message:'请输入有效名字'}
          ],
          ordPhone:[
            {required:true,message:'请输入手机号',trigger:'blur'},
            {validator:checkMobile}
          ],
          ordDetail:[
            {required:true,message:'请填写详细地址',trigger:'blur'},
            {min:1,max:20,message:'请将详细地址填写完成'}
          ]
        },
        editformRules:{

        },
        addOrderButtonLoading:false,
        editDialog:false,
        editOrderButtonLoading:false,
        dialogTableVisible:false,
        addOrdButtonLoading:false,
        szorder:[],
        cusList:[]
      }
    },
    methods: {
      areaCascaderChange() {

        console.log("di",this.selected2)
      },
      advancedQueryClick() {

        orderHttp.list(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
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
        /*添加订单*/
        this.$refs.addFormRef.resetFields()
        this.addForm.cusIdList = []
        this.addOrderButtonLoading = false
      },
      openEditOrder(){
        /*修改订单*/
        this.getOrderDetail()
      },
      delOrder(ordId){
        /*删除订单*/
        this.$confirm('确定删除此订单吗','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          ordId = this.rowordId
          orderHttp.delOrder(ordId).then(res => {
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
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.pageSize = this.pageSize
        orderHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      chakan(val){
        this.dialogTableVisible = true;
        alert(val);
        orderHttp.szxiangq(val).then(res=>{
          console.log(res);
          this.szorder=res;
        })
      },
      getOrderDetail() {
        orderHttp.getOrder(this.rowordId).then(res => {
          var order = res.data;
          if(order.ordState==1){
            alert("已完成中的订单不允许修改....");
            return;
          }
          this.editDialog = true;
          this.editForm = res.data
          window.console.log(1111)
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
      anniu(){
        alert(this.$store.state.empName)
      },
      addOrderClick(){
        this.addForm.ordProvince=this.selected2[0];
        this.addForm.ordCity = this.selected2[1];
        this.addForm.ordCountry=this.selected2[2];
        /*console.log("1:"+this.addForm.cusId);*/
        console.log(this.addForm.ordHead)
        let s = this.addForm.cusId;
        this.$refs.addFormRef.validate(valid => {
         /* console.log("2:"+s);*/
          if (!valid) return
          this.addOrderButtonLoading = true
          orderHttp.addOrder(this.addForm).then(res =>{

            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.addOrderButtonLoading = false
              this.addDialog = false
            } else {
              this.addOrderButtonLoading = false
              this.$message({
                message:res.message,
                type:"error"
              })
            }
          })
        })
      },
      editOrderClick(){
        this.editForm.ordProvince=this.selected2[0];
        this.editForm.ordCity = this.selected2[1];
        this.editForm.ordCountry=this.selected2[2];
        console.log("1"+this.selected2)
        this.editOrderButtonLoading = true
        this.editForm.ordId = this.rowordId
        window.console.log(this.editForm)
        orderHttp.editOrder(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editOrderButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editOrderButtonLoading = false
            window.console.log(this.editForm)
          }
            window.console.log(this.editForm)
        }
        )
      },
      initCusList() {
        customerHttp.listAll().then(res => {
          this.cusList = res.data
        })
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
      this.initCusList()
    }
  }
</script>

<style scoped>
  .area-selected-trigger {
    line-height: 15px !important;
  }
  .area-select.large {
    height: 28px;
    margin: 5px 0px;
  }
  .el-select .el-input__inner{
    height: 29px;
    font-size: 12px;
    width: 200px;
  }
  .el-select-dropdown .el-popper{
    min-width: 400px;
  }
</style>
