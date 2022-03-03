<template>
    <div class="category">
        <category-tab></category-tab>
        <category-content :categoryList="categoryList"></category-content>
        <tab-footer></tab-footer>
    </div>
</template>
<script>

//网络请求js模块
// import {getCategory} from '@/network/category.js'
import {tripLevel} from '../../assets/data/tripList'
//组件
import categoryTab from './childComps/cartgoryTab.vue' //顶部标题栏
import TabFooter from '@/components/common/TabFooter.vue'
import categoryContent from './childComps/categoryContent.vue' //分类页主要区域
export default {
    name:'Category',
    data() {
        return{
            categoryList:[],
        }
    },
    created() { 
        this.categoryList = tripLevel
    },
    components:{
        categoryTab,
        categoryContent,
        TabFooter
    }
}
</script>
<style lang="less" scoped>
.category{
    .category-content{
        margin-bottom: 1rem;
    }
}
</style>