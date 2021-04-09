import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const Index = () => import('@/views/index')
const Login = () => import('@/views/login/index')

const UserManage = () => import('@/views/user/index')

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/dashboard',
    component: Index,
    children: [
      {
        path: 'user',
        component: UserManage,
        meta: { title: '用户管理' }
      }
    ]
  }
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
