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
          <el-row>
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
          </el-row>
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
                    <el-tab-pane label="全部" :name="item" v-for="(item,index) in activeNameList" :key="index">
                      <span slot="label"><i class="el-icon-s-grid">全部</i>
                        <el-tag size="mini">{{item}}</el-tag>
                      </span>
                      <div>
                        {{item}}
                        <el-timeline>
                          <el-timeline-item
                            v-for="(activity, index) in activities"
                            :key="index"
                            :icon="activity.icon"
                            :type="activity.type"
                            :color="activity.color"
                            :size="activity.size"
                            :timestamp="activity.timestamp">
                            {{activity.content}}
                          </el-timeline-item>
                        </el-timeline>
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
      <customer-more ref="customerMoreRef" :msg="customerId"></customer-more>
    </el-main>
  </el-container>
</template>

<script>
  import CustomerMore from "../../components/customer/CustomerMore";
  import {customerHttp} from "../../network/pre_sale/customer";
  import {contactsHttp} from "../../network/pre_sale/contacts";
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

        activeName: '1',
        activeNameList:['1','2','3','4','5','6','7','8','9','10','11','12'],

        activities: [{
          content: '支持使用图标',
          timestamp: '2018-04-12 20:46',
          size: 'large',
          type: 'primary',
          icon: 'el-icon-more'
        }, {
          content: '支持自定义颜色',
          timestamp: '2018-04-03 20:46',
          color: '#0bbd87'
        }, {
          content: '支持自定义尺寸',
          timestamp: '2018-04-03 20:46',
          size: 'large'
        }, {
          content: '默认样式的节点',
          timestamp: '2018-04-03 20:46'
        }]
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
      }

    },
    created() {
      this.customerId = this.$urlUtil.getQueryVariable("customerId")
      this.initCustomerDetail()
      this.initContactsList()
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
</style>
