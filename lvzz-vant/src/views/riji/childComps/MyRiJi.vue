<template>
  <div>
    <div>
        <van-nav-bar left-arrow  @click-left="onClickLeft" fixed title="我的日记"/>
    </div>
  <div v-if="loginIn" style="margin-top:50px;margin-bottom:40px;" v-for="(item,index) in rijiList" :key="index">
    <van-card
  :desc="item.title"
  title="日记标题"
  :thumb="item.pic"
  >
  <!-- <template #tag>
    <van-tag plain type="danger">标签</van-tag>
    <van-tag plain type="danger">标签</van-tag>
  </template> -->
   <template #price>
    日记内容:{{item.content}}
  </template>
  <template #footer>
    <van-rate  v-model="item.feelScore" allow-half disabled/>
  </template>
</van-card>
</div>
<div style="margin-top:50px;" v-if="!loginIn">
     <van-empty description="您还未登录">
  <van-button round type="danger" @click="tologin()" class="bottom-button">前往登录</van-button>
</van-empty>
</div>
    <tab-footer></tab-footer>
  </div>
</template>
<script>
import TabFooter from '@/components/common/TabFooter.vue'
import {queryAllRiJi} from '../../../network/riji.js'
import Scroll from "@/components/common/scroll/Scroll.vue"
import { mapGetters } from 'vuex'
export default {
    
    name: "myriji",
    data() {
        return {
            userId:'',
            rijiList:[],
            value3:0
        };
    },
       computed: {
    ...mapGetters([
      'loginIn', // 登录标识
    ])
  },
    components: {
        TabFooter,
        Scroll
    },
    created(){
        
     this.queryAllRiJi();
    },
    methods: {
            //返回到home页面
        onClickLeft() {
            this.$router.go(-1)
        },
        queryAllRiJi(){
          if(this.loginIn){
          this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
          queryAllRiJi(this.userId).then(res=>{
                this.rijiList = res.data
                 for(let item in this.rijiList){
              this.rijiList[item].feelScore =  this.rijiList[item].feelScore / 2
              //  item.feelScore = item.feelScore / 2
            }
                console.log(this.rijiList)
          })
          }
        },
        tologin(){
          this.$router.push('/login')
        }
    }
};
</script>

<style lang="less" scoped>
    .van-nav-bar{
        background-color: skyblue;
    }
    .van-nav-bar__title{
        color: white;
    }
     .bottom-button {
    width: 160px;
    height: 40px;
  }
</style>