<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-03
 -->
<template>
  <div id="nav-header">
    <div class="nav-header-left">
      <!-- 折叠按钮 -->
      <div class="collapse-btn" @click="collapseChange">
        <svg-icon v-if="!collapse" iconClass="collapse_left"></svg-icon>
        <svg-icon v-else iconClass="collapse_right"></svg-icon>
      </div>
      <div class="logo">
        <img src="~assets/image/logo.png" />
      </div>
    </div>

    <div class="nav-header-center"></div>

    <div class="nav-header-right">
      <div class="user-avator">
        <img :src="$store.state.picPath" />
      </div>
      <el-dropdown class="user-name" trigger="click" @command="handleCommand">
        <span class="el-dropdown-link">
          {{ username }}
          <i class="el-icon-caret-bottom"></i>
        </span>

        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="logout">
            退出登录
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <div class="fullscreen-btn" @click="handleFullScreen">
        <el-tooltip :content="fullscreen ? '取消全屏' : '全屏'" placement="bottom">
          <i class="el-icon-rank"></i>
        </el-tooltip>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NavHeadbar',
  data() {
    return {
      collapse: false,
      fullscreen: false,
      username: localStorage.getItem('username')
    }
  },
  created() {},
  methods: {
    // 发送 collpse 到 兄弟组件home-sidebar 和父组件 home
    collapseChange() {
      this.collapse = !this.collapse
      this.$bus.$emit('collapse', this.collapse)
    },
    handleFullScreen() {
      //全屏功能
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen()
        } else if (document.webkitCancelFullScreen) {
          //safari, chrome
          document.webkitCancelFullScreen()
        } else if (document.mozCancelFullScreen) {
          //firefox
          document.mozCancelFullScreen()
        } else if (document.msExitFullScreen) {
          //ie
          document.msExitFullScreen()
        }
      } else {
        let element = document.documentElement
        if (element.requestFullscreen) {
          element.requestFullscreen()
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen()
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen()
        } else if (element.msRequestFullScreen) {
          element.msRequestFullScreen()
        }
      }
      this.fullscreen = !this.fullscreen
    },
    handleCommand(command) {
      if (command == 'logout') {
        localStorage.removeItem('username')
        localStorage.removeItem('userId')
        //退出登录，清空token
        localStorage.removeItem('token')
        localStorage.removeItem('role')
        localStorage.removeItem('roleName')
        this.$router.push('/login')
        window.location.reload()
      }
    }
  }
}
</script>

<style scoped>
#nav-header {
  box-sizing: border-box;
  height: 50px;
  font-size: 22px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-shadow: rgb(52 56 85 / 25%) 0px 2px 3px 0px;
}

.nav-header-left {
  display: flex;
  align-items: center;
}
.collapse-btn {
  float: left;
  line-height: 50px;
  padding: 0 20px;
  cursor: pointer;
}
.logo img {
  float: left;
  height: 36px;
  margin-left: -5px;
}

.nav-header-right {
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.user-avator img {
  display: block;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  margin-right: 5px;
}
.user-name {
  margin-left: 10px;
  margin-right: 20px;
}
.el-dropdown-link {
  color: #73aeea;
  cursor: pointer;
}
.fullscreen-btn {
  margin-right: 5px;
  font-size: 24px;
  transform: rotate(45deg);
}
</style>
