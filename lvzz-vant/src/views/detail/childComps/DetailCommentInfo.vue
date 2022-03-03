<template>
<div>
    <div class="comment_info" v-if="commentInfo.length !==0 ">
         <p style="font-size:20px;margin-top:15px;margin-bottom:5px">精彩评论: 共 {{commentInfo.length}} 条评论</p>
         <ul class="popular" v-for="(item, index) in commentInfo" :key="index">
           <li>
           <div class="user">
            <img :src="'http://localhost:9001/boot'+userPic[index]" />
            <div class="name">{{userName[index] }}</div>
        </div>
        <div class="comment">
            <p>{{ item.content }}</p>
            <div class="center">
                <span class="data">{{item.createTime }}</span>
            </div>
            
        </div>
          </li>
         </ul>
    </div>
     <div class="comment_info" v-if="commentInfo.length ==0 ">
         <p style="font-size:25px;">暂无评论</p>
    </div>
</div>
</template>
<script>
import {dateFormat} from '../../../common/utils.js'
import {getRecommend,getUsers} from '@/network/detail.js'
export default {
    name: "DetailCommentInfo",
    props: [
        'tripId'// 景区ID
        // commentInfo: {
        //     type: Object,
        //     default() {
        //         return {};
        //     }
        // }
    ],
    data() {
        return {
            commentInfo:[],
            userPic: [], // 保存评论用户头像
            userName: [], // 保存评论用户名字
        };
    },
    created() {
         this.getCommend(this.tripId);
    },
    methods: {
         getCommend(id){
            getRecommend(id).then(res => {
           if(res.code== 200){
               this.commentInfo = res.data;
               console.log(this.commentInfo)
           for(let item of this.commentInfo){
               this.getUsers(item.userId)
           }
           }
         }).catch(err=>{
              console.error(err);
         })
     },
       // 获取评论用户的昵称和头像
    getUsers (id) {
      getUsers(id).then(res => {
          console.log(res.data)
        this.userPic.push(res.data.pic);
        // console.log(this.userPic)
        this.userName.push(res.data.userName);
      }).catch(err => {
        console.log(err)
      })
    },
    },
    filters: {
        showDate(value) {
            // //1、将服务器返回过来的时间戳转化为Date对象
            let date = new Date(value*1000) //因为服务器返回的时间是秒，所以要乘以1000;
            //2、将date进行时间格式化
           return dateFormat("yyyy-MM-dd hh:mm", date)
        }
    }
};
</script>
<style lang="less" scoped>
.comment_info {
    margin-top: 0.4rem;
    border-top: 0.06rem solid #ececec;
    padding: 0 0.3rem;
    .user {
        display: flex;
        align-items: center;
        img {
            width: 1.1rem;
            height: 1.1rem;
            border: 1px solid #f5f4f7;
            border-radius: 100%;
            margin: 0.3rem 0.3rem 0 0;
        }
        .name {
            font-size: 0.32rem;
            font-weight: bold;
        }
    }
    .comment {
        p {
            margin-top: 0.3rem;
            font-size: 0.3rem;
            line-height: 0.4rem;
        }
        .center {
            font-size: 0.28rem;
            color: gray;
            line-height: 0.64rem;
            .data {
                margin-right: 0.3rem;
            }
        }
        .img {
            img {
                width: 1.5rem;
                height: 1.5rem;
            }
        }
    }
}
</style>