<template>
  <view>
    <view>
      <img class="uni-header-image" src="https://pic.5tu.cn/uploads/allimg/1911/pic_5tu_big_201911110937385525.jpg" />
    </view>
	<view class="uni-panel">
		<view class="uni-panel-h" @click="toAdmin">
			<text class="uni-panel-text">我的管理用户</text>
			<uni-icons type="right" size="15"></uni-icons>
		</view>
		<view class="uni-panel-h" @click="toAnswerList">
			<text class="uni-panel-text">答题记录</text>
			<uni-icons type="right" size="15"></uni-icons>
		</view>
		<view class="uni-panel-h">
			<text class="uni-panel-text">收藏</text>
			<uni-icons type="right" size="15"></uni-icons>
		</view>
	</view>
  </view>
</template>

<script>

export default {
  components: {
  },
  data () {
    return {

    }
  },
  computed: {

  },

  methods: {
    toAdmin () {
		uni.navigateTo({
			url: '/pages/adminInfo/main'
		})
    },
    toAnswerList () {
		uni.navigateTo({
			url:'/pages/userAnswerList/main'
		})
    }
  },

  created () {

  }
}
</script>

<style scope>

</style>
