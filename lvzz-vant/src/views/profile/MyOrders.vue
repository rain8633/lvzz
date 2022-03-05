<template>
  <div>
    <div>
        <van-nav-bar left-arrow  @click-left="onClickLeft" fixed title="我的订单"/>
    </div>
  <div v-if="loginIn" style="margin-top:50px;margin-bottom:40px;" v-for="(item,index) in ordersList" :key="index">
    <!-- <van-swipe-cell>
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
</van-swipe-cell> -->
<van-cell-group inset>
  <van-cell title="订单编号" :value="item.oid" />
  <van-cell title="日期" :value="item.createTime" />
  <van-cell title="景点信息" :value="item.tripName" :label="item.address"/>
  <van-cell title="订单金额" :value="item.totalPrice+'元/人'" />
  <van-cell title="订单状态">
  <!-- 使用 right-icon 插槽来自定义右侧图标 -->
  <template #right-icon>
     <van-tag v-if="item.status==0" type="danger">未支付</van-tag>
     <van-tag v-if="item.status==1" type="success">已支付</van-tag>
     <van-tag v-if="item.status==2" type="primary">手机支付</van-tag>
  </template>
</van-cell>
</van-cell-group>
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
import {findUserOrders} from '../../network/zhifu'
import Scroll from "@/components/common/scroll/Scroll.vue"
import { mapGetters } from 'vuex'
import { Dialog } from 'vant';
export default {
    name: "myorders",
    data() {
        return {
            userId:'',
            ordersList:[],
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
     this.findUserOrders();
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
        findUserOrders(){
          if(this.loginIn){
          this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
          findUserOrders(this.userId).then(res=>{
                this.ordersList = res.data
            //      for(let item in this.rijiList){
            //   this.rijiList[item].feelScore =  this.rijiList[item].feelScore / 2
            //   //  item.feelScore = item.feelScore / 2
            // }
                console.log(this.ordersList)
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