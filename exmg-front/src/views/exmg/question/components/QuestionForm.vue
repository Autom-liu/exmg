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
            <el-option v-for="(item) in categoryField.cateList" :key="item.id" :label="item.value" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="题目知识分类">
          <el-select v-model="form.categoryId">
            <el-option v-for="item in categoryField.children" :key="item.id" :label="item.value" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="贡献为公共题目">
          <el-switch v-model="form.common" active-text="贡献为公共题目" inactive-text="" />
        </el-form-item>
        <el-form-item label="题目配图">
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
          </el-upload>
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
        categoryId: 1,
        interpretation: 1,
        picsUrl: [],
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
      },
      categoryField: {
        cateList: [
          {
            id: 1,
            value: '计算机基础知识',
            children: [
              {
                id: 101,
                value: '数据结构与算法'
              }
            ]
          },
          {
            id: 2,
            value: '编程语言基础',
            children: [
              {
                id: 201,
                value: 'java语言基础'
              },
              {
                id: 202,
                value: 'java语言核心技术(JVM与多线程)'
              },
              {
                id: 203,
                value: 'Linux Shell脚本'
              }
            ]
          },
          {
            id: 3,
            value: '框架与中间件技术',
            children: [
              {
                id: 301,
                value: 'Spring框架'
              }
            ]
          },
          {
            id: 4,
            value: '大数据建模与分析',
            children: [
              {
                id: 401,
                value: '大数据分析'
              }
            ]
          }
        ],
        children: [],
        init: () => {
          this.categoryField.onTopChange(1)
        },
        onTopChange: (id) => {
          const item = this.categoryField.cateList[id - 1]
          this.categoryField.children = [...item.children]
          this.form.categoryId = item.children[0].id
        }
      }
    }
  },
  computed: {
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
    this.fileField.init()
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
      this.submitAction(this.submitForm).then((response) => {
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
