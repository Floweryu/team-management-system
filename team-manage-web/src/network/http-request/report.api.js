import axios from '../http-instance/index'
import host from '../http-env/index'

const report = {
  // 获取所有
  getAllReport() {
    return axios.get(`${host.apiHost}/manage/reports`)
  },
  getReportById(params) {
    return axios.get(`${host.apiHost}/manage/report/id`, params)
  },
  // 添加
  addReport(data) {
    return axios.post(`${host.apiHost}/manage/report`, data)
  },

  // 更新
  updateReport(data) {
    return axios.put(`${host.apiHost}/manage/report`, data)
  },

  // 删除
  deleteReport(data) {
    return axios.delete(`${host.apiHost}/manage/report`, { data })
  }
}

export default report
