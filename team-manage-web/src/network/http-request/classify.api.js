import axios from '../http-instance/index'
import host from '../http-env/index'

const classify = {
  // 获取所有类别
  getAllClassify() {
    return axios.get(`${host.apiHost}/manage/classify/all`)
  },
  getAllClassifyByUserId(params) {
    return axios.get(`${host.apiHost}/manage/classify/all/byUserId`, params)
  },
  addDocumentClassify(data) {
    return axios.post(`${host.apiHost}/manage/classify/member`, data)
  },
  // 更新
  updateClassify(data) {
    return axios.put(`${host.apiHost}/manage/classify`, data)
  },

  // 删除
  deleteClassify(data) {
    return axios.delete(`${host.apiHost}/manage/classify`, { data })
  },
  addClassify(data) {
    return axios.post(`${host.apiHost}/manage/classify`, data)
  }
}

export default classify
