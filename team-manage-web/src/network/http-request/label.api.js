import axios from '../http-instance/index'
import host from '../http-env/index'

const label = {
  // 获取所有标签
  getAllLabel() {
    return axios.get(`${host.apiHost}/manage/label/all`)
  }
}

export default label
