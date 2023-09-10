<template>
  <el-container>
    <el-main>
      <el-row style="width: 100%">
        <el-col :span="6" style="min-height: 100px" />
        <el-col :span="12">
          <el-form label-width="120px">
            <el-form-item label="题目列表">
              <el-input type="textarea" autosize :value="questionTexts" disabled>
              </el-input>
            </el-form-item>
            <el-form-item label="考试编号">
                <el-row style="width: 100%">
                  <el-col :span="12">
                    <el-select v-model="form.attrs.examId" disabled placeholder="试题必选">
                      <el-option :key="examDialog.selectedRow.id" :label="`${examDialog.selectedRow.id} - ${examDialog.selectedRow.examName}`" :value="examDialog.selectedRow.id" />
                    </el-select>
                  </el-col>
                  <el-col :span="4" style="padding-top: 1px"></el-col>
                  <el-col :span="8">
                    <el-button type="primary" round @click="examDialog.onShow">选择考试</el-button>
                  </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="分数">
              <el-input v-model="form.attrs.score" />
            </el-form-item>
            <el-form-item label="排序">
              <el-input v-model="form.attrs.sorted" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">新增题目</el-button>
              <el-button type="primary" @click="onRemove">移除题目</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-main>
    <el-dialog title="考试列表" :visible="examDialog.show" @close="examDialog.onClose">
      <exam-table selection simple :query="examDialog.query" refresh @table-click="examDialog.onSelectedRows" />
    </el-dialog>

  </el-container>
</template>

<script>
import ExamTable from '../exam/components/ExamTable'
import { resignExamQuestion } from '@/api/question'

export default {
  name: 'AssignExam',
  components: { ExamTable },
  data() {
    return {
      dialogTableVisible: false,
      questions: [],
      examDialog: {
        show: false,
        query: {
          autoGenerate: false,
          status: true
        },
        selectedRow: { id: 0},
        refresh: false,
        onShow: () => {
          this.examDialog.refresh = this.$set(this.examDialog, 'refresh', !this.examDialog.refresh)
          this.examDialog.show = true
        },
        onSelectedRows: (row) => {
          this.examDialog.selectedRow = row
          this.form.attrs.examId = row.id
          this.examDialog.show = false
        },
        onClose: () => {
          this.examDialog.show = false
        }
      },
      query: {

      },
      form: {
        attrs: {
          op: '',
          examId: null,
          score: 0,
          sorted: -1
        },
        questionIds: []
      }
    }
  },
  computed: {
    questionTexts() {
      return this.questions.map(x => `${x.id} - ${x.question}`).join('\n')
    }
  },
  created() {

  },
  activated() {
    this.initPage()
  },
  methods: {
    initPage() {
      this.questions = this.$route.params.questions
      this.form.questionIds = this.questions.map(x => x.id)
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'QuestionList' })
    },
    onSubmit() {
      this.form.attrs.op = 'A'
      this.submit()
    },
    onRemove() {
      this.form.attrs.op = 'D'
      this.submit()
    },
    submit() {
      console.log({...this.form})
      resignExamQuestion({...this.form})
    }
  }

}
</script>
