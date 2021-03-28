<!--
@author: pengjia
@date: 2020/10/15
@description:
-->
<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="searchInput" placeholder="请输入名称查询" clearable size="mini">
            <el-button @click="searchInputClick" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="9">
         <el-button type="primary" icon="el-icon-plus" size="mini" @click="addDialog = true">添加线索</el-button>
          <el-button type="primary" icon="el-icon-zoom-in" size="mini"
                     @click="advancedSearch = !advancedSearch">高级查询</el-button>
          <el-button type="primary" icon="el-icon-refresh"
                     size="mini" @click="resetForm"></el-button>
        </el-col>
        <el-col :span="9">
          <el-button type="warning" size="mini" icon="el-icon-edit"
                     :disabled="buttonDisabled" @click="openEditDialog">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     :disabled="buttonDisabled" @click="delClue">删除</el-button>
          <el-button type="info" size="mini" icon="el-icon-view"
                     :disabled="buttonDisabled" @click="getClueDetailButton">详情</el-button>
          <!--<el-button type="success" size="mini" icon="el-icon-edit-outline"
                     :disabled="this.multipleClueIdList.length == 0"
                     @click="openClueTypeEdit">批量</el-button>-->
        </el-col>
      </el-row>

      <transition name="el-zoom-in:top">
        <el-card class="advanced_search" v-show="advancedSearch" style="margin-top: 10px">
          <el-form :model="searchForm" ref="advancedSearchFormRef" size="mini" label-width="80px">
            <el-row>
              <el-col>
                <el-form-item label="高级搜索"></el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="线索手机">
                  <el-input v-model="searchForm.cluePhone" size="mini" placeholder="请输入手机号" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="处理人">
                  <el-select v-model="searchForm.handlePerson" size="mini" clearable>
                    <el-option v-for="item in empList" :key="item.empId"
                               :label="item.empName" :value="item.empName">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="处理结果">
                  <el-select v-model="searchForm.handleResult" clearable size="mini">
                    <el-option label="有效" :value="1"></el-option>
                    <el-option label="无效" :value="0"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="10">
                <el-form-item label="创建时间">
                  <el-date-picker v-model="searchForm.startDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                  type="date" style="width: 46%" placeholder="请输入"></el-date-picker>
                  <span>-</span>
                  <el-date-picker v-model="searchForm.endDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                  type="date" style="width: 46%" placeholder="请输入"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="线索状态">
                  <el-select v-model="searchForm.clueStatus" clearable size="mini">
                    <el-option label="已处理" :value="1"></el-option>
                    <el-option label="未处理" :value="0"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item>
                  <el-button size="mini" icon="el-icon-switch-button"
                             @click="advancedSearch = !advancedSearch"></el-button>
                  <el-button size="mini" @click="advancedQueryClick"
                             type="primary" icon="el-icon-search"></el-button>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="活动" prop="activityId">
                  <el-select v-model="searchForm.activityId" clearable size="mini">
                    <el-option v-for="item in clueSearchActivityList" :key="item.activityId"
                               :label="item.activityTitle" :value="item.activityId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="员工" prop="empId">
                  <el-select v-model="searchForm.empId" clearable size="mini">
                    <el-option v-for="item in empList" :key="item.empId"
                               :label="item.empName" :value="item.empId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </transition>

      <el-table :data="listForm" style="width: 100%;margin-top: 10px;margin-bottom: 10px"
                :header-row-style="iHeaderRowStyle" :header-cell-style="iHeaderCellStyle"
                highlight-current-row @row-click="handleRowClick" v-loading="tableLoading"
                border @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="50" :selectable="checkSelectTable"></el-table-column>
        <el-table-column prop="clueName" label="线索名称"></el-table-column>
        <el-table-column prop="cluePhone" label="联系方式"></el-table-column>
        <el-table-column prop="clueStatus" label="线索状态" width="120px">
          <template slot-scope="scope">
            {{scope.row.clueStatus | clueStatusFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="handleResult" label="处理结果" width="120px">
          <template slot-scope="scope">
            {{scope.row.handleResult | clueHandleResultFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="handlePerson" label="处理人" width="120px"></el-table-column>
        <el-table-column prop="createTime" label="提交时间" width="120px">
          <template slot-scope="scope">
            {{scope.row.createTime | dateFormat}}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="70px" align="center">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="处理" placement="top">
              <el-button type="text" icon="el-icon-thumb" size="medium"
                         @click="manipulateClueClick(scope.row.clueId)">
              </el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination background @current-change="handleCurrentChange"
                     :current-page="pageNum" :page-sizes="[1,2,5,10]"
                     :page-size="pageSize" :total="total"
                     layout="prev, pager, next, jumper, total">
      </el-pagination>
    </el-card>

    <el-dialog title="线索添加" :visible.sync="addDialog" @close="addHandleClose" width="30%">
      <el-form :model="addForm" label-width="80px" ref="addFormRef"
               label-position="right" :rules="formRules">
        <el-form-item label="线索名称" prop="clueName">
          <el-input v-model="addForm.clueName" size="mini" placeholder="请输入" clearable></el-input>
        </el-form-item>
        <el-form-item label="线索号码" prop="cluePhone">
          <el-input v-model="addForm.cluePhone" size="mini" placeholder="请输入" clearable></el-input>
        </el-form-item>
        <el-form-item label="处理人" prop="handlePerson">
          <el-select v-model="addForm.handlePerson" size="mini" clearable>
            <el-option v-for="item in empList" :key="item.empId"
                       :label="item.empName" :value="item.empName">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="addDialog = false">取消</el-button>
        <el-button type="primary" @click="addClueClick"
                   :loading="addClueButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="线索修改" :visible.sync="editDialog" @open="openBefore"
               width="30%" @close="editHandleClose">
      <el-form :aria-modal="editForm" label-width="80px"
               label-position="right" ref="editFormRef">
        <el-form-item>
          <el-tag type="warning" size="mini">只能修改通过弹框添加的线索</el-tag>
          <el-tag type="warning" size="mini">线索修改时，给线索所属员工下发修改通知</el-tag>
        </el-form-item>
        <el-form-item label="线索名称" prop="clueName">
          <el-input v-model="editForm.clueName" size="mini" clearable></el-input>
        </el-form-item>
        <el-form-item label="线索号码" prop="cluePhone">
          <el-input v-model="editForm.cluePhone" size="mini" clearable></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="editDialog = false">取消</el-button>
        <el-button type="primary" @click="editClueClick"
                   :loading="editClueButtonLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="线索详情" :visible.sync="clueDetailDialog">
      <el-form :model="clueDetail" label-position="right" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="线索名称">
              <el-tag>{{clueDetail.clueName}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系方式">
              <el-tag>{{clueDetail.cluePhone}}</el-tag>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="线索状态">
              <el-tag v-if="clueDetail.clueStatus === 1">已处理</el-tag>
              <el-tag v-else>未处理</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="处理结果">
<!--              <el-tag v-if="clueDetail.handleResult === 1">有效</el-tag>-->
<!--              <el-tag v-if="clueDetail.handleResult == null">暂未处理</el-tag>-->
<!--              <el-tag v-else>无效</el-tag>-->
              <el-tag>{{clueDetail.handleResult | clueHandleResultFormat}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="提交时间">
              <el-tag>{{clueDetail.createTime | dateFormat}}</el-tag>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="处理人">
              <el-tag v-if="clueDetail.handlePerson == null">暂未处理</el-tag>
              <el-tag v-else>{{clueDetail.handlePerson}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来源">
              <el-tag v-if="clueDetail.activityId == null">手动添加</el-tag>
              <el-tag v-else>{{clueDetail.activityResp.activityTitle}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属员工">
              <el-tag v-if="clueDetail.empId == null">手动添加</el-tag>
              <el-tag v-else>{{clueDetail.empResp.empName}}</el-tag>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="备注">
              <el-tag v-if="clueDetail.remark == null">暂无备注</el-tag>
              <el-tag v-else>{{clueDetail.remark}}</el-tag>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>

    <el-dialog title="采集信息" center :visible.sync="clueManipulateDialog"
               width="30%" top="55px" @close="manipulateFormClose">
      <el-form :model="manipulateForm" label-width="100px" label-position="right">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="数据所有人">
              <el-tag>{{manipulateForm.empName}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-button type="text" icon="el-icon-share"
                       @click="clueShare">共享</el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="text" icon="el-icon-s-promotion"
                       @click="openTransferDialog">转移</el-button>
          </el-col>
        </el-row>
        <el-form-item label="来源">
          <el-tag>{{manipulateForm.activityTitle}}</el-tag>
        </el-form-item>
        <el-form-item label="归属">
          <el-tag>{{manipulateForm.empName}}</el-tag>
        </el-form-item>
        <el-form-item label="提交时间">
          <el-tag>{{manipulateForm.createTime | dateTimeFormat}}</el-tag>
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="状态">
              <el-tag>{{manipulateForm.clueStatus | clueStatusFormat}}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结果">
              <el-tag v-if="manipulateForm.handleResult == null">暂未处理</el-tag>
              <el-tag v-else>{{manipulateForm.handleResult}}</el-tag>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-tooltip class="item" effect="dark" content="转为客户" placement="top">
              <el-button type="warning" icon="el-icon-s-custom" circle
                         @click="openCustomerDialog"
                         :disabled="this.manipulateForm.clueStatus === 1"></el-button>
            </el-tooltip>
          </el-col>
          <!--<el-col :span="6">
            <el-tooltip class="item" effect="dark" content="加入代办" placement="top">
              <el-button type="success" icon="el-icon-timer" circle></el-button>
            </el-tooltip>
          </el-col>
          <el-col :span="6">
            <el-tooltip class="item" effect="dark" content="转成订单" placement="top">
              <el-button type="primary" icon="el-icon-tickets" circle></el-button>
            </el-tooltip>
          </el-col>-->
          <el-col :span="12">
            <el-tooltip class="item" effect="dark" content="设为无效" placement="top">
              <el-button type="danger" icon="el-icon-error" circle
                         @click="invalidClueClick"
                         :disabled="this.manipulateForm.handleResult != null">
              </el-button>
            </el-tooltip>
          </el-col>
        </el-row>
      </span>
    </el-dialog>

    <el-dialog title="更改线索类型" :visible.sync="editClueTypeDialog" width="30%">
      <el-form>
        <el-form-item label="线索类型：" label-width="120px">
          <el-radio v-model="clueBatchEditTypeForm.clueType" :label="0">个人线索</el-radio>
          <el-radio v-model="clueBatchEditTypeForm.clueType" :label="1">公司线索</el-radio>
        </el-form-item>
        <span>提交后不可修改</span>
      </el-form>
      <span slot="footer">
        <el-button @click="editClueTypeDialog = false">取消</el-button>
        <el-button type="primary" @click="editClueTypeClick"
                   :loading="batchEditTypeLoading">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="新建客户" :visible.sync="clueTurnCustomerDialog"
               top="30px" @close="addCusAndConFormClose">
      <el-form :model="addCusAndConForm" label-width="80px" label-position="right"
               ref="addCusAndConFormRef" :rules="addCusAndConFormRules">
        <el-row>
          <el-col :span="24">
            <el-form-item label="公司名称" prop="cusName">
              <el-select v-model="addCusAndConForm.cusName" style="width: 550px"
                         filterable allow-create @change="transferCusChange">
                <el-option v-for="item in companyList" :key="item.clueId"
                           :label="item.clueName" :value="item.clueName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="公司简称" prop="abbreviation">
              <el-input v-model="addCusAndConForm.abbreviation"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名" prop="contactsName">
              <el-input  v-model="addCusAndConForm.contactsName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="手机" prop="contactsPhone">
              <el-input v-model="addCusAndConForm.contactsPhone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源" prop="cusDictSource">
              <el-select v-model="addCusAndConForm.cusDictSource"
                         :disabled="cusDisabled">
                <el-option v-for="item in dictList.children" :key="item.dictId"
                           :label="item.dictName" :value="item.dictId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="QQ" prop="qq">
              <el-input v-model="addCusAndConForm.qq"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="微信" prop="wechat">
              <el-input v-model="addCusAndConForm.wechat"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="备注" prop="cusRemark">
              <el-input v-model="addCusAndConForm.cusRemark" type="textarea"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button size="mini" @click="clueTurnCustomerDialog = false">取消</el-button>
        <el-button type="primary" size="mini" :loading="clueTurnCustomerClickLoading"
                   @click="clueTurnCustomerClick">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="转移客户" :visible.sync="transferCustomerDialog" width="25%"
               @close="transferDialogClose">
      <el-card>
        <el-form :model="transferCustomerForm" ref="transferCustomerRef">
          <el-form-item label="转移给：" prop="empId">
            <el-select v-model="transferCustomerForm.empId"
                       clearable placeholder="请选择">
              <el-option v-for="item in empList" :key="item.empId"
                         :label="item.empName" :value="item.empId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </el-card>
      <span slot="footer">
        <el-button @click="transferCustomerDialog = false">取消</el-button>
        <el-button type="primary" :loading="transferLoading"
                   @click="transferCustomerClick">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {clueHttp} from "../../network/pre_sale/clue";
  import {userHttp} from "../../network/system/user";
  import {activityHttp} from "../../network/pre_sale/activity";
  import {dictHttp} from "../../network/system/dict";
  import {customerHttp} from "../../network/pre_sale/customer";

  export default {
    name: "Clue",
    data() {
      let checkMobile = (rule,value,cb) => {
        const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
        if (regMobile.test(value)) {
          return cb()
        }
        cb(new Error('请输入合法的手机号'))
      }
      let checkQQ = (rule,value,cb) => {
        const regQQ = /^[1-9][0-9]{4,11}$/
        if (regQQ.test(value)) {
          return cb()
        }
        cb(new Error('请输入合法的QQ号'))
      }
      let checkWeChat = (rule,value,cb) => {
        const regWeChat = /^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$/
        if (regWeChat.test(value)) {
          return cb()
        }
        cb(new Error('请输入合法的微信号'))
      }
      return {
        cusDisabled:false,

        transferLoading:false,
        transferCustomerForm:{
          clueId:'',
          empId:'',
          transferId:''
        },
        dialogTransferId:'',
        transferCustomerDialog:false,

        clueTurnCustomerClickLoading:false,
        dictList:[],

        companyList:[],
        clueTurnCustomerDialog:false,
        addCusAndConForm:{
          clueId:'',
          empId:'',
          cusName:'',
          abbreviation:'',
          cusDictSource:'',
          cusRemark:'',
          contactsName:'',
          contactsPhone:'',
          qq:'',
          wechat:''
        },
        addCusAndConFormRules:{
          cusName:[
            {required:true,message:'请输入公司名称',trigger:'blur'}
          ],
          contactsName:[
            {required:true,message:'请输入名称',trigger:'blur'}
          ],
          contactsPhone:[
            {required:true,message:'请输入手机号',trigger:'blur'},
            {validator:checkMobile}
          ],
          qq:[
            {required:true,message:'请输入QQ号',trigger:'blur'},
            {validator:checkQQ}
          ],
          wechat:[
            {required:true,message:'请输入微信号',trigger:'blur'},
            {validator:checkWeChat}
          ]
        },

        batchEditTypeLoading:false,
        clueBatchEditTypeForm:{
          clueIdList:[],
          clueType:0,
          handlePerson:''
        },
        multipleClueIdList:[],
        clueTypeRadio:0,
        editClueTypeDialog:false,


        manipulateForm:{},
        clueManipulateDialog:false,

        clueDetail:{},
        clueDetailDialog:false,

        editForm:{},
        editClueButtonLoading:false,
        editDialog:false,
        editFormRules:{
          clueName:[
            {required:true,message:'请输入账号',trigger:'blur'},
          ],
          cluePhone:[
            {required:true,message:'请输入手机号',trigger:'blur'},
            {validator:checkMobile}
          ]
        },

        addForm:{
          clueName:'',
          cluePhone:'',
          handlePerson:''
        },
        addDialog:false,
        formRules:{
          clueName:[
            {required:true,message:'请输入账号',trigger:'blur'},
          ],
          cluePhone:[
            {required:true,message:'请输入手机号',trigger:'blur'},
            {validator:checkMobile}
          ]
        },
        addClueButtonLoading:false,
        empList:[],

        clueSearchEmpList:[],
        clueSearchActivityList:[],
        searchInput:'',
        searchForm:{
          clueName:'',
          cluePhone:'',
          clueStatus:'',
          handleResult:'',
          handlePerson:'',
          activityId:'',
          empId:'',
          startDate:'',
          endDate:''
        },

        listForm:[],
        buttonDisabled:true,
        rowClueId:0,
        pageNum:1,
        pageSize:10,
        total:1,
        tableLoading:false,
        advancedSearch:false
      }
    },
    methods: {
      transferCusChange(value) {
        this.listForm.cusName = value
        customerHttp.listByCusName(value).then(res => {
          this.addCusAndConForm.abbreviation = res.data.abbreviation
          this.addCusAndConForm.cusDictSource = res.data.cusDictSource
          this.addCusAndConForm.cusRemark = res.data.cusRemark
          this.cusDisabled = true
        })
      },
      invalidClueClick() {
        this.$confirm('请确认是否执行此操作','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          clueHttp.invalidClue(this.rowClueId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.clueManipulateDialog = false
            } else {
              this.$message.error(res.message)
              this.clueManipulateDialog = false
            }
          })
        })
      },
      clueShare() {
        this.$confirm('请再三考虑是否执行此操作','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          clueHttp.shareClue(this.rowClueId).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.clueManipulateDialog = false
            } else {
              this.$message.error(res.message)
              this.clueManipulateDialog = false
            }
          })
        })
      },
      openTransferDialog() {
        clueHttp.get(this.rowClueId).then(res => {
          if (res.code === 80033) {
            this.$message.error(res.message)
            this.transferCustomerDialog = false
          } else {
            this.dialogTransferId = res.data.empId
            this.transferCustomerDialog = true
          }
        })

      },
      transferCustomerClick() {
        this.$confirm('请再三考虑是否执行此操作','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          this.transferCustomerForm.clueId = this.manipulateForm.clueId
          this.transferCustomerForm.transferId = this.$store.state.empId
          this.transferLoading = true
          clueHttp.transferCustomer(this.transferCustomerForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.transferCustomerDialog = false
              this.transferLoading = false
              this.initList()
            } else {
              this.$message({
                message:res.message,
                type:'error'
              })
              this.transferLoading = false
            }
          })
        })
      },
      transferDialogClose() {
        this.$refs.transferCustomerRef.resetFields()
      },

      clueTurnCustomerClick() {
        this.clueTurnCustomerClickLoading = true
        this.addCusAndConForm.clueId = this.manipulateForm.clueId
        customerHttp.addCusAndCon(this.addCusAndConForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.clueTurnCustomerDialog = false
            this.clueTurnCustomerClickLoading = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.clueTurnCustomerClickLoading = false
          }
        })
      },
      initDictList() {
        dictHttp.tree_dict_byId(2).then(res => {
          this.dictList = res.data
        })
      },
      listCompanyMethod() {
        clueHttp.listCompany().then(res => {
          this.companyList = res.data
        })
        /*customerHttp.listAll().then(res => {
          this.companyList = res.data
        })*/
      },
      openCustomerDialog() {
        this.clueTurnCustomerDialog = true
        this.listCompanyMethod()
        this.initDictList()
      },
      addCusAndConFormClose() {
        this.$refs.addCusAndConFormRef.resetFields()
        this.clueTurnCustomerClickLoading = false
        this.addCusAndConForm.cusName = ''
        this.addCusAndConForm.contactsName = ''
        this.addCusAndConForm.contactsPhone = ''
      },
      manipulateFormClose() {
        this.initList()
      },

      checkSelectTable(row,index) {
        if (row.clueType == null) {
          return true
        } else {
          return false
        }
      },
      handleSelectionChange(val) {
        this.multipleClueIdList = []
        for (let i=0;i<val.length;i++) {
          if (this.multipleClueIdList.indexOf(val[i].clueId === -1)) {
            this.multipleClueIdList.push(val[i].clueId)
          }
        }
        console.log(this.multipleClueIdList)
      },
      editClueTypeClick() {
        if (this.multipleClueIdList.length > 0) {
          this.batchEditTypeLoading = true
          this.clueBatchEditTypeForm.clueIdList = this.multipleClueIdList
          this.clueBatchEditTypeForm.handlePerson = this.$store.state.empName
          clueHttp.batchEditClueType(this.clueBatchEditTypeForm).then(res => {
            if (res.code === 20000) {
              this.batchEditTypeLoading = false
              this.editClueTypeDialog = false
              this.initList()
              this.$message.success(res.message)
            } else {
              this.$message({
                message:res.message,
                type:'error'
              })
              this.batchEditTypeLoading = false
            }
          })
        }
      },
      openClueTypeEdit() {
        this.editClueTypeDialog = true
      },
      manipulateClueClick(clueId) {
        this.rowClueId = clueId
        this.clueManipulateDialog = true
        clueHttp.getDetail(clueId).then(res => {
          this.manipulateForm = res.data
          this.manipulateForm.empName = res.data.empResp.empName
          this.manipulateForm.activityTitle = res.data.activityResp.activityTitle
          this.addCusAndConForm.empId = this.manipulateForm.empId
          if (this.manipulateForm.clueType == 0 || this.manipulateForm.clueType == null) {
            this.addCusAndConForm.contactsName = this.manipulateForm.clueName
            this.addCusAndConForm.contactsPhone = this.manipulateForm.cluePhone
          }
        })
      },

      getClueDetail() {
        clueHttp.getDetail(this.rowClueId).then(res => {
          this.clueDetail = res.data
        })
      },
      getClueDetailButton() {
        /*this.clueDetailDialog = true
        this.getClueDetail()*/
        let resolve = this.$router.resolve({
          path:'/clue_detail',
          query:{
            clueId:this.rowClueId
          }
        });
        window.open(resolve.href,'_blank')
      },

      initActivityList() {
        activityHttp.listNotArgs().then(res => {
          this.clueSearchActivityList = res.data
        })
      },

      delClue(clueId) {
        this.$confirm('此操作将删除改线索，是否继续？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(() => {
          clueId = this.rowClueId
          clueHttp.del(clueId).then(res => {
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

      openBefore() {
        clueHttp.get(this.rowClueId).then(res => {
          if (res.code === 20000) {
            this.editDialog = true
            this.editForm = res.data
          } else {
            this.editDialog = false
            this.$message({
              message:res.message,
              type:'error'
            })
          }
        })
      },
      editHandleClose() {
        this.$refs.editFormRef.resetFields()
        this.editClueButtonLoading = false
      },
      editClueClick() {
        this.editClueButtonLoading = true
        this.editForm.clueId = this.rowClueId
        clueHttp.edit(this.editForm).then(res => {
          if (res.code === 20000) {
            this.$message.success(res.message)
            this.initList()
            this.editClueButtonLoading = false
            this.editDialog = false
          } else {
            this.$message({
              message:res.message,
              type:'error'
            })
            this.editClueButtonLoading = false
          }
        })
      },
      openEditDialog() {
        this.editDialog = true
      },

      addHandleClose() {
        this.$refs.addFormRef.resetFields()
        this.addClueButtonLoading = false
      },
      addClueClick() {
        this.$refs.addFormRef.validate(valid => {
          if (!valid) return
          this.addClueButtonLoading = true
          clueHttp.add(this.addForm).then(res => {
            if (res.code === 20000) {
              this.$message.success(res.message)
              this.initList()
              this.addClueButtonLoading = false
              this.addDialog = false
            } else {
              this.$message({
                message:res.message,
                type:"error"
              })
            }
          })
        })
      },

      resetForm() {
        this.$refs.advancedSearchFormRef.resetFields()
        this.searchInput = ''
        this.initList()
        this.rowClueId = 0
        this.buttonDisabled = true
      },
      advancedQueryClick() {
        clueHttp.list(this.searchForm).then(res => {
          if (res.code === 20000) {
            this.listForm = res.data.list
            this.total = res.data.total
            this.pageNum = res.data.pageNum
          }
        })
      },
      searchInputClick() {
        this.tableLoading = true
        this.listForm.clueName = this.searchInput
        clueHttp.list(this.listForm).then(res => {
          this.listForm = res.data.list
          this.total = res.data.total
          this.pageNum = res.data.pageNum
          this.tableLoading = false
        })
      },

      initEmpList() {
        userHttp.list().then(res => {
          this.empList = res.data.list
        })
      },
      initList() {
        this.tableLoading = true
        clueHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
          this.tableLoading = false
        })
      },
      handleRowClick(row,event,column) {
        this.rowClueId = row.clueId
        if (this.rowClueId != 0) {
          this.buttonDisabled = false
        }
      },
      handleCurrentChange(pageIndex) {
        this.pageNum = pageIndex
        clueHttp.listPage(this.pageNum,this.pageSize).then(res => {
          this.listForm = res.data.list
          this.pageNum = res.data.pageNum
          this.total = res.data.total
        })
      },
      iHeaderRowStyle:function({row,rowIndex}){
        return 'height:20px'
      },
      iHeaderCellStyle:function({row,column,rowIndex,columnIndex}){
        return 'padding:5px'
      }
    },
    created() {
      this.initList()
      this.initEmpList()
      this.initActivityList()
    }
  }
</script>

<style scoped>

</style>
