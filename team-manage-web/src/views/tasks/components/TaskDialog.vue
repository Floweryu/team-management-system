<template>
  <el-dialog :title="isEditButton ? '编辑' : '新增'" :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-form :model="formData" ref="formData" :rules="rules" label-width="80px" label-position="right">
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="startTime" label="开始时间" size="mini">
              <el-date-picker type="date" placeholder="选择日期" v-model="formData.startTime" style="width: 100%"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="endTime" label="结束时间" size="mini">
              <el-date-picker type="date" placeholder="选择日期" v-model="formData.endTime" style="width: 100%"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="content" label="任务内容" size="mini">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4 }"
                v-model="formData.content"
                placeholder="请输入任务内容"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="state" label="状态" size="mini">
              <el-radio-group v-model="formData.state">
                <el-radio :label="0">未完成</el-radio>
                <el-radio :label="1">正在进行</el-radio>
                <el-radio :label="2">已完成</el-radio>
              </el-radio-group>
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
  name: 'TaskDialog',
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
      formData: {},
      rules: {
        content: [{ required: true, message: '任务内容不能为空', trigger: 'blur' }]
      }
    }
  },
  watch: {
    // 监听点击编辑按钮传递的参数
    editValue(newValue) {
      let _formData =
        newValue == null
          ? {}
          : {
              id: newValue.id,
              publishUserId: newValue.publishUserId,
              startTime: newValue.startTime,
              endTime: newValue.endTime,
              content: newValue.content,
              state: parseInt(newValue.state)
            }
      this.formData = _formData
    }
  },
  methods: {
    // 提交表单
    submitForm(formName) {
      if (this.isEditButton) {
        // 如果是编辑页面
        this.updateTask(formName)
      } else {
        this.addTask(formName)
      }
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 新增信息
    addTask(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.formData.publishUserId = localStorage.getItem('userId')
          this.$http.task
            .addTask(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '添加成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getAllTask()
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
    updateTask(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.task
            .updateTask(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '更新成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getAllTask()
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
