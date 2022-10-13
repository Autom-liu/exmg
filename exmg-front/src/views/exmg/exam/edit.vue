<template>
  <el-container>
    <exam-form is-edit :params="params" />
  </el-container>
</template>

<script>
import ExamForm from './components/ExamForm'

export default {
  name: 'ExamEdit',
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
.card-item {
  margin: 10px 0;
}
</style>
