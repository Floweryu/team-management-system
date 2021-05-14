import axios from '../http-instance/index'
import host from '../http-env/index'

const meeting = {
  // 获取所有
  getAllMeeting() {
    return axios.get(`${host.apiHost}/manage/meetings`)
  },

  // 添加
  addMeeting(data) {
    return axios.post(`${host.apiHost}/manage/meeting`, data)
  },

  // 更新
  updateMeeting(data) {
    return axios.put(`${host.apiHost}/manage/meeting`, data)
  },

  // 删除
  deleteMeeting(data) {
    return axios.delete(`${host.apiHost}/manage/meeting`, { data })
  }
}

export default meeting
