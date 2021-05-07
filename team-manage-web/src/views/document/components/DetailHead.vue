<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-06
 -->
<template>
  <div class="header">
    <div class="left">
      <div class="select-classify">
        <span class="tag">分类：</span>
        <el-select
          class="select"
          v-model="classifyValue"
          clearable
          @clear="clearButton"
          placeholder="请选择"
          size="mini"
          @change="classifySelectChange"
        >
          <el-option v-for="item in classifyList" :key="item.value" :label="item.name" :value="item.id"> </el-option>
        </el-select>
        <span class="tag">标签：</span>
        <el-select
          class="select"
          v-model="labelValue"
          clearable
          @clear="clearButton"
          placeholder="请选择"
          size="mini"
          @change="labelSelectChange"
        >
          <el-option v-for="item in labelList" :key="item.value" :label="item.name" :value="item.id"> </el-option>
        </el-select>
      </div>
    </div>
    <div class="center">
      <el-select class="select" v-model="searchType" placeholder="请选择" size="mini" @change="selectChange">
        <el-option v-for="item in searchTypeList" :key="item.value" :label="item.label" :value="item.value"> </el-option>
      </el-select>
      <el-input
        class="input"
        v-model="input"
        prefix-icon="el-icon-search"
        @clear="clearButton"
        size="mini"
        clearable
        autosize
        placeholder="请输入查询关键词"
      ></el-input>
      <el-button @click="searchData" type="primary" size="mini" icon="el-icon-search" circle />
    </div>
    <div class="right">
      <dt>排序：</dt>
      <dd :class="{ active: index === isSelect }" @click="sortEvent(item.value, index)" v-for="(item, index) in sortedList" :key="index">
        {{ item.label }}
      </dd>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DetailHead',
  data() {
    return {
      input: '',
      searchType: 0,
      searchTypeList: [
        { value: 0, label: '标题' },
        { value: 1, label: '作者' },
        { value: 2, label: '出版单位' }
      ],
      sortedList: [
        { value: 'likeCount', label: '按点赞数' },
        { value: 'viewCount', label: '按浏览量' },
        { value: 'averageScore', label: '按评分' },
        { value: 'downloadCount', label: '按下载量' }
      ],
      isSelect: 0,
      classifyValue: '',
      classifyList: [],
      labelValue: '',
      labelList: []
    }
  },
  created() {
    this.getAllClassify()
    this.getAllLabel()
  },
  methods: {
    // 获取类别
    getAllClassify() {
      this.$http.classify
        .getAllClassify()
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.classifyList = data
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 获取标签
    getAllLabel() {
      this.$http.label
        .getAllLabel()
        .then(res => {
          if (res.code === 0 && res.data) {
            let data = res.data
            this.labelList = data
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    searchData() {
      this.$emit('search-data', this.searchType, this.input)
    },
    selectChange() {
      this.input = ''
    },
    clearButton() {
      this.$emit('clear-button')
    },
    sortEvent(val, index) {
      this.isSelect = index
      this.$emit('sort-event', val)
    },
    classifySelectChange() {
      this.$emit('classify-select-change', this.classifyValue)
    },
    labelSelectChange() {
      this.$emit('label-select-change', this.labelValue)
    }
  }
}
</script>

<style scoped>
.header {
  height: 42px;
  font-size: 14px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.left,
.center {
  display: flex;
  align-items: center;
}

.input {
  margin-right: 10px;
  width: 200px;
}

.select {
  width: 100px;
  margin-right: 10px;
}
.right {
  display: flex;
  align-items: center;
}

.tag {
  color: #9c9898;
}

.right dt {
  margin-right: 8px;
  color: #9c9898;
}
.right dd {
  margin-right: 24px;
  cursor: pointer;
}
.active {
  color: #fc5531;
}
</style>
