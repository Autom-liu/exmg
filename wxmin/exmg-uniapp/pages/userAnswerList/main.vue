<template>
  <div class="page-container">
    <div class="list-wrapper">
      <div class="list-title">答题列表</div>
      <div class="list-inner">
        <div v-for="item in examList" :key="item.id" class="list-table" @click="() => toDetail(item)">
          <div class="list-info">
            <div class="title">{{item.examInfo.examName}}</div>
            <div class="time">{{ item.record.timeRemark }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import http from '@/utils/request'
import commonTool from '@/utils/index'

export default {
  components: {

  },
  data () {
    return {
      examList: []
    }
  },
  computed: {

  },
  methods: {
    fetchExamList (query) {
      const userInfo = http.getUserInfo()
      const params = { userId: userInfo.userId, ...query }
	  http.post('/question/user/answer/record', params).then((response) => {
		if (response.code === '0000') {
			const originData = response.data;
			for( const item of originData) {
				item.record.timeRemark = commonTool.formatTime(new Date(item.record.endTime))
			}
			this.examList = originData
		}
	  })
    },
    toDetail (item) {
      const { userId } = http.getUserInfo()
	  console.log(item)
	  const query = {examId: item.examInfo.id, recordId: item.record.id, userId}
	  uni.navigateTo({
	  	url:`/pages/endExam/main?query=${JSON.stringify(query)}`
	  })
    }
  },
  onLoad (option) {
	const query = JSON.parse(option.query)
    this.fetchExamList(query)
  }
}
</script>

<style scope>
.page-container {
  padding: 20px 16px;
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
  margin-bottom: 12px;
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
