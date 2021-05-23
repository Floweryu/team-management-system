<template>
  <el-dialog :visible="dialogVisible" :before-close="cancel">
    <el-card shadow="hover">
      <el-form ref="classify" label-width="100px" label-position="right">
        <el-row type="flex" align="middle" justify="space-around" class="row-bg">
          <el-col :span="16">
            <el-form-item prop="classifyData" label="类别" size="mini">
              <el-select v-model="classifyData" multiple size="mini" placeholder="请选择类别">
                <el-option v-for="(item, index) in classifyList" :key="index" :label="item.name" :value="item.id"> </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item>
              <el-button type="primary" size="mini" @click="submitClassify">添加类别</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-form ref="label" label-width="100px" label-position="right">
        <el-row type="flex" align="middle" justify="space-around" class="row-bg">
          <el-col :span="16">
            <el-form-item prop="labelData" label="标签" size="mini">
              <el-select v-model="labelData" multiple size="mini" placeholder="请选择标签">
                <el-option v-for="(item, index) in labelList" :key="index" :label="item.name" :value="item.id"> </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item>
              <el-button type="primary" size="mini" @click="submitLabel">添加标签</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </el-dialog>
</template>

<script>
export default {
  name: 'ClassifyLabelDialog',
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    documentId: Number
  },
  data() {
    return {
      classifyData: [],
      classifyList: [],
      labelData: [],
      labelList: []
    }
  },
  created() {
    this.getAllClassify()
    this.getAllLabel()
  },
  methods: {
    submitClassify() {
      if (this.checkClassify()) {
        let formData = []
        this.classifyData.forEach(item => {
          let member = {}
          member.classifyId = item
          member.documentId = this.documentId
          formData.push(member)
        })
        this.$http.classify
          .addDocumentClassify(JSON.stringify(formData))
          .then(res => {
            if (res.code === 0) {
              this.$notify.success({
                message: '添加类别成功'
              })
              this.$emit('dialog-cancel')
            }
          })
          .catch(() => {
            this.$notify.error({
              message: '添加类别失败'
            })
            this.$emit('dialog-cancel')
          })
      }
    },
    checkClassify() {
      if (this.classifyData.length < 0 || this.classifyData.length > 3) {
        alert('类别至少选择一个，不能超过三个')
        return false
      }
      return true
    },
    checkLabel() {
      if (this.labelData.length < 0 || this.labelData.length > 3) {
        alert('标签至少选择一个，不能超过三个')
        return false
      }
      return true
    },
    submitLabel() {
      if (this.checkLabel()) {
        let formData = []
        this.labelData.forEach(item => {
          let member = {}
          member.labelId = item
          member.documentId = this.documentId
          formData.push(member)
        })
        this.$http.label
          .addDocumentLabel(JSON.stringify(formData))
          .then(res => {
            if (res.code === 0) {
              this.$notify.success({
                message: '添加标签成功'
              })
              this.$emit('dialog-cancel')
            }
          })
          .catch(() => {
            this.$notify.error({
              message: '添加标签失败'
            })
            this.$emit('dialog-cancel')
          })
      }
    },
    getAllClassify() {
      this.$http.classify
        .getAllClassify()
        .then(res => {
          if (res.code === 0 && res.data) {
            this.classifyList = res.data
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    getAllLabel() {
      this.$http.label
        .getAllLabel()
        .then(res => {
          if (res.code === 0 && res.data) {
            this.labelList = res.data
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 关闭弹窗
    cancel() {
      this.$emit('dialog-cancel')
    }
  }
}
</script>

<style scoped>
.el-select {
  width: 240px;
}
.row-bg {
  padding: 8px 8px;
  background-color: #fbfbfb85;
  border-radius: 4px;
}
</style>
