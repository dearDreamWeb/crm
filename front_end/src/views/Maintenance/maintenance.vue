<template>
  <el-card>
      <el-col :span="24">
        <el-button type="primary" size="mini" icon="el-icon-plus" @click="openAddDialog">派单</el-button>
        <el-button type="danger" size="mini" icon="el-icon-delete"
                   :disabled="buttonDisabled" @click="deleteCare">取消</el-button>
      </el-col>

  <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
            :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
            highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
    <el-table-column type="index" width="40"></el-table-column>
    <el-table-column prop="customerResp.cusName" label="对应客户"></el-table-column>
    <el-table-column prop="repairSfzb" label="联系人"></el-table-column>
    <!--        <el-table-column prop="deptResp.deptName" label="维修部门"></el-table-column>-->
    <el-table-column prop="productResp.productName" label="维修产品"></el-table-column>
    <el-table-column prop="empResp.empName" label="接单人"></el-table-column>
    <el-table-column prop="repairsjhm" label="是否在保"></el-table-column>
  </el-table>
  <el-pagination background
                 @current-change="handleCurrentChange"
                 :current-page="pageNum" :page-sizes="[1,2,5,10]"
                 :page-size="pageSize" :total="total"
                 layout="prev, pager, next, jumper, total">
  </el-pagination>
  </el-card>
</template>

<script>
  import {repairHttp} from "../../network/system/repair";

  export default {
    data() {
      return {
        listForm: [],
        rowCareId:0,
      }
    },
    methods: {
      handleCurrentChange(pageIndex){
        this.searchForm.pageNum = pageIndex
        this.searchForm.pageSize = this.pageSize
        repairHttp.queryEmp(this.searchForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      handleRowClick(row,event,column) {
        this.rowCareId = row.repairId
        if (this.repairId != 0) {
          this.buttonDisabled = false
        }
      },
      handleEdit() {

      },
      handleDelete(){

      }
    }
  }
</script>

<style scoped>

</style>
