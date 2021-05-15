<template>
  <div class="Assets-index">
    <header-top @search-data="searchData" @clear-button="clearButton" @add-assets="addAssets" @multiple-delete="multipleDelete" />
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
        <el-table-column fixed prop="number" show-overflow-tooltip label="资产编号" width="100" align="center" />
        <el-table-column prop="name" show-overflow-tooltip label="资产名" min-width="120" align="center" />
        <el-table-column prop="userId" show-overflow-tooltip label="使用者" width="100" align="center" />
        <el-table-column prop="remark" show-overflow-tooltip label="备注" min-width="120" align="center" />
        <el-table-column prop="byUserId" show-overflow-tooltip label="上传者" width="100" align="center" />
        <el-table-column fixed="right" label="操作" width="140" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editAssets(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              v-if="$store.state.role === 'admin'"
              @click="deleteAssets(scope.$index, scope.row)"
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
    <assets-dialog
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
import AssetsDialog from './components/AssetsDialog'
import { pageSeparate } from '@/utils/mixin'
export default {
  name: 'Index',
  components: {
    HeaderTop,
    AssetsDialog
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
    this.getAllAssets()
  },
  methods: {
    getAllAssets() {
      this.$http.assets
        .getAllAssets()
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
    deleteAssetsApi(data) {
      this.$http.assets
        .deleteAssets(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除成功'
            })
            this.getAllAssets()
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
          await this.deleteAssetsApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除信息
    deleteAssets(index, row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteAssetsApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    addAssets(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 编辑
    editAssets(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    clearButton() {
      this.getAllAssets()
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
      this.$http.assets
        .getAssetsByUser(query)
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
