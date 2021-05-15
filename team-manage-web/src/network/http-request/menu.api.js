import axios from '../http-instance/index'
import host from '../http-env/index'

const menu = {
  // 获取菜单
  getMenus(params) {
    return axios.get(`${host.apiHost}/manage/menus`, params)
  }
}

export default menu
