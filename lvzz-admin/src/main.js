import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/axios'
import VueParticles from 'vue-particles'
import element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import * as echarts from 'echarts'

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
