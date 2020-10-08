<template>
  <div>
    <el-card>
      <span>
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   @click="addDialog = true">添加菜单</el-button>
      </span>
      <tree-table class="treeTable" :data="treeList" :columns="columns"
                  :selection-type="false" :expand-type="false" style="margin-top: 10px"
                  show-index index-text="#" border :show-row-hover="false">
        <template slot="iconTemp" slot-scope="scope">
          <i :class="scope.row.icon"></i>
        </template>
        <template slot="isShowTemp" slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.isShow === 1">显示</el-tag>
          <el-tag size="mini" type="danger" v-else>不显</el-tag>
        </template>
        <template slot="menuType" slot-scope="scope">
          <el-tag size="mini" v-if="scope.row.menuType === 1">目录</el-tag>
          <el-tag type="success" size="mini" v-if="scope.row.menuType === 2">菜单</el-tag>
          <el-tag type="warning" size="mini" v-if="scope.row.menuType === 3">按钮</el-tag>
        </template>
        <template slot="opt" slot-scope="scope">
          <el-tooltip :enterable="false" effect="dark" content="编辑菜单" placement="top">
            <el-button type="success" icon="el-icon-edit" size="mini"
                       @click="editBtn(scope.row.menuId)"></el-button>
          </el-tooltip>
          <el-tooltip :enterable="false" effect="dark" content="删除菜单" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini"
                       @click="delBtn(scope.row.menuId)" v-loading.fullscreen.lock="fullscreenLoading"></el-button>
          </el-tooltip>
        </template>
      </tree-table>
    </el-card>

    <el-dialog title="菜单添加" :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" :rules="addFormRules"
               ref="addFormRef" label-width="100px"
               label-position="right">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="菜单名称" prop="menuName">
              <el-input size="mini" placeholder="请输入菜单名称" v-model="addForm.menuName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="匹配路由" prop="url">
              <el-input size="mini" placeholder="请输入匹配路由" v-model="addForm.url"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="权限标识" prop="perms">
              <el-input size="mini" placeholder="请输入权限标识" v-model="addForm.perms"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="导航显示">
              <el-radio-group v-model="addForm.isShow">
                <el-radio :label=1>显示</el-radio>
                <el-radio :label=0>不显示</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="图标" prop="icon">
              <e-icon-picker v-model="addForm.icon"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="父级菜单">
              <el-cascader v-model="addForm.pidList" :options="treeList"
                           @change="addHandleChange" clearable :props="defaultProps">
              </el-cascader>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addClick" :loading="addMenuButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="菜单编辑" :visible.sync="editDialog" @close="editHandleClose">
      <el-form :model="editForm" label-width="70px" ref="editFormRef">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="菜单名称" prop="menuName">
              <el-input size="mini" v-model="editForm.menuName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="匹配路由" prop="url">
              <el-input size="mini" v-model="editForm.url"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="权限标识" prop="perms">
              <el-input size="mini" v-model="editForm.perms"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="导航显示" prop="isShow">
              <el-radio-group v-model="editForm.isShow">
                <el-radio :label=1>显示</el-radio>
                <el-radio :label=0>不显示</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="图标" prop="icon">
              <e-icon-picker v-model="editForm.icon"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="父级菜单" prop="pidList">
              <el-cascader v-model="editForm.pidList" :options="treeList"
                           :props="defaultProps" @change="editHandleChange"
                           clearable>
              </el-cascader>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editClick" :loading="editMenuButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {menuHttp} from "../../network/system/menu";

  export default {
    name: "Menu",
    data() {
      return {
        fullscreenLoading:false,
        addMenuButtonLoading:false,
        editMenuButtonLoading:false,
        editForm:{
          menuId:'',
          menuName:'',
          pid:'',
          pidList:[],
          icon:'',
          type:'',
          perms:'',
          url:'',
          isShow:''
        },
        editDialog:false,
        addDialog:false,
        addForm:{
          menuName:'',
          type:'',
          url:'',
          icon:'',
          perms:'',
          isShow:1,
          pid:'',
          pidList:[]
        },
        addFormRules:{
          menuName:[
            {required:true,message:'请输入菜单名称',trigger:'blur'}
          ]
        },
        treeList:[],
        columns: [
          {label: '菜单名称',prop: 'menuName'},
          {label: '路由匹配',prop: 'url'},
          {label: '图标',prop: 'icon',type: 'template',template: 'iconTemp',width: '50px'},
          {label: '权限标识',prop: 'perms'},
          {label: '导航显示',prop: 'isShow',type: 'template',template: 'isShowTemp'},
          {label: '分类等级',prop: 'type',type: 'template',template: 'menuType'},
          {label: '操作',type: 'template',template: 'opt'}
        ],
        defaultProps: {
          checkStrictly:true,
          expandTrigger:'hover',
          children:'children',
          label:'menuName',
          value:'menuId'
        },
        menuIdList:[],
      }
    },
    methods: {
      delBtn(menuId) {
        this.$confirm('此操作将永久删除，是否继续','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.fullscreenLoading = true
          menuHttp.del(menuId).then(res => {
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
        this.editMenuButtonLoading = true
        menuHttp.edit(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editMenuButtonLoading = false
          } else {
            this.$message.error(res.message)
            this.editMenuButtonLoading = false
          }
          this.editDialog = false
        })
      },
      editBtn(menuId) {
        this.editDialog = true
        this.editForm.menuId = menuId
        menuHttp.get(menuId).then(res => {
          this.editForm = res.data
        })
      },
      addClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addMenuButtonLoading = true
          menuHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.addMenuButtonLoading = false
            } else {
              this.$message.error(res.message)
              this.addMenuButtonLoading = false
            }
            this.addDialog = false
          })
        })
      },
      editHandleChange() {
        const len = this.editForm.pidList.length
        if (len > 0) {
          this.editForm.pid = this.editForm.pidList[len - 1]
          this.editForm.type = len + 1
        } else {
          this.editForm.pid = 0
          this.editForm.pidList = [0]
          this.editForm.type = 1
        }
      },
      addHandleChange() {
        const len = this.addForm.pidList.length
        if (len > 0) {
          this.addForm.pid = this.addForm.pidList[len-1]
          this.addForm.type = len + 1
        } else {
          this.addForm.pid = 0
          this.addForm.pidList = [0]
          this.addForm.type = 1
        }
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editForm.pid = 0
        this.editForm.pidList = []
        this.editForm.type = 1
      },
      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addForm.pid = 0
        this.addForm.pidList = []
        this.addForm.type = 1
        this.addForm.isShow = 0
      },
      initList() {
        menuHttp.tree().then(res => {
          this.treeList = res.data
        })
      }
    },
    created() {
      this.initList()
    }
  }
</script>

<style scoped>

</style>
