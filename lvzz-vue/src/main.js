import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import './plugins/axios'
import VueParticles from 'vue-particles'
import element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import * as echarts from 'echarts'

//引用
import 'summernote'
import 'summernote/dist/summernote.css'
import 'summernote/lang/summernote-zh-CN.js'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'
import 'popper.js'



Vue.prototype.$echarts = echarts
Vue.use(VueParticles)

Vue.config.productionTip = false
Vue.use(element)
import './assets/css/et.css'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
