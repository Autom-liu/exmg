
const app = getApp()

import http from '../../utils/request'

// pages/scores/scores.js
Page({

  /**
   * Page initial data
   */
  data: {
    companyId: '',
    hasDetail: false,
    totalScore: 0,
    showResult: false,
    detailData: null
  },

  returnBack() {
    wx.redirectTo({ url: '/pages/index/index'})
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    this.setData({
      companyId: this.options.companyId
    })
    setTimeout(() => {
      this.setData({
        showResult: true
      })
    }, 5000)
    http.post('/company/single', this.options.companyId, {
      success: (response) => {
        if(response.code == '0000') {
          if(Array.isArray(response.data)) {
            this.setData({
              detailData: response.data,
              totalScore: this.vipScore(response.data),
              hasDetail: true
            })
          } else {
            this.setData({
              totalScore: this.notvipScore(response.data)
            })
          }
        } else {
          app.prompt(response.msg);
        }
      }
    })
  },

  notvipScore(obj) {
    let totalScores = 0;
    const keys = Object.keys(obj);
    for (let i = 0; i < keys.length; i++) {
      const scores = obj[keys[i]];
      totalScores += scores.reduce((a, b) => a + b);
    }
    return totalScores.toFixed(2);
  },

  vipScore(detailData) {
    let totalScores = 0;
    for(let i = 0; i < detailData.length; i++) {
      const scores = detailData[i].scores;
      totalScores += scores.reduce((a, b) => a + b);
    }
    return totalScores.toFixed(2);
  },

  onShowDetails() {
    if(!this.data.hasDetail) {
      app.prompt("当前为非vip用户，不支持查看");
    } else {
      const detailStr = JSON.stringify(this.data.detailData);
      wx.navigateTo({ url: `/pages/details/details?companyId=${this.data.companyId}&detailData=${detailStr}`});
    }
  },

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function () {

  },

  /**
   * Lifecycle function--Called when page show
   */
  onShow: function () {

  },

  /**
   * Lifecycle function--Called when page hide
   */
  onHide: function () {

  },

  /**
   * Lifecycle function--Called when page unload
   */
  onUnload: function () {

  },

  /**
   * Page event handler function--Called when user drop down
   */
  onPullDownRefresh: function () {

  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function () {

  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function () {

  }
})