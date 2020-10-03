<template>
  <div>
    <el-card>
      <tree-table class="treeTable" :data="treeList" :columns="columns"
                  :selection-type="false" :expand-type="false"
                  show-index index-text="#" border :show-row-hover="false">
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
            <el-button type="success" icon="el-icon-edit" size="mini"></el-button>
          </el-tooltip>
          <el-tooltip :enterable="false" effect="dark" content="删除菜单" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
          </el-tooltip>
        </template>
      </tree-table>
    </el-card>
  </div>
</template>

<script>
  import {menuHttp} from "../../network/system/menu";

  export default {
    name: "Menu",
    data() {
      return {
        treeList:[],
        columns: [
          {label: '菜单名称',prop: 'menuName'},
          {label: '路由匹配',prop: 'url'},
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
