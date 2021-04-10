<template>
  <div class="login-wrap">
    <div class="tm-title">TeamManage System</div>
    <div class="tm-login">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
        <el-form-item prop="userId">
          <el-input v-model="ruleForm.userId" placeholder="用户名" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="ruleForm.password" placeholder="密码" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="loginButton">登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      ruleForm: {
        userId: '',
        password: ''
      },
      rules: {
        userId: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    //登录验证
    loginButton() {
      this.$http.user.verifyPassword(JSON.stringify(this.ruleForm)).then(res => {
        if (res.code === 0 && res.data) {
          localStorage.setItem('userId', res.data.userId)
          localStorage.setItem('username', res.data.username)
          localStorage.setItem('loginTime', res.data.loginTime)
          localStorage.setItem('loginCount', res.data.loginCount)
          localStorage.setItem('createUid', res.data.createUid)
          this.$router.push('/dashboard')
          this.$notify.success('登陆成功')
        } else {
          this.$notify.error('登陆失败')
        }
      })
    }
  }
}
</script>

<style scoped>
.login-wrap {
  position: fixed;
  background: url('../../assets/image/banner4.jpg');
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
  /* margin: -8px; */
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
  position: absolute;
  left: 50%;
  top: 50%;
  width: 240px;
  height: 160px;
  margin-left: -180px;
  margin-top: -150px;
  padding: 40px;
  border-radius: 5px;
  background: #4e4b4bdb;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
}
</style>
