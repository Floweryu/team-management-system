<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-06
 -->
<template>
  <Header>
    <el-date-picker
      slot="right"
      size="mini"
      v-model="time"
      type="date"
      @change="selectTime"
      value-format="yyyy-MM-dd"
      placeholder="选择日期"
    >
    </el-date-picker>
    <el-input
      slot="right"
      class="input"
      v-model="input"
      prefix-icon="el-icon-search"
      @clear="clearButton"
      size="mini"
      clearable
      autosize
      placeholder="请输入用户账号"
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
      input: '',
      time: ''
    }
  },
  methods: {
    selectTime() {
      this.$emit('search-time', this.time)
    },
    searchData() {
      this.$emit('search-data', this.input)
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
