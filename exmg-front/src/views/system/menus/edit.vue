<template>
  <menu-form :is-edit="true" :params="params" />
</template>

<script>
import MenuForm from './components/MenuForm.vue'

export default {
  name: 'MenusEdit',
  components: {
    MenuForm
  },
  data() {
    return {
      params: {}
    }
  },
  created() {
    this.params = this.$route.params
  }
}
</script>

<style>

</style>
