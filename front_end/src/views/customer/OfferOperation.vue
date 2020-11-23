<!--
@author: pengjia
@date: 2020/11/21
@description:
-->
<template>
  <el-container>
    <el-main v-loading.fullscreen.lock="fullscreenLoading">
      <el-card>
        <div slot="header">
          <span>报价单明细 致：【客户】 日期：今天</span>
          <el-button @click="drawer = true" type="primary" icon="el-icon-plus"
                     circle style="float: right;margin-top: -10px"></el-button>
        </div>
        <el-table :data="tableData" style="width: 100%"
                  :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle">
          <el-table-column prop="productName" label="产品名称"></el-table-column>
          <el-table-column prop="offerDetailCount" sortable label="数量">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.offerDetailCount" :min="1" size="mini"
                               :max="scope.row.productStock"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column prop="productBrand" label="品牌"></el-table-column>
          <el-table-column prop="productModel" label="型号"></el-table-column>
          <el-table-column prop="productPrice" sortable label="单价"></el-table-column>
          <el-table-column prop="amountMoney" sortable label="金额">
            <template slot-scope="scope">
              {{scope.row.offerDetailCount * scope.row.productPrice}}
            </template>
          </el-table-column>
          <el-table-column prop="remark" label="备注">
            <template slot-scope="scope">
              <el-input type="textarea" v-model="scope.row.remark" clearable></el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="60px" align="center">
            <template slot-scope="scope">
              <el-tooltip content="删除" placement="top" effect="dark">
                <el-button type="text" icon="el-icon-delete"
                           @click="removeProduct(scope.row.productId)"></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
        <el-divider></el-divider>
        <el-button type="primary" icon="el-icon-plus" size="medium"
                   @click="addFormClick">保存明细</el-button>
      </el-card>

      <el-drawer :visible.sync="drawer" size="40%" :with-header="false" @close="handleClose">
        <el-form :model="searchProduct" size="mini" label-position="right"
                 ref="searchProductFormRef" label-width="30px">
          <el-row>
            <el-col>
              <el-form-item label="选择产品" label-width="80px"></el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item prop="productName">
                <el-input v-model="searchProduct.productName"
                          placeholder="请输入产品名称" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="productBrand">
                <el-select v-model="searchProduct.productBrand"
                           placeholder="请选择产品品牌" clearable>
                  <el-option v-for="item in productBrandList" :key="item.label"
                             :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10">
              <el-form-item prop="productModel">
                <el-select v-model="searchProduct.productModel"
                           clearable placeholder="请选择产品型号">
                  <el-option v-for="item in productModelList" :key="item.label"
                             :label="item.value" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item prop="productSize">
                <el-select v-model="searchProduct.productSize"
                           clearable placeholder="请选择产品尺寸">
                  <el-option v-for="item in productSizeList" :key="item.value"
                             :label="item.value" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item>
                <el-button type="primary" icon="el-icon-search"></el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-divider></el-divider>
        <el-table ref="multipleTable" :data="productListForm" border max-height="350px"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection" :selectable='tableDisabled'></el-table-column>
          <el-table-column prop="productName" label="名称"></el-table-column>
          <el-table-column prop="productBrand" label="品牌"></el-table-column>
          <el-table-column prop="productModel" label="型号"></el-table-column>
          <el-table-column prop="productSize" label="尺寸" width="80px"></el-table-column>
          <el-table-column prop="productStock" label="库存" width="60px"></el-table-column>
        </el-table>
        <el-divider></el-divider>
        <el-button type="primary" icon="el-icon-plus" size="medium"
                   style="margin-left: 20px" @click="productToOther">确定添加</el-button>
      </el-drawer>
    </el-main>
  </el-container>
</template>

<script>
  import {productBrand, productModel, productSize} from "../../common/data/product_date";
  import {productHttp} from "../../network/system/product";
  import {offerHttp} from "../../network/pre_sale/offer";

  export default {
    name: "OfferOperation",
    data() {
      return {
        abc:'',
        saleId:'',
        offerId:'',
        totalMoney:'',
        fullscreenLoading:false,

        drawer: false,
        direction: 'rtl',
        searchProduct:{},
        productBrandList:productBrand,
        productSizeList:productSize,
        productModelList:productModel,

        tableData: [],
        productListForm:[],
        addForm:{
          offerDetailId:'',
          offerDetailCount:'',
          offerDetailUnit:'',
          unitPrice:'',
          amountMoney:'',
          remark:'',
          offerId:'',
          productId:''
        },
        multipleSelection: []
      }
    },

    methods:{
      addFormClick() {
        for (let i=0;i<this.tableData.length;i++) {
          this.addForm.offerId = this.offerId
          this.addForm.offerDetailId = this.tableData[i].offerDetailId
          this.addForm.productId = this.tableData[i].productId
          this.addForm.offerDetailCount = this.tableData[i].offerDetailCount
          this.addForm.unitPrice = this.tableData[i].productPrice
          this.addForm.amountMoney = this.tableData[i].offerDetailCount * this.tableData[i].productPrice
          this.addForm.remark = this.tableData[i].remark
          this.fullscreenLoading = true
          offerHttp.offer_detail_add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.fullscreenLoading = false
            } else {
              this.$message.error(res.message)
              this.fullscreenLoading = false
            }
          })
        }
      },

      removeProduct(productId) {
        console.log(productId)
      },
      handleSelectionChange(val) {
        console.log("ISADD",val)
        this.multipleSelection.push(val)
        console.log("ISTABLE",this.tableData)
      },
      productToOther(value) {
        for (let i=0;i<value.length;i++) {
          for (let j=0;j<this.tableData.length;j++) {
            if (value[i].productId == this.tableData[j].productId) {
              this.tableData[j].offerDetailCount += 1
            } else {
              this.tableData.push(value[i])
            }
          }
        }
        this.drawer = false
      },
      tableDisabled(row,rowIndex) {
        if (row.productStock <= 0) {
          return false
        } else {
          return true
        }
      },
      handleClose() {
        this.$refs.multipleTable.clearSelection();
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
      initProductList() {
        productHttp.listAll().then(res => {
          this.productListForm = res.data.list
        })
      },
      initOfferDetail() {
        offerHttp.get_detail_by_offerId(this.offerId).then(res => {
          this.tableData = res.data
          for (let i=0;i<this.tableData.length;i++) {
            productHttp.getProduct(this.tableData[i].productId).then(res => {
              this.tableData[i].productName = res.data.productName
              this.tableData[i].productBrand = res.data.productBrand
              this.tableData[i].productModel = res.data.productModel
              this.tableData[i].productPrice = res.data.productPrice
              this.tableData[i].productStock = res.data.productStock
            })
          }
        })
      }
    },
    created() {
      this.saleId = this.$urlUtil.getQueryVariable("saleId")
      this.offerId = this.$urlUtil.getQueryVariable("offerId")
      this.initProductList()
      this.initOfferDetail()
    }
  }
</script>

<style scoped>
  /deep/ .el-drawer:focus {
    outline: none;
  }
</style>
