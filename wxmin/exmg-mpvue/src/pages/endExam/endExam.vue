<template>
  <div class="container-wrapper">
    <div class="container-top">
        <div class="title">{{resultData.examName}}</div>
        <div class="number">共{{resultData.totalNum}}题</div>
        <div class="score">{{resultData.totalScore}}分</div>
    </div>
    <div class="container-middle">
        <div class="blocks">
            <div class="block">
                <div>{{resultData.rightNum}}</div>
                <div>答对</div>
            </div>
            <div class="block red">
                <div>{{resultData.wrongNum}}</div>
                <div>答错</div>
            </div>
            <div class="block">
                <div>{{rightRate}}%</div>
                <div>正确率</div>
            </div>
        </div>
        <div class="infos">用时 {{formatTimeTxt}}</div>
    </div>
    <div class="container-end">
        <div class="btn-box">
          <van-button type="primary" block @click="toDetail">查看答案与解析</van-button>
        </div>
        <div class="btn-box">
          <van-button type="info" block @click="backAgain">再答一次</van-button>
        </div>
        <div class="btn-box">
          <van-button type="danger" block @click="backIndex">返回首页</van-button>
        </div>
    </div>
  </div>
</template>

<script>
import tool from '@/utils/index'

export default {
  components: {

  },
  data () {
    return {
      resultData: {}
    }
  },
  computed: {
    rightRate () {
      return parseInt(this.resultData.rightNum / this.resultData.totalNum * 100)
    },
    formatTimeTxt () {
      const timestamp = this.resultData.timestamp
      const hour = parseInt(timestamp / 3600)
      const minu = parseInt((timestamp - hour * 3600) / 60)
      const second = timestamp - hour * 3600 - minu * 60
      return `${tool.formatNumber(hour)}时 ${tool.formatNumber(minu)}分 ${tool.formatNumber(second)}秒`
    }
  },

  methods: {
    backIndex () {
      this.$router.replace({ path: '/pages/index/main' })
    },
    backAgain () {
      const { id, examName, examRemark, begintime, endtime } = this.resultData
      const examInfo = { id, examName, examRemark, begintime, endtime }
      this.$router.replace({ path: '/pages/prepareEnter/main', query: examInfo })
    },
    toDetail () {
      this.$router.replace({ path: '/pages/userAnswerDetail/main', query: this.resultData })
    }
  },
  created () {

  },
  mounted () {
    this.resultData = this.$route.query
    console.log(this.resultData)
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


