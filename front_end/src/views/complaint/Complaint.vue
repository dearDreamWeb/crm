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
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">新增</el-button>
          <el-button size="mini" type="primary" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
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
                <el-form-item label="客户">
                  <el-select v-model="searchForm.cusId">
                    <el-option v-for="item in empList" :key="item.cusId"
                               :label="item.cusName" :value="item.cusId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="complaintZt" label="投诉主题">
                  <el-input v-model="searchForm.complaintZt" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="complaintReceptionist" label="接待人">
                  <el-select v-model="searchForm.empId">
                    <el-option v-for="item in edpList" :key="item.empId"
                               :label="item.empName" :value="item.empId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">

              <el-col :span="6">
                <el-form-item prop="complaintClassification" label="分类">
                  <el-select v-model="searchForm.complaintClassification" placeholder="请选择分类">
                    <el-option label="产品投诉" value="产品投诉"></el-option>
                    <el-option label="服务投诉" value="服务投诉"></el-option>
                    <el-option label="客户意见" value="客户意见"></el-option>
                    <el-option label="其他" value="其他"></el-option>
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

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="40"></el-table-column>
        <el-table-column prop="complaintZt" label="投诉主题" sortable></el-table-column>
        <el-table-column prop="complaintClassification" label="分类"></el-table-column>
        <el-table-column prop="customerResp.cusName" label="客户"></el-table-column>
        <el-table-column prop="complaintData" label="时间">
          <template slot-scope="scope">
            {{scope.row.complaintData | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="complaintUrgent" label="紧急程度"></el-table-column>
        <el-table-column prop="empResp.empName" label="接待人"></el-table-column>
        <el-table-column prop="complaintHandlegc" label="处理结果"></el-table-column>
        <el-table-column prop="complaintComplainants" label="投诉人"></el-table-column>
      </el-table>

      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog
      title="投诉"
      :visible.sync="addDialog"
      width="50%"
      @close="handleClose">
      <el-form ref="addform" :model="addform" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉主题" prop="complaintZt">
              <el-input v-model="addform.complaintZt"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="8">
            <el-form-item label="接待人" prop="complaintReceptionist">
              <el-select v-model="addform.empId">
                <el-option v-for="item in edpList" :key="item.empId"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="客户">
              <el-select v-model="addform.cusId">
                <el-option v-for="item in empList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="日期">
              <el-date-picker type="date" placeholder="选择日期" v-model="addform.complaintData" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="分类">
              <el-select v-model="addform.complaintClassification" placeholder="请选择分类">
                <el-option label="产品投诉" value="产品投诉"></el-option>
                <el-option label="服务投诉" value="服务投诉"></el-option>
                <el-option label="客户意见" value="客户意见"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="描述">
              <el-input type="textarea" v-model="addform.complaintDescribe"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="处理结果">
              <el-select v-model="addform.complaintHandlegc" placeholder="请选择">
                <el-option label="未处理" value="未处理"></el-option>
                <el-option label="处理中" value="处理中"></el-option>
                <el-option label="处理完成" value="处理完成"></el-option>

              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="紧急程度">
              <el-select v-model="addform.complaintUrgent" placeholder="请选择">
                <el-option label="非常急" value="非常急"></el-option>
                <el-option label="急" value="急"></el-option>
                <el-option label="普通" value="普通"></el-option>

              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉人">
              <el-input v-model="addform.complaintComplainants"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理过程">
              <el-input v-model="addform.complaintHandle"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>

            <el-col :span="8">
              <el-form-item label="客户反馈">
                <el-input type="textarea" v-model="addform.complaintCustomerfk"></el-input>
              </el-form-item>
            </el-col>

        </el-row>
      </el-form>
      <div style="text-align: center;">
        <el-button @click="addDialog = false">取 消</el-button>
        <el-button type="primary" @click="addClick"
                   :loading="addDictButtonLoading">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改投诉"
      :visible.sync="editDialog"
      width="50%"
      @close="editHandleClose">
      <el-form ref="updateform" :model="updateform" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉主题" prop="complaintZt">
              <el-input v-model="updateform.complaintZt"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="8">
            <el-form-item label="接待人">
              <el-select v-model="updateform.empId">
                <el-option v-for="item in edpList" :key="item.empId"
                           :label="item.empName" :value="item.empId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="客户">
              <el-input
                placeholder="请输入内容"
                v-model="updateform.cusName"
                :disabled="true">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="日期">
              <el-date-picker type="date" placeholder="选择日期" v-model="updateform.complaintData" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="分类">
              <el-select v-model="updateform.complaintClassification" placeholder="请选择分类">
                <el-option label="产品投诉" value="产品投诉"></el-option>
                <el-option label="服务投诉" value="服务投诉"></el-option>
                <el-option label="客户意见" value="客户意见"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
          <el-form-item label="处理过程">
            <el-input type="textarea" v-model="updateform.complaintHandle"></el-input>
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="处理结果">
              <el-select v-model="updateform.complaintHandlegc" placeholder="请选择">
                <el-option label="未处理" value="未处理"></el-option>
                <el-option label="处理中" value="处理中"></el-option>
                <el-option label="处理完成" value="处理完成"></el-option>

              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="紧急程度">
              <el-select v-model="updateform.complaintUrgent" placeholder="请选择">
                <el-option label="非常急" value="非常急"></el-option>
                <el-option label="急" value="急"></el-option>
                <el-option label="普通" value="普通"></el-option>

              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="投诉人">
              <el-input v-model="updateform.complaintComplainants"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="描述">
              <el-input type="textarea" v-model="updateform.complaintDescribe"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div style="text-align: center;">
        <el-button @click="editDialog = false">取 消</el-button>
        <el-button type="warning" @click="editCareClick" :loading="editDictButtonLoading">确 定</el-button>
      </div>
    </el-dialog>
  </div>


</template>
<script>
  import {complaintHttp} from "../../network/system/complaint";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {userHttp} from "../../network/system/user";


  export default {
    data() {
      return {
        empList:[],
        edpList:[],
        rowCareId:0,
        addform:{
          complaintZt:'',
          cusId: '',
          empId:'',
          date1:'',
          complaintReceptionist:'',
          complaintClassification:'',
          complaintDescribe:'',
          complaintData:'',
          complaintComplainants: '',
          complaintUrgent: '',
          complaintHandle: '',
          complaintHandlegc:'',
          complaintCustomerfk:'',
          date:'',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },

        searchForm:{
          complaintZt:'',
          cusId: '',
          empId:'',
          date1:'',
          complaintReceptionist:'',
          complaintClassification:'',
          complaintDescribe:'',
          complaintData:'',
          complaintComplainants: '',
          complaintUrgent: '',
          complaintHandle: '',
          complaintHandlegc:'',
          complaintCustomerfk:'',
          startDate:'',
          endDate:'',
          delivery: false,
          type: [],
          date:'',
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
        complaintZt:'',
        date1:'',
        complaintData:'',
        date:'',
        listForm:[],
        tableLoading:'',
        total:0,
        pageNum:1,
        pageSize:3,
        value: '',
        input: '',

        tableData: [],
        form: {
          complaintZt:'',
          cusId: '',
          empId:'',
          complaintReceptionist:'',
          complaintClassification:'',
          complaintDescribe:'',
          complaintData:'',
          complaintComplainants: '',
          complaintUrgent: '',
          complaintHandle: '',
          complaintHandlegc:'',
          complaintCustomerfk:'',
          date:'',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        num: 1,
        rules:{
          complaintZt:[
            {required: true, message: '请输入活动名称', trigger: 'blur'},

          ]

        }
      }
    },
    methods:{
      openAddDialog() {
        this.addDialog = true
        this.initEmpList()
        this.initEdpList()
      },
      initEdpList(){
        userHttp.list().then(res =>{
          this.edpList = res.data.list
        })
      },
      initEmpList() {
        customerHttp.listAll().then(res => {
          this.empList = res.data
        })
      },
      // //打开新增的窗口
      // openAddDialog(){
      //   this.addDialog=true;//显示新增的对话框
      //   //重置新增的表单中的内容
      //   this.addform={};
      // },
      addClick(){
        console.log(this.$refs)
        this.$refs["addform"].validate(valid => {
          if (!valid) return
          this.addDictButtonLoading = true
          complaintHttp.add(this.addform).then(res => {
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
        complaintHttp.queryEmp(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
      editCareClick(){
        this.editDictButtonLoading = true
        this.updateform.complaintId = this.rowCareId
        complaintHttp.update(this.updateform).then(res => {
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
        complaintHttp.get(this.rowCareId).then(res =>{
          console.log("编辑获得的数据",res.data);
          this.updateform = res.data;
          this.updateform.cusName = res.data.customerResp.cusName
          this.updateform.empName = res.data.empResp.empName
        })
      },
      deleteCare() {
        this.$confirm('此操作将永久删除，是否继续','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          complaintHttp.del(this.rowCareId).then(res => {
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
        this.listForm.complaintZt = this.searchInput
        complaintHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },

      handleClose(){
        this.$refs["addform"].resetFields()
      },
      handleRowClick(row,event,column) {
        this.rowCareId = row.complaintId
        if (this.complaintId != 0) {
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
        complaintHttp.queryEmp(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      initList() {
        complaintHttp.listPage(this.pageNum, this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
    },

    created() {
      this.initList()
      this.initEmpList()
      this.initEdpList()
    }
  }
</script>

<style>

</style>
