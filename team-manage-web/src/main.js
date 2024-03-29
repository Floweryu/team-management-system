import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'

import '@/icons'
import http from '@/network/http-request/index'
import bus from '@/utils/bus'

Vue.config.productionTip = false

// 全局挂载axios和bus
Vue.prototype.$http = http
Vue.prototype.$bus = bus
Vue.prototype.$axios = axios

Vue.use(ElementUI)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
