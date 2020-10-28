<!--
@author: pengjia
@date: 2020/10/26
@description:
-->
<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入产品名称查询" clearable size="mini">
            <el-button size="mini" icon="el-icon-search" slot="append"></el-button>
          </el-input>
        </el-col>
        <el-col :span="9">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="openAddDialog">添加</el-button>
          <el-button type="primary" icon="el-icon-zoom-in" size="mini">高级查询</el-button>
          <el-button type="primary" icon="el-icon-refresh" size="mini"></el-button>
        </el-col>
        <el-col :span="9">
          <el-button type="warning" size="mini" icon="el-icon-edit">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete">删除</el-button>
        </el-col>
      </el-row>

      <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="productName" label="名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="productImage" label="产品图片">
          <template slot-scope="scope">
            <el-popover placement="top-start" trigger="click">
              <!--trigger属性值：hover、click、focus 和 manual-->
              <a :href="scope.row.productImage" target="_blank" title="查看最大化图片"><img :src="scope.row.productImage" style="width: 300px;height: 300px" /></a>
              <el-image slot="reference" :src="scope.row.productImage" style="width: 50px;height: 50px; cursor:pointer" />
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="productBrand" label="品牌"></el-table-column>
        <el-table-column prop="productModel" label="型号"></el-table-column>
        <el-table-column prop="productSize" label="尺寸"></el-table-column>
        <el-table-column prop="productCost" label="成本"></el-table-column>
        <el-table-column prop="productPrice" label="价格"></el-table-column>
        <el-table-column prop="productStatus" label="状态">
          <template slot-scope="scope">
            {{scope.row.productStatus | productStatusFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="productStock" label="库存"></el-table-column>
      </el-table>

      <el-pagination background :page-size="pageSize" :total="total"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     @current-change="handleCurrentChange"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="产品新增" :visible.sync="addDialog" @close="addHandleClose"
               top="20px" width="56%">
      <el-form :model="addForm" ref="addFormRef" label-width="80px"
               label-position="right" :rules="addFormRules" size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="productName">
<!--              <el-input v-model="addForm.productName" size="mini" placeholder="请输入" clearable></el-input>-->
              <el-autocomplete class="inline-input" @blur="productNameBlur"
                               v-model="addForm.productName" placeholder="请输入产品名称"
                               :fetch-suggestions="querySearch" :trigger-on-focus="false"
                               @select="productNameHandleSelect">
              </el-autocomplete>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="型号" prop="productModel">
              <el-select v-model="addForm.productModel" :disabled="productDisabled"
                         size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in productModelList" :key="item.label"
                           :label="item.value" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="品牌" prop="productBrand">
              <el-select v-model="addForm.productBrand" :disabled="productDisabled"
                         size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in productBrandList" :key="item.label"
                           :label="item.value" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="尺寸" prop="productSize">
              <el-select v-model="addForm.productSize" :disabled="productDisabled"
                         size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in productSizeList" :key="item.label"
                           :label="item.value" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="数量" prop="productCount" :rules="[
              {required:true,message:'数量不能为空'},
              {type:'number',message:'请输入合法的值'}]">
              <el-input type="productCount" v-model.number="addForm.productCount"
                        size="mini" clearable autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="成本" prop="productCost">
              <el-input-number v-model="addForm.productCost" :disabled="productDisabled"
                               :precision="2" size="mini"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="价格" prop="productPrice">
              <el-input-number v-model="addForm.productPrice" :disabled="productDisabled"
                               :precision="2" size="mini"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="库存" prop="productStock">
              <el-input-number disabled v-model="addForm.productStock" size="mini" :min="0"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="备注" prop="remark">
              <el-input v-model="addForm.remark" type="textarea" size="mini" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="14">
            <el-form-item label="图片" prop="productImage">
              <el-upload ref="upload"
                action="http://localhost:8099/qiniu/upload"
                list-type="picture-card" :on-success="handleSuccess"
                :auto-upload="true" :limit="1" multiple>
                <i slot="default" class="el-icon-plus"></i>
                <div slot="file" slot-scope="{file}">
                  <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
                  <span class="el-upload-list__item-actions">
                    <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                      <i class="el-icon-zoom-in"></i>
                    </span>
                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                      <i class="el-icon-download"></i>
                    </span>
                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                      <i class="el-icon-delete"></i>
                    </span>
                  </span>
                </div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
              </el-dialog>
            </el-form-item>
          </el-col>
          <!--<el-col :span="10">
            <transition name="el-zoom-in-top">
              <el-card style="width: 148px;height: 148px" v-show="productImageIsShow">
                <el-image :src="addForm.productImage" alt=""></el-image>
              </el-card>
            </transition>
          </el-col>-->
        </el-row>
      </el-form>
      <span slot="footer">
<!--        <el-button v-show="productImageIsShow" type="warning"-->
<!--                   icon="el-icon-delete" @click="updateProductImg"></el-button>-->
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addProductClick"
                   :loading="addProductButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {productBrand,productSize,productModel} from '../../common/data/product_date'
  import {fileHttp} from "../../network/system/fileUtils";
  import {productHttp} from "../../network/system/product";
  export default {
    name: "Product",
    data() {
      return {
        // productImageIsShow:false,
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false,
        fileName:'',

        productBrandList:productBrand,
        productSizeList:productSize,
        productModelList:productModel,

        productDisabled:false,
        restaurants:[],
        addForm:{
          productId:'',
          productName:'',
          productModel:'',
          productImage:'',
          productPrice:'',
          productBrand:'',
          productStock:'',
          productSize:'',
          productCost:'',
          productCount:'',
          remark:''
        },
        addFormRules:{
          productName:[
            {required:true,message:'请输入产品名称',trigger:'blur'}
          ],
          productModel:[
            {required:true,message:'请选择产品型号',trigger:'change'}
          ],
          productBrand:[
            {required:true,message:'请选择产品品牌',trigger:'change'}
          ],
          productSize:[
            {required:true,message:'请选择产品尺寸',trigger:'change'}
          ],
        },
        addProductButtonLoading:false,
        addDialog:false,

        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
      }
    },
    methods:{
      productNameBlur() {
        productHttp.getProductByName(this.addForm.productName).then(res => {
          if (res.data === "数据库暂无") {
            this.$message.success(res.data)
          } else {
            this.productDisabled = true
          }
        })
      },
      /*updateProductImg() {
        fileHttp.delFile(this.fileName).then(res => {
          if (res.code === 20000) {
            this.$message({
              message:'移除照片成功',
              type:'success'
            })
          }
        })
      },*/
      productNameHandleSelect(item) {
        console.log(item.value);
        productHttp.getProduct(item.id).then(res => {
          this.addForm = res.data
          this.productDisabled = true
          // this.productImageIsShow = true
          // const file1 = res.data.productImage.split(".")
          // const file2 = file1[2].split("/")
          // this.fileName = file2[1]+'.jpg'
        })
      },
      querySearch(queryString,cb) {
        const restaurants = this.restaurants
        const results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        cb(results)
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
        }
      },

      handleSuccess(res) {
        const file1 = res.data.split(".")
        const file2 = file1[2].split("/")
        this.fileName = file2[1]+'.jpg'
        this.addForm.productImage = res.data
      },
      handleRemove() {
        fileHttp.delFile(this.fileName).then(res => {
          if (res.code === 20000) {
            this.$message({
              message:'移除照片成功',
              type:'success'
            })
          }
        })
        this.$refs.upload.clearFiles()
      },
      handlePictureCardPreview(res) {
        this.dialogImageUrl = res.data;
        this.dialogVisible = true;
      },

      addProductClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addProductButtonLoading = true
          productHttp.addProduct(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addProductButtonLoading = false
              this.addDialog = false
              this.initList()
            } else {
              this.$message({
                message:res.message,
                type:'error'
              })
              this.addProductButtonLoading = false
            }
          })
        })
      },
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addProductButtonLoading = false
        this.$refs.upload.clearFiles()
      },
      openAddDialog() {
        this.addDialog = true
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.tableLoading = true
        productHttp.listPage(this.pageNum,this.pageSize).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.pageNum = res.data.pageNum
            this.total = res.data.total
            this.tableLoading = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
          }
        })
      },
      handleRowClick() {

      },
      initList() {
        this.tableLoading = true
        productHttp.listPage(this.pageNum,this.pageSize).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.pageNum = res.data.pageNum
            this.total = res.data.total
            this.tableLoading = false
            for (let item of this.listForm) {
              this.restaurants.push({
                value:item.productName,
                id:item.productId
              })
            }
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
          }
        })
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      }
    },
    created() {
      this.initList()
    },
    mounted() {
      this.restaurants = this.listForm
    }
  }
</script>

<style scoped>
  .el-dialog__body{
    padding: 10px 20px !important;
  }
</style>
