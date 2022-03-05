import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
    {
        path:'/',
        redirect:'/home'
    },
    {
        name:'Home',
        path:'/home',
        component: ()=> import('@/views/home/Home.vue')
    },
    {
        name:'Category',
        path:'/category',
        component: ()=> import('@/views/category/Category.vue')
    },
    {
        name:'Cart',
        path:'/cart',
        component: ()=>import('@/views/cart/Cart.vue')
    },
    {
        name:'Profile',
        path:'/Profile',
        component: ()=>import('@/views/profile/Profile.vue')
    },
    {
        name:'MyShouCang',
        path:'/myshoucang',
        component: ()=>import('@/views/profile/MyShouCang.vue')
    },
     {
        name:'MyOrders',
        path:'/myorders',
        component: ()=>import('@/views/profile/MyOrders.vue')
    },
    {
        name:'Login',
        path:'/login',
        component: ()=>import('@/views/Login/login.vue')
    },
      {
        name:'RiJi',
        path:'/riji',
        component: ()=>import('@/views/riji/riji.vue')
    },
     {
        name:'buy',
        path:'/buy',
        component: ()=>import('@/views/buy/buy.vue')
    },
    {
        name:'MyRiJi',
        path:'/myriji',
        component: ()=>import('@/views/riji/childComps/MyRiJi.vue')
    },
    {
        name:'Detail',
        path:'/Detail/:id',
        component: ()=>import('@/views/detail/Detail.vue')
    }
]

const router = new VueRouter({
//   mode: 'history',  // 路由模式*/
   mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
