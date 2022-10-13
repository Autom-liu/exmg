<template>
  <el-container>
    <el-main>
      <el-steps :active="active" finish-status="success">
        <el-step title="选择题目" />
        <el-step title="设置属性" />
        <el-step title="完成" />
      </el-steps>
      <el-button-group style="margin-top: 12px;">
        <el-button @click="next">下一步</el-button>
        <el-button v-if="active === 0" @click="questionField.commitSelect">选定题目</el-button>
      </el-button-group>

      <question-table v-if="active === 0" selection :refresh="questionField.refresh" :query="query" :default-selection="questionField.submitRows" @table-click="questionField.onSelect" />
    </el-main>
  </el-container>
</template>

<script>
import QuestionTable from './components/QuestionTable'
import { resignExamQuestion } from '@/api/question'

export default {
  name: 'AssignExam',
  components: { QuestionTable },
  data() {
    return {
      active: 0,
      query: {
        currentPage: 1,
        pageSize: 1000
      },
      examInfo: {},
      questionField: {
        show: false,
        refresh: false,
        currentPage: 1,
        rows: [],
        submitRows: [],
        onShow: () => {
          this.questionField.show = true
          this.$nextTick(() => {
            this.questionField.refresh = !this.questionField.refresh
            this.questionField.submitRows = this.$route.params.tableData
            this.questionField.currentPage = this.query.currentPage
          })
        },
        onSelect: (rows) => {
          this.questionField.rows = rows
        },
        commitSelect: () => {
          this.questionField.submitRows = this.questionField.rows
        },
        onSubmit: () => {
          const exqtList = []
          const examId = this.examInfo.id
          for (const item of this.questionField.submitRows) {
            exqtList.push({ examId, questionId: item.id, sorted: item.id })
          }
          const submitData = { examId, exqtList }
          resignExamQuestion(submitData).then((response) => {
            this.$message({ message: response.msg, type: 'success' })
          })
        }
      }
    }
  },
  created() {
    this.examInfo = this.$route.params.examInfo
  },
  activated() {
    this.questionField.onShow()
  },
  methods: {
    next() {
      if (this.active === 0) {
        this.active++
      } else if (this.active === 1) {
        this.questionField.onSubmit()
        this.active++
      } else {
        this.returnBackPage()
      }
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'ExamList' })
    }
  }

}
</script>
