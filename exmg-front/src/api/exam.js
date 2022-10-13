import request from '@/utils/request'

export function fetchExamList(data) {
  return request({
    url: '/admin/exam/list',
    data: data,
    method: 'post'
  })
}

export function createExam(data) {
  return request({
    url: '/admin/exam/create',
    data: data,
    method: 'post'
  })
}

export function editExam(data) {
  return request({
    url: '/admin/exam/edit',
    data: data,
    method: 'post'
  })
}

export function removeExam(data) {
  return request({
    url: '/admin/exam/remove',
    data: data,
    method: 'post'
  })
}
