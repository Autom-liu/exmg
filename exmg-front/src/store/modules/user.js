import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken, setAppToken, removeAppToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'

const state = {
  token: getToken(),
  currentUser: null,
  currentDept: null,
  currentRole: null,
  deptRoleList: [],
  name: '',
  avatar: '',
  introduction: '',
  roles: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_CURRENTUSER: (state, currentUser) => {
    state.currentUser = currentUser
  },
  SET_CURRENTDEPT: (state, currentDept) => {
    state.currentDept = currentDept
  },
  SET_CURRENTROLE: (state, currentRole) => {
    state.currentRole = currentRole
  },
  SET_DEPTROLELIST: (state, deptRoleList) => {
    state.deptRoleList = deptRoleList
  }
}

function filterCurrent(deptRoleList, currentDept, currentRole) {
  return deptRoleList.filter((udr) => udr.department.deptId !== currentDept.deptId && udr.role.deptId !== currentRole.roleId)
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { userId, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ userId: userId.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        setAppToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          reject('Verification failed, please Login again.')
        }

        const { user, currentDept, currentRole } = data
        let { deptRoleList } = data

        deptRoleList = filterCurrent(deptRoleList, currentDept, currentRole)

        commit('SET_CURRENTUSER', user)
        commit('SET_CURRENTDEPT', currentDept)
        commit('SET_CURRENTROLE', currentRole)
        commit('SET_DEPTROLELIST', deptRoleList)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_CURRENTUSER', null)
        commit('SET_CURRENTDEPT', null)
        commit('SET_CURRENTROLE', null)
        commit('SET_DEPTROLELIST', [])
        removeToken()
        removeAppToken()
        resetRouter()
        dispatch('permission/reset', null, { root: true })

        // reset visited views and cached views
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, { root: true })

        resolve('logout...')
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      removeToken()
      removeAppToken()
      resolve()
    })
  },

  // dynamically modify permissions
  async changeRoles({ commit, dispatch }, role) {
    const token = role + '-token'

    commit('SET_TOKEN', token)
    setToken(token)

    const { roles } = await dispatch('getInfo')

    resetRouter()

    // generate accessible routes map based on roles
    const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })
    // dynamically add accessible routes
    router.addRoutes(accessRoutes)

    // reset visited views and cached views
    dispatch('tagsView/delAllViews', null, { root: true })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
