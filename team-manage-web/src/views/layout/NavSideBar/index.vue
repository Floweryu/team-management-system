<template>
  <div id="nav-sidebar">
    <el-menu
      class="el-menu-sidebar"
      :default-active="onRoutes"
      :collapse="collapse"
      background-color="#FFFFFF"
      text-color="#83A3C5"
      active-text-color="#409EFF"
      unique-opened
      router
    >
      <template v-for="item in menuList">
        <template v-if="item.childMenu.length != 0">
          <el-submenu :index="item.path" :key="item.path">
            <template slot="title">
              <svg-icon :iconClass="item.icon"></svg-icon>
              <span slot="title">{{ item.name }}</span>
            </template>
            <template v-for="subMenu in item.childMenu">
              <el-menu-item :index="subMenu.path" :key="subMenu.path">
                <svg-icon :iconClass="subMenu.icon"></svg-icon>
                <span slot="title">{{ subMenu.name }}</span>
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.path" :key="item.path">
            <svg-icon :iconClass="item.icon"></svg-icon>
            <span slot="title">{{ item.name }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'NavSidebar',
  data() {
    return {
      collapse: false,
      menuList: []
    }
  },
  computed: {
    onRoutes() {
      return this.$route.path
    }
  },
  created() {
    this.getMenus()
    // 接收 home-header 发送来的 collapse
    this.$bus.$on('collapse', msg => {
      this.collapse = msg
    })
  },
  methods: {
    // 获取所有菜单
    getMenus() {
      let query = {
        params: {
          role: localStorage.getItem('role')
        }
      }
      this.$http.menu
        .getMenus(query)
        .then(res => {
          if (res.code == 0 || res.data) {
            this.menuList = res.data
          } else {
            this.$notify.error(res.msg)
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
#nav-sidebar {
  position: absolute;
  display: block;

  left: 0;
  top: 52px;
  bottom: 0;
  overflow-y: scroll;
}
#nav-sedebar::-webkit-scrollbar {
  width: 0;
}

.el-menu-sidebar:not(.el-menu--collapse) {
  width: 200px;
}

#nav-sidebar > ul {
  height: 100%;
}

.machines-num {
  color: #bfcbd9;
  float: right;
  margin-right: 20px;
}

span {
  margin-left: 10px;
}
</style>
