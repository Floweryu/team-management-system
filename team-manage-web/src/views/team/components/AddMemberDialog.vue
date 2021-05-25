<template>
  <el-dialog :visible="dialogVisible" :before-close="cancel">
    <add-member-dialog-top @search-data="searchData(arguments)" @add-confirm="addConfirm" />
    <el-table
      :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
      style="width: 100%"
      size="small"
      @selection-change="handleSelectionChange"
      border
      stripe
    >
      <el-table-column type="selection" width="40" align="center" />
      <el-table-column prop="userId" label="用户账号" min-width="100" align="center" />
      <el-table-column prop="username" label="用户姓名" min-width="100" align="center" />
      <el-table-column prop="_identity" label="身份" min-width="60" align="center" />
    </el-table>
    <page-separate
      :page-sizes="pageSizes"
      :page-size="pageSize"
      :total-count="tableData.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </el-dialog>
</template>

<script>
import AddMemberDialogTop from './AddMemberDialogTop.vue'
import { pageSeparate } from '@/utils/mixin'
export default {
  components: { AddMemberDialogTop },
  name: 'AddMemberDialog',
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    groupId: Number
  },
  mixins: [pageSeparate],
  data() {
    return {
      tableData: [],
      selectRows: [],
      memberList: []
    }
  },
  created() {
    this.getAllUsers()
  },
  methods: {
    // 获取所有用户
    getAllUsers() {
      this.$http.user.getAllUser().then(res => {
        if (res.code === 0 && res.data) {
          let data = res.data
          this.tableData = this.transData(data)
        }
      })
    },
    transData(data) {
      data.forEach(item => {
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
    addConfirm() {
      this.selectRows.forEach(item => {
        let member = {}
        member.groupId = this.groupId
        member.userId = item.userId
        this.memberList.push(member)
      })
      this.$http.team
        .addTeamMember(JSON.stringify(this.memberList))
        .then(res => {
          if (res.code === 0) {
            this.$notify.success({
              message: '添加成功'
            })
            this.$parent.getTeamMember(this.groupId)
            this.$emit('dialog-cancel')
          } else {
            this.$notify.error({
              message: '成员重复, 添加失败'
            })
          }
        })
        .catch(() => {
          this.$notify.error({
            message: '添加失败'
          })
          this.$emit('dialog-cancel')
        })
    },
    // 搜索用户
    searchData(msg) {
      let searchType = msg[0]
      let inputData = msg[1]
      switch (searchType) {
        case 0:
          this.getUserByUserId(inputData)
          break
        case 1:
          this.getUserByUserName(inputData)
          break
        case 2:
          this.getUserByIdentity(inputData)
          break
      }
    },
    // 根据用户账号获取用户
    getUserByUserId(val) {
      let query = {
        params: {
          userId: val
        }
      }
      this.$http.user.getUserByUserId(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    },
    handleSelectionChange(rows) {
      this.selectRows = rows
    },
    // 根据用户姓名获取用户
    getUserByUserName(val) {
      let query = {
        params: {
          username: val
        }
      }
      this.$http.user.getUserByUserName(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    },
    // 根据用户身份获取用户
    getUserByIdentity(val) {
      let query = {
        params: {
          identity: val
        }
      }
      this.$http.user.getUserByIdentity(query).then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = this.transData(res.data)
        }
      })
    },
    // 关闭弹窗
    cancel() {
      this.$emit('dialog-cancel')
    }
  }
}
</script>

<style scoped></style>
