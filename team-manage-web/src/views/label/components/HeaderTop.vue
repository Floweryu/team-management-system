<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-06
 -->
<template>
  <Header>
    <el-button slot="left" size="mini" type="primary" @click="addLabel" round icon="el-icon-plus">添加类别</el-button>
    <el-button slot="left" size="mini" type="danger" @click="multipleDelete" round icon="el-icon-delete">批量删除</el-button>
  </Header>
</template>

<script>
import Header from '@/components/Header/index'
export default {
  name: 'HeaderTop',
  components: {
    Header
  },
  methods: {
    addLabel() {
      this.$emit('add-label', true)
    },
    multipleDelete() {
      this.$emit('multiple-delete', true)
    }
  }
}
</script>

<style scoped>
.input {
  margin: 0 10px;
  width: 200px;
}
.select {
  width: 100px;
}

.select-identity {
  margin: 0 10px;
  width: 120px;
}

.search-button {
  margin: 0 10px;
}
</style>
