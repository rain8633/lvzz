<template>
    <div class="my">
        <div class="header">
            <div class="title">我的信息</div>
            <div class="info" v-if="user==null">
                <img src="../../assets/img/profile/avatar.svg">
                <div class="center">
                    <p class="login" @click="login()">登录/注册</p>
                    <!-- <p class="iphone"><van-icon name="graphic" />暂无绑定的手机号</p> -->
                </div>
                <div class="i">
                    <van-icon name="arrow" />
                </div>
            </div>
            <div class="info" v-if="user!=null">
                 <img :src="'http://localhost:9001/boot'+user.pic">
                <div class="center">
                    <p style="font-size:20px;margin-left:-250px;">{{user.userName}}</p>
                    <!-- <p class="iphone"><van-icon name="graphic" />暂无绑定的手机号</p> -->
                </div>
            </div>
        </div>
        <div class="news">
            <div>
                <img src="@/assets/img/profile/message.svg">
                <span>我的订单</span>
            </div>
            <div class="shop">
                <img src="../../assets\/img/profile/pointer.svg">
                <p>我的日记</p>
            </div>
            <div>
                <img src="../../assets/img/profile/message.svg">
                <p @click="shoucang()">我的收藏</p>
            </div>
            <div>
                <img src="../../assets/img/profile/vip.svg">
                <p @click="loginout()">退出登录</p>
            </div>
        </div>
        <tab-footer></tab-footer>
    </div>
</template>
<script>
import TabFooter from '@/components/common/TabFooter.vue'

export default {
    components: {
      TabFooter
    },
    data(){
       return{
           user:{}
       }
    },
    created() {
       this.user = JSON.parse(window.sessionStorage.getItem("user"))
    },
    methods:{
        login(){
            this.$router.push('/login')
        }, 
        loginout(){
             this.$store.commit('setLoginIn', false)
        //  window.sessionStorage.clear()
          window.sessionStorage.removeItem("user");
         this.$router.go(0)
        }
    }
}
</script>
<style lang="less">
    .my{
        .header{
            padding: 0.2rem 0.2rem 0.4rem 0.2rem;
            height: 2rem;
            background-color: #FF8E96;
            .title{
                color: white;
                font-size: 0.4rem;
                width: 1.6rem;
                line-height: 0.66rem;
                margin: 0 auto;
                text-align: center;
            }
            .info{
                display: flex;
                align-items: center;
                justify-content:space-between;
                color: white;
                margin-top: 0.1rem;
                img{
                    width: 1.5rem;
                    height: 1.5rem;
                }
                .center{
                    margin-left: -1.5rem;
                    .login{
                        font-size: 0.4rem;
                    }
                    .iphone{
                        display: flex;
                        align-items: center;
                        font-size: 0.32rem;
                    }
                }
                .i{
                    font-size: 0.5rem;
                }
            }
        }
        .detail{
            display: flex;
            height: 1.8rem;
            border-bottom: 0.12rem solid #F2F2F2;
            .center{
                border-left: 1px solid #FAFAFA;
                border-right: 1px solid #FAFAFA;
            }
            div{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                flex: 1;
                font-size: 0.32rem;
                p{
                    span{
                        color: #E67962;
                        font-weight: bold;
                    }
                }
            }
        }
        .news{
            display: flex;
            flex-direction: column;
            height: 3.2rem;
            padding: 0 0.3rem;
            border-bottom: 0.12rem solid #F2F2F2;
            .shop{
                p{  
                    display: flex;
                    align-items: center;
                    width: calc(100% - 0.6rem);
                    height: 100%;
                    border-top: 1px solid #f2f2f2;
                    border-bottom: 1px solid #f2f2f2;
                }
            }
            div{
                flex: 1;
                display: flex;
                align-items: center;
                font-size: 0.32rem;
                img{
                    margin-right: 0.2rem;
                    width: 0.6rem;
                    height: 0.6rem;
                }
            }
        }
        .cart{
            padding: 0 0.2rem;
            display: flex;
            justify-content: center;
            flex-direction: column;
            height: 2.1rem;
            div{
                flex: 1;
                display: flex;
                align-items: center;
                font-size: 0.32rem;
                img{
                    width: 0.6rem;
                    height: 0.6rem;
                }
                .app{
                    display: flex;
                    align-items: center;
                     width: calc(100% - 0.6rem);
                     height: 100%;
                     border-top: 1px solid #f2f2f2;
                }
            }
        }
    }
</style>