<template>
  <el-table
    ref="asyncTreeTable"
    :data="menusData"
    style="width: 100%"
    row-key="menuId"
    border
    lazy
    highlight-current-row
    :load="loadNode"
    :tree-props="treeProps"
    @current-change="onNodeClick"
    @selection-change="handleSelectionChange"
    @select="onMenuSelect"
    @select-all="onSelectAll"
  >
    <el-table-column v-if="multiselect" type="selection" width="55" />
    <el-table-column prop="menuId" label="菜单编号" />
    <el-table-column prop="menuName" label="菜单名称" />
    <el-table-column prop="menuType" label="菜单类型">
      <template slot-scope="scope">
        <el-tag v-if="scope.row.menuType == 0" disable-transitions>菜单</el-tag>
        <el-tag v-if="scope.row.menuType == 1" type="success" disable-transitions>页面</el-tag>
        <el-tag v-if="scope.row.menuType == 2" type="warning" disable-transitions>按钮</el-tag>
        <el-tag v-if="scope.row.menuType == 3" type="info" disable-transitions>权限</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="url" label="地址" />
    <el-table-column prop="remark" label="备注" />
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button size="mini" @click="refreshNode(scope.row)">刷新</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { listMenu } from '@/api/role'

export default {
  name: 'MenuTree',
  props: {
    query: {
      default: null,
      type: Object
    },
    multiselect: {
      default: false,
      type: Boolean
    }
  },
  data() {
    return {
      queryLine: '',
      menusData: [],
      treeProps: {
        children: 'children',
        hasChildren: 'hasChildren'
      },
      toggleAll: false,
      menuNode: new Map()
    }
  },
  computed: {
    lazyTreeNodeMap() {
      return this.$refs.asyncTreeTable.store.states.lazyTreeNodeMap
    }
  },
  watch: {

  },
  created() {
    this.fetchData({ parentId: '0' }, (data) => {
      this.menusData = data
    })
  },
  mounted() {

  },
  methods: {
    loadNode(tree, treeNode, resolve) {
      // this.menuNode.set(tree.menuId, { tree, treeNode, resolve })
      const parentId = tree ? tree.menuId : '0'
      this.fetchData({ parentId }, resolve)
      this.$nextTick(() => {
        this.menuNode.set(tree.menuId, { tree, treeNode, resolve })
      })
    },
    fetchData(params, resolve) {
      listMenu(params).then(response => {
        const { data } = response
        for (const item of data) {
          item.hasChildren = !item.leaf
        }
        resolve(data)
      }).catch(error => {
        console.error(error)
      })
    },
    /**
     * @selection 已选中的节点
     * @row  当前被选中的节点
     */
    onMenuSelect(selection, row) {
      // 选中父节点时，子节点一起选中或取消
      const type = selection.some((el) => row.menuId === el.menuId)
      this.toggleChildren(this.lazyTreeNodeMap, row.menuId, type)
    },
    /**
     * 选择/取消选择子节点
     * @treeMap 树节点的链式结构
     * @key  树节点id，即menuid
     * @type true： 选中 false 取消
     */
    toggleChildren(treeMap, key, type) {
      const children = treeMap[key]
      if (children) {
        for (const child of children) {
          this.toggleSelection(child, type)
          if (child.hasChildren && treeMap[child.menuId]) {
            this.toggleChildren(treeMap, child.menuId, type)
          }
        }
      }
    },
    /**
     * 选择/取消选择一行
     * @row 一行
     * @type true： 选中 false 取消
     */
    toggleSelection(row, type) {
      if (row) {
        this.$nextTick(() => {
          this.$refs.asyncTreeTable.toggleRowSelection(row, type)
        })
      }
    },
    onSelectAll(selection) {
      this.toggleAll = !this.toggleAll
      for (const k in this.lazyTreeNodeMap) {
        const children = this.lazyTreeNodeMap[k]
        for (const child of children) {
          this.toggleSelection(child, this.toggleAll)
        }
      }
    },
    onNodeClick(data) {
      this.$emit('node-click', data)
    },
    handleSelectionChange(value) {
      this.$emit('table-selected', value)
    },
    refreshNode(node) {
      const refreshParams = this.menuNode.get(node.menuId)
      if (refreshParams) {
        const { tree, treeNode, resolve } = refreshParams
        this.$set(this.lazyTreeNodeMap, node.menuId, [])
        this.loadNode(tree, treeNode, resolve)
      }
    }
  }
}

</script>

