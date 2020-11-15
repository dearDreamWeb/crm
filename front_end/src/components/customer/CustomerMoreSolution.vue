<!--
@author: pengjia
@date: 2020/11/15
@description:
-->
<template>
  <div>
    <el-dialog title="制定方案" :visible.sync="addDialog" @close="addDialogClose">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef"
               label-width="80px" label-position="right">
        <el-row>
          <el-col>
            <el-form-item label="方案主题" prop="solutionTitle">
              <el-input v-model="addForm.solutionTitle" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户" prop="cusId">
              <el-select v-model="addForm.cusId" clearable placeholder="请选择"
                         @change="customerChange">
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机会" prop="saleId">
              <el-select v-model="addForm.saleId" clearable placeholder="请选择"
                         @change="saleChange">
                <el-option v-for="item in saleList" :key="item.saleId"
                           :label="item.saleName" :value="item.saleId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="需求" prop="demandId">
              <el-select v-model="addForm.demandId" clearable placeholder="请选择">
                <el-option v-for="item in demandList" :key="item.demandId"
                           :label="item.demandTitle" :value="item.demandId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="提交时间" prop="createTime">
              <el-date-picker v-model="addForm.createTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date" disabled
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="方案内容" prop="content">
              <el-input type="textarea" v-model="addForm.content"
                        clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" :loading="addButtonLoading"
                   @click="addSolutionClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {saleHttp} from "../../network/pre_sale/sale";
  import {demandHttp} from "../../network/pre_sale/demand";

  export default {
    name: "CustomerMoreSolution",
    data() {
      return {
        addDialog:false,
        addButtonLoading:false,
        addForm:{},
        addFormRules:{
          solutionTitle:[
            {required:true,message:'请输入方案主题',trigger:'blur'}
          ],
          content:[
            {required:true,message:'请输入方案内容',trigger:'blur'}
          ],
          cusId:[
            {required:true,message:'请选择客户',trigger:'change'}
          ],
          saleId:[
            {required:true,message:'请选择机会',trigger:'change'}
          ],
          demandId:[
            {required:true,message:'请选择需求',trigger:'change'}
          ]
        },
        customerList:[],
        saleList:[],
        demandList:[],
      }
    },
    methods:{
      openAddDialog() {
        this.addDialog = true
      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addButtonLoading = false
      },
      customerChange(val) {
        saleHttp.getByCusId(val).then(res => {
          this.saleList = res.data.list
        })
      },
      saleChange(val) {
        demandHttp.getBySaleId(val).then(res => {
          this.demandList = res.data
        })
      },
      addSolutionClick() {

      }
    },
    created() {

    }
  }
</script>

<style scoped>

</style>
