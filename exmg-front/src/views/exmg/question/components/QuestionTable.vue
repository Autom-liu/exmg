<template>
  <el-main>
    <el-table ref="multipleTable" :data="tableData" border highlight-current-row @selection-change="onTableSelected">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="id" width="60" />
      <el-table-column prop="question" label="题目" />
      <el-table-column prop="questionType" label="题目类型" width="80">
        <template slot-scope="scope">
          {{ dict.questionTypeDict[scope.row.questionType] }}
        </template>
      </el-table-column>
      <el-table-column prop="difficulty" label="难度" width="80" />
      <el-table-column prop="categoryId" label="分类" width="80">
        <template slot-scope="scope">
          {{ examCategoryEnums[scope.row.categoryId] }}
        </template>
      </el-table-column>
      <el-table-column prop="common" label="公共题库" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.common" type="success" disable-transitions>是</el-tag>
          <el-tag v-else type="info" disable-transitions>否</el-tag>
        </template>
      </el-table-column>
      <el-table-column v-if="!selection" prop="interpretation" label="题目解析" width="80">
        <template slot-scope="scope">
          <el-link type="primary" @click="interpretationDialog.onShow(scope.row)">点击查看</el-link>
        </template>
      </el-table-column>
      <el-table-column v-if="!selection" prop="picsUrl" label="图片" width="120">
        <template slot-scope="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.picsUrl" :preview-src-list="[scope.row.picsUrl]" />
        </template>
      </el-table-column>
      <el-table-column v-if="!selection" prop="options" label="选项" width="80">
        <template slot-scope="scope">
          <el-link type="primary" @click="optionDialog.onShow(scope.row)">点击查看</el-link>
        </template>
      </el-table-column>
      <el-table-column v-if="!selection" label="操作" width="100">
        <template slot-scope="scope">
          <el-button :class="['btn-col']" size="small" type="info" icon="edit" @click="() => toEdit(scope.row)">编辑</el-button>
          <el-button :class="['btn-col']" size="small" type="danger" icon="delete" @click="() => onRemove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column v-if="!selection" label="操作" width="100">
        <template slot-scope="scope">
          <el-button size="small" @click="() => onChange(scope.row)">做题报告</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="prev, pager, next" :page-count="pageInfo.totalPage" @current-change="onPageChange" />

    <el-dialog title="题目选项" :visible.sync="optionDialog.show">
      <el-table :data="optionDialog.current">
        <el-table-column property="id" label="id" width="100" />
        <el-table-column property="option" label="选项描述" />
        <el-table-column property="right" label="是否正确" width="100">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.right" type="success" disable-transitions>正确</el-tag>
            <el-tag v-else type="info" disable-transitions>错误</el-tag>
          </template>
        </el-table-column>
        <el-table-column property="interpretation" label="选项解析" />
      </el-table>
    </el-dialog>
    <el-dialog title="题目解析" :visible.sync="interpretationDialog.show">
      {{ interpretationDialog.current }}
    </el-dialog>
  </el-main>
</template>

<script>
import { pageQuestionList, removeQuestion } from '@/api/question'

export default {
  name: 'QuestionTable',
  props: {
    selection: {
      type: Boolean,
      default: false
    },
    defaultSelection: {
      type: Array,
      default: () => []
    },
    query: {
      type: Object,
      default: () => {}
    },
    refresh: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      tableData: [],
      pageInfo: {
        totalPage: 1
      },
      optionDialog: {
        current: [],
        show: false,
        onShow: (row) => {
          this.optionDialog.current = [...row.options]
          this.optionDialog.show = true
        }
      },
      interpretationDialog: {
        show: false,
        current: '',
        onShow: (row) => {
          this.interpretationDialog.current = row.interpretation
          this.interpretationDialog.show = true
        }
      },
      dict: {
        questionTypeDict: {
          1: '单选',
          2: '多选',
          3: '填空简答'
        }
      }
    }
  },
  computed: {
    queryAction() {
      return pageQuestionList
    },
    examCategoryEnums() {
      return this.$store.getters['dataDict/examCategoryEnums']
    }
  },
  watch: {
    refresh() {
      this.initData()
    }
  },
  methods: {
    initData() {
      pageQuestionList(this.query).then((response) => {
        if (response.code === '0000') {
          const { pageInfo, data } = response.data
          this.pageInfo = pageInfo
          this.tableData = data
          this.initSelection()
        }
      })
    },
    initSelection() {
      const tableData = this.tableData
      const defaultSelection = this.defaultSelection
      for (const row of tableData) {
        for (const selected of defaultSelection) {
          if (row.id === selected.questionId) {
            this.$nextTick(() => {
              this.$refs.multipleTable.toggleRowSelection(row, true)
            })
          }
        }
      }
    },
    onTableSelected(rows) {
      this.$emit('table-click', rows)
    },
    onChange(row) {

    },
    toEdit(row) {
      this.$router.push({ name: 'QuestionEdit', params: row })
    },
    onRemove(row) {
      const tip = '请确认删除试题及其关联，删除操作不会删除关联关系，但会删除关联记录'
      this.$confirm(tip, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' })
        .then(() => {
          removeQuestion({ id: row.id }).then((response) => {
            this.$message({ type: 'success', message: '删除成功' })
            this.initData()
          })
        }).catch(() => {
          this.$message({ type: 'info', message: '已取消操作' })
        })
    },
    onPageChange(p) {
      this.query.currentPage = p
      this.initData()
    }
  }
}
</script>

<style>
.el-button.btn-col {
  margin-left: 0;
}
</style>
