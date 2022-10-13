<template>
  <el-row>
    <el-col :span="6" style="min-height: 100px" />
    <el-col :span="12">
      <el-form label-width="120px">
        <el-form-item label="角色编号">
          <el-input v-model="form.roleId" />
        </el-form-item>
        <el-form-item label="角色名称">
          <el-input v-model="form.roleName" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="6" style="min-height: 100px" />
  </el-row>
</template>

<script>

export default {
  name: 'RolesForm',
  props: {
    isEdit: {
      default: false,
      type: Boolean
    },
    params: {
      default: () => ({}),
      type: Object
    }

  },
  data() {
    return {
      form: {
        roleId: '',
        roleName: ''
      }
    }
  },
  computed: {
  },
  created() {
    this.initPage()
  },
  methods: {
    initPage() {
      if (this.isEdit) {
        this.form = this.params
      }
    },
    onSubmit() {
      this.returnBackPage()
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'RolesList' })
    }

  }
}
</script>
