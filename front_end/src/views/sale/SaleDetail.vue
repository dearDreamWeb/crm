<!--
@author: pengjia
@date: 2020/11/18
@description:
-->
<template>
  <el-container style="height: 100%">
    <el-main>
      <el-card>
        <div slot="header">
          <div class="header-left" style="font-size: 20px">
            <span>销售机会视图</span>
            <el-tooltip effect="dark" content="数据日志" placement="bottom">
              <i class="el-icon-menu"></i>
            </el-tooltip>
          </div>
        </div>
        <div>
          <el-row :gutter="20">
            <el-col :span="14">
              <el-card>
                <el-form>
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

                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="状态：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="客户联系人：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="类型：">

                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="发现时间：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="来源：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="负责人：">

                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="提供人：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="预计签单日期：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="预期金额：">

                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="可能性：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="星标：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="优先：">

                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-form-item label="阶段：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="阶段停留：">

                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="竞争：">

                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>
              </el-card>
            </el-col>
            <el-col :span="10">
              <el-row class="sale_detail_row">
                <el-col>
                  <el-card>
                    <div slot="header">
                      <span>待办任务</span>
                      <el-button icon="el-icon-circle-plus" size="mini"
                                 @click="openFollow" circle></el-button>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
              <el-row class="sale_detail_row">
                <el-col>
                  <el-card>
                    <div slot="header">
                      <span>客户需求</span>
                      <el-button size="mini" icon="el-icon-circle-plus"
                                 @click="openDemand" circle></el-button>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
              <el-row class="sale_detail_row">
                <el-col>
                  <el-card>
                    <div slot="header">
                      <span>解决方案</span>
                      <el-button size="mini" icon="el-icon-circle-plus"
                                 @click="openSolution" circle></el-button>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
              <el-row class="sale_detail_row">
                <el-col>
                  <el-card>
                    <div slot="header">
                      <span>报价</span>
                      <el-button size="mini" icon="el-icon-circle-plus" circle></el-button>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
              <el-row class="sale_detail_row">
                <el-col>
                  <el-card>
                    <div slot="header">
                      <span>竞争</span>
                      <el-button size="mini" icon="el-icon-circle-plus" circle></el-button>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <sale-more-follow ref="saleMoreFollowFef" :sale-id="saleId" :cus-id="cusId"></sale-more-follow>
      <sale-more-demand ref="saleMoreDemandRef" :sale-id="saleId" :cus-id="cusId"></sale-more-demand>
      <sale-more-solution ref="saleMoreSolutionRef" :sale-id="saleId" :cus-id="cusId"></sale-more-solution>
    </el-main>
  </el-container>
</template>

<script>
  import {saleHttp} from "../../network/pre_sale/sale";
  import SaleMoreFollow from "../../components/sale/SaleMoreFollow";
  import SaleMoreDemand from "../../components/sale/SaleMoreDemand";
  import SaleMoreSolution from "../../components/sale/SaleMoreSolution";

  export default {
    name: "SaleDetail",
    components: {SaleMoreSolution, SaleMoreDemand, SaleMoreFollow},
    data() {
      return {
        saleId:'',
        cusId:'',
        saleForm:{},
      }
    },
    methods:{
      initSaleDetail() {
        saleHttp.get_by_id(this.saleId).then(res => {
          this.saleForm = res.data
        })
      },
      openFollow() {
        this.$refs.saleMoreFollowFef.openAddDialog()
      },
      openDemand() {
        this.$refs.saleMoreDemandRef.openAddDialog()
      },
      openSolution() {
        this.$refs.saleMoreSolutionRef.openAddDialog()
      }
    },
    created() {
      this.saleId = this.$urlUtil.getQueryVariable("saleId")
      this.cusId = this.$urlUtil.getQueryVariable("cusId")
      this.initSaleDetail()
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
</style>
