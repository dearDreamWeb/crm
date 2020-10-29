<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form :v-model="searchForm">
            <el-form-item label="名称">
              <el-select v-model="searchForm.userId" size="mini" placeholder="请选择名称">
                <el-option v-for="item in empList" :key="item.empId" :label="item.empName" :value="item.empId"></el-option>

              </el-select>
            </el-form-item>
            <el-form-item prop="logTitle" label="标题">
              <el-input v-model="searchForm.logTitle" placeholder="请输入标题"></el-input>
            </el-form-item>
            <el-form-item prop="logId" label="id">
              <el-input v-model="searchForm.logId" placeholder="请输入id"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="advancedQueryClick">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>

          <el-col :span="18">
            <el-timeline size="mini" :model="listForm" @row-click="handleRowClick" v-loading="tableLoading">
              <el-timeline-item timestamp="" placement="top">
                <el-card>
                  <p type="index" width="50"></p>
                  <p v-model="listForm.logTitle" label="标题"></p>
                  <p v-model="listForm.userId" label="操作人id"></p>
                  <p v-model="listForm.logMethod" label="方法"></p>
                  <p v-model="listForm.logContent" label="操作内容"></p>
                  <p v-model="listForm.logIp" label="ip"></p>
                  <p v-model="listForm.logUri" label="uri"></p>
                  <p v-model="listForm.createTime" label="时间"></p>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </el-col>
      </el-row>

    </el-card>
  </div>
</template>

<script>
    import {syslogHttp} from "../../network/system/syslog";
    import {userHttp} from "../../network/system/user";

    export default {
      data(){
        return{
          empList:[],
            searchForm:{
              userId:'',
              logTitle:'',
              logId:'',

            },
          logTitle:'',
          listForm:[],
          tableLoading:false,
        }
      },
      methods:{
        initEdpList(){
          userHttp.list().then(res =>{
            this.empList = res.data.list
          })
        },
        advancedQueryClick(){
          syslogHttp.queryEmp(this.searchForm).then(res =>{
            if (res.code ===20000){
              this.listForm = res.data.list
            }
          })
        },
        handleRowClick(row,event,column) {
          this.rowEmpId = row.logId
          if (this.rowEmpId != 0) {
            this.buttonDisabled = false
          }
        },
        created(){
          this.initEdpList()
        }
      }
    }
</script>

<style scoped>

</style>
