<template>
  <el-main>
    <el-table :data="usersData" border highlight-current-row @current-change="onTableSelected">
      <el-table-column prop="userId" label="用户编号" width="180" />
      <el-table-column prop="userName" label="用户名称" width="180" />
      <el-table-column prop="mobile" label="手机号" width="180" />
      <el-table-column prop="email" label="邮箱" width="180" />
      <el-table-column prop="createTime" label="创建时间" width="180" />
      <el-table-column prop="status" label="状态" width="180">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 0" type="success" disable-transitions>正常</el-tag>
          <el-tag v-if="scope.row.status == 1" type="warning" disable-transitions>首次创建</el-tag>
          <el-tag v-if="scope.row.status == 2" disable-transitions>暂停</el-tag>
          <el-tag v-if="scope.row.status == 3" type="error" disable-transitions>离职</el-tag>
          <el-tag v-if="scope.row.status == 4" type="info" disable-transitions>未知</el-tag>
        </template>
      </el-table-column>
      <el-table-column v-if="details" label="用户部门角色分配情况" width="180">
        <template slot-scope="scope">
          <el-button size="mini" @click="toDetail(scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="prev, pager, next" :page-count="totalPage" @current-change="onPageChange" />
  </el-main>
</template>

<script>
import { pageUser } from '@/api/user'

export default {
  name: 'UsersList',
  props: {
    query: {
      default: () => {},
      type: Object
    },
    lazy: {
      default: false,
      type: Boolean
    },
    details: {
      default: false,
      type: Boolean
    }
  },
  data() {
    return {
      queryLine: '',
      selected: null,
      usersData: [],
      totalPage: 1
    }
  },
  watch: {
    query() {
      this.initUser()
    }
  },
  created() {
    if (!this.lazy) {
      this.initUser()
    }
  },
  methods: {
    initUser() {
      pageUser(this.query).then((response) => {
        this.totalPage = response.data.pageInfo.totalPage
        this.usersData = response.data.data
      }).catch(error => {
        console.error(error)
      })
    },
    onTableSelected(row) {
      this.selected = row
      this.$emit('table-click', row)
    },
    onPageChange(p) {

    },
    toDetail(data) {
      this.$router.push({ name: 'UsersDetail', params: data })
    }
  }
}
</script>
