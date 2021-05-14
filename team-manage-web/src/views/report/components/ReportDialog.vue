<template>
  <el-dialog title="发布" :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-form :model="formData" ref="formData" :rules="rules" label-width="100px" label-position="right">
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="type" label="报告类别" size="mini">
              <el-radio-group v-model="formData.type">
                <el-radio :label="0">日报</el-radio>
                <el-radio :label="1">周报</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="finishedTask" label="已完成任务" size="mini">
              <el-input
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 5 }"
                v-model="formData.finishedTask"
                placeholder="请输入..."
                maxlength="1000"
                show-word-limit
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="unfinishedTask" label="未完成任务" size="mini">
              <el-input
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 5 }"
                v-model="formData.unfinishedTask"
                placeholder="请输入..."
                maxlength="1000"
                show-word-limit
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="planTask" label="计划任务" size="mini">
              <el-input
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 5 }"
                v-model="formData.planTask"
                placeholder="请输入..."
                maxlength="1000"
                show-word-limit
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="selfReview" label="自我评价" size="mini">
              <el-input
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 5 }"
                v-model="formData.selfReview"
                placeholder="请输入..."
                maxlength="1000"
                show-word-limit
              ></el-input>
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
  name: 'ReportDialog',
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      formData: {},
      rules: { type: [{ required: true, message: '请选择报告类型', trigger: 'change' }] }
    }
  },
  methods: {
    // 提交表单
    submitForm(formName) {
      this.addReport(formName)
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 新增信息
    addReport(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.formData.userId = localStorage.getItem('userId')
          this.formData.username = localStorage.getItem('username')
          this.$http.report
            .addReport(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '添加成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getAllReport()
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
  background-color: #fbfbfb85;
  border-radius: 4px;
}
</style>
