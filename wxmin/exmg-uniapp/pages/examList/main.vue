<template>
  <van-row>
    <van-row>
      <van-button type="info" round icon="add" @click="tocreate">创建试题</van-button>
      <van-button type="info" round icon="column" @click="tocreate">我的题库</van-button>
    </van-row>
    <van-row>
      <van-card desc="我的描述描述" title="后端系统基础知识1" currency="-" price="2021-12-22" thumb="https://img.yzcdn.cn/vant/logo.png">
        <view slot="footer">
          <van-button type="danger" round size="small" icon="delete" @click="onClickRemove">删除</van-button>
          <van-button round size="small" icon="manager" @click="onClickManage" >管理</van-button>
        </view>
      </van-card>
    </van-row>
    <van-row>
      <van-card desc="我的描述描述" title="后端系统基础知识1" currency="-" price="2021-12-22" thumb="https://img.yzcdn.cn/vant/logo.png">
        <view slot="footer">
          <van-button type="danger" round size="small" icon="delete" @click="onClickRemove">删除</van-button>
          <van-button round size="small" icon="manager" @click="onClickManage" >管理</van-button>
        </view>
      </van-card>
    </van-row>
    <van-action-sheet :show="actionSheet.show" :actions="actionSheet.actions" @close="actionSheet.onClose" @select="actionSheet.onSelect"/>
    <van-toast id="van-toast" />
  </van-row>
</template>

<script>
import Toast from '@vant/weapp/dist/toast/toast'
import http from '@/utils/request'

export default {
  data () {
    return {
      actionSheet: {
        show: false,
        actions: [
          {
            id: 1,
            name: '编辑试题'
          },
          {
            id: 2,
            name: '题目管理'
          },
          {
            id: 3,
            name: '查看成绩排行榜'
          },
          {
            id: 4,
            name: '分享试题',
            openType: 'share'
          }
        ],
        onClose: () => {
          this.actionSheet.show = false
        },
        onSelect: (event) => {
          console.log(event)
        }
      }
    }
  },

  components: {

  },

  methods: {
    routeTest () {
      // this.$router.push('/pages/index/main')
      http.post('/exam/list', {}).then((response) => {

      })
    },
    onClickManage () {
      this.actionSheet.show = true
    },
    onClickRemove () {
      Toast.success('删除成功')
    },
    tocreate () {

    }
  },

  created () {
    // let app = getApp()
  }
}
</script>

<style scoped>
.red-font {
  font: red;
}
.top-margin {
  padding: 20px 0;
}
.grey-bg {
  background-color: rgb(17, 17, 17);
}
</style>
