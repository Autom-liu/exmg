<template>
  <el-main>
    <el-table :data="tableData" border highlight-current-row @current-change="onTableSelected">
      <el-table-column v-if="selection" type="selection" width="55" />
      <el-table-column prop="examName" label="考试名称" />
      <el-table-column prop="examRemark" label="考试说明" />
      <el-table-column prop="totalScore" label="总分" width="80" />
      <el-table-column prop="answerMode" label="答题模式" width="80" />
      <el-table-column prop="maxCount" label="最大答题次数" width="80" />
      <el-table-column prop="banner" label="是否展示首页" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.banner" type="success" disable-transitions>是</el-tag>
          <el-tag v-else type="info" disable-transitions>否</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="autoGenerate" label="是否随机生成" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.autoGenerate" type="success" disable-transitions>是</el-tag>
          <el-tag v-else type="info" disable-transitions>否</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="begintime" label="开始时间" width="180">
        <template slot-scope="scope">
          {{ parseTime(scope.row.begintime, '{y}-{m}-{d}') }}
        </template>
      </el-table-column>
      <el-table-column prop="endtime" label="结束时间" width="180">
        <template slot-scope="scope">
          {{ parseTime(scope.row.endtime, '{y}-{m}-{d}') }}
        </template>
      </el-table-column>
      <el-table-column label="封面缩略图" width="180">
        <template slot-scope="scope">
          <el-button size="small" @click="() => seeShortcut(scope.row)">查看缩略图</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status" type="success" disable-transitions>正常</el-tag>
          <el-tag v-else type="info" disable-transitions>失效</el-tag>
        </template>
      </el-table-column>
      <el-table-column v-if="!selection" label="操作" width="180">
        <template slot-scope="scope">
          <el-button size="small" type="info" icon="edit" @click="() => toEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" icon="delete" @click="() => onRemove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column v-if="!selection" label="操作" width="200">
        <template slot-scope="scope">
          <el-button size="small" @click="() => toExamQuestion(scope.row)">添加题目</el-button>
          <el-button size="small" @click="() => onChange(scope.row)">查看成绩</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="prev, pager, next" :page-count="totalPage" @current-change="onPageChange" />
    <el-dialog :visible.sync="shortcutDialog.show">
      <img width="100%" :src="shortcutDialog.src" alt="上传图片返回地址有误或未上传">
    </el-dialog>
  </el-main>
</template>

<script>
import { fetchExamList, removeExam } from '@/api/exam'
import { parseTime } from '@/utils'

export default {
  name: 'ExamTable',
  props: {
    selection: {
      type: Boolean,
      default: false
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
      totalPage: 0,
      shortcutDialog: {
        show: false,
        src: ''
      },
      parseTime
    }
  },
  watch: {
    refresh() {
      this.initData()
    }
  },
  methods: {
    initData() {
      fetchExamList(this.query).then((response) => {
        this.tableData = response.data
      })
    },
    onTableSelected(row) {
      this.selected = row
      this.$emit('table-click', this.selected)
    },
    seeShortcut(row) {
      this.shortcutDialog.src = row.picUrl
      this.shortcutDialog.show = true
    },
    onChange() {},
    toEdit(row) {
      this.$router.push({ name: 'ExamEdit', params: row })
    },
    onRemove(row) {
      const tip = '请确认删除试题及其关联，删除操作不会删除关联关系，但会删除关联记录'
      this.$confirm(tip, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' })
        .then(() => {
          removeExam({ id: row.id }).then((response) => {
            if (response.code === '0000') {
              this.$message({ type: 'success', message: '删除成功' })
              this.initData()
            }
          })
        })
        .catch(() => {
          this.$message({ type: 'info', message: '已取消操作' })
        })
    },
    onPageChange(p) {

    },
    toExamQuestion(row) {
      this.$router.push({ name: 'ExamQuestion', params: row })
    }
  }
}
</script>

