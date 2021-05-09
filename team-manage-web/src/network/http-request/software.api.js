import axios from '../http-instance/index'
import host from '../http-env/index'

const software = {
  // 获取所有
  getAllSoftware() {
    return axios.get(`${host.apiHost}/manage/softwares`)
  },

  // 添加
  addSoftware(data) {
    return axios.post(`${host.apiHost}/manage/software`, data)
  },

  // 更新
  updateSoftware(data) {
    return axios.put(`${host.apiHost}/manage/software`, data)
  },

  // 删除
  deleteSoftware(data) {
    return axios.delete(`${host.apiHost}/manage/software`, { data })
  },

  searchByName(param) {
    return axios.get(`${host.apiHost}/manage/software/searchByName`, param)
  }
}

export default software
