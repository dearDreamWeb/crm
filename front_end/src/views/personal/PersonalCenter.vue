<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card>
          <el-form :v-model="searchForm" size="mini" label-width="80px" label-position="right">
            <el-form-item label="About me" prop="userId">
            </el-form-item>
            <el-divider></el-divider>
            <el-form-item label="" prop="logTitle">
              <el-avatar :size="60" src="https://empty" @error="errorHandler">
                <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
              </el-avatar>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>

      <el-col :span="16">
        <el-card>
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="Activity" name="first">
              <div>
                <span>青春是一个短暂的美梦, 当你醒来时, 它早已消失无踪</span>
                <el-divider></el-divider>
                <span>少量的邪恶足以抵消全部高贵的品质, 害得人声名狼藉</span>
                <span>
                    <el-carousel :interval="4000" type="card" height="200px">
                      <el-carousel-item v-for="item in 6" :key="item">
                        <h3 class="medium">{{ item }}</h3>
                      </el-carousel-item>
                    </el-carousel>
                </span>
              </div>
            </el-tab-pane>
            <el-tab-pane label="Timeline" name="second">
              <el-timeline>
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
            </el-tab-pane>
            <el-tab-pane label="Account" name="third">
              <div>
              <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="密码">
                  <el-input v-model="form.mm"></el-input>
                </el-form-item>
                <el-form-item label="Email">
                  <el-input v-model="form.yx"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">Update</el-button>
                </el-form-item>
              </el-form>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          mm: '',
          yx:'',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
        }
    }
  }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
