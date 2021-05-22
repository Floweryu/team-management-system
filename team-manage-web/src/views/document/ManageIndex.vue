<template>
  <div class="manage-index">
    <header-top
      @search-data="searchData(arguments)"
      @clear-button="clearButton"
      @add-document="addDocument"
      @multiple-delete="multipleDelete"
    />
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
        <el-table-column fixed prop="title" show-overflow-tooltip label="文献标题" min-width="100" align="center" />
        <el-table-column prop="author" show-overflow-tooltip label="作者" min-width="80" align="center" />
        <el-table-column prop="publishTime" sortable label="发表时间" min-width="100" align="center" />
        <el-table-column prop="publishPlace" show-overflow-tooltip label="出版单位" min-width="80" align="center" />
        <el-table-column prop="size" label="MB" width="60" align="center" />
        <el-table-column prop="originAddress" show-overflow-tooltip label="来源地址" min-width="120" align="center">
          <template slot-scope="scope">
            <a :href="scope.row.originAddress" target="_blank" class="buttonText">{{ scope.row.originAddress }}</a>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="likeCount" label="点赞数" width="60" align="center" />
        <el-table-column prop="viewCount" label="浏览量" width="60" align="center" />
        <el-table-column prop="downloadCount" label="下载次数" width="80" align="center" /> -->
        <el-table-column prop="uploadUserId" label="上传用户" width="90" align="center" />
        <el-table-column prop="fileName" show-overflow-tooltip label="文件名(点击下载)" width="120" align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="downloadFile(scope.row)">{{ scope.row.fileName }}</el-button>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="文献上传" width="100" align="center">
          <template slot-scope="scope">
            <el-progress v-if="loading" type="line" :percentage="progressPercent" class="progress" :show-text="true"></el-progress>
            <el-upload
              v-else
              ref="upload"
              class="file-upload"
              action="#"
              :data="scope.row"
              :http-request="customUpload"
              :auto-upload="true"
              :before-upload="beforeUpload"
              :on-success="handleSuccess"
              :on-error="handleError"
              :show-file-list="false"
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
  name: 'DocumentManage',
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
      selectRows: [],
      progressPercent: 0,
      loading: false
    }
  },
  created() {
    if (this.$store.state.role === 'admin') {
      this.getAllDocuments()
    }
    if (this.$store.state.role === 'common') {
      this.getAllDocumentByUploadUser()
    }
  },
  methods: {
    getAllDocumentByUploadUser() {
      let query = {
        params: {
          userId: localStorage.getItem('userId')
        }
      }
      this.$http.document.getAllDocumentByUploadUser(query).then(res => {
        if (res.code === 0 && res.data) {
          let data = res.data
          this.tableData = this.transData(data)
        }
      })
    },
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
    customUpload(param) {
      this.progressPercent = 0
      console.log(param)
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
        url: `${process.env.VUE_APP_BASE_URL}/manage/document/upload?id=${param.data.id}`,
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

    // 上传前钩子
    beforeUpload(file) {
      const size = file.size / 1024 / 1024
      if (size >= 20) {
        this.$message.error('上传的文件大小必须小于 20M ')
        return false
      }
      this.loading = true
      return true
    },
    // 上传成功后
    handleSuccess(res) {
      if (res.data.code === 0) {
        this.getAllDocuments()
        this.$notify.success({
          message: res.data.msg
        })
      } else {
        this.$notify.error({
          message: '文件上传失败'
        })
      }
    },
    // 上传失败后
    handleError(err) {
      this.$notify.error({
        message: err
      })
    },
    // 添加文献事件
    addDocument(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    // 编辑
    editDocument(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
    },
    // 删除接口
    deleteDocumentApi(data) {
      this.$http.document
        .deleteDocument(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除成功'
            })
            this.getAllDocuments()
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '删除失败'
          })
        })
    },
    // 批量删除
    async multipleDelete() {
      this.$confirm('此操作将删除文献, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          let data = []
          this.selectRows.forEach(item => {
            data.push(item.id)
          })
          await this.deleteDocumentApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除信息
    deleteDocument(index, row) {
      this.$confirm('此操作将删除文献, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteDocumentApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 点击下载文件
    downloadFile(row) {
      let query = {
        params: {
          fileName: row.fileName,
          id: row.id
        },
        responseType: 'blob'
      }
      this.$http.document
        .downloadDocument(query)
        .then(res => {
          if (res) {
            let blob = new Blob([res])
            let url = window.URL.createObjectURL(blob)
            let link = document.createElement('a')
            link.href = url
            link.download = row.fileName || '下载文件' //下载后文件名
            document.body.appendChild(link)
            link.click() //点击下载
            link.remove() //下载完成移除元素
            window.URL.revokeObjectURL(link.href) //用完之后使用URL.revokeObjectURL()释放；
          } else {
            this.$notify.error({
              message: '文件下载失败，请重试'
            })
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '文件下载错误'
          })
        })
    },

    clearButton() {
      this.getAllDocuments()
    },
    // 搜索用户
    searchData(msg) {
      let searchType = msg[0]
      let inputData = msg[1]
      switch (searchType) {
        case 0:
          this.searchByTitle(inputData)
          break
        case 1:
          this.searchByAuthor(inputData)
          break
        case 2:
          this.searchByPlace(inputData)
          break
      }
    },
    // 根据关键字获取用户
    searchByTitle(val) {
      let query = {
        params: {
          title: val
        }
      }
      this.$http.document
        .searchByTitle(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.tableData = this.transData(res.data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 根据关键字获取用户
    searchByAuthor(val) {
      let query = {
        params: {
          author: val
        }
      }
      this.$http.document
        .searchByAuthor(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.tableData = this.transData(res.data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 根据关键字获取用户
    searchByPlace(val) {
      let query = {
        params: {
          place: val
        }
      }
      this.$http.document
        .searchByPlace(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.tableData = this.transData(res.data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
.el-card /deep/ .el-card__body {
  padding: 5px;
}

.file-upload {
  height: 28px;
}
.file-upload >>> .el-upload--text {
  width: 76px;
  height: 26px;
  border: 1px;
}
.buttonText {
  color: #409eff;
}
</style>
