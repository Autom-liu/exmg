<template>
  <el-container>
    <el-header>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="编号或名称">
          <el-input v-model="queryLine" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button-group>
            <el-button type="primary" icon="el-icon-plus" @click="onAdd" />
            <el-button type="primary" icon="el-icon-edit" @click="onEdit" />
            <el-button type="primary" icon="el-icon-delete" @click="onRemove" />
          </el-button-group>
        </el-form-item>
      </el-form>
    </el-header>
    <el-container>
      <roles-table :query="queryLine" />
    </el-container>
  </el-container>
</template>

<script>
import RolesTable from './components/RolesTable'

export default {
  name: 'RsersList',
  components: { RolesTable },
  data() {
    return {
      queryLine: ''
    }
  },
  created() {

  },
  methods: {
    onAdd() {
      this.$router.push({ name: 'RolesAdd' })
    },
    onEdit() {
      if (this.selected) {
        this.$router.push({ name: 'RolesEdit', params: this.selected })
      }
    },
    onRemove() {
      if (this.selected) {
        const tip = '请确认用户和权限关系是否解除关联，删除操作不会删除关联关系'
        this.$confirm(tip, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' })
          .then(() => {
            this.$message({ type: 'success', message: '删除成功' })
          })
          .catch(() => {
            this.$message({ type: 'info', message: '已取消操作' })
          })
      }
    }
  }
}

</script>

