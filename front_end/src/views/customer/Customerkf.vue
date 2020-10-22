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
                <el-form-item prop="cusId" label="对应客户">
                  <el-input v-model="searchForm.cusId" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="customerTheme" label="主题">
                  <el-input v-model="searchForm.customerTheme" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="customerExecutor" label="执行人">
                  <el-input v-model="searchForm.customerExecutor" size="mini" placeholder="请输入" clearable></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6">
                <el-form-item prop="customerServicefs" label="服务方式">
                  <el-select v-model="searchForm.customerServicefs" placeholder="请选择">
                    <el-option label="电话" value="电话"></el-option>
                    <el-option label="上门" value="上门"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item prop="customerState" label="处理状态">
                  <el-select v-model="searchForm.customerState" placeholder="请选择">
                    <el-option label="无需处理" value="无需处理"></el-option>
                    <el-option label="未处理" value="未处理"></el-option>
                    <el-option label="处理中" value="处理中"></el-option>
                    <el-option label="处理完成" value="处理完成"></el-option>
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
        <el-table-column prop="customerTheme" label="主题" sortable></el-table-column>
        <el-table-column prop="cusId" label="对应客户"></el-table-column>
        <el-table-column prop="customerServicelx" label="服务类型"></el-table-column>
        <el-table-column prop="customerData" label="日期">
          <template slot-scope="scope">
            {{scope.row.customerData | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="customerServicefs" label="服务方式"></el-table-column>
        <el-table-column prop="customerExecutor" label="执行人"></el-table-column>
        <el-table-column prop="customerState" label="处理状态"></el-table-column>
      </el-table>

      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog
      title="客服"
      :visible.sync="addDialog"
      width="50%"
      @close="handleClose">
      <el-form ref="addform" :model="addform" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="主题" prop="customerTheme">
              <el-input v-model="addform.customerTheme"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="8">
            <el-form-item label="执行人" prop="customerExecutor">
              <el-input v-model="addform.customerExecutor"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="客户">
              <el-input
                placeholder="请输入内容"
                v-model="input"
                :disabled="true">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="日期">
              <el-date-picker type="date" placeholder="选择日期" v-model="addform.customerData" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="服务类型">
              <el-select v-model="addform.customerServicelx" placeholder="请选择">
                <el-option label="答疑" value="答疑"></el-option>
                <el-option label="故障排除" value="故障排除"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务方式">
              <el-select v-model="addform.customerServicefs" placeholder="请选择">
                <el-option label="电话" value="电话"></el-option>
                <el-option label="上门" value="上门"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="处理状态">
              <el-select v-model="addform.customerState" placeholder="请选择">
                <el-option label="无需处理" value="无需处理"></el-option>
                <el-option label="未处理" value="未处理"></el-option>
                <el-option label="处理中" value="处理中"></el-option>
                <el-option label="处理完成" value="处理完成"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人">
              <el-input v-model="addform.customerKhcontacts"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="服务内容">
              <el-input v-model="addform.customerServicenr"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户反馈">
              <el-input type="textarea" v-model="addform.customerCustomerfk"></el-input>
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
            <el-form-item label="主题" prop="customerTheme">
              <el-input v-model="updateform.customerTheme"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="8">
            <el-form-item label="执行人" prop="customerExecutor">
              <el-input v-model="updateform.customerExecutor"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="客户">
              <el-input
                placeholder="请输入内容"
                v-model="input"
                :disabled="true">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="日期">
              <el-date-picker type="date" placeholder="选择日期" v-model="updateform.customerData" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="服务类型">
              <el-select v-model="updateform.customerServicelx" placeholder="请选择">
                <el-option label="答疑" value="答疑"></el-option>
                <el-option label="故障排除" value="故障排除"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="服务方式">
              <el-select v-model="updateform.customerServicefs" placeholder="请选择">
                <el-option label="电话" value="电话"></el-option>
                <el-option label="上门" value="上门"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="处理状态">
              <el-select v-model="updateform.customerState" placeholder="请选择">
                <el-option label="无需处理" value="无需处理"></el-option>
                <el-option label="未处理" value="未处理"></el-option>
                <el-option label="处理中" value="处理中"></el-option>
                <el-option label="处理完成" value="处理完成"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系人">
              <el-input v-model="updateform.customerKhcontacts"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="服务内容">
              <el-input v-model="updateform.customerServicenr"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户反馈">
              <el-input type="textarea" v-model="updateform.customerCustomerfk"></el-input>
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
  import {customerkfHttp} from "../../network/system/customerkf";


  export default {
    data() {
      return {
        rowCareId:0,
        addform:{
          customerTheme:'',
          cusId: '',
          date1:'',
          customerServicelx:'',
          customerServicefs:'',
          customerData:'',
          customerKhcontacts:'',
          customerState: '',
          customerExecutor: '',
          customerServicenr: '',
          customerCustomerfk:'',
          date:'',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },

        searchForm:{
          customerTheme:'',
          cusId: '',
          date1:'',
          customerServicelx:'',
          customerServicefs:'',
          customerData:'',
          customerKhcontacts:'',
          customerState: '',
          customerExecutor: '',
          customerServicenr: '',
          customerCustomerfk:'',
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
        date1:'',
        customerData:'',
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
          customerTheme:'',
          cusId: '',
          date1:'',
          customerServicelx:'',
          customerServicefs:'',
          customerData:'',
          customerKhcontacts:'',
          customerState: '',
          customerExecutor: '',
          customerServicenr: '',
          customerCustomerfk:'',
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
      addClick(){
        console.log(this.$refs)
        this.$refs["addform"].validate(valid => {
          if (!valid) return
          this.addDictButtonLoading = true
          customerkfHttp.add(this.addform).then(res => {
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
        customerkfHttp.queryEmp(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
      editCareClick(){
        this.editDictButtonLoading = true
        this.updateform.customerId = this.rowCareId
        customerkfHttp.update(this.updateform).then(res => {
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
        customerkfHttp.get(this.rowCareId).then(res =>{
          console.log("编辑获得的数据",res.data);
          this.updateform = res.data;
        })
      },
      deleteCare() {
        this.$confirm('此操作将永久删除，是否继续','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          customerkfHttp.del(this.rowCareId).then(res => {
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
        this.listForm.customerTheme = this.searchInput
        customerkfHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },


      handleClose(){
        this.$refs["addform"].resetFields()
      },
      handleRowClick(row,event,column) {
        this.rowCareId = row.customerId
        if (this.customerId != 0) {
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
        customerkfHttp.queryEmp(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      initList() {
        customerkfHttp.listPage(this.pageNum, this.pageSize).then(res => {
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

    }
  }
</script>

<style>

</style>
