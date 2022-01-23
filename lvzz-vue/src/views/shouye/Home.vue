<template>
  <div class="home">
    <the-header/>
    <swiper/>
    <div class="section" v-for="(item, index) in tripsList" :key="index">
      <div class="section-title">{{item.name}}</div>
      <content-list :contentList="item.list"></content-list>
    </div>
    <scroll-top/>
    <the-footer/>
  </div>
</template>


<script type="text/javascript">

import Swiper from "../../components/Swiper.vue"
import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ContentList from '../../components/ContentList'
import ScrollTop from '../../components/ScrollTop.vue'


export default {
  data() {
    return {
      tripsList:[
        {name:'热门景点推荐',list:[]}
      ]
    }
  },
  components: {
    Swiper,
    TheHeader,
    TheFooter,
    ContentList,
    ScrollTop
  },
  created () {
  this.getTripList()
  },
  methods: {
     getTripList(){
       this.$http.get('/trip/queryTripList').then(res=>{
          this.tripsList[0].list = res.data.data.slice(0, 5)

       }).catch(err=>{
         console.log(err)
       })
     }
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/home.scss';
</style>
