<template>
  <el-dialog :title="isEditButton ? '编辑' : '新增'" :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-form :model="formData" ref="formData" :rules="rules" label-width="80px" label-position="right">
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="articleName" label="文章标题" size="mini">
              <el-input v-model="formData.articleName" placeholder="请输入文章标题"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="magazineName" label="期刊" size="mini">
              <el-input v-model="formData.magazineName" placeholder="请输入期刊"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="submitTime" label="投递时间" size="mini">
              <el-date-picker type="date" placeholder="选择日期" v-model="formData.submitTime" style="width: 100%"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="checkTime" label="审核时间" size="mini">
              <el-date-picker type="date" placeholder="选择日期" v-model="formData.checkTime" style="width: 100%"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="cost" label="花费" size="mini">
              <el-input v-model="formData.cost" placeholder="请输入花费, 单位：(元)"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="reward" label="奖励" size="mini">
              <el-input v-model="formData.reward" placeholder="请输入奖励, 单位：(元)"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="receiveTime" label="接收时间" size="mini">
              <el-date-picker type="date" placeholder="选择日期" v-model="formData.receiveTime" style="width: 100%"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="userId" label="投稿人" size="mini">
              <el-input v-model="formData.userId" placeholder="投稿人id"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="remark" label="备注" size="mini">
              <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" v-model="formData.remark" placeholder="备注"></el-input>
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
  name: 'AchievementDialog',
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
        articleName: '',
        magazineName: '',
        submitTime: '',
        checkTime: '',
        receiveTime: '',
        cost: '',
        reward: '',
        userId: '',
        remark: ''
      },
      rules: {
        articleName: [{ required: true, message: '文章名不能为空', trigger: 'blur' }],
        userId: [{ required: true, message: '投稿人不能为空', trigger: 'blur' }]
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
        this.updateAchievement(formName)
      } else {
        this.addAchievement(formName)
      }
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 新增信息
    addAchievement(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.formData.byUserId = localStorage.getItem('userId')
          this.$http.achievement
            .addAchievement(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '添加成功',
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
    updateAchievement(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.formData.byUserId = localStorage.getItem('userId')
          this.$http.achievement
            .updateAchievement(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '更新成功',
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
