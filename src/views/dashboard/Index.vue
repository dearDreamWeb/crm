<!--
@author: pengjia
@date: 2020/12/1
@description:
-->
<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>新线索</span>
            <!--<el-button icon="el-icon-set-up" style="float: right; padding: 3px 0" type="text">切换</el-button>-->
            <el-select v-model="newClueForm.title" size="mini" clearable @change="newClueChange"
                       style="float: right;width: 85px;padding: 0 0" @clear="newClueClear">
              <el-option v-for="item in newClueFormData" :key="item.value"
                         :label="item.label" :value="item.value"></el-option>
            </el-select>
          </div>
          <el-row :gutter="20">
            <el-col :span="12">
              <div>
                <div id="uvClue" style="font-size: 30px">{{newClueForm.data}}</div>
              </div>
              <div>
                <span>日新增 7</span>
              </div>
            </el-col>
            <el-col :span="8" :offset="4">
              <div>
                <i style="font-size: 50px" class="el-icon-s-ticket"></i>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>新客户</span>
            <!--<el-button icon="el-icon-set-up" style="float: right; padding: 3px 0" type="text">切换</el-button>-->
            <el-select v-model="newCustomerForm.title" size="mini" clearable @change="newCustomerChange"
                       style="float: right;width: 85px;padding: 0 0" @clear="newCustomerClear">
              <el-option v-for="item in newCustomerFormData" :key="item.value"
                         :label="item.label" :value="item.value"></el-option>
            </el-select>
          </div>
          <el-row :gutter="20">
            <el-col :span="12">
              <div>
                <div id="uvChart" style="font-size: 30px">{{newCustomerForm.data}}</div>
              </div>
              <div>
                <span>日新增 180</span>
              </div>
            </el-col>
            <el-col :span="8" :offset="4">
              <div>
                <i style="font-size: 50px" class="el-icon-s-custom"></i>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>活动PV</span>
            <!--<el-button icon="el-icon-set-up" style="float: right; padding: 3px 0" type="text">切换</el-button>
            <el-select v-model="newPageViewForm.title" size="mini" clearable
                       style="float: right;width: 85px;padding: 0 0">
              <el-option v-for="item in newPageViewFormData" :key="item.value"
                         :label="item.label" :value="item.value"></el-option>
            </el-select>-->
          </div>
          <el-row :gutter="20">
            <el-col :span="12">
              <div>
                <div id="pvChart" style="font-size: 30px">{{newPageViewForm.data}}</div>
              </div>
              <div>
                <span>转化率{{conversionRate}}</span>
              </div>
            </el-col>
            <el-col :span="8" :offset="4">
              <div>
                <i style="font-size: 50px" class="el-icon-view"></i>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>新订单</span>
            <!--<el-button icon="el-icon-set-up" style="float: right; padding: 3px 0" type="text">切换</el-button>-->
            <el-select v-model="newOrderForm.title" size="mini" clearable @change="newOrderChange"
                       style="float: right;width: 85px;padding: 0 0" @clear="newOrderClear">
              <el-option v-for="item in newOrderFormData" :key="item.value"
                         :label="item.label" :value="item.value"></el-option>
            </el-select>
          </div>
          <el-row :gutter="20">
            <el-col :span="12">
              <div>
                <div id="orderChart" style="font-size: 30px">{{newOrderForm.data}}</div>
              </div>
              <div>
                <span>日新增 6</span>
              </div>
            </el-col>
            <el-col :span="8" :offset="4">
              <div>
                <i style="font-size: 50px" class="el-icon-document"></i>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-card>
          <div style="width:100%;height:500px" ref="chart"></div>
        </el-card>
      </el-col>
    </el-row>
    <!-- <el-row :gutter="20">
      <el-col :span="8">
        <el-card>
          <div style="width: 100%;height: 300px" ref="chart_one"></div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div style="width: 100%;height: 300px" ref="chart_two"></div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div style="width: 100%;height: 300px" ref="chart_three"></div>
        </el-card>
      </el-col>
    </el-row> -->

    <el-dialog title="处理报价" :visible.sync="offerExamineDialog"
               @close="offerExamineDialogClose" width="70%">
      <el-form :model="offerExamineForm" size="mini" label-width="80px"
               ref="offerExamineRef" label-position="right">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="主题" prop="offerTheme">
              <el-tag>{{offerExamineForm.offerTheme}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="单号" prop="offerNumbers">
              <el-tag>{{offerExamineForm.offerNumbers}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="offerStatus">
              <el-tag>{{offerExamineForm.offerStatus | offerStatusFormat}}</el-tag>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="联系人">
              <el-tag>{{contactsName}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="销售机会">
              <el-tag>{{saleName}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="负责人">
              <el-tag>{{empName}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="金额">
              <el-tag>{{amountMoney}}</el-tag>
            </el-form-item>
          </el-col>
        </el-row>
        <el-table :data="offerExamineForm.offerDetailResp">
          <el-table-column label="产品名称" prop="productName"></el-table-column>
          <el-table-column label="产品品牌" prop="productBrand"></el-table-column>
          <el-table-column label="产品型号" prop="productModel"></el-table-column>
          <el-table-column label="产品单价" prop="productPrice"></el-table-column>
          <el-table-column label="产品数量" prop="offerDetailCount"></el-table-column>
          <el-table-column label="金额" prop="amountMoney"></el-table-column>
        </el-table>
      </el-form>
      <span slot="footer">
        <el-button @click="offerExamineDialog = false">取消</el-button>
        <el-button type="primary" :loading="offerExamineLoading"
                   @click="offerExamineClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {offerHttp} from "../../network/pre_sale/offer";
  import {getMonth, homeChange} from "../../common/sanYiDate";
  import {homeHttp} from "../../network/home";
  import {newClueFormData,newCustomerFormData,newPageViewFormData,newOrderFormData} from '../../common/data/home_data'
  import {Percentage} from "../../common/sanYiDate";

  export default {
    name: "Index",
    data() {
      return {
        offerExamineList:[],
        offerExamineDialog:false,
        offerExamineLoading:false,
        offerExamineForm:{},
        contactsName:'',
        saleName:'',
        empName:'',
        amountMoney:0,

        newClueForm:{
          title:'本周',
          data:'',
          startDate:getMonth("s",0),
          endDate:getMonth("e",0)
        },
        newClueFormData:newClueFormData,
        clueCount:0,//所有线索的数量
        conversionRate:'',//转化率
        newCustomerForm:{
          title:'本周',
          data:'',
          startDate:getMonth("s",0),
          endDate:getMonth("e",0)
        },
        newCustomerFormData:newCustomerFormData,
        newPageViewForm:{
          title:'本周',
          data:'',
          startDate:getMonth("s",0),
          endDate:getMonth("e",0)
        },
        newPageViewFormData:newPageViewFormData,
        newOrderForm:{
          title:'本周',
          data:'',
          startDate:getMonth("s",0),
          endDate:getMonth("e",0)
        },
        newOrderFormData:newOrderFormData
      }
    },
    mounted() {
      this.initCharts()
      this.initChartOne()
      this.initChartTwo()
      this.initChartThree()
    },
    methods:{
      newClueClear() {
        this.newClueForm.title = 3
        this.newClueChange(3)
      },
      newClueChange(value) {
        let clueChange = homeChange(value);
        var strings;
        if (clueChange != null) {
          strings = clueChange.split(",");
          this.newClueForm.startDate = strings[0];
          this.newClueForm.endDate = strings[1];
        }
        homeHttp.getNewClue(this.newClueForm).then(res => {
          this.newClueForm.data = res.data
        })
      },
      newCustomerClear() {
        this.newCustomerForm.title = 3
        this.newCustomerChange(3)
      },
      newCustomerChange(value) {
        let clueChange = homeChange(value);
        var strings;
        if (clueChange != null) {
          strings = clueChange.split(",");
          this.newCustomerForm.startDate = strings[0];
          this.newCustomerForm.endDate = strings[1];
        }
        homeHttp.getNewCustomer(this.newCustomerForm).then(res => {
          this.newCustomerForm.data = res.data
        })
      },
      newOrderClear() {
        this.newOrderForm.title = 3
        this.newOrderChange(3)
      },
      newOrderChange(value) {
        let clueChange = homeChange(value);
        var strings;
        if (clueChange != null) {
          strings = clueChange.split(",");
          this.newOrderForm.startDate = strings[0];
          this.newOrderForm.endDate = strings[1];
        }
        homeHttp.getNewOrder(this.newOrderForm).then(res => {
          this.newOrderForm.data = res.data
        })
      },

      initNewClueForm() {
        homeHttp.getNewClue(this.newClueForm).then(res => {
          this.newClueForm.data = res.data
        })
        homeHttp.getClueCount().then(res => {
          var data = res.data;
          this.conversionRate = Percentage(data,this.newPageViewForm.data);
        })
      },
      initNewCustomer() {
        homeHttp.getNewCustomer(this.newCustomerForm).then(res => {
          this.newCustomerForm.data = res.data
        })
      },
      initPageView() {
        homeHttp.getPageView().then(res => {
          this.newPageViewForm.data = res.data
        })
      },
      initNewOrder() {
        homeHttp.getNewOrder(this.newOrderForm).then(res => {
          this.newOrderForm.data = res.data
        })
      },

      offerExamineClick() {
        this.offerExamineLoading = true
        offerHttp.offer_examine(this.offerExamineForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.offerExamineLoading = false
            this.offerExamineDialog = false
            this.initOfferExamineList()
          } else {
            this.$message.error(res.message)
            this.offerExamineLoading = false
          }
        })
      },
      offerExamineDialogClose() {
        this.$refs.offerExamineRef.resetFields()
        this.offerExamineLoading = false
        this.contactsName = ''
        this.saleName = ''
        this.empName = ''
        this.amountMoney = 0
      },
      handleExamine(offerId) {
        this.offerExamineDialog = true
        offerHttp.getOffer(offerId).then(res => {
          this.offerExamineForm = res.data
          this.contactsName = res.data.contactsResp.contactsName
          this.saleName = res.data.saleResp.saleName
          this.empName = res.data.empResp.empName
          for (let i=0;i<res.data.offerDetailResp.length;i++) {
            this.amountMoney = parseFloat(res.data.offerDetailResp[i].amountMoney) + parseFloat(this.amountMoney)
          }
          this.amountMoney = this.amountMoney.toFixed(2)
        })
      },
      initOfferExamineList() {
        offerHttp.listExamine().then(res => {
          this.offerExamineList = res.data
        })
      },

      initCharts() {
        let myChart = this.$echarts.init(this.$refs.chart);
        myChart.setOption({
          title: {
            text: '服装分类销售统计展示图'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#6a7985'
              }
            }
          },
          legend: {
            data: ["男装", "女装", "儿童装", "学生装", "其他"]
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              boundaryGap: false,
              data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [
            {
              name: '男装',
              type: 'line',
              stack: '总量',
              areaStyle: {},
              data: [120, 132, 101, 134, 90, 230, 210]
            },
            {
              name: '女装',
              type: 'line',
              stack: '总量',
              areaStyle: {},
              data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
              name: '儿童装',
              type: 'line',
              stack: '总量',
              areaStyle: {},
              data: [150, 232, 201, 154, 190, 330, 410]
            },
            {
              name: '学生装',
              type: 'line',
              stack: '总量',
              areaStyle: {},
              data: [320, 332, 301, 334, 390, 330, 320]
            },
            {
              name: '其他',
              type: 'line',
              stack: '总量',
              label: {
                normal: {
                  show: true,
                  position: 'top'
                }
              },
              areaStyle: {},
              data: [820, 932, 901, 934, 1290, 1330, 1320]
            }
          ]
      })
      },
      initChartOne() {
        let myChartOne = this.$echarts.init(this.$refs.chart_one);
        myChartOne.setOption({
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 10,
            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: ['50%', '70%'],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '30',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: [
                {value: 335, name: '直接访问'},
                {value: 310, name: '邮件营销'},
                {value: 234, name: '联盟广告'},
                {value: 135, name: '视频广告'},
                {value: 1548, name: '搜索引擎'}
              ]
            }
          ]
        })
      },
      initChartTwo() {
        let myChartTwo = this.$echarts.init(this.$refs.chart_two);
        myChartTwo.setOption({
          title: {
            text: '南丁格尔玫瑰图',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            left: 'center',
            top: 'bottom',
            data: ['rose1', 'rose2', 'rose3', 'rose4', 'rose5', 'rose6', 'rose7', 'rose8']
          },
          toolbox: {
            show: true,
            feature: {
              mark: {show: true},
              dataView: {show: true, readOnly: false},
              magicType: {
                show: true,
                type: ['pie', 'funnel']
              },
              restore: {show: true},
              saveAsImage: {show: true}
            }
          },
          series: [
            {
              name: '半径模式',
              type: 'pie',
              radius: [20, 110],
              center: ['50%', '50%'],
              roseType: 'radius',
              label: {
                show: false
              },
              emphasis: {
                label: {
                  show: true
                }
              },
              data: [
                {value: 10, name: 'rose1'},
                {value: 5, name: 'rose2'},
                {value: 15, name: 'rose3'},
                {value: 25, name: 'rose4'},
                {value: 20, name: 'rose5'},
                {value: 35, name: 'rose6'},
                {value: 30, name: 'rose7'},
                {value: 40, name: 'rose8'}
              ]
            },
          ]
        })
      },
      initChartThree() {
        let chartThree = this.$echarts.init(this.$refs.chart_three);
        chartThree.setOption({
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(220, 220, 220, 0.8)'
            }
          }]
        })
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      }
    },
    created() {
      this.initOfferExamineList()
      this.initNewCustomer()
      this.initPageView()
      this.initNewOrder()
      this.$nextTick(()=>{this.initNewClueForm()})
    }
  }
</script>

<style scoped>
  .el-row{
    margin-bottom: 20px;
  }
</style>
