<template>
<div class="home">
  <the-header/>
  <div class="song-list-album">
    <div class="album-slide">
      <div class="album-img">
        <img :src="path+trip.picImg" alt="">
      </div>
      <div class="album-info">
        <h2>简介：</h2>
        <span>
          {{trip.info}}
        </span>
      </div>
    </div>
    <div class="album-content">
      <div class="album-title">
        <p>景点名称:&nbsp;&nbsp;{{trip.tripName}}</p>
      </div>
      <div class="album-address">
        <p>地址:&nbsp;&nbsp;{{trip.address}}</p>
        <p>景区价格:&nbsp;&nbsp;{{trip.price}}元/人 &nbsp;&nbsp;&nbsp;&nbsp;景区级别:&nbsp;&nbsp;{{trip.levelName}}</p>
        <p>联系电话:&nbsp;&nbsp;{{trip.phone}}</p>
        <!-- <el-input-number v-model="num" @change="handleChange" :min="1" :max="10" label="票数"></el-input-number> -->
      </div>
      <!--评分-->
      <div class="album-score">
        <div>
          <h3>景区评分:</h3>
          <div>
            <el-rate v-model="value5" disabled></el-rate>
          </div>
        </div>
           <span>{{value5 * 2}}</span>
        <div>
          <h3>评价：</h3>
          <div @click="pushValue()">
            <el-rate v-model="value3" show-text allow-half></el-rate>
          </div>
        </div>
      </div>
          <div style="margin-top:30px;">
          <el-button v-if="trip.shoucang == false" type="primary" @click="addshoucang(trip.id)">收藏</el-button>
          <el-button v-if="trip.shoucang == true" type="primary" disabled>已收藏</el-button>
          <el-button type="success" @click="buy()">前往购票</el-button>
          </div>
      <!--评论-->
      <div class="songs-body">
        <comment :tripId="tripListId"></comment>
      </div>
    </div>
  </div>
    <scroll-top/>
    <the-footer/>
</div>
</template>

<script>
import { mixin } from '../../mixins'
import { mapGetters } from 'vuex'
import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ContentList from '../../components/ContentList'
import ScrollTop from '../../components/ScrollTop.vue'
import Comment from '../../components/Comment.vue'
// import { getRankOfSongListId, setRank, getSongOfId, getListSongOfSongId } from '../api/index'

export default {
  name: 'trip-detail',
  components: {
    TheHeader,
    TheFooter,
    ContentList,
    ScrollTop,
    // AlbumContent,
    Comment
  },
  data () {
    return {
      tripLists: [],
      trip: {},
      count: 0, // 点赞数
      tripListId: '', // 景点ID
      value3: 0,
      value5:0,
       num: 1
    }
  },
  computed: {
    ...mapGetters([
      'loginIn', // 登录标识
      'tempList', // 单个景点信息
    ])
  },
  created () {
    console.log(this.tempList);
    this.tripListId = this.tempList.id // 给景点ID赋值
    this.trip = this.tempList
    this.getRank(this.tripListId)
  },
  mixins: [mixin],
  methods: {
    pushValue(){
      if(this.loginIn){
      let userId=JSON.parse(window.sessionStorage.getItem("user")).id;
      this.$http.get("/rank/addRank",{params:{
       userId: userId,
       tripId:this.tripListId,
       score:this.value3 * 2
      }}).then(res => {
         if(res.data.code==200){
            this.notify('评分成功!','success')
            this.getRank(this.tripListId)
         }
         else{
           this.notify('评分失败!','error')
         }
      }).catch(err => {
            console.log(err)
          })
      }else{
        this.value3 =null;
        this.notify("请先登录",'warning');
      }
    },
    getRank(id){
      this.$http.post("/rank/queryRankByTripId/"+id).then(res => {
         if(res.data.code == 200){
           this.value5 =res.data.data / 2
         }
      }).catch(err => {
        console.log(err)
      })
    },
    addshoucang(id){
       if(this.loginIn){
      let userId=JSON.parse(window.sessionStorage.getItem("user")).id;
       this.$http.post('/shouCang/addShouCang/'+userId+'/'+id).then(res => {
         if(res.data.code == 200) {
              this.$message.success("收藏成功!")
              this.trip.shoucang=true
         }else{
            this.$message.error("收藏失败!")
         }
       })
    }else{
      this.notify("请先登录",'warning');
    }
  },
   handleChange(value) {
        console.log(value);
    },
  buy(){
    this.$router.push({path: '/buy', query: {trip: this.trip}})
  }
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/trip-list-album.scss';
</style>
