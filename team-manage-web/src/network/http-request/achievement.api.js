import axios from '../http-instance/index'
import host from '../http-env/index'

const achievement = {
  getAllAchievement() {
    return axios.get(`${host.apiHost}/manage/achievements`)
  },

  addAchievement(data) {
    return axios.post(`${host.apiHost}/manage/achievement`, data)
  },

  updateAchievement(data) {
    return axios.put(`${host.apiHost}/manage/achievement`, data)
  },

  deleteAchievement(data) {
    return axios.delete(`${host.apiHost}/manage/achievement`, { data })
  },

  getAchievementByUser(params) {
    return axios.get(`${host.apiHost}/manage/achievement/byUser`, params)
  },

  getAchievementByUploadUser(params) {
    return axios.get(`${host.apiHost}/manage/achievement/byUploadUser`, params)
  }
}

export default achievement
