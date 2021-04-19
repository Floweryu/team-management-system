<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-06
 -->
<template>
  <Header>
    <el-button slot="left" size="mini" type="primary" @click="addDocument" round icon="el-icon-plus">添加文献</el-button>
    <el-button slot="left" size="mini" type="danger" @click="multipleDelete" round icon="el-icon-delete">批量删除</el-button>
    <el-select slot="right" class="select" v-model="searchType" placeholder="请选择" size="mini" @change="selectChange">
      <el-option v-for="item in searchTypeList" :key="item.value" :label="item.label" :value="item.value"> </el-option>
    </el-select>
    <el-select v-if="searchType === 2" slot="right" class="select-identity" size="mini" v-model="input" placeholder="请选择身份">
      <el-option v-for="item in identityOptions" :key="item.value" :label="item.label" :value="item.value" />
    </el-select>
    <el-input
      v-else
      slot="right"
      class="input"
      v-model="input"
      prefix-icon="el-icon-search"
      @clear="searchData"
      size="mini"
      clearable
      autosize
      placeholder="请输入查询关键词"
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
      searchType: 0,
      searchTypeList: [
        { value: 0, label: '账号' },
        { value: 1, label: '姓名' },
        { value: 2, label: '身份' }
      ],
      identityOptions: [
        { value: 0, label: '教师' },
        { value: 1, label: '大一' },
        { value: 2, label: '大二' },
        { value: 3, label: '大三' },
        { value: 4, label: '大四' },
        { value: 5, label: '研一' },
        { value: 6, label: '研二' },
        { value: 7, label: '研三' }
      ]
    }
  },
  methods: {
    addDocument() {
      this.$emit('add-document', true)
    },
    searchData() {
      this.$emit('search-data', this.searchType, this.input)
    },
    multipleDelete() {
      this.$emit('multiple-delete', true)
    },
    selectChange() {
      this.input = ''
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
  width: 74px;
}

.select-identity {
  margin: 0 10px;
  width: 120px;
}

.search-button {
  margin: 0 10px;
}
</style>
