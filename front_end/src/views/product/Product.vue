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
        <el-table-column label="占位符"></el-table-column>
        <el-table-column label="占位符"></el-table-column>
        <el-table-column label="占位符"></el-table-column>
        <el-table-column label="占位符"></el-table-column>
        <el-table-column label="占位符"></el-table-column>
        <el-table-column label="占位符"></el-table-column>
        <el-table-column label="占位符"></el-table-column>
        <el-table-column label="占位符"></el-table-column>
      </el-table>

      <el-pagination background :page-size="pageSize" :total="total"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     @current-change="handleCurrentChange"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="产品新增" :visible.sync="addDialog" @close="addHandleClose"
               top="15px" width="56%">
      <el-form :model="addForm" ref="addFormRef" label-width="80px"
               label-position="right" :rules="addFormRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="productName">
              <el-input v-model="addForm.productName" size="mini" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="型号" prop="productModel">
              <el-select v-model="addForm.productModel" size="mini" placeholder="请选择" clearable>
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
              <el-select v-model="addForm.productBrand" size="mini" placeholder="请选择" clearable>
                <el-option v-for="item in productBrandList" :key="item.label"
                           :label="item.value" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="尺寸" prop="productSize">
              <el-select v-model="addForm.productSize" size="mini" placeholder="请选择" clearable>
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
              <el-input-number v-model="addForm.productCost" :precision="2" size="mini"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="价格" prop="productPrice">
              <el-input-number v-model="addForm.productPrice" :precision="2" size="mini"></el-input-number>
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
              <el-input type="textarea" size="mini" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
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
        </el-row>
      </el-form>
      <span slot="footer">
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
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false,
        fileName:'',

        productBrandList:productBrand,
        productSizeList:productSize,
        productModelList:productModel,

        addForm:{
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
        tableLoading:false
      }
    },
    methods:{
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
              this.initList()
              this.addProductButtonLoading = false
              this.addDialog = false
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
      },
      openAddDialog() {
        this.addDialog = true
      },
      handleCurrentChange() {

      },
      handleRowClick() {

      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      }
    },
    created() {
      console.log(this.productBrandList)
      console.log(this.productSizeList)
    }
  }
</script>

<style>
  .el-dialog__body{
    padding: 10px 20px !important;
  }
</style>
