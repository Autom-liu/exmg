import request from '@/utils/request'

export function fetchQuestionList(data) {
  return request({
    url: '/admin/question/list',
    data: data,
    method: 'post'
  })
}

export function pageQuestionList(data) {
  return request({
    url: '/admin/question/page',
    data: data,
    method: 'post'
  })
}

export function createQuestion(data) {
  return request({
    url: '/admin/question/create',
    data: data,
    method: 'post',
    json: true
  })
}

export function editQuestion(data) {
  return request({
    url: '/admin/question/edit',
    data: data,
    method: 'post',
    json: true
  })
}

export function removeQuestion(data) {
  return request({
    url: '/admin/question/remove',
    data: data,
    method: 'post'
  })
}

export function queryQuestionExam(data) {
  return request({
    url: '/admin/question/exam/query',
    data: data,
    method: 'post'
  })
}

export function resignExamQuestion(data) {
  return request({
    url: '/admin/question/exam/assign',
    data: data,
    method: 'post',
    json: true
  })
}
