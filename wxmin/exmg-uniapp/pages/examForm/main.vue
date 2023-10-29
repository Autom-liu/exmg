<template>
  <van-row class="grey-bg">
    <van-cell-group class="padding">
      <van-field :value="form.examName" label="试题名称" placeholder="试题名称" :border="false"/>
      <van-field :value="form.examRemark" label="试题说明" type="textarea" placeholder="试题说明" autosize :border="false"/>
      <van-field :value="form.userId" label="出题人" disabled :border="false"/>
      <van-field  type="number" :value="form.totalScore" label="总分" placeholder="总分" :border="false" :error-message="numberChecker.totalScore.tip" :error="numberChecker.totalScore.error" />
      <van-field type="number" :value="form.maxCount" label="最大考试次数" placeholder="最大考试次数" :border="false" :error-message="numberChecker.maxCount.tip" :error="numberChecker.maxCount.error" @blur="(value) => numberCheck('maxCount', value)"/>
      <van-cell title="缩略图" />
      <van-uploader accept="image" :max-count="1" :file-list="imgList" @afterRead="uploadImg" use-before-read @beforeRead="checkImg" @delete="removeImg" />
      <van-cell title="是否展示首页">
        <van-switch :checked="form.banner" @change="bannerSwitch" />
      </van-cell>
      <van-cell title="是否随机生成题目">
        <van-switch :checked="form.autoGenerate"  @change="autogenSwitch" />
      </van-cell>
      <van-cell title="考试开始时间" :value="form.begintimeLabel" @click="beginTimeCalendar.onShow" />
      <van-cell title="考试结束时间" :value="form.endtimeLabel" @click="endTimeCalendar.onShow" />
    </van-cell-group>
    <van-row>
      <van-col span="16" offset="4">
        <van-button round type="info" size="large" @click="onSubmit">确认创建试题</van-button>
      </van-col>
    </van-row>
    <van-calendar :show="beginTimeCalendar.show" :show-confirm="false" @close="beginTimeCalendar.onClose" @confirm="beginTimeCalendar.onConfirm" />
    <van-calendar :show="endTimeCalendar.show" :show-confirm="false" @close="endTimeCalendar.onClose" @confirm="endTimeCalendar.onConfirm" />
  </van-row>
</template>

<script>
import http from '@/utils/request'
import { formatDate, getFileName } from '@/utils/index'

export default {
  data () {
    return {
      form: {
        id: 0,
        examName: '',
        examRemark: '拿开了解放军恶无符号的我李开复女人哦个i',
        userFrom: 2,
        userId: '',
        totalScore: 100,
        answerMode: 1,
        maxCount: 1,
        picUrl: '',
        banner: true,
        autoGenerate: false,
        begintime: new Date(),
        begintimeLabel: '',
        endtime: new Date('2099-12-31'),
        endtimeLabel: ''
      },
      imgList: [],
      beginTimeCalendar: {
        show: false,
        onShow: () => {
          this.beginTimeCalendar.show = true
        },
        onClose: () => {
          this.beginTimeCalendar.show = false
        },
        onConfirm: (event) => {
          this.form.begintime = event.mp.detail
          this.form.begintimeLabel = formatDate(event.mp.detail)
          this.beginTimeCalendar.onClose()
        }
      },
      endTimeCalendar: {
        show: false,
        onShow: () => {
          this.endTimeCalendar.show = true
        },
        onClose: () => {
          this.endTimeCalendar.show = false
        },
        onConfirm: (event) => {
          this.form.endtime = event.mp.detail
          this.form.endtimeLabel = formatDate(event.mp.detail)
          this.endTimeCalendar.onClose()
        }
      },
      numberChecker: {
        tip: '必须填入数字',
        totalScore: {
          error: false,
          tip: ''
        },
        maxCount: {
          error: false,
          tip: ''
        }
      }
    }
  },

  components: {

  },

  methods: {
    numberCheck (field, event) {
      const { value } = event.mp.detail
      this.form[field] = value
      if (!/^[0-9]+$/.test(value)) {
        this.numberChecker[field].error = true
        this.numberChecker[field].tip = this.numberChecker.tip
      } else {
        this.numberChecker[field].error = false
        this.numberChecker[field].tip = ''
      }
    },
    bannerSwitch (value) {
      this.form.banner = !this.form.banner
    },
    autogenSwitch (value) {
      this.form.autoGenerate = !this.form.autoGenerate
    },
    uploadImg (event) {
      const { file } = event.mp.detail
      http.upload('/wx/savetofile', file).then((response) => {
        const { code, data } = response
        if (code === '0000') {
          this.imgList = this.imgList.concat([{ url: data, name: getFileName(data) }])
        }
      })
    },
    checkImg (event) {
      const { callback } = event.mp.detail
      // eslint-disable-next-line standard/no-callback-literal
      callback(true)
    },
    removeImg (event) {
      // eslint-disable-next-line no-unused-vars
      const { file, index } = event.mp.detail
      this.imgList.splice(index, 1)
    },
    onSubmit () {

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
.padding {
  padding: 100px;
}
.grey-bg {
  background-color: #666;
}
</style>
