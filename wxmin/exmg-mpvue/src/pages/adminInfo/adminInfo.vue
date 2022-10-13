<template>
  <van-row>
    <van-row>用户名: {{userInfo.userId}}</van-row>
    <van-row>密码: {{userInfo.password}}</van-row>
    <van-row>页面URL: </van-row>
  </van-row>
</template>

<script>

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
    const USER_KEY = 'CURRENT_USER'
    this.userInfo = mpvue.getStorageSync(USER_KEY)
  }
}
</script>

<style scope>

</style>
