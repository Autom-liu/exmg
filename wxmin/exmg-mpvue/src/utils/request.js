import Toast from '@vant/weapp/dist/toast/toast'

function createFly () {
  if (mpvuePlatform === 'wx') {
    const Fly = require('flyio/dist/npm/wx')
    return new Fly()
  } else {
    return null
  }
}

function handleError (err) {
  console.error(err)
}

const BASE_URL = 'http://127.0.0.1/api/exmg'
const USER_KEY = 'CURRENT_USER'

export default {
  post (url, params = {}) {
    url = BASE_URL + url
    const fly = createFly()
    if (fly) {
      return new Promise((resolve, reject) => {
        fly.post(url, params).then(response => {
          resolve(response.data)
        }).catch(err => {
          Toast.fail('数据加载失败')
          handleError(err)
          reject(err)
        })
      })
    }
  },
  upload (url, file, params = {}) {
    url = BASE_URL + url
    return new Promise((resolve, reject) => {
      wx.uploadFile({
        url: url,
        filePath: file.url,
        name: 'file',
        formData: params,
        success: (res) => {
          const data = JSON.parse(res.data)
          resolve(data)
        }
      })
    })
  },
  wxlogin (onsuccess) {
    const user = mpvue.getStorageSync(USER_KEY)
    if (!user) {
      mpvue.login({
        success: (res) => {
          if (res.code) {
            this.post('/wx/login', res.code).then((response) => {
              if (response.code === '0000') {
                mpvue.setStorageSync(USER_KEY, response.data)
              }
            })
          }
        }
      })
    }
  },
  getUserInfo () {
    return mpvue.getStorageSync(USER_KEY)
  }
}
