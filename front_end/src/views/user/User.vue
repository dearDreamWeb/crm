<template>
  <div>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" placeholder="请输入账号查询" size="mini" class="input-with-select">
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button type="primary" size="mini" icon="el-icon-plus" @click="openAddDialog">添加用户</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditEmp">修改用户</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="deleteEmp">删除用户</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
          <el-form :model="searchForm" ref="advancedSearchFormRef"
                   size="mini" label-position="right" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item prop="empName" label="昵称">
                  <el-input v-model="searchForm.empName" size="mini" placeholder="请输入昵称" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="email" label="邮箱">
                  <el-input v-model="searchForm.email" size="mini" placeholder="请输入邮箱" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="phone" label="手机">
                  <el-input v-model="searchForm.phone" size="mini" placeholder="请输入手机" clearable></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-form-item label="日期">
                  <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item prop="empStatus" label="状态">
                  <el-select v-model="searchForm.empStatus" clearable>
                    <el-option label="正常" value="1"></el-option>
                    <el-option label="冻结" value="0"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item prop="sex" label="性别">
                  <el-select v-model="searchForm.sex" clearable>
                    <el-option label="男" value="1"></el-option>
                    <el-option label="女" value="0"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item>
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
        <el-table-column prop="empName" label="账号" sortable width="130px"></el-table-column>
        <el-table-column prop="nickName" label="昵称"></el-table-column>
        <el-table-column prop="email" label="邮箱" width="180px"></el-table-column>
        <el-table-column prop="sex" label="性别" width="100px">
          <template slot-scope="scope">
            {{scope.row.sex | sexFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机"></el-table-column>
        <el-table-column prop="empStatus" label="状态" align="center">
          <template slot-scope="scope">
            <el-switch active-color="#13ce66" inactive-color="#ff4949"
                       v-model="scope.row.empStatus" :active-value="1"
                       :inactive-value="0" @change="empStatusChange(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column prop="创建时间" label="creteTime">
          <template slot-scope="scope">
            {{scope.row.createTime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>
      </el-table>

      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="员工添加" :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" label-width="100px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账号" prop="empName">
              <el-input v-model="addForm.empName" size="mini" placeholder="请输入账号（必填）" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="昵称" prop="nickName">
              <el-input v-model="addForm.nickName" size="mini" placeholder="请输入昵称" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="addForm.email" size="mini" placeholder="请输入邮箱" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="passWord">
              <el-input v-model="addForm.passWord" size="mini" placeholder="请输入密码" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="addForm.phone" size="mini" placeholder="请输入手机号（必填）" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="addForm.remark" type="textarea" size="mini" placeholder="请输入备注" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="部门">
              <el-select v-model="addForm.deptIdList" placeholder="请选择部门">
                <el-option v-for="item in deptList" :key="item.deptId"
                           :label="item.deptName" :value="item.deptId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-radio-group v-model="addForm.sex">
                <el-radio :label=1>男</el-radio>
                <el-radio :label=0>女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addEmpClick"
                   :loading="addEmpButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="员工修改" :visible.sync="editDialog" @close="editHandleClose">
      <el-form :model="editForm" label-width="100px" :rules="formRules"
               label-position="right" ref="editFormRef">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="账号" prop="empName">
              <el-input v-model="editForm.empName" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="昵称" prop="nickName">
              <el-input v-model="editForm.nickName" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="editForm.email" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="editForm.sex">
                <el-radio :label=1>男</el-radio>
                <el-radio :label=0>女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="editForm.phone" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="editForm.remark" type="textarea" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col>
            <el-form-item label="部门">
              <el-select v-model="editForm.deptId">
                <el-option v-for="item in deptList" :key="item.deptId"
                           :label="item.deptName" :value="item.deptId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editEmpClick"
                   :loading="editEmpButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {userHttp} from "../../network/system/user";
  import {deptHttp} from "../../network/system/dept";

  export default {
    name: "User",
    data(){
      let checkMobile = (rule,value,cb) => {
        const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
        if (regMobile.test(value)) {
          return cb()
        }
        cb(new Error('请输入合法的手机号'))
      }
      return{
        searchInput:'',
        searchForm:{
          empName:'',
          nickName:'',
          email:'',
          sex:'',
          phone:'',
          startDate:'',
          endDate:'',
          pageNum:1,
          pageSize:10
        },
        rowEmpId: 0,
        buttonDisabled:true,
        pageNum:1,
        pageSize:10,
        total:1,
        listForm:[],
        tableLoading:false,
        advancedSearch:false,
        addDialog:false,
        addForm:{
          empName:'',
          nickName:'',
          email:'',
          sex:1,
          phone:'',
          passWord:'',
          remark:'',
          deptIdList:[]
        },
        formRules:{
          empName:[
            {required:true,message:'请输入账号',trigger:'blur'},
            {min:3,max:10,message:'账号长度在3~10个字符之间'}
          ],
          passWord:[
            {required:true,message:'请输入密码',trigger:'blur'},
            {min:6,max:12,message:'密码长度在6~12个字符之间'}
          ],
          phone:[
            {required:true,message:'请输入手机号',trigger:'blur'},
            {validator:checkMobile}
          ]
        },
        addEmpButtonLoading:false,
        deptList:[],
        editDialog:false,
        editEmpButtonLoading:false,
        editForm:{}
      }
    },
    methods:{
      resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
      },
      advancedQueryClick() {
        userHttp.queryEmp(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
      empStatusChange(emp) {
        this.$confirm('是否继续？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(()=>{
          this.tableLoading = true
          userHttp.editEmp(emp).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message.error(res.message)
              this.tableLoading = false
            }
          })
        })
      },
      searchInputClick() {
        this.listForm.empName = this.searchInput
        userHttp.queryEmp(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editEmpButtonLoading = false
      },
      openEditEmp() {
        this.editDialog = true
        this.getEmpDetail()
      },
      editEmpClick() {
        this.editEmpButtonLoading = true
        this.editForm.empId = this.rowEmpId
        userHttp.editEmp(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editEmpButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editEmpButtonLoading = false
          }
        })
      },
      getEmpDetail() {
        userHttp.getEmp(this.rowEmpId).then(res => {
          this.editForm = res.data
        })
      },
      addEmpClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addEmpButtonLoading = true
          userHttp.addEmp(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.addEmpButtonLoading = false
              this.addDialog = false
            } else {
              this.addEmpButtonLoading = false
              this.$message({
                message:res.message,
                type:"error"
              })
            }
          })
        })
      },
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addForm.deptIdList = []
        this.addEmpButtonLoading = false
      },
      deleteEmp(empId) {
        this.$confirm('此操作将删除该用户，是否继续？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          empId = this.rowEmpId
          userHttp.del(empId).then(res => {
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
      handleRowClick(row,event,column) {
        this.rowEmpId = row.empId
        if (this.rowEmpId != 0) {
          this.buttonDisabled = false
        }
      },
      openAddDialog() {
        this.addDialog = true
      },
      handleCurrentChange(pageIndex) {
        this.searchForm.pageNum = pageIndex
        this.searchForm.pageSize = this.pageSize
        userHttp.queryEmp(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      initList() {
        this.tableLoading = true
        userHttp.queryEmp(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list;
            this.total = res.data.total
            this.pageNum = res.data.pageNum
            this.tableLoading = false
          } else {
            this.$message({
              message:res.data,
              type:'error'
            })
          }
        })
      },
      initDeptList() {
        deptHttp.listAll().then(res => {
          this.deptList = res.data
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
      this.initDeptList()
    }
  }
</script>

<style scoped>

</style>
