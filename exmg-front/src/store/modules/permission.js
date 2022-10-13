import router, { asyncRoutes, constantRoutes } from '@/router'
import { getPermission } from '@/api/user'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(routes, accessPath) {
  return routes.some((route) => route.children ? hasPermission(route.children, accessPath) : route.path === accessPath)
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, accessPaths, forbiddenRoute) {
  const accRoutes = []

  for (const route of routes) {
    if (Array.isArray(route.children)) {
      const tmpRt = { ...route }
      tmpRt.children = filterAsyncRoutes(route.children, accessPaths, forbiddenRoute)
      if (tmpRt.children.length > 0) {
        accRoutes.push(tmpRt)
      }
    } else {
      if (accessPaths.includes(route.path)) {
        accRoutes.push(route)
      } else {
        // 记录非法路由作为跳转401页面
        forbiddenRoute.push({ path: route.path, name: route.name, redirect: '/401', hidden: true })
      }
    }
  }
  return accRoutes
}

const state = {
  routes: [],
  addRoutes: [],
  status: false
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
    state.status = true
  },
  RESET_ROUTES: (state) => {
    state.addRoutes = []
    state.routes = constantRoutes
    state.status = false
  }
}

const actions = {
  generateRoutes({ commit }, roles) {
    return new Promise(resolve => {
      // 记录非法路由
      const forbiddenRoute = []
      let accessedRoutes = asyncRoutes // filterAsyncRoutes(asyncRoutes, roles, forbiddenRoute)
      accessedRoutes = accessedRoutes.concat(forbiddenRoute)
      // 最后拼上404页面
      accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  },

  getPermission({ dispatch, state, rootState }) {
    return new Promise((resolve, reject) => {
      getPermission(rootState.user.token).then(async(response) => {
        const { data } = response

        if (!data) {
          reject('Verification failed, please Login again.')
        }
        const accessRoutes = await dispatch('generateRoutes', data)
        router.addRoutes(accessRoutes)
        resolve(accessRoutes)
      }).catch(error => reject(error))
    })
  },
  reset({ commit }) {
    commit('RESET_ROUTES', [])
  }
}

const getters = {
  hasPermission: (state) => (path) => {
    return hasPermission(constantRoutes, path) || hasPermission(state.addRoutes, path)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
