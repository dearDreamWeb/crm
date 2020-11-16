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
          <el-button type="text" icon="el-icon-star-on"
                     @click="openSanYiDialog">三一客</el-button>
        </el-col>
        <el-col :span="8">
          <el-button type="text" icon="el-icon-coin">报价（暂缓）</el-button>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="text" icon="el-icon-office-building"
                     @click="openSaleDialog">销售机会</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-chat-dot-round"
                     @click="openDemandDialog">客户需求</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-s-claim"
                     @click="openSolutionDialog">解决方案</el-button>
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
      <span>全程</span>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="text" icon="el-icon-timer">待办任务</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-bicycle"
                     @click="openFollowDialog">行动记录</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-postcard"
                     @click="openContactsDialog">联系人</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="text" icon="el-icon-potato-strips">纪念日</el-button>
        </el-col>
      </el-row>
    </el-dialog>
    <customer-more-san-yi ref="customerMoreSanYiRef"></customer-more-san-yi>
    <customer-more-sale ref="customerMoreSaleRef"></customer-more-sale>
    <customer-more-demand ref="customerMoreDemandRef"></customer-more-demand>
    <customer-more-solution ref="customerMoreSolutionRef"></customer-more-solution>
    <customer-more-follow ref="customerMoreFollowRef"></customer-more-follow>
    <customer-more-contacts ref="customerMoreContactsRef"></customer-more-contacts>
  </div>
</template>

<script>
  import CustomerMoreSanYi from "./CustomerMoreSanYi";
  import CustomerMoreSale from "./CustomerMoreSale";
  import CustomerMoreDemand from "./CustomerMoreDemand";
  import CustomerMoreSolution from "./CustomerMoreSolution";
  import CustomerMoreFollow from "./CustomerMoreFollow";
  import CustomerMoreContacts from "./CustomerMoreContacts";
  export default {
    name: "CustomerMore",
    components: {
      CustomerMoreContacts,CustomerMoreSanYi,
      CustomerMoreFollow, CustomerMoreSolution,
      CustomerMoreDemand, CustomerMoreSale,
    },
    data() {
      return {
        customerMoreDialog:false,
      }
    },
    methods:{
      sanYiQualitativeRadioChange() {
        this.customerMoreSanYiRef.qualitativeRadioChange()
      },
      sanYiGradingRadioChange() {
        this.customerMoreSanYiRef.gradingRadioChange()
      },
      sanYiRationRadioChange() {
        this.customerMoreSanYiRef.rationRadioChange()
      },

      openCustomerMore() {
        this.customerMoreDialog = true
      },
      openSanYiDialog() {
        this.$refs.customerMoreSanYiRef.openSanYiDialog()
      },
      openSaleDialog() {
        this.$refs.customerMoreSaleRef.openAddDialog()
      },
      openDemandDialog() {
        this.$refs.customerMoreDemandRef.openAddDialog()
      },
      openSolutionDialog() {
        this.$refs.customerMoreSolutionRef.openAddDialog()
      },
      openFollowDialog() {
        this.$refs.customerMoreFollowRef.openAddDialog()
      },
      openContactsDialog() {
        this.$refs.customerMoreContactsRef.openAddDialog()
      }
    }
  }
</script>

<style scoped>
  >>>.el-radio__input .el-radio__inner{
    display: none;
  }
  .radio-box{
    text-align: center;
  }
  >>>.sanDinDialogBox .el-dialog__body{
    text-align: center;
  }
  >>>.sanDinDialogBox .el-dialog__body{
    padding-top: 5px;
  }
</style>
