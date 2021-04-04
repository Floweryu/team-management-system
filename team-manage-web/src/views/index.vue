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
import NavSidebar from '@/views/layout/NavSidebar'
import NavHeadbar from '@/views/layout/NavHeadbar'

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

<style scoped></style>
