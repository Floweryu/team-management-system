<template>
  <el-dialog :title="isEditButton ? '编辑' : '新增'" :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-form :model="formData" ref="formData" :rules="rules" label-width="80px" label-position="right">
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="title" label="文献标题" size="mini">
              <el-input v-model="formData.title" placeholder="请输入文献标题"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="introduction" label="简介" size="mini">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4 }"
                v-model="formData.introduction"
                placeholder="请输入简介"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="author" label="作者" size="mini">
              <el-input v-model="formData.author" placeholder="请输入作者"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="publishTime" label="发表时间" size="mini">
              <el-date-picker type="date" placeholder="选择日期" v-model="formData.publishTime" style="width: 100%"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="publishPlace" label="出版单位" size="mini">
              <el-input v-model="formData.publishPlace" placeholder="请输入出版单位"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="originAddress" label="来源地址" size="mini">
              <el-input v-model="formData.originAddress" placeholder="请输入地址"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" justify="center" class="row-bg">
          <el-col :span="12" :push="4">
            <el-form-item>
              <el-button type="primary" size="small" @click="submitForm('formData')">提交</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item>
              <el-button size="small" @click="resetForm('formData')">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </el-dialog>
</template>

<script>
export default {
  name: 'DocumentDialog',
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    isEditButton: {
      type: Boolean,
      default: false
    },
    editValue: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  data() {
    return {
      formData: {
        title: '',
        author: '',
        introduction: '',
        publishPlace: '',
        originAddress: '',
        uploadUserId: ''
      },
      rules: {
        title: [{ required: true, message: '文献标题不能为空', trigger: 'blur' }],
        author: [{ required: true, message: '请输入作者', trigger: 'blur' }],
        introduction: [{ required: true, message: '请输入简介', trigger: 'blur' }],
        publishPlace: [{ required: true, message: '请输入出版单位', trigger: 'blur' }],
        originAddress: [{ required: true, message: '请输入来源网址', trigger: 'blur' }],
        publishTime: [{ required: true, message: '请选择日期', trigger: 'change' }]
      }
    }
  },
  watch: {
    // 监听点击编辑按钮传递的参数
    editValue(newValue) {
      this.formData = Object.assign({}, newValue)
    }
  },
  methods: {
    // 提交表单
    submitForm(formName) {
      if (this.isEditButton) {
        // 如果是编辑页面
        this.updateDocument(formName)
      } else {
        this.addDocument(formName)
      }
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 新增文献信息
    addDocument(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.formData.uploadUserId = localStorage.getItem('userId')
          this.$http.document
            .addDocument(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '添加文献成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getByRole()
              } else {
                this.$notify.error({
                  message: '添加失败'
                })
              }
            })
            .catch(() => {
              this.$notify.error({
                message: '添加文献失败'
              })
            })
          this.$emit('dialog-cancel') // 关闭弹窗
        } else {
          return false
        }
      })
    },
    // 更新文献信息
    updateDocument(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.formData.uploadUserId = localStorage.getItem('userId')
          this.$http.document
            .updateDocument(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '文献信息更新成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getByRole()
              } else {
                this.$notify.error({
                  message: '更新失败'
                })
              }
            })
            .catch(() => {
              this.$notify.error({
                message: '文献信息更新失败'
              })
            })
          this.$emit('dialog-cancel') // 关闭弹窗
        } else {
          return false
        }
      })
    },
    // 关闭弹窗
    cancel() {
      this.$emit('dialog-cancel')
    }
  }
}
</script>

<style scoped>
.el-col {
  border-radius: 4px;
}
.el-radio {
  margin-left: 30px;
}
.row-bg {
  padding: 8px 8px;
  background-color: #fbfbfb85;
  border-radius: 4px;
}
</style>
