<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入内容" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">添加权限</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditDept">修改权限</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="deleteDept">删除权限</el-button>
          <el-button type="success" size="mini" icon="el-icon-tickets"
                     :disabled="buttonDisabled" @click="authClick">角色授权</el-button>
        </el-col>
      </el-row>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="deptName" label="权限名称" sortable></el-table-column>
        <el-table-column prop="deptKey" label="标识"></el-table-column>
        <el-table-column prop="createTime" label="创建时间">
          <template slot-scope="scope">
            {{scope.row.createTime | dateFormat}}
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog title="权限添加(重复添加已删除的记录...)" :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" label-width="100px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="权限名称" prop="deptName">
              <el-input v-model="addForm.deptName" size="mini" placeholder="请输入权限名称" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="权限标识" prop="deptKey">
              <el-input v-model="addForm.deptKey" size="mini" placeholder="请输入权限标识" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addDeptClick" :loading="addDeptButtonLoading">
          确定
        </el-button>
      </span>
    </el-dialog>

    <el-dialog title="权限修改" :visible.sync="editDialog" @close="editHandleClose">
      <el-form :model="editForm" label-width="100px" :rules="formRules"
               label-position="right" ref="editFormRef">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="权限名称" prop="deptName">
              <el-input v-model="editForm.deptName" size="mini"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="权限标识" prop="deptKey">
              <el-input v-model="editForm.deptKey" size="mini"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editDeptClick"
                   :loading="editDeptButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="授权" :visible.sync="allotDialog" @close="authDialogClose">
      <el-tree :data="menuTree" :props="defaultProps" ref="treeRef"
               show-checkbox node-key="menuId" default-expand-all
               :default-checked-keys="defaultKeys">
      </el-tree>
      <span slot="footer">
        <el-button @click="allotDialog = false">取消</el-button>
        <el-button type="primary" @click="allotClick"
                   :loading="authMenuButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {deptHttp} from "../../network/system/dept";
  import {menuHttp} from "../../network/system/menu";

  export default {
    name: "Dept",
    data() {
      return {
        authMenuButtonLoading:false,
        allotDialog:false,
        searchInput:'',
        editDialog:false,
        editForm:{},
        editDeptButtonLoading:false,
        addDeptButtonLoading:false,
        formRules:{
          deptName:[
            {required:true,message:'请输入权限名称',trigger:'blur'},
          ],
          deptKey:[
            {required:true,message:'请输入权限标识',trigger:'blur'}
          ]
        },
        addForm: {
          deptName:'',
          deptKey:''
        },
        addDialog:false,
        rowDeptId: 0,
        tableLoading:false,
        buttonDisabled:true,
        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        menuTree:[],
        defaultKeys:[],
        defaultProps: {
          children:'children',
          label:'menuName'
        },
        dept:{
          deptId:'',
          menuIdList:[]
        }
      }
    },
    methods: {
      allotClick() {
        this.dept.menuIdList = [
          ...this.$refs.treeRef.getCheckedKeys(),//获取全选的menuId
          ...this.$refs.treeRef.getHalfCheckedKeys()//获取半选的menuId
        ]
        this.authMenuButtonLoading = true
        deptHttp.auth(this.dept).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.authMenuButtonLoading = false
          } else {
            this.$message.error(res.message)
            this.authMenuButtonLoading = false
          }
          this.allotDialog = false
        })
      },
      authDialogClose() {
        this.defaultKeys = []
      },
      authClick(deptId) {
        this.allotDialog = true
        deptId = this.rowDeptId
        this.dept.deptId = this.rowDeptId
        menuHttp.tree().then(res => {
          this.menuTree = res.data
        })
        deptHttp.getDept(deptId).then(res => {
          this.defaultKeys = res.data.menuIds
        })
      },
      searchInputClick() {
        this.listForm.deptName = this.searchInput
        deptHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      resetForm() {
        this.initDept()
        this.searchInput = ''
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editDeptButtonLoading = false
      },
      editDeptClick() {
        this.editDeptButtonLoading = true
        this.editForm.deptId = this.rowDeptId
        deptHttp.editDept(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initDept()
            this.editDeptButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editDeptButtonLoading = false
          }
        })
      },
      openEditDept() {
        this.editDialog = true
        this.getDeptDetail()
      },
      getDeptDetail() {
        deptHttp.getDept(this.rowDeptId).then(res => {
          this.editForm = res.data
        })
      },
      deleteDept(deptId) {
        this.$confirm('此操作将删除改权限，是否继续？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          deptId = this.rowDeptId
          deptHttp.del(deptId).then(res => {
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
      openAddDialog() {
        this.addDialog = true
      },
      addDeptClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addDeptButtonLoading = true
          deptHttp.addDept(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initDept()
              this.addDeptButtonLoading = false
              this.addDialog = false
            } else {
              this.addDeptButtonLoading = false
              this.$message({
                message:res.message,
                type:'error'
              })
            }
          })
        })
      },
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addDeptButtonLoading = false
      },
      handleRowClick(row,event,column) {
        this.rowDeptId = row.deptId
        if (this.rowDeptId != 0) {
          this.buttonDisabled = false
        }
      },
      initDept() {
        deptHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
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
      this.initDept()
    }
  }
</script>

<style scoped>

</style>
