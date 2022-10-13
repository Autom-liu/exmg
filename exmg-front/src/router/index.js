import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: '/dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'Dashboard', icon: 'dashboard', affix: true }
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/system',
    component: Layout,
    meta: { title: '系统管理', icon: 'edit' },
    children: [
      {
        path: '/system/department',
        name: 'DeptIndex',
        component: () => import('@/views/system/department/index'),
        redirect: '/system/department/index',
        meta: { title: '部门管理', icon: 'edit' },
        children: [
          {
            path: '/system/department/index',
            component: () => import('@/views/system/department/list'),
            name: 'DepartmentList',
            meta: { title: '部门管理', icon: 'edit' }
          },
          {
            path: '/system/department/add',
            component: () => import('@/views/system/department/add'),
            name: 'DepartmentAdd',
            hidden: true,
            meta: { title: '添加', icon: 'edit' }
          },
          {
            path: '/system/department/edit',
            component: () => import('@/views/system/department/edit'),
            hidden: true,
            name: 'DepartmentEdit',
            meta: { title: '编辑', icon: 'edit' }
          }
        ]
      },
      {
        path: '/system/users',
        name: 'UsersIndex',
        component: () => import('@/views/system/users/index'),
        redirect: '/system/users/index',
        meta: { title: '用户管理', icon: 'edit' },
        children: [
          {
            path: '/system/users/index',
            component: () => import('@/views/system/users/list'),
            name: 'UsersList',
            meta: { title: '用户管理', icon: 'edit' }
          },
          {
            path: '/system/users/add',
            component: () => import('@/views/system/users/add'),
            name: 'UsersAdd',
            hidden: true,
            meta: { title: '添加', icon: 'edit' }
          },
          {
            path: '/system/users/edit',
            component: () => import('@/views/system/users/edit'),
            hidden: true,
            name: 'UsersEdit',
            meta: { title: '编辑', icon: 'edit' }
          },
          {
            path: '/system/users/detail',
            component: () => import('@/views/system/users/detail'),
            hidden: true,
            name: 'UsersDetail',
            meta: { title: '详情', icon: 'edit' }
          },
          {
            path: '/system/users/assign',
            component: () => import('@/views/system/users/assign'),
            hidden: true,
            name: 'UsersAssign',
            meta: { title: '分配', icon: 'setting' }
          }
        ]
      },
      {
        path: '/system/roles',
        name: 'RolesIndex',
        component: () => import('@/views/system/roles/index'),
        redirect: '/system/roles/index',
        meta: { title: '角色管理', icon: 'edit' },
        children: [
          {
            path: '/system/roles/index',
            component: () => import('@/views/system/roles/list'),
            name: 'RolesList',
            meta: { title: '角色管理', icon: 'edit' }
          },
          {
            path: '/system/roles/add',
            component: () => import('@/views/system/roles/add'),
            name: 'RolesAdd',
            hidden: true,
            meta: { title: '添加', icon: 'edit' }
          },
          {
            path: '/system/roles/edit',
            component: () => import('@/views/system/roles/edit'),
            hidden: true,
            name: 'RolesEdit',
            meta: { title: '编辑', icon: 'edit' }
          }
        ]
      },
      {
        path: '/system/menus',
        name: 'MenusIndex',
        component: () => import('@/views/system/menus/index'),
        redirect: '/system/menus/index',
        meta: { title: '菜单管理', icon: 'edit' },
        children: [
          {
            path: '/system/menus/index',
            component: () => import('@/views/system/menus/list'),
            name: 'MenusList',
            meta: { title: '菜单管理', icon: 'edit' }
          },
          {
            path: '/system/menus/add',
            component: () => import('@/views/system/menus/add'),
            name: 'MenusAdd',
            hidden: true,
            meta: { title: '添加', icon: 'edit' }
          },
          {
            path: '/system/menus/edit',
            component: () => import('@/views/system/menus/edit'),
            hidden: true,
            name: 'MenusEdit',
            meta: { title: '编辑', icon: 'edit' }
          },
          {
            path: '/system/menus/permission',
            component: () => import('@/views/system/menus/permission'),
            hidden: true,
            name: 'Permission',
            meta: { title: '菜单权限分配', icon: 'edit' }
          }
        ]
      }
    ]
  },
  {
    path: '/exmg',
    component: Layout,
    meta: { title: '题库系统', icon: 'edit' },
    children: [
      {
        path: '/exmg/exam',
        name: 'ExamIndex',
        component: () => import('@/views/exmg/exam/index'),
        redirect: '/exmg/exam/index',
        meta: { title: '试题管理', icon: 'edit' },
        children: [
          {
            path: '/exmg/exam/index',
            component: () => import('@/views//exmg/exam/list'),
            name: 'ExamList',
            meta: { title: '试题管理', icon: 'edit' }
          },
          {
            path: '/exmg/exam/add',
            component: () => import('@/views//exmg/exam/add'),
            name: 'ExamAdd',
            hidden: true,
            meta: { title: '试题添加', icon: 'edit' }
          },
          {
            path: '/exmg/exam/edit',
            component: () => import('@/views//exmg/exam/edit'),
            hidden: true,
            name: 'ExamEdit',
            meta: { title: '试题编辑', icon: 'edit' }
          }
        ]
      },
      {
        path: '/exmg/question',
        name: 'QuestionIndex',
        component: () => import('@/views/exmg/question/index'),
        redirect: '/exmg/question/index',
        meta: { title: '题目管理', icon: 'edit' },
        children: [
          {
            path: '/exmg/question/index',
            component: () => import('@/views/exmg/question/list'),
            name: 'QuestionList',
            meta: { title: '题目管理', icon: 'edit' }
          },
          {
            path: '/exmg/question/add',
            component: () => import('@/views/exmg/question/add'),
            name: 'QuestionAdd',
            hidden: true,
            meta: { title: '题目添加', icon: 'edit' }
          },
          {
            path: '/exmg/question/edit',
            component: () => import('@/views/exmg/question/edit'),
            hidden: true,
            name: 'QuestionEdit',
            meta: { title: '题目编辑', icon: 'edit' }
          },
          {
            path: '/exmg/question/exam',
            component: () => import('@/views/exmg/question/examquestion'),
            hidden: true,
            name: 'ExamQuestion',
            meta: { title: '考试题目', icon: 'edit' }
          },
          {
            path: '/exmg/question/exam/assign',
            component: () => import('@/views/exmg/question/assignexam'),
            hidden: true,
            name: 'AssignExam',
            meta: { title: '添加/编辑考试题目', icon: 'edit' }
          }
        ]
      }
    ]
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
