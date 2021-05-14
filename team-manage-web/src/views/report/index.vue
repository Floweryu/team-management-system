<template>
  <div class="report">
    <header-top @add-report="addReport" />
    <div class="report-detail">
      <el-timeline class="daily-report">
        <el-timeline-item v-for="(item, index) in dailyReportList" :key="index" :timestamp="item.createTime" type="primary" placement="top">
          <el-card class="detail-card" shadow="hover">
            <div class="card-top">
              <h4>{{ item.username + ' 的日报' }}</h4>
              <el-button type="text" class="button" @click="detailReport(item.id)">查看详情</el-button>
            </div>
            <p>{{ item.userId + ' 提交于 ' + item.createTime }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <el-timeline class="weekily-report">
        <el-timeline-item
          v-for="(item, index) in weekilyReportList"
          :key="index"
          :timestamp="item.createTime"
          type="primary"
          placement="top"
        >
          <el-card class="detail-card" shadow="hover">
            <div class="card-top">
              <h4>{{ item.username + ' 的周报' }}</h4>
              <el-button type="text" class="button" @click="detailReport(item.id)">查看详情</el-button>
            </div>
            <p>{{ item.userId + ' 提交于 ' + item.createTime }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>

    <report-dialog ref="child" :dialog-visible="dialogFormVisible" @dialog-cancel="dialogCancel" />
  </div>
</template>

<script>
import HeaderTop from './components/HeaderTop'
import { formatDate } from '@/utils/index'
import ReportDialog from './components/ReportDialog'
export default {
  name: 'Index',
  components: {
    HeaderTop,
    ReportDialog
  },
  data() {
    return {
      reportList: [],
      dailyReportList: [],
      weekilyReportList: [],
      dialogFormVisible: false
    }
  },
  created() {
    this.getAllReport()
  },
  methods: {
    getAllReport() {
      this.$http.report
        .getAllReport()
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.reportList = this.transData(data)
            this.reportList.forEach(item => {
              if (item.type === 1) {
                this.dailyReportList.push(item)
              } else {
                this.weekilyReportList.push(item)
              }
            })
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    transData(data) {
      data.forEach(item => {
        if (item.createTime) {
          let time = new Date(item.createTime)
          item.createTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
        }
      })
      return data
    },
    detailReport(msg) {
      this.$router.push({
        name: 'ReportDetail',
        params: {
          id: msg
        }
      })
    },
    deleteReportApi(data) {
      this.$http.Report.deleteReport(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除成功'
            })
            this.getAllReport()
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '删除失败'
          })
        })
    },
    editReportMember(val) {
      this.ReportId = val
      this.addDialogVisible = true
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
          await this.deleteReportApi(data)
        })
        .catch(() => {
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 删除信息
    deleteReport(index, row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          let data = []
          data.push(row.id)
          this.deleteReportApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    addReport(val) {
      this.isEditButton = false
      this.dialogFormVisible = val
      this.editValue = null
    },
    // 编辑
    editReport(index, row) {
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
.report-detail {
  display: flex;
  justify-content: space-around;
  margin: 20px auto 20px;
}
.daily-report,
.weekily-report {
  width: 45%;
}
.card-top {
  display: flex;
  justify-content: space-between;
}
h4 {
  font-weight: 400;
  color: #1f2f3d;
  line-height: 30px;
  font-size: 17px;
}
p {
  font-size: 14px;
  color: #5e6d82;
  line-height: 1.5em;
}
</style>
