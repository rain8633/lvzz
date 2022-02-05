<template>
 <div class="home">
   <the-header/>
  <div class="trip-list">
    <ul class="trip-list-header">
      <li v-for="(item,index) in tripLevel" :key="index"
      :class="{active:item.name === activeName}"
      @click="handleChangeView(item.name)">
        {{item.name}}
      </li>
    </ul>
    <div class="song-content">
      <content-list :contentList="tripList"></content-list>
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

<script type="text/javascript">
import { mapGetters } from 'vuex'
import {tripLevel} from '../../assets/data/tripList'
import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ContentList from '../../components/ContentList'
import ScrollTop from '../../components/ScrollTop.vue'

export default {
  name:'trip-list',
  data() {
    return {
      tripList:[],
      tripLevel:[],
      activeName: '全部景点',
      pageSize: 10, // 页数
      currentPage: 1, // 当前页
      total:0
    }
  },
  components: {
    TheHeader,
    TheFooter,
    ContentList,
    ScrollTop
  },
  computed: {
    ...mapGetters([
      'songsList'
    ]),
    // 计算当前表格中的数据
    data () {
      return this.tripList.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  mounted () {
    this.tripLevel=tripLevel
    this.handleChangeView('全部景点')
  },
  methods: {

    handleCurrentChange(pageNum){
      this.currentPage=pageNum
      console.log(this.currentPage)
      this.getTripList()
    },
    handleChangeView(name) {
      this.activeName = name
      this.tripList = []
      if (name === '全部景点') {
        this.getTripList()
      } else {
        this.getTripListBylevel(name)
      }
    },
    // 获取全部景点
    getTripList () {
      this.$http.get("/trip/queryTripList",{params:{
           pageSize:this.pageSize,
           pageNum:this.currentPage}})
        .then(res => {
          this.currentPage = res.data.data.pageNum
          this.tripList = res.data.data.rows
          // console.log(this.tripList)
          this.total=res.data.data.total
          // console.log(this.total)
          //  this.pageNum=res.data.data.pageNum
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 通过类别获取景点
    getTripListBylevel(name) {
     this.$http.get("/trip/queryTripListByLevel",{params:{
       pageSize:this.pageSize,
           pageNum:this.currentPage,
           levelName:name
     }}).then(res => {
          this.currentPage = res.data.data.pageNum
          this.tripList = res.data.data.rows
          this.total=res.data.data.total
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/trip-list.scss';
.the-footer{
  margin-top:-60px !important
}
</style>
