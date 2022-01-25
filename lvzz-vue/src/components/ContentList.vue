<template>
  <div class="content-list">
    <ul class="section-content">
      <li class="content-item" v-for="(item, index) in contentList" :key="index">
        <div class="kuo" @click="goAblum(item)">
          <img class="item-img" :src="path+item.picImg1" alt="">
        </div>
        <p class="item-name">{{item.tripName || item.title}}&nbsp;&nbsp;{{item.address}}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import { mixin } from '../mixins'

export default {
  name: 'content-list',
  mixins: [mixin],
  props: [
    'contentList'
  ],
  methods: {
    goAblum (item) {
        this.$store.commit('setTempList', item)

        this.$router.push({path: `/trip-detail/${item.id}`})

    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/content-list.scss';
</style>
