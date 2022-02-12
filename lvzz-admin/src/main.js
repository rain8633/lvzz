import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/axios'
import VueParticles from 'vue-particles'
import element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import * as echarts from 'echarts'

import 'summernote'
import 'summernote/dist/summernote.css'
import 'summernote/lang/summernote-zh-CN.js'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'
import 'popper.js'

Vue.prototype.$echarts = echarts
Vue.use(VueParticles)
Vue.use(element)
import './assets/css/et.css'
axios.defaults.withCredentials = true


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
