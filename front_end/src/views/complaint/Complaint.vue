<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="3"><el-select class="ssk" v-model="value" filterable placeholder="全部数据">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="6"><el-input class="ssk2"
                                    placeholder="请输入投诉主题"
                                    v-model="input"
                                    clearable>
        </el-input><div class="grid-content bg-purple-light"></div></el-col>
        <el-button type="primary" icon="el-icon-search">搜索</el-button>
      </el-row>
      <el-row>
        <el-col :span="7">投诉<div class="grid-content bg-purple"></div></el-col>
        <el-col :span="12"><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="2"><el-button type="text" @click="addBtn">新建</el-button><div class="grid-content bg-purple"></div></el-col>
        <el-dialog
          title="投诉"
          :visible.sync="dialogVisible"
          width="80%"
          :before-close="handleClose">
          <el-form ref="form" :model="form" label-width="80px">
            <el-row>
              <el-col :span="8">
                <el-form-item label="对应客户">
                  <el-input v-model="form.gdbh"></el-input>
                </el-form-item>
              </el-col >
              <el-col :span="8">
                <el-form-item label="投诉主题">
                  <el-input v-model="form.khname"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="接待人">
                  <el-input v-model="form.jdr"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="分类">
                  <el-select v-model="form.region" placeholder="请选择分类">
                    <el-option label="产品投诉" value="shanghai"></el-option>
                    <el-option label="服务投诉" value="beijing"></el-option>
                    <el-option label="客户意见" value="beijing"></el-option>
                    <el-option label="其他" value="beijing"></el-option>
                  </el-select>
                </el-form-item>
              </el-col >

              <el-col :span="24">
                <el-form-item label="描述">

                  <el-input type="textarea" v-model="form.desc"></el-input>

                </el-form-item>
              </el-col>

            </el-row>
            <el-form-item label="时间">
              <el-col :span="8">
                <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
              </el-col>

            </el-form-item>
            <el-row>
              <el-col :span="8">

              </el-col >
              <el-form-item label="紧急程度">
                <el-col :span="8">
                  <el-radio-group v-model="radio">
                    <el-radio :label="3">非常紧急</el-radio>
                    <el-radio :label="6">急</el-radio>
                    <el-radio :label="9">普通</el-radio>
                  </el-radio-group>
                </el-col>
              </el-form-item>
              <el-col :span="24">

              </el-col>
              <el-form-item label="处理结果">
                <el-col :span="8">
                  <el-radio-group v-model="radio1">
                    <el-radio :label="3">未处理</el-radio>
                    <el-radio :label="6">处理中</el-radio>
                    <el-radio :label="9">处理完成</el-radio>
                  </el-radio-group>
                </el-col>
              </el-form-item>
              <el-col :span="24">
                <el-form-item label="客户反馈">

                  <el-input type="textarea" v-model="form.desc2"></el-input>

                </el-form-item>
              </el-col>
            </el-row>
            <div style="width: 37%">
              <el-form-item label="投诉人">
                <el-input v-model="form.tsr"></el-input>
              </el-form-item>
            </div>
            <div style="margin-top:10px ">
              <el-form-item label="处理过程">

                <el-input type="textarea" v-model="form.desc1"></el-input>

              </el-form-item>
            </div>
          </el-form>
          <div style="text-align: center;margin-top: 10px">

            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </div>

        </el-dialog>
        <el-button><i class="el-icon-s-grid"></i></el-button>
        <el-button><i class="el-icon-s-tools"></i></el-button>
      </el-row>
      <el-row>
        <el-col :span="0"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="20"><el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            label="ID"
            width="120">
            <template slot-scope="scope">{{ scope.row.date }}</template>
          </el-table-column>
          <el-table-column
            prop="name"
            label="投诉主题"
            width="120">
          </el-table-column>
          <el-table-column
            prop="address"
            label="对应客户"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="address"
            label="费用"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="address"
            label="分类"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="address"
            label="时间"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="address"
            label="紧急程度"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="address"
            label="接待人"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="address"
            label="处理结果"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="address"
            label="投诉人"
            show-overflow-tooltip>
          </el-table-column>
        </el-table><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="0"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="0"><div class="grid-content bg-purple-light"></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="1"><el-button><i class="el-icon-delete"></i></el-button><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="1"><el-button><i class="el-icon-edit"></i></el-button><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="0"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="0"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="4"><div class="block">

          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="40">
          </el-pagination>
        </div><div class="grid-content bg-purple-light"></div></el-col>
      </el-row>
    </el-card>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        options: [{
          value: '选项1',
          label: '接件'
        }, {
          value: '选项2',
          label: '待维修'
        }, {
          value: '选项3',
          label: '维修中'
        }, {
          value: '选项4',
          label: '待支付'
        }, {
          value: '选项5',
          label: '已支付'
        }, {
          value: '选项6',
          label: '全部数据'
        }],
        value: '',
        input: '',
        dialogVisible: false,
        tableData: [],
        form: {
          gdbh:'',
          khname: '',
          jdr:'',
          wtms:'',
          cpbh:'',
          ddbh:'',
          xlh:'',
          tsr:'',
          gzms:'',
          hf:'',
          zb:'',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: '',
          desc1:''
        },
        num: 1,
        radio: 3,
        radio1: 3,
        radio2: 3
      }
    },

    methods:{
      addBtn(){
        this.dialogVisible = true;
        console.log(123)
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      onSubmit() {
        console.log('submit!');
      }
    }
  }
</script>

<style>
  .el-row {
    margin-bottom: 20px;
  }
  :last-child {
    margin-bottom: 0;
  }

  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: white;
  }
  .bg-purple {
    background: white;
  }
  .bg-purple-light {
    background: white;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 10px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
