import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/user/current',
    method: 'post',
    params: { token }
  })
}

export function getPermission(token) {
  return request({
    url: '/admin/permission/current',
    method: 'post',
    data: { token }
  })
}

export function logout() {
  return request({
    url: '/admin/user/logout',
    method: 'post'
  })
}

export function pageUser(data) {
  return request({
    url: '/admin/user/page',
    data: data,
    method: 'post'
  })
}

export function userDetail(data) {
  return request({
    url: '/admin/user/detail',
    data: data,
    method: 'post'
  })
}

export function createUser(data) {
  return request({
    url: '/admin/user/create',
    data: data,
    method: 'post'
  })
}

export function assignUser(data) {
  return request({
    url: '/admin/user/assign',
    data: data,
    method: 'post'
  })
}

export function resignUser(data) {
  return request({
    url: '/admin/user/resign',
    data: data,
    method: 'post'
  })
}

