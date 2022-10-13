<template>
  <el-container>
    <el-header>
      <el-button type="primary" round @click="toAssign">分配角色</el-button>
      <el-button type="primary" round @click="init">刷新</el-button>
    </el-header>
    <el-table :data="deptRoleList" border highlight-current-row>
      <el-table-column label="当前用户编号" width="180">
        <template>{{ userId }}</template>
      </el-table-column>
      <el-table-column label="当前用户名称" width="180">
        <template>{{ userEntity.userName }}</template>
      </el-table-column>
      <el-table-column prop="department.deptId" label="所属部门编号" width="180" />
      <el-table-column prop="department.deptName" label="所属部门编号" width="180" />
      <el-table-column prop="role.roleId" label="所属角色" width="180" />
      <el-table-column prop="role.roleName" label="所属部门名称" width="180" />
      <el-table-column prop="isDefault" label="默认" width="180">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isDefault" type="success" disable-transitions>默认</el-tag>
          <el-tag v-else type="info" disable-transitions>非默认</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="isDefault" label="默认" width="180">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="resign(scope.row)">解除分配</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-container>
</template>

<script>
import { userDetail } from '@/api/user'
import { resignUser } from '@/api/user'

export default {
  name: 'UsersDetail',
  components: { },
  props: {

  },
  data() {
    return {
      userId: '',
      userEntity: {},
      deptRoleList: []
    }
  },
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      return this.$route.path
    }
  },
  created() {
    this.userId = this.$route.params.userId
    this.userEntity = this.$route.params
    this.init()
  },
  destroyed() {

  },
  methods: {
    init() {
      if (this.userId) {
        userDetail({ userId: this.userId }).then((response) => {
          if (response.code === '0000') {
            this.deptRoleList = response.data.deptRoleList
          }
        })
      }
    },
    toAssign() {
      if (this.userId) {
        this.$router.push({ name: 'UsersAssign', params: this.userEntity })
      }
    },
    resign(data) {
      const tip = '请确认用户和部门角色关系是否解除关联'
      this.$confirm(tip, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' })
        .then(() => {
          resignUser({ userId: this.userId, deptId: data.department.deptId, roleId: data.role.roleId })
            .then((response) => {
              if (response.code === '0000') {
                this.$message({ type: 'success', message: '删除成功' })
              }
            })
        })
        .catch(() => {
          this.$message({ type: 'info', message: '已取消操作' })
        })
    }
  }
}
</script>
