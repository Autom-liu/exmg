<template>
  <van-row class="grey-bg">
    <van-tabs :active="activeTabs" @change="onTabChange">
      <van-tab v-for="item in questionTabs" :key="item.id" :title="item.categoryName" :name="item.id">
        <van-collapse accordion :value="activeColl" @change="onCollapseChange">
          <van-row v-for="(question, i) in item.questions" :key="question.id" >
            <van-col span="2" custom-class="top20" v-if="selectAble">
              <van-checkbox :value="selectedQuestion[question.id]" shape="square" @change="(event) => selectedChange(event, question.id)"></van-checkbox>
            </van-col>
            <van-col :span="collSpan">
              <van-collapse-item :name="question.id" :title="question.question" label="1234">
                <block v-for="(option, k) in question.options" :key="option.id">
                  <van-row custom-class="padding" >
                    <van-checkbox disabled :value="option.right" shape="square">{{option.option}}</van-checkbox>
                  </van-row>
                </block>
                <van-cell :border="false">
                  <view slot="title">
                    <van-button round plain size="small" icon="setting" >编辑</van-button>
                  </view>
                  <van-button type="danger" plain round size="small" icon="delete">删除</van-button>
                </van-cell>
              </van-collapse-item>
            </van-col>
          </van-row>
        </van-collapse>
      </van-tab>
    </van-tabs>
    <van-row v-if="selectAble" custom-class="bottom">
      <van-col span="12" custom-class="txt-right">共 {{selectedSize}} 题</van-col>
      <van-col span="12"><van-button type="info" block>添加到试题</van-button></van-col>
    </van-row>
  </van-row>
</template>

<script>

export default {
  data () {
    return {
      questionInfos: [
        {
          id: 1,
          question: '以下问题没有答案AAAAAAAAAa1',
          questionType: 1,
          difficulty: 3,
          topCategory: 1,
          categoryId: 10,
          options: [
            {
              id: 1,
              option: 'AAA',
              right: false
            },
            {
              id: 2,
              option: 'BBB',
              right: false
            },
            {
              id: 3,
              option: 'CCC',
              right: true
            },
            {
              id: 4,
              option: 'DDD',
              right: false
            }
          ]
        },
        {
          id: 2,
          question: '以下问题没有答案2',
          questionType: 1,
          difficulty: 3,
          topCategory: 2,
          categoryId: 10,
          options: [
            {
              id: 1,
              option: 'AAA',
              right: false
            },
            {
              id: 2,
              option: 'BBB',
              right: false
            },
            {
              id: 3,
              option: 'CCC',
              right: true
            },
            {
              id: 4,
              option: 'DDD',
              right: false
            }
          ]
        },
        {
          id: 3,
          question: '以下问题没有答案3',
          questionType: 1,
          difficulty: 3,
          topCategory: 3,
          categoryId: 10,
          options: [
            {
              id: 1,
              option: 'AAA',
              right: false
            },
            {
              id: 2,
              option: 'BBB',
              right: false
            },
            {
              id: 3,
              option: 'CCC',
              right: true
            },
            {
              id: 4,
              option: 'DDD',
              right: false
            }
          ]
        },
        {
          id: 4,
          question: '以下问题没有答案4',
          questionType: 1,
          difficulty: 3,
          topCategory: 4,
          categoryId: 10,
          options: [
            {
              id: 1,
              option: 'AAA',
              right: false
            },
            {
              id: 2,
              option: 'BBB',
              right: false
            },
            {
              id: 3,
              option: 'CCC',
              right: true
            },
            {
              id: 4,
              option: 'DDD',
              right: false
            }
          ]
        },
        {
          id: 5,
          question: '以下问题没有答案5',
          questionType: 1,
          difficulty: 3,
          topCategory: 3,
          categoryId: 10,
          options: [
            {
              id: 1,
              option: 'AAA',
              right: false
            },
            {
              id: 2,
              option: 'BBB',
              right: false
            },
            {
              id: 3,
              option: 'CCC',
              right: true
            },
            {
              id: 4,
              option: 'DDD',
              right: false
            }
          ]
        }
      ],
      categoryList: [
        {
          id: 1,
          categoryName: '计算机学科基础',
          parentId: 0,
          level: 1
        },
        {
          id: 2,
          categoryName: '编程语言',
          parentId: 0,
          level: 1
        },
        {
          id: 3,
          categoryName: '中间件技术',
          parentId: 0,
          level: 1
        },
        {
          id: 4,
          categoryName: '其他领域分支',
          parentId: 0,
          level: 1
        }
      ],
      selectAble: true,
      selectedQuestion: {},
      activeTabs: 0,
      activeColl: 0
    }
  },
  computed: {
    questionTabs () {
      const qsinfos = [...this.questionInfos]
      const cateList = [...this.categoryList]
      cateList.forEach((ele) => {
        ele.questions = qsinfos.filter((q) => q.topCategory === ele.id)
      })
      const all = { id: 0, categoryName: '全部', parentId: 0, level: 1, questions: qsinfos }
      cateList.unshift(all)
      return cateList
    },
    collSpan () {
      return this.selectAble ? 22 : 24
    },
    selectedList () {
      const arr = []
      for (const qid in this.selectedQuestion) {
        if (this.selectedQuestion[qid]) {
          arr.push(qid)
        }
      }
      return arr
    },
    selectedSize () {
      return this.selectedList.length
    }
  },

  components: {

  },

  methods: {
    onTabChange (event) {
      const { name } = event.mp.detail
      this.activeTabs = name
    },
    onCollapseChange (event) {
      this.activeColl = event.mp.detail
    },
    selectedChange (event, id) {
      this.$set(this.selectedQuestion, id, event.mp.detail)
    }
  },

  created () {
    // let app = getApp()
  }
}
</script>

<style scope>
.red-font {
  font: red;
}
.padding {
  padding: 4px 0;
}
.grey-bg {
  background-color: #666;
}
.top20 {
  position: relative;
  top: 20px;
  left: 8px;
}
.bottom {
  width: 100%;
  position: fixed;
  bottom: 0;
}
.txt-right {
  padding-right: 8px;
  text-align: right;
  vertical-align: middle;
}
</style>
