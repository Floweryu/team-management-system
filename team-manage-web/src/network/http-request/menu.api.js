import axios from '../http-instance/index'
import host from '../http-env/index'

const menu = {
  // 获取菜单
  getMenus() {
    return axios.get(`${host.apiHost}/manage/menus`)
  }
}

export default menu
