import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/shouye/Home.vue'
import Login from '../views/login/Login.vue'
import TripList from '../views/shouye/TripList.vue'
import TripDetail from '../views/shouye/TripDetail.vue'
import Route from '../views/shouye/Route2.vue'
import Setting from '../views/shouye/Setting.vue'
import Riji from '../views/shouye/Riji.vue'
import AllRiJi from '../views/shouye/AllRiJi.vue'
import AddRiJi from '../views/shouye/AddRiJi.vue'
import RiJiDetail from '../views/shouye/RiJiDetail.vue'
import ShouCang from '../views/shouye/ShouCang.vue'
import SearchTrips from '../views/shouye/SearchTrips.vue'
import Buy from '../views/shouye/Buy.vue'
import MyOrder from '../views/shouye/MyOrder.vue'
import Orders from '../components/Orders.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/',
    name: 'Login',
    component: Login
  },{
    path:'/trip-list',
    name:'TripList',
    component: TripList
  },
  {
      path: '/trip-detail/:id',
      name: 'trip-detail',
      component: TripDetail
    },
    {
      path:'/route',
      name:'Route',
      component:Route
    },{
      path:'/setting',
      name:'Setting',
      component: Setting
    },{
      path:'/my-trip',
      name:'Riji',
      component: Riji,
       children:[
      {
      path: '/AllRiji',
      name: 'AllRiji',
      component: AllRiJi
      },
      {
      path: '/AddRiji',
      name: 'AddRiji',
      component: AddRiJi
      },
    ]
    },
    {
        path:'/rijiDetail',
        name: 'RijiDetail',
        component: RiJiDetail
    },
    {
      path:'/shoucang',
      name:'Shoucang',
      component: ShouCang
    },
    {
      path:"/search",
      name:'Search',
    component: SearchTrips
    },
    {
      path:"/buy",
      name:'Buy',
      component: Buy
    },
    {
      path:'/myorder',
      name:'MyOrder',
      component:MyOrder,
      redirect:'/order',
       children:[
        {path:'/order',
        name:'Order',
       component:Orders,
       }
      ]
    }
]

const router = new VueRouter({
  routes,
  mode:'history'
})

const originalPush = VueRouter.prototype.push
   VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}

export default router
