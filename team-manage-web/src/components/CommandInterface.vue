<template>
  <el-container style="border: 1px solid #eee">
    <el-aside width="300px" style="background-color: rgb(238, 241, 246)">
      <el-card class="card-box"></el-card>
    </el-aside>
    <el-container class="right">
      <el-card class="card-box"> </el-card>
      <el-card class="card-box">
        <el-input type="textarea" v-model="textarea" :autosize="{ minRows: 4, maxRows: 8 }"></el-input>
      </el-card>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'CommandInterface',
  data() {
    return {
      textarea: ''
    }
  }
}
</script>

<style scoped>
.el-container {
  padding: 0px;
  margin: 0px;
  height: 100%;
}
.card-box {
  height: 98%;
  margin: 3px 3px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.right {
  display: flex;
  flex-direction: column;
}
</style>
