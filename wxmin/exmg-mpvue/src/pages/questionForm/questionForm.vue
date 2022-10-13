<template>
  <van-row custom-class="grey-bg">
    <van-cell-group>
      <van-cell title="题目类型" :border="false" />
      <van-radio-group direction="horizontal" custom-class="padding" :value="form.questionType" @change="questionTypeField.onChange">
        <van-radio v-for="item in questionTypes" :key="item.id" :name="item.id">{{item.label}}</van-radio>
      </van-radio-group>
      <van-field :value="form.question" label="题目" @change="(event) => onChange(event, 'question')" />
      <van-cell title="难度" >
        <van-rate :value="form.difficulty" @change="difficultyField.onChange" />
      </van-cell>
      <van-cell title="选择分类" is-link @click="categoryField.onShow" :value="categoryField.activeItem.text" />
      <van-field :value="form.interpretation" label="题目解析" type="textarea" autosize @change="(event) => onChange(event, 'interpretation')"/>
      <van-cell title="图片上传" :border="false" />
      <van-uploader accept="image" :max-count="5" :file-list="form.picsUrl" @afterRead="uploadImg" use-before-read @beforeRead="checkImg" @delete="removeImg" />
      <!-- 选项 -->
      <van-cell-group>
        <van-row custom-class="txt-small">
          <van-col span="20">选项或关键字</van-col>
          <van-col span="4">正确选项</van-col>
        </van-row>
        <van-row v-for="(item, index) in optionsField" :key="item.id">
          <van-col span="4" v-if="item.clearShow" custom-class="leftcenter" @click="(event) => optionField.removeOption(index)"><van-icon name="clear" /></van-col>
          <van-col :span="item.fieldSpan">
            <van-field :value="item.option" @change="(event) => optionField.txtChange(event, index)" />
          </van-col>
          <van-col span="4" v-if="item.checkShow">
            <van-checkbox :value="item.right" @change="(event) => optionField.checkRight(event, index)" shape="square" />
          </van-col>
        </van-row>
        <van-cell title="新增选项" icon="add" :border="false" @click="optionField.addOption"/>
      </van-cell-group>
      <!-- 占位空行 -->
      <van-row custom-class="large-padding"></van-row>
      <!-- 底部按钮 -->
      <van-row custom-class="bottom">
        <van-row custom-class="padding">
          <van-col span="8"><van-button type="danger" plain round custom-class="long-btn">取消</van-button></van-col>
          <van-col span="8" custom-class="padding"></van-col>
          <van-col span="8"><van-button type="info" plain round custom-class="long-btn" @click="onSubmit">保存</van-button></van-col>`
        </van-row>
      </van-row>
    </van-cell-group>
    <van-popup :show="categoryField.showPopup" @close="categoryField.onClose"  position="bottom">
      <van-tree-select :items="categoryField.items"  :main-active-index="categoryField.mainActiveIndex" :active-id="categoryField.activeItemId" @clickNav="categoryField.onLeftClick" @clickItem="categoryField.onRightClick" />
    </van-popup>
  </van-row>
</template>

<script>
import http from '@/utils/request'
import { getFileName } from '@/utils/index'

export default {
  data () {
    return {
      form: {
        id: 0,
        questionType: 2,
        question: '',
        difficulty: 0,
        topCategory: 0,
        categoryId: 0,
        options: [
          {
            id: 1,
            option: 'AAAAAA',
            right: true,
            optionInterpretation: ''
          },
          {
            id: 2,
            option: 'BBBBBB',
            right: true,
            optionInterpretation: ''
          }
        ],
        interpretation: '',
        picsUrl: []
      },
      questionTypes: [
        {
          id: 1,
          label: '单选'
        },
        {
          id: 2,
          label: '多选'
        },
        {
          id: 3,
          label: '填空/简答'
        }
      ],
      optionField: {
        addOption: () => {
          const options = [...this.form.options]
          const fakeId = options.length * -1
          const option = { id: fakeId, option: '', right: false, optionInterpretation: '' }
          options.push(option)
          this.$set(this.form, 'options', options)
        },
        removeOption: (index) => {
          const options = [...this.form.options]
          options.splice(index, 1)
          this.$set(this.form, 'options', options)
        },
        checkRight: (event, index) => {
          const value = event.mp.detail
          const options = [...this.form.options]
          if (this.form.questionType === 1 && value) {
            for (const op of options) {
              op.right = false
            }
          }
          options[index].right = value
          this.$set(this.form, 'options', options)
        },
        txtChange: (event, index) => {
          const value = event.mp.detail
          const options = [...this.form.options]
          options[index].option = value
          // this.$set(this.form, 'options', options)
        }
      },
      questionTypeField: {
        onChange: (event) => {
          const value = event.mp.detail
          this.form.questionType = value
        }
      },
      categoryField: {
        showPopup: false,
        items: [
          {
            id: 2,
            text: '一级分类2',
            children: [
              {
                text: '二级分类10',
                id: 10
              },
              {
                text: '二级分类13',
                id: 13
              },
              {
                text: '二级分类14',
                id: 14
              }
            ]
          },
          {
            id: 1,
            text: '一级分类1',
            children: [
              {
                text: '二级分类11',
                id: 11
              },
              {
                text: '二级分类15',
                id: 15
              }
            ]
          },
          {
            id: 3,
            text: '一级分类3',
            children: [
              {
                text: '二级分类12',
                id: 12
              }
            ]
          }
        ],
        mainActiveIndex: 0,
        activeItemId: 0,
        activeItem: {},
        onShow: () => {
          this.categoryField.showPopup = true
        },
        onClose: () => {
          this.categoryField.showPopup = false
        },
        onLeftClick: (event) => {
          const { index } = event.mp.detail
          this.categoryField.mainActiveIndex = index
        },
        onRightClick: (event) => {
          const { id } = event.mp.detail
          this.categoryField.activeItemId = id
          this.$set(this.categoryField.activeItem, 'text', event.mp.detail.text)
          this.$set(this.categoryField.activeItem, 'id', event.mp.detail.id)
          this.form.topCategory = this.categoryField.items[this.categoryField.mainActiveIndex].id
          this.form.categoryId = id
          this.categoryField.onClose()
        }
      },
      difficultyField: {
        onChange: (event) => {
          this.form.difficulty = event.mp.detail
        }
      },
      questionField: {
        onChange: (event) => {

        }
      }
    }
  },
  computed: {
    optionsField () {
      const options = [...this.form.options]
      const questionType = this.form.questionType
      const clearSpan = 4
      const checkSpan = 4
      const fieldSpan = 16
      if (options.length > 0) {
        for (let i = 0; i < options.length; i++) {
          options[i].clearShow = i !== 0
          options[i].checkShow = questionType !== 3
          let offset = 0
          if (!options[i].clearShow) {
            offset += clearSpan
          }
          if (!options[i].checkShow) {
            offset += checkSpan
          }
          options[i].fieldSpan = fieldSpan + offset
        }
      }
      return options
    }
  },

  components: {

  },

  methods: {
    onChange (event, field) {
      this.form[field] = event.mp.detail
    },
    uploadImg (event) {
      const { file } = event.mp.detail
      http.upload('/wx/savetofile', file).then((response) => {
        const { code, data } = response
        if (code === '0000') {
          this.imgList = this.imgList.concat([{ url: data, name: getFileName(data) }])
        }
      })
    },
    checkImg (event) {
      const { callback } = event.mp.detail
      // eslint-disable-next-line standard/no-callback-literal
      callback(true)
    },
    removeImg (event) {
      // eslint-disable-next-line no-unused-vars
      const { file, index } = event.mp.detail
      this.imgList.splice(index, 1)
    },
    onSubmit () {

    }
  },

  created () {
    // let app = getApp()
  }
}
</script>

<style scope>
.red-font {
  font: red;
}
.padding {
  padding: 12px;
  background-color: #fff;
}
.large-padding {
  padding: 60px;
  background-color: #fff;
}
.grey-bg {
  box-sizing: border-box;
  padding: 12px;
  background-color: #fff;
}
.leftcenter {
  position: relative;
  top: 12px;
  left: 12px;
  font-size: 24px;
}
.bottom {
  width: 100%;
  position: fixed;
  bottom: 0;
  z-index: 10;
}
.txt-small {
  text-align: left;
  font-size: 14px;
  vertical-align: middle;
}
.van-button.long-btn {
  padding: 0 40px;
}
.empty {
  padding: 10px 0;
  height: 100%;
}
.van-radio-group.van-radio-group--horizontal {
  padding: 0 12px;
}
</style>
