import axios from '../http-instance/index'
import host from '../http-env/index'

const assets = {
  getAllAssets() {
    return axios.get(`${host.apiHost}/manage/assets`)
  },

  addAssets(data) {
    return axios.post(`${host.apiHost}/manage/assets`, data)
  },

  updateAssets(data) {
    return axios.put(`${host.apiHost}/manage/assets`, data)
  },

  deleteAssets(data) {
    return axios.delete(`${host.apiHost}/manage/assets`, { data })
  },

  getAssetsByUser(params) {
    return axios.get(`${host.apiHost}/manage/assets/byUser`, params)
  },

  getAssetsByUploadUser(params) {
    return axios.get(`${host.apiHost}/manage/assets/byUploadUser`, params)
  }
}

export default assets
