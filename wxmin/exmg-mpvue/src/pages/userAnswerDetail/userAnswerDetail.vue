<template>
  <div class="page-container">
    <div class="exam-question">
      <span class="question">{{currentQuestion.question}}</span>
    </div>
    <div class="option-wapper">
      <div class="option-box">
        <div :class="'option-item ' + iconNames[option.right << 1 | option.status]" v-for="option in options" :key="id">
          <van-icon :name="iconNames[option.right << 1 | option.status]" />
          {{option.option}}
        </div>
      </div>
    </div>
    <div class="cloumn-container">
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
          <div class="content-left">暂无未知</div>
        </div>
      </div>
      <div class="column-box center">
        题目有问题？联系反馈
      </div>
    </div>
    <div class="exam-bottom">
      <van-button plain type="primary" size="small" @click="backIndex">回首页</van-button>
      <van-button plain type="primary" size="small" @click="prev">上一题</van-button>
      <van-button plain type="primary" size="small" @click="next">下一题</van-button>
    </div>
    <van-toast id="van-toast" />
    <van-dialog id="van-dialog" />
  </div>

</template>

<script>
import http from '@/utils/request'
import ButtomTab from '../../components/BottomTab'
import Toast from '@vant/weapp/dist/toast/toast'

export default {
  components: {
    ButtomTab
  },
  data () {
    return {
      /**
       * right && status = 3 => checked
       * !right && status = 1 =>  circle
       * right && !status = 2 => info
       * !right && !status = 0 => clear
       */
      iconNames: ['clear', 'circle', 'info', 'checked'],
      questionData: [],
      currentIndex: 0
    }
  },
  computed: {
    currentQuestion () {
      if (this.questionData.length > this.currentIndex) {
        return this.questionData[this.currentIndex]
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
    rightAnswerTxt () {
      const ops = [...this.options]
      const labels = ['A', 'B', 'C', 'D']
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
      const retArr = ops.filter(t => t.status === t.right).map(p => p.label)
      return retArr.join(',')
    }
  },
  methods: {
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
    backIndex () {
      this.$router.replace({ path: '/pages/index/main' })
    }
  },
  mounted () {
    const { id, userId } = this.$route.query
    const params = { examId: id, userId }
    this.currentIndex = 0
    http.post('/question/user/answer/detail', params).then((response) => {
      console.log(response)
      if (response.code === '0000') {
        this.questionData = response.data
      }
    })
  }
}
</script>

<style scope>
.page-container {
  padding: 20px 16px;
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
.exam-bottom .empty {
  width: 100px;
  height: 10px;
}
.option-wapper {
  margin-top: 24px;
}
.option-item {
  margin: 12px 0;
  padding: 12px 8px;
  font-size: 18px;
  line-height: 1.5em;
}
.option-item.checked {
  border: 1px solid #e5ffe5;
  background-color: #e5ffe5;
  border-radius: 10px;
  color: #00b300;
}
.option-item.clear {
  border: 1px solid #ffe5e5;
  background-color: #ffe5e5;
  border-radius: 10px;
  color: #e60000;
}
.option-item.info {
  border: 1px solid #ffffcc;
  background-color: #ffffcc;
  border-radius: 10px;
  color: #ffaa00;
}
.option-item.circle {
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
