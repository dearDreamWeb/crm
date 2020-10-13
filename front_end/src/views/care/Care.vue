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
          <el-input v-model="searchInput" size="mini" placeholder="请输入内容" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">新增</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm"></el-button>
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
        <el-table-column prop="careData" label="时间">
          <template slot-scope="scope">
            {{scope.row.createTime | dateFormat}}
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
  </div>


</template>
<script>
  import {careHttp} from "../../network/system/care";

  export default {
    data() {
      return {
        buttonDisabled:true,
        searchInput:'',
        careZt:'',
        carelxrcontacts:'',
        careData:'',
        careexecutor:'',
        listForm:[],
        tableLoading:'',
        total:0,
        pageNum:1,
        pageSize:10,
        // value: '',
        // input: '',
        // dialogVisible: false,
        // tableData: [],
        // form: {
        //   careZt:'',
        //   cusId: '',
        //   carelxrcontacts:'',
        //   careexecutor:'',
        //   carecustomerfk:'',
        //   carenr:'',
        //   xlh:'',
        //   wxr:'',
        //   gzms:'',
        //   hf:'',
        //   zb:'',
        //   region: '',
        //   date1: '',
        //   careData: '',
        //   delivery: false,
        //   type: [],
        //   resource: '',
        //   desc: ''
        // },
        // num: 1,
        // rules:{
        //   ghzt:[
        //     {required: true, message: '请输入活动名称', trigger: 'blur'},
        //     { min: 5, max: 8, message: '长度在 5 到 8个字符', trigger: 'blur' }
        //   ],
        //   date2:[
        //     {type: 'date', required: true, message: '请选择日期', trigger: 'change' }
        //   ]
        // }
      }
    },
    methods:{
    //   addBtn(){
    //     this.dialogVisible = true;
    //     console.log(123)
    //   },
    //   handleClose(done) {
    //     this.$confirm('确认关闭？')
    //       .then(_ => {
    //         done();
    //       })
    //       .catch(_ => {});
    //   },
    //   onSubmit() {
    //     console.log('submit!');
    //   },
    //
    //   delBtn(careId) {
    //     this.$confirm('此操作将永久删除，是否继续','提示',{
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     }).then(() => {
    //       this.fullscreenLoading = true
    //       careHttp.del(careId).then(res => {
    //         if (res.code === 20000) {
    //           this.$message.success(res.message)
    //           this.initList()
    //           this.fullscreenLoading = false
    //         } else {
    //           this.$message.error(res.message)
    //           this.fullscreenLoading = false
    //         }
    //       })
    //     })
    //   },
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
    //   editHandleClose() {
    //     this.$refs.editFormRef.resetFields()
    //   },
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
    //   addHandleClose() {
    //     this.$refs.addFormRef.resetFields()
    //   },
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

      },
      openAddDialog(){

      },
      resetForm(){

      },
      openEditCare(){

      },
      deleteCare(){

      },
      handleRowClick(){

      },
      iHeaderCellStyle(){

      },
      iHeaderRowStyle(){

      },
      handleCurrentChange(){

      },
      initList(){
        careHttp.listPage(this.pageNum,this.pageSize).then(res =>{
          this.listForm = res.data.list
          this.total = res.date.total
          this.pageNum = res.date.pageNum
        })
      }

    },
    created() {
      this.initList()

    }
  }
</script>

<style>
  /*.el-row {*/
  /*  margin-bottom: 20px;*/
  /*}*/
  /*:last-child {*/
  /*  margin-bottom: 0;*/
  /*}*/

  /*.el-col {*/
  /*  border-radius: 4px;*/
  /*}*/
  /*.bg-purple-dark {*/
  /*  background: white;*/
  /*}*/
  /*.bg-purple {*/
  /*  background: white;*/
  /*}*/
  /*.bg-purple-light {*/
  /*  background: white;*/
  /*}*/
  /*.grid-content {*/
  /*  border-radius: 4px;*/
  /*  min-height: 10px;*/
  /*}*/
  /*.row-bg {*/
  /*  padding: 10px 0;*/
  /*  background-color: #f9fafc;*/
  /*}*/
</style>
