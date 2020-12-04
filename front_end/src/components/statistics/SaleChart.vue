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
          <div style="width: 100%;height: 500px" ref="saleStageCountChartRef"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div style="width: 100%;height: 500px" ref="saleStageChartRef"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "SaleChart",
    data() {
      return {

      }
    },
    mounted() {
      this.initSaleStageCount()
      this.initSaleStageMoney()
    },
    methods:{
      initSaleStageCount() {
        let saleStageCount = this.$echarts.init(this.$refs.saleStageCountChartRef);
        saleStageCount.setOption({
          title: {
            text: '各阶段机会数量',
          },
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}%",
          },
          toolbox: {
            feature: {
              dataView: {readOnly: false},
              restore: {},
              saveAsImage: {}
            }
          },
          legend: {
            data: ['展现','点击','访问','咨询','订单'],
            orient: 'vertical',
            bottom: 'bottom',
          },
          series: [
            {
              name:'漏斗图',
              type:'funnel',
              left: '10%',
              top: 60,
              //x2: 80,
              bottom: 60,
              width: '70%',
              // height: {totalHeight} - y - y2,
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
              data: [
                {value: 160, name: '访问'},
                {value: 40, name: '咨询'},
                {value: 32, name: '订单'},
                {value: 80, name: '点击'},
                {value: 100, name: '展现'}
              ]
            }
          ]
        })
      },
      initSaleStageMoney() {
        let saleStage = this.$echarts.init(this.$refs.saleStageChartRef);
        saleStage.setOption({
          title: {
            text: '某站点用户访问来源',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            /*formatter: '{a} <br/>{b} : {c} ({d}%)'*/
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: '50%',
              data: [
                {value: 1048, name: '搜索引擎'},
                {value: 735, name: '直接访问'},
                {value: 580, name: '邮件营销'},
                {value: 484, name: '联盟广告'},
                {value: 300, name: '视频广告'}
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        })
      }
    }
  }
</script>

<style scoped>

</style>
