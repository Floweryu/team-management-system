<template>
  <el-dialog :title="isEditButton ? '编辑' : '新增'" :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-form :model="formData" ref="formData" :rules="rules" label-width="80px" label-position="right">
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="name" label="软件名称" size="mini">
              <el-input v-model="formData.name" placeholder="请输入软件名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="version" label="版本号" size="mini">
              <el-input v-model="formData.version" placeholder="请输入版本号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="downloadLink" label="下载地址" size="mini">
              <el-input v-model="formData.downloadLink" placeholder="请输入地址"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="remark" label="备注" size="mini">
              <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" v-model="formData.remark" placeholder="软件备注"></el-input>
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
  name: 'SoftwareDialog',
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
        name: '',
        version: '',
        remark: '',
        downloadLink: ''
      },
      rules: {
        name: [{ required: true, message: '软件名不能为空', trigger: 'blur' }],
        downloadLink: [{ required: true, message: '请输入下载地址', trigger: 'blur' }]
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
        this.updateSoftware(formName)
      } else {
        this.addSoftware(formName)
      }
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 新增信息
    addSoftware(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //   this.formData.uploadUserId = localStorage.getItem('userId')
          this.$http.software
            .addSoftware(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '添加成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getAllSoftware()
              } else {
                this.$notify.error({
                  message: '添加失败'
                })
              }
            })
            .catch(() => {
              this.$notify.error({
                message: '添加失败'
              })
            })
          this.$emit('dialog-cancel') // 关闭弹窗
        } else {
          return false
        }
      })
    },
    // 更新信息
    updateSoftware(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //   this.formData.uploadUserId = localStorage.getItem('userId')
          this.$http.software
            .updateSoftware(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '更新成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getAllSoftware()
              } else {
                this.$notify.error({
                  message: '更新失败'
                })
              }
            })
            .catch(() => {
              this.$notify.error({
                message: '更新失败'
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
