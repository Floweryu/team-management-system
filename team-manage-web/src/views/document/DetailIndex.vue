<template>
  <div id="document-detail">
    <header-top
      @search-data="searchData(arguments)"
      @clear-button="clearButton"
      @sort-event="sort"
      @classify-select-change="searchByClassify"
      @label-select-change="searchByLabel"
    />
    <el-card class="detail-box">
      <div class="item-box" v-for="(item, index) in tableData" :key="index">
        <div class="info-top">
          <div class="title">
            <span class="flag">标题</span>
            <el-button type="text" class="title-button" @click="jumpDetail(item.id)">{{ item.title }}</el-button>
          </div>
        </div>
        <div class="info-center">
          <div>{{ item.introduction }}</div>
        </div>
        <div class="info-bottom">
          <div class="info-bottom-left">
            <div class="author">
              <svg-icon class="author-name" iconClass="author"></svg-icon>
              {{ item.author }}
            </div>
            <div class="time">
              <svg-icon class="time-icon" iconClass="time"></svg-icon>
              {{ item.publishTime }}
            </div>
            <div class="publish-place">
              <svg-icon class="publish-place-icon" iconClass="publishPlace"></svg-icon>
              {{ item.publishPlace }}
            </div>
          </div>
          <div class="info-bottom-right">
            <div class="view">
              <svg-icon class="view-icon" iconClass="view"></svg-icon>
              {{ item.viewCount }}
            </div>
            <div class="like">
              <svg-icon class="like-icon" iconClass="dianzan"></svg-icon>
              {{ item.likeCount }}
            </div>
            <div class="download-count">
              <svg-icon class="download-count-icon" iconClass="downloadCount"></svg-icon>
              {{ item.downloadCount }}
            </div>
            <el-rate class="score" v-model="item.averageScore" :max="5" disabled show-score text-color="#ff9900" score-template="{value}" />
          </div>
        </div>
      </div>
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
import HeaderTop from './components/DetailHead'
import { formatDate } from '@/utils/index'
import { pageSeparate } from '@/utils/mixin'
export default {
  name: 'DetailIndex',
  components: {
    HeaderTop
  },
  mixins: [pageSeparate],
  data() {
    return {
      tableData: [],
      classifyList: []
    }
  },
  created() {
    this.getAllDocuments()
  },
  methods: {
    // 获取所有文献
    getAllDocuments() {
      this.$http.document
        .getAllDocument()
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.tableData = this.transData(data)
            this.tableData.sort(this.compare('likeCount'))
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 转换数据格式
    transData(data) {
      data.forEach(item => {
        if (item.publishTime) {
          let time = new Date(item.publishTime)
          item.publishTime = formatDate(time, 'yyyy-MM-dd')
        }
        if (item.uploadTime) {
          let time = new Date(item.uploadTime)
          item.uploadTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
        }
      })
      return data
    },
    searchByClassify(val) {
      if (val) {
        let query = {
          params: {
            id: val
          }
        }
        this.$http.document
          .getDocumentByClassify(query)
          .then(res => {
            if (res.code === 0 && res.data) {
              this.tableData = this.transData(res.data)
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    searchByLabel(val) {
      if (val) {
        let query = {
          params: {
            id: val
          }
        }
        this.$http.document
          .getDocumentByLabel(query)
          .then(res => {
            if (res.code === 0 && res.data) {
              this.tableData = this.transData(res.data)
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    compare(key) {
      return function(a, b) {
        let val1 = a[key]
        let val2 = b[key]
        return val2 - val1
      }
    },
    sort(type) {
      this.tableData.sort(this.compare(type))
    },
    // 跳转详情页
    jumpDetail(val) {
      console.log(val)
      this.$router.push({
        name: 'Detail',
        params: {
          id: val
        }
      })
    },
    clearButton() {
      this.getAllDocuments()
    },
    // 搜索用户
    searchData(msg) {
      let searchType = msg[0]
      let inputData = msg[1]
      switch (searchType) {
        case 0:
          this.searchByTitle(inputData)
          break
        case 1:
          this.searchByAuthor(inputData)
          break
        case 2:
          this.searchByPlace(inputData)
          break
      }
    },
    // 根据关键字获取用户
    searchByTitle(val) {
      let query = {
        params: {
          title: val
        }
      }
      this.$http.document
        .searchByTitle(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.tableData = this.transData(res.data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 根据关键字获取用户
    searchByAuthor(val) {
      let query = {
        params: {
          author: val
        }
      }
      this.$http.document
        .searchByAuthor(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.tableData = this.transData(res.data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 根据关键字获取用户
    searchByPlace(val) {
      let query = {
        params: {
          place: val
        }
      }
      this.$http.document
        .searchByPlace(query)
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
#document-detail {
  margin: 0 15px;
}
.detail-box {
  display: flex;
  flex-direction: column;
  margin-top: 7px;
}
.detail-box /deep/ .el-card__body {
  padding: 0;
}
.item-box {
  padding: 16px 24px 12px 24px;
  border-bottom: 1px solid #f0f2f5;
}
.item-box:hover {
  background: hsl(0, 0%, 96%);
}

.info-top {
  display: flex;
  align-items: center;
  justify-content: space-between;

  font-family: 'PingFang SC', 'Microsoft YaHei', 'SimHei', 'Arial', 'SimSun';
}

.el-button--text {
  color: #555666;
}
.el-button--text:hover {
  color: #409eff;
}
.el-button {
  padding: 0;
  font-weight: 500;
  font-size: 18px;
  line-height: 20px;
}

.author {
  color: #555666;
  font-size: 12px;
  line-height: 20px;
  font-weight: 500;
}

.flag {
  display: inline-block;
  color: #fc5531;
  background-color: #fff5f2;
  box-sizing: border-box;
  width: 34px;
  height: 20px;
  line-height: 20px;
  text-align: center;
  font-size: 12px;
  border-radius: 2px;
  margin-right: 4px;
}

.for-author {
  font-size: 12px;
}

.info-center {
  margin-top: 6px;
  font-size: 14px;
  line-height: 25px;
  white-space: normal;
  color: #999aaa;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.info-bottom {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 2px;
  font-size: 13px;
  color: #5f6471;
  line-height: 22px;
}
.info-bottom-left,
.info-bottom-right {
  display: flex;
  align-items: center;
}
.time-icon,
.view-icon,
.like-icon,
.publish-place-icon,
.download-count-icon,
.score {
  margin-left: 16px;
}
</style>
