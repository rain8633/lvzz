<template>
<div class="home">
   <the-header/>
   <div class="riji-list">
     <ul class="riji-list-header">
      <li class="active">
        日记详情
      </li>
    </ul>
    <div style="margin-left:250px;width:800px;">
       <el-row :gutter="28">
           <el-col :span="8"><span style="font-size:30px;margin-left:40px;float:left;">标题:&nbsp;&nbsp;{{riji.title}}</span></el-col>
           <el-col :span="5"><el-rate style="margin-top:13px;" v-model="riji.feelScore" disabled></el-rate></el-col>
           <el-col :span="8"><span style="font-size:20px;margin-top:23px;">&nbsp;&nbsp;{{riji.createTime}}</span></el-col>

       </el-row>

    <el-divider  style="left:20px;!important"><i class="el-icon-reading"></i> </el-divider>
    <div>
       <span>{{riji.content}}</span>
       <div>
       <img style="width:400px;height:400px;margin-top:20px;" :src="riji.pic"/>
       </div>
    </div>
    </div>
       <!-- <h4 style="margin-top:50px;margin-left:20px;font-size:3em; font-family: Arial,'方正舒体';color:red">游记详情</h4>
       <hr style="margin-left:10px;width: 100px;background-color:orange"/> -->
  </div>
  <scroll-top/>
  <the-footer/>
</div>

</template>

<script type="text/javascript">

import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ScrollTop from '../../components/ScrollTop.vue'

export default {
  data() {
    return {
       id:'',
       riji:{

       }
    }
  },
  components: {
    TheHeader,
    TheFooter,
    ScrollTop
  },
   created (){
    this.id=this.$route.query.id
    this.rijiDetail()
 },
  methods: {
    rijiDetail(){
      this.$http.post('/RiJi/queryRiJiDetail/'+this.id).then(res=>{
         if(res.data.code==200){
            res.data.data.feelScore = res.data.data.feelScore / 2
            this.riji=res.data.data
            // console.log(this.riji)
         }
      })
    }
  }
}
</script>

<style lang="scss"scoped>
@import '../../assets/css/rijiDetail.scss'
</style>
