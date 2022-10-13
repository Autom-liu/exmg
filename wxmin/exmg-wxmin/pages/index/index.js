// index.js
// 获取应用实例
const app = getApp()


import http from '../../utils/request'

Page({
  data: {
    inputValue: ''
  },

  onTap: function (params) {
    wx.login({
      success: (res) => {
        if (res.code) {
          console.log(res)
          http.post('/wx/login', res.code, {
            success: (response) => {
              console.log(response)
              if (response.code == '0000') {
                wx.setStorageSync(app.globalData.tokenKey, response.data)
                wx.redirectTo({ url: `/pages/scores/scores?companyId=${this.data.inputValue}`})
              }
            }
          })
        }
      }
    })
  },

  setInputValue(e) {
    this.setData({
      inputValue: e.detail.value
    })
  },

  onLoad() {

  },
  getUserProfile(e) {

  },
  getUserInfo(e) {

  }
})
