<!--
@author: pengjia
@date: 2020/10/12
@description:
-->
<template>
<div>
  <el-card>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-input v-model="searchInput" placeholder="请输入活动名称搜索" clearable size="mini">
          <el-button slot="append" icon="el-icon-search" @click="searchClick"></el-button>
        </el-input>
      </el-col>
      <el-col :span="10">
        <el-button type="primary" size="mini" icon="el-icon-plus"
                   @click="openAddDialog">添加活动</el-button>
        <el-button type="primary" size="mini" icon="el-icon-refresh"
                   @click="refreshClick"></el-button>
      </el-col>
      <el-col :span="8">
        <el-button type="warning" size="mini" icon="el-icon-edit"
                   :disabled="buttonDisabled" @click="openEditDialog">修改活动</el-button>
        <el-button type="danger" size="mini" icon="el-icon-delete"
                   :disabled="buttonDisabled" @click="deleteActivity">删除活动</el-button>
        <el-button type="success" size="mini" icon="el-icon-tickets"
                   :disabled="buttonDisabled" @click="openAuthDialog">分配活动</el-button>
      </el-col>
    </el-row>

    <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
              :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
              highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column label="活动名称" prop="activityTitle" show-overflow-tooltip></el-table-column>
      <el-table-column label="活动内容" prop="content" show-overflow-tooltip></el-table-column>
      <el-table-column label="活动链接" prop="activityLink" show-overflow-tooltip></el-table-column>
      <el-table-column label="访问量" prop="views"></el-table-column>
      <el-table-column label="创建人" prop="createBy"></el-table-column>
      <el-table-column label="开始时间">
        <template slot-scope="scope">
          {{scope.row.startTime | dateFormat}}
        </template>
      </el-table-column>
      <el-table-column label="结束时间">
        <template slot-scope="scope">
          {{scope.row.endTime | dateFormat}}
        </template>
      </el-table-column>
    </el-table>

    <el-row :gutter="20">
      <el-col :span="6" v-for="item in listForm" :key="item.activityId">
        <el-card shadow="hover" style="margin-top: 10px">
          <el-row type="flex" justify="space-between">
            <el-col :span="1">
              <el-tag>{{item.activityTitle}}</el-tag>
            </el-col>
            <el-col :span="10">
              <el-tag>{{item.views}}</el-tag>
            </el-col>
          </el-row>
          <hr>
          <!--          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">-->
          <div>
            <hr>
            <span style="font-family: 楷体">{{item.content}}</span>
            <div>
              <el-button type="text">编辑</el-button>
              <el-button type="text" style="float: right">删除</el-button>
            </div>
            <el-tag>{{item.startTime | dateFormat}}</el-tag>
            至
            <el-tag>{{item.endTime | dateFormat}}</el-tag>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-card>

  <el-dialog title="活动添加" :visible.sync="addDialog" @close="addHandleClose">
    <el-form :model="addForm" label-width="100px" label-position="right"
             ref="addFormRef" :rules="formRules">
      <el-row>
        <el-col :span="12">
          <el-form-item label="活动名称" prop="activityTitle">
            <el-input v-model="addForm.activityTitle" clearable size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="活动链接" prop="activityLink">
            <el-input disabled v-model="addForm.activityLink" clearable size="mini"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="内容" prop="content">
            <el-input v-model="addForm.content" clearable size="mini" type="textarea"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="活动日期">
            <el-date-picker
              v-model="addForm.activityDate"
              type="daterange"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer">
      <el-button @click="addDialog = false">取消</el-button>
      <el-button type="primary" :loading="addActivityLoading"
                 @click="addActivityClick">确定</el-button>
    </span>
  </el-dialog>

  <el-dialog title="活动修改" :visible.sync="editDialog" @close="editHandleClose">
    <el-form :model="editForm" label-width="100px" label-position="right"
             ref="editFormRef" :rules="formRules">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="活动标题" prop="activityTitle">
            <el-input v-model="editForm.activityTitle" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="活动链接" prop="activityLink">
            <el-input disabled v-model="editForm.activityLink" size="mini"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="活动内容" prop="content">
            <el-input size="mini" v-model="editForm.content" type="textarea"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="活动日期">
            <el-date-picker
              v-model="editForm.activityDate"
              type="daterange"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer">
      <el-button @click="editDialog = false">取消</el-button>
      <el-button type="primary" @click="editActivityClick"
                 :loading="editActivityLoading">确定</el-button>
    </span>
  </el-dialog>

  <el-dialog title="分配活动" :visible.sync="authDialog" @close="authHandleClose">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card>
          占位卡片，后续升级
          <hr>
          分配活动后，提示已分配员工
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <el-form :model="authForm">
            <el-form-item label="选择用户">
              <el-select v-model="authForm.empIdList" multiple filterable clearable>
                <el-option v-for="item in empList" :key="item.empId"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
    <span slot="footer">
            <el-button @click="authDialog = false">取消</el-button>
            <el-button type="primary" @click="authClick"
                       :loading="authButtonLoading">确定</el-button>
          </span>
  </el-dialog>

</div>
</template>

<script>
  import {activityHttp} from "../../network/pre_sale/activity";
  import {userHttp} from "../../network/system/user";

  export default {
    name: "Activity",
    data() {
      return {

        authDialog:false,
        authForm:{
          activityId:'',
          empIdList:[]
        },
        authButtonLoading:false,
        empList:[],

        searchInput:'',

        editDialog:false,
        editActivityLoading:false,
        editForm:{},

        addDialog:false,
        addForm:{
          activityTitle:'',
          content:'',
          activityLink:'http://localhost:8080/clue_page',
          createBy:'',
          startTime:'',
          endTime:'',
          activityDate:'',
          empId:0,
          views:0
        },
        formRules:{
          activityTitle:[
            {required:true,message:'请输入活动名称',trigger:'blur'}
          ],
          activityLink:[
            {required:true,message:'请输入活动链接',trigger:'blur'}
          ]
        },
        addActivityLoading:false,

        listForm:[],
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        buttonDisabled:true,
        rowActivityId:0,
        currentDate: new Date()
      }
    },
    methods:{
      refreshClick() {
        this.searchInput = ''
        this.initList()
        this.rowActivityId = 0
      },

      authClick() {
        this.authForm.activityId = this.rowActivityId
        this.authButtonLoading = true
        activityHttp.authActivity(this.authForm).then(res => {
          if (res.code === 20000) {
            this.authDialog = false
            this.$message.success("分配成功")
            this.initList()
            this.authButtonLoading = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.authButtonLoading = false
          }
        })
      },
      authHandleClose() {
        this.authForm.empIdList = []
        this.authButtonLoading = false
      },
      openAuthDialog() {
        this.authDialog = true
        this.initEmpList()
      },
      initEmpList() {
        userHttp.listNotAuth(this.rowActivityId).then(res => {
          this.empList = res.data
        })
      },

      searchClick() {
        this.listForm.activityTitle = this.searchInput
        activityHttp.list(this.listForm).then(res => {
          this.listForm = res.data
        })
      },

      deleteActivity(activityId) {
        this.$confirm('此操作将删除该数据，是否继续？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          activityId = this.rowActivityId
          activityHttp.del(activityId).then(res => {
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

      openEditDialog() {
        this.editDialog = true
        this.getActivityDetail()
      },
      getActivityDetail() {
        activityHttp.getActivity(this.rowActivityId).then(res => {
          this.editForm = res.data
          this.editForm.activityDate = [res.data.startTime,res.data.endTime]
        })
      },
      editActivityClick() {
        this.editActivityLoading = true
        this.editForm.activityId = this.rowActivityId
        activityHttp.edit(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editActivityLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editActivityLoading = false
          }
        })
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editActivityLoading = false
      },

      openAddDialog() {
        this.addDialog = true
      },
      addActivityClick() {
        this.addForm.startTime = this.addForm.activityDate[0]
        this.addForm.endTime = this.addForm.activityDate[1]
        this.addForm.createBy = this.$store.state.empName
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addActivityLoading = true
          activityHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.addActivityLoading = false
              this.addDialog = false
            } else {
              this.addActivityLoading = false
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
        this.addActivityLoading = false
        this.addForm.activityDate = ''
      },

      handleRowClick(row,event,column) {
        this.rowActivityId = row.activityId
        if (this.rowActivityId != 0) {
          this.buttonDisabled = false
        }
      },
      initList() {
        this.tableLoading = true
        activityHttp.list(this.listForm).then(res => {
          this.listForm = res.data
          this.tableLoading = false
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
.image{
  width: 100%;
}
</style>
