<template>
  <div>
    <div class="comment">
      <h2>评论</h2>
      <div class="comment-msg">
        <div class="comment-img">
          <img :src="path+pic" alt="">
        </div>
        <el-input
          class="comment-input"
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="textarea">
        </el-input>
      </div>
      <el-button type="primary" class="sub-btn" @click="postComment()">评论</el-button>
    </div>
    <p>精彩评论: 共 {{commentList.length}} 条评论</p>
    <ul class="popular" v-for="(item, index) in commentList" :key="index">
      <li>
        <div class="popular-img">
          <img :src="path+userPic[index]" alt="">
        </div>
        <div class="popular-msg">
          <ul>
            <li class="name">{{userName[index]}}</li>
            <li class="time">{{item.createTime}}</li>
            <li class="content">{{item.content}}</li>
          </ul>
        </div>
        <div class="up" ref="up" @click="postUp(item.id, item.up, index)">
          <!-- <div class="icon">
             <img :src="img1" alt="">
          </div> -->
          <!-- <div class="icon" :visible="cancledianzanVisible">
             <img :src="require('../assets/img/zan2.png')" >
          </div> -->
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-zan"></use>
          </svg>
          {{item.up}}
        </div>
      </li>
    </ul>
  </div>
</template>

<script>

import {mixin} from '../mixins'
import { mapGetters } from 'vuex'
// import { getUserOfId, setComment, setLike, getAllComment } from '../api/index'

export default {
  name: 'comment',
  mixins: [mixin],
  props: [
    'tripId', // 景区ID
  ],
  data () {
    return {
      id:'',
      pic:'',
      commentList: [], // 存放评论内容
      userPic: [], // 保存评论用户头像
      userName: [], // 保存评论用户名字
      textarea: '', // 存放输入内容
      dianzanVisible:true,
      cancledianzanVisible:false,
      // img1:require("../../static/zan1.png"),
      // url2:require('../assets/img/zan2.png'),
    }
  },
  computed: {
    ...mapGetters([
      'loginIn', // 用户是否登录
    ])
  },
  watch: {
    id () {
      this.getAllComment()
    }
  },
  mounted () {
    this.getAllComment()
    this.pic=JSON.parse(window.sessionStorage.getItem("user")).pic;
    // console.log(this.pic)
    this.id=JSON.parse(window.sessionStorage.getItem("user")).id;
  },
  methods: {
    // 获取所有评论
    getAllComment () {
      this.$http.post("/comment/getAllComment/"+this.tripId).then(res=>{
           this.commentList = res.data.data;
           for(let item of this.commentList){
               this.getUsers(item.userId)
           }
      }).catch(err=>{
        console.error(err);
      })
    },
    // 获取评论用户的昵称和头像
    getUsers (id) {
      this.$http.post("/user/queryUserById/"+id).then(res => {
        this.userPic.push(res.data.data.pic);
        // console.log(this.userPic)
        this.userName.push(res.data.data.userName);
      }).catch(err => {
        console.log(err)
      })
    },
    // 提交评论
    postComment () {
      if (this.loginIn) {
        let params = new URLSearchParams()
          params.append('tripId', this.tripId)
        params.append('userId', this.id)
        params.append('content', this.textarea)
        this.$http.post("/comment/addComment", params)
          .then(res => {
            if (res.data.code == 200) {
              this.textarea = ''
              this.getAllComment()
              this.notify('评论成功', 'success')
            } else {
              this.notify('评论失败', 'error')
            }
          })
          .catch(err => {
            console.log(err)
          })
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    // 点赞
    postUp (id, up, index) {
      if (this.loginIn) {
        let params = new URLSearchParams()
        params.append('id', id)
        params.append('up', up + 1)
        this.$http.post('/comment/setTripLike',params).then(res => {
            if (res.data.code === 200) {
              this.$refs.up[index].children[0].style.color = '#2796dd'
              this.getAllComment()
            }
          })
          .catch(err => {
            console.log(err)
          })
      } else {
        this.notify('请先登录', 'warning')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/comment.scss';
</style>
