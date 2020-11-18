<!--
@author: pengjia
@date: 2020/11/18
@description:销售机会详情页客户需求组件
-->
<template>
  <div>
    <el-dialog title="需求添加" :visible.sync="addDialog" @close="addDialogClose">
      <el-form :model="addForm" label-position="right" label-width="100px" size="mini"
               ref="addFormRef" :rules="addFormRules">
        <el-row>
          <el-col>
            <el-form-item label="需求主题" prop="demandTitle">
              <el-input v-model="addForm.demandTitle" clearable placeholder="请选择"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户" prop="cusId">
              <!--<el-select v-model="addForm.cusId" @change="addFormCusChange"
                         clearable placeholder="请选择">
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId"></el-option>
              </el-select>-->
              <el-tag>{{cusName}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="对应机会" prop="saleId">
              <!--<el-select v-model="addForm.saleId" clearable placeholder="请选择">
                <el-option v-for="item in saleList" :key="item.saleId"
                           :label="item.saleName" :value="item.saleId"></el-option>
              </el-select>-->
              <el-tag>{{saleName}}</el-tag>
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
  import {demandDegreeData} from "../../common/data/sale_data";
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {saleHttp} from "../../network/pre_sale/sale";

  export default {
    name: "SaleMoreDemand",
    props:['sale-id','cus-id'],
    data() {
      return {
        cusName:'',
        saleName:'',

        addDialog:false,
        addForm:{
          demandTitle:'',
          cusId:this.cusId,
          contactsId:'',
          saleId:this.saleId,
          demandDegree:'一般',
          demandContent:'',
          empId:''
        },
        addFormRules:{
          demandTitle:[
            {required:true,message:'请输入客户需求',trigger:'blur'}
          ],
          contactsId:[
            {required:true,message:'请选择联系人',trigger:'change'}
          ],
          demandDegree:[
            {required:true,message:'请选择重要程度',trigger:'change'}
          ],
          demandContent:[
            {required:true,message:'请输入需求内容',trigger:'blur'}
          ]
        },
        addDemandButtonLoading:false,
        customerList:[],
        saleList:[],
        contactsList:[],
        demandDegreeList:demandDegreeData,
      }
    },
    methods:{
      addDemandClick() {

      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addDemandButtonLoading = false
      },
      openAddDialog() {
        this.addDialog = true
        this.addForm.saleId = this.saleId
        this.addForm.cusId = this.cusId
        for (let i=0;i<this.saleList.length;i++) {
          if (this.saleList[i].saleId == this.saleId) {
            this.saleName = this.saleList[i].saleName
          }
        }
        for (let i=0;i<this.customerList.length;i++) {
          if (this.customerList[i].cusId == this.cusId) {
            this.cusName = this.customerList[i].cusName
          }
        }
      },
      initCustomerList() {
        customerHttp.listAll().then(res => {
          this.customerList = res.data
        })
      },
      initContactsList() {
        contactsHttp.list_all().then(res => {
          this.contactsList = res.data.list
        })
      },
      initSaleList() {
        saleHttp.list_all().then(res => {
          this.saleList = res.data.list
        })
      },
      addFormCusChange(val) {
        this.addForm.saleId = ''
        this.addForm.contactsId = ''
        saleHttp.getByCusId(val).then(res => {
          this.saleList = res.data.list
          if (res.data.list != null) {
            this.addForm.saleId = this.saleList[0].saleId
          }
        })
        contactsHttp.getByCusId(val).then(res => {
          this.contactsList = res.data.list
          if (res.data.list != null) {
            this.addForm.contactsId = this.contactsList[0].contactsId
          }
        })
      },

    },
    created() {
      this.initCustomerList()
      this.initContactsList()
      this.initSaleList()
    }
  }
</script>

<style scoped>

</style>
