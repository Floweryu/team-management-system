<template>
  <div class="login-wrap">
    <div class="tm-title">
      <el-image :src="require('@/assets/image/app_name.png')"></el-image>
    </div>
    <el-card class="tm-login">
      <div class="top-info">
        <div class="title">登录以继续使用</div>
        <el-button class="forget-password" type="text" @click="forgetPassword">忘记密码?</el-button>
      </div>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="loginFromRef">
        <el-form-item prop="userId">
          <el-input v-model="ruleForm.userId" placeholder="用户名" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="ruleForm.password" placeholder="密码" prefix-icon="el-icon-lock" show-password></el-input>
        </el-form-item>
        <el-form-item prop="verifyCode" class="verify">
          <el-row align="middle">
            <el-col :span="12">
              <el-input v-model="ruleForm.verifyCode" prefix-icon="el-icon-message" class="code" placeholder="验证码"></el-input>
            </el-col>
            <el-col :span="8">
              <img class="verifyCodeImg" :src="imgUrl" @click="resetImg" />
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="loginButton" class="login-btn">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      ruleForm: {
        userId: '',
        password: '',
        verifyCode: ''
      },
      imgUrl: `${process.env.VUE_APP_BASE_URL}/manage/verifyCode?time=` + new Date(),
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
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        verifyCode: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    //登录验证
    loginButton() {
      this.$refs.loginFromRef.validate(valid => {
        if (valid) {
          this.$http.user
            .verifyPassword(JSON.stringify(this.ruleForm))
            .then(res => {
              if (res.code === 0 && res.data) {
                this.$store.commit('storeUserinfo', res.data)
                this.$store.commit('changeToken', res.data.token)
                this.$router.push('/dashboard')
                this.$notify.success('登陆成功')
              } else if (res.code === 400) {
                this.$notify.error(res.msg)
              } else if (res.code === 401) {
                this.$notify.error('验证码输入错误')
                this.imgUrl = `${process.env.VUE_APP_BASE_URL}/manage/verifyCode?time=` + new Date()
              } else {
                this.$notify.error(res.msg)
              }
            })
            .catch(err => {
              console.log(err)
            })
        }
      })
    },
    forgetPassword() {
      this.$router.push('/forget_password')
    },
    resetImg() {
      this.imgUrl = `${process.env.VUE_APP_BASE_URL}/manage/verifyCode?time=` + new Date()
    }
  }
}
</script>

<style scoped>
.login-wrap {
  position: fixed;
  background: url('../../assets/image/background.jpg');
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
  justify-content: space-between;
}
.title {
  font-size: 22px;
  color: #262626;
  font-weight: 500;
  line-height: 32px;
  margin-bottom: 30px;
}
.forget-password {
  margin-bottom: 30px;
}
.login-btn {
  text-align: center;
  width: 100%;
}
.el-form-item /deep/ .el-form-item__content {
  line-height: 20px;
}
.verifyCodeImg {
  width: 100%;
  height: 40px;
  margin-left: 30px;
  cursor: pointer;
}
</style>
