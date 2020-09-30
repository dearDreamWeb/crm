<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input size="mini" placeholder="请输入内容" clearable>
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button size="mini" type="primary" icon="el-icon-plus">添加部门</el-button>
          <el-button size="mini" type="primary" icon="el-icon-zoom-in">高级查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh"></el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit">修改用户</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete">删除用户</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px">
          <el-form size="mini" label-position="right" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label-width="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="问题">
                  <el-input size="mini" placeholder="INPUT" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="日期">
                  <el-date-picker type="daterange" range-separator="至"
                                  start-placeholder="开始日期" end-placeholder="结束日期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </transition>

      <el-table border style="width: 100%;margin-top: 10px;margin-bottom: 10px">
        <el-table-column label="NAME" sortable></el-table-column>
        <el-table-column label="NAME"></el-table-column>
        <el-table-column label="NAME"></el-table-column>
        <el-table-column label="NAME"></el-table-column>
        <el-table-column label="NAME"></el-table-column>
        <el-table-column label="NAME"></el-table-column>
        <el-table-column label="NAME"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "Dept"
  }
</script>

<style scoped>

</style>
