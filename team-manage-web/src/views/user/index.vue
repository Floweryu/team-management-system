<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-03
 -->
<template>
  <div class="user">
    <header-top @search-data="searchData(arguments)" @add-user="addUser" @multiple-delete="multipleDelete" />
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
        <el-table-column prop="_identity" label="身份" width="60" align="center" />
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
    // 获取所有用户
    getAllUsers() {
      this.$http.user.getAllUser().then(res => {
        if (res.code === 0 && res.data) {
          let data = res.data
          this.tableData = this.transData(data)
        }
      })
    },
    // 转换数据格式
    transData(data) {
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
      })
      return data
    },
    // 添加用户事件
    addUser(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    // 编辑用户
    editUser(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
    },
    // 删除用户接口
    deleteUserApi(data) {
      this.$http.user
        .deleteUser(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除用户成功'
            })
            this.getAllUsers()
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '删除用户失败'
          })
        })
    },
    // 批量删除
    async multipleDelete() {
      this.$confirm('此操作将删除用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          let data = []
          this.selectRows.forEach(item => {
            data.push(item.id)
          })
          await this.deleteUserApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除用户信息
    deleteUser(index, row) {
      this.$confirm('此操作将删除用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteUserApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 搜索用户
    searchData(msg) {
      console.log(msg)
      let searchType = msg[0]
      let inputData = msg[1]
      switch (searchType) {
        case 0:
          this.getUserByUserId(inputData)
          break
        case 1:
          this.getUserByUserName(inputData)
          break
        case 2:
          this.getUserByIdentity(inputData)
          break
      }
    },
    // 根据用户账号获取用户
    getUserByUserId(val) {
      let query = {
        params: {
          userId: val
        }
      }
      this.$http.user.getUserByUserId(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    },
    // 根据用户姓名获取用户
    getUserByUserName(val) {
      let query = {
        params: {
          username: val
        }
      }
      this.$http.user.getUserByUserName(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    },
    // 根据用户身份获取用户
    getUserByIdentity(val) {
      let query = {
        params: {
          identity: val
        }
      }
      this.$http.user.getUserByIdentity(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    }
  }
}
</script>

<style scpoed>
.el-card /deep/ .el-card__body {
  padding: 5px;
}
</style>
