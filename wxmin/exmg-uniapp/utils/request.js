
const BASE_URL = 'https://wxapi.apricity.fun/api/exmg'
const USER_KEY = 'CURRENT_USER'

export default {
  post (url, params = {}) {
    url = BASE_URL + url
	return new Promise((resolve, reject) => {
	uni.request({
		url: url,
		data: params,
		method: 'POST',
		success: (res) => {
			console.log(res.data)
			resolve(res.data)
		},
		fail: (error) => {
			console.error(error)
			reject(error)
		}
	})
	})
  },
  upload (url, file, params = {}) {
    url = BASE_URL + url
    return new Promise((resolve, reject) => {
      uni.uploadFile({
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
    const user = uni.getStorageSync(USER_KEY)
    if (!user) {
      uni.login({
        success: (res) => {
          if (res.code) {
            this.post('/wx/login', res.code).then((response) => {
              if (response.code === '0000') {
                uni.setStorageSync(USER_KEY, response.data)
              }
            })
          }
        }
      })
    }
  },
  getUserInfo () {
    return uni.getStorageSync(USER_KEY)
  }
}
