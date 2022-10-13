<template>
  <el-tree :props="treeProps" :load="loadNode" lazy @node-click="onNodeClick" />
</template>

<script>
import { listDepartment } from '@/api/department'

export default {
  name: 'DeptTree',
  props: {
    query: {
      default: null,
      type: Object
    }
  },
  data() {
    return {
      queryLine: '',
      treeProps: {
        label: 'deptName',
        children: 'children',
        isLeaf: 'isLeaf'
      }
    }
  },
  methods: {
    loadNode(node, resolve) {
      const parentId = node.data ? node.data.deptId : '0'

      listDepartment({ parentId }).then(response => {
        resolve(response.data)
      }).catch(error => {
        console.error(error)
      })
    },
    onNodeClick(data) {
      this.$emit('node-click', data)
    }
  }
}

</script>

