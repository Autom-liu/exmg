<template>
  <div class="exam-container">
    <div class="exam-top">
      <div>{{pivotText}}</div>
      <div><van-button plain type="primary" size="small" @click="onSubmit">交卷</van-button></div>
    </div>
    <div class="exam-progress">
      <van-progress :pivot-text="pivotText" color="#1E90FF" :percentage="percentage" />
    </div>
    <div class="exam-question">
      <span class="exam-type">[{{currentType}}]</span>
      <span class="question">{{currentQuestion}}</span>
    </div>
    <div class="option-wapper">
      <div class="option-box">
        <div class="option-item" v-for="(option, index) in options" :key="option.id">
          <van-checkbox :value="option.selected" checked-color="#07c160" @change="(event) => onChange(event, index)">{{option.option}}</van-checkbox>
        </div>
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
            <span v-for="(item, index) in questionData" :class="answerStatus[index] ? 'index-round index-active': 'index-round'" :key="item.id" @click="(event) => answerSheet.onClick(index)">{{index + 1}}</span>
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
      answerStatus: [],
      examInfo: {},
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
      }
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
        return this.questionData[this.currentIndex].question
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
      if (this.examInfo.id) {
        const params = {examId: this.examInfo.id}
        http.post('/question/startExam', params).then((response) => {
          this.questionData = response.data
        })
      }
    },
    prev () {
      if (this.currentIndex > 0) {
        this.currentIndex--
      } else {
        Toast('已经是第一题了')
      }
    },
    next () {
      if (this.currentIndex < this.questionData.length - 1) {
        this.currentIndex++
      } else {
        Toast('已经是最后一题了')
      }
    },
    onSubmit () {
      Dialog.confirm({ title: '标题', message: '确认交卷吗' }).then(() => {
        Toast.loading({ message: '正在提交中...', forbidClick: true, duration: 0 })
        this.finishExam()
        const answerList = []
        const user = http.getUserInfo()
        for (const qstitem of this.questionData) {
          for (const option of qstitem.options) {
            answerList.push({ userId: user.userId, examId: this.examInfo.id, questionId: qstitem.questionId, optionId: option.id, selected: option.selected })
          }
        }

        const params = { userId: user.userId, examId: this.examInfo.id, answerList }
        http.post('/question/submitAnswer', params).then((response) => {
          const data = response.data
          const query = { ...this.examInfo, ...user, ...data, timestamp: this.timestamp }
          this.$router.replace({ path: '/pages/endExam/main', query })
          Toast.clear()
        })
      }).catch(() => {

      })
    },
    startExam () {
      if (this.started) {
        return
      }
      this.started = true
      this.examInfo = this.$route.query
      this.answerStatus = []
      this.questionData = []
      this.currentIndex = 0
      this.fetchQuestions()
      this.timer = setInterval(() => {
        this.timestamp++
      }, 1000)
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
</style>
