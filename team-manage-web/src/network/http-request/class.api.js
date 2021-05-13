import axios from '../http-instance/index'
import host from '../http-env/index'

const clas = {
  getAllClass(params) {
    return axios.get(`${host.apiHost}/manage/class`, params)
  },
  addClass(data) {
    return axios.post(`${host.apiHost}/manage/class`, data)
  },
  deleteClass(data) {
    return axios.delete(`${host.apiHost}/manage/class`, { data })
  },
  updateClass(data) {
    return axios.put(`${host.apiHost}/manage/class`, data)
  }
}

export default clas
