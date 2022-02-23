<template>
  <div class="home">
   <the-header/>
     <div class="order-list">
    <ul class="order-list-header">
      <li class="active">
        我的门票
      </li>
    </ul>
    <div class="song-content">
     <router-view :ordersList="orderslist" v-if="isRouterAlive"></router-view>
       <div class="pagination" style="margin-top:25px;margin-left:70px;">
        <el-pagination
         @current-change="handleCurrentChange"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total">
        </el-pagination>
    </div>
    </div>
  </div>
   <scroll-top/>
   <the-footer/>
  </div>
</template>

<script type="text/javascript">

import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ScrollTop from '../../components/ScrollTop.vue'
import Orders from '../../components/Orders.vue'
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      orderslist:[],
      userId:'',
       pageSize:5, // 页数
      currentPage: 1, // 当前页
      total:0,
      isRouterAlive: true
    }
  },
  components: {
    TheHeader,
    ScrollTop,
    TheFooter,
    Orders

  },
  provide() {
    return {
      reload: this.reload
    };
  },
  created () {
    this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
     this.findUserOrders()
  },
  computed: {
    ...mapGetters([
      'loginIn',
    ])
  },
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(() => {
        this.isRouterAlive = true;
      });
    },
     findUserOrders(){
       let params = new URLSearchParams();
       params.append("userId", this.userId)
           params.append("pageSize",this.pageSize);
       params.append("pageNum",this.currentPage)
       this.$http.post('/order/findUserOrders',params).then(res=>{
         if(res.data.code == 200){
          this.currentPage = res.data.data.pageNum
          this.total=res.data.data.total
          this.orderslist = res.data.data.rows
          // console.log(this.orderslist)
         }
       })
     },
      handleCurrentChange(pageNum){
      this.currentPage=pageNum
      // console.log(this.currentPage)
       this.findUserOrders()
    },
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/orders.scss';
</style>
