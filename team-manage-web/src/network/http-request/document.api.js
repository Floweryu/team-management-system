import axios from '../http-instance/index'
import host from '../http-env/index'

const document = {
  // 获取所有文献
  getAllDocument() {
    return axios.get(`${host.apiHost}/manage/documents`)
  },

  // 添加文献
  addDocument(data) {
    return axios.post(`${host.apiHost}/manage/document`, data)
  },

  // 更新文献
  updateDocument(data) {
    return axios.put(`${host.apiHost}/manage/document`, data)
  }
}

export default document
