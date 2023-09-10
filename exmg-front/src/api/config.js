import request from '@/utils/request'

export function enumValues(data) {
  return request({
    url: '/admin/config/enumValue',
    data: data,
    method: 'post'
  })
}
