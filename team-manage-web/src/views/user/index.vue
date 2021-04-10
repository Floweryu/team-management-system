<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-03
 -->
<template>
  <div class="user">
    <header-top @input-data="searchData" @add-user="addUser" @multiple-delete="multipleDelete" />
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        @selection-change="handleSelectionChange"
        border
        stripe
      >
        <el-table-column fixed type="selection" width="40" align="center" />
        <el-table-column fixed prop="userId" label="用户账号" width="90" align="center" />
        <el-table-column prop="password" label="账号密码" width="200" align="center" />
        <el-table-column prop="username" label="用户姓名" width="80" align="center" />
        <el-table-column label="是否删除" width="80" align="center">
          <template slot-scope="scope">
            <el-tag size="small" :type="scope.row.deleted ? 'danger' : 'success'"> {{ scope.row.deleted ? '删除' : '正常' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="_sex" label="性别" width="50" align="center" />
        <el-table-column prop="email" label="邮件" width="200" align="center" />
        <el-table-column prop="mobile" label="手机号码" width="110" align="center" />
        <el-table-column prop="qqOpenId" label="QQ" width="110" align="center" />
        <el-table-column prop="wxOpenId" label="微信" width="110" align="center" />
        <el-table-column prop="birth" label="生日" width="90" align="center" />
        <el-table-column prop="loginTime" label="登录时间" width="140" align="center" />
        <el-table-column prop="lastLoginTime" label="上次登录时间" width="140" align="center" />
        <el-table-column prop="loginCount" label="登录次数" width="80" align="center" />
        <el-table-column prop="createUid" label="创建者" width="90" align="center" />
        <el-table-column fixed="right" label="操作" width="140" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editUser(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              @click="deleteUser(scope.$index, scope.row)"
              size="mini"
              type="danger"
              icon="el-icon-delete"
              style="width: 50px; padding: 5px 0;"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <user-dialog
      ref="child"
      :is-edit-button="isEditButton"
      :edit-value="editValue"
      :dialog-visible="dialogFormVisible"
      @dialog-cancel="dialogCancel"
    />
    <page-separate
      :page-sizes="pageSizes"
      :page-size="pageSize"
      :total-count="tableData.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import HeaderTop from './components/HeaderTop'
import UserDialog from './components/UserDialog'

import { formatDate } from '@/utils/index'
import { pageSeparate } from '@/utils/mixin'

export default {
  name: 'SingerManage',
  components: {
    HeaderTop,
    UserDialog
  },
  mixins: [pageSeparate],
  data() {
    return {
      tableData: [],
      dialogFormVisible: false,
      isEditButton: false,
      editValue: {},
      selectRows: []
    }
  },
  created() {
    this.getAllUsers()
  },
  methods: {
    // 获取所有歌手
    getAllUsers() {
      this.$http.user.getAllUser().then(res => {
        if (res.code === 0 && res.data) {
          let data = res.data
          data.forEach(item => {
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
          })
          this.tableData = res.data
        }
      })
    },
    // 模糊查询歌手
    searchData(val) {
      let query = {
        params: {
          name: val
        }
      }
      this.$http.singer.getSingerByName(query).then(res => {
        if (res.code === 0 && res.data) {
          let data = res.data
          data.forEach(item => {
            if (item.birth) {
              let time = new Date(item.birth)
              item.birth = formatDate(time, 'yyyy-MM-dd')
            }
            switch (item.sex) {
              case 0:
                item._sex = '女'
                break
              case 1:
                item._sex = '男'
                break
              case 2:
                item._sex = '组合'
                break
            }
          })
          this.tableData = res.data
        }
      })
    },
    // 添加歌手
    addUser(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = {}
    },
    // 图片上传地址
    uploadUrl(id) {
      return `${process.env.VUE_APP_BASE_URL}/admin/singer/updatePic?id=${id}`
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
    handleImgSuccess(res) {
      if (res.code === 0) {
        this.getAllSingers()
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
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    // 编辑歌手
    editUser(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
    },
    // 删除歌手接口
    deleteSingerApi(data) {
      this.$http.singer
        .deleteSinger(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify({
              message: '删除歌手成功',
              type: 'success'
            })
            this.getAllSingers()
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '删除歌手失败'
          })
        })
    },
    // 批量删除
    async multipleDelete() {
      this.$confirm('此操作将永久删除歌手, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          let data = []
          this.selectRows.forEach(item => {
            data.push(item.id)
          })
          await this.deleteSingerApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除歌手信息
    async deleteUser(index, row) {
      this.$confirm('此操作将永久删除歌手, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          let data = []
          data.push(row.id)
          await this.deleteSingerApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    }
  }
}
</script>

<style>
.el-card__body {
  padding: 5px;
}
.el-tooltip__popper {
  max-width: 300px;
}
.avatar-uploader {
  width: 50px;
  height: 50px;
}
.el-upload--text {
  width: 50px;
  height: 50px;
}
.avatar {
  width: 50px;
  height: 50px;
  display: block;
}
</style>
