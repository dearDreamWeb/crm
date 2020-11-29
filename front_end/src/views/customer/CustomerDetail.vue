<!--
@author: pengjia
@date: 2020/11/13
@description:
-->
<template>
  <el-container style="height: 100%">
    <el-main>
      <div class="window-box">
        <el-button type="primary" circle @click="openCustomerMore"
                   icon="el-icon-circle-plus-outline"></el-button>
      </div>
      <div class="header-box">
        <p class="customer_name">{{this.customer.cusName}}</p>
        <p>
          创建：{{this.customer.createTime | dateFormat}}
          更新：{{this.customer.updateTime | dateFormat}}
          隶属：{{this.customer.empName}}
        </p>
      </div>
      <el-row :gutter="20">
        <el-col :span="8">
          <!--<el-row>
            <el-col>
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-star-on"></i> 三定一节点</span>
                </div>
                <div>
                  <el-row type="flex" justify="space-around">
                    <el-col :span="6">
                      <div>
                        <el-button class="contacts-border">
                          <div class="san-yi-inside-box">
                            <i class="el-icon-search san-yi-top-icon"></i>
                          </div><br>
                          <span>定性</span>
                        </el-button>
                      </div>
                    </el-col>
                    <el-col :span="6">
                      <div>
                        <el-button class="contacts-border" type="primary">
                          <div class="san-yi-inside-box">
                            <i class="el-icon-search san-yi-top-icon"></i>
                          </div><br>
                          <span>定级</span>
                        </el-button>
                      </div>
                    </el-col>
                    <el-col :span="6">
                      <div>
                        <el-button class="contacts-border">
                          <div class="san-yi-inside-box">
                            <i class="el-icon-search san-yi-top-icon"></i>
                          </div><br>
                          <span>定量</span>
                        </el-button>
                      </div>
                    </el-col>
                  </el-row>
                  <el-row type="flex" justify="space-between">
                    <el-col :span="4">
                      <div class="align-box">
                        <el-button size="mini" class="contacts-border">
                          <i class="el-icon-s-custom san-din-yi-icon"></i><br>
                          <span>识别接触</span><br>
                          <span>0 days</span>
                        </el-button>
                      </div>
                    </el-col>
                    <el-col :span="4">
                      <div class="align-box">
                        <el-button size="mini" class="contacts-border">
                          <i class="iconfont icon-xuqiu san-din-yi-icon"></i><br>
                          <span>激发需求</span><br>
                          <span>0 days</span>
                        </el-button>
                      </div>
                    </el-col>
                    <el-col :span="4">
                      <div class="align-box">
                        <el-button size="mini" class="contacts-border">
                          <i class="iconfont icon-chanpin san-din-yi-icon"></i><br>
                          <span>推荐产品</span><br>
                          <span>0 days</span>
                        </el-button>
                      </div>
                    </el-col>
                    <el-col :span="4">
                      <div class="align-box">
                        <el-button size="mini" class="contacts-border">
                          <i class="iconfont icon-jujue san-din-yi-icon"></i><br>
                          <span>拒绝处理</span><br>
                          <span>0 days</span>
                        </el-button>
                      </div>
                    </el-col>
                    <el-col :span="4">
                      <div class="align-box">
                        <el-button size="mini" class="contacts-border">
                          <i class="el-icon-trophy san-din-yi-icon"></i><br>
                          <span>促成销售</span><br>
                          <span>0 days</span>
                        </el-button>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-card>
            </el-col>
          </el-row>-->
          <el-row>
            <el-col>
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-s-order"></i> 签约数据</span>
                </div>
                <div>
                  <el-row type="flex" justify="space-between">
                    <el-col :span="5">
                      <div class="align-box">
                        <span>1万8</span><br>
                        <span>发货</span>
                      </div>
                    </el-col>
                    <el-col :span="5">
                      <div class="align-box">
                        <span>1万8</span><br>
                        <span>回款</span>
                      </div>
                    </el-col>
                    <el-col :span="5">
                      <div class="align-box">
                        <span>1万8</span><br>
                        <span>往来对账</span>
                      </div>
                    </el-col>
                    <el-col :span="5">
                      <div class="align-box">
                        <span>1万8</span><br>
                        <span>明细往来对账</span>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-card>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-s-custom"></i> 联系人</span>
                </div>
                <div>
                  <!--<el-row :gutter="30">
                    <el-col :span="8">
                      <div class="align-box">
                        <el-button class="contacts-border">
                          &lt;!&ndash;<i v-if="this.contactsList[0].sex === 1" class="icon iconfont icon-man"></i>
                          <i v-if="this.contactsList[0].sex === 2" class="icon iconfont icon-woman"></i>&ndash;&gt;
                          &lt;!&ndash;<br><span>{{this.contactsList[0].contactsName}}</span>&ndash;&gt;
                        </el-button>
                      </div>
                    </el-col>
                    <el-col :span="16">
                      <div>
                        <el-form size="mini">
                          <el-form-item label="电话">
                            {{this.contactsList.get(0).contactsPhone}}
                          </el-form-item>
                          <el-form-item label="微信">
                            {{contactsList[0].wechat}}
                          </el-form-item>
                          <el-form-item label="QQ">
                            {{contactsList[0].qq}}
                          </el-form-item>
                          <el-form-item label="邮箱">
                            {{contactsList[0].email}}
                          </el-form-item>
                        </el-form>
                      </div>
                    </el-col>
                  </el-row>-->
                  <el-row :gutter="10">
                    <el-col :span="6" v-for="item in contactsList" :key="item.contactsId">
                      <div class="align-box">
                        <el-button class="contacts-border">
                          <i v-if="item.sex === 1" class="icon iconfont icon-man"></i>
                          <i v-if="item.sex === 0" class="icon iconfont icon-woman"></i>
                          <br><span>{{item.contactsName}}</span>
                        </el-button>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-card>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-location-information"></i> 地址</span>
                </div>
                <div>
                  <el-form :inline="true" size="mini" class="address-box">
                    <el-form-item label="国家或地区:">CN China中国 亚洲</el-form-item>
                    <el-form-item label="省份:">
                      <span v-if="customer.province != null">{{customer.province}}</span>
                      <span v-else>暂定</span>
                    </el-form-item>
                    <el-form-item label="城市:">
                      <span v-if="customer.city != null">{{customer.city}}</span>
                      <span v-else>暂定</span>
                    </el-form-item>
                    <el-form-item label="地址:">
                      <span v-if="customer.detailAddress != null">{{customer.detailAddress}}</span>
                      <span v-else>暂定</span>
                    </el-form-item>
                  </el-form>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="16">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-document"></i> 基本信息</span>
                </div>
                <div>
                  <el-form :inline="true" size="mini" label-position="right"
                           label-width="50px">
                    <el-form-item label="简称:">
                      <span v-if="customer.abbreviation != null">{{customer.abbreviation}}</span>
                      <span v-else>暂定</span>
                    </el-form-item>
                    <el-form-item label="来源:">
                      <span v-if="customer.cusDictSourceName != null">{{customer.cusDictSourceName}}</span>
                      <span v-else>暂定</span>
                    </el-form-item>
                    <el-form-item label="首签:">
                      <span v-if="customer.firstSigningTime != null">{{customer.firstSigningTime}}</span>
                      <span v-else>暂定</span>
                    </el-form-item>
                  </el-form>
                </div>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-s-finance"></i> 应收/预付</span>
                </div>
                <div>
                  <el-row type="flex" justify="space-between">
                    <el-col :span="6">
                      <div class="align-box">
                        <span>￥ 3,325.00</span><br>
                        <span>计划回款</span>
                      </div>
                    </el-col>
                    <el-divider direction="vertical" class="divider-line"></el-divider>
                    <el-col :span="6">
                      <div class="align-box">
                        <span>-￥ 3,417.00</span><br>
                        <span>智能应收</span>
                      </div>
                    </el-col>
                    <el-divider direction="vertical" class="divider-line"></el-divider>
                    <el-col :span="6">
                      <div class="align-box">
                        <span>￥ 0.00</span><br>
                        <span>预付余额</span>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-card>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-watch"></i> 跟单时间线</span>
                </div>
                <div>
                  <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="全部" name="1">
                      <span slot="label">
                        <i class="el-icon-s-grid">客户跟进</i>
                      </span>
                      <div>
                        <el-timeline>
                          <el-timeline-item v-for="item in customerRecordList" :hide-timestamp="true"
                                            :key="item.recordId" :timestamp="item.recordTime"
                                            icon="el-icon-more" type="primary" size="large">
                            <span>标题：{{item.recordTitle}}</span><br>
                            <span>内容：{{item.recordContent}}</span><br>
                            <span>{{item.recordTime | dateFormat}}</span>
                          </el-timeline-item>
                        </el-timeline>
                      </div>
                    </el-tab-pane>
                    <el-tab-pane label="销售机会" name="2">
                      <span slot="label">
                        <i class="el-icon-s-help">销售机会</i>
                      </span>
                      <div>
                        <el-table :data="saleList" :header-row-style="iHeaderRowStyle"
                                  :header-cell-style="iHeaderCellStyle">
                          <el-table-column type="expand" prop="demandResps">
                            <template slot-scope="props">
                              <el-table
                                        :data="props.row.demandResps" :header-row-style="iHeaderRowStyle"
                                        :header-cell-style="iHeaderCellStyle" style="margin-top: -20px">
                                <el-table-column type="expand" prop="solutionResps">
                                  <template slot-scope="scope">
                                    <el-table v-if="scope.row.solutionResps.solutionTitle != null"
                                              :data="scope.row.solutionResps" :header-row-style="iHeaderRowStyle"
                                              :header-cell-style="iHeaderCellStyle" style="margin-top: -20px">
                                      <el-table-column prop="solutionTitle" label="方案主题"></el-table-column>
                                      <el-table-column prop="createTime" label="提交时间">
                                        <template slot-scope="scope">
                                          {{scope.row.createTime | dateFormat}}
                                        </template>
                                      </el-table-column>
                                      <el-table-column prop="content" label="内容" show-overflow-tooltip></el-table-column>
                                    </el-table>
                                  </template>
                                </el-table-column>
                                <el-table-column prop="demandTitle" label="需求主题"></el-table-column>
                                <el-table-column prop="demandDegree" label="等级"></el-table-column>
                                <el-table-column prop="demandContent" label="内容"></el-table-column>
                                <el-table-column prop="createTime" label="时间">
                                  <template slot-scope="scope">
                                    {{scope.row.createTime | dateFormat}}
                                  </template>
                                </el-table-column>
                              </el-table>
                            </template>
                          </el-table-column>
                          <el-table-column prop="saleName" label="机会名称"></el-table-column>
                          <el-table-column prop="saleStatus" label="机会状态"></el-table-column>
                          <el-table-column prop="saleType" label="机会类型"></el-table-column>
                          <el-table-column prop="discoveryTime" label="发现时间">
                            <template slot-scope="scope">
                              {{scope.row.discoveryTime | dateFormat}}
                            </template>
                          </el-table-column>
                          <el-table-column prop="saleDetailResp.salePriorLevel" label="等级"></el-table-column>
                          <el-table-column prop="saleDetailResp.saleStarBeacon" label="星标">
                            <template scope="scope">
                              <img :src="scope.row.saleDetailResp.saleStarBeacon"></img>
                            </template>
                          </el-table-column>
                          <el-table-column prop="saleDetailResp.saleStage" label="阶段"></el-table-column>
                          <el-table-column prop="saleDetailResp.saleEstimateDate" label="预签日期">
                            <template slot-scope="scope">
                              {{scope.row.saleDetailResp.saleEstimateDate | dateFormat}}
                            </template>
                          </el-table-column>
                          <el-table-column prop="saleDetailResp.saleExpectMoney" label="预签金额"></el-table-column>
                        </el-table>
                      </div>
                    </el-tab-pane>
                    <el-tab-pane label="报价" name="3">
                      <span slot="label">
                        <i class="el-icon-s-management">报价</i>
                      </span>
                      <div>
                        <el-table :data="offerList">
                          <el-table-column type="expand" prop="offerDetailResp">
                            <template slot-scope="props">
                              <el-form label-position="left" inline class="demo-table-expand">
                                <el-form-item label="产品名称">
                                  <span>{{ props.row.offerDetailResp.productName }}</span>
                                </el-form-item>
                                <el-form-item label="品牌">
                                  <span>{{ props.row.offerDetailResp.productBrand }}</span>
                                </el-form-item>
                                <el-form-item label="型号">
                                  <span>{{ props.row.offerDetailResp.productModel }}</span>
                                </el-form-item>
                                <el-form-item label="价格">
                                  <span>{{ props.row.offerDetailResp.productPrice }}</span>
                                </el-form-item>
                                <el-form-item label="数量">
                                  <span>{{ props.row.offerDetailResp.offerDetailCount }}</span>
                                </el-form-item>
                                <el-form-item label="金额">
                                  <span>{{ props.row.offerDetailResp.amountMoney }}</span>
                                </el-form-item>
                              </el-form>
                            </template>
                          </el-table-column>
                          <el-table-column prop="offerTheme" label="主题"></el-table-column>
                          <el-table-column prop="offerNumbers" label="单号"></el-table-column>
                          <el-table-column prop="offerStatus" label="状态">
                            <template slot-scope="scope">
                              {{scope.row.offerStatus | offerStatusFormat}}
                            </template>
                          </el-table-column>
                          <el-table-column prop="createTime" label="创建时间">
                            <template slot-scope="scope">
                              {{scope.row.createTime | dateFormat}}
                            </template>
                          </el-table-column>
                          <el-table-column prop="remark" label="备注"></el-table-column>
                        </el-table>
                      </div>
                    </el-tab-pane>
                    <!--<el-tab-pane label="任务行动" name="two">
                      <span slot="label"><i class="el-icon-bicycle">任务行动</i>
                        <el-tag size="mini">2</el-tag>
                      </span>
                    </el-tab-pane>
                    <el-tab-pane label="三一客" name="three">
                      <span slot="label"><i class="el-icon-star-on">三一客</i>
                        <el-tag size="mini">2</el-tag>
                      </span>
                    </el-tab-pane>
                    <el-tab-pane label="订单" name="four">
                      <span slot="label"><i class="el-icon-order">订单</i>
                        <el-tag size="mini">2</el-tag>
                      </span>
                    </el-tab-pane>
                    <el-tab-pane label="交付计划" name="five">
                      <span slot="label"><i class="el-icon-s-ticket">交付计划</i>
                        <el-tag size="mini">2</el-tag>
                      </span>
                    </el-tab-pane>
                    <el-tab-pane label="交付记录" name="el-icon-s-claim">
                      <span slot="label"><i class="el-icon-s-claim">交付记录</i>
                        <el-tag size="mini">2</el-tag>
                      </span>
                    </el-tab-pane>
                    <el-tab-pane label="发货单" name="seven">
                      <span slot="label"><i class="el-icon-s-data">发货单</i>
                        <el-tag size="mini">2</el-tag>
                      </span>
                    </el-tab-pane>
                    <el-tab-pane label="回款" name="eight">
                      <span slot="label"><i class="el-icon-brush">回款</i>
                        <el-tag size="mini">2</el-tag>
                      </span>
                    </el-tab-pane>
                    <el-tab-pane label="计划回款" name="nine">
                      <span slot="label"><i class="el-icon-collection">计划回款</i>
                        <el-tag size="mini">2</el-tag>
                      </span>
                    </el-tab-pane>-->
                  </el-tabs>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <customer-more ref="customerMoreRef" :msg="customerId" v-on:init="reloadDetail"></customer-more>
    </el-main>
  </el-container>
</template>

<script>
  import CustomerMore from "../../components/customer/CustomerMore";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {contactsHttp} from "../../network/pre_sale/contacts";
  import {saleHttp} from "../../network/pre_sale/sale";
  import {demandHttp} from "../../network/pre_sale/demand";
  import {offerHttp} from "../../network/pre_sale/offer";
  export default {
    name: "CustomerDetail",
    components:{
      CustomerMore
    },
    data() {
      return {
        customerId:'',
        customer:{},
        contactsList:{},
        customerRecordList:[],
        saleList:[],
        demandList:[],
        offerList:[],

        activeName: '1',
      }
    },
    methods:{
      initContactsList() {
        contactsHttp.getByCusId(this.customerId).then(res => {
          this.contactsList = res.data.list
        })
      },

      sanYiQualitativeRadioChange() {
        this.$refs.customerMoreRef.sanYiQualitativeRadioChange()
      },
      sanYiGradingRadioChange() {
        this.$refs.customerMoreRef.sanYiGradingRadioChange()
      },
      sanYiRationRadioChange() {
        this.$refs.customerMoreRef.sanYiRationRadioChange()
      },

      handleClick(tab, event) {

      },
      openCustomerMore() {
        this.$refs.customerMoreRef.openCustomerMore()
      },
      initCustomerDetail() {
        customerHttp.getCusById(this.customerId).then(res => {
          this.customer = res.data
        })
      },
      initCustomerRecord() {
        customerHttp.listRecordById(this.customerId).then(res => {
          this.customerRecordList = res.data
        })
      },
      initSaleList() {
        saleHttp.selectAll(this.customerId).then(res => {
          this.saleList = res.data
        })
      },
      initOfferList() {
        offerHttp.listByCus(this.customerId).then(res => {
          this.offerList = res.data
        })
      },

      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:3px'
      },
      reloadDetail() {
        window.location.reload()
      }
    },
    created() {
      this.customerId = this.$urlUtil.getQueryVariable("customerId")
      this.initCustomerDetail()
      this.initContactsList()
      this.initCustomerRecord()
      this.initSaleList()
      this.initOfferList()
    }
  }
</script>

<style scoped>
  .header-box{
    width: 100%;
    text-align: center;
  }
  >>>.customer_name{
    font-size: 24px;
    line-height: 0.3;
  }
  .el-row{
    margin-top: 10px;
  }

  .window-box{
    overflow: hidden;
    z-index: 99999;
    position: fixed;
    right: 20px;
    bottom: 30px;
  }

  >>>.divider-line{
    height: 3em;
  }
  .align-box{
    text-align: center;
  }
  >>>.contacts-border{
    border: 0px solid #ffffff;
  }
  .san-din-yi-icon{
    font-size: 20px;
  }
  .san-yi-top-icon{
    font-size: 24px;
  }
  .san-yi-inside-box{
    border: #0086b3 1px solid;
    border-radius: 50%;
    text-align: center;
    width: 40px;
    height: 40px;
    line-height: 50px;
  }
  >>>.el-form-item--mini.el-form-item{
    margin-bottom: 0px;
  }
  >>>.address-box .el-form-item__label{
    font-size: 12px;
  }
  >>>.address-box .el-form-item__content{
    font-size: 12px;
  }
  ::-webkit-scrollbar {
    width: 0 !important;
  }
  ::-webkit-scrollbar {
    width: 0 !important;height: 0;
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
