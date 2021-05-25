import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const Index = () => import('@/views/index')
const Login = () => import('@/views/login/index')
const ForgetPassword = () => import('@/views/login/components/ForgetPassword')
const Home = () => import('@/views/home/index')

const UserManage = () => import('@/views/user/index')
const DocumentManage = () => import('@/views/document/ManageIndex')
const DocumentDetail = () => import('@/views/document/DetailIndex')
const DocumentDetailPre = () => import('@/views/document/components/DocumentDetail')
const SoftwareIndex = () => import('@/views/software/index')
const SoftwareShow = () => import('@/views/software/components/SoftwareShow')
const AchievementIndex = () => import('@/views/achievement/index')
const AchievementShow = () => import('@/views/achievement/ShowIndex')
const AssetsIndex = () => import('@/views/assets/index')
const TeamManage = () => import('@/views/team/TeamManage')
const TeamShow = () => import('@/views/team/TeamShow')
const TeamDetail = () => import('@/views/team/components/TeamDetail')
const TeamDetailShow = () => import('@/views/team/components/TeamDetailShow')
const ClassIndex = () => import('@/views/classes/index')
const TaskIndex = () => import('@/views/tasks/index')
const TaskDetailShow = () => import('@/views/tasks/components/TaskDetailShow')
const MeetingIndex = () => import('@/views/meeting/index')
const ReportIndex = () => import('@/views/report/index')
const ReportDetail = () => import('@/views/report/components/ReportDetail')
const UserRoleIndex = () => import('@/views/role/index')
const LabelIndex = () => import('@/views/label/index')
const ClassifyIndex = () => import('@/views/classify/index')
const SystemLog = () => import('@/views/systemlog/index')

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
    path: '/forget_password',
    component: ForgetPassword
  },
  {
    path: '/dashboard',
    component: Index,
    children: [
      {
        path: 'home',
        component: Home
      },
      {
        path: 'systemlog',
        component: SystemLog
      },
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
        path: 'document/class',
        component: ClassifyIndex
      },
      {
        path: 'document/label',
        component: LabelIndex
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
        path: 'achievement/show',
        component: AchievementShow
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
      },
      {
        path: 'team/detail/show/:id',
        name: 'GroupDetailShow',
        component: TeamDetailShow
      },
      {
        path: 'class',
        component: ClassIndex
      },
      {
        path: 'task',
        component: TaskIndex
      },
      {
        path: 'task/detail/show/:id',
        name: 'TaskDetailShow',
        component: TaskDetailShow
      },
      {
        path: 'meeting',
        component: MeetingIndex
      },
      {
        path: 'report',
        component: ReportIndex
      },
      {
        path: 'report/detail/:id',
        name: 'ReportDetail',
        component: ReportDetail
      },
      {
        path: 'role',
        component: UserRoleIndex
      }
    ]
  }
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  document.title = '团队资源管理系统'
  if (to.path === '/login') {
    next()
  } else {
    let token = localStorage.getItem('Authorization')
    if (token === 'null' || token === '') {
      next('/login')
    } else {
      next()
    }
  }
})

export default router
