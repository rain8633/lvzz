<template>
  <div class="home">
   <the-header/>
  <div class="trip-list">
    <ul class="trip-list-header">
      <li class="active">
        搜索结果
      </li>
    </ul>
    <div class="song-content">
        <content-list :contentList="albumDatas"></content-list>
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
  </div>
  <scroll-top/>
  <the-footer/>
</div>

</template>

<script>
import { mapGetters } from 'vuex'
import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ContentList from '../../components/ContentList'
import ScrollTop from '../../components/ScrollTop.vue'


export default {
  name: 'search-song-Lists',
  components: {
    ContentList,
    TheHeader,
    TheFooter,
    ScrollTop
  },
  data () {
    return {
      albumDatas: [],
       searchword: '', // 搜索关键词
         pageSize: 10, // 页数
      currentPage: 1, // 当前页
      total:0,
      userId:''
    }
  },
   computed: {
    ...mapGetters([
      'songsList',
      'loginIn'
    ]),
  },
  mounted () {
    let user = JSON.parse(window.sessionStorage.getItem("user"))
    if(user != null){
       this.userId = user.id
    }
    this.getSearchList()
  },
  methods: {

      handleCurrentChange(pageNum){
      this.currentPage=pageNum
      console.log(this.currentPage)
      this.getSearchList()
    },
    getSearchList () {
        if(this.loginIn){
              if (!this.$route.query.keywords) {
                this.notify('您输入内容为空', 'warning')
               } else if (this.$route.query.keywords) {
               this.$http.get('/trip/queryUserTripsByKeyword',{params:{
                    userId: this.userId,
                    pageSize:this.pageSize,
                    pageNum:this.currentPage,
                    keywords: this.$route.query.keywords
                 }}).then(res => {
                    this.albumDatas = res.data.data.rows
                    this.currentPage = res.data.data.pageNum
                    this.total=res.data.data.total
                  })
            } else {
              this.notify('暂无该景点内容', 'error')
             }
       }  else{

        if (!this.$route.query.keywords) {
        this.notify('您输入内容为空', 'warning')
      } else if (this.$route.query.keywords) {
        this.$http.get('/trip/queryTripsByKeyword',{params:{
           pageSize:this.pageSize,
           pageNum:this.currentPage,
           keywords: this.$route.query.keywords
        }}).then(res => {
            this.albumDatas = res.data.data.rows
            this.currentPage = res.data.data.pageNum
            this.total=res.data.data.total
          })
      } else {
        this.notify('暂无该景点内容', 'error')
      }
    }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/trip-list.scss';
</style>
