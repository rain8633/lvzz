<template>
  <div class="home">
    <the-header/>
    <div class="shoucang-list">
      <ul class="shoucang-list-header">
      <li class="active">
        我的收藏
      </li>
    </ul>
      <el-row :gutter="22">
  <el-col style="margin-left:48px;" :span="5" v-for="(item, index) in shoucangList" :key="index">
    <div @click="goAblum(item)">
    <el-card :body-style="{ padding: '0px' }">
      <img :src="path+item.picImg" class="image">
      <div style="padding: 14px;">
        <span>{{item.tripName}}</span>
        <span>&nbsp;&nbsp;{{item.address}}</span>
        <div>
          <span>联系方式:{{item.phone}}</span>
          <el-tooltip content="取消收藏" placement="top">
            <el-button style="margin-left:20px;" type="danger" icon="el-icon-delete" @click="deleteShouCang(item.id)" circle></el-button>
         </el-tooltip>

        </div>

      </div>
    </el-card>
    </div>
  </el-col>
</el-row>
<div class="pagination">
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
    <scroll-top/>
    <the-footer/>
  </div>
</template>

<script type="text/javascript">

import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ScrollTop from '../../components/ScrollTop.vue'

export default {
  data() {
    return {
        userId:'',
        shoucangList:[],
         pageSize: 4, // 页数
      currentPage: 1, // 当前页
      total:0
    }
  },
  components: {
    TheHeader,
    TheFooter,
    ScrollTop,
  },
  created () {
     this.userId = JSON.parse(window.sessionStorage.getItem('user')).id;
     this.MyShouCang()
  },
  methods: {
     handleCurrentChange(pageNum){
      this.currentPage=pageNum
      console.log(this.currentPage)
      this.MyShouCang()
    },
     MyShouCang(){
        this.$http.get('/shouCang/queryMyShouCang',{params:{
           userId:this.userId,
           pageSize:this.pageSize,
           pageNum:this.currentPage
           }}).then(res =>{
            if(res.data.code == 200){
               this.currentPage = res.data.data.pageNum

              this.total=res.data.data.total
              this.shoucangList= res.data.data.rows
              console.log(this.shoucangList)
            }
        })
     },
       goAblum (item) {
        this.$store.commit('setTempList', item)

        this.$router.push({path: `/trip-detail/${item.id}`})

    },
    deleteShouCang(id) {
       this.$http.delete('/shouCang/deleteShouCang',{params:{
         userId:this.userId,
         tripId:id
       }}).then(res => {
          if(res.data.code == 200){
              this.$message.success("删除成功!")
              this.MyShouCang();
          }else{
            this.$message.error("删除失败")
          }
       }).catch(err => {
         console.error(err)
       })
    }
  }
}
</script>

<style lang="scss"scoped>
@import '../../assets/css/shoucangList.scss'

</style>
