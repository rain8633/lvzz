<template>
    <div class="login">
        <van-nav-bar title="登录/注册" left-arrow @click-left="onClickLeft" />
        <van-field v-model="user.name" type="text" placeholder="请输入用户名" label="用户名" />
        
        <van-field v-model="user.password" type="password" label="密码"  placeholder="请输入密码" />
        <div class="sms">
          <van-field
            v-model="user.code"
             center
              clearable
             label="验证码"
             placeholder="点击图片更换验证码"
            @keydown.enter.native="login()"
         />
        
            <img
            :src="vcUrl"
            @click="updateVerifyCode"
            alt=""
            style="cursor: pointer;margin-left:5px;float:left;height:38px;"
          />
        
       
        </div>
        <div class="login_btn">
            <van-button class="nextBtn" type="primary" size="normal"  @click="login()">登录</van-button>
        </div>
    </div>
</template>
<script>
import {queryUser} from '@/network/user.js'
export default {
    data() {
        return {
            user:{
               name:'',
               password:'',
               code: "",
            },
            vcUrl: "http://localhost:9001/boot/user/verifyCode?time=" + new Date(),
        };
    },
    methods: {
        onClickLeft() {
            this.$router.go(-1);
        },
       
        isBtnConfirm(){
            if(this.sms.length==4){
                this.nextBtn = false;
            }
        },
        updateVerifyCode() {
           this.vcUrl = "http://localhost:9001/boot/user/verifyCode?time=" + new Date();
        },
    
        // 登录
        login(){
            if(this.user.name&&this.user.password){
               queryUser(this.user.name,this.user.password,this.user.code).then(res=>{
                     if (res.code == 3) {
            this.$toast.fail("验证码错误！");
            this.vcUrl = "http://localhost:9001/boot/user/verifyCode?time=" + new Date();
          }else if(res.code == 200){
                window.sessionStorage.setItem("user",res.data);
                 this.$store.commit('setLoginIn', true);
                // console.log(res.data.data);
                this.$router.push('/Profile');
              
               this.$toast.success("欢迎回来");
            }
            else  if(res.code === 4){
             
              this.$toast.fail("用户名或密码错误");
              console.log(res.msg);
              this.vcUrl ="http://localhost:9001/boot/user/verifyCode?time=" + new Date();
            }

               })
            }else{
                this.$toast.fail("用户名和密码不能为空！");
            }
        }
    }
};
</script>
<style lang="less" scoped>
.login {
    .van-nav-bar {
        background-color: #f8f8f8;
    }
    .van-field{
        align-items: center;
        height: 1rem;
    }
    .sms{
        display: flex;
        align-items: center;
        border-bottom: 1px solid #FBFBFC;
        .btnCode{
            width: 2rem;
            margin-right: 0.2rem;
            border-radius: 20px;
        }
    }
    .login_btn{
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 0.6rem;
        width: 100%;
        height: 1rem;
        .nextBtn{
            width: 60%;
            border-radius: 20px;
        }   
    }
}
</style>