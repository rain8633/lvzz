import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/shouye/Home.vue'
import Login from '../views/login/Login.vue'
import TripList from '../views/shouye/TripList.vue'
import TripDetail from '../views/shouye/TripDetail.vue'
import Route from '../views/shouye/Route.vue'
import Setting from '../views/shouye/Setting.vue'

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
    }
]

const router = new VueRouter({
  routes,
  mode:'history'
})

export default router
