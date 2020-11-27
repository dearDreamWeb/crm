<!--
@author: pengjia
@date: 2020/11/15
@description:
-->
<template>
  <div>
    <el-dialog title="需求添加" :visible.sync="addDialog" @close="addDialogClose">
      <el-form :model="addForm" label-position="right" label-width="100px"
               ref="addFormRef" :rules="addFormRules">
        <el-row>
          <el-col>
            <el-form-item label="需求主题" prop="demandTitle">
              <el-input v-model="addForm.demandTitle" clearable placeholder="请选择"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <!--<el-col :span="12">
            <el-form-item label="客户" prop="cusId">
              <el-select v-model="addForm.cusId" clearable placeholder="请选择">
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>-->
          <el-col :span="12">
            <el-form-item label="对应机会" prop="saleId">
              <el-select v-model="addForm.saleId" clearable placeholder="请选择">
                <el-option v-for="item in saleList" :key="item.saleId"
                           :label="item.saleName" :value="item.saleId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="需求提供人" prop="contactsId">
              <el-select v-model="addForm.contactsId" clearable placeholder="请选择">
                <el-option v-for="item in contactsList" :key="item.contactsId"
                           :label="item.contactsName" :value="item.contactsId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重要程度" prop="demandDegree">
              <el-select v-model="addForm.demandDegree" clearable placeholder="请选择">
                <el-option v-for="item in demandDegreeList" :key="item.label"
                           :label="item.value" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col>
            <el-form-item label="需求内容" prop="demandContent">
              <el-input v-model="addForm.demandContent" type="textarea" clearable placeholder="请选择"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" :loading="addDemandButtonLoading"
                   @click="addDemandClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {saleHttp} from "../../network/pre_sale/sale";
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {demandDegreeData} from '../../common/data/sale_data'
  import {demandHttp} from "../../network/pre_sale/demand";

  export default {
    name: "CustomerMoreDemand",
    props:['msg'],
    data() {
      return {
        addDialog:false,
        addDemandButtonLoading:false,
        addForm:{
          demandTitle:'',
          cusId:this.msg,
          contactsId:'',
          saleId:'',
          demandDegree:'一般',
          demandContent:'',
        },
        addFormRules:{
          demandTitle:[
            {required:true,message:'请输入客户需求',trigger:'blur'}
          ],
          contactsId:[
            {required:true,message:'请选择联系人',trigger:'change'}
          ],
          saleId:[
            {required:true,message:'请选择销售机会',trigger:'change'}
          ],
          demandDegree:[
            {required:true,message:'请选择重要程度',trigger:'change'}
          ],
          demandContent:[
            {required:true,message:'请输入需求内容',trigger:'blur'}
          ]
        },
        customerList:[],
        saleList:[],
        contactsList:[],
        demandDegreeList:demandDegreeData
      }
    },
    methods:{
      openAddDialog() {
        this.addDialog = true
        this.initSaleList()
        this.initContactsList()
      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addDemandButtonLoading = false
      },
      addDemandClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addDemandButtonLoading = true
          demandHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addDemandButtonLoading = false
              this.addDialog = false
              this.$emit('moreclose')
            } else {
              this.$message.error(res.message)
              this.addDemandButtonLoading = false
            }
          })
        })
      },
      initSaleList() {
        saleHttp.getByCusId(this.msg).then(res => {
          this.saleList = res.data.list
        })
      },
      initContactsList() {
        contactsHttp.getByCusId(this.msg).then(res => {
          this.contactsList = res.data.list
        })
      },
    },
    created() {

    }
  }
</script>

<style scoped>

</style>
