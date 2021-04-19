<template>
  <el-dialog :title="isEditButton ? '编辑信息' : '新增用户'" :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-form :model="formData" ref="formData" :rules="rules" label-width="80px" label-position="right">
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="userId" label="用户账号" size="mini">
              <el-input v-model.number="formData.userId" placeholder="请输入用户账号"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="password" label="密码" size="mini">
              <el-input type="password" v-model="formData.password" placeholder="请输入密码" show-password></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="username" label="用户姓名" size="mini">
              <el-input v-model="formData.username" placeholder="请输入用户姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="email" label="邮件" size="mini">
              <el-input v-model="formData.email" placeholder="请输入邮件地址"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <!-- <el-col>
            <el-form-item prop="deleted" label="是否删除" size="mini">
              <el-radio-group v-model="formData.deleted">
                <el-radio :label="1">已删除</el-radio>
                <el-radio :label="0">正常</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col> -->
          <el-col>
            <el-form-item prop="identity" label="身份" size="mini">
              <el-select v-model="formData.identity" placeholder="请选择身份">
                <el-option v-for="item in identityOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="sex" label="性别" size="mini">
              <el-radio-group v-model="formData.sex">
                <el-radio :label="0">女</el-radio>
                <el-radio :label="1">男</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="mobile" label="手机号" size="mini">
              <el-input v-model="formData.mobile" placeholder="请输入手机号"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="qqOpenId" label="QQ号" size="mini">
              <el-input v-model.number="formData.qqOpenId" placeholder="请输入QQ号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col>
            <el-form-item prop="wxOpenId" label="微信号" size="mini">
              <el-input v-model="formData.wxOpenId" placeholder="请输入微信号"></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item prop="birth" label="生日" size="mini">
              <el-date-picker type="date" placeholder="选择日期" v-model="formData.birth" style="width: 100%"></el-date-picker>
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
  name: 'UserDialog',
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
      identityOptions: [
        { value: 0, label: '教师' },
        { value: 1, label: '大一' },
        { value: 2, label: '大二' },
        { value: 3, label: '大三' },
        { value: 4, label: '大四' },
        { value: 5, label: '研一' },
        { value: 6, label: '研二' },
        { value: 7, label: '研三' }
      ],
      rules: {
        userId: [
          { required: true, message: '用户账号不能为空', trigger: 'blur' },
          { type: 'number', message: '用户账号为数字值' },
          { pattern: /^\d{8,10}$/, message: '用户账号长度位8 ~ 10位', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 30, message: '长度在 6 到 30 个字符' }
        ],
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符' },
          { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文' }
        ],
        sex: [{ required: true, message: '请选择性别', trigger: 'change' }],
        identity: [{ required: true, message: '请选择用户身份', trigger: 'change' }],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { pattern: /^1[34578]\d{9}$/, message: '手机号码格式不对', trigger: 'blur' }
        ],
        qqOpenId: [{ pattern: /^[1-9][0-9]{4,10}$/, message: 'QQ号格式不对', trigger: 'blur' }],
        wxOpenId: [{ min: 6, max: 30, message: '微信号长度在 6 到 30 个字符', trigger: 'blur' }],
        birth: [{ required: true, message: '请选择日期', trigger: 'change' }]
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
              userId: parseInt(newValue.userId),
              password: newValue.password,
              username: newValue.username,
              identity: newValue.identity,
              deleted: newValue.deleted,
              sex: newValue.sex,
              email: newValue.email,
              mobile: newValue.mobile,
              qqOpenId: newValue.qqOpenId,
              wxOpenId: newValue.wxOpenId,
              birth: newValue.birth
            }
      this.formData = _formData
    }
  },
  methods: {
    // 提交表单
    submitForm(formName) {
      if (this.isEditButton) {
        // 如果是编辑页面
        this.updateUser(formName)
      } else {
        this.addUser(formName)
      }
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 新增用户信息
    addUser(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.formData.createUid = localStorage.getItem('userId')
          this.$http.user
            .addUser(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '添加用户成功',
                  type: 'success'
                })
                this.resetForm(formName)
                this.$parent.getAllUsers()
              }
            })
            .catch(() => {
              this.$notify.error({
                message: '添加用户失败'
              })
            })
          this.$emit('dialog-cancel') // 关闭弹窗
        } else {
          return false
        }
      })
    },
    // 更新用户信息
    updateUser(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.formData.createUid = localStorage.getItem('userId')
          this.$http.user
            .updateUser(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '用户信息更新成功',
                  type: 'success'
                })
                this.resetForm(formName)
                this.$parent.getAllUsers()
              }
            })
            .catch(() => {
              this.$notify.error({
                message: '用户信息更新失败'
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
