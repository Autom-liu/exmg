<template>
  <van-row>
    <van-row>
      <van-image width="350" height="100" />
    </van-row>
    <van-tabs :active="active" bind:change="onChange">
      <van-tab v-for="(title, id) in cateTabs" :key="id" :title="title">
        <van-row custom-class="middle">
          <van-circle  :value="75" :color="gradientColor" text="随机出题" fill="#FF7F50" layer-color="#FFB6C1" @click="toStartExam"></van-circle>
        </van-row>
      </van-tab>
    </van-tabs>
    <div class="list-wrapper">
      <div class="list-title">精选试题</div>
      <div class="list-inner">
        <div v-for="item in examList" :key="item.id" class="list-table" @click="toQuestion(item)">
          <div class="list-img">
            <image-view :src="item.picUrl"></image-view>
          </div>
          <div class="list-info">
            <div class="title">{{item.examName}}</div>
            <div class="time">{{ item.examRemark }}</div>
            <div class="time">{{ item.begintime }}</div>
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
      gradientColor: {'0%': '#ffd01e', '100%': '#ee0a24'},
      active: 1,
      cateTabs: {
        1: '基础知识',
        2: '编程语言',
        3: '框架中间件'
      },
      examList: []
    }
  },
  computed: {

  },

  methods: {
    fetchBannerExam () {
      http.post('/exam/banner', {}).then((response) => {
        this.examList = response.data.map((v) => ({ ...v, begintime: commonTool.formatDate(new Date(v.begintime)), endtime: commonTool.formatDate(new Date(v.endtime)) }))
      })
    },
    toQuestion (row) {
      this.$router.push({path: '/pages/prepareEnter/main', query: row})
    },
    toStartExam () {
      const userInfo = http.getUserInfo()
      const params = { userId: userInfo.userId }
      http.post('/exam/random', params).then((response) => {
        const examInfo = response.data
        this.$router.replace({path: '/pages/startExam/main', query: examInfo})
      })
    }
  },
  created () {
    http.wxlogin()
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
  padding: 14px 16px;
  background-color: #fff;
}
.list-table {
  display: flex;
  align-items: center;
}
.list-img {
  width: 48px;
  height: 72px;
}
.list-info {
  margin-left: 15px;
  width: 80%;
}
.list-info .title {
  color: #333;
  font-size: 16px;
  line-height: 36px;
  
}
.list-info .time {
  color: rgba(0, 0, 0, .45);
  font-size: 12px;
  line-height: 12px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
