<template>
  <el-row>
    <el-col :span="4" style="min-height: 100px" />
    <el-col :span="16">
      <el-form label-width="120px">
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确认授权</el-button>
        </el-form-item>
        <el-form-item label="用户权限分配">
          <el-button round @click="user.showDialog">请选择(不需要则不选)</el-button>
          <el-select v-model="form.userId" disabled placeholder="不需要则不选">
            <el-option :key="user.selected.userId" :label="user.selected.userName" :value="user.selected.userId" />
          </el-select>
        </el-form-item>
        <el-form-item label="权限分配机构">
          <el-button round @click="dept.showDialog">请选择(不需要则不选)</el-button>
          <el-select v-model="form.deptId" disabled placeholder="若为顶级机构则不选">
            <el-option :key="dept.selected.deptId" :label="dept.selected.deptName" :value="dept.selected.deptId" />
          </el-select>
        </el-form-item>
        <el-form-item label="权限分配角色">
          <el-button round @click="role.showDialog">请选择(不需要则不选)</el-button>
          <el-select v-model="form.roleId" disabled placeholder="不需要则不选">
            <el-option :key="role.selected.roleId" :label="role.selected.roleName" :value="role.selected.roleId" />
          </el-select>
        </el-form-item>
      </el-form>
      <menu-tree multiselect @table-selected="onMenuSelected" />
    </el-col>
    <el-col :span="4" style="min-height: 100px" />
    <el-dialog title="部门选择" :visible.sync="dept.dialogShow" @open="dept.onShow" @close="dept.closeDialog">
      <dept-tree @node-click="dept.selectDialog" />
      <div slot="footer">
        <el-button @click="dept.closeDialog">取消</el-button>
        <el-button type="success" @click="dept.confirm">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="角色选择" :visible.sync="role.dialogShow" @open="role.onShow" @close="role.closeDialog">
      <roles-table @table-click="role.selectDialog" />
      <div slot="footer">
        <el-button @click="role.closeDialog">取消</el-button>
        <el-button type="success" @click="role.confirm">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="用户选择" :visible.sync="user.dialogShow" @open="user.onShow" @close="user.closeDialog">
      <user-table @table-click="user.selectDialog" />
      <div slot="footer">
        <el-button @click="user.closeDialog">取消</el-button>
        <el-button type="success" @click="user.confirm">确定</el-button>
      </div>
    </el-dialog>
  </el-row>
</template>

<script>
import DeptTree from '@/views/system/department/components/DeptTree'
import RolesTable from '@/views/system/roles/components/RolesTable'
import UserTable from '@/views/system/users/components/UserTable'
import MenuTree from '@/views/system/menus/components/MenuTree'
import { permissionAssign } from '@/api/role'

export default {
  name: 'Permission',
  components: { DeptTree, RolesTable, UserTable, MenuTree },
  props: {

  },
  data() {
    return {
      form: {},
      user: {
        dialogShow: false,
        dialogSelect: {},
        selected: {},
        showDialog: () => {
          this.showDialog('user')
        },
        closeDialog: () => {
          this.dialogSelect('user', {})
          this.closeDialog('user')
        },
        onShow: () => {
          this.dialogSelect('user', {})
        },
        selectDialog: (row) => {
          this.dialogSelect('user', row)
        },
        confirm: () => {
          this.select('user', this.user.dialogSelect)
          this.form.userId = this.user.selected.userId
          this.closeDialog('user')
        }
      },
      dept: {
        dialogShow: false,
        dialogSelect: {},
        selected: {},
        showDialog: () => {
          this.showDialog('dept')
        },
        closeDialog: () => {
          this.dialogSelect('dept', {})
          this.closeDialog('dept')
        },
        onShow: () => {
          this.dialogSelect('dept', {})
        },
        selectDialog: (row) => {
          this.dialogSelect('dept', row)
        },
        confirm: () => {
          this.select('dept', this.dept.dialogSelect)
          this.form.deptId = this.dept.selected.deptId
          this.closeDialog('dept')
        }
      },
      role: {
        dialogShow: false,
        dialogSelect: {},
        selected: {},
        showDialog: () => {
          this.showDialog('role')
        },
        closeDialog: () => {
          this.dialogSelect('role', {})
          this.closeDialog('role')
        },
        onShow: () => {
          this.dialogSelect('role', {})
        },
        selectDialog: (row) => {
          this.dialogSelect('role', row)
        },
        confirm: () => {
          this.select('role', this.role.dialogSelect)
          this.form.roleId = this.role.selected.roleId
          this.closeDialog('role')
        }
      }
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

  },
  methods: {
    showDialog(type) {
      this[type].dialogShow = true
    },
    closeDialog(type) {
      this[type].dialogShow = false
    },
    dialogSelect(type, data) {
      this[type].dialogSelect = data
    },
    select(type, data) {
      this[type].selected = data
    },
    onNodeClick(node) {
      this.dialogSelected = node
    },
    onDialogClose() {
      this.dialogSelected = {}
    },
    onSubmit() {
      permissionAssign(this.form).then((resp) => {
        if (resp.code === '0000') {
          this.returnBackPage()
        }
      })
    },
    onMenuSelected(menus) {
      const menuIdarr = menus.map((menu) => menu.menuId)
      const menuIds = menuIdarr.join(',')
      this.form.menuIds = menuIds
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'MenusList' })
    }

  }
}
</script>
