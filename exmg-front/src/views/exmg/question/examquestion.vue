<template>
  <el-container>
    <el-main>
      <el-button-group>
        <el-button type="primary" icon="el-icon-plus" @click="toAssign">添加</el-button>
      </el-button-group>
      <el-table :data="tableData" border highlight-current-row>
        <el-table-column prop="question" label="题目" />
        <el-table-column prop="questionType" label="题目类型" width="80">
          <template slot-scope="scope">
            {{ dict.questionTypeDict[scope.row.questionType] }}
          </template>
        </el-table-column>
        <el-table-column prop="score" label="分值" width="80" />
        <el-table-column prop="sorted" label="序号" width="80" />
        <el-table-column prop="difficulty" label="难度" width="80" />
        <el-table-column prop="categoryId" label="分类" width="80" />
        <el-table-column prop="common" label="公共题库" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.common" type="success" disable-transitions>是</el-tag>
            <el-tag v-else type="info" disable-transitions>否</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination layout="prev, pager, next" :page-count="pageInfo.totalPage" @current-change="onPageChange" />
    </el-main>
  </el-container>
</template>

<script>
import { queryQuestionExam } from '@/api/question'

export default {
  name: 'ExamQuestion',
  data() {
    return {
      tableData: [],
      dict: {
        questionTypeDict: {
          '1': '单选',
          '2': '多选',
          '3': '填空简答'
        }
      },
      pageInfo: {

      },
      examInfo: {

      },
      query: {
        totalPage: 1,
        pageSize: 1000
      }
    }
  },
  created() {
    this.examInfo = this.$route.params
    this.query.examId = this.examInfo.id
    this.query.currentPage = 1
  },
  activated() {
    this.initData()
  },
  methods: {
    initData() {
      queryQuestionExam(this.query).then((response) => {
        if (response.code === '0000') {
          const { data, pageInfo } = response.data
          this.tableData = data
          this.pageInfo = pageInfo
        }
      })
    },
    toAssign() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'AssignExam', params: { examInfo: this.examInfo, tableData: this.tableData }})
    },
    onPageChange(p) {
      this.query.currentPage = p
      this.initData()
    }
  }
}

</script>

<style scoped>
.card-item {
  margin: 10px 0;
}
.top-margin {
  margin-top: 20px;
}
</style>
