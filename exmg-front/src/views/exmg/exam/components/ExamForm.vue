<template>
  <el-row style="width: 100%">
    <el-col :span="6" style="min-height: 100px" />
    <el-col :span="12">
      <el-form label-width="120px">
        <el-form-item label="考试名称">
          <el-input v-model="form.examName" />
        </el-form-item>
        <el-form-item label="考试说明">
          <el-input v-model="form.examRemark" type="textarea" />
        </el-form-item>
        <el-form-item label="总分">
          <el-input-number v-model="form.totalScore" :min="1" :max="300" label="总分" />
        </el-form-item>
        <el-form-item label="答题模式">
          <el-select v-model="form.answerMode">
            <el-option v-for="(value, key) in answerModeEnums" :key="Number(key)" :label="value" :value="Number(key)" />
          </el-select>
        </el-form-item>
        <el-form-item label="考试封面缩略图">
          <el-upload
            :action="`${BASE_URL}/admin/config/savetofile`"
            accept="image/*"
            name="file"
            :before-upload="fileCheck"
            :on-preview="filePreview"
            :on-remove="fileRemove"
            :on-success="fileUpload"
            :multiple="false"
            :file-list="fileField.fileList"
            list-type="picture"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传一张图片，图片尺寸控制在300*300,大小不超过1MB,上传后请点击预览确认图片是否上传地址是否有效</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="是否展示首页">
          <el-switch v-model="form.banner" active-text="首页展示" inactive-text="" />
        </el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker v-model="form.begintime" type="date" placeholder="选择开始日期" :clearable="false" />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker v-model="form.endtime" type="date" placeholder="选择结束日期" :clearable="false" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">{{ submitTxt }}</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="6" style="min-height: 100px" />
    <el-dialog :visible.sync="fileField.fileDialogShow">
      <img width="100%" :src="form.picUrl" alt="上传图片返回地址有误">
    </el-dialog>
  </el-row>
</template>

<script>
import { createExam, editExam } from '@/api/exam'
import { getFileName } from '@/utils'

export default {
  name: 'ExamForm',
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
      BASE_URL: process.env.VUE_APP_BASE_API,
      form: {
        id: 0,
        examName: '',
        examRemark: '',
        totalScore: 100,
        answerMode: 1,
        maxCount: 1,
        picUrl: '',
        banner: true,
        autoGenerate: false,
        begintime: new Date(),
        endtime: new Date('2030-12-31')
      },
      fileField: {
        fileDialogShow: false,
        fileList: []
      }
    }
  },
  computed: {
    answerModeEnums() {
      return this.$store.getters['dataDict/answerModeEnums']
    },
    submitTxt() {
      return this.isEdit ? '编辑' : '新增'
    },
    submitAction() {
      return this.isEdit ? editExam : createExam
    },
    submitForm() {
      // 转换成后端兼容的类型
      const begintime = this.form.begintime.getTime()
      const endtime = this.form.endtime.getTime()
      const sbform = { ...this.form, begintime, endtime }
      return sbform
    }
  },
  created() {
    this.initPage()
  },
  methods: {
    initPage() {
      if (this.isEdit) {
        const begintime = new Date(this.params.begintime)
        const endtime = new Date(this.params.endtime)
        this.form = { ...this.params, begintime, endtime }
        if (this.form.picUrl) {
          this.fileField.fileList = [{ url: this.form.picUrl, name: getFileName(this.form.picUrl) }]
        }
      }
    },
    fileCheck(file) {
      if (this.fileField.fileList.length > 0) {
        this.$message({ message: '只能上传一张图片', type: 'warning' })
        return false
      }
      if (file.size > 1024 * 1024) {
        this.$message({ message: '图片大小不能超过1MB', type: 'warning' })
        return false
      }
      return true
    },
    fileUpload(response, file, fileList) {
      if (response.code === '0000') {
        this.$message({ message: '上传图片成功', type: 'success' })
        this.fileField.fileList = fileList
        this.form.picUrl = response.data
      } else {
        this.$message({ message: `上传图片失败:${response.msg},请尝试重新进入页面上传`, type: 'error' })
      }
    },
    filePreview() {
      this.fileField.fileDialogShow = true
    },
    fileRemove() {

    },
    onSubmit() {
      this.submitAction(this.submitForm).then((response) => {
        if (response.code === '0000') {
          this.returnBackPage()
        }
      })
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'ExamList' })
    }

  }
}
</script>
