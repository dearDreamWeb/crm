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
         <!-- <template>-->
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditOrder">修改订单</el-button>
          <!--</template>-->
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
    </el-card>

    <el-dialog title="订单详情" :visible.sync="dialogTableVisible">
      <el-table :data="szorder">
        <el-table-column property="odetId" label="详情编号" width="150"></el-table-column>
        <el-table-column property="productId" label="产品编号" width="200"></el-table-column>
        <el-table-column property="odetBuynum" label="购买数量" width="200"></el-table-column>
        <el-table-column property="odetBuymoney" label="购买价格"></el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog title="订单添加" :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" label-width="100px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="主题" prop="ordTheme">
              <el-input v-model="addForm.ordTheme" size="mini" placeholder="请输入主题" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="负责人" prop="ordHead">
              <el-input v-model="addForm.ordHead" size="mini" placeholder="请输入负责人" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="总金额" prop="ordTotalmoney">
              <el-input v-model="addForm.ordTotalmoney" size="mini" placeholder="总金额" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="订单状态" prop="ordState" >
              <el-select v-model="addForm.ordState" clearable>
                <el-option label="执行中" value="0"></el-option>
                <el-option label="已完成" value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
         <!-- <el-col :span="8">
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
          <el-col :span="24">
            <el-form-item label="详细地址" prop="ordDetail">
              <el-input v-model="addForm.ordDetail" size="mini" placeholder="详细地址" clearable/>
            </el-form-item>
          </el-col>-->
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="开始时间">
              <el-date-picker type="date" placeholder="选择日期" v-model="addForm.ordStarttime" style="width: 100%;">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addOrderClick"
                   :loading="addOrderButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="订单修改" :visible.sync="editDialog" @close="editHandleClose">
      <el-form :model="editForm" label-width="100px" :rules="formRules"
               label-position="right" ref="editFormRef">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="主题" prop="ordTheme">
              <el-input v-model="editForm.ordTheme" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="负责人" prop="ordHead">
              <el-input v-model="editForm.ordHead" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="总金额" prop="ordTotalmoney">
              <el-input v-model="editForm.ordTotalmoney" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="订单状态" prop="ordState">
              <el-radio-group v-model="editForm.ordState">
                <el-radio :label=0>执行中</el-radio>
                <el-radio :label=1>已完成</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="24">
          <el-col :span="12">
            <el-form-item label="开始时间" prop="ordStarttime">
              <el-date-picker v-model="editForm.ordStarttime" size="mini"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
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

  export default {
    name: "Order",
    data() {
      return {
        searchInput:'',
        editForm:{

        },
        searchForm:{
          ordTheme:'',
          ordHead:'',
          ordStarttime:'',
          ordDealtime:'',
          ordState:'',
          startDate:'',
          endDate:'',
          pageNum:1,
          pageSize:5
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
        addForm: {
          ordTheme:'',
          ordTotalmoney:'',
          ordStarttime:'',
          ordDealtime:'',
          ordState:'',
          ordHead:'',
          pageNum:1,
          pageSize:5
        },
        formRules:{
          ordHead:[
            {required:true,message:'请输入订单名称',trigger:'blur'},
          ]
        },
        addOrderButtonLoading:false,
        editDialog:false,
        editOrderButtonLoading:false,
        dialogTableVisible:false,
        szorder:[]
      }
    },
    methods: {
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
      addOrderClick(){
        this.$refs.addFormRef.validate(valid => {
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
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .el-form-item__label {
    text-align: right;
    vertical-align: middle;
    float: left;
    font-size: 14px;
    color: #99a9bf;
    line-height: 40px;
    padding: 0 12px 0 0;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }
</style>
