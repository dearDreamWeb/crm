<!--
@author: pengjia
@date: 2020/11/15
@description:
-->
<template>
  <div>
    <el-dialog :visible.sync="customerMoreDialog" top="25px" :show-close="false">
      <span>售前</span>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-button type="text" icon="el-icon-star-on" @click="openSanYiDialog">三一客</el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="text" icon="el-icon-coin">报价</el-button>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="text" icon="el-icon-office-building">销售机会</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-chat-dot-round">客户需求</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-s-claim">解决方案</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-warning">竞争关系</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <span>售中</span>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="text" icon="el-icon-tickets">订单</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-finished">回款</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-c-scale-to-original">计划回款</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <span>售后</span>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="text" icon="el-icon-scissors">维修工单</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-phone-outline">客服记录</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-bangzhu">投诉</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-dessert">客户关怀</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <span>全部</span>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="text" icon="el-icon-timer">待办任务</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-bicycle">行动记录</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-postcard">联系人</el-button>
        </el-col>
      </el-row>
    </el-dialog>
    <el-dialog :visible.sync="sanYiDialog" width="30%"></el-dialog>
  </div>
</template>

<script>
  export default {
    name: "CustomerMore",
    data() {
      return {
        customerMoreDialog:false,
        sanYiDialog:false
      }
    },
    methods:{
      openCustomerMore() {
        this.customerMoreDialog = true
      },
      openSanYiDialog() {
        this.sanYiDialog = true
      }
    }
  }
</script>

<style scoped>

</style>
