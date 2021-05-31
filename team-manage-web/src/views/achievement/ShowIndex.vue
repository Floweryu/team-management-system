<template>
  <div class="achievement-index">
    <show-top @search-data="searchData" @clear-button="clearButton" />
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        border
        stripe
      >
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
      </el-table>
    </el-card>
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
import ShowTop from './components/ShowTop'
import { pageSeparate } from '@/utils/mixin'
import { formatDate } from '@/utils/index'
export default {
  name: 'Index',
  components: {
    ShowTop
  },
  mixins: [pageSeparate],
  data() {
    return {
      tableData: []
    }
  },
  created() {
    this.getAllAchievement()
  },
  methods: {
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
    clearButton() {
      this.getAllAchievement()
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
.el-card /deep/ .el-card__body {
  padding: 5px;
}
</style>
