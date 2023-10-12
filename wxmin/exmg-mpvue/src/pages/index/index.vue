<template>
  <van-row>
    <div class="notice">
      <img class="icon" src="/static/images/boardcast.png">
      <div class="text-container">
        <div class="text" ref="text">{{ noticeText }}</div>
      </div>
    </div>
    <van-row>
      <van-image src="https://pic.5tu.cn/uploads/allimg/1911/pic_5tu_big_201911110937385525.jpg" width="350" height="100" />
    </van-row>
    <div class="category-bar" @touchstart="startDrag" @touchmove="drag" @touchend="dragEnd">
      <div class="category-override" :style="categoryTabsAttr.overrideStyle">
        <div class="slider" :style="categoryTabsAttr.sliderStyle" ref="slider">
          <!-- 忽视报错,在小程序中需要这样的写法-->
          <div class="category" :style="categoryTabsAttr.itemStyle" v-for="category in categoryField.categories" :key="code" @click="toRandomExercise(category)">
            <img src="/static/images/database.png" alt="图标">
            <span>{{ category.msg }}</span>
          </div>
        </div>
      </div>
      <div class="page-indicator">
        <span v-for="(_, index) in categoryTabsAttr.pageCount" :key="index" :class="{ 'active': index === categoryField.categoryPageActive }"></span>
      </div>
    </div>

    <div class="list-wrapper">
      <div class="list-title">精选试题</div>
      <div class="list-inner">
        <!-- 忽视报错,在小程序中需要这样的写法-->
        <div v-for="item in examList" :key="id" class="list-table" @click="toQuestion(item)">
          <div class="list-img">
            <image-view :src="item.picUrl"></image-view>
          </div>
          <div class="content-container">
            <div class="title">{{item.examName}}</div>
            <div class="time">{{ item.begintime }}</div>
          </div>
          <div class="arrow-container">
            <img src="/static/images/arrowright.png" class="arrow-icon">
          </div>
        </div>
      </div>
    </div>
    <buttom-tab />
  </van-row>
</template>

<script>
import ButtomTab from '../../components/BottomTab'
import ImageView from '../../components/ImageView'
import http from '@/utils/request'
import commonTool from '@/utils/index'

export default {
  components: {
    ButtomTab,
    ImageView
  },
  data () {
    return {
      noticeText: '欢迎使用答题宝，有任何问题欢迎各位给我建议反馈以及交流沟通',
      categoryField: {
        categories: [],
        // 分类栏位每页数量
        categoryPageCount: 4,
        // 当前所在页数
        categoryPageActive: 0,
        // 拖动起始位置
        dragStartX: 0,
        // 向左滑动
        dragLeft: false
      },
      examList: []
    }
  },
  computed: {
    categoryTabsAttr () {
      console.log(this.categoryField.categories)
      const pageCount = Math.ceil(this.categoryField.categories.length / this.categoryField.categoryPageCount)
      const itemWidth = 100 / (pageCount * this.categoryField.categoryPageCount)

      return {
        overrideStyle: `width:${pageCount * 100}%`,
        itemStyle: `width:${itemWidth}%`,
        sliderStyle: `transform: translateX(-${this.categoryField.categoryPageActive * itemWidth * this.categoryField.categoryPageCount}%)`,
        pageCount: pageCount
      }
    }
  },

  methods: {
    startDrag (event) {
      this.categoryField.dragStartX = event.clientX
    },
    drag (event) {
      const dragDistance = event.clientX - this.categoryField.dragStartX
      const dragLeft = dragDistance < 0
      this.categoryField.dragLeft = dragLeft
    },
    dragEnd () {
      if (this.categoryField.dragLeft) {
        this.categoryField.categoryPageActive = Math.min(this.categoryField.categoryPageActive + 1, this.categoryTabsAttr.pageCount - 1)
      } else {
        this.categoryField.categoryPageActive = Math.max(this.categoryField.categoryPageActive - 1, 0)
      }
    },
    fetchBannerExam () {
      http.post('/exam/banner', {}).then((response) => {
        this.examList = response.data.map((v) => ({ ...v, begintime: commonTool.formatDate(new Date(v.begintime)), endtime: commonTool.formatDate(new Date(v.endtime)) }))
      })
    },
    toQuestion (row) {
      this.$router.push({path: '/pages/prepareEnter/main', query: row})
    },
    toRandomExercise (row) {
      this.$router.push({path: '/pages/randomExercise/main', query: row})
    },
    initPage () {
      http.wxlogin()
      this.initCategory()
    },
    initCategory () {
      http.post('/question/category', {}).then((response) => {
        this.categoryField.categories = response.data
      })
    }
  },
  created () {
    this.initPage()
  },
  mounted () {
    this.fetchBannerExam()
  }
}
</script>

<style scope>
.middle {
  margin-top: 24px;
  margin-left: 35%;
}
.van-circle .van-circle__text {
  color: #fff;
  font-weight: 600;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}
.list-wrapper{
  padding: 14px 0;
  background-color: #F7F7F7;
}
.list-title {
  padding: 14px 16px;
}
.list-inner {
  display: flex;
  flex-direction: column;
  margin: 10px;
}
.list-table {
  display: flex;
  align-items: center;
  margin: 10px;
}
.list-img {
  width: 48px;
  height: 72px;
}

.content-container {
  margin-left: 10px;
  flex: 1;
  height: 64px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.content-container .title {
  color: #333;
  font-size: 18px;
  font-weight: bold;
  
}

.content-container .time {
  color: rgba(0, 0, 0, .45);
  font-size: 14px;
  line-height: 14px;
}

.arrow-container {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 30px;
  height: 100px;
}

.arrow-icon {
  width: 20px;
  height: 20px;
}


.notice {
  display: flex;
  align-items: center;
  height: 26px;
  padding: 0 20px;
  overflow: hidden;
  position: relative;
  background-color: #f8ecd2;;
}

.notice .icon {
  width: 25px;
  height: 16px;
}

.notice .text-container {
  margin-left: 10px;
  overflow: hidden;
}

.notice .text {
  font-size: 13px;
  white-space: nowrap;
  color: #1296db;
  animation: scrollText 10s linear infinite;
}

@keyframes scrollText {
  from {
    transform: translateX(100%);
  }
  to {
    transform: translateX(-100%);
  }
}

.category-bar {
  height: 75px;
  margin: 10px;
  position: relative;
}

.category-override {
  width: 200%
}

.category-override .slider {
  display: flex;
  transition: transform 0.3s ease-in-out;
}

.category-override .category {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 12.5%;
}

.category-override .category img {
  width: 40px;
  height: 40px;
}

.category-override .category span {
  font-size: 14px;
  margin-top: 10px;
}

.page-indicator {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.page-indicator span {
  width: 10px;
  height: 10px;
  background-color: #c1c1c1;
  border-radius: 50%;
  margin: 0 5px;
  transition: background-color 0.3s ease-in-out;
}

.page-indicator span.active {
  background-color: #000;
}

</style>
