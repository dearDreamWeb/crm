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
        <el-table-column prop="complaintZt" label="主题" sortable></el-table-column>
        <el-table-column prop="complaintClassification" label="对应客户"></el-table-column>
        <el-table-column prop="customerResp.cusName" label="费用"></el-table-column>
        <el-table-column prop="complaintUrgent" label="状态"></el-table-column>
        <el-table-column prop="empResp.empName" label="承接部门"></el-table-column>
        <el-table-column prop="complaintHandlegc" label="接单人"></el-table-column>
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
  export default {
    data() {
      return {
        value: '',
        input: '',
        dialogVisible: false,
        tableData: [],
        form: {
          gdbh:'',
          khname: '',
          jdr:'',
          wtms:'',
          cpbh:'',
          ddbh:'',
          xlh:'',
          wxr:'',
          gzms:'',
          hf:'',
          zb:'',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        num: 1
      }
    },
    methods:{
      addBtn(){
        this.dialogVisible = true;
        console.log(123)
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      onSubmit() {
        console.log('submit!');
      }
    }
  }
</script>

<style>
</style>
