<template>
  <div class="the-footer">
   <p v-for="(item,index) in list" :key="index">
    {{item}}
   </p>
  </div>
</template>

<script type="text/javascript">
export default {
   name: 'the-footer',
  data() {
    return {
      list:
       [
       `关于 | 帮助 | 条款 | 反馈`,
        `Copyright © 2022`,
        `Designed To BiShe`
        ]

    }
  }
}
</script>

<style lang="scss" scoped>
@import "../assets/css/the-footer.scss";
</style>
