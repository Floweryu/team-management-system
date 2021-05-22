<template>
  <div class="login-wrap">
    <div class="tm-title">
      <el-image :src="require('@/assets/image/app_name.png')"></el-image>
    </div>
    <el-card class="tm-login">
      <div class="top-info">
        <el-button type="primary" icon="el-icon-back" circle @click="backLogin"></el-button>
        <div class="title">重置密码</div>
      </div>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="resetPassword">
        <el-form-item prop="userId">
          <el-input v-model="ruleForm.userId" placeholder="请输入需要重置密码的账号" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="ruleForm.email" placeholder="请输入邮箱进行验证" prefix-icon="el-icon-s-promotion"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="ruleForm.password"
            placeholder="请输入新密码"
            prefix-icon="el-icon-lock"
            show-password
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item prop="checkPassword">
          <el-input
            type="password"
            v-model="ruleForm.checkPassword"
            placeholder="请再次输入新密码"
            prefix-icon="el-icon-lock"
            show-password
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="submitForm('resetPassword')" class="login-btn">修改密码</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPassword !== '') {
          this.$refs.resetPassword.validateField('checkPassword')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        userId: '',
        password: '',
        checkPassword: '',
        email: ''
      },
      rules: {
        userId: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            min: 8,
            max: 10,
            message: '长度在 8 到 10 个字符',
            trigger: 'blur'
          }
        ],
        password: [{ validator: validatePass, trigger: 'blur' }],
        checkPassword: [{ validator: validatePass2, trigger: 'blur' }],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.user
            .resetPassword(JSON.stringify(this.ruleForm))
            .then(res => {
              if (res.code === 0) {
                this.$notify.success('密码重置成功')
                this.$router.push('/login')
              } else {
                this.$notify.error(res.msg)
              }
            })
            .catch(err => {
              console.log(err)
            })
        } else {
          return false
        }
      })
    },
    backLogin() {
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.login-wrap {
  position: fixed;
  background: url(~@/assets/image/background.jpg);
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
}
.tm-title {
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -266px;
  text-align: center;
  font-size: 40px;
  font-weight: 600;
  color: white;
}
.tm-login {
  width: 370px;
  margin: 0 auto;
  box-shadow: 0 4px 6px rgb(0 0 0 / 10%), 0 12px 20px rgb(38 38 38 / 12%);
  padding: 30px 36px;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  opacity: 0.97;
  margin-top: 30px;
}
.top-info {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}
.title {
  margin-left: 20px;
  font-size: 22px;
  color: #262626;
  font-weight: 500;
  line-height: 32px;
}
.login-btn {
  text-align: center;
  width: 100%;
}
.el-form-item /deep/ .el-form-item__content {
  line-height: 20px;
}
</style>
