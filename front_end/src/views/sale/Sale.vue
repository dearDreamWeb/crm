<!--
@author: pengjia
@date: 2020/11/2
@description:
-->
<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" placeholder="请输入机会名称" clearable size="mini">
            <el-button size="mini" icon="el-icon-search" slot="append"></el-button>
          </el-input>
        </el-col>
        <el-col :span="9">
          <el-button type="primary" icon="el-icon-plus" size="mini"
                     @click="openAddDialog">新增</el-button>
          <el-button type="primary" icon="el-icon-zoom-in" size="mini">高查</el-button>
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
        <el-table-column prop="saleName" label="机会主题" width="150px"></el-table-column>
        <el-table-column prop="cusName" label="客户"></el-table-column>
        <el-table-column prop="saleDetailResp.salePriorLevel" label="优先" width="60px"></el-table-column>
        <el-table-column prop="saleDetailResp.saleStarBeacon" label="星标" width="60px" align="center">
          <template slot-scope="scope">
            <img style="width: 20px;height: 20px" :src="scope.row.saleDetailResp.saleStarBeacon"></img>
          </template>
        </el-table-column>
        <el-table-column prop="saleType" label="类型"></el-table-column>
        <el-table-column prop="empName" label="负责人"></el-table-column>
        <el-table-column prop="saleDetailResp.saleEstimateDate" label="预计签单">
          <template slot-scope="scope">
            <span v-if="scope.row.saleDetailResp.saleEstimateDate != null">{{scope.row.saleDetailResp.saleEstimateDate | dateFormat}}</span>
            <span v-else>暂定</span>
          </template>
        </el-table-column>
        <el-table-column prop="saleDetailResp.saleExpectMoney" label="预期金额"></el-table-column>
        <el-table-column prop="saleDetailResp.salePossibility" label="可能性"></el-table-column>
        <el-table-column prop="saleDetailResp.saleStage" label="阶段"></el-table-column>
        <el-table-column prop="saleStatus" label="状态" width="80px"></el-table-column>
      </el-table>

      <el-pagination background :page-size="pageSize" :total="total"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     @current-change="handleCurrentChange"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="机会添加" :visible.sync="addDialog" @close="addDialogClose" top="30px">
      <el-form :model="addForm" ref="addFormRef" :rules="addFormRules"
               label-width="65px" label-position="right" size="mini">
        <el-row>
          <el-col>
            <el-form-item label="主题" prop="saleName">
              <el-input v-model="addForm.saleName" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户" prop="cusId">
              <el-select v-model="addForm.cusId" @change="customerChange"
                         clearable placeholder="请选择">
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系人" prop="contactsId">
              <el-select v-model="addForm.contactsId" clearable placeholder="请选择">
                <el-option v-for="item in contactsList" :key="item.contactsId"
                           :label="item.contactsName" :value="item.contactsId"></el-option>
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
          <el-col :span="12">
            <el-form-item label="优先级" prop="salePriorLevel">
              <el-select v-model="addForm.salePriorLevel" clearable placeholder="请选择">
                <el-option v-for="item in starList" :key="item.label"
                           :label="item.level" :value="item.level"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
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
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="需求" prop="demandContent">
              <el-input v-model="addForm.demandContent" type="textarea" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
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
  import {saleHttp} from "../../network/pre_sale/sale";
  import {saleStatusData,saleTypeData,saleStageData,starData}
    from '../../common/data/sale_data'
  import {customerHttp} from "../../network/pre_sale/customer";
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {dictHttp} from "../../network/system/dict";

  export default {
    name: "Sale",
    data() {
      return {
        addSaleButtonLoading:false,
        addDialog:false,
        addForm:{
          saleName:'',
          saleStatus:'跟踪',
          cusId:'',
          contactsId:'',
          saleSource:'',
          discoveryTime:'',
          saleType:'',
          saleStarBeacon:'',
          salePriorLevel:'',
          demandContent:'',
          empId:''
        },
        starBeacon:'',
        addFormRules:{
          saleName:[
            {required:true,message:'请输入机会主题',trigger:'blur'}
          ],
          saleStatus:[
            {required:true,message:'请选择机会状态',trigger:'change'}
          ],
          cusId:[
            {required:true,message:'请选择客户',trigger:'change'}
          ],
          contactsId:[
            {required:true,message:'请选择联系人',trigger:'change'}
          ],
          saleSource:[
            {required:true,message:'请选择来源',trigger:'change'}
          ],
          discoveryTime:[
            {required:true,message:'请选择时间',trigger:'change'}
          ],
          saleType:[
            {required:true,message:'请选择机会类型',trigger:'change'}
          ],
          demandContent:[
            {required:true,message:'请输入客户需求',trigger:'blur'}
          ]
        },
        saleStatusList:saleStatusData,
        saleTypeList:saleTypeData,
        saleStageList:saleStageData,
        customerList:[],
        contactsList:[],
        customerSourceList:[],
        starList:starData,

        listForm:[],
        searchInput:'',
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        rowSaleId:0
      }
    },
    methods:{
      addSaleClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addSaleButtonLoading = true
          this.addForm.empId = this.$store.state.empId
          this.addForm.saleStarBeacon = this.starBeacon
          saleHttp.addSaleAndDetail(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addSaleButtonLoading = false
              this.addDialog = false
              this.initList()
            } else {
              this.addSaleButtonLoading = false
              this.$message.error(res.message)
            }
          })
        })
      },
      starBeaconChange(row) {
        this.addForm.salePriorLevel = row.level
        this.starBeacon = row.value
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
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addSaleButtonLoading = false
      },
      openAddDialog() {
        this.addDialog = true
        this.initCustomerList()
        this.initCustomerSourceList()
      },

      handleRowClick(row,event,column) {
        this.rowSaleId = row.saleId
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.tableLoading = true
        saleHttp.listPage(this.pageNum,this.pageSize).then(res => {
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
      initList() {
        this.tableLoading = true
        saleHttp.listPage(this.pageNum,this.pageSize).then(res => {
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
      initCustomerList() {
        customerHttp.listAll().then(res => {
          this.customerList = res.data
        })
      },
      initCustomerSourceList() {
        dictHttp.tree_dict_byId(2).then(res => {
          this.customerSourceList = res.data
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
    }
  }
</script>

<style scoped>

</style>
