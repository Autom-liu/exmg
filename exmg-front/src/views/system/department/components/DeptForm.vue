<template>
  <el-row>
    <el-col :span="6" style="min-height: 100px" />
    <el-col :span="12">
      <el-form label-width="120px">
        <el-form-item label="机构名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="父机构">
          <el-select v-model="form.parentId" disabled>
            <el-option :key="params.deptId" :label="params.name" :value="params.deptId" />
          </el-select>
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
  name: 'DeptForm',
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
        name: '',
        parentId: 0
      }
    }
  },
  computed: {

  },
  created() {
    this.form.parentId = this.params && this.params.deptId ? this.params.deptId : '0'
  },
  methods: {
    onSubmit() {
      this.returnBackPage()
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'departmentList' })
    }
  }
}
</script>
