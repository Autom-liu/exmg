<template>
  <el-row>
    <el-col :span="6" style="min-height: 100px" />
    <el-col :span="12">
      <el-form label-width="120px" :disabled="isDetail">
        <el-form-item label="用户名称">
          <el-input v-model="form.userName" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.mobile" />
        </el-form-item>
        <el-form-item label="办公电话">
          <el-input v-model="form.officePhone" />
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="form.email" />
        </el-form-item>
        <el-form-item label="其他信息">
          <el-input v-model="form.remark" />
        </el-form-item>
        <el-form-item label="默认归属机构">
          <el-button v-if="!isDetail" round @click="deptDialogVisible = true">请选择(不需要则不选)</el-button>
          <el-select v-model="form.deptId" disabled placeholder="若为顶级机构则不选">
            <el-option :key="selected.deptId" :label="selected.deptName" :value="selected.deptId" />
          </el-select>
        </el-form-item>
        <el-form-item label="默认归属角色">
          <el-button v-if="!isDetail" round @click="roleDialogVisible = true">请选择(不需要则不选)</el-button>
          <el-select v-model="form.roleId" disabled placeholder="不需要则不选">
            <el-option :key="roleSelected.roleId" :label="roleSelected.roleName" :value="roleSelected.deptId" />
          </el-select>
        </el-form-item>
        <el-form-item v-if="!isDetail">
          <el-button type="primary" @click="onSubmit">{{ isEdit ? '编辑' : '新增' }}</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="6" style="min-height: 100px" />
    <el-dialog title="部门选择" :visible.sync="deptDialogVisible" @open="onDialogShow" @close="onDialogClose">
      <dept-tree @node-click="onNodeClick" />
      <div slot="footer">
        <el-button @click="deptDialogVisible = false">取消</el-button>
        <el-button type="success" @click="onDialogConfirm">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="角色选择" :visible.sync="roleDialogVisible" @open="onDialogShow" @close="onDialogClose">
      <roles-table @table-click="onNodeClick" />
      <div slot="footer">
        <el-button @click="roleDialogVisible = false">取消</el-button>
        <el-button type="success" @click="onRoleDialogConfirm">确定</el-button>
      </div>
    </el-dialog>
  </el-row>
</template>

<script>
import DeptTree from '@/views/system/department/components/DeptTree'
import RolesTable from '../../roles/components/RolesTable.vue'
import { createUser } from '@/api/user'

export default {
  name: 'UsersForm',
  components: { DeptTree, RolesTable },
  props: {
    formType: {
      default: 'Add',
      type: String
    },
    userId: {
      default: '',
      type: String
    }
  },
  data() {
    return {
      form: {
        userName: ''
      },
      deptDialogVisible: false,
      roleDialogVisible: false,
      selected: {},
      roleSelected: {}
    }
  },
  computed: {
    isEdit() {
      return this.formType === 'Edit'
    },
    isDetail() {
      return this.formType === 'Detail'
    }
  },
  created() {
    this.initPage()
  },
  methods: {
    initPage() {
      if (this.isEdit || this.isDetail) {
        // TODO: fetch UserDetail to Edit
      }
    },
    onDialogShow() {
      this.dialogSelected = {}
    },
    onNodeClick(node) {
      this.dialogSelected = node
    },
    onRoleNodeClick(node) {
      this.onRoleDialogConfirm = node
    },
    onDialogConfirm() {
      this.selected = this.dialogSelected
      this.form.deptId = this.selected.deptId
      this.deptDialogVisible = false
    },
    onRoleDialogConfirm() {
      this.roleSelected = this.dialogSelected
      this.form.roleId = this.roleSelected.roleId
      this.roleDialogVisible = false
    },
    onDialogClose() {
      this.dialogSelected = {}
    },
    onSubmit() {
      createUser(this.form).then((response) => {
        if (response.code === '0000') {
          this.returnBackPage()
        }
      })
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'UsersList' })
    }

  }
}
</script>
