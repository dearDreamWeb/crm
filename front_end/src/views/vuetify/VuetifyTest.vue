<!--
@author: pengjia
@date: 2020/10/30
@description:
-->
<template>
  <div>
    <el-select v-model="value" placeholder="请选择" style="width: 200px">
      <el-option style="float: left;margin: auto"
        v-for="item in cities"
        :key="item.value"
        :label="item.label"
        :value="item.value">
        <i :class="item.icon"></i>
      </el-option>
    </el-select>
  </div>
</template>

<script>
  export default {
    name: "VuetifyTest",
    data() {
      return {
        cities: [{
          value: 'Beijing',
          label: '北京',
          icon: 'el-icon-edit'
        }, {
          value: 'Shanghai',
          label: '上海',
          icon: 'el-icon-edit'
        }, {
          value: 'Nanjing',
          label: '南京',
          icon: 'el-icon-edit'
        }, {
          value: 'Chengdu',
          label: '成都',
          icon: 'el-icon-edit'
        }, {
          value: 'Shenzhen',
          label: '深圳',
          icon: 'el-icon-edit'
        }, {
          value: 'Guangzhou',
          label: '广州',
          icon: 'el-icon-edit'
        }],
        value: ''
      }
    }
  }
</script>

<style scoped>

</style>
