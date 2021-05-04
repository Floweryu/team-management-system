import axios from '../http-instance/index'
import host from '../http-env/index'

const score = {
  // 添加分数
  addScore(data) {
    return axios.post(`${host.apiHost}/manage/score/add`, data)
  }
}

export default score
