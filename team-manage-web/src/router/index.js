import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const Index = () => import('@/views/index')

const UserManage = () => import('@/components/UserManage')
const CommandInterface = () => import('@/components/CommandInterface')

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/home',
    name: 'UserManage',
    component: UserManage
  },
  {
    path: '/command',
    component: CommandInterface
  }
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
