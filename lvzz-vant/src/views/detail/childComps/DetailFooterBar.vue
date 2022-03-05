<template>
    <div class="detail_footer_bar">
        <van-goods-action>
            <van-goods-action-icon icon="star-o" v-if="!shoucang" text="收藏" @click="addShoucang"/>
            <van-goods-action-icon icon="star" color="red" v-if="shoucang"  text="已收藏"/>
            <van-goods-action-button type="danger" @click="buy" text="前往购票" />
        </van-goods-action>
    </div>
</template>
<script>
import {addshoucang} from '../../../network/shoucang.js'
import { mapGetters } from 'vuex'
export default {
    name: "DetailFooterBar",
    props:[
         'shoucang',
         'id'
    ],
     computed: {
    ...mapGetters([
      'loginIn', // 登录标识
      'tempList', // 单个景点信息
    ])
  },
    methods:{
       
    addShoucang(){
        if(this.loginIn){
          let userId=JSON.parse(window.sessionStorage.getItem("user")).id;
           addshoucang(userId,this.id).then(res=>{
             if(res.code == 200) {
             this.$toast.success("收藏成功!")
            this.shoucang=true
         }else{
             this.$toast.fail("收藏失败!")
         }
           })
        }else{
          this.$toast.fail("请先登录")
        }
            // console.log("点击添加到购物车")
            // this.$emit('addshoucang')
        },
        buy(){
            this.$emit('buy')
        }
    }
};
</script>
<style lang="less" scoped>
    .van-goods-action{
        border-top: 1px solid #FBFBFB;
    }
</style>