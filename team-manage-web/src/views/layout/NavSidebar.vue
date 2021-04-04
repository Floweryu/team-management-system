<template>
  <div id="nav-sidebar">
    <el-menu
      class="el-menu-sidebar"
      :default-active="onRoutes"
      :collapse="collapse"
      background-color="#FFFFFF"
      text-color="#8396A5"
      active-text-color="#409EFF"
      unique-opened
      router
    >
      <template v-for="item in menuList">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-menu-item :index="subItem.index" :key="subItem.index">
                <i :class="subItem.icon"></i>
                <span slot="title">{{ subItem.title }}</span>
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
const menuList = [
  {
    index: 'document',
    icon: 'el-icon-s-marketing',
    title: '文献管理',
    subs: [
      {
        index: 'document',
        icon: 'el-icon-s-marketing',
        title: '文献列表'
      }
    ]
  }
]
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
    this.menuList = menuList
    // 接收 home-header 发送来的 collapse
    this.$bus.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>

<style scoped>
#nav-sidebar {
  position: absolute;
  display: block;

  left: 0;
  top: 50px;
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
</style>
