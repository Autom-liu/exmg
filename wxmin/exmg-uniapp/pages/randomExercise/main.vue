<template>
  <div class="exam-container">
    <div class="exam-top">
      <div>{{pivotText}}</div>
      <div><button type="primary" size="mini" @click="backIndex">返回</button></div>
    </div>
    <div class="exam-progress">
	  <self-progress :pivot-text="pivotText" color="#1E90FF" :percentage="percentage" />
    </div>
    <div class="exam-question">
      <span class="exam-type">[{{currentType}}]</span>
      <span class="question">{{currentQuestion.question}}</span>
    </div>
    <view v-if="currentQuestion.picsUrl">
      <image :src="currentQuestion.picsUrl" mode="widthFix"  />
    </view>
    <div v-if="!mode" class="option-wapper">
		<div class="option-item" v-for="(option, index) in options" :key="option.id">
			<self-checkbox :value="option.selected" checked-color="#07c160" @change="(event) => onChange(event, index)">{{option.option}}</self-checkbox>
		</div>
	  <button type="primary" @click="showInterpretation">查看解析</button>
    </div>
    <div v-if="mode" class="option-wapper-fix">
      <div class="option-box">
        <div :class="'option-item-fix ' + iconNames[option.right << 1 | option.selected]" v-for="option in options" :key="option.id">
			<uni-icons :type="iconNames[option.right << 1 | option.selected]" :color="iconColor[option.right << 1 | option.selected]" size="20"></uni-icons>
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
          <text class="content-left">{{mergeInterpretation}}</text>
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
        <span @click="answerSheet.onShow"><uni-icons type="settings-filled" size="30"></uni-icons>答题卡</span>
        <span>{{formatTimeTxt}}</span>
        <button type="primary" size="mini" @click="prev">上一题</button>
        <button type="primary" size="mini" @click="next">下一题</button>
    </div>
    <uni-popup ref="answerSheet" type="bottom" :show="answerSheet.show" title="答题卡" cancel-text="取消" @cancel="answerSheet.onClose" @close="answerSheet.onClose">
        <div class="sheet-title">答题卡</div>
		<div class="answer-sheet">
          <span v-for="(item, index) in questionData" :class="answerStatus[index] ? 'index-round index-active': 'index-round'" :key="item.id" @click="(event) => answerSheet.onClick(index)">{{index + 1}}</span>
        </div>
    </uni-popup>
    <view>
		<uni-popup ref="message" type="message">
			<uni-popup-message type="warn" :message="messageText" :duration="2000"></uni-popup-message>
		</uni-popup>
	</view>

	<view>
		<uni-popup ref="alertDialog" type="dialog">
			<uni-popup-dialog type="success" cancelText="关闭" confirmText="确定" title="随机练习模式" content="你即将进入随机练习模式，该模式下可以随时查看解析,且答题不会被记录"
			@close="dialogClose" @confirm="dialogClose"></uni-popup-dialog>
		</uni-popup>
	</view>
	
  </div>
</template>

<script>
import http from '@/utils/request'
import tool from '@/utils/index'
import selfCheckbox from '@/pages/components/self/selfCheckbox.vue'
import selfProgress from '@/pages/components/self/selfProgress.vue'

export default {
  components: {
	  selfCheckbox,
	  selfProgress
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
	  messageText: '',
      answerSheet: {
        show: false,
        onShow: () => {
          this.$refs.answerSheet.open()
        },
        onClose: () => {
          this.$refs.answerSheet.close()
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
      iconNames: ['circle', 'clear', 'info-filled', 'checkbox-filled'],
	  iconColor: ['#333', '#e60000', '#ffaa00', '#00b300']
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
        return {}
      }
    },
	mergeInterpretation() {
		if (this.currentQuestion) {
			const questionInterpretation = this.currentQuestion.interpretation
			let result = [questionInterpretation]
			const labels = ['A选项', 'B选项', 'C选项', 'D选项', 'E选项', 'F选项', 'G选项']
			for (let i=0; i<this.options.length; i++) {
				if (this.options[i].interpretation) {
					result = result.concat([labels[i] + this.options[i].interpretation])
				}
				
			}
			return result.join('\n')
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
	  const labels = ['A', 'B', 'C', 'D', 'E', 'F', 'G']
	  for (let index = 0; index < ops.length; index++) {
	    ops[index].label = labels[index]
	  }
	  const retArr = ops.filter(t => t.selected).map(p => p.label)
	  return retArr.join(',')
	}
  },

  methods: {
    onChange (value, index) {
	  // 设置selected字段状态并展示视图中
	  this.$set(this.options[index], 'selected', value)
	  // 对于单选将其他状态置灰
	  if (this.currentType === this.questionTypeDict[1] && value) {
	    for (let i = 0; i < this.options.length; i++) {
	  	  if (i !== index) {
		    this.$set(this.options[i], 'selected', false)
		  }
	    }
	  }
	  // 维护答题卡状态
	  if (value) {
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
        this.Toast('已经是第一题了')
      }
    },
    next () {
      if (this.currentIndex < this.questionData.length - 1) {
        this.currentIndex++
        this.mode = false
      } else {
        this.Toast('已经是最后一题了')
      }
    },
	Toast(message) {
		this.messageText = message
	   this.$refs.message.open()
	},
    showInterpretation () {
      this.mode = true
    },
    backIndex () {
		uni.switchTab({
			url: '/pages/index/main'
		})
    },
    startExam (route) {
      if (this.started) {
        return
      }
      this.started = true
      this.category = JSON.parse(route.query)
      this.answerStatus = []
      this.questionData = []
      this.currentIndex = 0
      this.fetchQuestions()
	  this.$refs.alertDialog.open()
    },
	dialogClose() {
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
  onLoad (option) {
    this.startExam(option)
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
	padding-bottom: 70px;
}
.option-box {
  
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
.sheet-title {
    background-color: #e5ffe5;
	border-bottom: 1px solid #333;
	padding-left: 20px;
	line-height: 40px;
}
.answer-sheet {
    display: flex;
    flex-wrap: wrap;
	background-color: #fff;
	margin-bottom: 60px;
}
.index-round {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    background-color: #c3c3c3;
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
.option-item-fix.checkbox-filled {
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
.option-item-fix.info-filled {
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
