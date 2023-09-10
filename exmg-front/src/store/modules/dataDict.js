import { enumValues } from '@/api/config'

const state = {
  initLock: false,
  dataDict: {}
}

const mutations = {
  INIT_DATADICT: (state, dataDict) => {
    state.dataDict = dataDict
  },
  SET_INITLOCK: (state, lock) => {
    state.initLock = lock
  }
}

const actions = {
  initDataDict({ commit, state }) {
    if (!state.initLock || Object.keys(state.dataDict).length === 0) {
      commit('SET_INITLOCK', true)
      enumValues({}).then((response) => {
        if (response.code === '0000') {
          commit('INIT_DATADICT', response.data)
        } else {
          commit('SET_INITLOCK', false)
        }
      })
    }
  }
}

function parseDict(arrDict, keyField, labelField) {
  const obj = {}
  for (const item of arrDict) {
    obj[item[keyField]] = item[labelField]
  }
  return obj
}

const getters = {
  answerModeEnums: state => parseDict(state.dataDict.AnswerModeEnums, 'code', 'msg'),
  examCategoryEnums: state => parseDict(state.dataDict.ExamCategoryEnums, 'code', 'msg'),
  topCategoryEnums: state => parseDict(state.dataDict.ExamCategoryEnums.filter(t=>t.top), 'code', 'msg'),
  getCategoryByParentId: state => parentId => {
    return parseDict(state.dataDict.ExamCategoryEnums.filter(t=>t.parentId === parentId), 'code', 'msg')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
