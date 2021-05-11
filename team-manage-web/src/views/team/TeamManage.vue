<template>
  <div class="team-index">
    <header-top @search-data="searchData" @clear-button="clearButton" @add-team="addTeam" @multiple-delete="multipleDelete" />
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
        <el-table-column fixed prop="name" show-overflow-tooltip label="团队名称" min-width="120" align="center" />
        <el-table-column prop="introduce" show-overflow-tooltip label="团队介绍" min-width="120" align="center" />
        <el-table-column prop="workDirection" show-overflow-tooltip label="工作方向" min-width="120" align="center" />
        <el-table-column prop="fileName" label="团队成员" width="120" align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="editGroupMember(scope.row.id)">点击编辑</el-button>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="140" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editTeam(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              @click="deleteTeam(scope.$index, scope.row)"
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
    <team-dialog
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
import TeamDialog from './components/TeamDialog'
import { pageSeparate } from '@/utils/mixin'
export default {
  name: 'Index',
  components: {
    HeaderTop,
    TeamDialog
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
    this.getAllTeam()
  },
  methods: {
    getAllTeam() {
      this.$http.team
        .getAllTeam()
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
    deleteTeamApi(data) {
      this.$http.team
        .deleteTeam(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除成功'
            })
            this.getAllTeam()
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '删除失败'
          })
        })
    },
    editGroupMember(val) {
      console.log(val)
      this.$router.push({
        name: 'GroupDetail',
        params: {
          id: val
        }
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
          await this.deleteTeamApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除信息
    deleteTeam(index, row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteTeamApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    addTeam(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 编辑
    editTeam(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    clearButton() {
      this.getAllTeam()
    },
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
    },
    searchData(val) {
      let query = {
        params: {
          name: val
        }
      }
      this.$http.team
        .getTeamByName(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.tableData = res.data
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
