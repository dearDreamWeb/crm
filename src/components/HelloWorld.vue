<template>
  <div>
    <el-upload action="http://localhost:9999/aliyun/upload"
               list-type="picture-card"
               :on-preview="handlePictureCardPreview"
               :on-remove="handleRemove"
               :on-success="uploadOk">
      <i class="el-icon-plus"></i>
      <el-dialog :visible.sync="dialogVisible" size="tiny">
        <img :src="dialogImageUrl" width="100%" alt />
      </el-dialog>
    </el-upload>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
      dialogFormVisible: false,
      imageUrl:""
    }
  },
  methods:{
    uploadOk(response, file, fileList) {
      this.imageUrl = response.data;
    },
    addPicture: function () {
      this.dialogFormVisible = true;
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
