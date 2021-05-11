<template>
  <div class="team-detail">
    <el-card class="detail-box">
      <div class="doc">
        <div class="team-center">
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
      </div>
    </el-card>
    <el-card class="team-member">
      <el-table
        :data="userList.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        @selection-change="handleSelectionChange"
        border
        stripe
      >
        <el-table-column type="selection" width="40" align="center" />
        <el-table-column prop="userId" label="成员账号" min-width="90" align="center" />
        <el-table-column prop="username" label="成员姓名" min-width="80" align="center" />
        <el-table-column prop="identity" label="身份" min-width="80" align="center" />
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
            <el-button
              @click="deleteFromTeam(scope.row)"
              size="mini"
              type="danger"
              icon="el-icon-delete"
              style="width: 50px; padding: 5px 0;"
              >移除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
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
export default {
  name: 'TeamDetail',
  data() {
    return {
      teamInfo: {},
      userList: [],
      selectRows: []
    }
  },
  mixins: [pageSeparate],
  created() {
    this.id = this.$route.params.id
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
            if (data.group) {
              this.teamInfo = data.group
            }
            if (data.group && data.userList) {
              this.userList = this.transData(data.userList, data.group.byUserId)
            }
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    transData(data, byUserId) {
      data.forEach(item => {
        if (item.userId === byUserId) {
          item.identity = '负责人'
        } else {
          item.identity = '成员'
        }
      })
      return data
    },
    viewMemberInfo(msg) {
      let query = {
        userId: msg.userId,
        groupId: this.id
      }
      console.log(msg)
    },
    deleteFromTeam(msg) {
      console.log(msg)
    },
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
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
