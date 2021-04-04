<template>
  <div id="user-manage">
    <div class="header">
      <div class="left">
        <el-button size="mini" type="primary" @click="userManage" round icon="el-icon-plus">用户管理</el-button>
        <el-button size="mini" type="primary" @click="addManager" round icon="el-icon-plus">添加管理员</el-button>
      </div>
    </div>
    <el-container>
      <el-tabs tab-position="left" style="width: 100%" @tab-click="switchResource">
        <el-tab-pane v-for="(item, index) in resourceList" :key="index" :name="item.title">
          <span class="tabs" slot="label">
            <label>{{ item.title }}</label>
            <i class="el-icon-arrow-right"></i>
          </span>
          <div class="table">
            <el-card shadow="hover">
              <el-table :data="tableData" style="width: 100%" size="small" @selection-change="handleSelectionChange" border stripe>
                <el-table-column prop="commond" label="命令" min-width="8%" align="center" />
                <el-table-column prop="introduction" show-overflow-tooltip label="简介" min-width="30%" align="center" />
                <el-table-column label="操作" min-width="10%" align="center">
                  <template slot-scope="scope">
                    <el-button
                      @click="edit(scope.$index, scope.row)"
                      size="mini"
                      type="primary"
                      icon="el-icon-edit"
                      style="width: 50px; padding: 5px 0;"
                      >编辑
                    </el-button>
                    <el-button
                      @click="delete (scope.$index, scope.row)"
                      size="mini"
                      type="danger"
                      icon="el-icon-delete"
                      style="width: 50px; padding: 5px 0;"
                      >删除</el-button
                    >
                  </template>
                </el-table-column>
              </el-table>
            </el-card>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'UserManage',
  data() {
    return {
      resourceList: [{ title: '用户1' }, { title: '用户2' }, { title: '用户3' }, { title: '用户4' }, { title: '用户5' }],
      tableData: [
        { commond: 'ls', introduction: '展示' },
        { commond: 'cd', introduction: '进入目录' }
      ],
      selectRows: []
    }
  },
  methods: {
    userManage() {},
    addManager() {},
    delete() {},
    edit() {},
    // 存储选择行信息
    handleSelectionChange(rows) {
      this.selectRows = rows
    }
  }
}
</script>

<style scoped>
.header {
  position: sticky;
  top: -1px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #fff;
  z-index: 9;
  margin: 2px 0;
  border-radius: 8px;
  border: 1px solid #ddd;
  padding: 3px 0;
}
.left {
  margin-left: 10px;
  display: flex;
  align-items: center;
}
.center {
  display: flex;
  align-items: center;
}
.right {
  display: flex;
  align-items: center;
}
</style>
