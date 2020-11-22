<!--
@author: pengjia
@date: 2020/11/21
@description:
-->
<template>
  <el-container>
    <el-main>
      <el-card>
        <div slot="header">
          <span>报价单明细 致：【客户】 日期：今天</span>
          <el-button @click="drawer = true" type="primary" icon="el-icon-plus"
                     circle style="float: right;margin-top: -10px"></el-button>
        </div>
        <el-table :data="tableData" show-summary style="width: 100%"
                  :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle">
          <el-table-column type="selection" width="50"></el-table-column>
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
          <el-table-column prop="remark" label="备注"></el-table-column>
          <el-table-column label="操作" width="60px" align="center">
            <template slot-scope="scope">
              <el-tooltip content="删除" placement="top" effect="dark">
                <el-button type="text" icon="el-icon-delete"
                           @click="removeProduct(scope.row.productId)"></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <el-drawer :visible.sync="drawer" size="40%" :with-header="false">
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
        <el-table :data="productListForm" border max-height="350px"
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

  export default {
    name: "OfferOperation",
    data() {
      return {

        saleId:'',
        offerId:'',

        drawer: false,
        direction: 'rtl',
        searchProduct:{},
        productBrandList:productBrand,
        productSizeList:productSize,
        productModelList:productModel,

        tableData: [],
        productListForm:[]
      }
    },

    methods:{
      removeProduct(productId) {
        console.log(productId)
      },
      handleSelectionChange(val) {
        this.tableData = val
      },
      productToOther() {

      },
      tableDisabled(row,rowIndex) {
        if (row.productStock <= 0) {
          return false
        } else {
          return true
        }
      },
      handleClose() {
        console.log("彭佳")
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
      pengjia() {
        console.log("OfferOperation")
      },
      initProductList() {
        productHttp.listAll().then(res => {
          this.productListForm = res.data.list
        })
      }
    },
    created() {
      this.saleId = this.$urlUtil.getQueryVariable("saleId")
      this.offerId = this.$urlUtil.getQueryVariable("offerId")
      this.initProductList()
    }
  }
</script>

<style scoped>
  /deep/ .el-drawer:focus {
    outline: none;
  }
</style>
