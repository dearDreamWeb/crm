<!--
@author: pengjia
@date: 2020/10/23
@description:
-->
<template>
  <div>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" size="mini" icon="el-icon-zoom-in"
                     @click="advancedSearch = !advancedSearch">高查</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh"
                     @click="resetForm"></el-button>
        </el-col>
        <!--<el-col :span="8">
          <el-button type="warning" :disabled="buttonDisabled" icon="el-icon-edit"
                     @click="openEditDialog" size="mini">修改</el-button>
        </el-col>-->
      </el-row>

      <transition name="el-zoom-in:top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
          <el-form :model="searchForm" ref="advancedSearchFormRef" size="mini"
                   label-position="right" label-width="80px">
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="报价主题" prop="offerTheme">
                  <el-input v-model="searchForm.offerTheme" clearable placeholder="请输入"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="销售机会" prop="saleId">
                  <el-select v-model="searchForm.saleId" clearable placeholder="请选择">
                    <el-option v-for="item in saleList" :key="item.saleId"
                               :label="item.saleName" :value="item.saleId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="报价人" prop="contactsId">
                  <el-select v-model="searchForm.contactsId" clearable placeholder="请选择">
                    <el-option v-for="item in contactsList" :key="item.contactsId"
                               :label="item.contactsName" :value="item.contactsId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="创建时间">
                  <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
              <!--<el-col :span="8">
                <el-form-item label="产品" prop="productId">
                  <el-select v-model="searchForm.productId" clearable placeholder="请选择">
                    <el-option v-for="item in productList" :key="item.productId"
                               :label="item.productName" :value="item.productId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>-->
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

      <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column prop="offerTheme" label="报价主题"></el-table-column>
        <el-table-column prop="offerNumbers" label="报价单号" show-overflow-tooltip></el-table-column>
        <el-table-column prop="createTime" label="创建时间">
          <template slot-scope="scope">
            {{scope.row.createTime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="contactsResp.contactsName" label="报价人"></el-table-column>
        <el-table-column prop="saleResp.saleName" label="销售机会"></el-table-column>
        <el-table-column prop="offerDetailResp.productName" label="产品名称"></el-table-column>
        <el-table-column prop="offerDetailResp.productBrand" label="品牌"></el-table-column>
        <el-table-column prop="offerDetailResp.productModel" label="型号"></el-table-column>
        <el-table-column prop="offerDetailResp.productPrice" label="单价"></el-table-column>
        <el-table-column prop="offerDetailResp.offerDetailCount" label="数量" width="60px"></el-table-column>
        <el-table-column prop="offerDetailResp.amountMoney" label="金额"></el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>
      </el-table>

      <el-pagination background :page-size="pageSize" :total="total"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     @current-change="handleCurrentChange"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <!--<el-dialog title="修改报价" :visible.sync="editDialog" @close="editDialogClose">
      <el-form :model="editForm" label-width="80px" label-position="right"
               ref="editFormRef" size="mini" :rules="editFormRules">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="报价主题" prop="offerTheme">
              <el-input v-model="editForm.offerTheme" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label=""
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>-->
  </div>
</template>

<script>
  import {offerHttp} from "../../network/pre_sale/offer";
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {productHttp} from "../../network/system/product";
  import {saleHttp} from "../../network/pre_sale/sale";

  export default {
    name: "BaoJia",
    data() {
      return {
        contactsList:[],
        productList:[],
        saleList:[],


        editForm:{},
        editDialog:false,

        buttonDisabled:true,
        rowOfferId:0,
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,

        searchInput:'',
        advancedSearch:false,
        searchForm:{
          offerTheme:'',
          saleId:'',
          contactsId:'',
          startDate:'',
          endDate:'',
        }
      }
    },
    methods:{
      openEditDialog() {
        this.editDialog = true
        offerHttp.getOffer(this.rowOfferId).then(res => {
          this.editForm = res.data
        })
      },

      resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowOfferId = 0
        this.buttonDisabled = true
      },

      handleRowClick(row,event,column) {
        this.rowOfferId = row.offerId
        if (this.rowOfferId != 0) {
          this.buttonDisabled = false
        }
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.tableLoading = true
        offerHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },

      searchInputClick() {
        this.searchForm.offerTheme = this.searchInput
        offerHttp.queryOffer(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      advancedQueryClick() {
        offerHttp.queryOffer(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
        })
      },

      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
      initList() {
        this.tableLoading = true
        offerHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      initContactsList() {
        contactsHttp.list_all().then(res => {
          this.contactsList = res.data.list
        })
      },
      initProductList() {
        productHttp.listAll().then(res => {
          this.productList = res.data.list
        })
      },
      initSaleList() {
        saleHttp.list_all().then(res => {
          this.saleList = res.data.list
        })
      }
    },
    created() {
      this.initList()
      this.initContactsList()
      this.initProductList()
      this.initSaleList()
    }
  }
</script>

<style scoped>

</style>
