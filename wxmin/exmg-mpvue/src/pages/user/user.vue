<template>
  <van-row>
    <van-row>
      <van-image width="350" height="200" />
    </van-row>
    <van-cell-group>
      <van-cell title="我的管理用户" is-link @click="toAdmin" />
      <van-cell title="答题记录" is-link @click="toAnswerList" />
      <van-cell title="收藏" is-link />
    </van-cell-group>
    <buttom-tab active='user' />
  </van-row>
</template>

<script>
import ButtomTab from '../../components/BottomTab'

export default {
  components: {
    ButtomTab
  },
  data () {
    return {

    }
  },
  computed: {

  },

  methods: {
    toAdmin () {
      this.$router.push('/pages/adminInfo/main')
    },
    toAnswerList () {
      this.$router.push('/pages/userAnswerList/main')
    }
  },

  created () {

  }
}
</script>

<style scope>

</style>
