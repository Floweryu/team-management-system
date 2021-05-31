<template>
  <div class="achievement-index">
    <header-top @search-data="searchData" @clear-button="clearButton" @add-achievement="addAchievement" @multiple-delete="multipleDelete" />
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
        <el-table-column fixed prop="articleName" show-overflow-tooltip label="文章名" width="100" align="center" />
        <el-table-column prop="magazineName" show-overflow-tooltip label="期刊名" width="80" align="center" />
        <el-table-column prop="submitTime" show-overflow-tooltip label="投稿时间" width="100" align="center" />
        <el-table-column prop="checkTime" show-overflow-tooltip label="审核时间" width="100" align="center" />
        <el-table-column prop="receiveTime" show-overflow-tooltip label="接收时间" width="100" align="center" />
        <el-table-column prop="cost" show-overflow-tooltip label="花费(元)" width="80" align="center" />
        <el-table-column prop="reward" show-overflow-tooltip label="奖励(元)" width="80" align="center" />
        <el-table-column prop="userId" show-overflow-tooltip label="投稿人" width="95" align="center" />
        <el-table-column prop="remark" show-overflow-tooltip label="备注" min-width="120" align="center" />
        <el-table-column prop="byUserId" show-overflow-tooltip label="上传者" width="95" align="center" />
        <el-table-column fixed="right" label="操作" width="140" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editAchievement(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              @click="deleteAchievement(scope.$index, scope.row)"
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
    <achievement-dialog
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
import AchievementDialog from './components/AchievementDialog'
import { pageSeparate } from '@/utils/mixin'
import { formatDate } from '@/utils/index'
export default {
  name: 'Index',
  components: {
    HeaderTop,
    AchievementDialog
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
        this.getAllAchievement()
      }
      if (this.$store.state.role === 'common') {
        this.getAchievementByUploadUser()
      }
    },
    getAchievementByUploadUser() {
      let query = {
        params: {
          userId: localStorage.getItem('userId')
        }
      }
      this.$http.achievement
        .getAchievementByUploadUser(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.tableData = this.transData(data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    getAllAchievement() {
      this.$http.achievement
        .getAllAchievement()
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.tableData = this.transData(data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    transData(data) {
      data.forEach(item => {
        if (item.submitTime) {
          let time = new Date(item.submitTime)
          item.submitTime = formatDate(time, 'yyyy-MM-dd')
        }
        if (item.checkTime) {
          let time = new Date(item.checkTime)
          item.checkTime = formatDate(time, 'yyyy-MM-dd')
        }
        if (item.receiveTime) {
          let time = new Date(item.receiveTime)
          item.receiveTime = formatDate(time, 'yyyy-MM-dd')
        }
      })
      return data
    },
    deleteAchievementApi(data) {
      this.$http.achievement
        .deleteAchievement(JSON.stringify(data))
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
          await this.deleteAchievementApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除信息
    deleteAchievement(index, row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteAchievementApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    addAchievement(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 编辑
    editAchievement(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    clearButton() {
      this.getByRole()
    },
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
    },
    searchData(val) {
      let query = {
        params: {
          userId: val
        }
      }
      this.$http.achievement
        .getAchievementByUser(query)
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
.el-card__body {
  padding: 5px;
}
</style>
