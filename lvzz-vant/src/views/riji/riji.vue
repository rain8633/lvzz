<template>
  <div>
    <riji-tab></riji-tab>
    <div class="riji">
       <!-- 可以使用 CellGroup 作为容器 -->
          <van-field
      v-model="title"
      name="标题"
      label="标题"
      placeholder="请输入日记标题"
      :rules="[{ required: true, message: '请输入日记标题' }]"
    />

    <van-field name="rate" label="评分">
       <template #input>
          <van-rate v-model="level" />
        </template>
    </van-field>
 
   <div id="summernote"></div>

    </div>
      <tab-footer></tab-footer>
  </div>
</template>

<script type="text/javascript">
import TabFooter from '@/components/common/TabFooter.vue'
import rijiTab from './childComps/rijiTab.vue' //顶部标题栏
export default {
  data() {
    return {
       title:'',
       level:''
    }
  },
  components: {
     TabFooter,
     rijiTab,
    
  }
}
</script>

<style lang="less"scoped>
.riji{
  margin-top:50px;
}
</style>
