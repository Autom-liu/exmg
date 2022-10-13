import Cookies from 'js-cookie'

const GlobalTokenKey = 'Admin-GlobalToken'

const AppTokenKey = 'Admin-AppToken'

export function getToken() {
  return Cookies.get(GlobalTokenKey)
}

export function setToken(token) {
  return Cookies.set(GlobalTokenKey, token)
}

export function removeToken() {
  return Cookies.remove(GlobalTokenKey)
}

export function getAppToken() {
  return window.sessionStorage.getItem(AppTokenKey)
}

export function setAppToken(token) {
  return window.sessionStorage.setItem(AppTokenKey, token)
}

export function removeAppToken() {
  return window.sessionStorage.removeItem(AppTokenKey)
}
