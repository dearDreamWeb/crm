<!--
@author: pengjia
@date: 2020/11/13
@description:
-->
<template>
  <el-container>
    <el-main>
      <div class="window-box">
        <el-button type="primary" circle icon="el-icon-circle-plus-outline"></el-button>
      </div>
      <div class="header-box">
        <p class="customer_name">客户名称</p>
        <p>创建：更新：隶属：</p>
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
                      <el-button style="margin-left: 20px" icon="el-icon-search" circle></el-button>
                    </el-col>
                    <el-col :span="6">
                      <el-button style="margin-left: 20px" icon="el-icon-search" circle></el-button>
                    </el-col>
                    <el-col :span="6">
                      <el-button style="margin-left: 20px" icon="el-icon-search" circle></el-button>
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
                  <span><i class="el-icon-s-finance"></i> 应收/预付</span>
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
                  BODY
                </div>
              </el-card>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-document"></i> 基本信息</span>
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
                  BODY
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="16">
          <el-row>
            <el-col>
              <el-card>
                <div slot="header">
                  <span><i class="el-icon-s-flag"></i> 跟单里程碑</span>
                </div>
                <div>
                  BODY
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
                    <el-tab-pane label="全部" name="one">
                      <span slot="label"><i class="el-icon-s-grid">全部</i>
                        <el-tag size="mini">11</el-tag>
                      </span>
                      标签页内容
                    </el-tab-pane>
                    <el-tab-pane label="任务行动" name="two">
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
                    </el-tab-pane>
                  </el-tabs>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: "CustomerDetail",
    data() {
      return {
        customerId:'',
        customer:{},
        activeName: 'one'
      }
    },
    methods:{
      handleClick(tab, event) {

      }
    },
    created() {
      this.customerId = this.$urlUtil.getQueryVariable("customerId")
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

</style>
