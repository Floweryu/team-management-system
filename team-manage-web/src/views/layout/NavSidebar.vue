<template>
  <div id="navigation-container">
    <el-menu
      class="nav-sidebar"
      :default-active="onRoutes"
      text-color="#fff"
      background-color="#262626"
      :collapse="collapse"
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
      menuList: []
    }
  },
  created() {
    this.menuList = menuList
  }
}
</script>

<style scoped>
.nav-sidebar:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
