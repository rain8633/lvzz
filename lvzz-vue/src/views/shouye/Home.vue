<template>
  <div class="home">
    <the-header/>
    <swiper/>
  </div>
</template>


<script type="text/javascript">

import Swiper from "../../components/Swiper.vue"
import TheHeader from '../../components/TheHeader.vue'

export default {
  data() {
    return {

    }
  },
  components: {
    Swiper,
    TheHeader,
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/home.scss';
</style>
