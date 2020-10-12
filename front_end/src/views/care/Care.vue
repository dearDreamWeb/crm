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
                                    placeholder="请输入关怀主题"
                                    v-model="input"
                                    clearable>
        </el-input><div class="grid-content bg-purple-light"></div></el-col>
        <el-button type="primary" icon="el-icon-search">搜索</el-button>
      </el-row>
      <el-row>
        <el-col :span="7">客户关怀<div class="grid-content bg-purple"></div></el-col>
        <el-col :span="12"><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="2"><el-button type="text" @click="addBtn">新建</el-button><div class="grid-content bg-purple"></div></el-col>
        <el-dialog
          title="客户关怀"
          :visible.sync="dialogVisible"
          width="50%"
          :before-close="handleClose">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-row>
              <el-col :span="8">
                <el-form-item label="关怀主题">
                  <el-input v-model="form.ghzt"></el-input>
                </el-form-item>
              </el-col >
              <el-col :span="8">
                <el-form-item label="联系人">
                  <el-input v-model="form.lxr"></el-input>
                </el-form-item>
              </el-col>

            </el-row>

            <el-row>
              <el-col :span="8">
                <el-form-item label="客户">
                  <el-input v-model="form.khname"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="日期">
                  <el-date-picker type="date" placeholder="选择日期" v-model="form.date2" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>


            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="执行人">
                  <el-input v-model="form.zxr"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="关怀内容">

                  <el-input type="textarea" v-model="form.ghnr"></el-input>

                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="客户反馈">

                  <el-input type="textarea" v-model="form.khfk"></el-input>

                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div style="text-align: center;">

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
            prop="ghzt"
            label="关怀主题"
            width="120">
          </el-table-column>
          <el-table-column
            prop="lxr"
            label="联系人"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="khname"
            label="客户"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="rq"
            label="日期"
            show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="zxr"
            label="执行人"
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
          ghzt:'',
          khname: '',
          lxr:'',
          zxr:'',
          khfk:'',
          ghnr:'',
          xlh:'',
          wxr:'',
          gzms:'',
          hf:'',
          zb:'',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        num: 1,
        rules:{
          ghzt:[
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            { min: 5, max: 8, message: '长度在 5 到 8个字符', trigger: 'blur' }
          ],
          date2:[
            {type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ]
        }
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
