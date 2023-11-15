<template>
  <el-row style="width: 100%">
    <el-col :span="6" style="min-height: 100px" />
    <el-col :span="12">
      <el-form label-width="120px">
        <el-form-item label="题目">
          <el-input v-model="form.question" />
        </el-form-item>
        <el-form-item label="题目解析">
          <el-input v-model="form.interpretation" type="textarea" />
        </el-form-item>
        <el-form-item label="难度">
          <el-input-number v-model="form.difficulty" :min="1" :max="5" label="总分" />
        </el-form-item>
        <el-form-item label="题目类型">
          <el-select v-model="form.questionType">
            <el-option label="单选" :value="1" />
            <el-option label="多选" :value="2" />
            <el-option label="填空/简答" :value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="题目知识一级分类">
          <el-select v-model="form.topCategory" @change="categoryField.onTopChange">
            <el-option v-for="(label, key) in categoryField.topCategoryEnums" :key="Number(key)" :label="label" :value="Number(key)" />
          </el-select>
        </el-form-item>
        <el-form-item label="题目知识分类">
          <el-select v-model="form.categoryId">
            <el-option v-for="(label, key) in categoryField.children" :key="Number(key)" :label="label" :value="Number(key)" />
          </el-select>
        </el-form-item>
        <el-form-item label="贡献为公共题目">
          <el-switch v-model="form.common" active-text="贡献为公共题目" inactive-text="" />
        </el-form-item>
        <el-form-item label="题目配图">
          <el-input v-model="form.picsUrl" />
          <!-- 暂时作废上传
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
            <div slot="tip" class="el-upload__tip">图片尺寸控制在300*300,大小不超过1MB,上传后请点击预览确认图片是否上传地址是否有效</div>
          </el-upload> -->
        </el-form-item>
        <el-form-item v-for="(item, index) in form.options" :key="index" :label="`选项${index}`">
          <el-col :span="8">
            <el-input v-model="item.option" placeholder="选项描述" />
          </el-col>
          <el-col :span="1" style="height: 1px" />
          <el-col :span="2">
            <el-checkbox v-model="item.right" @change="(value) => optionField.optionChecked(value, index)">是否正确</el-checkbox>
          </el-col>
          <el-col :span="1" style="height: 1px" />
          <el-col :span="8">
            <el-input v-model="item.interpretation" type="textarea" placeholder="选项解析" />
          </el-col>
          <el-col :span="2" style="height: 1px" />
          <el-col :span="2">
            <el-button @click="() => optionField.removeOption(index)">删除</el-button>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button @click="optionField.addOption">新增选项</el-button>
          <el-button @click="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="6" style="min-height: 100px" />
    <el-dialog :visible.sync="fileField.fileDialogShow">
      <img width="100%" :src="fileField.previewUrl" alt="上传图片返回地址有误">
    </el-dialog>
  </el-row>
</template>

<script>
import { createQuestion, editQuestion } from '@/api/question'
import { getFileName } from '@/utils'

export default {
  name: 'QuestionForm',
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
        question: '',
        questionType: 1,
        difficulty: 1,
        topCategory: 1,
        categoryId: 101,
        interpretation: 1,
        picsUrl: '',
        common: true,
        options: [
          {
            id: 0,
            option: '',
            interpretation: '',
            right: false
          }
        ]
      },
      fileField: {
        fileDialogShow: false,
        fileList: [],
        previewUrl: '',
        init: () => {
          const picUrls = this.form.picsUrl
          this.form.picsUrl = []
          if (this.isEdit && picUrls) {
            const picsUrl = picUrls.split(',')
            this.fileField.fileList = picsUrl
            this.fileField.fileList = picsUrl.map((value) => ({ url: value, name: getFileName(value) }))
          }
        }
      },
      optionField: {
        addOption: () => {
          const options = [...this.form.options]
          const option = { id: options.length, option: '', interpretation: '', right: false }
          options.push(option)
          this.$set(this.form, 'options', options)
        },
        removeOption: (index) => {
          const options = [...this.form.options]
          options.splice(index, 1)
          this.$set(this.form, 'options', options)
        },
        optionChecked: (value, index) => {
          const options = [...this.form.options]
          if (this.form.questionType === 1 && value) {
            for (const item of options) {
              item.right = false
            }
          }
          options[index].right = value
          this.$set(this.form, 'options', options)
        }
      }
    }
  },
  computed: {
    categoryField() {
      return {
        topCategoryEnums: this.topCategoryEnums,
        children: [],
        init: () => {
          this.categoryField.onTopChange(this.form.topCategory)
        },
        onTopChange: (id) => {
          const getCategoryByParentId = this.$store.getters['dataDict/getCategoryByParentId']
          const children = getCategoryByParentId(id)
          this.categoryField.children = children
          if (Object.keys(children).length > 0) {
            const chr = Object.keys(children)
            // this.form.categoryId = Number(chr[0])
          }
        }
      }
    },
    topCategoryEnums() {
      return this.$store.getters['dataDict/topCategoryEnums']
    },
    submitTxt() {
      return this.isEdit ? '编辑' : '新增'
    },
    submitAction() {
      return this.isEdit ? editQuestion : createQuestion
    },
    submitForm() {
      // 转换成后端兼容的类型
      const picUrls = [...this.form.picsUrl]
      const picsUrl = picUrls.join(',')
      const sbform = { ...this.form, picsUrl }
      return sbform
    }
  },
  created() {
    this.initPage()
    this.categoryField.init()
  },
  methods: {
    initPage() {
      if (this.isEdit) {
        this.form = { ...this.params }
      }
    },
    fileCheck(file) {
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
        this.form.picsUrl.push(response.data)
      } else {
        this.$message({ message: `上传图片失败:${response.msg},请尝试重新进入页面上传`, type: 'error' })
      }
    },
    filePreview(file) {
      this.fileField.fileDialogShow = true
      this.fileField.previewUrl = file.response.data
    },
    fileRemove(file, fileList) {
      this.fileField.fileList = fileList
      this.form.picsUrl = fileList.map((item) => item.response.data)
    },
    onSubmit() {
      this.submitAction(this.form).then((response) => {
        if (response.code === '0000') {
          this.returnBackPage()
        }
      })
    },
    returnBackPage() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.replace({ name: 'QuestionList' })
    }

  }
}
</script>
