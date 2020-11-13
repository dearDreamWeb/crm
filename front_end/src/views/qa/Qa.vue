<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入主题内容查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAdd">新增</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditCare">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="deleteCare">删除</el-button>
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
                <el-form-item prop="qaWtproblem" label="标题">
                  <el-input v-model="searchForm.qaWtproblem" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="qaJjsolve" label="答案">
                  <el-input v-model="searchForm.qaJjsolve" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="qaCustomerfk" label="录入人">
                  <el-select v-model="searchForm.empId">
                    <el-option v-for="item in edpList" :key="item.empId"
                               :label="item.empName" :value="item.empId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">

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

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="40"></el-table-column>
        <el-table-column prop="qaWtproblem" label="标题" sortable></el-table-column>
        <el-table-column prop="qaJjsolve" label="答案"></el-table-column>
        <el-table-column prop="empResp.empName" label="录入人"></el-table-column>
        <el-table-column prop="qaData" label="录入日期">
          <template slot-scope="scope">
            {{scope.row.qaData | dateFormat}}
          </template>
        </el-table-column>

      </el-table>

      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog
      title="新增"
      :visible.sync="addDialog"
      width="50%"
      @close="handleClose">
      <el-form ref="addform" :model="addform" :rules="rules" label-width="80px" style="height: 450px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="标题" prop="qaWtproblem">
              <el-input v-model="addform.qaWtproblem"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="12">
            <el-form-item label="录入人">
              <el-select v-model="addform.empId">
                <el-option v-for="item in edpList" :key="item.empId"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <el-form-item label="答案">
          <quill-editor v-model="addform.qaJjsolve" ref="myQuillEditor" style="height: 300px;" :options="editorOption">
          </quill-editor>
        </el-form-item>
         <!-- <el-col :span="8">
            <el-form-item label="录入日期">
              <el-date-picker type="date" placeholder="选择日期" v-model="addform.qaData" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>-->
      </el-form>
      <span slot="footer" style="text-align: center;">
        <el-button @click="addDialog = false">取 消</el-button>
        <el-button type="primary" @click="addClick"
                   :loading="addDictButtonLoading">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="修改"
      :visible.sync="editDialog"
      width="50%"
      @close="editHandleClose">
      <el-form ref="updateform" :model="updateform" :rules="rules" label-width="80px" style="height: 450px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="标题" prop="qaWtproblem">
              <el-input v-model="updateform.qaWtproblem"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="8">
            <el-form-item label="录入人">
              <el-select v-model="updateform.empId">
                <el-option v-for="item in edpList" :key="item.empId"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="录入日期">
              <el-date-picker type="date" placeholder="选择日期" v-model="updateform.qaData" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>

        </el-row>

        <el-form-item label="答案">
          <quill-editor v-model="updateform.qaJjsolve" ref="myQuillEditor" style="height: 300px;" :options="editorOption">
          </quill-editor>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取 消</el-button>
        <el-button type="warning" @click="editCareClick" :loading="editDictButtonLoading">确 定</el-button>
      </span>
    </el-dialog>
  </div>


</template>
<script>
  import {qaHttp} from "../../network/system/qa";

  import {
    quillEditor
  } from 'vue-quill-editor'

  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import {userHttp} from "../../network/system/user";
  // import 'quill/dist/quill.bubble.css'

  export default {
    name: 'FuncFormsEdit',
    components: {
      quillEditor
    },
    data() {
      return {
        edpList:[],
        content: null,
        editorOption: {},
        rowCareId:0,
        addform:{
          qaWtproblem:'',
          qaJjsolve:'',
          empId:'',
          qaCustomerfk:'',
          qaData:'',

          date1: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },

        searchForm:{
          qaWtproblem:'',
          qaJjsolve:'',
          qaCustomerfk:'',
          startDate:'',
          empId:'',
          endDate:'',
          region: '',
          date1: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        advancedSearch:false,
        updateform:{},
        addDialog:false,
        buttonDisabled:true,
        addDictButtonLoading:false,
        editDictButtonLoading:false,
        editDialog:false,
        searchInput:'',
        qaWtproblem:'',
        qaJjsolve:'',
        qaCustomerfk:'',
        qaData:'',
        listForm:[],
        tableLoading:'',
        total:0,
        pageNum:1,
        pageSize:3,
        value: '',
        input: '',

        tableData: [],
        form: {
          qaWtproblem:'',
          qaJjsolve:'',
          empId:'',
          qaCustomerfk:'',
          region: '',
          date1: '',
          qaData: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        num: 1,
        rules:{
          qaWtproblem:[
            {required: true, message: '请输入活动名称', trigger: 'blur'}
          ]

        }
      }
    },
    methods:{

      openAddDialog() {
        this.addDialog = true
        this.initEdpList()
      },
      initEdpList(){
        userHttp.list().then(res =>{
          this.edpList = res.data.list
        })
      },
      addClick(){
        this.$refs["addform"].validate(valid => {
          if (!valid) return
          this.addDictButtonLoading = true
          qaHttp.add(this.addform).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.addDialog = false
              this.addDictButtonLoading = false
            } else {
              this.$message({
                message:res.message,
                type:"error"
              })
              this.addDictButtonLoading = false
            }
          })
        })
      },
      resetForm(){
        this.$refs["advancedSearchFormRef"].resetFields()
        this.searchInput = ''
        this.initList()
        this.rowCareId = 0
        this.buttonDisabled = true
      },
      advancedQueryClick(){
        qaHttp.queryEmp(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
      editCareClick(){
        this.editDictButtonLoading = true
        this.updateform.qaId = this.rowCareId
        qaHttp.update(this.updateform).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editDictButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editDictButtonLoading = false
          }
        })
      },
      openEditCare(){
        this.editDialog = true;
        this.getEmpDetail()
      },
      getEmpDetail(){
        qaHttp.get(this.rowCareId).then(res =>{
          console.log("编辑获得的数据",res.data);
          this.updateform = res.data;
          this.updateform.empName = res.data.empResp.empName
        })
      },
      deleteCare() {
        this.$confirm('此操作将永久删除，是否继续','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          qaHttp.del(this.rowCareId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()

            } else {
              this.$message.error(res.message)
            }
          })
        })
      },

      editHandleClose() {
        this.$refs["updateform"].resetFields()
        this.editDictButtonLoading = false
      },

      addHandleClose() {
        this.$refs["addform"].resetFields()
      },

      searchInputClick(){
        this.listForm.qaWtproblem = this.searchInput
        qaHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },


      handleClose(){
        this.$refs["addform"].resetFields()
      },
      handleRowClick(row,event,column) {
        this.rowCareId = row.qaId
        if (this.qaId != 0) {
          this.buttonDisabled = false
        }
      },
      iHeaderCellStyle(){

      },
      iHeaderRowStyle(){

      },
      handleCurrentChange(pageIndex){
        this.searchForm.pageNum = pageIndex
        this.searchForm.pageSize = this.pageSize
        qaHttp.queryEmp(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      initList() {
        qaHttp.listPage(this.pageNum, this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      //打开新增的窗口
      openAdd(){
        this.addDialog=true;//显示新增的对话框
        //重置新增的表单中的内容
        this.addform={};
      }
    },
    created() {
      this.initList()
      this.initEdpList()
    }
  }
</script>

<style scoped>

</style>
