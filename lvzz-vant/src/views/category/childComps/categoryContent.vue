<template>
    <div class="category_content">
        <div class="title">
            <div :class="{active: countIndex==index}" v-for="(item,index) in categoryList" :key="index" @click="indexChange(index,item)">{{ item.name }}</div>
        </div>
        <div class="content">
            <div  style="margin-bottom: 50px;" @click="itemDetail(item)" v-for="(item,index) in cateContentList" :key="index">
                <!-- <a :href="item.link"> -->
                <img :src="'http://localhost:9001/boot'+item.picImg">
                <p>{{ item.tripName }}</p>
                <!-- </a> -->
            </div>
        </div>
    </div>
</template>
<script>
import {getSubCategory,getAllCategory} from '../../../network/category.js'
export default {
    props: {
        categoryList: {
            type: Array,
            default() {
                return [];
            }
        }
    },
    data() {
        return {
            countIndex:0,
            name:'',
            cateContentList:[]
        };
    },
    created() {
       this.getAllCategory()
    },
    methods:{
        itemDetail(item) {
            this.$store.commit('setTempList',item)
            this.$router.push(`/detail/${item.id}`)
        },
        getAllCategory(){
           getAllCategory().then((res)=>{
            console.log(res)
             this.cateContentList = res.data;
             console.log(this.cateContentList)
        });
        },
        indexChange(index,item){
           this.countIndex = index;
            if(item.name==='全部景点'){
                this.getAllCategory()
            }else{
            this.name = item.name
            console.log(this.name);
            getSubCategory(this.name).then((res)=>{
                console.log(res);
                this.cateContentList = res.data;
            });
            }
        }
    }
};
</script>

<style lang="less" scoped>
.category_content {
        margin-top: 46px;
        overflow: hidden; position:fixed; top:0; bottom:0; left:0; right:0;
    .title {
        background-color: #F7F7F7;
        text-align: center;
        float:left; 
        overflow:auto; 
        width:30%;
        height: 100%;
        div {
            line-height: 0.9rem;
            font-size: 0.3rem;
        }
        div:hover{
            cursor: pointer;
        }
        .active{
            background-color: #ffffff;
            border-left: 3px solid #FF8A9D;
        }
    }
    .content{
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
        float:left;
       
         overflow:auto; 
         width:70%; 
         height: 100%;
         div{
             img{
                 width: 2rem;
                 height: 2rem;
             }
             p{
                 font-size: 0.36rem;
             }
         }
    }
}
</style>