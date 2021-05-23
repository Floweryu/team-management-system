import axios from '../http-instance/index'
import host from '../http-env/index'

const label = {
  // 获取所有标签
  getAllLabel() {
    return axios.get(`${host.apiHost}/manage/label/all`)
  },
  addDocumentLabel(data) {
    return axios.post(`${host.apiHost}/manage/label/member`, data)
  },
  // 更新
  updateLabel(data) {
    return axios.put(`${host.apiHost}/manage/label`, data)
  },
  // 删除
  deleteLabel(data) {
    return axios.delete(`${host.apiHost}/manage/label`, { data })
  },
  addLabel(data) {
    return axios.post(`${host.apiHost}/manage/label`, data)
  },
  getAllLabelByUserId(params) {
    return axios.get(`${host.apiHost}/manage/label/all/byUserId`, params)
  }
}

export default label
