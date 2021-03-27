<!--
@author: pengjia
@date: 2020/11/21
@description:
-->
<template>
  <div>
    <el-dialog title="添加报价" :visible.sync="addDialog"
               @close="addDialogClose" top="15px" width="60%">
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
              <el-input v-model="addForm.offerNumbers" disabled
                        clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机会" prop="saleId">
              <el-tag v-model="addForm.saleId">{{saleName}}</el-tag>
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
          <el-col>
            <el-form-item label="备注" prop="remark">
              <el-input v-model="addForm.remark" type="textarea"
                        clearable placeholder="请输入"></el-input>
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
  import {dateFormat, dateTimeFormat, getWeekDate, randomString} from "../../common/formatUtils";
  import {offerHttp} from "../../network/pre_sale/offer";

  export default {
    name: "SaleMoreOffer",
    props:['sale-id','cus-id','emp-id','sale-form'],
    data() {
      return {
        addDialog:false,
        addButtonLoading:false,
        addForm:{
          offerTheme:'',
          offerNumbers:'',
          contactsId:'',
          saleId:this.saleId,
          empId:this.empId,
          remark:''
        },
        saleName:'',
        addFormRules:{
          offerTheme:[
            {required:true,message:'请输入主题',trigger:'blur'}
          ],
          contactsId:[
            {required:true,message:'请选择报价人',trigger:'change'}
          ]
        },
        contactsList:[]
      }
    },
    methods:{
      openAddDialog() {
        this.addDialog = true
        this.initOfferNumber()
        this.saleName = this.saleForm.saleName
      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addButtonLoading = false
      },
      addClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addButtonLoading = true
          offerHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addButtonLoading = false
              this.addDialog = false
              this.$emit('init-page')
            } else {
              this.addButtonLoading = false
              this.$message.error(res.message)
            }
          })
        })
      },
      initContactsList() {
        contactsHttp.getByCusId(this.cusId).then(res => {
          this.contactsList = res.data.list
        })
      },
      initOfferNumber() {
        let format = dateTimeFormat(new Date())
        let weekDate = getWeekDate();
        this.addForm.offerNumbers = this.$md5(format+weekDate);
      }
    },
    created() {
      this.initContactsList()
    }
  }
</script>

<style scoped>

</style>
