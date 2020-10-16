<!--
@author: pengjia
@date: 2020/10/15
@description:
-->
<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" placeholder="请输入名称查询" clearable size="mini">
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="addDialog = true">添加线索</el-button>
          <el-button type="primary" icon="el-icon-zoom-in" size="mini"
                     @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button type="primary" icon="el-icon-refresh"
                     size="mini" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditDialog">修改线索</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delClue">删除线索</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px">
          <el-form :model="searchForm" ref="advancedSearchFormRef" size="mini" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="线索手机">
                  <el-input v-model="searchForm.cluePhone" size="mini" placeholder="请输入手机号" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="处理人">
                  <el-input v-model="searchForm.handlePerson" size="mini"
                            placeholder="请输入处理人（可做下拉框）" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="处理结果">
                  <el-select v-model="searchForm.handleResult" clearable size="mini">
                    <el-option label="有效" value="1"></el-option>
                    <el-option label="无效" value="0"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-form-item label="创建时间">
                  <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                  type="date" style="width: 46%" placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                  type="date" style="width: 46%" placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="线索状态">
                  <el-select disabled v-model="searchForm.clueStatus" clearable size="mini">
                    <el-option label="未处理" value="1"></el-option>
                    <el-option label="已处理" value="0"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item>
                  <el-button size="mini" icon="el-icon-switch-button"
                             @click="advancedSearch = !advancedSearch"></el-button>
                  <el-button size="mini" @click="advancedQueryClick"
                             type="primary" icon="el-icon-search"></el-button>
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
        <el-table-column prop="clueName" label="线索名称"></el-table-column>
        <el-table-column prop="cluePhone" label="联系方式"></el-table-column>
        <el-table-column prop="clueStatus" label="线索状态"></el-table-column>
        <el-table-column prop="handleResult" label="处理结果"></el-table-column>
        <el-table-column prop="handlePerson" label="处理人"></el-table-column>
        <el-table-column prop="createTime" label="创建时间">
          <template slot-scope="scope">
            {{scope.row.createTime | dateFormat}}
          </template>
        </el-table-column>
      </el-table>

      <el-pagination background @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="线索添加" :visible.sync="addDialog" @close="addHandleClose" width="30%">
      <el-form :model="addForm" label-width="80px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-form-item label="线索名称" prop="clueName">
          <el-input v-model="addForm.clueName" size="mini" placeholder="请输入" clearable></el-input>
        </el-form-item>
        <el-form-item label="线索号码" prop="cluePhone">
          <el-input v-model="addForm.cluePhone" size="mini" placeholder="请输入" clearable></el-input>
        </el-form-item>
        <el-form-item label="处理人" prop="handlePerson">
          <el-select v-model="addForm.handlePerson" size="mini" clearable>
            <el-option v-for="item in empList" :key="item.empId"
                       :label="item.empName" :value="item.empName">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addClueClick"
                   :loading="addClueButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="线索修改" :visible.sync="editDialog" @open="openBefore"
               width="30%" @close="editHandleClose">
      <el-form :aria-modal="editForm" label-width="80px"
               label-position="right" ref="editFormRef">
        <el-form-item>
          <el-tag type="warning" size="mini">只能修改通过弹框添加的线索</el-tag>
          <el-tag type="warning" size="mini">线索修改时，给线索所属员工下发修改通知</el-tag>
        </el-form-item>
        <el-form-item label="线索名称" prop="clueName">
          <el-input v-model="editForm.clueName" size="mini" clearable></el-input>
        </el-form-item>
        <el-form-item label="线索号码" prop="cluePhone">
          <el-input v-model="editForm.cluePhone" size="mini" clearable></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editClueClick"
                   :loading="editClueButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {clueHttp} from "../../network/pre_sale/clue";
  import {userHttp} from "../../network/system/user";

  export default {
    name: "Clue",
    data() {
      let checkMobile = (rule,value,cb) => {
        const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
        if (regMobile.test(value)) {
          return cb()
        }
        cb(new Error('请输入合法的手机号'))
      }
      return {
        editForm:{},
        editClueButtonLoading:false,
        editDialog:false,
        editFormRules:{
          clueName:[
            {required:true,message:'请输入账号',trigger:'blur'},
          ],
          cluePhone:[
            {required:true,message:'请输入手机号',trigger:'blur'},
            {validator:checkMobile}
          ]
        },

        addForm:{
          clueName:'',
          cluePhone:'',
          handlePerson:''
        },
        addDialog:false,
        formRules:{
          clueName:[
            {required:true,message:'请输入账号',trigger:'blur'},
          ],
          cluePhone:[
            {required:true,message:'请输入手机号',trigger:'blur'},
            {validator:checkMobile}
          ]
        },
        addClueButtonLoading:false,
        empList:[],

        searchInput:'',
        searchForm:{
          clueName:'',
          cluePhone:'',
          clueStatus:'',
          handleResult:'',
          handlePerson:'',
          startDate:'',
          endDate:''
        },

        listForm:[],
        buttonDisabled:true,
        rowClueId:0,
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        advancedSearch:false
      }
    },
    methods: {
      delClue(clueId) {
        this.$confirm('此操作将删除改线索，是否继续？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          clueId = this.rowClueId
          clueHttp.del(clueId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message({
                message:res.message,
                type:'error'
              })
            }
          })
        })
      },

      openBefore() {
        clueHttp.get(this.rowClueId).then(res => {
          if (res.code === 20000) {
            this.editDialog = true
            this.editForm = res.data
          } else {
            this.editDialog = false
            this.$message({
              message:res.message,
              type:'error'
            })
          }
        })
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editClueButtonLoading = false
      },
      editClueClick() {
        this.editClueButtonLoading = true
        this.editForm.clueId = this.rowClueId
        clueHttp.edit(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editClueButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editClueButtonLoading = false
          }
        })
      },
      openEditDialog() {
        this.editDialog = true
      },

      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addClueButtonLoading = false
      },
      addClueClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addClueButtonLoading = true
          clueHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.addClueButtonLoading = false
              this.addDialog = false
            } else {
              this.$message({
                message:res.message,
                type:"error"
              })
            }
          })
        })
      },

      resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowClueId = 0
        this.buttonDisabled = true
      },
      advancedQueryClick() {
        clueHttp.list(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
      searchInputClick() {
        this.tableLoading = true
        this.listForm.clueName = this.searchInput
        clueHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
          this.tableLoading = false
        })
      },

      initEmpList() {
        userHttp.list().then(res => {
          this.empList = res.data.list
        })
      },
      initList() {
        this.tableLoading = true
        clueHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      handleRowClick(row,event,column) {
        this.rowClueId = row.clueId
        if (this.rowClueId != 0) {
          this.buttonDisabled = false
        }
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        clueHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
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
      this.initEmpList()
    }
  }
</script>

<style scoped>

</style>
