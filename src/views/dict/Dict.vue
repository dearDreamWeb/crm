<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" placeholder="请输入字典名称查询" size="mini" class="input-with-select">
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button type="primary" icon="el-icon-plus" size="mini"
                     @click="openAddDialog">添加字典</el-button>
        </el-col>
      </el-row>
      <tree-table class="treeTable" :data="treeList" :columns="columns"
                  :selection-type="false" :expand-type="false" style="margin-top: 10px"
                  show-index index-text="#" border :show-row-hover="false">
        <template slot="createTime" slot-scope="scope">
          {{scope.row.createTime | dateFormat}}
        </template>
        <template slot="opt" slot-scope="scope">
          <el-tooltip :enterable="false" effect="dark" content="编辑" placement="top">
            <el-button type="success" icon="el-icon-edit" size="mini"
                       @click="editBtn(scope.row.dictId)"></el-button>
          </el-tooltip>
          <el-tooltip :enterable="false" effect="dark" content="删除" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini"
                       @click="delBtn(scope.row.dictId)" v-loading.fullscreen.lock="fullscreenLoading"></el-button>
          </el-tooltip>
        </template>
      </tree-table>
    </el-card>

    <el-dialog title="字典添加" :visible.sync="addDialog" @close="addHandleClose" width="30%">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef"
               label-width="100px" label-position="right">
        <el-form-item label="字典名称" prop="dictName">
          <el-input v-model="addForm.dictName" size="mini" placeholder="请输入字典名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="字典标识" prop="dictCode">
          <el-input v-model="addForm.dictCode" size="mini" placeholder="请输入字典编码" clearable></el-input>
        </el-form-item>
        <el-form-item label="父级菜单">
          <el-select v-model="addForm.pid" placeholder="请选择父级菜单" clearable size="mini">
            <el-option v-for="item in dictOption" :key="item.dictId"
                       :label="item.dictName" :value="item.dictId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addClick"
                   :loading="addDictButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="字典编辑" :visible.sync="editDialog" @close="editHandleClose">
      <el-form :model="editForm" label-width="70px" ref="editFormRef">
        <el-form-item label="字典名称" prop="dictName">
          <el-input v-model="editForm.dictName" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="字典编码" prop="dictCode">
          <el-input v-model="editForm.dictCode" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="父级编号" prop="pid">
          <el-select v-model="editForm.pid" size="mini">
            <el-option v-for="item in dictOption" :key="item.dictId"
                       :label="item.dictName" :value="item.dictId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editClick"
                   :loading="editDictButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {dictHttp} from "../../network/system/dict";

  export default {
    name: "Dict",
    data() {
      return {
        searchForm: {
          dictName:''
        },
        searchInput:'',
        fullscreenLoading:false,
        editForm:{
          dictId:'',
          dictName:'',
          dictCode:'',
          pid:''
        },
        editDictButtonLoading:false,
        editDialog:false,
        addDictButtonLoading:false,
        dictOption:[],
        addFormRules: {
          dictName:[
            {required:true,message:'请输入字典名称',trigger:'blur'}
          ],
          dictCode:[
            {required:true,message:'请输入字典编码',trigger:'blur'}
          ]
        },
        addDialog:false,
        addForm:{
          dictName:'',
          dictCode:'',
          pid:''
        },
        treeList:[],
        columns: [
          {label: '字典名称',prop: 'dictName'},
          {label: '字典编号',prop: 'dictCode'},
          {label: '父级编号',prop: 'pid'},
          {label: '创建时间',prop: 'createTime',type: 'template',template: 'createTime'},
          {label: '操作',type: 'template',template: 'opt'}
        ],
        defaultProps: {
          checkStrictly:true,
          expandTrigger:'hover',
          children:'children',
          label:'menuName',
          value:'menuId'
        },
      }
    },
    methods:{
      openAddDialog() {
        this.addDialog = true
        this.initDict()
      },
      searchInputClick() {
        this.searchForm.dictName = this.searchInput
        dictHttp.list(this.searchForm).then(res => {
          this.treeList = res.data
        })
      },
      delBtn(dictId) {
        this.$confirm('此操作将永久删除，是否继续','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.fullscreenLoading = true
          dictHttp.del(dictId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.fullscreenLoading = false
            } else {
              this.$message.error(res.message)
              this.fullscreenLoading = false
            }
          })
        })
      },
      editClick() {
        this.editDictButtonLoading = true
        dictHttp.edit(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editDictButtonLoading = false
            this.editDialog = false
          } else {
            this.$message.error(res.message)
            this.editDictButtonLoading = false
          }
        })
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
      },
      editBtn(dictId) {
        this.editDialog = true
        this.editForm.dictId = dictId
        dictHttp.get(dictId).then(res => {
          this.editForm = res.data
        })
      },
      addClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addDictButtonLoading = true
          dictHttp.add(this.addForm).then(res => {
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
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addForm.pid = ''
      },
      initList() {
        dictHttp.tree().then(res => {
          this.treeList = res.data
        })
      },
      initDict() {
        dictHttp.getPidList().then(res => {
          this.dictOption = res.data
        })
      }
    },
    created() {
      this.initList()
      this.initDict()
    }
  }
</script>

<style scoped>

</style>
