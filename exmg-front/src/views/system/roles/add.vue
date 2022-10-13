<template>
  <roles-form :is-edit="false" />
</template>

<script>
import RolesForm from './components/RolesForm.vue'

export default {
  name: 'RolesAdd',
  components: {
    RolesForm
  },
  props: {

  },
  data() {
    return {

    }
  },
  computed: {
  },
  created() {
    this.initPage()
  },
  methods: {
    initPage() {

    }
  }
}
</script>
