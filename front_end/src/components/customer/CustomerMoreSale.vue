<!--
@author: pengjia
@date: 2020/11/15
@description:
-->
<template>
  <div>
    <el-dialog title="机会添加" :visible.sync="addDialog" @close="addDialogClose" top="15px">
      <el-form :model="addForm" ref="addFormRef" :rules="addFormRules"
               label-width="65px" label-position="right" size="medium">
        <el-row>
          <el-col>
            <el-form-item label="主题" prop="saleName">
              <el-input v-model="addForm.saleName" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <!--<el-col :span="12">
            <el-form-item label="客户" prop="cusId">
              <el-select v-model="addForm.cusId" @change="customerChange"
                         clearable placeholder="请选择">
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>-->
          <el-col :span="12">
            <el-form-item label="联系人" prop="contactsId">
              <el-select v-model="addForm.contactsId" clearable placeholder="请选择">
                <el-option v-for="item in contactsList" :key="item.contactsId"
                           :label="item.contactsName" :value="item.contactsId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="星标" prop="saleStarBeacon">
              <el-select v-model="addForm.saleStarBeacon" @change="starBeaconChange"
                         clearable placeholder="请选择">
                <el-option v-for="item in starList" :key="item.label"
                           :label="item.name" :value="item">
                  <span style="float: left">{{item.name}}</span>
                  <img style="float: right" :src="item.value"></img>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="来源" prop="saleSource">
              <el-select v-model="addForm.saleSource" clearable placeholder="请选择">
                <el-option v-for="item in customerSourceList.children" :key="item.dictId"
                           :label="item.dictName" :value="item.dictName"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="saleType">
              <el-select v-model="addForm.saleType" clearable placeholder="请选择">
                <el-option v-for="item in saleTypeList" :key="item.value"
                           :label="item.label" :value="item.label"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">

          <!--<el-col :span="12">
            <el-form-item label="优先级" prop="salePriorLevel">
              <el-select v-model="addForm.salePriorLevel" clearable placeholder="请选择">
                <el-option v-for="item in starList" :key="item.label"
                           :label="item.level" :value="item.level"></el-option>
              </el-select>
            </el-form-item>
          </el-col>-->
        </el-row>
        <!--<el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="时间" prop="discoveryTime">
              <el-date-picker v-model="addForm.discoveryTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date" clearable
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="saleStatus">
              <el-select v-model="addForm.saleStatus" clearable placeholder="请选择">
                <el-option v-for="item in saleStatusList" :key="item.value"
                           :label="item.label" :value="item.label"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>-->
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" :loading="addSaleButtonLoading"
                   @click="addSaleClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {saleStageData, saleStatusData, saleTypeData, starData} from "../../common/data/sale_data";
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {dictHttp} from "../../network/system/dict";
  import {dateFormat} from "../../common/formatUtils";
  import {saleHttp} from "../../network/pre_sale/sale";

  export default {
    name: "CustomerMoreSale",
    props:['msg'],
    data() {
      return {
        addDialog:false,
        addSaleButtonLoading:false,
        addForm:{
          saleName:'',
          saleStatus:'跟踪',
          cusId:this.msg,
          contactsId:'',
          saleSource:'',
          discoveryTime:new Date(),
          saleType:'',
          saleStarBeacon:'',
          salePriorLevel:'',
        },
        starBeacon:'',
        starBeaconValue:'',
        addFormRules:{
          saleName:[
            {required:true,message:'请输入机会主题',trigger:'blur'}
          ],
          contactsId:[
            {required:true,message:'请选择联系人',trigger:'change'}
          ],
          saleSource:[
            {required:true,message:'请选择来源',trigger:'change'}
          ],
          saleType:[
            {required:true,message:'请选择机会类型',trigger:'change'}
          ],
        },
        saleStatusList:saleStatusData,
        saleTypeList:saleTypeData,
        saleStageList:saleStageData,
        customerList:[],
        contactsList:[],
        customerSourceList:[],
        starList:starData,
      }
    },
    methods:{
      initContactsList() {
        contactsHttp.getByCustomerId(this.msg).then(res => {
          this.contactsList = res.data
        })
        this.addForm.contactsId = ''
      },
      initCustomerSourceList() {
        dictHttp.tree_dict_byId(2).then(res => {
          this.customerSourceList = res.data
        })
      },

      openAddDialog() {
        this.addDialog = true
      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addSaleButtonLoading = false
      },
      customerChange(val) {
        contactsHttp.getByCustomerId(val).then(res => {
          this.contactsList = res.data
        })
        this.addForm.contactsId = ''
        customerHttp.getCusById(val).then(res => {
          dictHttp.get(res.data.cusDictSource).then(res => {
            this.addForm.saleSource = res.data.dictName
          })
        })
      },
      starBeaconChange(row) {
        this.addForm.salePriorLevel = row.level
        this.starBeacon = row.value
        this.starBeaconValue = row.value
      },
      addSaleClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addSaleButtonLoading = true
          this.addForm.saleStarBeacon = this.starBeaconValue
          saleHttp.addSaleAndDetail(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addSaleButtonLoading = false
              this.addDialog = false
              this.$emit('moreclose')
            } else {
              this.$message.error(res.message)
              this.addSaleButtonLoading = false
            }
          })
        })
      }
    },
    created() {
      this.initContactsList()
      this.initCustomerSourceList()
    }
  }
</script>

<style scoped>

</style>
