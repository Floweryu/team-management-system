<template>
  <div class="Classify-index">
    <header-top @add-classify="addClassify" @multiple-delete="multipleDelete" />
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
        <el-table-column fixed prop="name" show-overflow-tooltip label="类别名" min-width="100" align="center" />
        <el-table-column prop="description" show-overflow-tooltip label="类别描述" min-width="150" align="center" />
        <el-table-column prop="byUserId" show-overflow-tooltip label="创建者" width="90" align="center" />
        <el-table-column fixed="right" label="操作" width="140" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editClassify(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              @click="deleteClassify(scope.$index, scope.row)"
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
    <classify-dialog
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
import ClassifyDialog from './components/ClassifyDialog'
import { pageSeparate } from '@/utils/mixin'
export default {
  name: 'Index',
  components: {
    HeaderTop,
    ClassifyDialog
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
    this.getByRole()
  },
  methods: {
    getByRole() {
      if (this.$store.state.role === 'admin') {
        this.getAllClassify()
      }
      if (this.$store.state.role === 'common') {
        this.getAllClassifyByUserId()
      }
    },
    getAllClassifyByUserId() {
      let query = {
        params: {
          userId: localStorage.getItem('userId')
        }
      }
      this.$http.classify
        .getAllClassifyByUserId(query)
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
    getAllClassify() {
      this.$http.classify
        .getAllClassify()
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
    deleteClassifyApi(data) {
      this.$http.classify
        .deleteClassify(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除成功'
            })
            this.getByRole()
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
          await this.deleteClassifyApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除信息
    deleteClassify(index, row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteClassifyApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    addClassify(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 编辑
    editClassify(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
    }
  }
}
</script>

<style scoped>
.el-card__body {
  padding: 5px;
}
</style>
