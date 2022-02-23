<template>
<div>
  <el-table
    ref="filterTable"
    :data="ordersList"
    style="width: 90%;margin-left:70px;">
     <el-table-column
      prop="oid"
      label="订单编号"
      width="240">
    </el-table-column>
    <el-table-column
      prop="createTime"
      label="日期"
      sortable
      width="200"
      column-key="date"
    >
    </el-table-column>
    <el-table-column
      prop="tripName"
      label="景区名称"
      width="200">
    </el-table-column>
    <el-table-column
      prop="totalPrice"
      label="订单金额"
       width="180"
      :formatter="formatter">
    </el-table-column>
    <el-table-column
      prop="status"
      label="订单状态"
      width="200"
      :filters="[{ text: '已支付', value: 1 }, { text: '未支付', value: 0 }]"
      :filter-method="filterTag"
      filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag v-if="scope.row.status==1"
          :type="'success'"
          disable-transitions>已支付</el-tag>
          <el-tag v-if="scope.row.status==0"
          :type="'primary'"
          disable-transitions>未支付</el-tag>
          <el-button style="margin-left:10px;" v-if="scope.row.status==0" @click="pay(scope.row)" type="warning" size="small"  round>前往支付</el-button>
      </template>
    </el-table-column>
  </el-table>
   <el-dialog
  title="请扫码支付"
  :visible.sync="dialogVisible"
  width="20%">
 <vueQr :text="text" style="margin-left:25px;" :size="210" v-if="!paySucc"></vueQr>
</el-dialog>
</div>
</template>

<script type="text/javascript">
import vueQr from 'vue-qr'
export default {
  name:'orders',
  inject: ['reload'],
  props: [
    'ordersList'
  ],
  created(){
    console.log(this.ordersList)
  },
  data() {
    return {
       dialogVisible:false,
       paySucc:false,
       text:'',

    }
  },
  components: {
     vueQr
  },
  methods: {

      formatter(row, column) {
        return row.totalPrice;
      },
      filterTag(value, row) {
        return row.status === value;
      },
      pay(row){
         let _this = this;
     let params = new URLSearchParams();
     params.append("oid",row.oid)
     params.append("userName",row.userName)
     params.append("phone",row.phone)
     params.append("tripName",row.tripName)
     params.append("address",row.address)
     params.append("totalPrice",row.totalPrice)
     this.$http.post('/order/updateOrderStatus',params).then(res =>{
       if(res.data.code==200){
       this.dialogVisible=true;
          _this.text= res.data.data
          // console.log(_this.text)

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
                                        this.reload();
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

      }
  }
}
</script>

<style scoped>
</style>
