import request from '@/utils/request'

export function pageRole(data) {
  return request({
    url: '/admin/role/page',
    method: 'post',
    data
  })
}

export function listMenu(data) {
  return request({
    url: '/admin/menu/list',
    method: 'post',
    data
  })
}

export function createMenu(data) {
  return request({
    url: '/admin/menu/create',
    method: 'post',
    data
  })
}

export function removeMenu(data) {
  return request({
    url: '/admin/menu/remove',
    method: 'post',
    data
  })
}

export function editMenu(data) {
  return request({
    url: '/admin/menu/edit',
    method: 'post',
    data
  })
}

export function permissionAssign(data) {
  return request({
    url: '/admin/permission/assign',
    data: data,
    method: 'post'
  })
}
