<!--
@author: pengjia
@date: 2020/11/15
@description:
-->
<template>
  <div>
    <el-dialog title="联系人添加" :visible.sync="addDialog" @close="addDialogClose" top="15px">
      <el-form :model="addForm" label-position="right" label-width="80px"
               :rules="addFormRules" ref="addFormRef">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="名称" prop="contactsName">
              <el-input v-model="addForm.contactsName" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生日" prop="birthday">
              <el-date-picker v-model="addForm.birthday" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date"
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="手机" prop="contactsPhone">
              <el-input v-model="addForm.contactsPhone" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-radio v-model="addForm.sex" :label=1>男</el-radio>
              <el-radio v-model="addForm.sex" :label=0>女</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="微信" prop="wechat">
              <el-input v-model="addForm.wecaht" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="QQ" prop="qq">
              <el-input v-model="addForm.qq" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="addForm.email" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="contactsDictType">
              <el-select v-model="addForm.contactsDictType" clearable placeholder="请选择">
                <el-option></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="所属客户" prop="cusId">
              <el-select v-model="addForm.cusId" clearable placeholder="请选择">
                <el-option></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input type="textarea" v-model="addForm.remark" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" :loading="addContactsButtonLoading"
                   @click="addContactsClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "CustomerMoreContacts",
    data() {
      return {
        addDialog:false,
        addForm:{
          contactsName:'',
          birthday:'',
          sex:1,
          contactsPhone:'',
          wecaht:'',
          qq:'',
          email:'',
          contactsDictType:'',
          cusId:'',
          remark:''
        },
        addFormRules:{

        },
        addContactsButtonLoading:false,
      }
    },
    methods:{
      openAddDialog() {
        this.addDialog = true
      },
      addDialogClose() {

      },
      addContactsClick() {

      }
    },
    created() {
    }
  }
</script>

<style scoped>

</style>
