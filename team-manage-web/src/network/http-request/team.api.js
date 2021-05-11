import axios from '../http-instance/index'
import host from '../http-env/index'

const team = {
  // 获取所有
  getAllTeam() {
    return axios.get(`${host.apiHost}/manage/teams`)
  },

  // 添加
  addTeam(data) {
    return axios.post(`${host.apiHost}/manage/team`, data)
  },

  // 更新
  updateTeam(data) {
    return axios.put(`${host.apiHost}/manage/team`, data)
  },

  // 删除
  deleteTeam(data) {
    return axios.delete(`${host.apiHost}/manage/team`, { data })
  },

  getTeamByName(params) {
    return axios.get(`${host.apiHost}/manage/team/byName`, params)
  },
  getTeamById(params) {
    return axios.get(`${host.apiHost}/manage/team/byTeamId`, params)
  }
}

export default team
