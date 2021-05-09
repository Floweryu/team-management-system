import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const Index = () => import('@/views/index')
const Login = () => import('@/views/login/index')

const UserManage = () => import('@/views/user/index')
const DocumentManage = () => import('@/views/document/ManageIndex')
const DocumentDetail = () => import('@/views/document/DetailIndex')
const DocumentDetailPre = () => import('@/views/document/components/DocumentDetail')
const SoftwareIndex = () => import('@/views/software/index')

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
      },
      {
        path: 'document/manage',
        component: DocumentManage,
        meta: { title: '文献管理' }
      },
      {
        path: 'document/detail',
        component: DocumentDetail,
        meta: { title: '文献详情' }
      },
      {
        path: 'document/detail/:id',
        name: 'Detail',
        component: DocumentDetailPre,
        meta: { title: '详情页' }
      },
      {
        path: 'software',
        component: SoftwareIndex,
        meta: { title: '软件管理' }
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
