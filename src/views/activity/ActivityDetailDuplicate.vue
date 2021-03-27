<!--
@author: pengjia
@date: 2020/11/11
@description:
-->
<template>
  <div>
    <el-card>
      <div slot="header">
        <span style="font-size: 20px">活动详情</span>
      </div>
      <div>
        <el-row>
          <el-col :offset="1">
            <el-form :model="activityForm" size="mini"
                     label-width="100px" label-position="right">
              <el-form-item label="活动名称：" prop="activityTitle">
                {{activityForm.activityTitle}}
              </el-form-item>
              <el-form-item label="活动内容：" prop="content">
                {{activityForm.content}}
              </el-form-item>
              <el-form-item label="活动链接：" prop="activityLink">
                {{activityForm.activityLink}}
              </el-form-item>
              <el-form-item label="访问量：" prop="views">
                <el-tag type="success">{{activityForm.views}}</el-tag>
              </el-form-item>
              <el-form-item label="创建人：" prop="createBy">
                {{activityForm.createBy}}
              </el-form-item>
              <el-form-item label="活动时间：">
                {{activityForm.startTime}}~{{activityForm.endTime}}
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-table :data="activityDetailList">
              <el-table-column type="index" width="100px"></el-table-column>
              <el-table-column prop="url" label="专属链接"></el-table-column>
              <el-table-column prop="empName" label="所属员工" width="130px"></el-table-column>
              <el-table-column prop="authDate" label="分配日期" width="130px">
                <template slot-scope="scope">
                  {{scope.row.authDate | dateFormat}}
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
  import {activityHttp} from "../../network/pre_sale/activity";

  export default {
    name: "ActivityDetailDuplicate",
    data() {
      return {
        activityId:'',
        activityForm:{},
        activityDetailList:[]
      }
    },
    methods:{
      initActivity() {
        activityHttp.getActivity(this.activityId).then(res => {
          this.activityForm = res.data
          this.activityDetailList = res.data.activityDetailResps
        })
      }
    },
    created() {
      this.activityId = this.$urlUtil.getQueryVariable("activityId")
      // console.log(this.activityId)
      this.initActivity()
    }
  }
</script>

<style scoped>

</style>
