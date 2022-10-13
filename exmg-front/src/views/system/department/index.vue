<template>
  <keep-alive :include="cachedViews">
    <router-view :key="key" />
  </keep-alive>
</template>

<script>
export default {
  name: 'DeptIndex',
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      return this.$route.path
    }
  },
  created() {
    const name = this.$options.name
    const fakeRoute = { ...this.$route }
    fakeRoute.name = name
    this.$store.dispatch('tagsView/addCachedView', fakeRoute)
  }
}
</script>
