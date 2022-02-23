<template>
<div class="home">
  <the-header/>
  <div class="riji-list">
    <ul class="riji-list-header">
      <li class="active">
        购票信息
      </li>
    </ul>
    <div>
     <el-descriptions style="margin-left:60px;!important">
    <el-descriptions-item  label="用户名">{{user.userName}}</el-descriptions-item>
    <el-descriptions-item  label="手机号">{{user.phone}}</el-descriptions-item>
    <el-descriptions-item label="景点名称">{{trip.tripName}}</el-descriptions-item>
    <el-descriptions-item label="备注" v-if="trip.price == 0">
      <el-tag size="small">免费</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="门票价格" v-if="trip.price > 0">
      {{trip.price}}元/人
    </el-descriptions-item>
    <el-descriptions-item label="景点位置">{{trip.address}}</el-descriptions-item>
</el-descriptions>
      <p style="margin-top:10px; margin-left:60px;!important ">购票数量:&nbsp;&nbsp;<el-input-number v-model="num" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number></p>
     <el-button v-if="trip.price != 0" style=" margin-left:60px;!important " type="primary" @click="pay()">前往支付</el-button>
    </div>
  </div>
  <div>
     <el-dialog
  title="请扫码支付"
  :visible.sync="dialogVisible"
  width="20%">
 <vueQr :text="text" style="margin-left:25px;" :size="210" v-if="!paySucc"></vueQr>
</el-dialog>
  </div>
  <scroll-top/>
  <the-footer/>
  </div>
</template>

<script>

import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ScrollTop from '../../components/ScrollTop.vue'
import FacePay from '../../components/FacePay.vue'
import vueQr from 'vue-qr'
export default {
  data() {
    return {
      trip:{},
       userId:'',
       user:{},
       num: 1,
       payResult:'',
       dialogVisible:false,
       paySucc:false,
       text:''

    }
  },
  components: {
     TheHeader,
     TheFooter,
     ScrollTop,
     FacePay,
     vueQr
  },
  created() {
    this.user = JSON.parse(window.sessionStorage.getItem("user"))
    // this.trip=this.$route.query.trip
    this.trip = JSON.parse(window.sessionStorage.getItem("tempList"))

  },
  mounted () {
    // this.userId = JSON.parse(window.sessionStorage.getItem("user")).id

  },
  methods: {
     handleChange(value) {
        console.log(value);
      },
      pay(){
let _this = this;
     let params = new URLSearchParams();
     params.append("userName",this.user.userName)
     params.append("phone",this.user.phone)
     params.append("tripName",this.trip.tripName)
     params.append("address",this.trip.address)
     params.append("totalPrice",this.trip.price * this.num)
     this.$http.post('/order/addOrder',params).then(res =>{
       if(res.data.code==200){
       this.dialogVisible=true;
          _this.text= res.data.data
          console.log(_this.text)

         if ("WebSocket" in window) {
                   // 打开一个 web socket
                            var ws = new WebSocket("ws://localhost:9001/boot/bindingRecord");
                            console.log(ws)
                            ws.onopen = function() {
                                // Web Socket 已连接上，使用 send() 方法发送数据
                                // ws.send("data");
                                // alert("数据发送中...");
                            };

                            ws.onmessage = function(evt) {
                                var received_msg = evt.data;
                                // alert("数据已接收..." + evt.data);
                                if (Boolean(evt.data)) {

                                    setTimeout(() => {
                                          alert("支付成功");
                                        _this.dialogVisible = false;
                                    }, 1000);
                                }

                                ws.close();

                            };

                            ws.onclose = function() {
                                // // 关闭 websocket
                                console.log("连接已关闭...");
                            };
                          } else {
                            // 浏览器不支持 WebSocket
                            alert("您的浏览器不支持 WebSocket!");
                        }

       }
     }).catch((err) => {
          console.log(err)
        })
      },
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/riji.scss';
</style>
