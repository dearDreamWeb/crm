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
      }
    ]
  }
]

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router


