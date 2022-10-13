## 前端应用模板

### 基础框架详见

[vue-element-admin](https://panjiachen.github.io/vue-element-admin) is a production-ready front-end solution for admin interfaces. It is based on [vue](https://github.com/vuejs/vue) and uses the UI Toolkit [element-ui](https://github.com/ElemeFE/element).


### 项目结构

api：接口请求
assets：静态资源
components：通用组件
directive：自定义指令
filters：自定义过滤器
icons：图标组件
layout：布局组件
router：路由配置
store：状态管理
styles：自定义样式
utils：通用工具方法
-- auth.js：token 存取
-- permission.js：权限检查
request.js：axios 请求封装
index.js：工具方法
views：页面
permission.js：登录认证和路由跳转
settings.js：全局配置
main.js：全局入口文件
App.vue：全局入口组件

### 配置本地代理

vue.config.js

```js
  devServer: {
    port: port,
    open: true,
    overlay: {
      warnings: false,
      errors: true
    },
    proxy: {
      [process.env.VUE_APP_BASE_API]: {  // 指名哪些接口要代理
        target: process.env.MOCK_URL,    // 代理目的URL
        changeOrigin: true,              // 开启跨域
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: process.env.VUE_APP_BASE_API  // 重些  如 '^/api': '' 会将 http://127.0.0.1:xxx/api/xxx  重写为 http://127.0.0.1:xxx/xxx
        }
      }
    },
    after: require('./mock/mock-server.js')
  },
```
