<template>
  <div class="team-index">
    <header-top @search-data="searchData" @clear-button="clearButton" />
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        border
        stripe
      >
        <el-table-column fixed prop="name" show-overflow-tooltip label="团队名称" min-width="120" align="center" />
        <el-table-column prop="introduce" show-overflow-tooltip label="团队介绍" min-width="120" align="center" />
        <el-table-column prop="workDirection" show-overflow-tooltip label="工作方向" min-width="120" align="center" />
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
import HeaderTop from './components/TeamShowTop'
import { pageSeparate } from '@/utils/mixin'
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

    clearButton() {
      this.getAllTeam()
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
