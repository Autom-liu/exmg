<template>
  <van-row>
    <van-tabbar :active="active" @change="onChange">
        <van-tabbar-item name="home" icon="home-o">首页</van-tabbar-item>
        <van-tabbar-item name="search" icon="search">数据</van-tabbar-item>
        <van-tabbar-item name="setting" icon="setting-o">题库</van-tabbar-item>
        <van-tabbar-item name="user" icon="friends-o">我的</van-tabbar-item>
    </van-tabbar>
  </van-row>
</template>

<script>

export default {
  props: {
    active: {
      type: String,
      default: 'home'
    }
  },
  data () {
    return {

    }
  },
  computed: {

  },

  components: {

  },

  methods: {
    onChange (e) {
      const active = e.mp.detail
      if (this.active === active) {
        return
      }
      switch (active) {
        case 'home':
          this.$router.push('/pages/index/main')
          break
        case 'user':
          this.$router.push('/pages/user/main')
          break
      }
    }
  },

  created () {
    // let app = getApp()
  }
}
</script>

<style scope>

</style>
