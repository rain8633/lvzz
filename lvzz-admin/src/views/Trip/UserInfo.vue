<template>
  <div class="userinfo">
    <el-card class="box-card">
  <div>
    <h2>用户信息管理</h2>
  </div>
    <el-table
    :data="tableData.filter(data => !search || data.userName.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="头像">
      <template slot-scope="scope">
        <el-image style="height:70px;width:100px;" :src="path+scope.row.pic"></el-image>
      </template>
    </el-table-column>
    <el-table-column
      label="姓名"
      prop="userName">
    </el-table-column>
     <el-table-column
      label="联系方式"
      prop="phone">
    </el-table-column>
    <!-- <el-table-column
      label="权限"
      prop="role">
      <template slot-scope="scope">
        {{scope.row.role==1 ? '超级管理员' : '普通用户'}}
      </template>
    </el-table-column> -->
    <el-table-column
      align="right">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <!-- <el-button
          size="mini"
          @click="handleupdate(scope.$index, scope.row)">授权</el-button> -->
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
    </el-card>
  </div>
</template>

<script type="text/javascript">
export default {
  name:'userinfo',
  data() {
    return {
      tableData: [],
        search: ''
    }
  },
  components: {

  },
  methods:{

      handleDelete(index, row) {//删除

      this.$confirm('是否删除用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
        // let userId=JSON.parse(window.sessionStorage.getItem("user")).id
        this.$http.delete('/user/deleteUser/'+row.id).then(res=>{
            if(res.data.code==200){
                this.$message.success("删除成功")
                this.queryAllUser()
            }else {
              this.$message.error("删除失败!")
            }
         })
      })
      },
     queryAllUser(){
       this.$http.get('/user/queryAllUser').then(res=>{
        if(res.data.code==200){
           this.tableData = res.data.data
        }
       })

     }
  },
  created(){
    this.queryAllUser()
  }
}
</script>

<style scoped>
</style>
