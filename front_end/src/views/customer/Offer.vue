<!--
@author: pengjia
@date: 2020/10/23
@description:
-->
<template>
  <div>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" placeholder="请输入" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" size="mini" icon="el-icon-zoom-in"
                     @click="advancedSearch = !advancedSearch">高查</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh"></el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in:top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
          <el-form :model="searchForm" ref="advancedSearchFormRef" size="mini"
                   label-position="right" label-width="80px">
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="报价主题" prop="offerTheme">
                  <el-input v-model="searchForm.offerTheme" clearable placeholder="请输入"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="销售机会" prop="saleId">
                  <el-select v-model="searchForm.saleId" clearable placeholder="请选择"></el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
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
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "BaoJia",
    data() {
      return {
        searchInput:'',
        advancedSearch:false,
        searchForm:{
          offerTheme:'',
          saleId:''
        }
      }
    },
    methods:{
      searchInputClick() {

      },
      advancedQueryClick() {

      }
    }
  }
</script>

<style scoped>

</style>
