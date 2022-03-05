<template>
  <div>
    <div>
        <van-nav-bar left-arrow  @click-left="onClickLeft" fixed title="购票详情"/>
    </div>
  <div style="margin-top:50px;">
      <van-cell-group inset>
  <van-field v-model="user.userName" label="用户名" readonly  />

  <van-field v-model="user.phone"  label="手机号"  readonly />
 
  <van-field v-model="trip.tripName"  label="景点名称" readonly />
 
  <van-field v-model="trip.price"   v-if="trip.price > 0" label="景区价格" readonly />

  <van-field v-model="price"   v-if="trip.price == 0" label="景区价格" readonly  />

  <van-field v-model="trip.address"  autosize  type="textarea" label="景区位置" readonly />

  <van-field name="stepper" label="购票数量">
  <template #input>
    <van-stepper @change="onChange" v-model="num" />
  </template>
</van-field>
<van-field v-model="totalPrice"  autosize  type="textarea" label="总价格" readonly />
<van-button style="margin-left:130px;" round type="primary" @click="zhifu()">前往支付</van-button>
  </van-cell-group>
  
  </div>
    <tab-footer></tab-footer>
  </div>
</template>
<script>
import TabFooter from '@/components/common/TabFooter.vue'
import {addOrder} from '../../network/zhifu.js'
import Scroll from "@/components/common/scroll/Scroll.vue"
import { mapGetters } from 'vuex'
export default {
    
    name: "myriji",
    data() {
        return {
            user:{},
            trip:{},
            userId:'',
            rijiList:[],
            value3:0,
            num:0,
            price:'免费',
            totalPrice:0
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
    this.user = JSON.parse(window.sessionStorage.getItem("user"))
    this.trip = JSON.parse(window.sessionStorage.getItem("tempList"))
    this.totalPrice = this.trip.price
    },
    methods: {
        onChange(value){
           this.totalPrice = this.trip.price * value;
        },
            //返回到home页面
        onClickLeft() {
            this.$router.go(-1)
        },
       
      zhifu(){
        addOrder(this.user.userName,this.user.phone,this.trip.tripName,this.trip.address,this.totalPrice).then(res=>{
           if(res.code ==200){
               this.$toast.success("支付成功!")
               this.$router.push("/home")
           }else{
                this.$toast.fail("支付失败!")
           }
        })
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