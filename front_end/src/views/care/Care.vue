<template>
  <!--<div>
    <el-card>
      <el-row :gutter="20">

        <el-col :span="6">
          <el-input class="ssk2"
                                    placeholder="请输入关怀主题"
                                    v-model="input"
                                    clearable>
        </el-input>
          <div class="grid-content bg-purple-light"></div>
        </el-col>
        <el-button @click="careInput" type="primary" icon="el-icon-search">搜索</el-button>
      </el-row>
      <el-row>
        <el-col :span="7">客户关怀<div class="grid-content bg-purple"></div></el-col>
        <el-col :span="12"><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="2"><el-button type="text" @click="addBtn">新建</el-button><div class="grid-content bg-purple"></div></el-col>
        <el-dialog
          title="客户关怀"
          :visible.sync="dialogVisible"
          width="50%"
          :before-close="handleClose">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-row>
              <el-col :span="8">
                <el-form-item label="关怀主题">
                  <el-input v-model="form.careZt"></el-input>
                </el-form-item>
              </el-col >
              <el-col :span="8">
                <el-form-item label="联系人">
                  <el-input v-model="form.carelxrcontacts"></el-input>
                </el-form-item>
              </el-col>

            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="客户">
                  <el-input
                    placeholder="请输入内容"
                    v-model="cusId"
                    :disabled="true">
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item>
                  <el-input placeholder="请输入内容" v-model="input3" class="input-with-select">

                    <el-button slot="append" icon="el-icon-search"></el-button>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="日期">
                  <el-date-picker type="date" placeholder="选择日期" v-model="form.careData" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="执行人">
                  <el-input v-model="form.careexecutor"></el-input>
                </el-form-item>
              </el-col>

            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="关怀内容">
                  <el-input type="textarea" v-model="form.carenr"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="客户反馈">
                  <el-input type="textarea" v-model="form.carecustomerfk"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
            </el-row>
          </el-form>
          <div style="text-align: center;">

            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </div>

        </el-dialog>
        <el-button><i class="el-icon-s-grid"></i></el-button>
        <el-button><i class="el-icon-s-tools"></i></el-button>
      </el-row>
      <el-row>
        <el-col :span="0"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="20">
          <el-table
                ref="multipleTable"
                :data="tableData"
                tooltip-effect="dark"
                style="width: 100%">
          <el-table-column
                  type="selection"
                  width="55">
          </el-table-column>
          <el-table-column
                  prop="careId"
            label="ID"
            width="120">
            <template slot-scope="scope">{{ scope.row.date }}</template>
          </el-table-column>
          <el-table-column
            prop="careZt"
            label="关怀主题"
            width="120">
          </el-table-column>
          <el-table-column
            prop="carelxrcontacts"
            label="联系人"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="cusId"
            label="客户"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="careData"
            label="日期"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="careexecutor"
            label="执行人"
            show-overflow-tooltip>
          </el-table-column>
        </el-table><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="0"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="0"><div class="grid-content bg-purple-light"></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="1"><el-button @click="delBtn(scope.row.careId)" v-loading.fullscreen.lock="fullscreenLoading"><i class="el-icon-delete"></i></el-button><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="1"><el-button><i class="el-icon-edit"></i></el-button><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="0"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="0"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="4"><div class="block">

          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="40">
          </el-pagination>
        </div><div class="grid-content bg-purple-light"></div></el-col>
      </el-row>
    </el-card>
  </div>-->
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入主题内容查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="addDialog = true">新增</el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditCare">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="deleteCare">删除</el-button>
        </el-col>
      </el-row>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="20"></el-table-column>
        <el-table-column prop="careZt" label="关怀主题" sortable></el-table-column>
        <el-table-column prop="carelxrcontacts" label="联系人"></el-table-column>
        <el-table-column prop="cusId" label="客户"></el-table-column>
        <el-table-column prop="careData" label="时间">
          <template slot-scope="scope">
            {{scope.row.careData | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="careexecutor" label="执行人"></el-table-column>
      </el-table>

      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog
      title="新增关怀"
      :visible.sync="addDialog"
      width="50%"
      @close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="关怀主题" prop="careZt">
              <el-input v-model="form.careZt"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="8">
            <el-form-item label="联系人">
              <el-input v-model="form.carelxrcontacts"></el-input>
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
              <el-date-picker type="date" placeholder="选择日期" v-model="form.careData" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="执行人">
              <el-input v-model="form.careexecutor"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="关怀内容">
              <el-input type="textarea" v-model="form.carenr"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="客户反馈">
              <el-input type="textarea" v-model="form.carecustomerfk"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div style="text-align: center;">

        <el-button @click="addDialog = false">取 消</el-button>
        <el-button type="primary" @click="addClick" :loading="addDictButtonLoading">确 定</el-button>
      </div>

    </el-dialog>

    <el-dialog
      title="修改关怀"
      :visible.sync="editDialog"
      width="50%"
      @close="editHandleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="关怀主题" prop="careZt">
              <el-input v-model="form.careZt"></el-input>
            </el-form-item>
          </el-col >
          <el-col :span="8">
            <el-form-item label="联系人">
              <el-input v-model="form.carelxrcontacts"></el-input>
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
              <el-date-picker type="date" placeholder="选择日期" v-model="form.careData" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="执行人">
              <el-input v-model="form.careexecutor"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="关怀内容">
              <el-input type="textarea" v-model="form.carenr"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="客户反馈">
              <el-input type="textarea" v-model="form.carecustomerfk"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div style="text-align: center;">

        <el-button @click="editDialog = false">取 消</el-button>
        <el-button type="primary" @click="editCareClick" :loading="addDictButtonLoading">确 定</el-button>
      </div>

    </el-dialog>
  </div>


</template>
<script>
  import {careHttp} from "../../network/system/care";


  export default {
    data() {
      return {
        rowCareId:0,

        addDialog:false,
        buttonDisabled:true,
        addDictButtonLoading:false,
        editEmpButtonLoading:false,
        editDialog:false,
        searchInput:'',
        careZt:'',

        careData:'',

        listForm:[],
        tableLoading:'',
        total:0,
        pageNum:1,
        pageSize:3,
        value: '',
        input: '',

        tableData: [],
        form: {
          careZt:'',
          cusId: '',
          carelxrcontacts:'',
          careexecutor:'',
          carecustomerfk:'',
          carenr:'',
          region: '',
          date1: '',
          careData: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        num: 1,
        rules:{
          careZt:[
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            { min: 5, max: 8, message: '长度在 5 到 8个字符', trigger: 'blur' }
          ]

        }
      }
    },
    methods:{
      addClick(){
        this.$refs.form.validate(valid => {
          if (!valid) return
          this.addDictButtonLoading = true
          careHttp.add(this.form).then(res => {
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
      editCareClick(){
        this.editEmpButtonLoading = true
        this.form.careId = this.rowCareId
        careHttp.update(this.form).then(res => {
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
      openEditCare(){
        this.editDialog = true
        this.getEmpDetail()
      },
      getEmpDetail(){
        careHttp.get(this.rowCareId).then(res =>{
          this.form = res.data
        })
      },
      deleteCare() {
        this.$confirm('此操作将永久删除，是否继续','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          careHttp.del(this.rowCareId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()

            } else {
              this.$message.error(res.message)
            }
          })
        })
      },
    //   editClick() {
    //     this.editDictButtonLoading = true
    //     careHttp.edit(this.editForm).then(res => {
    //       if (res.code === 20000) {
    //         this.$message.success(res.message)
    //         this.initList()
    //         this.editDictButtonLoading = false
    //         this.editDialog = false
    //       } else {
    //         this.$message.error(res.message)
    //         this.editDictButtonLoading = false
    //       }
    //     })
    //   },
      editHandleClose() {
        this.$refs.form.resetFields()
        this.editEmpButtonLoading = false
      },
    //   editBtn(careId) {
    //     this.editDialog = true
    //     this.editForm.careId = careId
    //     careHttp.get(careId).then(res => {
    //       this.editForm = res.data
    //     })
    //   },
    //   addClick() {
    //     this.$refs.addFormRef.validate(valid => {
    //       if (!valid) return
    //       this.addDictButtonLoading = true
    //       careHttp.add(this.addForm).then(res => {
    //         if (res.code === 20000) {
    //           this.$message.success(res.message)
    //           this.initList()
    //           this.addDialog = false
    //           this.addDictButtonLoading = false
    //         } else {
    //           this.$message({
    //             message:res.message,
    //             type:"error"
    //           })
    //           this.addDictButtonLoading = false
    //         }
    //       })
    //     })
    //   },
      addHandleClose() {
        this.$refs.form.resetFields()
      },
    //   initList() {
    //     careHttp.list().then(res => {
    //       this.listForm = res.data
    //     })
    //   },
    //   initDict() {
    //     careHttp.getPidList().then(res => {
    //       this.dictOption = res.data
    //     })
    //   }
    // },
    // created() {
    //   this.initList()
    //   this.initDict()
      searchInputClick(){
        this.listForm.careZt = this.searchInput
        careHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },


      handleClose(){
        this.$refs.form.resetFields()
      },
      handleRowClick(row,event,column) {
        this.rowCareId = row.careId
        if (this.careId != 0) {
          this.buttonDisabled = false
        }
      },
      iHeaderCellStyle(){

      },
      iHeaderRowStyle(){

      },
      handleCurrentChange(pageIndex){
        this.pageNum = pageIndex
        this.pageSize = this.pageSize
        careHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      initList(){
        careHttp.listPage(this.pageNum,this.pageSize).then(res =>{
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      }

    },
    created() {
      this.initList()

    }
  }
</script>

<style>

</style>
