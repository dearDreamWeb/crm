<template>
  <div>
    <el-card>
      <span>
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   @click="addDialog = true">添加字典</el-button>
      </span>
      <tree-table class="treeTable" :data="treeList" :columns="columns"
                  :selection-type="false" :expand-type="false" style="margin-top: 10px"
                  show-index index-text="#" border :show-row-hover="false">
        <template slot="opt" slot-scope="scope">
          <el-tooltip :enterable="false" effect="dark" content="编辑" placement="top">
            <el-button type="success" icon="el-icon-edit" size="mini"
                       @click="editBtn(scope.row.dictId)"></el-button>
          </el-tooltip>
          <el-tooltip :enterable="false" effect="dark" content="删除" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini"
                       @click="delBtn(scope.row.dictId)"></el-button>
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
  </div>
</template>

<script>
  import {dictHttp} from "../../network/system/dict";

  export default {
    name: "Dict",
    data() {
      return {
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
