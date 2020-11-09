<!--
@author: pengjia
@date: 2020/11/9
@description:
-->
<template>
  <el-container>
    <el-main>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card>
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="活动" name="clue">
                <el-form :model="clueForm" label-width="50" size="mini"
                         label-position="right" ref="clueForm">
                  <el-form-item label="标题" prop="titleClue">
                    <el-input v-model="clueForm.titleClue"
                              clearable placeholder="请输入"></el-input>
                  </el-form-item>
                  <el-form-item label="内容" prop="contentClue">
                    <el-input v-model="clueForm.contentClue" type="textarea"
                              clearable placeholder="请输入"></el-input>
                  </el-form-item>
                  <el-form-item label="日期" prop="dateClue">
                    <el-date-picker v-model="clueForm.startDate" format="yyyy-MM-dd"
                                    value-format="yyyy-MM-dd" type="date"
                                    placeholder="请输入"></el-date-picker>
                    <span>-</span>
                    <el-date-picker v-model="clueForm.endDate" format="yyyy-MM-dd"
                                    value-format="yyyy-MM-dd" type="date"
                                    placeholder="请输入"></el-date-picker>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="尺寸" name="first">
                <el-form :model="sizeForm" label-position="right"
                         label-width="50px" size="mini" ref="sizeFormRef">
                  <el-form-item label="标题" prop="clueTitle">
                    <el-slider v-model="sizeForm.clueTitle"></el-slider>
                  </el-form-item>
                  <el-form-item label="内容" prop="clueContent">
                    <el-slider v-model="sizeForm.clueContent"></el-slider>
                  </el-form-item>
                  <el-form-item label="日期" prop="clueDate">
                    <el-slider v-model="sizeForm.clueDate"></el-slider>
                  </el-form-item>
                  <el-form-item label="名称" prop="clueNameInput">
                    <el-slider v-model="sizeForm.clueNameInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="手机" prop="cluePhoneInput">
                    <el-slider v-model="sizeForm.cluePhoneInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="按钮" prop="clueButton">
                    <el-slider v-model="sizeForm.clueButton"></el-slider>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="颜色" name="second">
                <el-form :model="colorForm" label-position="right"
                         label-width="50px" size="mini" ref="colorFormRef">
                  <el-form-item label="标题" prop="clueTitle">
                    <el-slider v-model="colorForm.clueTitle"></el-slider>
                  </el-form-item>
                  <el-form-item label="内容" prop="clueContent">
                    <el-slider v-model="colorForm.clueContent"></el-slider>
                  </el-form-item>
                  <el-form-item label="日期" prop="clueDate">
                    <el-slider v-model="colorForm.clueDate"></el-slider>
                  </el-form-item>
                  <el-form-item label="名称" prop="clueNameInput">
                    <el-slider v-model="colorForm.clueNameInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="手机" prop="cluePhoneInput">
                    <el-slider v-model="colorForm.cluePhoneInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="按钮" prop="clueButton">
                    <el-slider v-model="colorForm.clueButton"></el-slider>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="字体" name="third">
                <el-form :model="fontForm" label-position="right"
                         label-width="50px" size="mini" ref="fontFormRef">
                  <el-form-item label="标题" prop="clueTitle">
                    <el-slider v-model="fontForm.clueTitle"></el-slider>
                  </el-form-item>
                  <el-form-item label="内容" prop="clueContent">
                    <el-slider v-model="fontForm.clueContent"></el-slider>
                  </el-form-item>
                  <el-form-item label="日期" prop="clueDate">
                    <el-slider v-model="fontForm.clueDate"></el-slider>
                  </el-form-item>
                  <el-form-item label="名称" prop="clueNameInput">
                    <el-slider v-model="fontForm.clueNameInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="手机" prop="cluePhoneInput">
                    <el-slider v-model="fontForm.cluePhoneInput"></el-slider>
                  </el-form-item>
                  <el-form-item label="按钮" prop="clueButton">
                    <el-slider v-model="fontForm.clueButton"></el-slider>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="边距" name="fourth">
                <el-form :model="marginForm" label-position="right"
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
                </el-form>
              </el-tab-pane>
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
                  :src="url"
                  :preview-src-list="srcList">
                </el-image>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="16">
          <el-card></el-card>
        </el-col>
      </el-row>
    </el-main>
    <el-footer>
      <el-card></el-card>
    </el-footer>
  </el-container>
</template>

<script>
  export default {
    name: "CustomerActivity",
    data() {
      return {
        activeName: 'second',

        clueForm:{
          titleClue:'',
          contentClue:'',
          startDate:'',
          endDate:''
        },
        sizeForm:{
          clueTitle:0,
          clueContent:0,
          clueDate:0,
          clueNameInput:0,
          cluePhoneInput:0,
          clueButton:0
        },
        colorForm:{
          clueTitle:0,
          clueContent:0,
          clueDate:0,
          clueNameInput:0,
          cluePhoneInput:0,
          clueButton:0
        },
        fontForm:{
          clueTitle:0,
          clueContent:0,
          clueDate:0,
          clueNameInput:0,
          cluePhoneInput:0,
          clueButton:0
        },
        marginForm:{
          clueTitle:0,
          clueContent:0,
          clueDate:0,
          clueNameInput:0,
          cluePhoneInput:0,
          clueButton:0
        },
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        srcList: [
          'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
          'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
        ]
      }
    },
    methods:{
      handleClick(tab, event) {
        console.log(tab, event);
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
</style>
