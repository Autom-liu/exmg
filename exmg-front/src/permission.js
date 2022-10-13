import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getAppToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login', '/auth-redirect'] // no redirect whitelist

function isWhiteList(path) {
  return whiteList.indexOf(path) !== -1
}

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  if (isWhiteList(to.path)) {
    next()
  } else {
    // set page title
    document.title = getPageTitle(to.meta.title)

    // determine whether the user has logged in
    const hasAppToken = getAppToken()

    if (hasAppToken) {
      if (to.path === '/login') {
        // if is logged in, redirect to the home page
        next({ path: '/' })
        NProgress.done()
      } else {
        // determine whether the user has obtained his permission roles through getInfo
        const hasPermission = store.state.permission.status
        if (hasPermission) {
          next()
        } else {
          await Promise.all([store.dispatch('user/getInfo'), store.dispatch('permission/getPermission')])
          // next()
          next({ ...to, replace: true })
          NProgress.done()
        }
      }
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next('/login')
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
