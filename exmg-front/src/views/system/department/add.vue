<template>
  <dept-form :is-edit="false" :params="params" />
</template>

<script>
import DeptForm from './components/DeptForm'

export default {
  name: 'DepartmentAdd',
  components: { DeptForm },
  props: {

  },
  data() {
    return {
      params: { deptId: 0, name: '顶级机构' }
    }
  },
  computed: {
  },
  created() {
    if (this.$route.params.deptId) {
      this.params = this.$route.params
    }
  },
  methods: {

  }
}
</script>
