<template>
  <el-dialog :title="isEditButton ? '编辑' : '新增'" :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-form :model="formData" ref="formData" :rules="rules" label-width="80px" label-position="right">
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="userId" label="用户账号" size="mini">
              <el-input v-model="formData.userId" :disabled="isEditButton" placeholder="请输入用户账号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="roleId" label="角色" size="mini">
              <el-select v-model="formData.roleId" placeholder="请选择角色">
                <el-option v-for="item in roleOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
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
  name: 'AssetsDialog',
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
      roleOptions: [
        { value: 1, label: '管理员' },
        { value: 2, label: '普通用户' }
      ],
      formData: {
        userId: '',
        roleId: Number
      },
      rules: {
        userId: [
          { required: true, message: '用户账号不能为空', trigger: 'blur' },
          { pattern: /^\d{8,10}$/, message: '用户账号长度位8 ~ 10位', trigger: 'blur' }
        ],
        roleId: [{ required: true, message: '请选择角色', trigger: 'change' }]
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
              userId: newValue.userId,
              roleId: parseInt(newValue.roleId)
            }
      this.formData = _formData
    }
  },
  methods: {
    // 提交表单
    submitForm(formName) {
      if (this.isEditButton) {
        // 如果是编辑页面
        this.updateUserRole(formName)
      } else {
        this.addRole(formName)
      }
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 新增信息
    addRole(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.role
            .addUserRole(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '添加成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getUserRole()
              } else {
                this.$notify.error({
                  message: res.msg
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
    updateUserRole(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.role
            .updateUserRole(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '更新成功',
                  type: 'success'
                })
                this.$refs[formName].resetFields()
                this.$parent.getUserRole()
              } else {
                this.$notify.error({
                  message: res.msg
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
