import axios from '../http-instance/index'
import host from '../http-env/index'

const user = {
  //验证登录
  verifyPassword(params) {
    return axios.post(`${host.apiHost}/manage/login`, params)
  },

  // 获取所有用户
  getAllUser() {
    return axios.get(`${host.apiHost}/manage/users`)
  },

  // 根据用户账号获取用户
  getUserByUserId(params) {
    return axios.get(`${host.apiHost}/manage/getUserByUserId`, params)
  },

  // 根据用户姓名获取用户
  getUserByUserName(params) {
    return axios.get(`${host.apiHost}/manage/getUserByUserName`, params)
  },

  // 根据用户身份获取用户
  getUserByIdentity(params) {
    return axios.get(`${host.apiHost}/manage/getUserByIdentity`, params)
  },

  // 通过用户主键获取用户
  getUserById(params) {
    return axios.get(`${host.apiHost}/manage/user`, params)
  },

  // 添加用户
  addUser(data) {
    return axios.post(`${host.apiHost}/manage/user`, data)
  },

  // 更新用户
  updateUser(data) {
    return axios.put(`${host.apiHost}/manage/user`, data)
  },

  //删除对应id用户
  deleteUser(data) {
    return axios.delete(`${host.apiHost}/manage/user`, { data })
  }
}

export default user
