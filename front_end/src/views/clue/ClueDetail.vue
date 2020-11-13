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
            <el-col :offset="15">
              <div class="radio">
                排序：
                <el-radio-group v-model="reverse">
                  <el-radio :label="false">正序</el-radio>
                  <el-radio :label="true">倒序</el-radio>
                </el-radio-group>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="2">
              <el-timeline :reverse="reverse">
                <el-timeline-item
                  v-for="item in activities"
                  :key="item.clueFollowId"
                  :icon="icon"
                  :size="size"
                  :type="type">
                  操作人：{{item.clueFollowPerson}}<br>
                  操作时间：{{item.clueFollowTime}}<br>
                  操作内容：{{item.clueFollowContent}}
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
  import {dateFormat} from "../../common/formatUtils";

  export default {
    name: "ClueDetail",
    data() {
      return {
        clueId:'',
        clueDetailForm:{},
        activities: [],
        icon:'el-icon-more',
        size: 'large',
        type: 'primary',
        reverse:false
      }
    },
    methods:{
      initClue() {
        clueHttp.getDetail(this.clueId).then(res => {
          this.clueDetailForm = res.data
        })
      },
      initClueFollowList() {
        clueHttp.getFollow(this.clueId).then(res => {
          this.activities = res.data
          for (let i=0;i<this.activities.length;i++) {
            this.activities[i].clueFollowTime =
              dateFormat(this.activities[i].clueFollowTime)
          }
        })
      }
    },
    created() {
      this.clueId = this.$urlUtil.getQueryVariable("clueId")
      this.initClue()
      this.initClueFollowList()
    }
  }
</script>

<style scoped>

</style>
