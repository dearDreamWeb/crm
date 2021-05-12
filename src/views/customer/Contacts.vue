<!--
@author: pengjia
@date: 2020/11/10
@description:
-->
<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <!-- <el-col :span="6">
          <el-row :gutter="10">
            <el-col :span="20">
              <el-input v-model="filterText" size="mini" clearable placeholder="请输入"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button size="mini" icon="el-icon-search" type="primary"
                         :disabled="treeQueryDisabled" @click="treeQueryClick"></el-button>
            </el-col>
          </el-row>
          <div style="margin-top: 20px;height: 450px;overflow: auto">
            <el-tree :data="customerTreeData" :props="defaultProps" ref="tree"
                     @node-click="handleNodeClick" :filter-node-method="filterNode"></el-tree>
          </div>
        </el-col> -->
        <!-- <el-col :span="1">
          <el-divider direction="vertical"></el-divider>
        </el-col> -->
        <el-col :span="17">
          <el-row :gutter="20">
            <el-col :span="7">
              <el-input v-model="searchInput" size="mini" clearable placeholder="请输入">
                <el-button @click="searchInputClick" icon="el-icon-search" slot="append"></el-button>
              </el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" icon="el-icon-plus" size="mini"
                         @click="openAddDialog">新增</el-button>
              <el-button type="primary" icon="el-icon-zoom-in" size="mini"
                         @click="advancedSearch = !advancedSearch">高查</el-button>
              <el-button type="primary" icon="el-icon-refresh" size="mini"></el-button>
            </el-col>
            <el-col :span="9">
              <el-button type="warning" size="mini" icon="el-icon-edit"
                         :disabled="buttonDisabled">修改</el-button>
              <el-button type="danger" size="mini" icon="el-icon-delete"
                         :disabled="buttonDisabled">删除</el-button>
              <el-popover placement="bottom" trigger="click" width="500">
                <el-checkbox-group v-model="colOptions">
                  <el-checkbox v-for="item in colSelect" :label="item" :key="item"></el-checkbox>
                </el-checkbox-group>
                <el-button type="primary" size="mini" slot="reference" icon="el-icon-menu"></el-button>
              </el-popover>
            </el-col>
          </el-row>

          <transition name="el-zoom-in:top">
            <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
              <el-form :model="searchForm" ref="searchFormRef" size="mini"
                       label-position="right" label-width="50px">
                <el-row :gutter="20">
                  <el-col :span="18">
                    <el-form-item label-width="80px" label="高级查询"></el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-button icon="el-icon-zoom-out" size="mini"
                               @click="closeAdvancedSearch"></el-button>
                    <el-button size="mini" @click="advancedSearchClick" type="primary"
                               icon="el-icon-search"></el-button>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="8">
                    <el-form-item label="手机" prop="contactsPhone">
                      <el-input v-model="searchForm.contactsPhone" clearable placeholder="请输入"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="性别" prop="sex">
                      <el-select v-model="searchForm.sex" clearable placeholder="请选择">
                        <el-option label="男" value="1"></el-option>
                        <el-option label="女" value="0"></el-option>
                        <el-option label="未知" value="2"></el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="微信" prop="wechat">
                      <el-input v-model="searchForm.wechat" clearable placeholder="请输入"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="8">
                    <el-form-item label="QQ" prop="qq">
                      <el-input v-model="searchForm.qq" clearable placeholder="请输入"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="邮箱" prop="email">
                      <el-input v-model="searchForm.email" clearable placeholder="请输入"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="类型" prop="contactsDictType">
                      <el-select v-model="searchForm.contactsDictType"
                                 clearable placeholder="请输入">
                        <el-option v-for="item in contactsDictTypeList.children" :key="item.dictId"
                                   :label="item.dictName" :value="item.dictId"></el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </el-card>
          </transition>

          <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                    :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                    highlight-current-row @row-click="handleRowClick" v-loading="tableLoading"
                    ref="tableDataRef">
            <el-table-column type="index" width="50px"></el-table-column>
            <el-table-column v-if="colData[0].isTrue" prop="contactsName" label="名称"></el-table-column>
            <el-table-column v-if="colData[1].isTrue" prop="birthday" label="生日">
              <template slot-scope="scope">
                {{scope.row.birthday | dateFormat}}
              </template>
            </el-table-column>
            <el-table-column v-if="colData[2].isTrue" prop="sex" label="性别">
              <template slot-scope="scope">
                {{scope.row.sex | sexFormat}}
              </template>
            </el-table-column>
            <el-table-column v-if="colData[3].isTrue" prop="contactsPhone" label="手机号"></el-table-column>
            <el-table-column v-if="colData[4].isTrue" prop="wecaht" label="微信"></el-table-column>
            <el-table-column v-if="colData[5].isTrue" prop="qq" label="QQ"></el-table-column>
            <el-table-column v-if="colData[6].isTrue" prop="email" label="邮箱"></el-table-column>
            <el-table-column v-if="colData[7].isTrue" prop="contactsDictType" label="类型"></el-table-column>
            <el-table-column v-if="colData[8].isTrue" prop="cusName" label="所属客户">
              <template slot-scope="scope">
                {{ scope.row.customerResp.cusName }}
              </template>
            </el-table-column>
            <el-table-column v-if="colData[9].isTrue" prop="remark" label="备注"></el-table-column>
          </el-table>

          <el-pagination background :page-size="pageSize" :total="total"
                         :current-page="pageNum" :page-sizes="[1,2,5,10]"
                         @current-change="handleCurrentChange"
                         layout="prev, pager, next, jumper, total">
          </el-pagination>
        </el-col>
      </el-row>
    </el-card>

    <el-dialog title="联系人添加" :visible.sync="addDialog" @close="addDialogClose">
      <el-form :model="addForm" label-position="right" label-width="80px"
               :rules="addFormRules" ref="addFormRef">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="名称" prop="contactsName">
              <el-input v-model="addForm.contactsName" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生日" prop="birthday">
              <el-date-picker v-model="addForm.birthday" format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd" type="date"
                              placeholder="请输入"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="手机" prop="contactsPhone">
              <el-input v-model="addForm.contactsPhone" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-radio v-model="addForm.sex" :label=1>男</el-radio>
              <el-radio v-model="addForm.sex" :label=0>女</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="微信" prop="wechat">
              <el-input v-model="addForm.wecaht" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="QQ" prop="qq">
              <el-input v-model="addForm.qq" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="addForm.email" placeholder="请输入" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="contactsDictType">
              <el-select v-model="addForm.contactsDictType" clearable placeholder="请选择">
                  <el-option v-for="item in contactsDictTypeList.children" :key="item.dictId"
                    :label="item.dictName" :value="item.dictId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="所属客户" prop="cusId">
              <el-select v-model="addForm.cusId" clearable placeholder="请选择">
                <el-option></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input type="textarea" v-model="addForm.remark" clearable placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" :loading="addContactsButtonLoading"
                   @click="addContactsClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {dictHttp} from "../../network/system/dict";

  export default {
    name: "Contacts",
    data() {
      return {
        addDialog:false,
        addForm:{
          contactsName:'',
          birthday:'',
          sex:1,
          contactsPhone:'',
          wecaht:'',
          qq:'',
          email:'',
          contactsDictType:'',
          cusId:'',
          remark:''
        },
        addFormRules:{

        },
        addContactsButtonLoading:false,

        pageNum:1,
        pageSize:10,
        total:1,
        listForm:[],
        tableLoading:false,
        rowContactsId:0,

        filterText:'',
        customerTreeData:[],
        treeQueryDisabled:true,
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        treeContactsId:0,

        searchInput:'',
        advancedSearch:false,
        buttonDisabled:true,
        contactsDictTypeList:[],
        searchForm:{
          contactsName:'',
          contactsPhone:'',
          sex:'',
          wechat:'',
          qq:'',
          email:'',
          contactsDictType:'',
          cusId:''
        },

        colData:[
          {title:"名称",isTrue:true},
          {title:"生日",isTrue:false},
          {title:"性别",isTrue:false},
          {title:"手机号",isTrue:true},
          {title:"微信",isTrue:false},
          {title:"QQ",isTrue:false},
          {title:"邮箱",isTrue:true},
          {title:"类型",isTrue:true},
          {title:"所属客户",isTrue:true},
          {title:"备注",isTrue:false},
        ],
        colOptions:["名称","手机号","邮箱","类型","所属客户"],
        colSelect:["名称","生日","性别","手机号","微信","QQ","邮箱","类型","所属客户","备注"],
      }
    },
    watch: {
      filterText(val) {
        this.$refs.tree.filter(val);
      },
      colOptions(valArr){
        let arr = this.colSelect.filter(i => valArr.indexOf(i) < 0);
        this.colData.filter(i => {
          if(arr.indexOf(i.title) != -1){
            i.isTrue = false;
            this.$nextTick(() => {
              this.$refs.tableDataRef.doLayout()
            })
          }else{
            i.isTrue = true
            this.$nextTick(() => {
              this.$refs.tableDataRef.doLayout();
            })
          }
        })
      }
    },
    methods:{
      addContactsClick() {

      },
      addDialogClose() {

      },

      initContactsTypeList() {
        dictHttp.tree_dict_byId(1).then(res => {
          this.contactsDictTypeList = res.data
        })
      },

      handleRowClick(row,event,column) {
        this.rowContactsId = row.contactsId
        if (this.rowContactsId  != 0) {
          this.buttonDisabled = false
        }
      },
      initList() {
        this.tableLoading = true
        contactsHttp.listPage(this.pageNum,this.pageSize).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.pageNum = res.data.pageNum
            this.total = res.data.total
            this.tableLoading = false
          } else {
            this.$message.error(res.message)
          }
        })
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.tableLoading = true
        contactsHttp.listPage(this.pageNum,this.pageSize).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.pageNum = res.data.pageNum
            this.total = res.data.total
            this.tableLoading = false
          } else {
            this.$message.error(res.message)
          }
        })
      },

      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      handleNodeClick(data) {
        this.treeContactsId = data.id
        if (this.treeContactsId != 0) {
          this.treeQueryDisabled = false
        }
      },
      treeQueryClick() {

      },

      searchInputClick() {
        this.searchForm.contactsName = this.searchInput
        this.tableLoading = true
        contactsHttp.search(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.pageNum = res.data.pageNum
            this.total = res.data.total
            this.tableLoading  = false
          } else {
            this.$message.error(res.message)
          }
        })
        this.searchInput = ''
      },
      openAddDialog() {
        this.addDialog = true
      },
      closeAdvancedSearch() {
        this.advancedSearch = !this.advancedSearch
        this.$refs.searchFormRef.resetFields()
      },
      advancedSearchClick() {
        this.tableLoading = true
        contactsHttp.search(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.pageNum = res.data.pageNum
            this.total = res.data.total
            this.tableLoading = false
          } else {
            this.$message.error(res.message)
          }
        })
      },
      resetForm() {
        this.$refs.searchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowContactsId = 0
        this.buttonDisabled = true
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
      this.initContactsTypeList()
    }
  }
</script>

<style scoped>
  .el-divider--vertical{
    height: 2em !important;
  }
</style>
