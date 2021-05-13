<template>
  <div class="team-detail">
    <el-card class="detail-box">
      <div class="doc">
        <el-row type="flex" align="middle" class="row">
          <el-col class="tag" :span="2">团队名称：</el-col>
          <el-col class="title" :span="22">{{ teamInfo.name }}</el-col>
        </el-row>
        <el-row type="flex" align="top" class="row">
          <el-col class="tag" :span="2">团队介绍：</el-col>
          <el-col class="text" :span="22">{{ teamInfo.introduce }}</el-col>
        </el-row>
        <el-row type="flex" align="top" class="row">
          <el-col class="tag" :span="2">工作方向：</el-col>
          <el-col class="text" :span="22">{{ teamInfo.workDirection }}</el-col>
        </el-row>
      </div>
    </el-card>
    <el-card class="team-member">
      <el-table :data="userList.slice((currentPage - 1) * pageSize, currentPage * pageSize)" style="width: 100%" size="small" border stripe>
        <el-table-column prop="userId" label="成员账号" min-width="90" align="center" />
        <el-table-column prop="username" label="成员姓名" min-width="80" align="center" />
        <el-table-column prop="_identity" label="身份" min-width="80" align="center" />
        <el-table-column fixed="right" label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button
              @click="viewMemberInfo(scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 100px; padding: 5px 0;"
              >查看成员信息
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <user-info-dialog :user-info="userInfo" :dialog-visible="dialogFormVisible" @dialog-cancel="dialogCancel" />
    <page-separate
      :page-sizes="pageSizes"
      :page-size="pageSize"
      :total-count="userList.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { pageSeparate } from '@/utils/mixin'
import { formatDate } from '@/utils/index'
import UserInfoDialog from './UserInfoDialog'
export default {
  name: 'TeamDetailShow',
  components: {
    UserInfoDialog
  },
  data() {
    return {
      teamInfo: {},
      userList: [],
      userInfo: {},
      dialogFormVisible: false,
      id: Number
    }
  },
  mixins: [pageSeparate],
  created() {
    this.id = parseInt(this.$route.params.id)
    this.getTeamDetail(this.id)
  },
  methods: {
    // 获取文献详细信息
    getTeamDetail(val) {
      let query = {
        params: {
          id: val
        }
      }
      this.$http.team
        .getTeamById(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.teamInfo = data
            this.getTeamMember(val)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    getTeamMember(val) {
      let query = {
        params: {
          id: val
        }
      }
      this.$http.team
        .getTeamMember(query)
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.userList = this.transData(data)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 转换数据格式
    transData(data) {
      data.forEach(item => {
        if (item.birth) {
          let time = new Date(item.birth)
          item.birth = formatDate(time, 'yyyy-MM-dd')
        }
        if (item.loginTime) {
          let time = new Date(item.loginTime)
          item.loginTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
        }
        if (item.lastLoginTime) {
          let time = new Date(item.lastLoginTime)
          item.lastLoginTime = formatDate(time, 'yyyy-MM-dd hh:mm:ss')
        }
        item._sex = item.sex ? '男' : '女'
        if (item.identity) {
          switch (item.identity) {
            case 0:
              item._identity = '教师'
              break
            case 1:
              item._identity = '大一'
              break
            case 2:
              item._identity = '大二'
              break
            case 3:
              item._identity = '大三'
              break
            case 4:
              item._identity = '大四'
              break
            case 5:
              item._identity = '研一'
              break
            case 6:
              item._identity = '研二'
              break
            case 7:
              item._identity = '研三'
              break
          }
        }
      })
      return data
    },
    viewMemberInfo(msg) {
      this.userInfo = msg
      this.dialogFormVisible = true
    },
    dialogCancel() {
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>
.team-detail {
  margin: 0 10px;
}
.detail-box {
  margin-top: 6px;
  margin-bottom: 6px;
}
.detail-box /deep/ .el-card__body {
  display: flex;
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
