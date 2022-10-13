<template>
  <el-container>
    <el-header>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button-group>
            <el-button type="primary" icon="el-icon-plus" @click="onAdd" />
            <el-button type="primary" icon="el-icon-edit" @click="onEdit" />
            <el-button type="primary" icon="el-icon-delete" @click="onRemove" />
            <el-button type="primary" icon="el-icon-setting" @click="toPermission">菜单权限分配</el-button>
          </el-button-group>
        </el-form-item>
      </el-form>
    </el-header>
    <menu-tree @node-click="onNodeClick" />
  </el-container>
</template>

<script>
import MenuTree from './components/MenuTree'
import { MessageBox } from 'element-ui'
import { removeMenu } from '@/api/role'

export default {
  name: 'MenusList',
  components: {
    MenuTree
  },
  data() {
    return {
      selected: null
    }
  },
  methods: {
    onNodeClick(data) {
      this.selected = data
    },
    onAdd() {
      this.$router.push({ name: 'MenusAdd', params: this.selected })
    },
    onEdit() {
      this.$router.push({ name: 'MenusEdit', params: this.selected })
    },
    toPermission() {
      this.$router.push({ name: 'Permission', params: this.selected })
    },
    onRemove() {
      MessageBox.confirm('你将删除菜单', '确认删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        removeMenu({ menuId: this.selected.menuId }).then((response) => {
          if (response.code === '0000') {
            this.$message({ type: 'success', message: '已成功删除，请点击上一节点的刷新按钮更新列表' })
          }
        })
      })
    },
    deactivated() {
    }
  }

}
</script>

<style>

</style>
