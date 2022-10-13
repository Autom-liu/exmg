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
      <el-aside width="400px">
        <dept-tree @node-click="onNodeClick" />
      </el-aside>
      <el-main>
        <el-card>
          <div class="card-item">机构编号：{{ selected ? selected.deptId : '' }}</div>
          <div class="card-item">机构名称：{{ selected ? selected.deptName : '' }}</div>
          <div class="card-item">机构层级： {{ selected ? selected.deptLevel : '' }}</div>
          <div class="card-item">机构管理： {{ selected ? selected.deptManager : '' }}</div>
          <div class="card-item">机构电话： {{ selected ? selected.phone : '' }}</div>
          <div class="card-item">机构地址：{{ selected ? selected.address : '' }}</div>
        </el-card>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import DeptTree from './components/DeptTree'

export default {
  name: 'DepartmentList',
  components: { DeptTree },
  data() {
    return {
      queryLine: '',
      selected: null
    }
  },
  methods: {
    onNodeClick(data) {
      this.selected = data
    },
    onAdd() {
      this.$router.push({ name: 'DepartmentAdd', params: this.selected })
    },
    onEdit() {
      if (this.selected) {
        this.$router.push({ name: 'DepartmentEdit', params: this.selected })
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

<style scoped>
.card-item {
  margin: 10px 0;
}
</style>
