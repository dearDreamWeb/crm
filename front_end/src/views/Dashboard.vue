<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>本周新客户</span>
          </div>
          <div>
            <div id="userChart" style="font-size: 30px">324</div>
          </div>
          <div>
            <span>日新增</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>本周新线索</span>
          </div>
          <div>
            <div id="uvChart" style="font-size: 30px">5325</div>
          </div>
          <div>
            <span>日新增 180</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>本周PV</span>
          </div>
          <div>
            <div id="pvChart" style="font-size: 30px">96546</div>
          </div>
          <div>
            <span>转化率 78%</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div slot="header">
            <span>本周订单</span>
          </div>
          <div>
            <div id="orderChart" style="font-size: 30px">¥ 645435</div>
          </div>
          <div>
            <span>日新增 6</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="18">
        <el-card>
          <div slot="header">
            <span>动态</span>
          </div>
          <el-row v-for="n in 5" :key="n">
            <el-col :span="20">张三新建了项目<span class="text-primary">Element Admin</span></el-col>
            <el-col :span="4" class="text-info">2020-9-25</el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-row>
          <el-col>
            <el-card>
              <div slot="header">
                <span>热销商品</span>
              </div>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
              <hr>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
              <hr>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
              <hr>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
              <hr>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
              <hr>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
              <hr>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
              <hr>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
              <hr>
              <div class="text-primary">2020联想(Lenovo)小新Ar14锐龙版轻海本14
                <el-tag type="danger" size="mini" class="float-right">¥ 4599</el-tag>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Dashboard"
  }
</script>

<style lang="scss" scoped>
 .el-row{
   margin-bottom: 20px;
   /*&:last-child{
     margin-bottom: 0;
   }*/
 }
</style>
