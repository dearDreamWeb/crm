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
          <el-row :gutter="10">
            <el-col :span="20">
              <el-input v-model="filterText" size="mini" clearable placeholder="请输入"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button size="mini" icon="el-icon-search" type="primary"
                         :disabled="treeQueryDisabled" @click="treeQueryClick"></el-button>
            </el-col>
          </el-row>
          <div style="margin-top: 20px;height: 450px;overflow: auto">
            <el-tree :data="demandTreeData" :props="defaultProps" ref="tree"
                     @node-click="handleNodeClick" :filter-node-method="filterNode"></el-tree>
          </div>
        </el-col>
        <el-col :span="1">
          <el-divider direction="vertical"></el-divider>
        </el-col>
        <el-col :span="17">
          <el-row :gutter="20">
            <el-col :span="7">
              <el-input v-model="searchInput" size="mini" clearable placeholder="请输入">
                <el-button @click="searchInputClick" icon="el-icon-search" slot="append"></el-button>
              </el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" icon="el-icon-plus" size="mini"
                         @click="openAddDialog">新增</el-button>
              <el-button type="primary" icon="el-icon-zoom-in" size="mini"
                         @click="advancedSearch = !advancedSearch">高查</el-button>
              <el-button type="primary" icon="el-icon-refresh" size="mini"></el-button>
            </el-col>
            <el-col :span="9">
              <el-button type="warning" size="mini" icon="el-icon-edit"
                         :disabled="buttonDisabled" @click="openEditDialog">修改</el-button>
              <el-button type="danger" size="mini" icon="el-icon-delete"
                         :disabled="buttonDisabled" @click="delSolution">删除</el-button>
            </el-col>
          </el-row>

          <transition name="el-zoom-in:top">
            <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
              <el-form :model="searchForm" ref="searchFormRef" size="mini"
                       label-width="80px" label-position="right">
                <el-row>
                  <el-col :span="4">
                    <el-form-item label="高级查询"></el-form-item>
                  </el-col>
                  <el-col :span="14">
                    <el-form-item label="时间">
                      <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd"
                                      value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                      placeholder="请输入"></el-date-picker>
                      <span>-</span>
                      <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd"
                                      value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                      placeholder="请输入"></el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item>
                      <el-button size="mini" icon="el-icon-zoom-out"
                                 @click="closeAdvancedSearch"></el-button>
                      <el-button size="mini" @click="advancedSearchClick" type="primary"
                                 icon="el-icon-search"></el-button>
                    </el-form-item>
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
                    <el-form-item label="机会" prop="saleId">
                      <el-select v-model="searchForm.saleId" clearable placeholder="请选择">
                        <el-option v-for="item in saleList" :key="item.saleId"
                                   :label="item.saleName" :value="item.saleId"></el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="需求" prop="demandId">
                      <el-select v-model="searchForm.demandId" clearable placeholder="请选择">
                        <el-option v-for="item in demandList" :key="item.demandId"
                                   :label="item.demandTitle" :value="item.demandId"></el-option>
                      </el-select>
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
            <el-table-column prop="solutionTitle" label="方案主题"></el-table-column>
            <el-table-column prop="createTime" label="提交时间">
              <template slot-scope="scope">
                {{scope.row.createTime | dateFormat}}
              </template>
            </el-table-column>
            <el-table-column prop="content" label="方案内容"></el-table-column>
            <el-table-column prop="feedback" label="客户反馈"></el-table-column>
            <el-table-column prop="cusName" label="客户"></el-table-column>
            <el-table-column prop="saleName" label="机会"></el-table-column>
            <el-table-column prop="demandTitle" label="需求"></el-table-column>
          </el-table>

          <el-pagination background :page-size="pageSize" :total="total"
                         :current-page="pageNum" :page-sizes="[1,2,5,10]"
                         @current-change="handleCurrentChange"
                         layout="prev, pager, next, jumper, total">
          </el-pagination>
        </el-col>
      </el-row>
    </el-card>

    <el-dialog title="制定方案" :visible.sync="addDialog" @close="addDialogClose">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef"
               label-width="80px" label-position="right" size="mini">
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

    <el-dialog title="修改方案" :visible.sync="editDialog" @close="editDialogClose">
      <el-form :model="editForm" label-position="right" label-width="80px"
               ref="editFormRef" :rules="editFormRules" size="mini">
        <el-row>
          <el-col>
            <el-form-item label="方案主题" prop="solutionTitle">
              <el-input v-model="editForm.solutionTitle" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户" prop="cusId">
              <el-select v-model="editForm.cusId" disabled>
                <el-option v-for="item in customerList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机会" prop="saleId">
              <el-select v-model="editForm.saleId" disabled>
                <el-option v-for="item in saleList" :key="item.saleId"
                           :label="item.saleName" :value="item.saleId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="需求" prop="demandId">
              <el-select v-model="editForm.demandId" disabled>
                <el-option v-for="item in demandList" :key="item.demandId"
                           :label="item.demandTitle" :value="item.demandId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="时间" prop="createTime">
              <el-date-picker v-model="editForm.createTime" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date" disabled
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="内容" prop="content">
              <el-input type="textarea" v-model="editForm.content" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" :loading="editButtonLoading"
                   @click="editSolutionClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {solutionHttp} from "../../network/pre_sale/solution";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {saleHttp} from "../../network/pre_sale/sale";
  import {demandHttp} from "../../network/pre_sale/demand";

  export default {
    name: "Solution",
    data() {
      return {
        count:0,
        filterText:'',
        treeQueryDisabled:true,
        treeSolutionId:0,

        searchInput:'',
        advancedSearch:false,
        searchForm:{
          solutionTitle:'',
          cusId:'',
          saleId:'',
          demandId:'',
          startDate:'',
          endDate:''
        },

        editDialog:false,
        editButtonLoading:false,
        editForm:{},
        editFormRules:{
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
        editSaleId:0,

        addForm:{
          solutionTitle:'',
          content:'',
          cusId:'',
          saleId:'',
          demandId:'',
          createTime:new Date()
        },
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
        addButtonLoading:false,
        addDialog:false,
        customerList:[],
        demandList:[],
        saleList:[],

        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        rowSolutionId:0,
        buttonDisabled:true,

        demandTreeData: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    watch: {
      filterText(val) {
        this.$refs.tree.filter(val);
      }
    },
    methods:{
      treeQueryClick() {
        this.searchForm.demandId = this.treeSolutionId
        solutionHttp.list(this.searchForm).then(res => {
          this.listForm = res.data.list
        })
      },
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },

      initDemandTreeList() {
        demandHttp.listAll().then(res => {
          for (let i=0;i<res.data.list.length;i++) {
            let obj = {
              label:res.data.list[i].demandTitle,
              id:res.data.list[i].demandId,
              children:{}
            }
            this.demandTreeData.push(obj)
          }
        })
      },

      searchInputClick() {
        this.searchForm.solutionTitle = this.searchInput
        this.tableLoading = true
        solutionHttp.list(this.searchForm).then(res => {
          this.tableLoading = false
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
        })
      },
      resetForm() {
        this.$refs.searchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowSolutionId = 0
        this.buttonDisabled = true
      },
      advancedSearchClick() {
        this.tableLoading = true
        solutionHttp.list(this.searchForm).then(res => {
          this.tableLoading = false
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
        })
      },
      closeAdvancedSearch() {
        this.advancedSearch = !this.advancedSearch
        this.$refs.searchFormRef.resetFields()
      },

      delSolution() {
        this.$confirm('此操作将删除该方案，请谨慎操作','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          solutionHttp.del(this.rowSolutionId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message.error(res.message)
            }
          })
        })
      },

      editSolutionClick() {
        this.$refs.editFormRef.validate(valid => {
          if (!valid) return
          this.editButtonLoading = true
          solutionHttp.edit(this.editForm).then(res => {
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
        solutionHttp.getSolution(this.rowSolutionId).then(res => {
          this.editForm = res.data
        })
        customerHttp.listAll().then(res => {
           this.customerList = res.data
        })
        saleHttp.list_all().then(res => {
          this.saleList = res.data.list
        })
        demandHttp.listAll().then(res => {
          this.demandList = res.data.list
        })
      },

      addSolutionClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addButtonLoading = true
          solutionHttp.addSolution(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.addButtonLoading = false
              this.addDialog = false
              this.initList()
            } else {
              this.$message.error(res.message)
              this.addButtonLoading = false
            }
          })
        })
      },
      addDialogClose() {
        this.$refs.addFormRef.resetFields()
        this.addButtonLoading = false
      },
      openAddDialog() {
        this.addDialog = true
        this.initCustomerList()
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

      handleRowClick(row,event,column) {
        this.rowSolutionId = row.solutionId
        if (this.rowSolutionId  != 0) {
          this.buttonDisabled = false
        }
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.tableLoading = true
        solutionHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      initList() {
        this.tableLoading = true
        solutionHttp.listPage(this.pageNum,this.pageSize).then(res => {
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
      initSaleList() {
        saleHttp.list_all().then(res => {
          this.saleList = res.data.list
        })
      },
      initDemandList() {
        demandHttp.listAll().then(res => {
          this.demandList = res.data.list
        })
      },

      handleNodeClick(data) {
        console.log(data.id);
        this.treeSolutionId = data.id
        if (this.treeSolutionId != 0) {
          this.treeQueryDisabled = false
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
      this.initSaleList()
      this.initDemandList()
      this.initCustomerList()
      this.initDemandTreeList()
    }
  }
</script>

<style scoped>
  .el-divider--vertical{
    height: 2em !important;
  }
</style>
