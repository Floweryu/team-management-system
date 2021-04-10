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
          <el-col>
            <el-form-item prop="deleted" label="是否删除" size="mini">
              <el-radio-group v-model="formData.deleted">
                <el-radio :label="1">已删除</el-radio>
                <el-radio :label="0">正常</el-radio>
              </el-radio-group>
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
              <el-button type="primary" size="small" @click="submitForm('formData')">添加</el-button>
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
      formData: {
        userId: '',
        password: '',
        username: '',
        deleted: 0,
        sex: 1,
        email: '',
        mobile: '',
        qqOpenId: '',
        wxOpenId: '',
        birth: '',
        createUid: localStorage.getItem('userId')
      },
      rules: {
        userId: [
          { required: true, message: '用户账号不能为空', trigger: 'blur' },
          { type: 'number', message: '用户账号为数字值' },
          {
            validator: function(rule, value, callback) {
              if (value < 1000000000 || value > 9999999999) {
                callback(new Error('用户账号长度为10'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 30, message: '长度在 6 到 30 个字符' },
          { pattern: /^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线' }
        ],
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符' },
          { pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          {
            validator: function(rule, value, callback) {
              if (/^1[34578]\d{9}$/.test(value) == false) {
                callback(new Error('请输入正确的手机号'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        qqOpenId: [
          {
            validator: function(rule, value, callback) {
              if (value === '') {
                callback(new Error('请输入QQ号'))
              } else if (/^[1-9][0-9]{4,10}$/.test(value)) {
                callback()
              } else {
                callback(new Error('输入正确的QQ号'))
              }
            },
            trigger: 'blur'
          }
        ],
        wxOpenId: [{ min: 6, max: 30, message: '微信号长度在 6 到 30 个字符', trigger: 'blur' }],
        birth: [{ type: 'date', message: '请选择日期', trigger: 'change' }]
      }
    }
  },
  methods: {
    // 新增歌手信息
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.user
            .addUser(JSON.stringify(this.formData))
            .then(res => {
              if (res.code === 0) {
                this.$notify({
                  message: '添加用户成功',
                  type: 'success'
                })
                this.$parent.getAllUsers()
              }
            })
            .catch(() => {
              this.$notify.error({
                message: '添加歌手失败'
              })
            })
          this.$emit('dialog-cancel') // 关闭弹窗
        } else {
          return false
        }
      })
    },
    // 保存歌手信息
    saveUser() {
      if (this.checkForm()) {
        this.$http.singer
          .updateSinger(JSON.stringify(this.formData))
          .then(res => {
            if (res.code === 0) {
              this.$notify({
                message: '歌手信息更新成功',
                type: 'success'
              })
              this.$parent.getAllSingers()
            }
          })
          .catch(() => {
            this.$notify.error({
              message: '歌手信息更新失败'
            })
          })
        this.$emit('dialog-cancel') // 关闭弹窗
      }
    },
    // 关闭弹窗
    cancel() {
      this.$emit('dialog-cancel')
    },
    // 检查表单是否符合要求
    checkForm() {
      if (this.formData.name === '' || typeof this.formData.name === 'undefined') {
        alert('歌手姓名不能为空！')
        return false
      }
      return true
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
