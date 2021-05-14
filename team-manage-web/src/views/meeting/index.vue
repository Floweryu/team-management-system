<template>
  <div class="Meeting-index">
    <header-top @add-meeting="addMeeting" @multiple-delete="multipleDelete" />
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
        <el-table-column prop="organizerId" show-overflow-tooltip label="发布者" min-width="100" align="center" />
        <el-table-column prop="startTime" show-overflow-tooltip label="开始时间" min-width="100" align="center" />
        <el-table-column prop="endTime" show-overflow-tooltip label="结束时间" min-width="100" align="center" />
        <el-table-column prop="content" show-overflow-tooltip label="会议内容" min-width="100" align="center" />
        <el-table-column prop="place" show-overflow-tooltip label="会议地点" min-width="100" align="center" />
        <el-table-column prop="_state" show-overflow-tooltip label="任务状态" min-width="100" align="center" />
        <el-table-column fixed="right" label="操作" min-width="120" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editMeeting(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              @click="deleteMeeting(scope.$index, scope.row)"
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
    <meeting-dialog
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
import MeetingDialog from './components/MeetingDialog'
import { pageSeparate } from '@/utils/mixin'
import { formatDate } from '@/utils/index'
export default {
  name: 'Index',
  components: {
    HeaderTop,
    MeetingDialog
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
    this.getAllMeeting()
  },
  methods: {
    getAllMeeting() {
      this.$http.meeting
        .getAllMeeting()
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
        if (item.startTime) {
          let time = new Date(item.startTime)
          item.startTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
        }
        if (item.endTime) {
          let time = new Date(item.endTime)
          item.endTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
        }
        if (item.state != null) {
          switch (item.state) {
            case 0:
              item._state = '未开始'
              break
            case 1:
              item._state = '进行中'
              break
            case 2:
              item._state = '已完成'
              break
          }
        }
      })
      return data
    },
    deleteMeetingApi(data) {
      this.$http.meeting
        .deleteMeeting(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除成功'
            })
            this.getAllMeeting()
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
          await this.deleteMeetingApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除信息
    deleteMeeting(index, row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteMeetingApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    addMeeting(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 编辑
    editMeeting(index, row) {
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
