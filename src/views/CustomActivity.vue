<!--
@author: pengjia
@date: 2020/11/9
@description:
-->
<template>
  <el-container style="height: 100%">
    <el-main class="customActivity">
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card>
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="活动" name="activity">
                <el-form :model="clueForm" label-width="50" size="mini"
                         label-position="right" ref="clueForm">
                  <el-form-item label="标题" prop="titleClue"
                                style="margin-top: 10px;width: 80%">
                    <el-input v-model="clueForm.titleClue"
                              clearable placeholder="请输入"></el-input>
                  </el-form-item>
                  <el-form-item label="内容" prop="contentClue"
                                style="margin-top: 10px;width: 80%">
                    <el-input v-model="clueForm.contentClue" type="textarea"
                              clearable placeholder="请输入"></el-input>
                  </el-form-item>
                  <el-form-item label="日期" prop="dateClue">
                    <el-date-picker v-model="clueForm.startDate" format="yyyy-MM-dd"
                                    value-format="yyyy-MM-dd" type="date" style="width: 40%"
                                    placeholder="请输入"></el-date-picker>
                    <span>-</span>
                    <el-date-picker v-model="clueForm.endDate" format="yyyy-MM-dd"
                                    value-format="yyyy-MM-dd" type="date" style="width: 40%"
                                    placeholder="请输入"></el-date-picker>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="尺寸" name="size">
                <el-form :model="sizeForm" label-position="right"
                         label-width="50px" size="mini" ref="sizeFormRef">
                  <el-form-item label="标题" prop="clueTitle" style="margin-top: 10px">
                    <el-slider v-model="sizeForm.clueTitle"></el-slider>
                  </el-form-item>
                  <el-form-item label="内容" prop="clueContent">
                    <el-slider v-model="sizeForm.clueContent"></el-slider>
                  </el-form-item>
                  <el-form-item label="日期" prop="clueDate">
                    <el-slider v-model="sizeForm.clueDate"></el-slider>
                  </el-form-item>
                  <!--<el-form-item label="名称" prop="clueNameInput">
                    <el-slider v-model="sizeForm.clueNameInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="手机" prop="cluePhoneInput">
                    <el-slider v-model="sizeForm.cluePhoneInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="按钮" prop="clueButton">
                    <el-slider v-model="sizeForm.clueButton"></el-slider>
                  </el-form-item>-->
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="颜色" name="color">
                <el-form :model="colorForm" label-position="right"
                         label-width="50px" size="mini" ref="colorFormRef">
                  <el-row :gutter="20" style="margin-top: 10px">
                    <el-col :span="8">
                      <el-form-item label="标题" prop="clueTitle">
                        <el-color-picker v-model="colorForm.clueTitle"></el-color-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="内容" prop="clueContent">
                        <el-color-picker v-model="colorForm.clueContent"></el-color-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="日期" prop="clueDate">
                        <el-color-picker v-model="colorForm.clueDate"></el-color-picker>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <!--<el-form-item label="名称" prop="clueNameInput">
                    <el-slider v-model="colorForm.clueNameInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="手机" prop="cluePhoneInput">
                    <el-slider v-model="colorForm.cluePhoneInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="按钮" prop="clueButton">
                    <el-slider v-model="colorForm.clueButton"></el-slider>
                  </el-form-item>-->
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="字体" name="font">
                <el-form :model="fontForm" label-position="right"
                         label-width="50px" size="mini" ref="fontFormRef">
                  <el-form-item label="标题" prop="clueTitle" style="margin-top: 10px">
                    <el-select v-model="fontForm.clueTitle" clearable placeholder="请选择">
                      <el-option v-for="item in fontFamilyList" :key="item.label"
                                 :label="item.value" :value="item.value"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="内容" prop="clueContent">
                    <el-select v-model="fontForm.clueContent" clearable placeholder="请选择">
                      <el-option v-for="item in fontFamilyList" :key="item.label"
                                 :label="item.value" :value="item.value"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="日期" prop="clueDate">
                    <el-select v-model="fontForm.clueDate" clearable placeholder="请选择">
                      <el-option v-for="item in fontFamilyList" :key="item.label"
                                 :label="item.value" :value="item.value"></el-option>
                    </el-select>
                  </el-form-item>
                  <!--<el-form-item label="名称" prop="clueNameInput">
                    <el-slider v-model="fontForm.clueNameInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="手机" prop="cluePhoneInput">
                    <el-slider v-model="fontForm.cluePhoneInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="按钮" prop="clueButton">
                    <el-slider v-model="fontForm.clueButton"></el-slider>
                  </el-form-item>-->
                </el-form>
              </el-tab-pane>
              <!--<el-tab-pane label="边距" name="margin">
                &lt;!&ndash;<el-form :model="marginForm" label-position="right"
                         label-width="50px" size="mini" ref="marginFormRef">
                  <el-form-item label="标题" prop="clueTitle">
                    <el-slider v-model="marginForm.clueTitle"></el-slider>
                  </el-form-item>
                  <el-form-item label="内容" prop="clueContent">
                    <el-slider v-model="marginForm.clueContent"></el-slider>
                  </el-form-item>
                  <el-form-item label="日期" prop="clueDate">
                    <el-slider v-model="marginForm.clueDate"></el-slider>
                  </el-form-item>
                  <el-form-item label="名称" prop="clueNameInput">
                    <el-slider v-model="marginForm.clueNameInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="手机" prop="cluePhoneInput">
                    <el-slider v-model="marginForm.cluePhoneInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="按钮" prop="clueButton">
                    <el-slider v-model="marginForm.clueButton"></el-slider>
                  </el-form-item>
                </el-form>&ndash;&gt;
                <el-collapse v-model="collapseName" accordion>
                  <el-collapse-item title="标题" name="1">
                    <el-form :model="clueTitleMargin" label-width="30px" label-position="right">
                      <el-row :gutter="10">
                        <el-col :span="12">
                          <el-form-item label="上" prop="topMargin">
                            <el-slider v-model="clueTitleMargin.topMargin"></el-slider>
                          </el-form-item>
                        </el-col>
                        <el-col :span="12">
                          <el-form-item label="下" prop="bottomMargin">
                            <el-slider v-model="clueTitleMargin.bottomMargin"></el-slider>
                          </el-form-item>
                        </el-col>
                      </el-row>
                      <el-row :gutter="10">
                        <el-col :span="12">
                          <el-form-item label="左" prop="leftMargin">
                            <el-slider v-model="clueTitleMargin.leftMargin"></el-slider>
                          </el-form-item>
                        </el-col>
                        <el-col :span="12">
                          <el-form-item label="右" prop="rightMargin">
                            <el-slider v-model="clueTitleMargin.rightMargin"></el-slider>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item title="内容" name="2">
                    <el-row :gutter="10">
                      <el-col :span="12"></el-col>
                    </el-row>
                  </el-collapse-item>
                  <el-collapse-item title="日期" name="3">
                    <el-row :gutter="10">
                      <el-col :span="12"></el-col>
                    </el-row>
                  </el-collapse-item>
                  <el-collapse-item title="名称" name="4">
                    <el-row :gutter="10">
                      <el-col :span="12"></el-col>
                    </el-row>
                  </el-collapse-item>
                  <el-collapse-item title="手机" name="5">
                    <el-row :gutter="10">
                      <el-col :span="12"></el-col>
                    </el-row>
                  </el-collapse-item>
                  <el-collapse-item title="按钮" name="6">
                    <el-row :gutter="10">
                      <el-col :span="12"></el-col>
                    </el-row>
                  </el-collapse-item>
                </el-collapse>
              </el-tab-pane>-->
            </el-tabs>
            <el-divider content-position="left">背景图片</el-divider>
            <el-row :gutter="10">
              <el-col :span="16">
                <el-upload
                  class="upload-demo"
                  drag
                  action="https://jsonplaceholder.typicode.com/posts/"
                  multiple>
                  <i class="el-icon-upload"></i>
                  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                  <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
              </el-col>
              <el-col :span="8">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="url">
                </el-image>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="16">
          <el-card>
            <div style="width: 100%;height: 100%">
              <div class="out-box" :style="{ 'background-image' : 'url(' + this.url + ')' }" style="background-size:cover;">
                <div style="margin: auto" :style="{ color: this.colorForm.clueTitle, fontSize: this.sizeForm.clueTitle + 'px',fontFamily:this.fontForm.clueTitle, }">
                  <div :style="{marginTop:this.clueTitleMargin.topMargin+'px',marginBottom:this.clueTitleMargin.bottomMargin+'px',marginLeft:this.clueTitleMargin.leftMargin+'px',marginRight:this.clueTitleMargin.rightMargin+'px'}">{{clueForm.titleClue}}</div>
                </div>
                <div :style="{ color: this.colorForm.clueContent, fontSize: this.sizeForm.clueContent + 'px',fontFamily:this.fontForm.clueContent }">{{clueForm.contentClue}}</div>
                <el-row>
                  <el-col>
                    <div :style="{ color:this.colorForm.clueDate,fontSize:this.sizeForm.clueDate+'px',fontFamily:this.fontForm.clueDate }">{{clueForm.startDate}}</div>
                    <div :style="{ color:this.colorForm.clueDate,fontSize:this.sizeForm.clueDate+'px',fontFamily:this.fontForm.clueDate }">{{clueForm.endDate}}</div>
                  </el-col>
                </el-row>
                <!--<div>
                  <el-input style="width: 30%" clearable placeholder="请输入"></el-input>
                </div>
                <div>
                  <el-input style="width: 30%" clearable placeholder="请输入"></el-input>
                </div>
                <div>
                  <el-button type="primary" icon="el-icon-plus">立即参加</el-button>
                </div>-->
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
    <el-footer>
      <el-card></el-card>
    </el-footer>
  </el-container>
</template>

<script>
  import {ActivityFamilyCN} from '../common/data/custom_activity'
  export default {
    name: "CustomerActivity",
    data() {
      return {
        fontFamilyList:ActivityFamilyCN,

        activeColor: 'black',

        activeName: 'activity',

        collapseName:'1',
        clueForm:{
          titleClue:'',
          contentClue:'',
          startDate:'',
          endDate:''
        },
        sizeForm:{
          clueTitle:40,
          clueContent:50,
          clueDate:20,
          clueNameInput:1,
          cluePhoneInput:1,
          clueButton:1
        },
        colorForm:{
          clueTitle:'#409EFF',
          clueContent:'#409EFF',
          clueDate:'#409EFF',
          clueNameInput:0,
          cluePhoneInput:0,
          clueButton:0
        },
        fontForm:{
          clueTitle:'小豆岛默陌乐童简',
          clueContent:'小豆岛默陌乐童简',
          clueDate:'小豆岛默陌乐童简'
        },
        marginForm:{
          clueTitle:0,
          clueContent:0,
          clueDate:0,
          clueNameInput:0,
          cluePhoneInput:0,
          clueButton:0
        },
        clueTitleMargin:{
          topMargin:0,
          bottomMargin:0,
          leftMargin:0,
          rightMargin:0
        },

        url: 'http://www.eblog.icu/jaychou.jpg',
      }
    },
    methods:{
      handleClick(tab, event) {

      }
    }
  }
</script>

<style scoped>
  >>>.el-form-item{
    margin-top: -5px;
  }
  >>>.el-upload-dragger{
    width: 240px;
    height: 100px;
  }
  >>>.el-upload-dragger .el-icon-upload{
    line-height: 1px;
  }

  .out-box{
    height: 500px;
    width: 800px;
    border: black solid 0px;
    margin: auto;
  }

  .customActivity {
    overflow-y: scroll;
    white-space: nowrap;
  }
  ::-webkit-scrollbar {
    width: 0 !important;
  }
  ::-webkit-scrollbar {
    width: 0 !important;height: 0;
  }
</style>
