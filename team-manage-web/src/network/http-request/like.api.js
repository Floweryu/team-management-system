import axios from '../http-instance/index'
import host from '../http-env/index'

const like = {
  // 添加点赞
  addLike(data) {
    return axios.post(`${host.apiHost}/manage/like/add`, data)
  },
  // 添加点赞
  deleteLike(data) {
    return axios.delete(`${host.apiHost}/manage/like/delete`, { data })
  }
}

export default like
