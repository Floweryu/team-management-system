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
  },

  // 删除文献
  deleteDocument(data) {
    return axios.delete(`${host.apiHost}/manage/document`, { data })
  },

  // 下载文献
  downloadDocument(param) {
    return axios.get(`${host.apiHost}/manage/document/download`, param)
  },

  // 通过关键词查找
  searchByTitle(param) {
    return axios.get(`${host.apiHost}/manage/document/searchByTitle`, param)
  },
  // 通过关键词查找
  searchByAuthor(param) {
    return axios.get(`${host.apiHost}/manage/document/searchByAuthor`, param)
  },
  // 通过关键词查找
  searchByPlace(param) {
    return axios.get(`${host.apiHost}/manage/document/searchByPlace`, param)
  },
  // 获取文献详情
  getDocumentDetail(param) {
    return axios.get(`${host.apiHost}/manage/document/detail`, param)
  }
}

export default document
