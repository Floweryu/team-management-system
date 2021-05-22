<template>
  <div id="home">
    <el-card class="body">
      <div class="user-main-info">
        <el-upload
          class="avatar-uploader"
          action="#"
          :data="userInfo"
          :http-request="customUpload"
          :before-upload="beforeUpload"
          :on-success="handleSuccess"
          :on-error="handleError"
          :show-file-list="false"
          name="file"
        >
          <img :src="userInfo.picPath" class="avatar" />
          <el-progress v-if="loading" type="line" :percentage="progressPercent" class="progress" :show-text="true"></el-progress>
        </el-upload>
        <div class="user-name-id">
          <div class="username">{{ userInfo.username }}</div>
          <div class="userId">{{ userInfo.userId }}</div>
        </div>
      </div>
    </el-card>
    <el-card class="body" shadow="hover">
      <div class="top">
        <div class="title">基本信息</div>
        <el-button type="text" icon="el-icon-edit" @click="editInfo">编辑</el-button>
      </div>
      <div class="user-info">
        <el-row type="flex" align="middle" class="row-bg">
          <el-col :span="6"><label>身份：</label></el-col>
          <el-col :span="18">
            {{ userInfo._identity }}
          </el-col>
          <el-col :span="6"><label>性别：</label></el-col>
          <el-col :span="18">
            {{ userInfo._sex }}
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col :span="6"><label>是否已删除：</label></el-col>
          <el-col :span="18">
            {{ userInfo._isDelete }}
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
        <el-row type="flex" align="middle" class="row-bg">
          <el-col :span="6"><label>创建者:</label></el-col>
          <el-col :span="18">
            {{ userInfo.createUid }}
          </el-col>
          <el-col :span="6"><label>登录次数：</label></el-col>
          <el-col :span="18">
            {{ userInfo.loginCount }}
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" class="row-bg">
          <el-col :span="6"><label>上次登录时间：</label></el-col>
          <el-col :span="18">
            {{ userInfo.lastLoginTime }}
          </el-col>
          <el-col :span="6"><label>登录时间：</label></el-col>
          <el-col :span="18">
            {{ userInfo.loginTime }}
          </el-col>
        </el-row>
      </div>
    </el-card>
    <user-dialog
      ref="child"
      :is-edit-button="isEditButton"
      :edit-value="editValue"
      :dialog-visible="dialogFormVisible"
      @dialog-cancel="dialogCancel"
    />
  </div>
</template>

<script>
import UserDialog from '@/views/user/components/UserDialog'
import { formatDate } from '@/utils/index'
export default {
  name: 'Home',
  components: {
    UserDialog
  },
  data() {
    return {
      userInfo: {},
      edit: false,
      dialogFormVisible: false,
      isEditButton: true,
      editValue: {},
      loading: false,
      progressPercent: 0
    }
  },
  created() {
    this.getUserByUserId()
  },
  methods: {
    dialogCancel() {
      this.dialogFormVisible = false
    },
    editInfo() {
      this.dialogFormVisible = true
      this.editValue = this.userInfo
    },
    // 根据用户账号获取用户
    getUserByUserId() {
      let query = {
        params: {
          userId: localStorage.getItem('userId')
        }
      }
      this.$http.user.getUserByUserId(query).then(res => {
        if (res.code === 0 && res.data) {
          this.userInfo = this.transData(res.data[0])
          this.$store.commit('changePicPath', this.userInfo.picPath)
        }
      })
    },
    transData(item) {
      if (item.birth) {
        let time = new Date(item.birth)
        item.birth = formatDate(time, 'yyyy-MM-dd')
      }
      if (item.loginTime) {
        let time = new Date(item.loginTime)
        item.loginTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
      }
      if (item.lastLoginTime) {
        let time = new Date(item.lastLoginTime)
        item.lastLoginTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
      }
      item._sex = item.sex ? '男' : '女'
      item._isDelete = item.deleted ? '是' : '否'
      if (item.identity) {
        switch (item.identity) {
          case 0:
            item._identity = '教师'
            break
          case 1:
            item._identity = '大一'
            break
          case 2:
            item._identity = '大二'
            break
          case 3:
            item._identity = '大三'
            break
          case 4:
            item._identity = '大四'
            break
          case 5:
            item._identity = '研一'
            break
          case 6:
            item._identity = '研二'
            break
          case 7:
            item._identity = '研三'
            break
        }
      }
      return item
    },
    // 图片上传地址
    customUpload(param) {
      let formData = new FormData()
      formData.append('file', param.file)
      const onUploadProgress = progressEvent => {
        this.progressPercent = Math.floor((progressEvent.loaded * 100) / progressEvent.total)
        if (this.progressPercent >= 100) {
          setTimeout(() => {
            this.loading = false
          }, 1500)
        }
      }
      this.$axios({
        url: `${process.env.VUE_APP_BASE_URL}/manage/user/updatePic?id=${param.data.id}`,
        method: 'post',
        headers: {
          token: localStorage.getItem('token')
        },
        data: formData,
        //上传进度
        onUploadProgress
      })
        .then(res => {
          param.onSuccess(res)
        })
        .catch(err => {
          param.onError(err)
        })
    },
    // 校验图片格式
    beforeUpload(file) {
      const isPic = file.type === 'image/jpeg' || file.type === 'image/png'
      if (!isPic) {
        this.$message.error('上传的头像图片必须为 jpg 或 png 格式')
        return false
      }

      const size = file.size / 1024 / 1024
      if (size >= 2) {
        this.$message.error('上传的头像图片大小必须小于 2M ')
        return false
      }
      return true
    },
    // 上传图片成功后
    handleSuccess(res) {
      if (res.data.code === 0) {
        this.getUserByUserId()
        this.$notify({
          message: '图片上传并更新成功',
          type: 'success'
        })
      } else {
        this.$notify.error({
          message: '图片上传失败'
        })
      }
    },
    handleError(err) {
      this.$notify.error({
        message: err
      })
    }
  }
}
</script>

<style scoped>
#home {
  display: flex;
  flex-direction: column;
}
.el-card /deep/ .el-card__body {
  padding: 20px;
}
.body {
  height: 48%;
  width: 96%;
  margin: 10px auto;
}
.top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}
.title {
  font-size: 22px;
  color: #262626;
  font-weight: 500;
  line-height: 32px;
}
.user-main-info {
  display: flex;
  align-items: center;
}
.user-name-id {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
}
.username {
  font-size: 22px;
  color: #262626;
  font-weight: 500;
  line-height: 32px;
  margin-bottom: 20px;
}
.userId {
  color: #5f6471;
  font-size: 16px;
}
.avatar-uploader >>> .el-upload {
  width: 100px;
  height: 100px;
  border: 2px solid #fff;
  box-shadow: 0 0 10px 2px rgb(0 0 0 / 6%);
  border-radius: 50%;
}
.avatar {
  width: 100%;
  height: 100%;
  display: block;
  border-radius: 50%;
}
.avator-hover {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
}
.row-bg {
  padding: 8px 8px;
  background-color: #fbfbfb85;
  border-radius: 4px;
}
</style>
