<template>
  <div id="index">
    <nav-headbar />
    <nav-sidebar />
    <div class="content-box" :class="{ 'content-collapse': collapse }">
      <div class="content-loading">
        <transition name="move" mode="out-in">
          <router-view :key="$route.fullPath" />
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import NavSidebar from '@/views/layout/NavSideBar/index'
import NavHeadbar from '@/views/layout/NavHeadBar/index'

export default {
  name: 'index',
  components: {
    NavHeadbar,
    NavSidebar
  },
  data() {
    return {
      collapse: false
    }
  },
  created() {
    // 接收子组件 home-header 的 collapse
    this.$bus.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>

<style scoped>
.content-box {
  position: absolute;
  left: 200px;
  right: 0;
  top: 52px;
  bottom: 0;
  padding-bottom: 30px;
  -webkit-transition: left 0.3s ease-in-out;
  transition: left 0.3s ease-in-out;
  background: #f0f0f0;
}
.content-collapse {
  left: 65px;
}
.content-loading {
  position: relative;
  width: auto;
  height: 100%;
  /* padding: 0 5px 10px; */
  overflow-y: scroll;
  box-sizing: border-box;
}
</style>
