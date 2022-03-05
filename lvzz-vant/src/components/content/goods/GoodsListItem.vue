<template>
    <div class="goods_list_item" @click="itemDetail">
        <img v-lazy="showImg" @load="imgLoad"/>
        <p>{{ goodsItem.tripName }}</p>
        <div class="price">
            <p class="price_goods">{{ goodsItem.price }}元/人</p>
            <!-- <p><van-icon name="star" />{{ goodsItem.cfav }}</p> -->
        </div>
    </div>
</template>
<script>
export default {
    name:'GoodsListItem',
    props:{
        goodsItem:{
            type:Object,
            default() {
                return {}
            }
        }
    },
    methods:{
        itemDetail() {
            this.$store.commit('setTempList', this.goodsItem)
            this.$router.push(`/detail/${this.goodsItem.id}`)
        },
        imgLoad() {
            // console.log("a")
            this.$bus.$emit("itemImgLoad")
        },
    },
    computed: {
        showImg() {
            return 'http://localhost:9001/boot'+this.goodsItem.picImg 
        }
    },
}
</script>
<style lang="less" scoped>
    .goods_list_item{
        display: flex;
        width: 46vw;
        height:40vw;
        flex-direction: column;
        padding: 0 2vw;
        margin-bottom: 30px;
        margin-left: 10px;
        img{
            width: 100%;
            height:85%;
            border-radius: 0.1rem;
        }
        p{
            display: -webkit-box;  //超出文字部分用... 表示
            // overflow: hidden;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 1;
            font-size: 0.24rem;
            padding:0 0.08rem;
            margin-top: 0.2rem;
        }
        .price{
            display: flex;
            justify-content: center;
            margin:-1.53rem 0rem 0rem 2rem;
            .price_goods{
                color: #EF232F;
            }
        }
    }
</style>