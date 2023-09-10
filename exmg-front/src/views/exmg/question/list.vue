<template>
  <el-container>
    <el-header>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="题目名称">
          <el-input v-model="query.questionName" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="initData">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button-group>
            <el-button type="primary" icon="el-icon-plus" @click="toAdd">添加题目</el-button>
            <el-button type="primary" icon="el-icon-upload" @click="uploadDialog.onShow">上传题目</el-button>
            <el-button type="primary" icon="el-icon-upload" @click="toAssign">加入考试</el-button>
          </el-button-group>
        </el-form-item>
      </el-form>
    </el-header>
    <el-container>
      <question-table :query="query" :refresh="refresh" @table-click="onQuestionTableClick" />
    </el-container>
    <el-dialog title="上传对话框" :visible.sync="uploadDialog.show">
      <el-link type="primary" :href="`${BASE_URL}/admin/question/template/download`" target="_blank">下载导入模板</el-link>
      <el-upload
        class="top-margin"
        :action="`${BASE_URL}/admin/question/template/upload`"
        :on-preview="uploadDialog.fileCheck"
        :on-success="uploadDialog.onSuccess"
        :multiple="false"
        :limit="1"
        :file-list="uploadDialog.fileList"
      >
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">上传xlsx文件，大小不超过10MB</div>
      </el-upload>
    </el-dialog>
  </el-container>
</template>

<script>
import QuestionTable from './components/QuestionTable'

export default {
  name: 'QuestionList',
  components: { QuestionTable },
  data() {
    return {
      query: {
        questionName: ''
      },
      qstTable: {
        selectedRows: []
      },
      BASE_URL: process.env.VUE_APP_BASE_API,
      refresh: false,
      uploadDialog: {
        show: false,
        fileList: [],
        onShow: () => {
          this.uploadDialog.show = true
        },
        onClose: () => {
          this.uploadDialog.show = false
        },
        fileCheck: (file) => {
          return true
        },
        onSuccess: (response, file, fileList) => {
          if (response.code === '0000') {
            this.uploadDialog.onClose()
            this.initData()
          }
        }
      }
    }
  },
  activated() {
    this.initData()
  },
  methods: {
    toAdd() {
      this.$router.push({ name: 'QuestionAdd' })
    },
    toAssign() {
      if (this.qstTable.selectedRows.length < 1) {
        this.$message({
          message: '请选择题目操作',
          type: 'warning'
        })
        return
      }
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'AssignExam', params: { questions: this.qstTable.selectedRows }})
    },
    initData() {
      this.refresh = !this.refresh
    },
    onQuestionTableClick(rows) {
      this.qstTable.selectedRows = rows
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
