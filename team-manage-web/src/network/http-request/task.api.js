import axios from '../http-instance/index'
import host from '../http-env/index'

const task = {
  // 获取所有
  getAllTask() {
    return axios.get(`${host.apiHost}/manage/tasks`)
  },

  // 添加
  addTask(data) {
    return axios.post(`${host.apiHost}/manage/task`, data)
  },
  // 添加
  addTaskMember(data) {
    return axios.post(`${host.apiHost}/manage/task/member`, data)
  },

  // 更新
  updateTask(data) {
    return axios.put(`${host.apiHost}/manage/task`, data)
  },

  // 删除
  deleteTask(data) {
    return axios.delete(`${host.apiHost}/manage/task`, { data })
  },

  getTaskById(params) {
    return axios.get(`${host.apiHost}/manage/task/id`, params)
  },

  getTaskMember(params) {
    return axios.get(`${host.apiHost}/manage/task/member`, params)
  },
  deleteTaskMember(params) {
    return axios.delete(`${host.apiHost}/manage/taskMember`, params)
  }
}

export default task
