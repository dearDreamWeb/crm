// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from "./store";
import ElementUI from 'element-ui'
import TreeTable from 'vue-table-with-tree-grid'
import urlUtils from "./common/urlUtils";
import VueAreaLinkage from 'vue-area-linkage'
import iconPicker from 'e-icon-picker'
import md5 from 'js-md5'
import "@/style/app.scss"
import 'vue-area-linkage/dist/index.css'
import 'e-icon-picker/dist/index.css'
import 'font-awesome/css/font-awesome.min.css'
import './assets/alibaba_icon/icon/iconfont.css'
import 'element-ui/lib/theme-chalk/icon.css'
import "@/common/font/myfont.css";
import "@/common/font_cn/myfont.css";
import echarts from 'echarts'

import onlyNumber from '@/directive/el-input'; //自定义全局指令

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(iconPicker,{
  FontAwesome: true,
  ElementUI: true,
  eIcon: true,
  eIconSymbol: true
})
Vue.use(VueAreaLinkage)
Vue.use(onlyNumber); //使用该全局指令
Vue.component('tree-table', TreeTable)
Vue.prototype.$urlUtil = urlUtils
Vue.prototype.$md5 = md5
Vue.prototype.$echarts = echarts

/*格式化*/
import {
  sexFormat,dateFormat,clueStatusFormat,timeFormat,clueTypeFormat,
  ordStateFormat,delStateFormat,planInvoiceFormat,recoReceivableFormat,
  dateTimeFormat,clueHandleResultFormat,productStatusFormat,followCategoryFormat,
  salePriorLevelFormat,saleStarBeaconFormat,dateDiff,cusIsSolve,offerStatusFormat
} from "./common/formatUtils";
import {liftCycleFormat} from "./common/customerFormat";

Vue.filter('dateFormat',dateFormat)
Vue.filter('dateDiff',dateDiff)
Vue.filter('offerStatusFormat',offerStatusFormat)
Vue.filter('cusIsSolve',cusIsSolve)
Vue.filter('timeFormat',timeFormat)
Vue.filter('sexFormat',sexFormat)
Vue.filter('clueStatusFormat',clueStatusFormat)
Vue.filter('clueTypeFormat',clueTypeFormat)
Vue.filter('ordStateFormat',ordStateFormat)
Vue.filter('delStateFormat',delStateFormat)
Vue.filter('planInvoiceFormat',planInvoiceFormat)
Vue.filter('recoReceivableFormat',recoReceivableFormat)
Vue.filter('dateTimeFormat',dateTimeFormat)
Vue.filter('clueHandleResultFormat',clueHandleResultFormat)
Vue.filter('productStatusFormat',productStatusFormat)
Vue.filter('followCategoryFormat',followCategoryFormat)
Vue.filter('salePriorLevelFormat',salePriorLevelFormat)
Vue.filter('saleStarBeaconFormat',saleStarBeaconFormat)
Vue.filter('liftCycleFormat',liftCycleFormat)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
}).$mount('#app')
