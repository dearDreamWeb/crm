import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from "../views/Login";
import Dashboard from "../views/Dashboard";
import Layout from "../views/layout/Layout";

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
    path: '/base',
    component: () => import('../views/layout/Layout'),
    redirect: 'home',
    children: [
      {
        path: '/home',
        component: () => import('../views/Dashboard')
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
        path: '/szorder',
        component: () => import('../views/shouzhong/szorder')
      }
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
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  next()
})

export default router


