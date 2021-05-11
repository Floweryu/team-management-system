import axios from '../http-instance/index'
import host from '../http-env/index'

const comment = {
  addComment(data) {
    return axios.post(`${host.apiHost}/manage/comment`, data)
  },
  getCommentByTopicId(params) {
    return axios.get(`${host.apiHost}/manage/comment/byTopicId`, params)
  },
  deleteComment(params) {
    return axios.delete(`${host.apiHost}/manage/comment`, params)
  }
}

export default comment
