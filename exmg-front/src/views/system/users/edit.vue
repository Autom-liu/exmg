<template>
  <user-form type="Edit" :user-id="userId" />
</template>

<script>
import UserForm from './components/UserForm'

export default {
  name: 'UsersEdit',
  components: { UserForm },
  props: {

  },
  data() {
    return {
      userId: ''
    }
  },
  computed: {
  },
  created() {
    this.userId = this.$route.params.userId
  },
  methods: {

  }
}
</script>
