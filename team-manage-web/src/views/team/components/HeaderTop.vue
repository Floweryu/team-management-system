<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-06
 -->
<template>
  <Header>
    <el-button slot="left" size="mini" type="primary" @click="addTeam" round icon="el-icon-plus">添加团队</el-button>
    <el-button slot="left" size="mini" type="danger" @click="multipleDelete" round icon="el-icon-delete">批量删除</el-button>
    <el-input
      slot="right"
      class="input"
      v-model="input"
      prefix-icon="el-icon-search"
      @clear="clearButton"
      size="mini"
      clearable
      autosize
      placeholder="请输入团队名"
    ></el-input>
    <el-button slot="right" class="search-button" @click="searchData" type="primary" size="mini" icon="el-icon-search" round>
      搜索
    </el-button>
  </Header>
</template>

<script>
import Header from '@/components/Header/index'
export default {
  name: 'HeaderTop',
  components: {
    Header
  },
  data() {
    return {
      input: ''
    }
  },
  methods: {
    addTeam() {
      this.$emit('add-team', true)
    },
    searchData() {
      this.$emit('search-data', this.input)
    },
    multipleDelete() {
      this.$emit('multiple-delete', true)
    },
    clearButton() {
      this.$emit('clear-button')
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
