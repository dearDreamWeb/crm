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
          <el-input v-model="searchInput" placeholder="请输入需求主题" size="mini" clearable>
            <el-button @click="searchClick" size="mini" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="9">
          <el-button type="primary" icon="el-icon-plus" size="mini"
                     @click="openAddDialog">新增</el-button>
          <el-button type="primary" icon="el-icon-zoom-in" size="mini"
                     @click="advancedSearch = !advancedSearch">高查</el-button>
          <el-button type="primary" icon="el-icon-refresh"
                     size="mini" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="9">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditDialog">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delDemandClick">删除</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in:top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
          <el-form :model="searchForm" ref="searchFormRef" label-width="80px"
                   label-position="right" size="mini">
            <el-row>
              <el-col>
                <el-form-item label="高级查询"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="所有者" prop="empId">
                  <el-select v-model="searchForm.empId" clearable placeholder="请选择">
                    <el-option v-for="item in empList" :key="item.empId"
                               :label="item.empName" :value="item.empId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="需求程度" prop="demandDegree">
                  <el-select v-model="searchForm.demandDegree" clearable placeholder="请选择">
                    <el-option v-for="item in demandDegreeList" :key="item.label"
                               :label="item.value" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="是否解决" prop="isSolve">
                  <el-radio v-model="searchForm.isSolve" label="是">是</el-radio>
                  <el-radio v-model="searchForm.isSolve" label="否">否</el-radio>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="提供者" prop="contactsId">
                  <el-select v-model="searchForm.contactsId" clearable placeholder="请选择">
                    <el-option v-for="item in contactsList" :key="item.contactsId"
                               :label="item.contactsName" :value="item.contactsId"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="销售机会" prop="saleId">
                  <el-select v-model="searchForm.saleId" clearable placeholder="请选择">
                    <el-option v-for="item in saleList" :key="item.saleId"
                               :label="item.saleName" :value="item.saleId"></el-option>
                  </el-select>
                </el-form-item>
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
        <el-table-column prop="demandTitle" label="需求主题"></el-table-column>
        <el-table-column prop="demandDegree" label="重要程度"></el-table-column>
        <el-table-column prop="demandContent" label="需求内容"></el-table-column>
        <el-table-column prop="isSolve" label="是否解决"></el-table-column>
        <el-table-column prop="contactsName" label="提供人"></el-table-column>
        <el-table-column prop="saleName" label="机会"></el-table-column>
        <el-table-column prop="empName" label="所有者"></el-table-column>
        <el-table-column prop="cusName" label="客户"></el-table-column>
      </el-table>

      <el-pagination background :page-size="pageSize" :total="total"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     @current-change="handleCurrentChange"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

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
              <el-select v-model="addForm.cusId" @change="addFormCusChange"
                         clearable placeholder="请选择">
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
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

    <el-dialog title="修改需求" :visible.sync="editDialog" @close="editDialogClose">
      <el-form :model="editForm" label-position="right" label-width="80px"
               size="mini" ref="editFormRef" :rules="editFormRules">
        <el-row>
          <el-col>
            <el-form-item label="需求主题" prop="demandTitle">
              <el-input v-model="editForm.demandTitle"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="重要程度" prop="demandDegree">
              <el-select v-model="editForm.demandDegree">
                <el-option v-for="item in demandDegreeList" :key="item.label"
                           :label="item.value" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="需求内容" prop="demandContent">
              <el-input v-model="editForm.demandContent" type="textarea"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" :loading="editButtonLoading"
                   @click="editDemandClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {demandDegreeData} from '../../common/data/sale_data'
  import {demandHttp} from "../../network/pre_sale/demand";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {saleHttp} from "../../network/pre_sale/sale";
  import {userHttp} from "../../network/system/user";

  export default {
    name: "Demand",
    data() {
      return {
        editDialog:false,
        editForm:{},
        editFormRules:{
          demandTitle:[
            {required:true,message:'请输入需求主题',trigger:'blur'}
          ],
          demandDegree:[
            {required:true,message:'请选择',trigger:'change'}
          ],
          demandContent:[
            {required:true,message:'请输入需求内容',trigger:'blur'}
          ]
        },
        editButtonLoading:false,

        addForm:{
          demandTitle:'',
          cusId:'',
          contactsId:'',
          saleId:'',
          demandDegree:'一般',
          demandContent:'',
          empId:''
        },
        addFormRules:{
          demandTitle:[
            {required:true,message:'请输入客户需求',trigger:'blur'}
          ],
          cusId:[
            {required:true,message:'请选择客户',trigger:'change'}
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
        addDialog:false,
        customerList:[],
        contactsList:[],
        saleList:[],
        demandDegreeList:demandDegreeData,
        addDemandButtonLoading:false,

        searchInput:'',
        advancedSearch:false,
        searchForm:{
          demandTitle:'',
          cusId:'',
          contactsId:'',
          saleId:'',
          demandDegree:'',
          empId:'',
          isSolve:''
        },
        empList:[],

        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        rowDemandId:0,
        buttonDisabled:true
      }
    },
    methods:{
      delDemandClick() {
        this.$confirm('将删除该需求','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          demandHttp.delDemand(this.rowDemandId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message.error(res.message)
            }
          })
        })
      },

      editDemandClick() {
        this.$refs.editFormRef.validate(valid => {
          if (!valid) return
          this.editButtonLoading = true
          demandHttp.editDemand(this.editForm).then(res => {
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
        demandHttp.getById(this.rowDemandId).then(res => {
          this.editForm = res.data
        })
      },

      resetForm() {
        this.$refs.searchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowDemandId = 0
        this.buttonDisabled = true
      },

      searchClick() {
        this.searchForm.demandTitle = this.searchInput
        this.tableLoading = true
        demandHttp.search(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.tableLoading = false
            this.listForm = res.data.list
            this.total = res.data.total
          }
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
      addDemandClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addDemandButtonLoading = true
          this.addForm.empId = this.$store.state.empId
          demandHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addDemandButtonLoading = false
              this.addDialog = false
              this.initList()
            } else {
              this.$message.error(res.message)
              this.addDemandButtonLoading = false
            }
          })
        })
      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addDemandButtonLoading = false
      },
      openAddDialog() {
        this.addDialog = true
        this.initCustomerList()
      },

      advancedSearchClick() {
        this.tableLoading = true
        demandHttp.search(this.searchForm).then(res => {
          this.tableLoading = false
          this.listForm = res.data.list
        })
      },
      closeAdvancedSearch() {
        this.advancedSearch = !this.advancedSearch
        this.$refs.searchFormRef.resetFields()
      },

      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.tableLoading = true
        demandHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      initList() {
        this.tableLoading = true
        demandHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
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
      initEmpList() {
        userHttp.list().then(res => {
          this.empList = res.data.list
        })
      },
      handleRowClick(row,event,column) {
        this.rowDemandId = row.demandId
        if (this.rowDemandId != 0) {
          this.buttonDisabled = false
        }
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
      this.initEmpList()
      this.initContactsList()
      this.initSaleList()
    }
  }
</script>

<style scoped>

</style>
