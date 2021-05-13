<template>
  <div class="Class-index">
    <header-top @add-class="addClass" @multiple-delete="multipleDelete" />
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
        <el-table-column fixed prop="name" show-overflow-tooltip label="课表名称" min-width="120" align="center" />
        <el-table-column prop="storePath" show-overflow-tooltip label="查看课表" min-width="120" align="center">
          <template slot-scope="scope">
            <el-link v-if="scope.row.storePath" type="primary" target="_blank" :href="scope.row.storePath">点击查看</el-link>
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
              <el-button size="mini" type="text">点击上传</el-button>
            </el-upload>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="140" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editClass(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              @click="deleteClass(scope.$index, scope.row)"
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
    <Class-dialog
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
import ClassDialog from './components/ClassDialog'
import { pageSeparate } from '@/utils/mixin'
export default {
  name: 'Index',
  components: {
    HeaderTop,
    ClassDialog
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
    this.getAllClass()
  },
  methods: {
    getAllClass() {
      let query = {
        params: {
          userId: localStorage.getItem('userId')
        }
      }
      this.$http.clas
        .getAllClass(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.tableData = data
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    deleteClassApi(data) {
      this.$http.clas
        .deleteClass(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除成功'
            })
            this.getAllClass()
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
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          let data = []
          this.selectRows.forEach(item => {
            data.push(item.id)
          })
          await this.deleteClassApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除信息
    deleteClass(index, row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteClassApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    addClass(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 编辑
    editClass(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    clearButton() {
      this.getAllClass()
    },
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
    },
    showClass(msg) {
      window.open(msg)
    },
    // 自定义上传
    customUpload(param) {
      this.progressPercent = 0
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
        url: `${process.env.VUE_APP_BASE_URL}/manage/class/upload?id=${param.data.id}`,
        method: 'post',
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
        this.getAllClass()
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
    }
  }
}
</script>

<style>
.el-card__body {
  padding: 5px;
}
.file-upload {
  height: 28px;
}
.el-upload--text {
  width: 76px;
  height: 26px;
  border: 1px;
}
</style>
