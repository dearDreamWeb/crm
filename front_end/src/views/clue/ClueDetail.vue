<!--
@author: pengjia
@date: 2020/11/12
@description:
-->
<template>
  <div>
    <el-card>
      <div slot="header">
        <span>线索详情</span>
      </div>
      <div>
        <el-row>
          <el-col :offset="1">
            <el-form :model="clueDetailForm" size="mini" label-width="180px"
                     label-position="right">
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="线索名称" prop="clueName">
                    {{clueDetailForm.clueName}}
                  </el-form-item>
                  <el-form-item label="线索电话" prop="cluePhone">
                    {{clueDetailForm.cluePhone}}
                  </el-form-item>
                  <el-form-item label="线索状态" prop="clueStatus">
                    {{clueDetailForm.clueStatus | clueStatusFormat}}
                  </el-form-item>
                  <el-form-item label="线索类型" prop="clueType">
                    {{clueDetailForm.clueType | clueTypeFormat}}
                  </el-form-item>
                  <el-form-item label="处理结果" prop="handleResult">
                    {{clueDetailForm.handleResult | clueHandleResultFormat}}
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="处理人" prop="handlePerson">
                    {{clueDetailForm.handlePerson}}
                  </el-form-item>
                  <el-form-item label="创建时间" prop="createTime">
                    {{clueDetailForm.createTime | dateFormat}}
                  </el-form-item>
                  <el-form-item label="备注"  prop="remark">
                    {{clueDetailForm.remark}}
                  </el-form-item>
                  <el-form-item label="来源" prop="activityId">
                    {{clueDetailForm.activityResp.activityTitle}}
                  </el-form-item>
                  <el-form-item label="负责人" prop="empId">
                    {{clueDetailForm.empResp.empName}}
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-col>
        </el-row>
        <el-divider><i class="el-icon-notebook-1"> 跟进日志</i></el-divider>
        <div class="block">
          <el-row>
            <el-col :offset="2">
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
            </el-col>
          </el-row>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
  import {clueHttp} from "../../network/pre_sale/clue";

  export default {
    name: "ClueDetail",
    data() {
      return {
        clueId:'',
        clueDetailForm:{},
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
      initClue() {
        clueHttp.getDetail(this.clueId).then(res => {
          this.clueDetailForm = res.data
        })
      }
    },
    created() {
      this.clueId = this.$urlUtil.getQueryVariable("clueId")
      this.initClue()
    }
  }
</script>

<style scoped>

</style>
