<template>
    <div>
        <!-- 顶部 nav-bar -->
        <van-nav-bar title="旅游自助平台" :fixed="true" :border="false" >
             <template #right>
          <van-icon name="search" size="18" />
             </template>
        </van-nav-bar>
        <div class="scroll">
            <!-- 轮播图 -->
            <home-swiper :banner="banner"></home-swiper>
            <van-notice-bar
         left-icon="volume-o"
           text="无论我们能活多久，我们能够享受的只有无法分割的此刻，此外别无其他。"
         />
            <!-- 推荐 -->
            <!-- <recommend :recommend="recommend"></recommend>-->
            <!-- 推荐下面的介绍 -->
            <!-- <feature-view></feature-view>  -->
            <!-- 导航吸顶 -->
            <van-sticky :offset-top="45">
                <!-- tabControl导航 -->
                <tab-control :title="['热门景点推荐']"></tab-control>
            </van-sticky>
        <!-- tabControl下面的商品 -->
        <goods-list :goods="goods"></goods-list>

        <tab-footer></tab-footer>
        </div>
        <!-- 返回顶部按钮 -->
        <!-- <ReturnTop></ReturnTop> -->
    </div>
</template>
<script>
//网络请求 js 模块
import {getHomeGoods,getHomeGoodsByUserId} from "@/network/home.js";
import { mapGetters } from 'vuex'

//home子组件
import HomeSwiper from "./childComps/HomeSwiper.vue"; //轮播图
import Recommend from "./childComps/Recommend.vue"; // 推荐
import FeatureView from "./childComps/FeatureView.vue"; //本周流行
import TabFooter from '@/components/common/TabFooter.vue'
import { swiperList } from '../../assets/data/swiper';

//公共组件
import TabControl from "@/components/content/TabControl.vue";
import GoodsList from "@/components/content/goods/GoodsList.vue";
import Scroll from "@/components/common/scroll/Scroll.vue"
export default {
    name: "Home",
    data() {
        return {
            banner: [],
            recommend: [],
            goods: [],
            currentType: "pop", //页面默认渲染当前类型
            serach:'',
            userId:''
        };
    },
    computed: {
    ...mapGetters([
      'loginIn', // 登录标识
    ])
  },
    mounted() {
     
            //获取轮播图数据
            this.banner = swiperList;
            
            this.getHomeGoods();
      

        //图片自定义事件 当图片加载后就会触发这个自定义事件
        //  this.$bus.$on("itemImgLoad",()=>{
            // console.log("a")
            // this.$refs.scrollRef.refresh()
        // })

        document.addEventListener('scroll', this.scrollMoreData, false)
        // console.log(document.querySelector(".goods_list_wrap").offsetHeight)
        
    },
    methods: {
        // 请求函数
        getHomeGoods() {
            if(!this.loginIn){
               getHomeGoods().then(res => {
                // console.log(res.data)
                  this.goods = res.data.slice(0, 10)//slice使用于数组
                // this.goods[type].list = res.data.list 不能这样写，不然永远只有三十条数据
                // this.goods[currentType].page++;
                // console.log(this.goods[currentType].list);
            })
            }else{
                this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
                   getHomeGoodsByUserId(this.userId).then(res=>{
                   this.goods = res.data.slice(0, 10)//slice使用于数组
               })
            }
        },
        //点击tabControll切换
        titleChange(index){
            switch(index){
                case 0:
                    this.currentType = 'pop';
                    break;
                case 1:
                    this.currentType = 'new';
                    break;
                case 2:
                    this.currentType = 'sell';
            }
            console.log(index)
        },

        // 自定义滚动事件
        scrollMoreData() {
            // 计算 总页数
            // this.pageTotal = Math.ceil(this.total / this.goodsParams.pagesize);
            const scrollTopHeight = document.documentElement.scrollTop;         //  获取 文档向上滚动的 距离;
            const clientHeight = document.documentElement.clientHeight;         // 获取浏览器窗口的 高度;
            const offsetHeight = document.querySelector(".scroll").offsetHeight;   // 获取滚动 内容的 高度;
             if ((scrollTopHeight + clientHeight) - 46 + 50 >= offsetHeight) {
                 // -54 是因为顶部的 搜索框占了 54px;  
                
                this.getHomeGoods(this.currentType);
                console.log('触底了'); 
          }
                // console.log(scrollTopHeight)    //4736    4736 + 812 = 5548 - 5452 = 底部  -  上部分 = 50 - 46 = 4 
                // console.log(clientHeight)       // 812
                // console.log(offsetHeight)       //5452
     },
    },
    
    components: {
        HomeSwiper,
        Recommend,
        FeatureView,
        GoodsList,
        TabControl,
        Scroll,
        TabFooter
    }
};
</script>
<style lang="less" scoped>
.van-nav-bar {
    background-color: orange;
}
.van-nav-bar__title {
    color: #fff;
}

</style>