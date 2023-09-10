<template>
  <el-container>
    <el-header>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="试题名称">
          <el-input v-model="query.examName" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="initData">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button-group>
            <el-button type="primary" icon="el-icon-plus" @click="toAdd" />
          </el-button-group>
        </el-form-item>
      </el-form>
    </el-header>
    <el-container>
      <exam-table :query="query" />
    </el-container>
  </el-container>
</template>

<script>
import ExamTable from './components/ExamTable'

export default {
  name: 'ExamList',
  components: { ExamTable },
  data() {
    return {
      query: {
        examName: ''
      },
      refresh: false
    }
  },
  activated() {
    this.initData()
  },
  methods: {
    toAdd() {
      this.$router.push({ name: 'ExamAdd' })
    },
    initData() {
      this.refresh = !this.refresh
    }
  }
}

</script>

<style scoped>
.card-item {
  margin: 10px 0;
}
</style>
