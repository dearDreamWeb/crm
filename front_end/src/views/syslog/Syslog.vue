<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form :v-model="searchForm">
            <el-form-item>
              <el-select size="mini" placeholder="请选择分类">
                <el-option label="产品投诉" value="产品投诉"></el-option>
                <el-option label="服务投诉" value="服务投诉"></el-option>
                <el-option label="客户意见" value="客户意见"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="审批人">
              <el-input placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="审批人">
              <el-input placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="审批人">
              <el-input placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="审批人">
              <el-input placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>

          <el-col :span="18">
            <el-timeline size="mini">
              <el-timeline-item timestamp="2018/4/12" placement="top">
                <el-card>
                  <h4>更新 Github 模板</h4>
                  <p>王小虎 提交于 2018/4/12 20:46</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="2018/4/3" placement="top">
                <el-card>
                  <h4>更新 Github 模板</h4>
                  <p>王小虎 提交于 2018/4/3 20:46</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="2018/4/2" placement="top">
                <el-card>
                  <h4>更新 Github 模板</h4>
                  <p>王小虎 提交于 2018/4/2 20:46</p>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </el-col>
      </el-row>

    </el-card>
  </div>
</template>

<script>
    export default {
      data(){
        return{

            searchForm: ''

        }
      }
    }
</script>

<style scoped>

</style>
