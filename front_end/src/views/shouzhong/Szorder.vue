<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" size="mini" placeholder="请输入主题进行查询" clearable>
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button size="mini" type="primary" icon="el-icon-plus" @click="openAddDialog">添加订单</el-button>
          <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button size="mini" type="primary" icon="el-icon-refresh" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditOrder">修改订单</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delOrder">删除订单</el-button>
        </el-col>
      </el-row>

      <transition name="el-zoom-in-top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px;">
          <el-form :model="searchForm" ref="advancedSearchFormRef"
                   size="mini" label-position="right" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item prop="ordTheme" label="主题">
                  <el-input v-model="searchForm.ordTheme" size="mini" placeholder="请输入主题" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item prop="ordHead" label="负责人">
                  <el-input v-model="searchForm.ordHead" size="mini" placeholder="请输入负责人" clearable></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-form-item label="选择日期段">
                  <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" type="date" style="width: 46%"
                                  placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item prop="ordState" label="订单状态">
                  <el-select v-model="searchForm.ordState" clearable>
                    <el-option label="执行中" value="0"></el-option>
                    <el-option label="已完成" value="1"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item>
                  <el-button size="mini" @click="advancedQueryClick"
                             type="primary" icon="el-icon-search"></el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </transition>

      <el-table :data="listForm" border style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading">
        <el-table-column type="index" width="45"></el-table-column>
        <el-table-column prop="ordTheme" label="主题"></el-table-column>
        <!--<el-table-column prop="ordTotalmoney" label="总金额"></el-table-column>-->
        <el-table-column prop="ordStarttime" label="开始时间" sortable width="150">
          <template slot-scope="scope">
            {{scope.row.ordStarttime | dateFormat}}
          </template>
        </el-table-column>
        <!--<el-table-column prop="ordDealtime" label="成交时间" sortable>
          <template slot-scope="scope">
            {{scope.row.ordDealtime | dateFormat}}
          </template>
        </el-table-column>-->
        <el-table-column prop="customerResp.cusName" label="客户"></el-table-column>
        <el-table-column prop="ordState" label="订单状态" width="140">
          <template slot-scope="scope">
            {{scope.row.ordState | ordStateFormat}}
          </template>
        </el-table-column>
        <!--<el-table-column prop="ordState" label="订单状态" sortable></el-table-column>-->
        <el-table-column prop="ordHead" label="负责人" width="140"></el-table-column>
        <el-table-column label="操作" sortable>
          <template slot-scope="scope">
            <el-button type="text" @click="chakan(scope.row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
      <el-button  @click="anniu">按钮</el-button>
    </el-card>

    <el-dialog title="订单" :visible.sync="dialogTableVisible" width="65%" top="70px">
      <el-card shadow="always" class="card">
      <div class="mingxi">
        <span>主题：{{xiangqing.ordTheme}}</span>
        <span>订单号： {{xiangqing.ordId}}</span>
        <span>负责人：{{xiangqing.ordHead}}</span>
        <span>订单状态
              <i v-if="xiangqing.ordState == 1">已完成</i>
              <i v-if="xiangqing.ordState == 0">执行中</i>
            </span>
        <span>对应客户：{{xiangqing.cusId}}</span>
      </div>
    </el-card>
      <el-tabs type="border-card" >
        <el-tab-pane label="订单明细">
<!--          <div  v-for="x in szorder">
           <div v-for="n in x.szOrderDetails">
             {{n.odetId}}
            </div>
        </div>-->
          <el-table :data="Detail" border>
            <el-table-column prop="odetId" label="详情编号"></el-table-column>
            <el-table-column prop="productId" label="产品编号" ></el-table-column>
            <el-table-column prop="productReq.productName" label="1产品名称"></el-table-column>
            <el-table-column prop="productName" label="2产品名称">
<!--             <div  v-for="x in szorder.productReq">
                {{x}}
             </div>&ndash;&gt;
              <template slot-scope="scope">
                {{scope.row.szOrderDetails[0].productName}}
              </template>-->
            </el-table-column>
            <el-table-column prop="odetBuynum" label="数量">
              <template slot-scope="scope">
                {{scope.row.odetBuynum}} 件
              </template>
            </el-table-column>
            <el-table-column prop="odetBuymoney" label="单价">
              <template slot-scope="scope">
                {{scope.row.odetBuymoney}} 元
              </template>
            </el-table-column>
            <el-table-column label="金额">
              <template slot-scope="scope">
                {{scope.row.odetBuynum * scope.row.odetBuymoney}} 元
              </template>
            </el-table-column>
          </el-table>
          <div>
            <span>总金额:{{xiangqing.ordTotalmoney}}</span>
          </div>
        </el-tab-pane>
        <el-tab-pane label="关于回款">
          <el-table :data="abPlan" border>
            <el-table-column prop="planId" label="计划回款编号" width="150"></el-table-column>
            <el-table-column prop="planPeriod" label="回款期次" width="150"></el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="关于发货">
          <el-table :data="abdeliver" >
            <el-table-column prop="delId" label="发货编号"></el-table-column>
            <el-table-column prop="delCompany" label="物流公司" >
              <template slot-scope="scope">
                {{scope.row.delCompany | delCompanyFormat}}
              </template>
            </el-table-column>
            <el-table-column prop="delWuliuid" label="物流单号" ></el-table-column>
            <el-table-column prop="delActualtime" label="发货时间">
              <template slot-scope="scope">
                {{scope.row.delActualtime | dateFormat}}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>

    <el-dialog title="订单添加" width="65%" top="20px" :visible.sync="addDialog" @close="addHandleClose">
      <el-form :model="addForm" label-width="80px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-row>
          <el-col :span="8">
            <el-form-item label="主题" prop="ordTheme">
              <el-input v-model="addForm.ordTheme" size="medium" placeholder="请输入主题" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8" width="600px">
            <el-form-item label="客户" size="medium" style="margin-top: 4px">
              <el-select v-model="addForm.cusId" placeholder="请选择客户" >
                <el-option v-for="item in cusList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="总金额" prop="ordTotalmoney" >
              <el-input v-model="zj" size="medium" :disabled="true"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>

          <el-col :span="8">
            <el-form-item label="收货人" prop="ordConsignee">
              <el-input v-model="addForm.ordConsignee" size="medium" placeholder="请填写收货人姓名" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机号" prop="ordPhone">
              <el-input v-model="addForm.ordPhone" size="medium" placeholder="请填写收货人电话" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="地址" size="mini" style="margin-top: 6px">
              <area-cascader style="margin-bottom: 3px" type='text' v-model="selected2" :level='1' :data="pcaa" @change="areaCascaderChange"></area-cascader>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="详细地址" prop="ordDetail">
              <el-input v-model="addForm.ordDetail" size="medium" placeholder="请完善详细地址" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-popover
        placement="right"
        width="650" heigth="350px"
        trigger="click":visible.sync="fenyetwoDialog">

        <el-input v-model="searchInputpro" size="mini" placeholder="请根据产品名称进行查询" clearable>
          <el-button @click="searchInputClickpro" slot="append" icon="el-icon-search"></el-button>
        </el-input>

        <el-table :data="gridData" :row-style="{height:'2px'}"
                  :cell-style="{padding:'5px 0'}" height="300px">
          <el-table-column width="80" property="productBrand" label="品牌" ></el-table-column>
          <el-table-column width="220" property="productName" label="产品名称" ></el-table-column>
          <el-table-column property="productModel" label="型号"></el-table-column>
          <el-table-column property="productStock" label="库存"></el-table-column>
          <el-table-column property="productPrice" label="价格"></el-table-column>
          <el-table-column width="70" label="操作" >
            <template slot-scope="scope">
              <el-button type="text" size="small" icon="el-icon-plus" @click="addpro(scope.row)" :disabled="isDisable"></el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页2-->
        <el-pagination background
                       @current-change="handleCurrentChange1"
                       :current-page="kuaun.pageNum" :page-sizes="[1,2,5,10]"
                       :page-size="kuaun.pageSize" :total="kuaun.total"
                       layout="prev, pager, next, jumper, total">
        </el-pagination>

        <el-button slot="reference" icon="el-icon-plus" type="primary" @click="choosepro">选择产品</el-button>
      </el-popover>
      <h3>购物车</h3>
      <el-table :data="addproplus" style="text-align: center;">
        <el-table-column width="100" property="productBrand" label="产品品牌"></el-table-column>
        <el-table-column property="productName" label="产品名称" ></el-table-column>
        <el-table-column property="productModel" label="产品型号"></el-table-column>
        <el-table-column width="180" property="productNumber" label="数量" >
          <template slot-scope="scope">
          <el-input-number
            size="mini"
            v-model.number="scope.row.productNumber"
            :max="scope.row.max"
            :min="1"
            :step="1" @change="ProNumderChange(scope.row)">1</el-input-number>
          </template>
        </el-table-column>
        <el-table-column width="100" property="productPrice" label="产品单价"></el-table-column>
        <el-table-column width="100" property="odetBuymoney" label="操作" >
          <template slot-scope="scope">
            <el-button type="text" @click="delpro(scope.row.productId)">删除</el-button>
          </template>
          <!-- @click="chakan(scope.row.ordId)-->
        </el-table-column>
      </el-table>
      <span>总金额: {{zj.toFixed(2)}}</span>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
       <!-- <el-button @click="developClick" :loading="developButtonLoading">制定回款</el-button>-->
        <el-button type="primary" @click="addOrderClick"
                   :loading="addOrderButtonLoading">制定回款</el-button>
      </span>
    </el-dialog>

    <!--制定回款-->
    <el-dialog title="制定回款" :visible.sync="addPlanDialog">
      <el-form :model="addPlanForm" label-width="80px" ref="addPlanFormRef"
               label-position="right">
        <el-row>
          <el-col :span="11">
            <el-form-item label="分期(可选)" :data="xuanfenqi">
              <el-select v-model="addPlanForm.planPeriod" placeholder="请选择期次"  @change="fenqi" clearable>
                <el-option label="全款" value="1"></el-option>
                <el-option label="分3期(免手续费)" value="3"></el-option>
                <el-option label="分6期(免手续费)" value="6"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="总金额">
              <el-input v-model="addPlanForm.ordTotalmoney" size="medium"/>
            </el-form-item>
          </el-col>

        </el-row>
      </el-form>
      <!-- 分期表格-->
      <el-table :data="addPlan" style="text-align: center;" size="small" align="center">
        <el-table-column label="期次" align="center" width="140px">
          <template slot-scope="s">
            第{{s.row.recordPlan}}期
          </template>
        </el-table-column>
        <el-table-column prop="timePlan" label="还款时间" align="center"></el-table-column>
        <el-table-column prop="moneyPlan" label="还款金额" align="center"></el-table-column>
      </el-table>
      <span slot="footer">
        <el-button @click="addPlanDialog = false">取消</el-button>
        <el-button type="primary" @click="addPlanClick"
                   :loading="addPlanButtonLoading">立即制定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="订单修改" :visible.sync="editDialog" @close="editHandleClose">
      <el-form :model="editForm" label-width="100px" :rules="editformRules"
               label-position="right" ref="editFormRef">
        <el-row >
          <el-col :span="12">
            <el-form-item label="主题" prop="ordTheme">
              <el-input v-model="editForm.ordTheme" size="medium"/>
            </el-form-item>
          </el-col>
          <!--<el-col :span="12">
            <el-form-item label="负责人" prop="ordHead">
              <el-input v-model="editForm.ordHead" size="mini"/>
            </el-form-item>
          </el-col>-->
          <el-col :span="12">
            <el-form-item label="客户">
              <el-select v-model="editForm.cusId" :disabled="true">
                <el-option style="width:215px" v-for="item in cusList" :key="item.cusId"
                           :label="item.cusName" :value="item.cusId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="总金额" prop="ordTotalmoney">

              <el-input v-model="editForm.ordTotalmoney" size="medium" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="订单状态" prop="ordState">
              <el-radio-group v-model="editForm.ordState" :disabled="true" >
                <el-radio :label=0>执行中</el-radio>
                <el-radio :label=1>已完成</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="收货人" prop="ordConsignee">
              <el-input v-model="editForm.ordConsignee" size="medium" placeholder="请填写收货人姓名" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="ordPhone">
              <el-input v-model="editForm.ordPhone" size="medium" placeholder="请填写收货人电话" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="地址">
              <area-cascader type='text' v-model="selected2" :level='1' :data="pcaa" @change="areaCascaderChange"></area-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="详细地址" prop="ordDetail">
              <el-input v-model="editForm.ordDetail" size="medium" clearable/>
            </el-form-item>
          </el-col>
        </el-row>
        <!--<el-row :gutter="24">
          <el-col :span="12">
            <el-form-item label="开始时间" prop="ordStarttime">
              <el-date-picker v-model="editForm.ordStarttime" size="mini"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>-->
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editOrderClick"
                   :loading="editOrderButtonLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {orderHttp} from "../../network/system/order";
  import {pca,pcaa} from 'area-data'
  import {customerHttp} from "../../network/pre_sale/customer";
  import {productHttp} from "../../network/system/product";
  import {planHttp} from "../../network/system/plan";
  import {deliverHttp} from "../../network/system/deliver";

  export default {
    name: "Order",
    data() {
      let checkMobile = (rule,value,cb) => {
        const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
        if (regMobile.test(value)) {
          return cb()
        }
        cb(new Error('请输入合法的手机号'))
      }
      return {
        input: '',
        searchInput:'',
        selected2:['湖南省','株洲市','荷塘区'],
        pca:pca,
        pcaa:pcaa,
        gridData:[],
        isDisable:false,
        searchInputpro:'',
        editForm:{
        },
        searchForm:{
          ordTheme:'',
          ordHead:'',
          ordStarttime:'',
          ordState:'',
          ordProvince:'',
          ordCity:'',
          ordCountry:'',
          ordDetail:'',
          pageNum:1,
          pageSize:10
        },
        fenyetwoDialog:false,
        addDialog:false,
        addPlanDialog:false,
        rowordId: 0,
        tableLoading:false,
        buttonDisabled:true,
        advancedSearch:false,
        listForm:[],
        numpro:1,
        pageNum:1,
        pageSize:10,
        total:1,
/*产品分页*/
        kuaun:{
          pageNum:1,
          pageSize:5,
          total:0,
        },

        addForm: {
          ordTheme:'',
          ordHead:'',
          ordTotalmoney:'',
          ordConsignee:'',
          ordPhone:'',
          ordProvince:'',
          ordCity:'',
          ordCountry:'',
          ordDetail:'',
          cusId: '',
          szReceivableRecorde:[]
        },
        addPlanForm:{
        },
        formRules:{
          ordTheme:[
            {required:true,message:'请输入主题',trigger:'blur'},
            {min:3,max:12,message:'长度在3~12个字符之间'}
          ],
          ordHead:[
            {required:true,message:'请选择客户,还没改ordHead',trigger:'blur'}
          ],
          ordConsignee:[
            {required:true,message:'请输入收货人姓名',trigger:'blur'},
            {min:1,max:20,message:'请输入有效名字'}
          ],
          ordPhone:[
            {required:true,message:'请输入手机号',trigger:'blur'},
            {validator:checkMobile}
          ],
          ordDetail:[
            {required:true,message:'请填写详细地址',trigger:'blur'},
            {min:1,max:20,message:'请将详细地址填写完成'}
          ]
        },
        editformRules:{
        },
        addOrderButtonLoading:false,
        developButtonLoading:false,
        addPlanButtonLoading:false,
        editDialog:false,
        editOrderButtonLoading:false,
        dialogTableVisible:false,
        szrecord:[],
        /*szproduct:[],*/
        szorder:[],
        Detail:[],
        abPlan:[],
        abdeliver:[],
        NeedProduct:[],
        SeqProduct:[],
        cusList:[],
        addproplus: [],
        addPlan:[],
        xuanfenqi:[],
        totalmoney:'',
        activeName: 'first',
        oid:0,
        xiangqing:{}
      }
    },
    methods: {
      /*算钱change*/
      ProNumderChange(row){
      /*  /!*productNumber=this.product*!/
        console.log('数量:',row.productNumber)
        console.log('1价格:',row.productNumber * row.productPrice)
        row.totalmoney=row.productNumber * row.productPrice
        console.log("产品总金额：",row.totalmoney)
       /!* this.zj = this.zj+ row.productPrice
        console.log(this.zj);*!/
       let totalzj = 0;
        this.addproplus.forEach(value => {
          totalzj=totalzj + value.totalmoney
        })
        this.addForm.ordTotalmoney = totalzj
        this.zj=totalzj
        console.log("添加产品后总价：",this.zj)*/
      },
      delpro(index,row){
        var index = this.addproplus.indexOf(row)
        this.addproplus.splice(index,1)
        let totalzj = 0;
        this.addproplus.forEach(value => {
          totalzj=totalzj + value.totalmoney
        })
        this.addForm.ordTotalmoney = totalzj
        this.zj=totalzj
        console.log("删除产品后总价：",this.zj)
      },
      areaCascaderChange() {
        console.log("di",this.selected2)
      },
      advancedQueryClick() {
        orderHttp.list(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
      searchInputClick() {
        this.listForm.ordTheme = this.searchInput
        orderHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      searchInputClickpro() {
        this.gridData.productName=this.searchInputpro
        productHttp.list(this.gridData).then(res => {
          this.gridData = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowordId = 0
        this.buttonDisabled = true
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editOrderButtonLoading = false
      },
      addHandleClose(){
        /*添加订单*/
        this.$refs.addFormRef.resetFields()
        this.addForm.cusIdList = []
        this.addOrderButtonLoading = false
      },
      /*选择产品*/
      choosepro(){
        this.fenyetwoDialog = true
        productHttp.listDialog(this.kuaun).then(res=>{
          this.gridData = res.data.list
          this.kuaun.total = res.data.total
          this.kuaun.pageNum = res.data.pageNum
        })
      },
      addpro(row){
        this.addDialog=true;
        let pro={
          productId:row.productId,
          productBrand:row.productBrand,
          productName:row.productName,
          productModel:row.productModel,
          productPrice:row.productPrice,
          max:row.productStock,
          productNumber:1
        }
        //判断产品是否已经存在，如果存在，数量累加，否则新增选择的商品信息
        let result = this.addproplus.filter(p=>{return p.productId==pro.productId});
        if(result && result.length>0){
          if(result[0].productNumber==row.productStock){
            alert("没有更多库存了")
          }else{
            //产品已经选择，修改产品的数量
            result[0].productNumber= result[0].productNumber+1;
          }
        }else{
          this.addproplus.push(pro)
        }
        console.log(this.addproplus)
      },
      AddProPage(pageIndex){
        this.pageNum = pageIndex
        this.pageSize = this.pageSize
        productHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      openEditOrder(){
        /*修改订单*/
        this.getOrderDetail()
      },
      delOrder(ordId){
        /*删除订单*/
        this.$confirm('确定删除此订单吗','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          ordId = this.rowordId
          orderHttp.delOrder(ordId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
            } else {
              this.$message({
                message:res.message,
                type:'error'
              })
            }
          })
        })
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        this.pageSize = this.pageSize
        orderHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
      /*产品分页*/
      handleCurrentChange1(pageIndex) {
        this.kuaun.pageNum=pageIndex
        productHttp.listDialog(this.kuaun).then(res => {
          this.gridData = res.data.list
          this.kuaun.total = res.data.total
          this.kuaun.pageNum = res.data.pageNum
        })
      },
      chakan(val){
        this.dialogTableVisible = true;
        let id=val.ordId;
        this.xiangqing=val;
        orderHttp.szxiangq(id).then(res=>{
          this.szorder=res;
          this.Detail=res[0].szOrderDetails;
          this.abPlan.splice(0,0,res[0].szReceivablePlan)
          this.abdeliver.splice(0,0,res[0].szDeliver)
          console.log("all查看详情:",res)
          console.log("订单，查看详情：",res[0].szOrderDetails);
          console.log("回款，查看详情：",res[0].szReceivablePlan);
          console.log("发货，查看详情：",res[0].szDeliver);
        })
      },
      getOrderDetail() {
        orderHttp.getOrder(this.rowordId).then(res => {
          var order = res.data;
          if(order.ordState==1){
            alert("已完成中的订单不允许修改....");
            return;
          }
          this.editDialog = true;
          this.editForm = res.data
        })
      },
      openAddDialog() {
        this.addDialog = true
      },
      handleRowClick(row,event,column) {
        this.rowordId= row.ordId
        if (this.rowOrdId != 0) {
          this.buttonDisabled = false
        }
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      },
      anniu(){
        //alert(this.$store.state.empName)
        this.addPlanDialog=true;
      },
      /*选择分期*/
      fenqi(){
        this.addPlan=[];
        console.log("1分期",this.addPlanForm.planPeriod)
        let it = this.addPlanForm.planPeriod; //选中的x期次
        let recordplan=0;
        let money=0; //前x-1期
        let qici=1;
        let qc=[];
        let yumoney=0;
        var nowDate = new Date();//时间
        for(var i=1; i<=it;i++){
          var date = {
            year: nowDate.getFullYear(),
            month: nowDate.getMonth() + 1 +i,
            day: nowDate.getDate(),
          }
          if (date.month>12){
            date.year = date.year+1
            date.month=date.month-12
          }
          if (date.day<10){
            date.day="0"+date.day
          }
          //分期小于循环
          if(this.addPlanForm.planPeriod<=i){
            yumoney= parseFloat(this.zj - money);
            recordplan=yumoney;
            console.log("最后一期："+yumoney);
            console.log("recordplan",recordplan)
            this.addPlan.splice(i,0,{recordPlan:i,timePlan:date.year + '-' + ("0" + (date.month)).slice(-2) + '-' + date.day ,moneyPlan:recordplan.toFixed(2)})
            return false;
          }
          money+=parseInt(this.zj/this.addPlanForm.planPeriod);
          console.log("金额：",money);
          console.log("第",i,"次循环");
          console.log("this.zj",this.zj);
          recordplan=parseInt(this.zj/this.addPlanForm.planPeriod);
          qici++;
          qc.push(qici);
          this.addPlan.splice(i,0,{recordPlan:i,timePlan:date.year+ '-'  + ("0" + (date.month)).slice(-2) + '-'  + date.day , moneyPlan:recordplan.toFixed(2)})

        }
      },
      /*制定回款*/
      developClick(val){
        /*判断是否完善订单信息*/
        if(this.addproplus.length>0){
          this.addPlanDialog=true;
        }else{
          this.$message({
            message: '请先完善订单信息',
            type: 'warning'
          });
        }
      },
      /*制定回款的确认按钮*/
      addPlanClick(){
          this.addPlanForm.szReceivableRecorde=this.addPlan
          console.log("确定添加：",this.addPlanForm)
          console.log("this.addPlan：",this.addPlanForm.szReceivableRecorde);
          this.addPlanForm.ordId=this.oid;
          console.log("jjj",this.addPlanForm.ordId);
          console.log("kkk",this.oid);
          //获取当前登录用户 给 操作人
          this.addPlanForm.planCaozuopeople=this.$store.state.empName;

          console.log("this.addPlanForm.planCaozuopeople",this.addPlanForm.planCaozuopeople)
          console.log("操作人",this.$store.state.empName)
        //ordHead:this.$store.state.empName,
        console.log("this.addPlanForm:",this.addPlanForm)
          planHttp.addplan(this.addPlanForm).then(res => {
            if (res.code === 20000) {
              console.log("rrr",this.addPlanForm);
              console.log("O：",res);
              console.log("Oid：",res.data);
              let ppp={
                ordId : this.addPlanForm.ordId
              }
              planHttp.plan_editOrder(ppp).then(res=>{
                console.log(res.data)
              })
              this.addPlanDialog = false;
              this.addDialog = false;
            }
          })
        /*修改订单的回款状态*/

      },
    /*新增订单（选择产品同时新增订单详情）*/
      addOrderClick(){
        if(this.addproplus.length>0){
          console.log("this.addproplu:",this.addproplus)
          var addDetail = [];
          /*通过循环得到已选取产品的主键、价格、数量*/
          for(var i=0,n=this.addproplus.length;i<n;i++){
            console.log("addproplus中价格:",this.addproplus[i].productPrice)
            var json={
              productId:this.addproplus[i].productId,
              odetBuynum:this.addproplus[i].productNumber,
              odetBuymoney:this.addproplus[i].productPrice,
            }
            addDetail.push(json)
          }
          console.log(json)
          console.log("addDetail:",addDetail)
          /*给文本框绑定需要的值*/
          let ppp={
            ordTheme:this.addForm.ordTheme,
            ordHead:this.$store.state.empName,
            ordTotalmoney:this.zj,
            ordConsignee:this.addForm.ordConsignee,
            ordProvince:this.selected2[0],
            ordCity:this.selected2[1],
            ordCountry:this.selected2[2],
            ordDetail:this.addForm.ordDetail,
            ordPhone:this.addForm.ordPhone,
            cusId:this.addForm.cusId,
            szOrderDetails:addDetail,
            ordPlan : 0
          }
          console.log(" ordTotalmoney:", ppp.ordTotalmoney)
          console.log(" this.zj:", this.zj)
          this.addOrderButtonLoading = true;
           orderHttp.addOrder(ppp).then(res => {
              if (res.code === 20000) {
                this.$message.success(res.message)
                this.initList()
                console.log("O：",res);
                console.log("Oid：",res.data)
                this.oid=res.data;
                this.addPlanForm.ordTotalmoney="";
                this.addPlanForm.ordTotalmoney=this.zj;
                this.addPlanDialog=true;
                this.addOrderButtonLoading = false;
                let id = this.oid,
                  $this = this;
                const h = this.$createElement;
                console.log(id);
              } else {
                this.$message({
                  message:res.message,
                  type:"warning"
                })
              }
            })
        }else{
          this.$message({
            message: '请先完善订单信息',
            type: 'error'
          });
        }
      },
/*      //点击事件回调
      defineCallBack() {
        console.log("传递过来的值是：",this.oid);
        console.log(val.ordId)
      },*/
      /*修改订单*/
      editOrderClick(){
        this.editForm.ordProvince=this.selected2[0];
        this.editForm.ordCity = this.selected2[1];
        this.editForm.ordCountry=this.selected2[2];
        console.log("1"+this.selected2)
        this.editOrderButtonLoading = true
        this.editForm.ordId = this.rowordId
        window.console.log(this.editForm)
        orderHttp.editOrder(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editOrderButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editOrderButtonLoading = false
            window.console.log(this.editForm)
          }
            window.console.log(this.editForm)
        }
        )
      },
      initCusList() {
        customerHttp.listAll().then(res => {
          this.cusList = res.data
        })
      },
      initList() {
        orderHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
        })
      },
    },
    created() {
      this.initList()
      this.initCusList()
    },
    /*计算属性*/
    computed:{
      zj(){
         //循环所选商品的数据集合
        let total=0;
        const totalCount = this.addproplus .reduce((total, cur, i, arr) => {
          var count = cur.productNumber*cur.productPrice;
          return total + count;
        }, 0);
        return totalCount;
      }
    }
  }
</script>

<style scoped>

  .area-select .area-selected-trigger{
    padding: 1px 20px 7px 12px;
  }
  .el-select .el-input__inner{
    height: 29px;
    font-size: 12px;
    width: 200px;
  }
  .el-select-dropdown .el-popper{
    min-width: 400px;
  }

  .mingxi{
    margin: 10px 5px;
  }
  .mingxi span{
    padding: 5px 25px;
    color: #909399;
  }
  .card{
    margin-bottom: 20px;
  }
</style>
