// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from "./store";
import ElementUI from 'element-ui'
import TreeTable from 'vue-table-with-tree-grid'
import urlUtils from "./common/urlUtils";
import "@/style/app.scss"
import VueAreaLinkage from 'vue-area-linkage'
import 'vue-area-linkage/dist/index.css'
import iconPicker from 'e-icon-picker'
import 'e-icon-picker/dist/index.css'
import 'font-awesome/css/font-awesome.min.css'
import 'element-ui/lib/theme-chalk/icon.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(iconPicker,{
  FontAwesome: true,
  ElementUI: true,
  eIcon: true,
  eIconSymbol: true
})
Vue.use(VueAreaLinkage)
Vue.component('tree-table', TreeTable)
Vue.prototype.$urlUtil = urlUtils

/*格式化*/
import {
  sexFormat,dateFormat,clueStatusFormat,timeFormat,
  ordStateFormat,delStateFormat,planInvoiceFormat,dateTimeFormat,
  clueHandleResultFormat,productStatusFormat
} from "./common/formatUtils";
Vue.filter('dateFormat',dateFormat)
Vue.filter('timeFormat',timeFormat)
Vue.filter('sexFormat',sexFormat)
Vue.filter('clueStatusFormat',clueStatusFormat)
Vue.filter('ordStateFormat',ordStateFormat)
Vue.filter('delStateFormat',delStateFormat)
Vue.filter('planInvoiceFormat',planInvoiceFormat)
Vue.filter('dateTimeFormat',dateTimeFormat)
Vue.filter('clueHandleResultFormat',clueHandleResultFormat)
Vue.filter('productStatusFormat',productStatusFormat)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
}).$mount('#app')
