import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/login/Login.vue'
import Welcome from '../views/Welcome.vue'
import AddTrip from '../views/Trip/AddTrip.vue'
import TripList from '../views/Trip/TripList.vue'
import AllRiJi from '../views/Trip/AllRiJi.vue'
import Statistics from '../views/Trip/Statistics.vue'
import userInfo from '../views/Trip/UserInfo.vue'
import Orders from '../views/Trip/Orders.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect:'/welcome',
    children:[
      {
      path: '/welcome',
      name: 'Welcome',
      component: Welcome
      },
      {
        path:"/addTrip",
        name:"addTrip",
        component:AddTrip
      },{
        path:'/TripList',
        name:'TripList',
        component:TripList
      },{
        path:"/AllRiji",
        name:"AllRiji",
        component:AllRiJi
      },{
        path:"/statistics",
        name:"Statistics",
        component:Statistics
      },
      {
        path:"/userInfo",
        name:"UserInfo",
        component:userInfo
      },{
        path:'/orders',
        name: 'Orders',
        component:Orders
      }
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/',
    name: 'Login',
    component: Login
  },
]

const router = new VueRouter({
  routes,
  mode:'history',
})

export default router
