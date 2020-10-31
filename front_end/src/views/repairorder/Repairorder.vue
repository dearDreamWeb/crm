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

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="40"></el-table-column>
        <el-table-column prop="repairProblem" label="主题" sortable></el-table-column>
        <el-table-column prop="cusId" label="对应客户"></el-table-column>
        <el-table-column prop="repairWxfy" label="费用"></el-table-column>
        <el-table-column prop="repairGdstae" label="状态"></el-table-column>
        <el-table-column prop="empResp.empName" label="承接部门"></el-table-column>
        <el-table-column prop="empId" label="接单人"></el-table-column>
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
        value: '',
        input: '',
        listForm:[],
        rowCareId:0,
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
      handleRowClick(row,event,column) {
        this.rowCareId = row.repairId
        if (this.repairId != 0) {
          this.buttonDisabled = false
        }
      },
      initList() {
        repairHttp.listPage(this.pageNum, this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
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
    },
    created() {
      this.initList()

    }
  }
</script>

<style>
</style>
