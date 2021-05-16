import axios from '../http-instance/index'
import host from '../http-env/index'

const role = {
  // 获取所有
  getAllRole() {
    return axios.get(`${host.apiHost}/manage/roles`)
  },
  getUserRole() {
    return axios.get(`${host.apiHost}/manage/user/role`)
  },
  getUserRoleByUserId(params) {
    return axios.get(`${host.apiHost}/manage/user/role/byuserId`, params)
  },
  addUserRole(data) {
    return axios.post(`${host.apiHost}/manage/role/user`, data)
  },

  updateUserRole(data) {
    return axios.put(`${host.apiHost}/manage/role/user`, data)
  },
  // 删除
  deleteUserRole(data) {
    return axios.delete(`${host.apiHost}/manage/role/user`, { data })
  }
}

export default role
