<template>
  <el-container>
    <exam-form :is-edit="false" :params="params" />
  </el-container>
</template>

<script>
import ExamForm from './components/ExamForm'

export default {
  name: 'ExamAdd',
  components: { ExamForm },
  data() {
    return {
      params: {}
    }
  },
  created() {
    this.params = this.$route.params
  },
  methods: {

  }
}

</script>

<style scoped>

</style>
