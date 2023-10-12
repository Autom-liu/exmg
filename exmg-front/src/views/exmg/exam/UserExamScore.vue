<template>
  <el-main>
    <el-table :data="tableData" border highlight-current-row>
      <el-table-column v-if="selection" type="selection" width="55" />
      <el-table-column prop="userId" label="用户id" />
      <el-table-column prop="totalNum" label="总题数" />
      <el-table-column prop="rightNum" label="正确题数" />
      <el-table-column prop="wrongNum" label="错误题数" />
      <el-table-column prop="answerTime" label="答题时间" />
      <el-table-column prop="answerNum" label="答题次数" />
      <el-table-column label="操作" />
    </el-table>
  </el-main>
</template>

<script>
import { fetchExamList, removeExam } from '@/api/exam'
import { parseTime } from '@/utils'

export default {
  name: 'userExamScore',
  data() {
    return {
      tableData: []
    }
  },
  computed: {

  },
  created() {

  },
  activated() {

  },
  methods: {
    initData() {

    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'ExamList' })
    }
  }
}
</script>

