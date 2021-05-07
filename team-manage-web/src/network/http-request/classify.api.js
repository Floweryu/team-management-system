import axios from '../http-instance/index'
import host from '../http-env/index'

const classify = {
  // 获取所有类别
  getAllClassify() {
    return axios.get(`${host.apiHost}/manage/classify/all`)
  }
}

export default classify
