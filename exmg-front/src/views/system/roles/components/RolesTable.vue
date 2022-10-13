<template>
  <el-main>
    <el-table :data="usersData" border highlight-current-row @current-change="onTableSelected">
      <el-table-column prop="roleId" label="角色编号" width="180" />
      <el-table-column prop="roleName" label="角色名称" width="180" />
      <el-table-column prop="remark" label="备注" width="180" />
      <el-table-column prop="status" label="状态" width="180" />
    </el-table>
    <el-pagination layout="prev, pager, next" :page-count="totalPage" @current-change="onPageChange" />
  </el-main>
</template>

<script>
import { pageRole } from '@/api/role'

export default {
  name: 'RolesTable',
  data() {
    return {
      selected: null,
      usersData: [],
      totalPage: 1
    }
  },
  created() {
    this.onNodeClick()
  },
  methods: {
    onNodeClick() {
      pageRole().then((response) => {
        this.totalPage = response.data.pageInfo.totalPage
        this.usersData = response.data.data
      })
    },
    onTableSelected(row) {
      this.selected = row
      this.$emit('table-click', this.selected)
    },
    onPageChange(p) {

    }
  }
}
</script>

