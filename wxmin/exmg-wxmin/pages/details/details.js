// pages/details/details.js
Page({

  /**
   * Page initial data
   */
  data: {
    detailData: null,
    companyId: ''
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    const detailData = JSON.parse(options.detailData);
    this.setData({
      detailData: this.toViewData(detailData),
      companyId: options.companyId
    });
  },

  toViewData(origin) {
    return origin.map((e) => {
      const scores = e.scores;
      const values = e.values;
      const arr = [];
      for(let i = 0; i < scores.length; i++) {
        arr.push({
          score: scores[i],
          value: values[i],
          serno: 2015 + i
        });
      }
      arr.push({
        score: scores.reduce((a, b) => a + b).toFixed(2),
        value: (values.reduce((a, b) => a + b) / values.length).toFixed(4),
        serno: 'total.'
      })
      return {projectTarget: e.projectTarget, table: arr};
    });
  },

  returnBack() {
    wx.navigateBack({
      delta: 1
    })
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