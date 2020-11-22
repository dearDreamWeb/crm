<!--
@author: pengjia
@date: 2020/11/18
@description:
-->
<template>
  <el-container style="height: 100%">
    <el-main v-loading.fullscreen.lock="fullscreenLoading">
      <el-card>
        <div slot="header">
          <div class="header-left" style="font-size: 20px">
            <span>销售机会视图||根据销售机会确定跟进、需求、方案、报价</span>
            <el-tooltip effect="dark" content="数据日志" placement="bottom">
              <i class="el-icon-menu"></i>
            </el-tooltip>
          </div>
        </div>
        <div>
          <el-row :gutter="20">
            <el-col :span="14">
              <el-card>
                <el-form label-width="100px" label-position="right">
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="机会主题：">
                        {{saleForm.saleName}}
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="更新日期：">
                        {{saleForm.updateTime | dateFormat}}
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="客户：">
                        <el-tag>{{saleForm.cusName}}</el-tag>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="状态：">
                        {{saleForm.saleStatus}}
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="客户联系人：">
                        {{saleForm.contactsName}}
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="类型：">
                        {{saleForm.saleType}}
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="发现时间：">
                        {{saleForm.discoveryTime | dateFormat}}
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="负责人：">
                        {{saleForm.empName}}
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="阶段：">
                        <el-tag>{{saleForm.saleDetailResp.saleStage}}</el-tag>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="预签日期：">
                        <el-tag v-if="saleForm.saleDetailResp.saleEstimateDate==null">暂定</el-tag>
                        <el-tag v-else>{{saleForm.saleDetailResp.saleEstimateDate | dateFormat}}</el-tag>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="预期金额：">
                        <el-tag v-if="saleForm.saleDetailResp.saleExpectMoney==null">暂定</el-tag>
                        <el-tag v-else>{{saleForm.saleDetailResp.saleEstimateDate | dateFormat}}</el-tag>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="可能性：">
                        <el-tag v-if="saleForm.saleDetailResp.salePossibility == null">暂定</el-tag>
                        <el-progress v-else type="circle" :width="50"
                                     :percentage="parseInt(saleForm.saleDetailResp.salePossibility)"></el-progress>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="星标：">
                        <img :src="saleForm.saleDetailResp.saleStarBeacon">
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="优先：">
                        {{saleForm.saleDetailResp.salePriorLevel}}
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="阶段停留：">
                        <el-tag></el-tag>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="竞争：">
                        <el-tag>静态-暂定</el-tag>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>
                <el-button @click="wulihua">BUTTON</el-button>
              </el-card>
            </el-col>
            <el-col :span="10">
              <el-card>
                <el-collapse v-model="activeNames" accordion>
                  <el-collapse-item name="1">
                    <template slot="title">
                      <span>待办任务</span>
                      <el-button icon="el-icon-circle-plus" size="mini" class="collapse-button"
                                 @click="openFollow" circle></el-button>
                    </template>
                    <el-table height="260px" :data="followListForm" class="list_form_table">
                      <el-table-column type="index"></el-table-column>
                      <el-table-column>
                        <template slot-scope="scope">
                          内容：<el-tag>{{scope.row.followContent}}</el-tag><br>
                          时间：<el-tag>{{scope.row.createTime | dateTimeFormat}}</el-tag>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-collapse-item>
                  <el-collapse-item name="2">
                    <template slot="title">
                      <span>客户需求</span>
                      <el-button size="mini" icon="el-icon-circle-plus" class="collapse-button"
                                 @click="openDemand" circle></el-button>
                    </template>
                    <el-table :data="demandListForm" height="260px" class="list_form_table">
                      <el-table-column type="index"></el-table-column>
                      <el-table-column>
                        <template slot-scope="scope">
                          内容：<el-tag>{{scope.row.demandContent}}</el-tag>
                          <el-tag type="danger">{{scope.row.isSolve | cusIsSolve}}</el-tag><br>
                          时间：<el-tag>{{scope.row.createTime | dateFormat}}</el-tag>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-collapse-item>
                  <el-collapse-item name="3">
                    <template slot="title">
                      <span>解决方案</span>
                      <el-button size="mini" icon="el-icon-circle-plus" class="collapse-button"
                                 @click="openSolution" circle></el-button>
                    </template>
                    <el-table :data="solutionListForm" height="260px" class="list_form_table">
                      <el-table-column type="index"></el-table-column>
                      <el-table-column>
                        <template slot-scope="scope">
                          内容：{{scope.row.content}}<br>
                          时间：<el-tag>{{scope.row.createTime | dateFormat}}</el-tag>
                          机会：<el-tag type="warning">{{scope.row.saleName}}</el-tag>
                          需求：<el-tag type="info">{{scope.row.demandTitle}}</el-tag>
                        </template>
                      </el-table-column>
                      <el-table-column width="30px">
                        <template slot-scope="scope">
                          <el-button icon="el-icon-edit" type="text"></el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-collapse-item>
                  <el-collapse-item name="4">
                    <template slot="title">
                      <span>产品报价</span>
                      <el-button size="mini" icon="el-icon-circle-plus" circle
                                 class="collapse-button" @click="openOffer"></el-button>
                    </template>
                    <el-table :data="offerListForm" height="260px" class="list_form_table">
                      <el-table-column type="index"></el-table-column>
                      <el-table-column>
                        <template slot-scope="scope">
                          主题：{{scope.row.offerTheme}}<br>
                          单号：{{scope.row.offerNumbers}}
                          状态：<el-tag>{{scope.row.offerStatus}}</el-tag><br>
                          时间：{{scope.row.createTime | dateFormat}}
                          <el-button type="text" icon="el-icon-right" size="mini"
                                     @click="editDetail(scope.row.offerId)">编辑明细</el-button>
                          <el-button type="text" icon="el-icon-right" size="mini"
                                     @click="viewDetail(scope.row.offerId)">查看明细</el-button>
                          <el-button type="text" icon="el-icon-right" size="mini"
                                     @click="turnOrder">转成订单</el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-collapse-item>
                </el-collapse>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <sale-more-follow ref="saleMoreFollowFef" :sale-id="saleId" :cus-id="cusId"
                        :emp-id="empId" v-on:init-page="initSaleDetail"></sale-more-follow>
      <sale-more-demand ref="saleMoreDemandRef" :sale-id="saleId" :cus-id="cusId"
                        :emp-id="empId" v-on:init-page="initSaleDetail"></sale-more-demand>
      <sale-more-solution ref="saleMoreSolutionRef" :sale-id="saleId" :cus-id="cusId"
                          :emp-id="empId" v-on:init-page="initSaleDetail"></sale-more-solution>
      <sale-more-offer ref="saleMoreOfferRef" :sale-id="saleId" :cus-id="cusId" :sale-form="saleForm"
                       :emp-id="empId" v-on:init-page="initSaleDetail"></sale-more-offer>
    </el-main>
  </el-container>
</template>

<script>
  import {saleHttp} from "../../network/pre_sale/sale";
  import SaleMoreFollow from "../../components/sale/SaleMoreFollow";
  import SaleMoreDemand from "../../components/sale/SaleMoreDemand";
  import SaleMoreSolution from "../../components/sale/SaleMoreSolution";
  import {followHttp} from "../../network/pre_sale/followlog";
  import {demandHttp} from "../../network/pre_sale/demand";
  import {solutionHttp} from "../../network/pre_sale/solution";
  import SaleMoreOffer from "../../components/sale/SaleMoreOffer";
  import {offerHttp} from "../../network/pre_sale/offer";
  import OfferOperation from "../customer/OfferOperation";

  export default {
    name: "SaleDetail",
    components: {SaleMoreOffer, SaleMoreSolution, SaleMoreDemand, SaleMoreFollow},
    data() {
      return {

        fullscreenLoading:false,

        saleId:'',
        cusId:'',
        empId:'',
        saleForm:{
          saleDetailResp:{}
        },

        followListForm:[],
        demandListForm:[],
        solutionListForm:[],
        offerListForm:[],

        followListFormLoading:false,
        activeNames: ['1']
      }
    },
    mounted() {

    },
    methods:{
      wulihua() {
        OfferOperation.methods.pengjia()
      },
      editDetail(offerId) {
        console.log(offerId)
        let resolve = this.$router.resolve({
          path:'/offer_operation',
          query:{
            saleId:this.saleId,
            offerId:offerId
          }
        });
        window.open(resolve.href,"_blank")
      },
      viewDetail() {

      },
      turnOrder() {

      },

      initSaleDetail() {
        this.fullscreenLoading = true
        saleHttp.get_by_id(this.saleId).then(res => {
          this.saleForm = res.data
          this.fullscreenLoading = false
        })
        this.initFollowBySale()
        this.initDemandBySale()
        this.initSolutionBySale()
        this.initOfferBySale()
      },
      openFollow() {
        this.$refs.saleMoreFollowFef.openAddDialog()
      },
      openDemand() {
        this.$refs.saleMoreDemandRef.openAddDialog()
      },
      openSolution() {
        this.$refs.saleMoreSolutionRef.openAddDialog()
      },
      openOffer() {
        this.$refs.saleMoreOfferRef.openAddDialog()
      },
      initFollowBySale() {
        followHttp.list_by_sale(this.saleId).then(res => {
          this.followListForm = res.data
        })
      },
      initDemandBySale() {
        demandHttp.list_by_sale(this.saleId).then(res => {
          this.demandListForm = res.data
        })
      },
      initSolutionBySale() {
        solutionHttp.list_by_sale(this.saleId).then(res => {
          this.solutionListForm = res.data
        })
      },
      initOfferBySale() {
        offerHttp.list_by_sale(this.saleId).then(res => {
          this.offerListForm = res.data
        })
      }
    },
    created() {
      this.saleId = this.$urlUtil.getQueryVariable("saleId")
      this.cusId = this.$urlUtil.getQueryVariable("cusId")
      this.empId = this.$urlUtil.getQueryVariable("empId")
      this.initSaleDetail()
      this.initFollowBySale()
      this.initDemandBySale()
      this.initSolutionBySale()
      this.initOfferBySale()
    },
  }
</script>

<style scoped>
  .sale_detail_row{
    margin-bottom: 20px;
  }
  >>>.el-form .el-form-item__label{
    color: #aabada;
  }
  .list_form_table{
    margin-top: -38px;
  }
  ::-webkit-scrollbar {
    width: 0 !important;
  }
  ::-webkit-scrollbar {
    width: 0 !important;height: 0;
  }
  .el-table ::-webkit-scrollbar {
    width: 0 !important;
  }
  .el-table ::-webkit-scrollbar {
    width: 0 !important;height: 0;
  }
  .collapse-button {
    margin-left: 5px;
  }
</style>
