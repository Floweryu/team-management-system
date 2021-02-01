<template>
  <div id="index">
    <nav-sidebar />
    <div class="content-box">
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

export default {
  name: 'index',
  components: {
    NavSidebar
  },
  data() {
    return {}
  }
}
</script>

<style scoped></style>
