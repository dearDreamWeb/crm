<!--
@author: pengjia
@date: 2020/12/4
@description:
-->
<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card>
          <div slot="header" class="clearfix">
            <span>销售机会统计</span>
          </div>
          <div>
            <div style="width: 100%;height: 500px" ref="saleStageCountChartRef"></div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div slot="header" class="clearfix">
            <span>销售机会统计</span>
            <el-select v-model="distribution" clearable placeholder="请选择"
                       size="mini" style="float: right; padding: 3px 0">
              <el-option v-for="item in distributionData" :key="item.value"
                         :label="item.label" :value="item.value"></el-option>
            </el-select>
          </div>
          <div>
            <div style="width: 100%;height: 500px" ref="saleStageChartRef"></div>
          </div>
          {{salestagecount}}
          {{salestagecountname}}
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {distributionSelectData} from '../../common/data/home_data'
  export default {
    name: "SaleChart",
    props:{
      salestagecountname:{
        type: Array,
        required: true
      },
      salestagecount:{
        type: Array,
        required: true
      },
    },
    data() {
      return {
        distribution:'',
        distributionData:distributionSelectData,

        countNameData:[],
        countListData:[],
      }
    },
    mounted() {
      this.initSaleStageCount()
      this.initSaleStageMoney()
    },
    methods:{
      initData() {
        console.log(this.salestagecount)
        this.countNameData = this.salestagecountname
        console.log("AA",this.countNameData)
        for (let i=0;i<this.salestagecount.length;i++) {
          var object = {
            name:this.salestagecount[i].name,
            value:this.salestagecount[i].value
          }
          this.countListData.push(object)
        }
        console.log("BB",this.countListData)
      },
      initSaleStageCount() {
        let saleStageCount = this.$echarts.init(this.$refs.saleStageCountChartRef);
        saleStageCount.setOption({
          title: {
            text: '各阶段机会数量',
          },
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}",
          },
          toolbox: {
            feature: {
              dataView: {readOnly: false},
              restore: {},
              saveAsImage: {}
            }
          },
          legend: {
            data: this.countNameData,
            orient: 'vertical',
            bottom: 'bottom',
          },
          series: [
            {
              name:'漏斗图',
              type:'funnel',
              left: '10%',
              top: 60,
              bottom: 60,
              width: '70%',
              min: 0,
              max: 100,
              minSize: '0%',
              maxSize: '100%',
              sort: 'descending',
              gap: 2,
              label: {
                show: true,
                position: 'right',
              },
              labelLine: {
                length: 10,
                lineStyle: {
                  width: 1,
                  type: 'solid'
                }
              },
              itemStyle: {
                borderColor: '#fff',
                borderWidth: 1
              },
              emphasis: {
                label: {
                  fontSize: 20
                }
              },
              data: this.countListData
            }
          ]
        })
      },
      initSaleStageMoney() {
        let saleStage = this.$echarts.init(this.$refs.saleStageChartRef);
        saleStage.setOption({
          title: {
            text: '分布',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          toolbox: {
            left: 'left',
            feature: {
              dataView: {readOnly: false},
              restore: {},
              saveAsImage: {}
            }
          },
          legend: {
            orient: 'vertical',
            bottom: 'bottom'
          },
          series: [
            {
              name: '分布',
              type: 'pie',
              radius: '50%',
              top: -60,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
              data: [
                {value: 1048, name: '搜索引擎'},
                {value: 735, name: '直接访问'},
                {value: 580, name: '邮件营销'},
                {value: 484, name: '联盟广告'},
                {value: 300, name: '视频广告'}
              ]
            }
          ]
        })
      }
    },
    created() {
      this.initData()
    }
  }
</script>

<style scoped>

</style>
