<template>
  <el-row>
    <el-col :span="6" style="min-height: 100px" />
    <el-col :span="12">
      <el-form ref="addForm" label-width="120px" :model="form" :rules="formRules">
        <el-form-item label="菜单编号" prop="menuId">
          <el-input v-model="form.menuId" :disabled="isEdit" />
        </el-form-item>
        <el-form-item label="菜单名称" prop="menuName">
          <el-input v-model="form.menuName" />
        </el-form-item>
        <el-form-item label="父菜单编号" prop="parentId">
          <el-input v-model="form.parentId" disabled />
        </el-form-item>
        <el-form-item label="菜单类型" prop="menuType">
          <el-select v-model="form.menuType" placeholder="请选择菜单类型">
            <el-option label="菜单" value="0" />
            <el-option label="页面" value="1" />
            <el-option label="按钮" value="2" />
            <el-option label="权限" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="菜单地址" prop="url">
          <el-input v-model="form.url" />
        </el-form-item>
        <el-form-item label="菜单排序" prop="order">
          <el-input v-model="form.order" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">{{ isEdit ? '编辑' : '新增' }}</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="6" style="min-height: 100px" />
  </el-row>
</template>

<script>
import { createMenu, editMenu } from '@/api/role'

export default {
  name: 'MenuForm',
  props: {
    isEdit: {
      default: false,
      type: Boolean
    },
    params: {
      required: true,
      type: Object
    }
  },
  data() {
    const validateUrl = (rule, value, callback) => {
      // eslint-disable-next-line eqeqeq
      if (this.form.menuType == 0 || value) {
        callback()
      } else {
        callback(new Error('请输入'))
      }
    }
    return {
      form: {
        menuId: '',
        menuName: '',
        parentId: '0',
        menuType: '',
        url: '',
        order: ''
      },
      formRules: {
        menuId: [{ required: true, message: '请输入', trigger: 'blur' }],
        menuName: [{ required: true, message: '请输入', trigger: 'blur' }],
        parentId: [{ required: true, message: '请输入', trigger: 'blur' }],
        menuType: [{ required: true, message: '请输入', trigger: 'blur' }],
        url: [{ message: '请输入', trigger: 'blur', validator: validateUrl }],
        order: [{ required: true, message: '请输入', trigger: 'blur' }]
      }
    }
  },
  created() {
    const params = this.params
    const isEdit = this.isEdit
    if (params && isEdit) {
      this.form = params
    } else {
      this.form.parentId = params && params.menuId ? params.menuId : '0'
    }
  },
  methods: {
    onSubmit() {
      this.$refs.addForm.validate(valid => {
        if (valid) {
          if (!this.isEdit) {
            createMenu(this.form).then((response) => {
              if (response.code === '0000') {
                this.returnBackPage()
              }
            })
          } else {
            editMenu(this.form).then((response) => {
              if (response.code === '0000') {
                this.returnBackPage()
              }
            })
          }
        }
      })
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'MenusList' })
    }
  }
}
</script>

<style>

</style>
