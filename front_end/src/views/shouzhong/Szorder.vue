<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入内容" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">添加订单</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditOrder">修改订单</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delOrder">删除订单</el-button>
        </el-col>
      </el-row>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="ordTheme" label="主题" sortable></el-table-column>
        <el-table-column prop="ordHead" label="负责人" sortable></el-table-column>
        <el-table-column prop="ordTotalmoney" label="总金额" sortable></el-table-column>
        <el-table-column prop="ordCreatetime" label="创建时间" sortable></el-table-column>
        <el-table-column prop="ordDealtime" label="成交时间" sortable></el-table-column>
        <el-table-column prop="ordFahuotime" label="发货时间" sortable></el-table-column>
        <el-table-column prop="ordHuikuanmoney" label="回款金额" sortable></el-table-column>
      </el-table>
      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="订单添加" :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" label-width="100px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="负责人" prop="ordHead">
              <el-input v-model="addForm.ordHead" size="mini" placeholder="请输入负责人" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总金额" prop="ordTotalmoney">
              <el-input v-model="addForm.ordTotalmoney" size="mini" placeholder="总金额" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
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
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="详细地址" prop="ordDetail">
              <el-input v-model="addForm.ordDetail" size="mini" placeholder="详细地址" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号" prop="ordPhone">
              <el-input v-model="addForm.ordPhone" size="mini" placeholder="收货手机号" clearable/>
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
  </div>
</template>

<script>
  import {orderHttp} from "../../network/system/order";
  import {userHttp} from "../../network/system/user";

  export default {
    name: "Order",
    data() {
      return {
        searchInput:'',
        editForm:{},
        /*formRules:{
          ordHead:[
            {required:true,message:'请输入订单名称',trigger:'blur'},
          ]
        },*/
        addDialog:false,
        rowordId: 0,
        tableLoading:false,
        buttonDisabled:true,
        listForm:[],
        pageNum:1,
        pageSize:2,
        total:1,
        addForm: {
          ordTheme:'',
          ordHead:'',
          ordTotalmoney:'',
          ordCreatetime:'',
          ordDealtime:'',
          ordFahuotime:'',
          ordHuikuanmoney:'',
          pageNum:1,
          pageSize:2
        },
        formRules:{

        },
        addOrderButtonLoading:false,

      }
    },
    methods: {
      searchInputClick() {
        this.listForm.ordHead = this.searchInput
        orderHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
        })
      },
      resetForm() {
        this.searchInput = ''
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
      },
      addHandleClose(){
        /*添加订单*/
      },
      openEditOrder(){
        /*修改订单*/
      },
      delOrder(){
        /*删除订单*/
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

      getDeptDetail() {
        orderHttp.getDept(this.rowordId).then(res => {
          this.editForm = res.data
        })
      },
      openAddDialog() {
        this.addDialog = true
      },
      handleRowClick(row,event,column) {
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
      addOrderClick(){

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

</style>
