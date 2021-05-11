<template>
  <div class="software-index">
    <detail-top @search-data="searchData" @clear-button="clearButton" />
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        border
        stripe
      >
        <el-table-column fixed prop="name" show-overflow-tooltip label="软件名" width="100" align="center" />
        <el-table-column prop="version" show-overflow-tooltip label="版本号" width="80" align="center" />
        <el-table-column prop="downloadLink" show-overflow-tooltip label="下载地址" min-width="120" align="center">
          <template slot-scope="scope">
            <a :href="scope.row.downloadLink" target="_blank" class="buttonText">{{ scope.row.downloadLink }}</a>
          </template>
        </el-table-column>
        <el-table-column prop="remark" show-overflow-tooltip label="备注" min-width="120" align="center" />
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
import { pageSeparate } from '@/utils/mixin'
import DetailTop from './DetailTop'
export default {
  name: 'Index',
  components: { DetailTop },
  mixins: [pageSeparate],
  data() {
    return {
      tableData: []
    }
  },
  created() {
    this.getAllSoftware()
  },
  methods: {
    getAllSoftware() {
      this.$http.software
        .getAllSoftware()
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
    clearButton() {
      this.getAllSoftware()
    },
    searchData(val) {
      let query = {
        params: {
          name: val
        }
      }
      this.$http.software
        .searchByName(query)
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
