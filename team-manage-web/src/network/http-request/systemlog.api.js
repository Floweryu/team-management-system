import axios from '../http-instance/index'
import host from '../http-env/index'

const systemlog = {
  // 获取所有
  getAllSystemLog() {
    return axios.get(`${host.apiHost}/manage/systemlogs`)
  },
  getSystemLogByTime(params) {
    return axios.get(`${host.apiHost}/manage/systemlogs/byTime`, params)
  },
  getSystemLogByUserId(params) {
    return axios.get(`${host.apiHost}/manage/systemlogs/byUserId`, params)
  }
}
export default systemlog
