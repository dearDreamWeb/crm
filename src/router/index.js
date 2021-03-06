import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from "../views/Login";
import Dashboard from "../views/Dashboard";
import Layout from "../views/layout/Layout";
import CustomActivity from "../views/CustomActivity";

Vue.use(Router)

const routes = [
  {
    path: '/',
    redirect: 'home'
  },
  {
    path: '/login',
    component: () => import('../views/Login')
  },
  {
    path: '/clue_page',
    component: () => import('../views/CluePage')
  },
  {
    name: CustomActivity,
    path: '/custom_activity',
    component: () => import('../views/CustomActivity')
  },
  {
    path: '/activity_detail_duplicate',
    component: () => import('../views/activity/ActivityDetailDuplicate')
  },
  {
    path: '/clue_detail',
    component: () => import('../views/clue/ClueDetail')
  },
  {
    path: '/customer_detail',
    component: () => import('../views/customer/CustomerDetail')
  },
  {
    path: '/sale_detail',
    component: () => import('../views/sale/SaleDetail')
  },
  {
    path: '/offer_operation',
    component: () => import('../views/customer/OfferOperation')
  },
  {
    path: '/base',
    component: () => import('../views/layout/Layout'),
    redirect: 'home',
    children: [
      {
        path: '/home',
        component: () => import('../views/Dashboard')
      },
      {
        path: '/dash_board',
        component: () => import('../views/dashboard/Index')
      },
      {
        path: '/user',
        component: () => import('../views/user/User')
      },
      {
        path: '/dept',
        component: () => import('../views/dept/Dept')
      },
      {
        path: '/menu',
        component: () => import('../views/menu/Menu')
      },
      {
        path: '/dict',
        component: () => import('../views/dict/Dict')
      },
      {
        path: '/activity',
        component: () => import('../views/activity/Activity')
      },
      {
        path: '/order',
        component: () => import('../views/shouzhong/Szorder')
      },
      {
        path: '/deliver',
        component: () => import('../views/shouzhong/SzDeliver')
      },
      {
        path: '/care',
        component: () => import('../views/care/Care')
      },
      {
        path: '/clue',
        component: () => import('../views/clue/Clue')
      },
      {
        path: '/complaint',
        component: () => import('../views/complaint/Complaint')
      },
      {
        path: '/activityDetail',
        component: () => import('../views/activity/ActivityDetail')
      },
      {
        path: '/qa_library',
        component: () => import('../views/qa/Qa')
      },
      {
        path: '/syslog',
        component: () => import('../views/syslog/Syslog')
      },
      {
        path: '/cus_kf',
        component: () => import('../views/customer/Customerkf')
      },
      {
        path: '/customer',
        component: () => import('../views/customer/Customer')
      },
      {
        path: '/offer',
        component: () => import('../views/customer/Offer')
      },
      {
        path: '/follow_up',
        component: () => import('../views/customer/FollowUp')
      },
      {
        path: '/product',
        component: () => import('../views/product/Product')
      },
      {
        path: '/plan',
        component: () => import('../views/shouzhong/SzReceivablePlan')
      },
      {
        path: '/repairorder',
        component: () => import('../views/repairorder/Repairorder')
      },
      {
        path: '/vuetify',
        component: () => import('../views/vuetify/VuetifyTest')
      },
      {
        path: '/sale',
        component: () => import('../views/sale/Sale')
      },
      {
        path: '/demand',
        component: () => import('../views/demand/Demand')
      },
      {
        path: '/solution',
        component: () => import('../views/solution/Solution')
      },
      {
        path: '/contacts',
        component: () => import('../views/customer/Contacts')
      },
      {
        path: '/repair_control_desk',
        component: () => import('../views/Maintenance/maintenance')
      },
      {
        path: '/personal',
        component: () => import('../views/personal/PersonalCenter')
      },
    ]
  }
]

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next)=>{
  if (to.path === '/login') return next()
  if (to.path === '/clue_page') return next()
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  next()
})

export default router


