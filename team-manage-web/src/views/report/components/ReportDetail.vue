<template>
  <div class="report-detail">
    <el-card class="detail-box">
      <div class="doc">
        <el-row type="flex" align="top" class="row">
          <el-col class="tag" :span="2">已完成任务:</el-col>
          <el-col class="text" :span="22">{{ reportInfo.finishedTask }}</el-col>
        </el-row>
        <el-row type="flex" align="top" class="row">
          <el-col class="tag" :span="2">未完成任务:</el-col>
          <el-col class="text" :span="22">{{ reportInfo.unfinishedTask }}</el-col>
        </el-row>
        <el-row type="flex" align="top" class="row">
          <el-col class="tag" :span="2">计划任务:</el-col>
          <el-col class="text" :span="22">{{ reportInfo.planTask }}</el-col>
        </el-row>
        <el-row type="flex" align="top" class="row">
          <el-col class="tag" :span="2">自我评价:</el-col>
          <el-col class="text" :span="22">{{ reportInfo.selfReview }}</el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import { pageSeparate } from '@/utils/mixin'
export default {
  name: 'ReportDetailShow',
  data() {
    return {
      reportInfo: {},
      id: Number
    }
  },
  mixins: [pageSeparate],
  created() {
    this.id = parseInt(this.$route.params.id)
    this.getReportDetail(this.id)
  },
  methods: {
    getReportDetail(val) {
      let query = {
        params: {
          id: val
        }
      }
      this.$http.report
        .getReportById(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.reportInfo = data
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
.report-detail {
  margin: 0 10px;
}
.detail-box {
  margin-top: 6px;
  margin-bottom: 6px;
}
.doc {
  width: 90%;
  min-width: 750px;
  margin: 0 auto 20px;
}

.flag {
  display: inline-block;
  box-sizing: border-box;
  width: 70px;
  height: 20px;
  border-radius: 2px;
}
.row {
  min-height: 26px;
  padding-top: 8px;
  font-size: 14px;
  color: #000;
  text-align: justify;
}
.row .tag {
  font-weight: bold;
}

.row .text {
  color: #666;
}

.row .flag {
  color: #517faf;
  margin-right: -10px;
}
.title {
  font-size: 24px;
  color: #333;
  font-weight: normal;
  line-height: 35px;
  vertical-align: middle;
  font-family: 'Microsoft yahei';
}
</style>
