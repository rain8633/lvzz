<template>
    <div class="detail">
        <van-notify />
        <!-- 导航栏 -->
        <van-nav-bar class="detail_nav" title="标题" left-arrow  @click-left="onClickLeft" fixed>
            <div slot="title" class="title">
                <span class="active">景点详情</span>
            </div>
        </van-nav-bar>
        <!-- 轮播图组件 -->
        <div class="timg">
       <img :src="path+trip.picImg" alt="">
       </div>
       <div class="album-content">
         <div class="album-title">
           <p>景点名称:&nbsp;&nbsp;{{trip.tripName}}</p>
         </div>
         <div class="album-address">
           <p>地址:&nbsp;&nbsp;{{trip.address}}</p>
           <p>景区价格:&nbsp;&nbsp;{{trip.price}}元/人</p>
           <p>景区级别:&nbsp;&nbsp;{{trip.levelName}}</p>
           <p>联系电话:&nbsp;&nbsp;{{trip.phone}}</p>
         </div>
      </div>
       <div class="album-info">
        <p style="font-size:15px;">
          简介：{{trip.info}}
        </p>
      </div>
      <div class="album-score">
        <div>
          <span style="margin-left:30px;">景区评分:</span>
            <van-rate v-model="value5" disabled allow-half />
           <span >&nbsp;&nbsp;{{value5 * 2}}</span>
        </div>
        <div style="margin-top:-20px;">
          <span style="margin-left:30px;">我的评价：</span>
          <div style="margin-left:30px;margin-top:-30px;" @click="pushValue()">
            <van-rate  v-model="value3" allow-half />
          </div>
        </div>
      </div>
      <div class="commend">
            <!-- 商品评论信息 -->
        <detail-comment-info ref="commentRef" :tripId="tripListId"></detail-comment-info>
      </div> 
        <!-- 底部商品导航 -->
        <detail-footer-bar class="footer_bar" :shoucang="trip.shoucang" :id="trip.id" @buy="buy"></detail-footer-bar>
         <!-- <div class="detail_footer_bar">
        <van-goods-action>
            <van-goods-action-icon icon="star-o" v-if="!trip.shoucang" text="收藏" @click="addshoucang(trip.id)"/>
            <van-goods-action-icon icon="star" color="red" v-if="trip.shoucang"  text="已收藏"/>
            <van-goods-action-button type="danger" @click="buy()" text="前往购票" />
        </van-goods-action>
    </div> -->
    </div>
</template>
<script>
//js网络请求模块
import {getRecommend,getRank,AddRank} from '@/network/detail.js'
import {addShoucang} from '../../network/shoucang.js'
import { mapGetters } from 'vuex'

import Scroll from '@/components/common/scroll/Scroll.vue' //引入滚动组件
import DetailFooterBar from "./childComps/DetailFooterBar.vue"  //底部商品导航
import DetailCommentInfo from './childComps/DetailCommentInfo.vue' //商品的评论信息

export default {
    name: "Detail",
    data() {
        return {
            contIndex:0,
            trip: {},
             tripListId: '', // 景点ID
            id:null,
             value3: 0,
             value5:0,
             commentInfo:[], //商品评论信息
        };
    },
     computed: {
    ...mapGetters([
      'loginIn', // 登录标识
      'tempList', // 单个景点信息
    ])
  },
    components:{
        Scroll,
        DetailFooterBar,
        DetailCommentInfo
    },
    created() {
        //获取iid
        this.id = this.$route.params.id;
         this.tripListId = this.tempList.id // 给景点ID赋值
        this.trip = this.tempList
        this.getRank(this.tripListId)
       
        console.log(this.trip)
      
    },
    methods: {
        //返回到home页面
        onClickLeft() {
            this.$router.go(-1)
        },

     buy(){
        if(this.loginIn){
        this.$router.push({path: '/buy', query: {trip: this.trip}})
        }else{
          //  this.$toast.fail("您还未登录")
          // Notify({ type: 'warning', message: '您还未登录' });
           this.$notify('您还未登录','warning');
        }
     },
    pushValue(){
      if(this.loginIn){
      let userId=JSON.parse(window.sessionStorage.getItem("user")).id;
      AddRank(userId,this.tripListId,this.value3 * 2).then(res => {
         if(res.code==200){
          
          this.$toast.success("评分成功")
            this.getRank(this.tripListId)
         }
         else{
         this.$toast.fail("评分失败")
         }
      }).catch(err => {
            console.log(err)
          })
      }else{
        this.value3 =null;
        
         this.$toast.fail("请先登录")
        
      }
    },
    getRank(id){
     getRank(id).then(res => {
         if(res.code == 200){
           this.value5 =res.data / 2
         }
      }).catch(err => {
        console.log(err)
      })
    },
    addshoucang(id){
       if(this.loginIn){
      let userId=JSON.parse(window.sessionStorage.getItem("user")).id;
       this.$http.post('/shouCang/addShouCang/'+userId+'/'+id).then(res => {
         if(res.data.code == 200) {
              this.$toast.success("收藏成功!")
              this.trip.shoucang=true
         }else{
            this.$toast.fail("收藏失败!")
         }
       })
    }else{
      this.$toast.fail("请先登录!")
    }
  },
  },
};
</script>
<style lang="less" scoped>
     span,p {
         font-size:20px;
         margin:15px;
     }
     .timg{
        width: 100vw;
        height: 6rem;
        img{
            margin-left: 2vw;
            width: 95vw;
             height: 5rem;
        }
    }
    .album-info{
        margin-top: 10px;
        margin-left:15px;
        margin-right: 10px;
    }
    .album-content{

        margin-top: -30px;
        margin-left:15px;
        margin-right: 10px;
    }
    .detail{
        padding-bottom: 1rem;
    }
    .van-icon::before{
        color: gray;
    }
    .title{
        display: flex;
        justify-content: space-around;
        color: gray;
        font-size: 0.28rem;
    }
    .active{
        color: #EF232F;
    }
    .footer_bar{
        position: relative;
        z-index: 999;
    }
      .van-goods-action{
        border-top: 1px solid #FBFBFB;
    }
</style>