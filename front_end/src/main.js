// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from "./store";
import ElementUI from 'element-ui'
import TreeTable from 'vue-table-with-tree-grid'
import "@/style/app.scss"

import iconPicker from 'e-icon-picker'
import 'e-icon-picker/dist/index.css'
import 'e-icon-picker/dist/main.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(iconPicker,{
  FontAwesome: true,
  ElementUI: true,
  eIcon: true,
  eIconSymbol: true
})
Vue.component('tree-table', TreeTable)

/*格式化*/
import {sexFormat,dateFormat} from "./common/formatUtils";
Vue.filter('dateFormat',dateFormat)
Vue.filter('sexFormat',sexFormat)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
}).$mount('#app')
