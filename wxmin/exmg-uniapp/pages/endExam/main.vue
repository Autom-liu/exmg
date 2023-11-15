<template>
  <view class="container-wrapper">
    <view class="container-top">
        <view class="title">{{resultData.examName}}</view>
        <view class="number">共{{resultData.totalNum}}题</view>
        <view class="score">{{resultData.totalScore * rightRate / 100}}分</view>
    </view>
    <view class="container-middle">
        <view class="blocks">
            <view class="block">
                <view>{{resultData.rightNum}}</view>
                <view>答对</view>
            </view>
            <view class="block red">
                <view>{{resultData.wrongNum}}</view>
                <view>答错</view>
            </view>
            <view class="block">
                <view>{{rightRate}}%</view>
                <view>正确率</view>
            </view>
        </view>
        <view class="infos">用时 {{formatTimeTxt}}</view>
    </view>
    <view class="container-end">
        <view class="btn-box">
          <button size="default" type="primary" class="btn" @click="toDetail">查看答案与解析</button>
        </view>
        <view class="btn-box">
          <button size="default" type="primary" class="btn" @click="backIndex">返回首页</button>
        </view>
    </view>
  </view>
</template>

<script>
import tool from '@/utils/index'
import http from '@/utils/request'

export default {
  components: {

  },
  data () {
    return {
      resultData: {
		  examName: '',
		  totalNum: 0,
		  totalScore: 0,
		  rightNum: 0,
		  wrongNum: 0,
		  timestamp: 0
	  },
	  query: {},
    }
  },
  computed: {
    rightRate () {
      return parseInt(this.resultData.rightNum / this.resultData.totalNum * 100)
    },
    formatTimeTxt () {
      const timestamp = this.resultData.timestamp / 1000
      const hour = parseInt(timestamp / 3600)
      const minu = parseInt((timestamp - hour * 3600) / 60)
      const second = timestamp - hour * 3600 - minu * 60
      return `${tool.formatNumber(hour)}时 ${tool.formatNumber(minu)}分 ${tool.formatNumber(second)}秒`
    }
  },

  methods: {
    backIndex () {
      uni.switchTab({ url: '/pages/index/main'})
    },
    toDetail () {
      // this.$router.replace({ path: '/pages/userAnswerDetail/main', query: this.resultData })
	  uni.navigateTo({url:`/pages/userAnswerDetail/main?query=${JSON.stringify(this.query)}`})
    }
  },
  created () {

  },
  onLoad (option) {
    this.query = JSON.parse(option.query)
	http.post('/question/user/answer/record', this.query).then((response) => {
	  const record = response.data[0].record
	  const examInfo = response.data[0].examInfo
	  this.resultData.timestamp = record.endTime - record.startTime
	  this.resultData.rightNum = record.rightNum
	  this.resultData.totalNum = record.totalNum
	  this.resultData.wrongNum = record.totalNum - record.rightNum
	  this.resultData.totalScore = examInfo.totalScore
	  this.resultData.examName = examInfo.examName
	})
  },
  mounted () {
    
  }
}
</script>

<style scope>
.container-wrapper {
    padding: 0 16px;
}
.container-top {
    margin-top: 24px;
    display: flex;
    height: 160px;
    flex-direction: column;
    justify-content: space-between;
    text-align: center;
}
.container-top .title {
  font-size: 18px;
  font-weight: 600;
  color: #333;
}
.container-top .number {
  font-size: 14px;
  color: #A9A9A9;
}
.container-top .score {
  font-size: 24px;
  color: #FF0000;
  font-weight: 600;
}
.container-middle {
    margin-top: 24px;
}
.container-middle .blocks {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    border-bottom: 2px, solid, #A9A9A9;
    text-align: center;
}
.block.red {
    color: #FF0000;
}
.container-middle .infos {
    margin: 12px 0;
}
.btn-box {
    margin: 24px 48px;
}
</style>


