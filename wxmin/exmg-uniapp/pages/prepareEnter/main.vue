<template>
  <div class="btn-wrapper" @click="closeRemark">
    <div class="btn-box">
        <div class="btn-item"><van-button round block color="linear-gradient(to right, #FF4500, #DC143C)" @click="toStartExam">开始答题</van-button></div>
        <div class="btn-item"><van-button round block color="linear-gradient(to right, #FF4500, #DC143C)">查看排名</van-button></div>
        <div class="btn-item"><van-button round block color="linear-gradient(to right, #FF4500, #DC143C)" @click.stop="showRemark">考试说明</van-button></div>
    </div>
    <div class="exam-remark" v-show="remarkShow">
        <van-row>考试名称: {{examInfo.examName}}</van-row>
        <van-row>考试说明: {{examInfo.examRemark}}</van-row>
        <van-row>开始时间: {{examInfo.begintime}}</van-row>
        <van-row>截止时间: {{examInfo.endtime}}</van-row>
    </div>
  </div>
</template>

<script>

export default {
  components: {

  },
  data () {
    return {
      examInfo: {},
      remarkShow: false
    }
  },
  computed: {

  },

  methods: {
    showRemark () {
      this.remarkShow = true
    },
    closeRemark () {
      this.remarkShow = false
    },
    toStartExam () {
      this.$router.replace({path: '/pages/startExam/main', query: this.examInfo})
    }
  },
  created () {

  },
  mounted () {
    this.examInfo = this.$route.query
  }
}
</script>

<style scope>
.btn-wrapper {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url('https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.huabanimg.com%2Fc61f9c3075d166e70f4671bb6b16e23bed45383c42587-dZTcSs_fw658&refer=http%3A%2F%2Fhbimg.huabanimg.com&app=2002&size=f300,500&q=a80&n=0&g=0n&fmt=jpeg');
    background-size: cover;
}
.btn-box {
    width: 250px;
    height: 250px;
}
.btn-item {
    font-size: 16px;
    font-weight: 600;
    margin: 16px 0;
}
.exam-remark {
    position: absolute;
    width: 250px;
    height: 250px;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
    background-color: #f5f5f5;
}
</style>


