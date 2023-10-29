<template>
  <view>
    <view>用户名: {{userInfo.userId}}</view>
    <view>密码: {{userInfo.password}}</view>
    <view>页面URL: http://in-exmg.apricity.fun/</view>
  </view>
</template>

<script>
import http from '@/utils/request'
export default {
  components: {

  },
  data () {
    return {
      userInfo: {}
    }
  },
  computed: {

  },

  methods: {

  },

  mounted () {
    this.userInfo = http.getUserInfo()
  }
}
</script>

<style scope>

</style>
