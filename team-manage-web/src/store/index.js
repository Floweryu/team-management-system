import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'

Vue.use(Vuex)

const state = {
  token: localStorage.getItem('token') ? localStorage.getItem('token') : ''
}

export default new Vuex.Store({
  state,
  mutations
})
