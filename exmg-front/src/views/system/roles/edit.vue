<template>
  <roles-form :is-edit="true" :params="params" />
</template>

<script>
import RolesForm from './components/RolesForm.vue'

export default {
  name: 'RolesEdit',
  components: {
    RolesForm
  },
  props: {

  },
  data() {
    return {
      params: {}
    }
  },
  computed: {
  },
  created() {
    this.initPage()
  },
  methods: {
    initPage() {
      this.params = this.$route.params
    }
  }
}
</script>
