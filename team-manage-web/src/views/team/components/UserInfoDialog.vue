<template>
  <el-dialog :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-row type="flex" align="middle" class="row-bg">
        <el-col :span="6"><label>账号：</label></el-col>
        <el-col :span="18">
          {{ userInfo.userId }}
        </el-col>
        <el-col :span="6"><label>用户名：</label></el-col>
        <el-col :span="18">
          {{ userInfo.username }}
        </el-col>
      </el-row>
      <el-row type="flex" align="middle" class="row-bg">
        <el-col :span="6"><label>性别：</label></el-col>
        <el-col :span="18">
          {{ userInfo._sex }}
        </el-col>
        <el-col :span="6"><label>出生日期：</label></el-col>
        <el-col :span="18">
          {{ userInfo.birth }}
        </el-col>
      </el-row>
      <el-row type="flex" align="middle" class="row-bg">
        <el-col :span="6"><label>邮件：</label></el-col>
        <el-col :span="18">
          {{ userInfo.email }}
        </el-col>
        <el-col :span="6"><label>电话：</label></el-col>
        <el-col :span="18">
          {{ userInfo.mobile }}
        </el-col>
      </el-row>
      <el-row type="flex" align="middle" class="row-bg">
        <el-col :span="6"><label>QQ：</label></el-col>
        <el-col :span="18">
          {{ userInfo.qqOpenId }}
        </el-col>
        <el-col :span="6"><label>微信：</label></el-col>
        <el-col :span="18">
          {{ userInfo.wxOpenId }}
        </el-col>
      </el-row>
    </el-card>
  </el-dialog>
</template>

<script>
export default {
  name: 'UserInfoDialog',
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    userInfo: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  methods: {
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
