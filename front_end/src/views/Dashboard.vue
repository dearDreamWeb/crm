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
      <el-col :span="12">
        <el-card>
          <el-table :data="repairList" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                    highlight-current-row v-loading="tableLoading">
            <el-table-column type="index" width="40"></el-table-column>
            <el-table-column prop="customerResp.cusName" label="对应客户"></el-table-column>
            <!--    <el-table-column prop="repairSfzb" label="联系人"></el-table-column>-->
            <el-table-column prop="szOrder.ordPhone" label="手机号码"></el-table-column>
            <el-table-column prop="szOrder.ordConsignee" label="联系人"></el-table-column>
            <!--        <el-table-column prop="deptResp.deptName" label="维修部门"></el-table-column>-->
            <el-table-column prop="szOrder.ordTheme" label="维修产品"></el-table-column>
            <!--    <el-table-column prop="empResp.empName" label="接单人"></el-table-column>-->
            <el-table-column prop="repairsjhm" label="是否在保"></el-table-column>
            <el-table-column prop="empResp.empName" label="维修人"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini"
                           @click="openAddDialog(scope.row.repairId)">付款</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card>
          对于线索的每一个跟进操作，都将操作记录下来
          <hr>
          准备集成企业微信
          <hr>
          线索添加微信或QQ字段，通过企业微信等确认后做下一步转化；
          线索转为个人客户后，直接添加到联系人
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          线索剩余：待办和转订单未完成
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-card>
          任务调度待解决<hr>
          10月15待完成任务：线索模块前后端增删改查
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card header="消息提醒思路">
          在vue加载dom完成后，用create函数请求后台的数据，接收到之后统计消息。然后用element的徽标标记。当用户点击之后调用后台查看消息
          <hr>
          信息过期后人性化提示
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card header="删除时业务">
          删除一条主要数据时，考虑其余表的数据引用，重要时不可删除或更改状态
          <hr>
          使用弹框添加的线索，后续处理（差不多已完善）
          <hr>
          删除线索只能由其所属员工删除
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <!--<el-col :span="18">
        <el-card>
          <div slot="header">
            <span>动态</span>
          </div>
          <el-row v-for="n in 5" :key="n">
            <el-col :span="20">张三新建了项目<span class="text-primary">Element Admin</span></el-col>
            <el-col :span="4" class="text-info">2020-9-25</el-col>
          </el-row>
        </el-card>
      </el-col>-->
      <el-col :span="6">
        <el-row>
          <el-col>
            <el-card>
              <div>
                <e-icon-picker v-model="icon"/>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-card>
              （已完成）页面长时间未操作，清除session和vuex，方式采用前端解决<br>后续考虑走后端
            </el-card>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-card header="实现任务调度的四种方式">
              Timer<br>
              ScheduledExecutor<br>
              开源工具包 Quartz<br>
              开源工具包 JCronTab
              <hr>
              https://developer.ibm.com/zh/articles/j-lo-taskschedule/
              <hr>
              活动分配问题
              <hr>
              活动页面给员工联系电话
            </el-card>
          </el-col>
        </el-row>

        <el-row>
          <el-col>
            <el-card>
              获客页面的表单提交验证，前台后台
            </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-card>
              除了超级管理员账号之外，所有账号不能对敏感按钮做操作
            </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-card>
              后期考虑接口文档：丝袜哥
            </el-card>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="18">
        <el-card header="防止用户多端登录解决方法">
          <p>两种解决方案：
            1、将用户的登录信息用一个标志位的字段保存起来，每次登录成功就标记1，注销登录就标记为0，当标记为1的时候不允许别人登录。

            2、将用户的登录信息保存在application内置作用域内， 然后利用session监听器监听每一个登录用户的登录情况。
          </p>
          <hr>
          <br>
          <p>
            第一种方式 每次登录 都需要操作数据库，多了一些不必要的性能开销，而且在登录状态下 万一突然电脑关闭了，那就永远都不能登录了，
            可用性比较低。
          </p>
          <p>
            第二种方式的具体实现：
            1、在处理登录的login方法中，先查询数据库验证下该用户是否存在，如果存在 判断该登录账户是否已经锁定了，
            然后从application内置作用域对象中取出所有的登录信息，查看该username账户是否已经登录，如果登录了，就友好提示下，
            反之表示可以登录，将该登录信息以键值对的方式保存在application中。
            2、登录入口处理完之后，考虑到会话结束的话，那么对应的登录用户也应该相应的注销登录。我们可以写一个Session监听器，
            监听sessioon销毁的时候，我们将登录的用户注销掉，也就是从application中移除。表示该用户已经下线了。
            3、另外，还有一个问题，如果说登录的用户突然关闭了浏览器或者页面而没有点击退出按钮。那么可以利用beforeunload 事件，
            在浏览器刷新或者关闭的时候触发。
          </p>
          <p>https://blog.csdn.net/chenghui0317/article/details/9373345</p>
        </el-card>

      </el-col>
    </el-row>

<!--    <area-select v-model="selected" :data="pca" @change="areaSelectChange"></area-select>-->
<!--    <area-cascader v-model="selected2" :data="pca" @change="areaCascaderChange"></area-cascader>-->

<!--    <area-select type='all' :level='2' v-model="selected" :data="pcaa" @change="areaSelectChange"></area-select>-->
    <area-cascader type='text' v-model="selected2" :level='1' :data="pcaa" @change="areaCascaderChange"></area-cascader>
    <hr>
    <!--<el-calendar v-model="value">
    </el-calendar>-->
    <hr>
    <el-select v-model="brand" @change="brandChange">
      <el-option v-for="item in productBrandList" :key="item.label"
                 :label="item.value" :value="item.value">
      </el-option>
    </el-select>
    <!--<el-calendar v-model="value">
      <template slot="dateCell" slot-scope="{date, data}">
        <el-row>
          <el-col :span="12">
            <p :class="data.isSelected ? 'is-selected' : ''">
              {{ data.day.split('-').slice(1).join('-') }}
            </p>
            <el-button size="mini" v-if="data.isSelected ? 'is-selected' : ''"
                       type="danger" icon="el-icon-delete" @click="testButton">
            </el-button>
          </el-col>
          <el-col :span="12" v-if="data.isSelected ? 'is-selected' : ''">
            <el-row style="margin-top: 5px">
              <el-col>
                <el-button size="mini" type="primary" icon="el-icon-search"></el-button>
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <el-button size="mini" type="success" icon="el-icon-plus"></el-button>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </template>
    </el-calendar>-->

    <div>
      <h3>wangEditor with vue</h3>
      <div id="demo1"></div>
      <el-button class="btn" @click="getEditorData">获取当前内容</el-button>
    </div>
<!--    <v-btn large color="primary" @click="getEditorData">Primary</v-btn>-->
  </div>
</template>

<script>
  import E from 'wangeditor'
  import productBrand from '../common/data/product_date'
  import {pca,pcaa} from 'area-data'
  import {userHttp} from "../network/system/user";

  export default {
    name: "Dashboard",
    mounted() {
      const editor = new E('#demo1')
      editor.create()
      this.editor = editor
      console.log(this.editor)
      this.getPaidan();
    },
    data(){
      return{
        isClear:false,
        detail:'',
        repairList:[],
        brand:'',
        icon:'',
        tableLoading: false,
        shopId:'',
        edpList:[],
        selected:[],
        selected2:['湖南省','邵阳市','新邵县'],
        pca:pca,
        pcaa:pcaa,
        value: new Date(),
        productBrandList:productBrand.productBrand
      }
    },
    methods:{
      brandChange() {
        console.log("====",this.brand)
      },
      areaCascaderChange() {
        console.log(this.selected2)
      },
      getEditorData() {
        // 通过代码获取编辑器内容
        let data = this.editor.txt.text()
        alert(data)

      },
      //派单信息
      getPaidan(){
        let p = {
          empId: this.$store.state.empId,
          repairGdstate: '已派单'
        }
        this.tableLoading = true
        userHttp.getPaidan(p).then(res => {
          console.log(res.data.list)
          if(res.code == 20000){
            this.repairList = res.data.list;
            this.tableLoading = false
          }
        });
      }
    },

    created() {

    }
  }
</script>

<style lang="scss" scoped>
 .el-row{
   margin-bottom: 20px;
   /*&:last-child{
     margin-bottom: 0;
   }*/
 }
 /* .el-calendar-day{*/
 /*   height: 35px !important;*/
 /* }*/
 /* .el-calendar{*/
 /*   width: 35% !important;*/
 /* }*/
 /*.el-calendar-table td.is-selected{*/
 /*   background-color: brown !important;*/
 /* }*/
</style>
