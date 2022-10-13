// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  /*提示信息,icon：有效值 "success", "loading", "none",image:自定义icon*/
  prompt: function (msg, icon = "none", image = '') {
    wx.showToast({
      title: msg,
      icon: icon,
      image: image,
      mask: true,
      duration: 2000
    })
  },
  globalData: {
    userInfo: null,
    baseUrl: 'http://localhost:9080/api/finance',
    tokenKey: 'X-Auth-Token'
  }
})
