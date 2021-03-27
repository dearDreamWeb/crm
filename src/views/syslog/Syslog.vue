<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="10">
        <el-row>
          <el-col>
            <el-card>
              <el-form :v-model="searchForm" size="mini" label-width="80px" label-position="right">
                <el-form-item label="操作人" prop="userId">
                  <el-select v-model="searchForm.userId" clearable>
                    <el-option v-for="item in empList" :key="item.empId"
                               :label="item.empName" :value="item.empId">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="标题" prop="logTitle">
                  <el-input v-model="searchForm.logTitle" placeholder="请输入标题"
                            clearable style="width: 60%"></el-input>
                </el-form-item>
                <el-form-item label="开始时间" prop="startDate">
                  <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
                <el-form-item label="结束时间" prop="endDate">
                  <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
                <el-form-item>
                  <el-button icon="el-icon-search" type="primary" @click="searchClick"></el-button>
                </el-form-item>
              </el-form>
            </el-card>
          </el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col>
            <el-card>
              <div style="width: 100%;height: 300px" ref="sysLogRef"></div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>

      <el-col :span="14">
        <el-card>
          <div class="radio">
            排序：
            <el-radio-group v-model="reverse">
              <el-radio :label="true">倒序</el-radio>
              <el-radio :label="false">正序</el-radio>
            </el-radio-group>
          </div>
          <hr>
          <el-timeline :reverse="reverse">
            <el-timeline-item v-for="item in listForm" :key="item.logId"
                              icon="el-icon-more" type="primary" size="large"
                              :timestamp="item.createTime" :hide-timestamp="true">
              日志标题：<el-tag class="timeLineTag" type="success">{{item.logTitle}}</el-tag>
              <br>
              调用方法：<el-tag class="timeLineTag" type="warning">{{item.logMethod}}</el-tag>
              <br>
              操作时间：<el-tag class="timeLineTag" type="info">{{item.createTime | dateTimeFormat}}</el-tag>
              <hr>
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {syslogHttp} from "../../network/system/syslog";
import {userHttp} from "../../network/system/user";

export default {
  data(){
    return{
      listForm:[],
      reverse:true,

      searchForm:{
        userId:'',
        logtjs:'',
        logTitle:'',
        startDate:'',
        endDate:''
      },
      empList:[],

      count: 10,
      loading: false
    }
  },
  mounted() {
    this.initChart()
  },
  methods:{
    initChart() {
      let initChart = this.$echarts.init(this.$refs.sysLogRef);
      initChart.setOption({
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

    searchClick() {
      syslogHttp.search(this.searchForm).then(res => {
        this.listForm = res.data
      })
    },
    initLogList() {
      syslogHttp.list().then(res => {
        this.listForm = res.data
      })
    },
    initEmpList() {
      userHttp.list().then(res => {
        this.empList = res.data.list
      })
    }
  },
  created(){
    this.initLogList()
    this.initEmpList()
  }
}
</script>

<style scoped>
.timeLineTag{
  margin-bottom: 10px;
}
</style>
