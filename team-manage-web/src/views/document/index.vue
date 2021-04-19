<template>
  <div class="user">
    <header-top @search-data="searchData(arguments)" @add-document="addDocument" @multiple-delete="multipleDelete" />
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
        <el-table-column fixed prop="title" show-overflow-tooltip label="文献标题" width="120" align="center" />
        <el-table-column prop="author" label="作者" width="80" align="center" />
        <el-table-column prop="publishTime" label="发表时间" width="90" align="center" />
        <el-table-column prop="introduction" show-overflow-tooltip label="文献简介" width="90" align="center" />
        <el-table-column prop="publishPlace" show-overflow-tooltip label="出版单位" width="80" align="center" />
        <el-table-column prop="size" label="大小(MB)" width="80" align="center" />
        <el-table-column prop="originAddress" show-overflow-tooltip label="来源地址" width="120" align="center">
          <template slot-scope="scope">
            <a :href="scope.row.originAddress" target="_blank" class="buttonText">{{ scope.row.originAddress }}</a>
          </template>
        </el-table-column>
        <el-table-column prop="likeCount" label="点赞数" width="60" align="center" />
        <el-table-column prop="viewCount" label="浏览量" width="60" align="center" />
        <el-table-column prop="downloadCount" label="下载次数" width="80" align="center" />
        <el-table-column prop="uploadUserId" label="上传用户" width="90" align="center" />
        <el-table-column prop="storePath" show-overflow-tooltip label="存储位置" width="80" align="center" />
        <el-table-column fixed="right" prop="storePath" label="文献上传" width="100" align="center">
          <template scope="scope">
            <el-upload
              ref="upload"
              class="file-upload"
              action="#"
              :http-request="file => customUpload(file, scope.row.id)"
              :on-remove="handleRemove"
              :auto-upload="true"
              :before-upload="beforeUpload"
              :on-success="handleSuccess"
              name="file"
            >
              <el-button size="mini" type="primary">点击上传</el-button>
            </el-upload>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="140" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editDocument(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              @click="deleteDocument(scope.$index, scope.row)"
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
    <document-dialog
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
import DocumentDialog from './components/DocumentDialog'

import { formatDate } from '@/utils/index'
import { pageSeparate } from '@/utils/mixin'

export default {
  name: 'SingerManage',
  components: {
    HeaderTop,
    DocumentDialog
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
    this.getAllDocuments()
  },
  methods: {
    // 获取所有文献
    getAllDocuments() {
      this.$http.document.getAllDocument().then(res => {
        if (res.code === 0 && res.data) {
          let data = res.data
          this.tableData = this.transData(data)
        }
      })
    },
    // 转换数据格式
    transData(data) {
      data.forEach(item => {
        if (item.publishTime) {
          let time = new Date(item.publishTime)
          item.publishTime = formatDate(time, 'yyyy-MM-dd')
        }
        if (item.uploadTime) {
          let time = new Date(item.uploadTime)
          item.uploadTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
        }
      })
      return data
    },
    // 自定义上传
    customUpload(file, id) {
      console.log(file)
      let FormDatas = new FormData()
      FormDatas.append('file', file.file)
      this.$axios({
        url: `${process.env.VUE_APP_BASE_URL}/manage/document/upload?id=${id}`,
        method: 'post',
        data: FormDatas,
        //上传进度
        onUploadProgress: progressEvent => {
          let num = ((progressEvent.loaded / progressEvent.total) * 100) | 0 //百分比
          file.onProgress({ percent: num }) //进度条
        }
      }).then(() => {
        file.onSuccess() //上传成功(打钩的小图标)
      })
    },
    beforeUpload(file) {
      const size = file.size / 1024 / 1024
      if (size >= 20) {
        this.$message.error('上传的头像图片大小必须小于 20M ')
        return false
      }
      return true
    },
    // 上传图片成功后
    handleSuccess(res) {
      if (res.code === 0) {
        this.getAllDocuments()
        this.$notify({
          message: '文件上传并更新成功',
          type: 'success'
        })
      } else {
        this.$notify.error({
          message: '文件上传失败'
        })
      }
    },
    // 取消上传
    handleRemove(file) {
      this.$refs.upload.abort() //取消上传
      this.$message({ message: '成功移除' + file.name, type: 'success' })
    },
    // 添加用户事件
    addDocument(val) {
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

<style>
.el-card__body {
  padding: 5px;
}
.el-tooltip__popper {
  max-width: 300px;
}
.file-upload {
  height: 28px;
}
.el-upload--text {
  width: 76px;
  height: 26px;
  border: 1px;
}
.buttonText {
  color: #409eff;
}
</style>
