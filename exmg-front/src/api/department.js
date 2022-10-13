import request from '@/utils/request'

export function listDepartment(data) {
  return request({
    url: '/admin/dept/list',
    method: 'post',
    data
  })
}

export function createDepartment(token) {
  return request({
    url: '/admin/dept/create',
    method: 'post',
    params: { token }
  })
}
