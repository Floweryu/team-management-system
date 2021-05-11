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
const SoftwareShow = () => import('@/views/software/components/SoftwareShow')
const AchievementIndex = () => import('@/views/achievement/index')
const AssetsIndex = () => import('@/views/assets/index')
const TeamManage = () => import('@/views/team/TeamManage')
const TeamShow = () => import('@/views/team/TeamShow')
const TeamDetail = () => import('@/views/team/components/TeamDetail')

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
        path: 'software/manage',
        component: SoftwareIndex,
        meta: { title: '软件管理' }
      },
      {
        path: 'software/detail',
        component: SoftwareShow,
        meta: { title: '软件展示' }
      },
      {
        path: 'achievement',
        component: AchievementIndex,
        meta: { title: '成果管理' }
      },
      {
        path: 'assets',
        component: AssetsIndex,
        mmeta: { title: '资产管理' }
      },
      {
        path: 'team/manage',
        component: TeamManage
      },
      {
        path: 'team/show',
        component: TeamShow
      },
      {
        path: 'team/detail/:id',
        name: 'GroupDetail',
        component: TeamDetail
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
