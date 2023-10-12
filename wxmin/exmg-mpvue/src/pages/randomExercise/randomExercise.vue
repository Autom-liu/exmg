<template>
  <div class="exam-container">
    <div class="exam-top">
      <div>{{pivotText}}</div>
      <div><van-button plain type="primary" size="small" @click="backIndex">返回</van-button></div>
    </div>
    <div class="exam-progress">
      <van-progress :pivot-text="pivotText" color="#1E90FF" :percentage="percentage" />
    </div>
    <div class="exam-question">
      <span class="exam-type">[{{currentType}}]</span>
      <span class="question">{{currentQuestion.question}}</span>
    </div>
    <van-row v-if="currentPics">
      <van-image :src="currentPics" width="350" height="100" />
    </van-row>
    <div v-if="!mode" class="option-wapper">
      <div class="option-box">
        <!-- 忽视报错,在小程序中需要这样的写法-->
        <div class="option-item" v-for="(option, index) in options" :key="id">
          <van-checkbox :value="option.selected" checked-color="#07c160" @change="(event) => onChange(event, index)">{{option.option}}</van-checkbox>
        </div>
        <van-button type="primary" size="large" @click="showInterpretation">查看解析</van-button>
      </div>
    </div>
    <div v-if="mode" class="option-wapper-fix">
      <div class="option-box">
        <!-- 忽视报错,在小程序中需要这样的写法-->
        <div :class="'option-item-fix ' + iconNames[option.right << 1 | option.selected]" v-for="option in options" :key="id">
          <van-icon :name="iconNames[option.right << 1 | option.selected]" />
          {{option.option}}
        </div>
      </div>
    </div>
    <div v-if="mode" class="cloumn-container">
      <div class="column-box">
        <div class="column-title">答案</div>
        <div class="column-body">
          <div class="content-left">正确答案：{{rightAnswerTxt}}</div>
          <div class="content-right">你的答案：{{yourAnswerTxt}}</div>
        </div>
      </div>
      <div class="column-box">
        <div class="column-title">解析</div>
        <div class="column-body">
          <div class="content-left">{{currentQuestion.interpretation}}</div>
        </div>
      </div>
      <div class="column-box">
        <div class="column-title">知识点</div>
        <div class="column-body">
          <div class="content-left">{{category.msg}}</div>
        </div>
      </div>
      <div class="column-box center">
        题目有问题？联系反馈
      </div>
    </div>
    <div class="exam-bottom">
        <span @click="answerSheet.onShow"><van-icon name="comment-o" />答题卡</span>
        <span>{{formatTimeTxt}}</span>
        <van-button plain type="primary" size="small" @click="prev">上一题</van-button>
        <van-button plain type="primary" size="small" @click="next">下一题</van-button>
    </div>
    <van-action-sheet :show="answerSheet.show" title="答题卡" cancel-text="取消" @cancel="answerSheet.onClose" @close="answerSheet.onClose">
        <div class="answer-sheet">
          <!-- 忽视报错,在小程序中需要这样的写法-->
          <span v-for="(item, index) in questionData" :class="answerStatus[index] ? 'index-round index-active': 'index-round'" :key="id" @click="(event) => answerSheet.onClick(index)">{{index + 1}}</span>
        </div>
    </van-action-sheet>
    <van-toast id="van-toast" />
    <van-dialog id="van-dialog" />
  </div>
</template>

<script>
import http from '@/utils/request'
import tool from '@/utils/index'
import Toast from '@vant/weapp/dist/toast/toast'
import Dialog from '@vant/weapp/dist/dialog/dialog'

export default {
  components: {

  },
  data () {
    return {
      started: false,
      checked: false,
      // true-解析,false-正在答题
      mode: false,
      answerStatus: [],
      category: {},
      questionData: [],
      currentIndex: 0,
      timestamp: 0,
      timer: null,
      answerSheet: {
        show: false,
        onShow: () => {
          this.answerSheet.show = true
        },
        onClose: () => {
          this.answerSheet.show = false
        },
        onClick: (index) => {
          this.currentIndex = index
          this.answerSheet.onClose()
        }
      },
      questionTypeDict: {
        1: '单选',
        2: '多选'
      },
      iconNames: ['circle', 'clear', 'info', 'checked']
    }
  },
  computed: {
    pivotText () {
      return `${this.currentIndex + 1}/${this.questionData.length}`
    },
    percentage () {
      return (this.currentIndex + 1) / this.questionData.length * 100
    },
    currentQuestion () {
      if (this.questionData.length > this.currentIndex) {
        return this.questionData[this.currentIndex]
      } else {
        return ''
      }
    },
    currentPics () {
      if (this.questionData.length > this.currentIndex) {
        return this.questionData[this.currentIndex].picsUrl
      } else {
        return ''
      }
    },
    currentType () {
      if (this.questionData.length > this.currentIndex) {
        return this.questionTypeDict[this.questionData[this.currentIndex].questionType]
      } else {
        return ''
      }
    },
    options () {
      if (this.questionData.length > this.currentIndex) {
        return this.questionData[this.currentIndex].options
      } else {
        return []
      }
    },
    formatTimeTxt () {
      const timestamp = this.timestamp
      const hour = parseInt(timestamp / 3600)
      const minu = parseInt((timestamp - hour * 3600) / 60)
      const second = timestamp - hour * 3600 - minu * 60
      return `${tool.formatNumber(hour)}:${tool.formatNumber(minu)}:${tool.formatNumber(second)}`
    },
    rightAnswerTxt () {
      const ops = [...this.options]
      const labels = ['A', 'B', 'C', 'D', 'E', 'F', 'G']
      for (let index = 0; index < ops.length; index++) {
        ops[index].label = labels[index]
      }
      const retArr = ops.filter(t => t.right).map(p => p.label)
      return retArr.join(',')
    },
    yourAnswerTxt () {
      const ops = [...this.options]
      const labels = ['A', 'B', 'C', 'D']
      for (let index = 0; index < ops.length; index++) {
        ops[index].label = labels[index]
      }
      const retArr = ops.filter(t => t.selected).map(p => p.label)
      return retArr.join(',')
    }
  },

  methods: {
    onChange (event, index) {
      // 设置selected字段状态并展示视图中
      this.$set(this.options[index], 'selected', event.mp.detail)
      // 对于单选将其他状态置灰
      if (this.currentType === this.questionTypeDict[1] && event.mp.detail) {
        for (let i = 0; i < this.options.length; i++) {
          if (i !== index) {
            this.$set(this.options[i], 'selected', false)
          }
        }
      }
      // 维护答题卡状态
      if (event.mp.detail) {
        this.answerStatus[this.currentIndex] = true
      } else {
        // TODO 多选题取消选择只有在全部没选了才置灰
        this.answerStatus[this.currentIndex] = this.options.some(t => t.selected)
      }
    },
    fetchQuestions () {
      if (this.category.code) {
        const params = {categoryId: this.category.code}
        http.post('/question/random', params).then((response) => {
          this.questionData = response.data
        })
      }
    },
    prev () {
      if (this.currentIndex > 0) {
        this.currentIndex--
        this.mode = false
      } else {
        Toast('已经是第一题了')
      }
    },
    next () {
      if (this.currentIndex < this.questionData.length - 1) {
        this.currentIndex++
        this.mode = false
      } else {
        Toast('已经是最后一题了')
      }
    },
    showInterpretation () {
      this.mode = true
    },
    backIndex () {
      this.$router.replace({ path: '/pages/index/main' })
    },
    startExam () {
      if (this.started) {
        return
      }
      Dialog.confirm({ title: '随机练习模式', message: '你即将进入随机练习模式，该模式下可以随时查看解析,且答题不会被记录' })
        .then(() => {
          this.timer = setInterval(() => {
            this.timestamp++
          }, 1000)
        })
      this.started = true
      this.category = this.$route.query
      this.answerStatus = []
      this.questionData = []
      this.currentIndex = 0
      this.fetchQuestions()
    },
    finishExam () {
      clearInterval(this.timer)
      this.timestamp = 0
      this.started = false
    }
  },
  created () {

  },
  onLoad () {
    this.startExam()
  },
  onHide () {

  },
  onUnload () {

  }
}
</script>

<style scope>
.exam-container {
    padding: 0 16px;
}
.exam-top {
  margin: 24px 0;
  display: flex;
  justify-content: space-between;
}
.exam-progress {
  margin: 24px 0;
}
.exam-question {

}
.exam-type {
    color: #00BFFF;
}
.option-wapper {
    margin-top: 24px;
}
.option-box {
  padding-bottom: 70px;
}
.option-item {
    margin: 30px 0;
    font-size: 20px;
    line-height: 1.5em;
}
.van-checkbox .van-checkbox__label {
  line-height: 1.5em;
}
.exam-bottom {
    position: fixed;
    width: 100%;
    left: 0;
    bottom: 0;
    height: 70px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    border-top: #666, solid;
    background-color: #f5f5f5;
}
.answer-sheet {
    display: flex;
    flex-wrap: wrap;
}
.index-round {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    background-color: #f5f5f5;
    margin: 15px;
    line-height: 60px;
    text-align: center;
}
.index-round.index-active {
  background-color: #1E90FF
}


.option-wapper-fix {
  margin-top: 24px;
}
.option-item-fix {
  margin: 12px 0;
  padding: 12px 8px;
  font-size: 18px;
  line-height: 1.5em;
}
.option-item-fix.checked {
  border: 1px solid #e5ffe5;
  background-color: #e5ffe5;
  border-radius: 10px;
  color: #00b300;
}
.option-item-fix.clear {
  border: 1px solid #ffe5e5;
  background-color: #ffe5e5;
  border-radius: 10px;
  color: #e60000;
}
.option-item-fix.info {
  border: 1px solid #ffffcc;
  background-color: #ffffcc;
  border-radius: 10px;
  color: #ffaa00;
}
.option-item-fix.circle {
  border: 1px solid #b3b3b3;
  border-radius: 10px;
}

.cloumn-container {
  padding-bottom: 70px;
}
.column-box {
  margin: 12px;
}
.column-box.center {
  font-size: 12px;
  text-align: center;
}
.column-box .column-title {
  font-weight: bold;
}
.column-box .column-body {
  font-size: 16px;
}
</style>
