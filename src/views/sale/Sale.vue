<!--
@author: pengjia
@date: 2020/11/2
@description:
-->
<template>
  <div>
    <div>
      <el-card>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-input v-model="searchInput" placeholder="请输入机会主题" clearable size="mini">
              <el-button @click="searchClick" size="mini" icon="el-icon-search" slot="append"></el-button>
            </el-input>
          </el-col>
          <el-col :span="9">
            <el-button type="primary" icon="el-icon-plus" size="mini"
                       @click="openAddDialog">新增</el-button>
            <el-button type="primary" icon="el-icon-zoom-in" size="mini"
                       @click="advancedSearch = !advancedSearch">高查</el-button>
            <el-button type="primary" icon="el-icon-refresh" size="mini"
                       @click="resetForm"></el-button>
          </el-col>
          <el-col :span="9">
            <el-button type="warning" size="mini" icon="el-icon-edit"
                       :disabled="buttonDisabled" @click="openEditDialog">修改</el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete"
                       :disabled="buttonDisabled" @click="delSaleClick">删除</el-button>
          </el-col>
        </el-row>

        <transition name="el-zoom-in:top">
          <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
            <el-form :model="searchForm" ref="searchFormRef" size="mini"
                     label-position="right" label-width="70px">
              <el-row>
                <el-col>
                  <el-form-item label="高级查询"></el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <el-form-item label="客户" prop="cusId">
                    <el-select v-model="searchForm.cusId" clearable placeholder="请选择">
                      <el-option v-for="item in customerList" :key="item.cusId"
                                 :label="item.cusName" :value="item.cusId"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="优先级" prop="salePriorLevel">
                    <el-select v-model="searchForm.salePriorLevel" clearable placeholder="请选择">
                      <el-option v-for="item in starList" :key="item.label"
                                 :label="item.level" :value="item.level"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="类型" prop="saleType">
                    <el-select v-model="searchForm.saleType" clearable placeholder="请选择">
                      <el-option v-for="item in saleTypeList" :key="item.value"
                                 :label="item.label" :value="item.label"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <el-form-item label="负责人" prop="empId">
                    <el-select v-model="searchForm.empId" clearable placeholder="请选择">
                      <el-option v-for="item in empList" :key="item.empId"
                                 :label="item.empName" :value="item.empId"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="阶段" prop="saleStage">
                    <el-select v-model="searchForm.saleStage" clearable placeholder="请选择">
                      <el-option v-for="item in saleStageList" :key="item.label"
                                 :label="item.value" :value="item.value"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="状态" prop="saleStatus">
                    <el-select v-model="searchForm.saleStatus" clearable placeholder="请选择">
                      <el-option v-for="item in saleStatusList" :key="item.value"
                                 :label="item.label" :value="item.label"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <el-form-item label="星标" prop="saleStarBeacon">
                    <el-select v-model="searchForm.saleStarBeacon" clearable placeholder="请选择">
                      <el-option v-for="item in starList" :key="item.label"
                                 :label="item.value" :value="item.value">
                        <span style="float: left">{{item.name}}</span>
                        <img style="float: right" :src="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item></el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item>
                    <el-button size="mini" icon="el-icon-zoom-out"
                               @click="closeAdvancedSearch"></el-button>
                    <el-button size="mini" @click="advancedSearchClick" type="primary"
                               icon="el-icon-search"></el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-card>
        </transition>

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
          <el-table-column label="操作" width="70px" align="center">
            <template slot-scope="scope">
              <el-tooltip class="item" effect="dark" content="详情" placement="top">
                <el-button type="text" icon="el-icon-view" size="medium"
                           @click="manipulateCustomerClick(scope.row.saleId,scope.row.cusId)">
                </el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination background :page-size="pageSize" :total="total"
                       :current-page="pageNum" :page-sizes="[1,2,5,10]"
                       @current-change="handleCurrentChange"
                       layout="prev, pager, next, jumper, total">
        </el-pagination>
      </el-card>

      <el-divider></el-divider>

      <sale-chart ref="saleChartRef" v-bind="$attrs" v-on="$listeners"
                  v-bind:salestagecount="saleStageCount"
                  v-bind:salestagecountname="saleStageCountName"></sale-chart>

      <el-dialog title="机会添加" :visible.sync="addDialog" @close="addDialogClose" top="30px">
        <el-form :model="addForm" ref="addFormRef" :rules="addFormRules"
                 label-width="80px" label-position="right" size="mini">
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
              <el-form-item label="发现时间" prop="discoveryTime">
                <el-date-picker v-model="addForm.discoveryTime" format="yyyy-MM-dd"
                                value-format="yyyy-MM-dd" type="date" clearable
                                placeholder="请输入"></el-date-picker>
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

      <el-dialog title="机会修改" :visible.sync="editDialog" @close="editDialogClose" top="30px">
        <el-form :model="editForm" label-width="80px" label-position="right"
                 ref="editFormRef" :rules="editFormRules" size="mini">
          <el-row>
            <el-col>
              <el-form-item label="主题" prop="saleName">
                <el-input v-model="editForm.saleName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="类型" prop="saleType">
                <el-select v-model="editForm.saleType">
                  <el-option v-for="item in saleTypeList" :key="item.value"
                             :label="item.label" :value="item.label"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <!--<el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="星标" prop="saleStarBeacon">
                <el-select v-model="editForm.saleStarBeacon">
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
                <el-select v-model="editForm.salePriorLevel">
                  <el-option v-for="item in starList" :key="item.label"
                             :label="item.level" :value="item.level"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>-->
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="预计签单" prop="saleEstimateDate">
                <el-date-picker v-model="editForm.saleEstimateDate" format="yyyy-MM-dd"
                                value-format="yyyy-MM-dd" type="date" clearable
                                placeholder="请输入"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="预期金额" prop="saleExpectMoney">
                <el-input v-model="editForm.saleExpectMoney" clearable
                          v-only-number="{max:999999999,min:0,precision:2}"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="可能性" prop="salePossibility">
                <el-input v-model="editForm.salePossibility"
                          v-only-number="{max:100,min:0,precision:0}">
                  <template slot="append">%</template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="阶段" prop="saleStage">
                <el-select v-model="editForm.saleStage" clearable>
                  <el-option v-for="item in saleStageList" :key="item.label"
                             :label="item.value" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <span slot="footer">
          <el-button @click="editDialog = false">取消</el-button>
          <el-button type="primary" :loading="editButtonLoading"
                     @click="editSaleClick">确定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import {saleHttp} from "../../network/pre_sale/sale";
  import {saleStatusData,saleTypeData,saleStageData,starData}
    from '../../common/data/sale_data'
  import {customerHttp} from "../../network/pre_sale/customer";
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {dictHttp} from "../../network/system/dict";
  import {userHttp} from "../../network/system/user";
  import SaleChart from "../../components/echarts/SaleChart";
  import {noRepeat, unique} from "../../common/sanYiDate";

  export default {
    name: "Sale",
    components: {SaleChart},
    data() {
      return {
        saleStageCount:[],
        saleStageCountName:[],

        editForm:{
          saleId:'',
          saleName:'',
          saleType:'',
          saleStarBeacon:'',
          salePriorLevel:'',
          saleEstimateDate:'',
          saleExpectMoney:'',
          salePossibility:'',
          saleStage:''
        },
        editDetailForm:{},
        editFormRules:{
          saleName:[
            {required:true,message:'请输入机会主题',trigger:'blur'}
          ],
          saleType:[
            {required:true,message:'请输入机会主题',trigger:'change'}
          ],
          saleStarBeacon:[
            {required:true,message:'请输入机会主题',trigger:'change'}
          ],
          salePriorLevel:[
            {required:true,message:'请输入机会主题',trigger:'change'}
          ]
        },
        editButtonLoading:false,
        editDialog:false,

        saleDetailForm:{
          saleStarBeacon:'',
          salePriorLevel:'',
          saleEstimateDate:'',
          saleExpectMoney:'',
          salePossibility:'',
          saleStage:''
        },

        advancedSearch:false,
        searchForm:{
          cusId:'',
          saleName:'',
          salePriorLevel:'',
          saleType:'',
          empId:'',
          saleStage:'',
          saleStatus:'',
        },
        empList:[],

        addSaleButtonLoading:false,
        addDialog:false,
        addForm:{
          saleName:'',
          saleStatus:'跟踪',
          cusId:'',
          contactsId:'',
          discoveryTime:new Date(),
          saleType:'办公',
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
          discoveryTime:[
            {required:true,message:'请选择时间',trigger:'change'}
          ],
          saleType:[
            {required:true,message:'请选择机会类型',trigger:'change'}
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
        rowSaleId:0,
        buttonDisabled:true
      }
    },
    methods:{
      manipulateCustomerClick(saleId,cusId) {
        let resolve = this.$router.resolve({
          path:'/sale_detail',
          query:{
            saleId:saleId,
            cusId:cusId,
            empId:this.$store.state.empId
          }
        });
        window.open(resolve.href,'_blank')
      },

      editSaleClick() {
        this.$refs.editFormRef.validate(valid => {
          if (!valid) return
          this.editButtonLoading = true
          saleHttp.editSaleAndDetail(this.editForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.editButtonLoading = false
              this.editDialog = false
              this.initList()
            } else {
              this.$message.error(res.message)
              this.editButtonLoading = false
            }
          })
        })
      },
      editDialogClose() {
        this.$refs.editFormRef.resetFields()
        this.editButtonLoading = false
      },
      openEditDialog() {
        this.editDialog = true
        this.initCustomerSourceList()
        saleHttp.get_by_id(this.rowSaleId).then(res => {
          this.editForm = res.data
          this.saleDetailForm = res.data.saleDetailResp
        })
        this.editForm.saleStarBeacon = this.saleDetailForm.saleStarBeacon
        this.editForm.salePriorLevel = this.saleDetailForm.salePriorLevel
        this.editForm.saleEstimateDate = this.saleDetailForm.saleEstimateDate
        this.editForm.saleExpectMoney = this.saleDetailForm.saleExpectMoney
        this.editForm.salePossibility = this.saleDetailForm.salePossibility
        this.editForm.saleStage = this.saleDetailForm.saleStage
      },

      delSaleClick() {
        this.$confirm('将删除该机会','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          saleHttp.del(this.rowSaleId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message.error(res.message)
            }
          })
        })
      },

      resetForm() {
        this.$refs.searchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowSaleId = 0
        this.buttonDisabled = true
      },
      searchClick() {
        this.searchForm.saleName = this.searchInput
        saleHttp.search_sale(this.searchForm).then(res => {
          this.listForm = res.data.list
        })
        this.searchForm.saleName = ''
      },
      advancedSearchClick() {
        saleHttp.search_sale(this.searchForm).then(res => {
          this.listForm = res.data.list
        })
      },
      closeAdvancedSearch() {
        this.advancedSearch = !this.advancedSearch
        this.$refs.searchFormRef.resetFields()
      },

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
      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addSaleButtonLoading = false
      },
      openAddDialog() {
        this.addDialog = true
        this.initCustomerList()
        this.initCustomerSourceList()
        /*this.addForm.*/
      },

      handleRowClick(row,event,column) {
        this.rowSaleId = row.saleId
        if (this.rowSaleId != 0) {
          this.buttonDisabled = false
        }
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
      initEmpList() {
        userHttp.list().then(res => {
          this.empList = res.data.list
        })
      },
      initAllSaleList() {
        saleHttp.all_sale_list().then(res => {
          this.saleStageCount = res.data
          for (let i=0;i<res.data.length;i++) {
            this.saleStageCountName.push(res.data[i].name)
          }
          console.log("xxx")
          console.log("xxsdsad")
        })
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
    },
    created() {
      this.initList()
      this.initEmpList()
      this.initCustomerList()
      this.initAllSaleList()
    },
  }
</script>

<style scoped>

</style>
