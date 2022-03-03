<template>
    <div class="detail_footer_bar">
        <van-goods-action>
            <van-goods-action-icon icon="star-o" text="收藏" @click="addShoucang"/>
            <van-goods-action-button type="danger" text="前往购票" />
        </van-goods-action>
    </div>
</template>
<script>
export default {
    name: "DetailFooterBar",
    methods:{
        addShoucang() {
            // console.log("点击添加到购物车")
            this.$emit('addshoucang')
        }
    }
};
</script>
<style lang="less" scoped>
    .van-goods-action{
        border-top: 1px solid #FBFBFB;
    }
</style>