const app = getApp()

export default {
    get(url, params) {
        wx.request({
            ...params,
            url: `${app.globalData.baseUrl}${url}`,
            method: 'GET',
            success: (res) => {
                console.log(res)
                params.success(res.data)
            },
            fail: (err) => {
                console.error(err)
            }
        })
    },
    post(url, data, params) {
        const token = wx.getStorageSync(app.globalData.tokenKey);
        if(!params.header) {
            params.header = {}
        }
        params.header[app.globalData.tokenKey] = token
        wx.request({
            ...params,
            url: `${app.globalData.baseUrl}${url}`,
            method: 'POST',
            data: data,
            success: (res) => {
                console.log(res)
                params.success(res.data)
            },
            fail: (err) => {
                console.error(err)
            }
        })
    }
}