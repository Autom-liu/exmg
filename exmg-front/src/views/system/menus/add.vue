<template>
  <menu-form :is-edit="false" :params="params" />
</template>

<script>
import MenuForm from './components/MenuForm.vue'

export default {
  name: 'MenusAdd',
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
