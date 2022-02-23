<template>
  <div class="Collections">
<el-card class="box-card">
  <div>
    <h2>所有订单</h2>
  </div>
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
      prop="userName"
      label="用户名"
      width="100">
    </el-table-column>
    <el-table-column
      prop="tripName"
      label="景区名称"
      width="100">
    </el-table-column>
     <el-table-column
      prop="phone"
      label="用户联系方式"
      width="150">
    </el-table-column>
    <el-table-column
      prop="totalPrice"
      label="订单金额"
       width="100"
    >
    </el-table-column>
    <el-table-column
      prop="status"
      label="订单状态"
      width="100"
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
      </template>
    </el-table-column>

    <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
		  <el-button @click="deleteOrder(scope.row)" type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
  </el-table>
</el-card>

 <div class="pagination" style="margin-top:25px;margin-left:70px;">
        <el-pagination
         @current-change="handleCurrentChange"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total">
        </el-pagination>
    </div>

  </div>
</template>

<script>

export default {
  name:"AllRiJi",
  data() {
    return {
       ordersList:[],
         pageSize:5, // 页数
      currentPage: 1, // 当前页
      total:0,
    }
  },
  components: {

  },
   created() {
    this.queryAllOrders()
  },
  methods:{
     handleCurrentChange(pageNum){
      this.currentPage=pageNum
      // console.log(this.currentPage)
       this.queryAllOrders()
    },

      queryAllOrders(){
           let params = new URLSearchParams();

       params.append("pageSize",this.pageSize);
       params.append("pageNum",this.currentPage)
        this.$http.post("/order/queryAllUserOrders",params).then(res=>{
          if(res.data.code == 200){
             this.ordersList = res.data.data.rows
             this.total=res.data.data.total
           this.currentPage=res.data.data.pageNum
          }
        }).catch(err => {
          console.log(err)
        })
      },

  filterTag(value, row) {
        return row.status === value;
      },

    deleteOrder(row){
      this.$confirm('是否删除订单?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
        // console.log(row.id)
        // console.log(this.userId)
         this.$http.get('/order/deleteorderById',{params:{
              oid: row.oid,
         }}).then(res => {
            if(res.data.code == 200){
                this.$message.success("删除成功!")
                this.queryAllOrders()
            }else{
              this.$message.error("删除失败!请稍后重试")
            }
         }).catch(err => {
           console.error(err)
         })
    })
    }
  },

}
</script>

<style scoped>
</style>
