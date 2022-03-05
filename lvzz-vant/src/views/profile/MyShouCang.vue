<template>
  <div>
    <div>
        <van-nav-bar left-arrow  @click-left="onClickLeft" fixed title="我的收藏"/>
    </div>
  <div v-if="loginIn" style="margin-top:50px;margin-bottom:40px;" v-for="(item,index) in shoucangList" :key="index">
    <van-swipe-cell>
  <van-card
    :price="item.price"
    :desc="item.tripName"
    title="景点名称"
    class="goods-card"
    :thumb="path+item.picImg"
   @click="itemDetail(item)"
  >
    <template #footer>
    景区地址:{{item.address}}
  </template>
  </van-card>
  <template #right>
    <van-button @click="deleteshoucang(item.id)" square text="删除" type="danger" class="delete-button" />
  </template>
</van-swipe-cell>
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
import {queryAllShouCang,deleteshoucang} from '../../network/shoucang.js'
import Scroll from "@/components/common/scroll/Scroll.vue"
import { mapGetters } from 'vuex'
import { Dialog } from 'vant';
export default {
    name: "myshoucang",
    data() {
        return {
            userId:'',
            shoucangList:[],
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
     this.queryAllShouCang();
    },
    methods: {
            //返回到home页面
        onClickLeft() {
            this.$router.go(-1)
        },
           itemDetail(item) {
            this.$store.commit('setTempList',item)
            this.$router.push(`/detail/${item.id}`)
        },
        queryAllShouCang(){
          if(this.loginIn){
          this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
          queryAllShouCang(this.userId).then(res=>{
                this.shoucangList = res.data
            //      for(let item in this.rijiList){
            //   this.rijiList[item].feelScore =  this.rijiList[item].feelScore / 2
            //   //  item.feelScore = item.feelScore / 2
            // }
                console.log(this.rijiList)
          })
          }
        },
        tologin(){
          this.$router.push('/login')
        },
        deleteshoucang(id){
             Dialog.confirm({
              title: '确定删除吗？',
            }).then(()=>{
              this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
              deleteshoucang(this.userId,id).then(res=>{
                   if(res.code == 200){
            this.$toast.success("删除成功!")
              this.queryAllShouCang();
          }else{
            this.$toast.fail("删除失败!")
             }
              }).catch(err => {
                  console.error(err)
              })
            });
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
    .goods-card {
    margin: 0;
    background-color: white;
  }
  .delete-button {
    height: 100%;
  }
</style>