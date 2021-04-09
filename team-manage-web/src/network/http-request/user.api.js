import axios from '../http-instance/index'
import host from '../http-env/index'

const user = {
  //验证登录
  verifyPassword(params) {
    return axios.post(`${host.apiHost}/admin/login`, params)
  },

  // 获取所有用户
  getAllUser() {
    return axios.get(`${host.apiHost}/manage/users`)
  }
}

export default user
