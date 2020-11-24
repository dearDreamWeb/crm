<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入状态内容查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
      </el-row>
      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="40"></el-table-column>
        <el-table-column prop="customerResp.cusName" label="对应客户"></el-table-column>
        <!--    <el-table-column prop="repairSfzb" label="联系人"></el-table-column>-->
        <el-table-column prop="szOrder.ordPhone" label="手机号码"></el-table-column>
        <el-table-column prop="szOrder.ordConsignee" label="联系人"></el-table-column>
        <!--        <el-table-column prop="deptResp.deptName" label="维修部门"></el-table-column>-->
        <el-table-column prop="szOrder.ordTheme" label="维修产品"></el-table-column>
        <!--    <el-table-column prop="empResp.empName" label="接单人"></el-table-column>-->
        <el-table-column prop="repairsjhm" label="是否在保"></el-table-column>
        <el-table-column prop="empResp.empName" label="维修人"></el-table-column>
        <el-table-column label="操作">

        </el-table-column>
      </el-table>
      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>
    <el-dialog
      title="新增"
      :visible.sync="addDialog"
      width="50%"
      @close="handleClose">

      <el-form ref="addformRef" :model="addform" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="维修费用" prop="empId">
              <el-select v-model="">

              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div style="text-align: center;">
        <el-button @click="addDialog = false">取 消</el-button>
        <el-button type="primary" @click="addClick()"
                   :loading="addDictButtonLoading">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "paidanyg"
    }
</script>

<style scoped>

</style>
