<template>
  <div class="systemlog-index">
    <header-top @search-data="searchData" @search-time="timeSearch" @clear-button="clearButton" />
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        border
        stripe
      >
        <el-table-column fixed prop="url" show-overflow-tooltip label="请求地址" min-width="120" align="center" />
        <el-table-column prop="description" show-overflow-tooltip label="操作描述" min-width="120" align="center" />
        <el-table-column prop="requestDate" show-overflow-tooltip label="请求时间" min-width="100" align="center" />
        <el-table-column prop="requestMethod" label="请求方法" width="80" align="center" />
        <el-table-column prop="requestBody" show-overflow-tooltip label="请求内容" min-width="120" align="center" />
        <el-table-column prop="reponseStatus" label="响应状态" width="80" align="center" />
        <el-table-column prop="userId" label="操作者" width="90" align="center" />
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
import HeaderTop from './components/HeaderTop'
import { pageSeparate } from '@/utils/mixin'
import { formatDate } from '@/utils/index'
export default {
  name: 'Index',
  components: {
    HeaderTop
  },
  mixins: [pageSeparate],
  data() {
    return {
      tableData: []
    }
  },
  created() {
    this.getAllSystemLog()
  },
  methods: {
    getAllSystemLog() {
      this.$http.systemlog
        .getAllSystemLog()
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
        if (item.requestDate) {
          let time = new Date(item.requestDate)
          item.requestDate = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
        }
      })
      return data
    },
    clearButton() {
      this.getAllSystemLog()
    },
    timeSearch(val) {
      console.log(val)
      let query = {
        params: {
          time: val
        }
      }
      this.$http.systemlog
        .getSystemLogByTime(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.tableData = this.transData(res.data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },

    searchData(val) {
      let query = {
        params: {
          userId: val
        }
      }
      this.$http.systemlog
        .getSystemLogByUserId(query)
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

<style scoped></style>
