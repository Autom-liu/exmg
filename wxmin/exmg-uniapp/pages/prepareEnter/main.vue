<template>
  <view class="btn-wrapper" @click="closeRemark">
    <view class="btn-box">
        <view class="btn-item">
			<button size="default" type="default" class="btn" @click="toStartExam">开始答题</button>
		</view>
		<view class="btn-item">
			<button size="default" type="default" class="btn" @click.stop="toAnswerList">查看历史答题记录</button>
		</view>
        <view class="btn-item">
			<button size="default" type="default" class="btn" @click.stop="showRemark">考试说明</button>
		</view>
    </view>
    <view class="exam-remark" v-show="remarkShow">
        <view>考试名称: {{examInfo.examName}}</view>
        <view>考试说明: {{examInfo.examRemark}}</view>
        <view>开始时间: {{examInfo.begintime}}</view>
        <view>截止时间: {{examInfo.endtime}}</view>
    </view>
  </view>
</template>

<script>

export default {
  components: {

  },
  data () {
    return {
      examInfo: {},
      remarkShow: false
    }
  },
  computed: {

  },

  methods: {
    showRemark () {
      this.remarkShow = true
    },
    closeRemark () {
      this.remarkShow = false
    },
    toStartExam () {
	  uni.navigateTo({
		url:`/pages/startExam/main?query=${JSON.stringify(this.examInfo)}`
	  })
    },
	toAnswerList() {
		uni.navigateTo({ url:`/pages/userAnswerList/main?query=${JSON.stringify({examId:this.examInfo.id})}`})
	}
  },
  onLoad (route) {
	this.examInfo = JSON.parse(route.query)
  }
}
</script>

<style scope>
.btn-wrapper {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url('https://img2.baidu.com/it/u=1155746535,60905048&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889');
    background-size: cover;
}
.btn-box {
    width: 250px;
    height: 250px;
}
.btn-item {
    font-size: 16px;
    font-weight: 600;
    margin: 16px 0;
}
.btn-item .btn {
	background-color: #ff8d00;
	color: #f5f5f5;
}
.exam-remark {
    position: absolute;
    width: 250px;
    height: 250px;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
    background-color: #f5f5f5;
}
</style>


