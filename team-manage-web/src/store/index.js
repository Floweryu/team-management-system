import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
import getters from './getters'

Vue.use(Vuex)

const state = {
  token: localStorage.getItem('token') ? localStorage.getItem('token') : '',
  userId: localStorage.getItem('userId') ? localStorage.getItem('userId') : '',
  username: localStorage.getItem('username') ? localStorage.getItem('username') : '',
  role: localStorage.getItem('role') ? localStorage.getItem('role') : ''
}

export default new Vuex.Store({
  state,
  mutations,
  getters
})
