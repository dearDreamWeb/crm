<!--
@author: pengjia
@date: 2020/11/21
@description:
-->
<template>
  <div>
    <el-dialog title="添加报价" :visible.sync="addDialog" @close="addDialogClose" top="15px">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef"
               label-position="right" label-width="80px" size="mini">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="主题" prop="offerTheme">
              <el-input v-model="addForm.offerTheme" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单号" prop="offerNumbers">
              <el-input v-model="addForm.offerNumbers" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机会" prop="saleId">
              <el-tag>机会</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报价人" prop="contactsId">
              <el-select v-model="addForm.contactsId" clearable placeholder="请选择">
                <el-option v-for="item in contactsList" :key="item.contactsId"
                           :label="item.contactsName" :value="item.contactsId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="addForm.remark" type="textarea"
                        clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="占位符">
              <el-input clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addClick"
                   :loading="addButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {contactsHttp} from "../../network/pre_sale/contacts";

  export default {
    name: "SaleMoreOffer",
    props:['sale-id','cus-id','emp-id'],
    data() {
      return {
        addDialog:false,
        addButtonLoading:false,
        addForm:{
          offerTheme:'',
          offerNumbers:'',
          contacts:'',
          saleId:this.saleId,
          empId:this.empId,
          remark:''
        },
        saleName:'',
        addFormRules:{

        },
        contactsList:[]
      }
    },
    methods:{
      openAddDialog() {
        this.addDialog = true
      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
      },
      addClick() {

      },
      initContactsList() {
        contactsHttp.getByCusId(this.cusId).then(res => {
          this.contactsList = res.data
        })
      }
    },
    created() {
      this.initContactsList()
    }
  }
</script>

<style scoped>

</style>
