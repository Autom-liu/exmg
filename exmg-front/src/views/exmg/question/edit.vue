<template>
  <el-container>
    <question-form is-edit :params="params" />
  </el-container>
</template>

<script>
import QuestionForm from './components/QuestionForm'

export default {
  name: 'QuestionAdd',
  components: { QuestionForm },
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
