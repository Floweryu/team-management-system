<template>
  <div class="document-detail">
    <el-card class="detail-box">
      <div class="doc">
        <div class="document-top">
          <div class="document-top-left">
            <div class="publish-time">
              <span class="flag">发布时间：</span>
              {{ document.publishTime }}
            </div>
            <div class="upload-user">
              <span class="flag">上传者：</span>
              {{ document.uploadUserId }}
            </div>
          </div>
          <div class="document-top-right">
            <div class="view">
              <svg-icon class="view-icon" iconClass="view"></svg-icon>
              {{ document.viewCount }}
            </div>
            <div class="like">
              <svg-icon class="like-icon" iconClass="dianzan"></svg-icon>
              {{ document.likeCount }}
            </div>
            <div class="download-count">
              <svg-icon class="download-count-icon" iconClass="downloadCount"></svg-icon>
              {{ document.downloadCount }}
            </div>
            <el-rate
              class="score"
              v-model="document.averageScore"
              :max="5"
              disabled
              show-score
              text-color="#ff9900"
              score-template="{value}"
            />
          </div>
        </div>
        <div class="document-center">
          <div class="brief">
            <div class="title">{{ document.title }}</div>
            <div class="author">
              <svg-icon iconClass="author"></svg-icon>
              {{ document.author }}
            </div>
            <div class="publish-place">
              <svg-icon class="publish-place-icon" iconClass="publishPlace"></svg-icon>
              {{ document.publishPlace }}
            </div>
          </div>
          <el-row type="flex" align="top" class="row">
            <el-col class="tag" :span="2">摘要：</el-col>
            <el-col class="text" :span="22">{{ document.introduction }}</el-col>
          </el-row>
          <el-row type="flex" align="middle" class="row">
            <el-col class="tag" :span="2">分类：</el-col>
            <el-col class="flag" :span="2" v-for="item in classifyList" :key="item.id">{{ item.name }}</el-col>
          </el-row>
          <el-row type="flex" align="middle" class="row">
            <el-col class="tag" :span="2">标签：</el-col>
            <el-col class="flag" :span="2" v-for="item in labelList" :key="item.id">
              {{ item.name }}
            </el-col>
          </el-row>
        </div>
        <div class="document-bottom">
          <div class="document-bottom-left">
            <div class="is-like" @click="likeEvent">
              <svg-icon v-if="hasLike" class="is-like-icon" iconClass="is_like"></svg-icon>
              <svg-icon v-else class="is-like-icon" iconClass="not_like"></svg-icon>
              <span class="like-count" v-if="hasLike" :class="{ active: hasLike }">已赞{{ likeCount }}</span>
              <span class="like-count" v-else>点赞{{ likeCount }}</span>
            </div>
            <div class="comment">
              <svg-icon class="comment-icon" iconClass="comment"></svg-icon>
              <span class="comment-count">评论{{ commentCount }}</span>
            </div>
            <div class="download">
              <el-button v-if="document.storePath != ''" type="primary" size="mini" @click="downloadFile">
                点击下载<i class="el-icon-download el-icon--right"></i>
              </el-button>
              <span v-else class="download-text">文件资源暂缺</span>
            </div>
          </div>

          <div class="document-bottom-right">
            <div class="rate">
              <span v-if="!isDisabled" class="rate-text">评分：</span>
              <span v-else class="rate-text">已提交过评分</span>
              <el-rate v-if="!isDisabled" v-model="score" @change="submitScore" :disabled="isDisabled"> </el-rate>
            </div>
          </div>
        </div>
      </div>
      <div class="user-comment">
        <div class="self-comment">
          <el-input
            class="self-comment-text"
            type="textarea"
            v-model="comment.content"
            :autosize="{ minRows: 1, maxRows: 4 }"
            placeholder="写下你的评论..."
            maxlength="1000"
            show-word-limit
          ></el-input>
          <el-button type="primary" size="mini" @click="publishBtn">发布</el-button>
        </div>
        <div class="comment-list" v-for="(item, index) in commentList" :key="index">
          <el-row type="flex" align="top" class="row">
            <el-col class="comment-user" :span="3"><svg-icon iconClass="author"></svg-icon>{{ item.fromUid + '：' }}</el-col>
            <el-col class="comment-content" :span="21">
              <span class="content-text">{{ item.content }}</span>
              <el-button class="delete-comment-btn" type="text" size="mini" @click="deleteComment(item.id)">删除</el-button>
            </el-col>
          </el-row>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { formatDate } from '@/utils/index'
export default {
  name: 'DocumentDetail',
  data() {
    return {
      documentInfo: {},
      document: {},
      classifyList: [],
      labelList: [],
      id: Number,
      likeCount: Number,
      hasLike: false,
      score: null,
      isDisabled: false,
      comment: {
        topicId: this.$route.params.id,
        topicType: 1,
        content: '',
        fromUid: localStorage.getItem('userId'),
        toUid: ''
      },
      commentList: [],
      commentCount: Number
    }
  },
  watch: {
    likeCount(val) {
      this.document.likeCount = val
    }
  },
  created() {
    this.id = this.$route.params.id
    this.getDocumentDetail(this.id)
    this.getCommentByTopicId(this.id)
  },
  methods: {
    getCommentByTopicId(val) {
      let query = {
        params: {
          id: val,
          type: 1
        }
      }
      this.$http.comment
        .getCommentByTopicId(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.commentList = res.data.commentList
            this.commentCount = res.data.commentCount
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    deleteCommentAPI(msg) {
      let query = {
        params: {
          id: msg
        }
      }
      this.$http.comment
        .deleteComment(query)
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '删除成功'
            })
            this.getCommentByTopicId(this.id)
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '删除失败'
          })
        })
    },
    deleteComment(data) {
      this.$confirm('此操作将删除文献, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteCommentAPI(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: '取消删除操作'
          })
        })
    },
    // 获取文献详细信息
    getDocumentDetail(val) {
      let query = {
        params: {
          id: val,
          userId: localStorage.getItem('userId')
        }
      }
      this.$http.document
        .getDocumentDetail(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            this.documentInfo = res.data
            this.document = this.transData(this.documentInfo.document)
            this.likeCount = this.document.likeCount
            this.classifyList = this.documentInfo.classifyList
            this.labelList = this.documentInfo.labelList
            this.isDisabled = this.documentInfo.hasScore
            this.hasLike = this.documentInfo.hasLike
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 转换数据格式
    transData(data) {
      if (data.publishTime) {
        let time = new Date(data.publishTime)
        data.publishTime = formatDate(time, 'yyyy-MM-dd')
      }
      return data
    },
    //点赞触发事件
    likeEvent() {
      let data = {
        documentId: this.id,
        userId: localStorage.getItem('userId')
      }
      if (this.hasLike) {
        this.$http.like
          .deleteLike(JSON.stringify(data))
          .then(res => {
            if (res.code === 0 && res.data) {
              this.hasLike = res.data.hasLike
              this.likeCount = res.data.likeNum
            }
          })
          .catch(err => {
            this.hasLike = true
            console.log(err)
          })
      } else {
        this.$http.like
          .addLike(JSON.stringify(data))
          .then(res => {
            if (res.code === 0 && res.data) {
              this.$notify.success({
                message: '点赞成功'
              })
              this.hasLike = res.data.hasLike
              this.likeCount = res.data.likeNum
            }
          })
          .catch(err => {
            this.$notify.error({
              message: '点赞失败'
            })
            this.hasLike = false
            console.log(err)
          })
      }
    },
    // 提交分数
    submitScore(val) {
      let data = {
        documentId: this.id,
        userId: localStorage.getItem('userId'),
        score: val
      }
      this.$http.score
        .addScore(JSON.stringify(data))
        .then(res => {
          if (res.code === 0) {
            this.isDisabled = true
            this.$notify.success({
              message: '评分成功'
            })
          }
        })
        .catch(err => {
          this.$notify.error({
            message: '评分失败'
          })
          console.log(err)
        })
    },
    // 点击下载文件
    downloadFile() {
      let query = {
        params: {
          fileName: this.document.fileName,
          id: this.document.id
        },
        responseType: 'blob'
      }
      this.$http.document
        .downloadDocument(query)
        .then(res => {
          if (res) {
            let blob = new Blob([res])
            let url = window.URL.createObjectURL(blob)
            let link = document.createElement('a')
            link.href = url
            link.download = this.document.fileName.fileName || '下载文件' //下载后文件名
            document.body.appendChild(link)
            link.click() //点击下载
            link.remove() //下载完成移除元素
            window.URL.revokeObjectURL(link.href) //用完之后使用URL.revokeObjectURL()释放；
          } else {
            this.$notify.error({
              message: '文件下载失败，请重试'
            })
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '文件下载错误'
          })
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
      this.$http.document.searchByTitle(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    },
    // 根据关键字获取用户
    searchByAuthor(val) {
      let query = {
        params: {
          author: val
        }
      }
      this.$http.document.searchByAuthor(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    },
    // 根据关键字获取用户
    searchByPlace(val) {
      let query = {
        params: {
          place: val
        }
      }
      this.$http.document.searchByPlace(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    },
    // 添加评论
    publishBtn() {
      this.$http.comment
        .addComment(JSON.stringify(this.comment))
        .then(res => {
          if (res.code === 0) {
            this.$notify({
              message: '评论成功',
              type: 'success'
            })
            this.comment.content = ''
            this.getCommentByTopicId(this.id)
          } else {
            this.$notify.error({
              message: res.msg
            })
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '评论失败'
          })
        })
    }
  }
}
</script>

<style scoped>
.document-detail {
  margin: 0 10px;
}
.detail-box {
  margin-top: 6px;
}

.doc,
.user-comment {
  width: 80%;
  min-width: 750px;
  margin: 0 auto 30px;
}
.self-comment {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.self-comment-text {
  margin-right: 20px;
}
.comment-list {
  margin-top: 10px;
  padding-bottom: 8px;
  border-bottom: 1px solid #f0f2f5;
  line-height: 24px;
}
.comment-user {
  color: #6d757a;
}
.content-text {
  margin-right: 10px;
}
.document-top,
.document-top-right,
.document-top-left {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.document-top {
  font-size: 13px;
  color: #5f6471;
  line-height: 22px;
}
.document-bottom {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 20px;
}
.document-bottom-left {
  display: flex;
  align-items: center;
}

.view-icon,
.like-icon,
.download-count-icon,
.score {
  margin-left: 16px;
}

.is-like-icon,
.comment-icon {
  margin-right: 4px;
  font-size: 20px;
}
.publish-time,
.upload-user {
  text-align: center;
  color: #fc5531;
  margin-right: 20px;
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
.brief {
  text-align: center;
}
.title {
  padding-top: 25px;
  padding-bottom: 15px;
  font-size: 24px;
  color: #333;
  font-weight: normal;
  line-height: 35px;
  vertical-align: middle;
  font-family: 'Microsoft yahei';
}
.author {
  padding: 0;
  line-height: 27px;
  text-align: center;
  font-size: 14px;
  font-weight: normal;
  word-break: break-all;
}
.publish-place {
  line-height: 27px;
  font-size: 14px;
  font-weight: normal;
}

.is-like,
.comment,
.download {
  width: 60px;
  height: 30px;
  display: flex;
  align-items: center;
  cursor: pointer;
  margin-right: 20px;
}
.download {
  width: 85px;
}
.rate {
  display: flex;
  align-items: center;
}
.rate-text,
.download-text {
  font-size: 14px;
  color: #fc5531;
}
.like-count,
.comment-count {
  font-size: 12px;
}
.active {
  color: #fc5531;
}
</style>
